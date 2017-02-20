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
        model.addAttribute("password", this.passwordService.getPassword(Long.valueOf(1)).getPassword());
        return "index";
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Employees>> addEmployee(@RequestBody Employees employees) throws SQLException {
        employees.setStatus(true);
        this.employeesService.addEmployees(employees);
        addEmplBasic(employees);
        return new ResponseEntity<>(listEmployeesTrue(), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Employees>> removeEmployees(@RequestBody Employees employees) throws SQLException {
        this.employeesService.setStatus(employees.getIdEmployee(), false);
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
    public void changePassword(@RequestBody Password password) throws SQLException {
        this.passwordService.editPassword(password);
    }

    @RequestMapping(value = "saveChangeComplex", method = RequestMethod.POST)
    public
    @ResponseBody
    void save(@RequestParam("idEmployee") Long idEmployee, @RequestParam("idRecord") Long idRecord) throws SQLException {
        this.basicService.setComplex(idEmployee, idRecord);
    }


    @RequestMapping(value = "getAllByDate", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> getAllByDate(@RequestBody MyDate myDate, Model model) throws SQLException {
        ObjectModel objectModel = new ObjectModel();
        Date dateValue = myDate.getDate();// значение даты со страницы
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        Long idGetDate = new Long(0);
        for (MyDate date : myDateList) {
            if(compareDate(date.getDate(),dateValue)) {
                        idGetDate = date.getIdDate();
                    }
        }
        myDate.setIdDate(idGetDate);
        objectModel = returnInfoByDay(model, idGetDate, myDate, objectModel);
        return new ResponseEntity<>(objectModel, HttpStatus.OK);
    }

    public void addCurrDate(Model model) {
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        List<Long> idRecordList = new ArrayList<>();
        List<Integer> listNumber = new ArrayList<>();
        ObjectModel objectModel = new ObjectModel();
        Date currentDate = new Date(System.currentTimeMillis()); // сегодняшняя дата
        if (myDateList.isEmpty()) { // если таблица пустая, то добавили дату
            this.passwordService.addPassword();
            MyDate todayDate = new MyDate();
            todayDate.setDate(currentDate);
            dateService.addDate(todayDate);
            returnComplexInit();
            for (int i = 0; i < complexesService.listComplexes().size(); i++) {
                DateAndComplexes dateAndComplexes = new DateAndComplexes();
                dateAndComplexes.setIdComplex(complexesService.listComplexes().get(i));
                dateAndComplexes.setIdDate(dateService.listDate().get(0));
                dateAndComplexesService.addDateComplex(dateAndComplexes);
                idRecordList.add(dateAndComplexes.getIdRecord());
            }
            listNumber.add(1);
            listNumber.add(2);
            listNumber.add(3);
            model.addAttribute("listNumber", new Gson().toJson(listNumber));
            setModel(model, todayDate, listEmployeesTrue(), complexesService.listComplexes(), idRecordList, listNumber);
        } else {
            MyDate lastDate = compareDate(myDateList);
            Long idLastDate = lastDate.getIdDate();
            returnInfoByDay(model, idLastDate, lastDate, objectModel);
        }
    }

    // заполняет все списки по выбранной дате
    public ObjectModel returnInfoByDay(Model model, Long idDate, MyDate date, ObjectModel objectModel) {
        List<DateAndComplexes> dateAndComplexes = dateAndComplexesService.listDateComplexes();
        List<Basic> basicList = basicService.listBasics();

        List<Long> idRecList = new ArrayList<>();
        List<Complexes> listComplexes = new ArrayList<>();
        List<Employees> listEmployeesTrue = new ArrayList<>();
        List<Integer> listNumber = new ArrayList<>();

        // в цикле заполним список комплексов по дате и получим список записей по дате
        for (DateAndComplexes dateComplex : dateAndComplexes) {
            if (idDate == dateComplex.getIdDate().getIdDate()) {
                idRecList.add(dateComplex.getIdRecord());
                listComplexes.add(dateComplex.getIdComplex());
            }
        }
        //находим список действительных сотрудников в конкретный день
        for (int i = 0; i < idRecList.size(); i++) {
            for (Basic basic : basicList) {
                if (basic.getIdRecord().getIdRecord() == idRecList.get(i)) {
                    if (basic.getIdEmployee().getStatus() == true) {
                        listEmployeesTrue.add(basic.getIdEmployee());
                    }
                }
            }
        }
        sortList(listEmployeesTrue);
        // получаем список комплексов для каждого сотрудника
        for (Employees listEmpl : listEmployeesTrue) {
            for (Basic basic : basicList) {
                if (listEmpl.getIdEmployee() == basic.getIdEmployee().getIdEmployee()) {
                    for (int i = 0; i < idRecList.size(); i++) {
                        if (basic.getIdRecord().getIdRecord() == idRecList.get(i)) {
                            listNumber.add(i + 1);
                        }
                    }
                }
            }
        }
        setModel(model, date, listEmployeesTrue(), listComplexes, idRecList, listNumber);
        objectModel.setComplexesList(listComplexes);
        objectModel.setEmployeesList(listEmployeesTrue);
        objectModel.setIdRecordList(idRecList);
        objectModel.setNumberList(listNumber);
        return objectModel;
    }
    public boolean compareDate(Date d1, Date d2){
        if (d1.getYear() == d2.getYear()) {
            if (d1.getMonth() == d2.getMonth()) {
                if (d1.getDay() == d2.getDay()) {
                   return true;
                }
            }
        }
        return false;
    }
    public List<Employees> sortList(List<Employees> employeesList) {
        Collections.sort(employeesList, new Comparator<Employees>() {
            public int compare(Employees o1, Employees o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return employeesList;
    }

    public void setModel(Model model, MyDate date, List<Employees> lEmpl, List<Complexes> lComp, List<Long> lRecord, List<Integer> listNumber) {
        model.addAttribute("currentDate", date.getDate());
        model.addAttribute("idDate", date.getIdDate());
        model.addAttribute("listEmployees", new Gson().toJson(lEmpl));
        model.addAttribute("listComplexes", new Gson().toJson(lComp));
        model.addAttribute("listNumber", new Gson().toJson(listNumber));
        model.addAttribute("idRecordList", new Gson().toJson(lRecord));
    }

    public Complexes complexInit(int number) {
        Complexes complex = new Complexes();
        complex.setFirstCourse("Первое");
        complex.setSecondCourse("Второе");
        complex.setSalad("Салат");
        complex.setDrinks("Сок");
        complex.setNumber(number);
        return complex;
    }

    public void returnComplexInit() {
        complexesService.addComplex(complexInit(1));
        complexesService.addComplex(complexInit(2));
        complexesService.addComplex(complexInit(3));
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

    public MyDate compareDate(List<MyDate> listDate) {
        MyDate dateMax = listDate.get(0);
        for (MyDate dI : listDate) {
            if (dI.getDate().compareTo(dateMax.getDate()) > 0) {
                dateMax = dI;
            }
        }
        return dateMax;
    }
}
