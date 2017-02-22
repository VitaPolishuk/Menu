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
    ResponseEntity<ObjectModel> addEmployee(@RequestBody Employees employees,@RequestParam("date") Date date) throws SQLException {
        employees.setStatus(true);

        this.employeesService.addEmployees(employees);
        this.basicService.addEmployeeToBasic(employees,date);

        return new ResponseEntity<>(listEmployeesTrue(), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> removeEmployees(@RequestBody Employees employees) throws SQLException {
        this.employeesService.setStatus(employees.getIdEmployee(), false);
        return new ResponseEntity<>(listEmployeesTrue(), HttpStatus.OK);
    }

    @RequestMapping(value = "editEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> editEmployee(@RequestBody Employees employees) throws SQLException {
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
    void save(@RequestParam("idEmployee") Long idEmployee, @RequestParam("idRecord") Long idRecord, @RequestParam("date") Date date) throws SQLException {
        this.basicService.setComplex(idEmployee, idRecord);
    }

    @RequestMapping(value = "getAllByDate", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> getAllByDate(@RequestBody MyDate myDate, Model model) throws SQLException {
        ObjectModel objectModel = new ObjectModel();
        Long idGetDate = findIdDate(myDate);

        if (idGetDate == 0) {
            MyDate lastDate = compareDate(dateService.listDate());
            Long idLastDate = lastDate.getIdDate();
            objectModel = returnInfoByDay(model, idLastDate, lastDate, objectModel);
           // return new ResponseEntity<>(objectModel, HttpStatus.BAD_REQUEST);
        } else {
            myDate.setIdDate(idGetDate);
            objectModel = returnInfoByDay(model, idGetDate, myDate, objectModel);
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
            dateService.addDate(myDate);
            objectModel = createData();
            addEmplBasic(myDate.getDate());
            objectModel = returnInfoByDay(model, findIdDate(myDate), myDate, objectModel);

        } else {
            myDate.setIdDate(idGetDate);
            objectModel = returnInfoByDay(model, idGetDate, myDate, objectModel);
        }
        return new ResponseEntity<>(objectModel, HttpStatus.OK);
    }

    public Long findIdDate(MyDate myDate) {

     /*   Date dateValue = myDate.getDate();// значение даты со страницы
        Long idGetDate = new Long(0);
        for (MyDate date : dateService.listDate()) {
            if (compareDate(date.getDate(), dateValue)) {
                idGetDate = date.getIdDate();
            }
        }*/
        if (dateService.getDateByValue(myDate.getDate()) == null) {
            return new Long(0);
        } else {
            return dateService.getDateByValue(myDate.getDate()).getIdDate();
        }
    }

    public ObjectModel createData() {
        ObjectModel objectModel = new ObjectModel();
        List<Long> idRecordList = new ArrayList<>();
        List<Integer> listNumber = new ArrayList<>();
        returnComplexInit();
        for (int i = complexesService.listComplexes().size() - 4; i < complexesService.listComplexes().size(); i++) {
            DateAndComplexes dateAndComplexes = new DateAndComplexes();
            dateAndComplexes.setIdComplex(complexesService.listComplexes().get(i));
            dateAndComplexes.setIdDate(dateService.listDate().get(dateService.listDate().size() - 1));
            dateAndComplexesService.addDateComplex(dateAndComplexes);
            idRecordList.add(dateAndComplexes.getIdRecord());
        }
        listNumber.add(1);
        listNumber.add(2);
        listNumber.add(3);
        listNumber.add(0);
        objectModel = listEmployeesTrue();
        objectModel.setComplexesList(complexesService.listComplexes());
        objectModel.setNumberList(listNumber);
        return objectModel;
    }


    public void addCurrDate(Model model) {
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
       // List<Long> idRecordList = new ArrayList<>();
      //  List<Integer> listNumber = new ArrayList<>();
        ObjectModel objectModel = new ObjectModel();
        Date currentDate = new Date(System.currentTimeMillis()); // сегодняшняя дата
        if (myDateList.isEmpty()) { // если таблица пустая, то добавили дату
            this.passwordService.addPassword();
            MyDate todayDate = new MyDate();
            todayDate.setDate(currentDate);
            dateService.addDate(todayDate);
            objectModel = createData();
            setModel(model, todayDate, objectModel);
        } else {
            MyDate lastDate = compareDate(myDateList);
            Long idLastDate = lastDate.getIdDate();
            returnInfoByDay(model, idLastDate, lastDate, objectModel);
        }
    }

    // заполняет все списки по выбранной дате
    public ObjectModel returnInfoByDay(Model model, Long idDate, MyDate date, ObjectModel objectModel) {
     ///   List<DateAndComplexes> dateAndComplexes = dateAndComplexesService.listDateComplexes();
       // List<Basic> basicList = basicService.listBasics();
        List<Employees> listEmployeesTrue = new ArrayList<>();
        List<Long> idRecList = dateAndComplexesService.returnIdRecordByDate(date.getDate());
        List<Complexes> listComplexes = dateAndComplexesService.returnIdComplexesByDate(date.getDate());
        // в цикле заполним список комплексов по дате и получим список записей по дате
        //for (DateAndComplexes dateComplex : dateAndComplexes) {
         //   if (idDate == dateComplex.getIdDate().getIdDate()) {
                //idRecList.add(dateComplex.getIdRecord());
          //      listComplexes.add(dateComplex.getIdComplex());
         //       System.out.println();
          //  }
       // }
        //находим список действительных сотрудников в конкретный день
       for (int i = 0; i < idRecList.size(); i++) {
        // Employees empl =  basicService.returnEmployeeByRecord(idRecList.get(i));
          //  for (Basic basic : basicList) {
          //      if (basic.getIdRecord().getIdRecord() == idRecList.get(i)) {
           //         if (basic.getIdEmployee().getStatus() == true) {
         //  DateAndComplexes dateAndComplexes1 = new DateAndComplexes();
           //dateAndComplexes1.setIdRecord(idRecList.get(i));
           if( basicService.returnEmployeeByRecord(idRecList.get(i), true) != null){
                        listEmployeesTrue.add(basicService.returnEmployeeByRecord(idRecList.get(i), true));
                }}
            //    }
           // }
     //   }
      sortList(listEmployeesTrue);
        objectModel.setComplexesList(listComplexes);
        objectModel.setEmployeesList(listEmployeesTrue);
        objectModel.setIdRecordList(idRecList);
        objectModel.setNumberList(returnNumber(listEmployeesTrue, idRecList));
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
                int q = complexesService.numberToEmployee(listEmpl,idR );
                if (q!=5) {
                    listok.add(q);
                }
           /* for (Basic basic : basicService.listBasics()) {
                if (listEmpl.getIdEmployee() == basic.getIdEmployee().getIdEmployee()) {
                    for (int i = 0; i < listRec.size(); i++) {
                        if (basic.getIdRecord().getIdRecord() == listRec.get(i)) {
                            listok.add(i + 1);
                        }
                    }
                }
            }*/

            }
        }
        return listok;
    }

    public ObjectModel listEmployeesTrue() {
        List<DateAndComplexes> dateAndComplexes = dateAndComplexesService.listDateComplexes();
        Date currentDate = new Date(System.currentTimeMillis());
        //List<Long> idRecList = new ArrayList<>();
        ObjectModel objectModel = new ObjectModel();
        //for (DateAndComplexes dateComplex : dateAndComplexes) {
        //    if (compareDate(currentDate, dateComplex.getIdDate().getDate())) {
         //       idRecList.add(dateComplex.getIdRecord());
        //    }
      //  }
        //
        List<Long> idRecList = dateAndComplexesService.returnIdRecordByDate(currentDate);
       // List<Employees> listAll = employeesService.listEmployees();
      //  List<Employees> listTrue = new ArrayList<Employees>();
      //  for (Employees employee : listAll) {
      //      if (employee.getStatus()) {
     //           listTrue.add(employee);
      //      }
      //  }

        List<Employees> listTrue = employeesService.listEmployeesToStatus(true);


        objectModel.setIdRecordList(idRecList);
        objectModel.setEmployeesList(listTrue);
        objectModel.setNumberList(returnNumber(listTrue, idRecList));
        return objectModel;
    }
//
    public void addEmplBasic(Date date) {
        List<Employees> employeesList = employeesService.listEmployees();
        for (Employees employee : employeesList) {
            basicService.addEmployeeToBasic(employee,date);
          /*  Basic basicTable = new Basic();
            basicTable.setIdEmployee(employee);
            for (DateAndComplexes dateComplex : dateAndComplexesService.listDateComplexes()) {
                if (compareDate(date, dateComplex.getIdDate().getDate())) {
                    if (dateComplex.getIdComplex().getNumber() == 0) {
                        basicTable.setIdRecord(dateComplex);
                        break;
                    }
                }
            }
            basicService.addBasic(basicTable);*/
        }
    }

   /* public void addEmplBasic(Employees employees) {
//
    public void addEmplBasic(Employees employees) {

        Basic basicTable = new Basic();
        basicTable.setIdEmployee(employees);
        List<Long> idRecList = new ArrayList<>();
        Long idRec = new Long(0);
        Date currentDate = new Date(System.currentTimeMillis());
        for (DateAndComplexes dateComplex : dateAndComplexesService.listDateComplexes()) {
            if (compareDate(currentDate, dateComplex.getIdDate().getDate())) {
                if (dateComplex.getIdComplex().getNumber() == 0) {
                    basicTable.setIdRecord(dateComplex);
                    break;
                }
            }
        }
        basicService.addBasic(basicTable);
    }
*/
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
