package com.sandarovich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/purchase")

public class PurchaseFileUpload {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showPurchaseAddForm() {
        return "purchase";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile() {
        return "";
    }


}
