package com.sandarovich.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class JsonFile {
    private static final String ENCODING = "UTF-8";
    private MultipartFile jsonFile;

    public MultipartFile getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(MultipartFile jsonFile) {
        this.jsonFile = jsonFile;
    }

    private String asText() {
        try {
            return IOUtils.toString(jsonFile.getInputStream(), ENCODING);
        } catch (IOException e) {
            return "";
        }

    }

    public JsonObject asJsonObject() throws JsonParseException {
        JsonParser parser = new JsonParser();
        return parser.parse(asText()).getAsJsonObject();

    }
}
