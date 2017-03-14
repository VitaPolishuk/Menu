package com.menuAndersen.controller;

import com.google.gson.Gson;
import com.menuAndersen.config.Config;
import com.menuAndersen.model.*;
import com.menuAndersen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
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
    private DateAndComplexesService dateAndComplexesService;

    @Autowired(required = true)
    private BasicService basicService;

    @Autowired(required = true)
    private ComplexesService complexesService;
    @Autowired(required = true)
    private MyDateService myDateService;

    @Autowired(required = true)
    private DishService dishService;

    @Autowired(required = true)
    private PasswordService passwordService;


    @Autowired(required = true)
    private TimeBlockedService timeBlockedService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) throws IOException {
        /*Timer timer = new Timer();
        MyTimer myTimer = new MyTimer(timeBlockedService,myDateService);
        timer.schedule(myTimer,0,1000);
        addCurrDate(model);*/

        addCurrDate(model);

        //String passwordInitial = "1";


        // this.passwordService.addPassword(String.valueOf(passwordInitial.hashCode()));
        model.addAttribute("password", this.passwordService.getPassword(Long.valueOf(1)).getPassword());
     /*   File image = new File("C:/Users/vita/Desktop/addIcon.png");
        FileInputStream fis = new FileInputStream(image);
        byte[] buffer = new byte[fis.available()];
        // считаем файл в буфер
        fis.read(buffer, 0, fis.available());
        Dish dish = new Dish();
        dish.setNameDish("запеканка");
        dish.setTypeDish("второе");
        dish.setImgDish(buffer);
        dishService.addDish(dish);*/


      /*  FileOutputStream file = new FileOutputStream("C:/Users/vita/Desktop/copyAddIcon.png");
        ByteArrayInputStream input = new ByteArrayInputStream(rezImg);
        BufferedImage bi = ImageIO.read(input);
        ImageIO.write(bi, "png", file);*/
        return "index";
    }

    @RequestMapping(value = "image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public
    @ResponseBody
    byte[] test(@RequestParam("id") Long id) throws SQLException {
        Dish dish = dishService.getDish(id);
        byte rezImg[] = dish.getImgDish();
        return rezImg;
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> addEmployee(@RequestBody Employees employees, @RequestParam("date") Date date) throws SQLException {


        this.employeesService.addEmployees(employees);
        this.basicService.addEmployeeToBasic(employees, date, true);

        return new ResponseEntity<>(listEmployeesTrue(date), HttpStatus.OK);
    }

    @RequestMapping(value = "addDish", method = RequestMethod.POST)
    public ResponseEntity<Long> handleFileUpload(@RequestParam CommonsMultipartFile[] upload) throws Exception {
        Long id = null;
        if (upload != null && upload.length > 0) {
            for (CommonsMultipartFile aFile : upload) {
                Dish dish = new Dish();
                dish.setImgDish(aFile.getBytes());
                dishService.addDish(dish);
                dish = dishService.returnDish();
                id = dish.getIdDish();
            }
        }
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "addDishN", method = RequestMethod.POST)
    public ResponseEntity<DishListAll> handleFile(@RequestBody Dish dish, @RequestParam("idDish") Long id) throws Exception {
        dishService.editDish(id, dish.getNameDish(), dish.getTypeDish());
        DishListAll dishListAll = new DishListAll(dishService.returnDishByType("Первое"), dishService.returnDishByType("Гарнир"),
                dishService.returnDishByType("Мясное"), dishService.returnDishByType("Салат"), dishService.returnDishByType("Напиток"));
        return new ResponseEntity<>(dishListAll, HttpStatus.OK);
    }

    /*  @RequestMapping(value = "addDish", method = RequestMethod.POST)
    void addDish(@RequestBody Dish dish) throws SQLException, IOException {
       // String imgA = imgAddr.replace("\\","/");
      //  File image = new File(imgA);
      //  FileInputStream fis = new FileInputStream(image);
      //  byte[] buffer = new byte[fis.available()];
      //  fis.read(buffer, 0, fis.available());
        System.out.println();
       // dish.setImgDish(dish);
        dishService.addDish(dish);
    }
*/
    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<ObjectModel> removeEmployees(@RequestBody Employees employees, @RequestParam("date") Date date) throws SQLException {
        this.basicService.setStatus(employees.getIdEmployee(), false, date);
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
        this.basicService.setComplex(idEmployee, idRecord, date);
    }

    @RequestMapping(value = "countComplexes", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Integer>> countComplexes(@RequestParam("date") Date date) throws SQLException {
        List<Integer> listCountComplex = new ArrayList<>();
        listCountComplex.add(this.basicService.countComplex(date, 1));
        listCountComplex.add(this.basicService.countComplex(date, 2));
        listCountComplex.add(this.basicService.countComplex(date, 3));
        return new ResponseEntity<>(listCountComplex, HttpStatus.OK);
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
        return new ResponseEntity<MyDate>(myDateService.getDateByValue(date), HttpStatus.OK);
    }

    @RequestMapping(value = "buttonSaveTime", method = RequestMethod.POST)
    public
    @ResponseBody
    void statusCheckbox(@RequestParam("status") boolean status, @RequestParam("time") String time) throws SQLException {
        if (status) {
            timeBlockedService.cheangeGlobalTime(time);
            timeBlockedService.cheangeCurrentTime(time);
        } else {
            timeBlockedService.cheangeCurrentTime(time);
        }

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

    // новый день только при старте
    public ObjectModel createData(MyDate myDate) {
        ObjectModel objectModel = new ObjectModel();

        for (int i = 0; i < 4; i++) {
            complexesService.addComplex(new Complexes(i));
        }
        int cnt = 0, cnt1 = 0;
        for (int i = 0; i < 20; i++) {
            if (i % 5 == 0 && i != 0) cnt++;
            if (i % 5 == 0 && i != 0) cnt1 = 0;
            dateAndComplexesService.addDateComplex(new DateAndComplexes(myDate,
                    complexesService.listComplexes().get(cnt), dishService.listDish().get(cnt1)));
            cnt1++;
        }
        addEmplBasic(myDate.getDate());
        objectModel = listEmployeesTrue(myDate.getDate());
        return objectModel;
    }


    public void addCurrDate(Model model) throws IOException {
        List<MyDate> myDateList = dateService.listDate();// получаю из таблицы дат все даты
        ObjectModel objectModel = new ObjectModel();
        Date currentDate = new Date(System.currentTimeMillis()); // сегодняшняя дата
        java.util.Date date = new java.util.Date();
        String time = date.getHours() + ":" + date.getMinutes();
        if (myDateList.isEmpty()) { // если таблица пустая, то добавили дату
            String passwordInitial = "1";
            String globalTime = "09:30";
            timeBlockedService.addTime(globalTime, globalTime);
            this.passwordService.addPassword(String.valueOf(passwordInitial.hashCode()));
            MyDate todayDate = new MyDate();
            todayDate.setDate(currentDate);
            todayDate.setBlocked(true);
            dateService.addDate(todayDate);
            returnDishInit();
            objectModel = createData(todayDate);
            setModel(model, todayDate, objectModel, globalTime);
        } else {
            MyDate lastDate = compareDate(myDateList);
            returnInfoByDay(model, lastDate, objectModel);
        }
    }

    // заполняет все списки по выбранной дате
    public ObjectModel returnInfoByDay(Model model, MyDate date, ObjectModel objectModel) {
        List<Employees> listEmployeesTrue = new ArrayList<>();
        List<Long> idRecList = dateAndComplexesService.returnIdRecordByDate(date.getDate());
        //List<Complexes> listComplexes = dateAndComplexesService.returnIdComplexesByDate(date.getDate());
        if (basicService.returnEmployeeByRecord(idRecList.get(0), idRecList.get(idRecList.size() - 1), true) != null) {
            listEmployeesTrue = basicService.returnEmployeeByRecord(idRecList.get(0), idRecList.get(idRecList.size() - 1), true);
        }
        sortList(listEmployeesTrue);
        objectModel.setDishList(dateAndComplexesService.returnDishByDate(date.getDate()));
        objectModel.setEmployeesList(listEmployeesTrue);
        objectModel.setIdRecordList(idRecList);
        objectModel.setNumberList(returnNumber(listEmployeesTrue, idRecList));
        objectModel.setMyDate(date);
        objectModel.setTimeBlocked(this.timeBlockedService.getTime(Long.valueOf(1)));
        objectModel.setDishListAll(new DishListAll(dishService.returnDishByType("Первое"), dishService.returnDishByType("Гарнир"),
                dishService.returnDishByType("Мясное"), dishService.returnDishByType("Салат"), dishService.returnDishByType("Напиток")));
        setModel(model, date, objectModel, timeBlockedService.getTime(Long.valueOf(1)).getCurrentTime());

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

    public void setModel(Model model, MyDate date, ObjectModel objectModel, String time) {
        model.addAttribute("currentDate", date.getDate());
        model.addAttribute("idDate", date.getIdDate());
        model.addAttribute("objectModel", new Gson().toJson(objectModel));
        model.addAttribute("time", time);

    }
    public byte[] transferImgToByte(String src) throws IOException {
        File image = new File("../webapps/ROOT/pages/image/"+src);
        FileInputStream fis = new FileInputStream(image);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer, 0, fis.available());
        return buffer;
    }


    public void returnDishInit() throws IOException {

        dishService.addDish(new Dish("Суп", "Первое", transferImgToByte("soup.jpg")));
        dishService.addDish(new Dish("Бульба", "Гарнир", transferImgToByte("pasta.jpg")));
        dishService.addDish(new Dish("Колбасень", "Мясное", transferImgToByte("meat.jpg")));
        dishService.addDish(new Dish("Трава", "Салат", transferImgToByte("salad.jpg")));
        dishService.addDish(new Dish("Вода", "Напиток", transferImgToByte("drink.jpg")));
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
            if (basicService.returnEmployeeFalse(employee) == 0) {
                listTrue.add(employee);
            }
        }
        sortList(listTrue);
        objectModel.setIdRecordList(idRecList);
        objectModel.setEmployeesList(listTrue);
        objectModel.setNumberList(returnNumber(listTrue, idRecList));
        objectModel.setTimeBlocked(this.timeBlockedService.getTime(Long.valueOf(1)));
        objectModel.setDishList(dateAndComplexesService.returnDishByDate(date));
        return objectModel;
    }

    public void addEmplBasic(Date date) {
        for (Employees employee : employeesService.listEmployees()) {
            if (basicService.returnEmployeeFalse(employee) == 0) {
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
