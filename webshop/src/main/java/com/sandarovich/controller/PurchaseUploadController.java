package com.sandarovich.controller;

import com.sandarovich.fileupload.model.File;
import com.sandarovich.fileupload.validation.FileValidator;
import com.sandarovich.fileupload.validation.ParseException;
import com.sandarovich.service.UploadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.PersistenceException;

@Controller
@RequestMapping(value = "/upload")

public class PurchaseUploadController {

    private static final Logger logger = Logger.getLogger(PurchaseUploadController.class);

    @Autowired
    FileValidator fileValidator;

    @Autowired
    private UploadService uploadService;

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
    public String uploadFile(Model model, @Validated File file, BindingResult result) {

        String returnVal = "successFile";

        if (result.hasErrors()) {
            return "upload";
        } else {
            try {
                uploadService.setFile(file);
                uploadService.parseFile();
                uploadService.uploadFile();
            } catch (ParseException e) {
                model.addAttribute("file", "Unable to parse JSON");
            } catch (PersistenceException e) {
                model.addAttribute("file", "Unable to Save to BD");
            }

            return returnVal;
        }
    }

}
