package com.sandarovich.fileupload.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class File {
    private static final String ENCODING = "UTF-8";
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private String asText() throws IOException {
        return IOUtils.toString(file.getInputStream(), ENCODING);
    }

    public JsonObject asJsonObject() throws IOException, JsonParseException {
        JsonParser parser = new JsonParser();
        return parser.parse(asText()).getAsJsonObject();

    }
}
