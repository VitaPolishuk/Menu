package com.menuAndersen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.menuAndersen.model.Complexes;
import com.menuAndersen.model.DateAndComplexes;
import com.menuAndersen.model.Employees;
import com.menuAndersen.model.MyDate;
import com.menuAndersen.service.*;
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
import java.util.*;

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
    private DateAndComplexesService dateAndComplexesService;

    @Autowired(required = true)
    private BasicService basicService;

    @Autowired(required = true)
    private ComplexesService complexesService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {

        // List<Complexes> l = returnComplexByDate(myDate);
        model.addAttribute("currentDate", new Date(System.currentTimeMillis()));
        model.addAttribute("listEmployees", new Gson().toJson(employeesService.listEmployees()));
        model.addAttribute("listNumber", new Gson().toJson(listNumber(this.employeesService.listEmployees().size())));
        model.addAttribute("listComplexes", new Gson().toJson( addCurrDate()));
        return "index";

    }
    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
  ResponseEntity<Map<Integer,Employees>> addEmployee(@RequestBody Employees employees) throws SQLException {//PathVariable
        this.employeesService.addEmployees(employees);
       // model.addAttribute("listNumber", new Gson().toJson(listNumber(this.employeesService.listEmployees().size())));

      /*  ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        return new ResponseEntity<>(listInMap(listNumber(this.employeesService.listEmployees().size()),employeesService.listEmployees()),HttpStatus.OK);


    }

    public Map<Integer,Employees> listInMap(List<Integer> lstN,List<Employees>lstE){
        Map<Integer,Employees> map = new HashMap<>();
        for (int i = 0; i < lstE.size(); i++) {
            map.put(lstN.get(i),lstE.get(i));
        }
        return map;
    }



    public List<Complexes> addCurrDate() {
        boolean flag = true;

        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        Date currentDate = new Date(System.currentTimeMillis()); // сегодняшняя дата
        List<Complexes> listComplexes = new ArrayList<>();
       if (myDateList.isEmpty()) { // если таблица пустая, то добавили дату
            MyDate todayDate = new MyDate();
            todayDate.setDate(currentDate);
            dateService.addDate(todayDate);
            complexesService.addComplex(complexInit());
            complexesService.addComplex(complexInit());
            complexesService.addComplex(complexInit());
            return complexesService.listComplexes();
     }

        /*else {
            for (MyDate date : myDateList) {// проверяем есть ли в таблице текущая дата
                if (date.equals(currentDate)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                todayDate.setDate(currentDate);
                dateService.addDate(todayDate);
            }
        }*/
      else{ return null;}
    }

   /* public List<Complexes> returnComplexByDate() {
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        MyDate lastDate = myDateList.get(myDateList.size() - 1); // получила из таблицы последнюю дату

        Long idCurrentDate = date.getIdDate();
        List<DateAndComplexes> dateAndComplexesList = dateAndComplexesService.listDateComplexes();
        List<Complexes> listComplexes = new ArrayList<>();
        for (int i = 0; i < dateAndComplexesList.size(); i++) {
            if (idCurrentDate == dateAndComplexesList.get(i).getIdDate().getIdDate()) {
                Complexes complex = dateAndComplexesList.get(i).getIdComplex();
                listComplexes.add(complex);
            } else {
                listComplexes.add(complexInit());
            }
        }
        return listComplexes;
    }*/

    public List<Integer> listNumber(int size) {
        List<Integer> listNumber = new ArrayList<>();
        for (int i = 1; i < size + 1; i++) {
            listNumber.add(i);
        }
        return listNumber;
    }

    public Complexes complexInit() {
        Complexes complex = new Complexes();
        complex.setFirstCourse("Первое");
        complex.setSecondCourse("Второе");
        complex.setSalad("Салат");
        complex.setDrinks("Сок");
        return complex;
    }

}
