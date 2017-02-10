package com.menuAndersen.controller;

import com.google.gson.Gson;
import com.menuAndersen.model.Employees;
import com.menuAndersen.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    @Autowired(required = true)
    private EmployeesService employeesService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String start(Model model) {
        model.addAttribute("listEmployees",new Gson().toJson(employeesService.listEmployees()));
        return "index";

    }
}
