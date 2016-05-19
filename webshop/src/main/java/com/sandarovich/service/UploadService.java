package com.sandarovich.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    boolean isFileUploaded();

    void setFile(MultipartFile file);

    String saveToBD();

}
