package com.sandarovich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private static final String PAGE_INDEX = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage() {
        return PAGE_INDEX;
    }
}
