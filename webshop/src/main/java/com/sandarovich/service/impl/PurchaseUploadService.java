package com.sandarovich.service.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sandarovich.model.PurchaseData;
import com.sandarovich.service.UploadService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(PurchaseUploadService.class);

    private MultipartFile file;

    @Override
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String saveToBD() {
        return parseFile();
    }

    public String parseFile() throws ParseFileException {
        String fileAsString;
        ByteArrayInputStream stream;
        try {
            stream = new ByteArrayInputStream(file.getBytes());
            fileAsString = IOUtils.toString(stream, ENCODING);
        } catch (IOException e) {
            logger.error("Unable to read file", e);
            throw new ParseFileException("Unable to read file" + e.getMessage());
        }

        JsonParser parser = new JsonParser();
        JsonObject jsonObject;
        try {
            jsonObject = parser.parse(fileAsString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            logger.error("Problems in JSON file. Validate http://jsonlint.com/ first", e);
            throw new ParseFileException("Problems in JSON file.  Validate http://jsonlint.com/ first");
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
