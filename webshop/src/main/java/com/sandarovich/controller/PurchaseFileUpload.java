package com.sandarovich.controller;

import com.sandarovich.fileupload.model.File;
import com.sandarovich.fileupload.validaton.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/upload")

public class PurchaseFileUpload {

    @Autowired
    FileValidator fileValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model) {
        File file = new File();
        model.addAttribute("file", file);
        return "upload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fileUploadFile(Model model, @Validated File file, BindingResult result) {

        String returnVal = "successFile";

        if (result.hasErrors()) {
            return returnVal = "upload";
        } else {
            MultipartFile multipartFile = file.getFile();
            model.addAttribute("fileName", multipartFile.getContentType());
        }

        return returnVal;
    }


}
