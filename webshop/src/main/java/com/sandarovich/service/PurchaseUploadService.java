package com.sandarovich.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sandarovich.dao.ProductDao;
import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.fileupload.ParseException;
import com.sandarovich.model.*;
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
    private JsonFile jsonFile;
    private List<PurchaseData> purchaseData;

    @Override
    public void setJsonFile(JsonFile jsonFile) {
        this.jsonFile = jsonFile;
    }

    @Override
    public void uploadFiletoDB() throws JsonParseException {
        parseFile();

        Purchase purchase = purchaseDao.save();

        for (PurchaseData item : purchaseData) {
            Product product = new Product();
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
            JsonObject jsonObject = jsonFile.asJsonObject();
            JsonArray data = jsonObject.get(ROOT_JSON_KEY).getAsJsonArray();
            Type listType = new TypeToken<ArrayList<PurchaseData>>() {
            }.getType();
            this.purchaseData = new Gson().fromJson(data, listType);
        } catch (IOException e) {
            throw new ParseException("Unable to parse JSON");
        }
    }
}
