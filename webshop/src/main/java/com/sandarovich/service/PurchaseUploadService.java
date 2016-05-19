package com.sandarovich.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sandarovich.fileupload.model.File;
import com.sandarovich.fileupload.validation.ParseException;
import com.sandarovich.model.PurchaseData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseUploadService implements UploadService {

    private static final String ROOT_JSON_KEY = "data";

    private File file;
    private List<PurchaseData> purchaseData;

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void uploadFile() {

    }

    @Override
    public void parseFile() throws JsonParseException {
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
