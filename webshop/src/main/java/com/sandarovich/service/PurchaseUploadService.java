package com.sandarovich.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandarovich.dao.ProductDao;
import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.dto.PurchaseDTO;
import com.sandarovich.dto.PurchaseDTOHolder;
import com.sandarovich.model.Product;
import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PurchaseUploadService implements UploadService {

    private static final Logger logger = Logger.getLogger(PurchaseUploadService.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PurchaseDao purchaseDao;
    private String json;
    private List<PurchaseDTO> data;

    @Override
    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public void uploadFileToDB() {
        parseFileFromJson(json);
        Purchase purchase = purchaseDao.save();

        for (PurchaseDTO item : data) {
            Product product = getProduct(item);

            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setProduct(product);
            purchaseItem.setQuantity(item.getCount());
            purchaseItem.setPurchase(purchase);

            purchaseDao.saveItem(purchaseItem);
        }
    }

    private Product getProduct(PurchaseDTO dtoItem) {
        Product product = new Product();
        if (!productDao.isExist(dtoItem.getProduct())) {
            product.setName(dtoItem.getProduct());
            // O.K We expect that sum in file = Grand total sum for one product,
            // so we need to calculate price for one dtoItem
            product.setPrice(dtoItem.getSum() / dtoItem.getCount());
            product = productDao.save(product);
        } else {
            product = productDao.getByName(dtoItem.getProduct());
        }
        return product;
    }

    private void parseFileFromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PurchaseDTOHolder purchaseDTOHolder = mapper.readValue(json, PurchaseDTOHolder.class);
            data = purchaseDTOHolder.getPurchaseDTOList();

        } catch (IOException e) {
            logger.error("JSON Parsing problem", e);
        }


    }
}
