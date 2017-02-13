package com.menuAndersen.controller;

import com.google.gson.Gson;
import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.Employees;
import com.menuAndersen.model.MyDate;
import com.menuAndersen.service.BasicService;
import com.menuAndersen.service.ComplexesService;
import com.menuAndersen.service.EmployeesService;
import com.menuAndersen.service.MyDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    @Autowired(required = true)
    private EmployeesService employeesService;

    @Autowired(required = true)
    private MyDateService dateService;

    @Autowired(required = true)
    private BasicService basicService;

    @Autowired(required = true)
    private ComplexesService complexesService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String start(Model model) {
        MyDate todayDate = new MyDate();
        todayDate.setDate(new Date(System.currentTimeMillis()));
        dateService.addDate(todayDate);



        model.addAttribute("listEmployees",new Gson().toJson(employeesService.listEmployees()));


        return "index";

    }
}
