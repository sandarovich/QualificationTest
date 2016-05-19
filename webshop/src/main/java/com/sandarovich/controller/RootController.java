package com.sandarovich.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RootController {

    private static final String PAGE_INDEX = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showApplicationRootPage() {
        return new ModelAndView(PAGE_INDEX);
    }
}
