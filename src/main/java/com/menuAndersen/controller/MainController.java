package com.menuAndersen.controller;

import com.google.gson.Gson;
import com.menuAndersen.model.*;
import com.menuAndersen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired(required = true)
    private PasswordService passwordService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        addCurrDate(model);
        addEmplBasic();
        model.addAttribute("password",this.passwordService.getPassword(Long.valueOf(1)).getPassword());
        return "index";
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Employees>> addEmployee(@RequestBody Employees employees) throws SQLException {
        employees.setStatus(true);
        this.employeesService.addEmployees(employees);
        addEmplBasic(employees);
        return new ResponseEntity<>( listEmployeesTrue(), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody    ResponseEntity<List<Employees>> removeEmployees(@RequestBody Employees employees) throws SQLException {


       this.employeesService.setStatus(employees.getIdEmployee(),false);


        return new ResponseEntity<>(listEmployeesTrue(), HttpStatus.OK);
    }

    @RequestMapping(value = "editEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Employees>> editEmployee(@RequestBody Employees employees) throws SQLException {
        this.employeesService.editEmployees(employees);
        return new ResponseEntity<>(listEmployeesTrue(), HttpStatus.OK);
    }
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public
    String changePassword(@RequestBody Password password) throws SQLException {
        System.out.println(password.getIdPassword() + "   "+ password.getPassword());
       this.passwordService.editPassword(password);
       return "Пароль успешно изменен!";
    }

    @RequestMapping(value = "saveChangeComplex", method = RequestMethod.POST)
    public @ResponseBody void save(@RequestParam("idEmployee") Long idEmployee, @RequestParam("idRecord") Long idRecord) throws SQLException {
        System.out.println("111111111");
        this.basicService.setComplex(idEmployee,idRecord);
       // this.basicService.setComplex(employees.getIdEmployee(),basic.getIdRecord());


    }
  /*  public Map<Integer, Employees> listInMap(List<Integer> lstN, List<Employees> lstE) {
        Map<Integer, Employees> map = new HashMap<>();
        for (int i = 0; i < lstE.size(); i++) {
            map.put(lstN.get(i), lstE.get(i));
        }
        return map;
    }*/

    public void addCurrDate(Model model) {

        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        List<Long> idRecordList = new ArrayList<>();
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
            idRecordList.add(dateAndComplexes.getIdRecord());
        }
            model.addAttribute("currentDate", currentDate);
            model.addAttribute("listEmployees", new Gson().toJson(listEmployeesTrue()));
            model.addAttribute("listComplexes", new Gson().toJson(complexesService.listComplexes()));
            model.addAttribute("idRecordList", new Gson().toJson(idRecordList));

         } else {
                MyDate lastDate = compareDate(myDateList);
                model.addAttribute("currentDate", lastDate.getDate());

          }
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
        List<Employees> employeesList = employeesService.listEmployees();
        for (Employees employee : employeesList) {
            Basic basicTable = new Basic();
            basicTable.setIdEmployee(employee);
            basicService.addBasic(basicTable);
        }
    }
    public void addEmplBasic(Employees employees) {

            Basic basicTable = new Basic();
            basicTable.setIdEmployee(employees);
            basicService.addBasic(basicTable);
    }
    public MyDate compareDate(List<MyDate> listDate){
        MyDate dateMax = listDate.get(0);
        for(MyDate dI:listDate){
         if(dI.getDate().compareTo(dateMax.getDate())>0){
             dateMax = dI;
         }
        }
        return dateMax;
    }
}
