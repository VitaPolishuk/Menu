package com.menuAndersen.controller;

import com.google.gson.Gson;
import com.menuAndersen.model.Employees;
import com.menuAndersen.model.MyDate;
import com.menuAndersen.service.BasicService;
import com.menuAndersen.service.ComplexesService;
import com.menuAndersen.service.EmployeesService;
import com.menuAndersen.service.MyDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    SimpleDateFormat newDateFormat = new SimpleDateFormat("d.MM.yyyy", Locale.getDefault());
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
        addCurrDate();
        model.addAttribute("currentDate", newDateFormat.format(new Date(System.currentTimeMillis())));
        model.addAttribute("listEmployees", new Gson().toJson(employeesService.listEmployees()));
        model.addAttribute("listComplexes", new Gson().toJson(complexesService.listComplexes()));
        return "index";

    }
    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Employees>> addEmployee(@RequestBody Employees employees) throws SQLException {//PathVariable
        this.employeesService.addEmployees(employees);

        return new ResponseEntity<>(this.employeesService.listEmployees(), HttpStatus.OK);
    }
    public  void addCurrDate(){
        MyDate todayDate = new MyDate();
        List<MyDate> myDateList = dateService.listDate();
        System.out.println(myDateList);
        Date currentDate = new Date(System.currentTimeMillis());
        if (myDateList.isEmpty()) {
            todayDate.setDate(currentDate);
            dateService.addDate(todayDate);
        } else {
            for (MyDate date : myDateList) {
                if (date.equals(currentDate)) {
                } else {
                    todayDate.setDate(currentDate);
                    dateService.addDate(todayDate);
                }
            }
        }
    }
}
