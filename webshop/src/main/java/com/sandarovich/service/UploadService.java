package com.sandarovich.service;

import com.sandarovich.fileupload.model.File;


public interface UploadService {
    void setFile(File file);

    void uploadFiletoDB();

}
