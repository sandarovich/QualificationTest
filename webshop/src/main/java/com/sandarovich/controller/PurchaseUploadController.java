package com.sandarovich.controller;

import com.sandarovich.fileupload.JsonFileValidator;
import com.sandarovich.model.JsonFile;
import com.sandarovich.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/upload")

public class PurchaseUploadController {

    private static final String UPLOAD_PAGE = "upload";
    private static final String SUCCESS_UPLOAD_PAGE = "successUpload";

    @Autowired
    private JsonFileValidator jsonFileValidator;

    @Autowired
    private UploadService uploadService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(jsonFileValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model) {

        JsonFile jsonFile = new JsonFile();
        model.addAttribute("jsonFile", jsonFile);
        return UPLOAD_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String uploadFile(Model model, @Validated JsonFile jsonFile, BindingResult result) {

        if (result.hasErrors()) {
            return UPLOAD_PAGE;
        }
        uploadService.setJsonFile(jsonFile);
        uploadService.uploadFileToDB();
        return SUCCESS_UPLOAD_PAGE;
    }

}
