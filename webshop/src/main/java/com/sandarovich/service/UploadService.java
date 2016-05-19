package com.sandarovich.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    void setFile(MultipartFile file);

    String parseFile();

    String saveToBD();

}
