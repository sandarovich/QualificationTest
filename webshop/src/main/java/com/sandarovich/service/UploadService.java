package com.sandarovich.service;

import com.sandarovich.fileupload.model.File;
import com.sandarovich.fileupload.validation.ParseException;


public interface UploadService {
    void setFile(File file);

    void parseFile() throws ParseException;

    void uploadFile();

}
