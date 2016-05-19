package com.sandarovich.service;

import com.sandarovich.model.JsonFile;


public interface UploadService {
    void setJsonFile(JsonFile jsonFile);

    void uploadFiletoDB();

}
