package com.sandarovich.controller;

import com.sandarovich.model.UploadForm;
import com.sandarovich.service.UploadService;
import com.sandarovich.validator.UploadFormValidator;
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
    private static final String REDIRECT_PATH = "redirect:upload/success";

    @Autowired
    private UploadFormValidator uploadFormValidator;

    @Autowired
    private UploadService uploadService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(uploadFormValidator);
    }


    @RequestMapping(method = RequestMethod.GET)

    public String getUploadForm(Model model) {
        UploadForm uploadForm = new UploadForm();
        model.addAttribute("uploadForm", uploadForm);
        return UPLOAD_PAGE;
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String showSuccessUpload(Model model) {
        return SUCCESS_UPLOAD_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)

    public String uploadFile(Model model, @Validated UploadForm uploadForm, BindingResult result) {
        if (result.hasErrors()) {
            return UPLOAD_PAGE;
        }
        uploadService.setJson(uploadForm.getJson());
        uploadService.uploadFileToDB();
        return REDIRECT_PATH;
    }

}
