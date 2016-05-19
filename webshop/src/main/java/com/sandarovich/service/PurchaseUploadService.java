package com.sandarovich.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sandarovich.dao.ProductDao;
import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.fileupload.model.File;
import com.sandarovich.fileupload.validation.ParseException;
import com.sandarovich.model.Product;
import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseData;
import com.sandarovich.model.PurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseUploadService implements UploadService {

    private static final String ROOT_JSON_KEY = "data";
    @Autowired
    ProductDao productDao;
    @Autowired
    PurchaseDao purchaseDao;
    private File file;
    private List<PurchaseData> purchaseData;

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void uploadFiletoDB() throws JsonParseException {
        parseFile();

        Purchase purchase = purchaseDao.save();

        for (PurchaseData item : purchaseData) {
            Product product = null;
            if (!productDao.isExist(item.getProduct())) {
                product.setName(item.getProduct());
                // O.K We expect that sum = total sum, so we calculate price for one item
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

    private void parseFile() throws JsonParseException {
        try {
            JsonObject jsonObject = file.asJsonObject();
            JsonArray data = jsonObject.get(ROOT_JSON_KEY).getAsJsonArray();
            Type listType = new TypeToken<ArrayList<PurchaseData>>() {
            }.getType();
            this.purchaseData = new Gson().fromJson(data, listType);
        } catch (IOException e) {
            throw new ParseException("Unable to parse JSON");
        }
    }
}
