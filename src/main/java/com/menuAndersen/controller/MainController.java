package com.menuAndersen.controller;

import com.google.gson.Gson;
import com.menuAndersen.dao.DateAndComplexesDao;
import com.menuAndersen.model.*;
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
        model.addAttribute("currentDate", new Date(System.currentTimeMillis()));
        model.addAttribute("mapNumberEmployees", new Gson().toJson(listInMap(listNumber(listEmployeesTrue().size()), listEmployeesTrue())));
        model.addAttribute("listComplexes", new Gson().toJson(addCurrDate()));
        addEmplBasic();
        return "index";
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Map<Integer, Employees>> addEmployee(@RequestBody Employees employees) throws SQLException {
        employees.setStatus(true);
        this.employeesService.addEmployees(employees);
        return new ResponseEntity<>(listInMap(listNumber(this.employeesService.listEmployees().size()), employeesService.listEmployees()), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Map<Integer, Employees>> removeEmployees(@RequestBody Employees employees) throws SQLException {


        Employees employees1 = this.employeesService.getEmployees(2);

        return new ResponseEntity<>(listInMap(listNumber(this.employeesService.listEmployees().size()), employeesService.listEmployees()), HttpStatus.OK);
    }

    @RequestMapping(value = "editEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Map<Integer, Employees>> editEmployee(@RequestBody Employees employees) throws SQLException {
        this.employeesService.editEmployees(employees);
        return new ResponseEntity<>(listInMap(listNumber(this.employeesService.listEmployees().size()), employeesService.listEmployees()), HttpStatus.OK);
    }

    public Map<Integer, Employees> listInMap(List<Integer> lstN, List<Employees> lstE) {
        Map<Integer, Employees> map = new HashMap<>();
        for (int i = 0; i < lstE.size(); i++) {
            map.put(lstN.get(i), lstE.get(i));
        }
        return map;
    }

    public List<Complexes> addCurrDate() {
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        Date currentDate = new Date(System.currentTimeMillis()); // сегодняшняя дата

        if (myDateList.isEmpty()) { // если таблица пустая, то добавили дату
        MyDate todayDate = new MyDate();
        todayDate.setDate(currentDate);
        dateService.addDate(todayDate);
        complexesService.addComplex(complexInit());
        complexesService.addComplex(complexInit());
        complexesService.addComplex(complexInit());
        for (int i = 0; i < complexesService.listComplexes().size(); i++) {
            DateAndComplexes dateAndComplexes = new DateAndComplexes();
            dateAndComplexes.setIdComplex(complexesService.listComplexes().get(i));
            dateAndComplexes.setIdDate(dateService.listDate().get(0));
            dateAndComplexesService.addDateComplex(dateAndComplexes);
        }
        return complexesService.listComplexes();
         } else {
              return null;
          }
    }

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

    public List<Employees> listEmployeesTrue() {
        List<Employees> listAll = employeesService.listEmployees();
        List<Employees> listTrue = new ArrayList<Employees>();
        for (Employees employee : listAll) {
            if (employee.getStatus()) {
                listTrue.add(employee);
            }
        }
        return listTrue;
    }

    public void addEmplBasic() {
        List<Employees> employeesList = listEmployeesTrue();
        for (Employees employee : employeesList) {
            Basic basicTable = new Basic();
            basicTable.setIdEmployee(employee);
            basicService.addBasic(basicTable);
        }
    }
}
