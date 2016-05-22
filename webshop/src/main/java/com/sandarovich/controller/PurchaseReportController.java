package com.sandarovich.controller;


import com.sandarovich.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/report")
public class PurchaseReportController {

    public static final String REPORT_PAGE = "report";
    @Autowired
    ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showReportPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(REPORT_PAGE);
        return modelAndView;
    }

    @RequestMapping(value = "{month}", method = RequestMethod.GET, produces = "application/json")
    public String showReport(@PathVariable final int month) {
        return reportService.getReport(month);
    }
}
