package com.sandarovich.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sandarovich.dao.ProductDao;
import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.model.Product;
import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseItem;
import com.sandarovich.model.PurchaseProxy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseUploadService implements UploadService {

    private static final Logger logger = Logger.getLogger(PurchaseUploadService.class);

    private static final String ROOT_JSON_KEY = "data";

    @Autowired
    ProductDao productDao;

    @Autowired
    PurchaseDao purchaseDao;
    private String json;
    private List<PurchaseProxy> purchaseProxy;

    @Override
    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public void uploadFileToDB() {
        parseFileFromJson(json);
        Purchase purchase = purchaseDao.save();

        for (PurchaseProxy item : purchaseProxy) {
            Product product = new Product();
            if (!productDao.isExist(item.getProduct())) {
                product.setName(item.getProduct());
                // O.K We expect that sum in file = Grand total sum for one product,
                // so we need to calculate price for one item
                product.setPrice(item.getSum() / item.getCount());

                product = productDao.save(product);
            } else {
                product = productDao.getByName(item.getProduct());
            }

            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setProduct(product);
            purchaseItem.setQuantity(item.getCount());
            purchaseItem.setPurchase(purchase);

            purchaseDao.saveItem(purchaseItem);
        }

    }

    private void parseFileFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonArray data = jsonObject.get(ROOT_JSON_KEY).getAsJsonArray();
        Type listType = new TypeToken<ArrayList<PurchaseProxy>>() {
        }.getType();
        this.purchaseProxy = new Gson().fromJson(data, listType);
    }
}
