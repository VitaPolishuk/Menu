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

    @Autowired(required = true)
    private MyDateService myDateService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        addCurrDate(model);
        model.addAttribute("password", this.passwordService.getPassword(Long.valueOf(1)).getPassword());
        return "index";
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> addEmployee(@RequestBody Employees employees, @RequestParam("date") Date date) throws SQLException {


        this.employeesService.addEmployees(employees);
        this.basicService.addEmployeeToBasic(employees, date, true);
        
        return new ResponseEntity<>(listEmployeesTrue(date), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> removeEmployees(@RequestBody Employees employees, @RequestParam("date") Date date) throws SQLException {
        this.basicService.setStatus(employees.getIdEmployee(), false,  date);
        return new ResponseEntity<>(listEmployeesTrue(date), HttpStatus.OK);
    }

    @RequestMapping(value = "editEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> editEmployee(@RequestBody Employees employees, @RequestParam("date") Date date) throws SQLException {
        this.employeesService.editEmployees(employees);
        return new ResponseEntity<>(listEmployeesTrue(date), HttpStatus.OK);
    }

    @RequestMapping(value = "editComplex", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> editComplex(@RequestBody Complexes complexes, @RequestParam("date") Date date) throws SQLException {
        this.complexesService.editComplex(complexes);
        return new ResponseEntity<>(listEmployeesTrue(date), HttpStatus.OK);
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public
    @ResponseBody
    Password changePassword(@RequestParam("passwordNew") String passwordNew) throws SQLException {
         Password password = new Password();
         password.setIdPassword(new Long(1));
         password.setPassword(String.valueOf(passwordNew.hashCode()));
        this.passwordService.editPassword(password);
    //    model.addAttribute("password",password.getPassword());
        return password;
    }

    @RequestMapping(value = "saveChangeComplex", method = RequestMethod.POST)
    public
    @ResponseBody
    void save(@RequestParam("idEmployee") Long idEmployee, @RequestParam("idRecord") Long idRecord, @RequestParam("date") Date date) throws SQLException {
        this.basicService.setComplex(idEmployee, idRecord,date);
    }
    @RequestMapping(value = "lastDate", method = RequestMethod.POST)
    public
    @ResponseBody
    Date lastDate() throws SQLException {
        return compareDate(dateService.listDate()).getDate();
    }

    @RequestMapping(value = "blockedDate", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<MyDate> blockedDate(@RequestParam("date") Date date) throws SQLException {
    //this.myDateService.setStatusDate(date);
    return new ResponseEntity<MyDate>(myDateService.getDateByValue(date),HttpStatus.OK);
    }

    @RequestMapping(value = "setStatusDateFalse", method = RequestMethod.POST)
    public
    @ResponseBody
    void setStatus(@RequestParam("date") Date date) throws SQLException {
        this.myDateService.setStatusDate(date);

    }

    @RequestMapping(value = "getAllByDate", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> getAllByDate(@RequestBody MyDate myDate, Model model) throws SQLException {
        ObjectModel objectModel = new ObjectModel();
        Long idGetDate = findIdDate(myDate);

        if (idGetDate == 0) {
            MyDate lastDate = compareDate(dateService.listDate());
              objectModel = returnInfoByDay(model, lastDate, objectModel);

        } else {
            myDate.setIdDate(idGetDate);
            objectModel = returnInfoByDay(model, myDate, objectModel);
        }

        return new ResponseEntity<>(objectModel, HttpStatus.OK);
    }

    @RequestMapping(value = "getAllByDateAdmin", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> getAllByDateAdmin(@RequestBody MyDate myDate, Model model) throws SQLException {
        ObjectModel objectModel = new ObjectModel();
        Long idGetDate = findIdDate(myDate);
        if (idGetDate == 0) {
            myDateService.setAllStatusFalse();
            myDate.setBlocked(true);
            dateService.addDate(myDate);
            objectModel = createData(myDate);

            objectModel = returnInfoByDay(model, myDate, objectModel);

        } else {
            myDate.setIdDate(idGetDate);
            objectModel = returnInfoByDay(model, myDate, objectModel);
        }
        return new ResponseEntity<>(objectModel, HttpStatus.OK);
    }

    public Long findIdDate(MyDate myDate) {
        if (dateService.getDateByValue(myDate.getDate()) == null) {
            return new Long(0);
        } else {
            return dateService.getDateByValue(myDate.getDate()).getIdDate();
        }
    }
// новый день
    public ObjectModel createData(MyDate myDate) {
        ObjectModel objectModel = new ObjectModel();
       returnComplexInit();
        for (int i = complexesService.listComplexes().size() - 4; i < complexesService.listComplexes().size(); i++) {
           dateAndComplexesService.addToDate(complexesService.listComplexes().get(i),myDate);
       }
       addEmplBasic(myDate.getDate());
        objectModel = listEmployeesTrue(myDate.getDate());
        return objectModel;
    }


    public void addCurrDate(Model model) {
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        ObjectModel objectModel = new ObjectModel();
        Date currentDate = new Date(System.currentTimeMillis()); // сегодняшняя дата
        if (myDateList.isEmpty()) { // если таблица пустая, то добавили дату
            String passwordInitial= "1";

          this.passwordService.addPassword(String.valueOf(passwordInitial.hashCode()));
            MyDate todayDate = new MyDate();
            todayDate.setDate(currentDate);
            todayDate.setBlocked(true);
            dateService.addDate(todayDate);
            objectModel = createData(todayDate);
            setModel(model, todayDate, objectModel);
        } else {
            MyDate lastDate = compareDate(myDateList);
            returnInfoByDay(model, lastDate, objectModel);
        }
    }

    // заполняет все списки по выбранной дате
    public ObjectModel returnInfoByDay(Model model, MyDate date, ObjectModel objectModel) {
        List<Employees> listEmployeesTrue = new ArrayList<>();
        List<Long> idRecList = dateAndComplexesService.returnIdRecordByDate(date.getDate());
        List<Complexes> listComplexes = dateAndComplexesService.returnIdComplexesByDate(date.getDate());
        if (basicService.returnEmployeeByRecord(idRecList.get(0), idRecList.get(idRecList.size() - 1), true) != null) {
            listEmployeesTrue = basicService.returnEmployeeByRecord(idRecList.get(0), idRecList.get(idRecList.size() - 1), true);
        }
        sortList(listEmployeesTrue);
        objectModel.setComplexesList(listComplexes);
        objectModel.setEmployeesList(listEmployeesTrue);
        objectModel.setIdRecordList(idRecList);
        objectModel.setNumberList(returnNumber(listEmployeesTrue, idRecList));
        objectModel.setMyDate(date);
        setModel(model, date, objectModel);
        return objectModel;
    }

    public boolean compareDate(Date d1, Date d2) {
        if (d1.getYear() == d2.getYear()) {
            if (d1.getMonth() == d2.getMonth()) {
                if (d1.getDate() == d2.getDate()) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Employees> sortList(List<Employees> employeesList) {
        Collections.sort(employeesList, new Comparator<Employees>() {
            public int compare(Employees o1, Employees o2) {
                return o1.getFio().compareTo(o2.getFio());
            }
        });
        return employeesList;
    }

    public void setModel(Model model, MyDate date, ObjectModel objectModel) {
        model.addAttribute("currentDate", date.getDate());
        model.addAttribute("idDate", date.getIdDate());
        model.addAttribute("objectModel", new Gson().toJson(objectModel));
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
        complexesService.addComplex(complexInit(0));
    }

    public List<Integer> returnNumber(List<Employees> lst, List<Long> listRec) {

        List<Integer> listok = new ArrayList<>();
        // получаем список комплексов для каждого сотрудника
        for (Employees listEmpl : lst) {
            for (Long idR : listRec) {
                int q = complexesService.numberToEmployee(listEmpl, idR);
                if (q != 5) {
                    listok.add(q);
                }
            }
        }
        return listok;
    }

    public ObjectModel listEmployeesTrue(Date date) {

        ObjectModel objectModel = new ObjectModel();
        List<Long> idRecList = dateAndComplexesService.returnIdRecordByDate(date);
        List<Employees> listTrue = new ArrayList<>();
        for (Employees employee : employeesService.listEmployees()) {
            if(basicService.returnEmployeeFalse(employee)==0){
                listTrue.add(employee);
            }
        }
        sortList(listTrue);
        objectModel.setIdRecordList(idRecList);
        objectModel.setEmployeesList(listTrue);

        objectModel.setNumberList(returnNumber(listTrue, idRecList));
        objectModel.setComplexesList(complexesService.returnComplexesByDate(date));
        return objectModel;
    }

    public void addEmplBasic(Date date) {
        for (Employees employee : employeesService.listEmployees()) {
            if(basicService.returnEmployeeFalse(employee)==0){
            basicService.addEmployeeToBasic(employee, date, true);
            }
        }
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
