package com.sandarovich.service.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandarovich.model.PurchaseData;
import com.sandarovich.service.UploadService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Service
public class PurchaseUploadService implements UploadService {

    private static final String ENCODING = "UTF-8";
    private static final String ROOT_JSON_KEY = "data";
    private MultipartFile file;

//    @Autowired
//    PurchaseDao purchaseDao;
//
//    @Autowired
//    ProductDao productDao;


    @Override
    public boolean isFileUploaded() {

        return MediaType.APPLICATION_JSON.equals(MediaType.valueOf(file.getContentType()))
                && file.getSize() > 0;
    }

    @Override
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String saveToBD() {
        return parseFile();
    }

    private String parseFile() throws ParseFileException {
        String fileAsString;
        ByteArrayInputStream stream;
        try {
            stream = new ByteArrayInputStream(file.getBytes());
            fileAsString = IOUtils.toString(stream, ENCODING);
        } catch (IOException e) {
            throw new ParseFileException("Unable to read file" + e.getMessage());
        }

        JsonParser parser = new JsonParser();
        JsonObject jsonObject;
        try {
            jsonObject = parser.parse(fileAsString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            throw new ParseFileException("Possible problems in JSON content in file" + e.getMessage());
        }
        JsonArray data = jsonObject.get(ROOT_JSON_KEY).getAsJsonArray();

        Type listType = new TypeToken<ArrayList<PurchaseData>>() {
        }.getType();
        ArrayList<PurchaseData> purchases = new Gson().fromJson(data, listType);

        String result = "";
        for (PurchaseData purchase : purchases) {
            result += purchase.getProduct() + "\n " +
                    purchase.getCount() + "\n " +
                    purchase.getSum() + "\n ";
        }
        return result;


    }
}
