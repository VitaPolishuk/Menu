function dbClickTable(ev) {
    var row = ev.target.parentElement.rowIndex;
    var cell = ev.target.cellIndex;
    var table = ev.currentTarget;
    if ((table.id == "employees") && (row == 0 || cell == 0 || cell == 3)) {
        alert("КУДА ТЫКАЕШЬ");
    } else {
        table.rows[row].cells[cell].setAttribute("contenteditable", "true");
        table.rows[row].cells[cell].focus();

        table.rows[row].cells[cell].onkeydown = function (event) {
            if (event.keyCode == 13) {
                return false;
            }
        }

        table.rows[row].cells[cell].onblur = function () {
            if (table.id == "employees") {
                editEmployees(table, row, cell);
            }else if(table.id=="complex1"||table.id=="complex2"||table.id=="complex3"){
                editComplex(table);
            }
            table.rows[row].cells[cell].setAttribute("contenteditable", "false");
        }
    }
}


function onClickTable(ev) {
    var row = ev.target.parentElement.rowIndex;
    var cell = ev.target.cellIndex;
    var table = ev.currentTarget;
    var allTable = document.getElementsByTagName("table");
    for (var j = 0; j < allTable.length; j++) {
        for (var i = 0; i < allTable[j].rows.length; i++) {
            allTable[j].rows[i].setAttribute("class", "stroka");
        }
    }

    if (ev.target.parentElement.tagName == "TR" && row != 0) {
        ev.target.parentElement.setAttribute("class", "stroka selectColor");

    }

}

function showCover() {
    var coverDiv = document.createElement('div');
    coverDiv.id = 'cover-div';
    document.body.appendChild(coverDiv);
}

function hideCover() {
    document.body.removeChild(document.getElementById('cover-div'));
}

function showPrompt(text, callback) {
    showCover();
    var form = document.getElementById('prompt-form');
    var container = document.getElementById('prompt-form-container');
    document.getElementById('prompt-message').innerHTML = text;
    form.elements.text.value = '';

    function complete(value) {
        if(value == null){
        container.style.display = "none";
        document.body.removeChild(document.getElementById('cover-div'));}
        document.onkeydown = null;
        callback(value);
    }

    form.onsubmit = function () {
        var value = form.elements.text.value;
        if (value == '') return false; // игнорировать пустой submit
        complete(value);
        return false;
    };
    form.elements.cancel.onclick = function () {
        complete(null);

    };
    container.style.display = 'block';
    form.elements.text.focus();
}

function authentication(password) {
    var name = document.getElementById("button").value;
    var fl;
    if (name == "Войти") {
        showPrompt("Введите пароль<br>администратора :)", function (value) {
            if ((value.hashCode()+"")!= null) {
                if ((value.hashCode()+"") == password) {
                    var last = lastDate();
                    if (document.getElementById("calendarD").value==last){
                    addButtonPages();}
                    document.getElementById("button").value = "Выйти";
                    document.getElementById('prompt-form-container').style.display = "none";
                    document.body.removeChild(document.getElementById('cover-div'));

                } else {
                    alert("Не угадал ");
                    document.getElementById('prompt-form').elements.text.value = "";
                }
            }
        });
    } else {
        document.getElementById("button").value = "Войти";
        deleteButtonPages();

    }
}

function loadEmployees(objectModel) {

    var template = document.getElementById('templateTable').innerHTML.trim();
    if (objectModel.idRecordList != 0) {

        template = _.template(template);
        document.getElementById('tableEmployees').innerHTML = template({
            listEmployees: objectModel.employeesList,
            idRecordList: objectModel.idRecordList

        });
    }
    countComplexes();
}

function loadComplexes(objectModel) {

    var template = document.getElementById('templateComplex').innerHTML.trim();
    template = _.template(template);
    for (var i = 0; i < objectModel.complexesList.length-1; i++) {
        var str = 'complex' + (i + 1);
        document.getElementById(str).innerHTML = template({
            listComplex: objectModel.complexesList[i]


        });
    }

}


function addButtonPages() {
    var calendar = document.getElementById("dateqqqq");
    var div = document.getElementById("addEmployees-container");
    div.style.display = "block";
    var table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("ondblclick", "dbClickTable(event)");
    }

    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("onclick", "onClickTable(event)");
    }

    document.getElementById("windowDel").style.display = "block";


    document.getElementById("changePasswordLink").style.display = "block";
    document.getElementById("blockPages").style.display = "block";

}

function deleteButtonPages() {
    var div = document.getElementById("addEmployees-container");
    div.style.display = "none";
    var table = document.getElementsByTagName("table");

    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("ondblclick", "");
    }

    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("onclick", "");
    }

    for (var j = 0; j < table.length; j++) {
        for (var i = 0; i < table[j].rows.length; i++) {
            table[j].rows[i].setAttribute("class", "stroka");
        }
    }
    document.getElementById("windowDel").style.display = "none";
    document.getElementById("changePasswordLink").style.display = "none";
    document.getElementById("blockPages").style.display = "none";
    document.getElementById("changePassword").style.display = "none";
}

function checkoldPassword() {
    var oldPassword = document.getElementById("oldPassword");
    oldPassword.setAttribute("class", "");
    if (oldPassword.value = "Заполните поле") {
        oldPassword.value = "";
        oldPassword.type = "password";
    }
    oldPassword.onblur = function () {
        if (oldPassword.value == "") {
            oldPassword.setAttribute("class", "oldPassword");
            oldPassword.value = "Заполните поле";
            oldPassword.type = "text";
        }
    }

}

function checknewPassword() {
    var newPassword = document.getElementById("newPassword");
    newPassword.setAttribute("class", "");
    if ((newPassword.value = "Заполните поле")) {
        newPassword.value = "";
        newPassword.type = "password";
    }
    newPassword.onblur = function () {
        if (newPassword.value == "") {
            newPassword.setAttribute("class", "newPassword");
            newPassword.value = "Заполните поле";
            newPassword.type = "text";
        }
    }
}
function savePassword(password) {
    var oldPassword = document.getElementById("oldPassword");
    var newPassword = document.getElementById("newPassword");
    if (oldPassword.value!="" && newPassword.value!="") {
        if (oldPassword.className == "" && newPassword.className == "") {

        if ((document.getElementById("oldPassword").value.hashCode()+"") == password) {

            $.ajax({
                type: "POST",
                url: "/changePassword?passwordNew="+document.getElementById("newPassword").value ,
                async: false,
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                success: function (data, textStatus, jqXHR) {
                    alert("Пароль успешно изменен!")
                   document.getElementById("button").name = data.password;
                    document.getElementById("buttonSavePassword").name = data.password;
                }
            })

            document.getElementById("changePassword").style.display = "none";
        }
        else {
            alert("Неверный старый пароль!");
            $("#oldPassword").val("");
            $("#newPassword").val("");
        }
    }

}
}
function cancelPassword() {
    document.getElementById("changePassword").style.display = "none";
}

function clickLink() {
    document.getElementById("changePassword").style.display = "block";
}
function checkFIO() {
    var inputFIO = document.getElementById("inputFIO");
    inputFIO.setAttribute("class", "");
    if (inputFIO.value = "Введите ФИО") {
        inputFIO.value = "";
    }
    inputFIO.onblur = function () {
        if (inputFIO.value == "") {
            inputFIO.setAttribute("class", "inputFIO");
            inputFIO.value = "Введите ФИО";
        }
    }

}
function checkPositionHeld() {
    var inputPositionHeld = document.getElementById("inputPositionHeld");
    inputPositionHeld.setAttribute("class", "");
    if ((inputPositionHeld.value = "Введите должность")) {
        inputPositionHeld.value = "";
    }
    inputPositionHeld.onblur = function () {
        if (inputPositionHeld.value == "") {
            inputPositionHeld.setAttribute("class", "inputPositionHeld");
            inputPositionHeld.value = "Введите должность";
        }
    }
}
function editComplex(table) {

        var dataJson = {
            idComplex: table.rows[0].cells[0].abbr,
            firstCourse: table.rows[0].cells[0].innerHTML,
            secondCourse: table.rows[1].cells[0].innerHTML,
            number:table.rows[1].cells[0].abbr,
            salad: table.rows[2].cells[0].innerHTML,
            drinks: table.rows[3].cells[0].innerHTML
        };

    $.ajax({
        type: "POST",
        url: "/editComplex?date="+document.getElementById("calendarD").value,
        data: JSON.stringify(dataJson),


        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {

            deleteTableComplex();
            loadComplexes(data);


        },
        error: function (data) {
            alert(data);
        }
    })
    var table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("ondblclick", "dbClickTable(event)");
        table[i].setAttribute("onclick", "onClickTable(event)");
    }
}
function addEmployee() {
    var inputFIO = document.getElementById("inputFIO");
    var inputPositionHeld = document.getElementById("inputPositionHeld");
if (inputFIO.value!="" && inputPositionHeld.value!="") {
    if (inputFIO.className == "" && inputPositionHeld.className == "") {
        var dataJson = {
            fio: inputFIO.value,
            positionHeld: inputPositionHeld.value
        };

            $.ajax({
                type: "POST",
                url: "/addEmployee?date=" + document.getElementById("calendarD").value,
                data: JSON.stringify(dataJson),
                async: false,
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                success: function (data, textStatus, jqXHR) {
                    $("#inputFIO").val("");
                    $("#inputPositionHeld").val("");
                    deleteTable();
                    loadEmployees(data);
                    setRadioButton(data.numberList);
                },
                error: function (data) {
                    alert(data);
                }
            })
            var table = document.getElementsByTagName("table");
            for (var i = 0; i < table.length; i++) {
                table[i].setAttribute("ondblclick", "dbClickTable(event)");
                table[i].setAttribute("onclick", "onClickTable(event)");
            }
        }
    }
}
function  deleteEmployees() {

    var table = document.getElementById("employees");
    for (var i = 0; i < table.rows.length; i++) {
        var str = table.rows[i].getAttribute("class");
        if (str == "stroka selectColor") {
            var dataJson = {idEmployee: table.rows[i].cells[1].abbr};

        }
    }
    $.ajax({
        type: "POST",
        url: "/deleteEmployee?date="+document.getElementById("calendarD").value,
        data: JSON.stringify(dataJson),

        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            deleteTable();
            loadEmployees(data);
            setRadioButton(data.numberList);
        },
        error: function (data) {
            alert("Комплексы можно только редактировать");
        }
    })
    var table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("ondblclick", "dbClickTable(event)");
        table[i].setAttribute("onclick", "onClickTable(event)");
    }
}

function editEmployees(table, row, cell) {
    if (cell == 1) {
        var dataJson = {
            idEmployee: table.rows[row].cells[cell].abbr,
            fio: table.rows[row].cells[cell].innerHTML,
            positionHeld: table.rows[row].cells[cell + 1].innerHTML,
            status: true
        };
    } else if (cell == 2) {
        var dataJson = {
            idEmployee: table.rows[row].cells[cell - 1].abbr,
            fio: table.rows[row].cells[cell - 1].innerHTML,
            positionHeld: table.rows[row].cells[cell].innerHTML,
            status: true
        };
    }


    $.ajax({
        type: "POST",
        url: "/editEmployee?date="+document.getElementById("calendarD").value,
        data: JSON.stringify(dataJson),


        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {

            deleteTable();
            loadEmployees(data);
            setRadioButton(data.numberList);

        },
        error: function (data) {
            alert(data);
        }
    })
    var table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("ondblclick", "dbClickTable(event)");
        table[i].setAttribute("onclick", "onClickTable(event)");
    }

}
function deleteTable() {
    var table = document.getElementById("employees")
    if (table.rowIndex > 0) {

        for (var i = table.rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
    }

}

function deleteTableComplex() {
    var allTable = document.getElementsByTagName("table")
    for (var i=0;i<allTable.length;i++){
        if (allTable[i].id == "complex1" || allTable[i].id == "complex2" || allTable[i].id == "complex3") {
                for (var j=allTable[i].rows.length-1;j>=0;j--){
                    allTable[i].deleteRow(j);
                }
        }
    }

}

function changeRadioButton(obj) {
    obj.checked=!obj.isChecked;


   if (obj.checked==true) {
        saveChangeComplex(obj.parentElement.parentElement.cells[1].abbr, obj.alt, document.getElementById("calendarD").value);

    } else {
        saveChangeComplex(obj.parentElement.parentElement.cells[1].abbr, obj.parentElement.abbr, document.getElementById("calendarD").value);

    }
    var table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("onclick", "onClickTable(event)");
    }
}

function changeRadioButton1(obj) {
    obj.isChecked=obj.checked;
}


function saveChangeComplex(idEmployee,idRecord,date) {
    $.ajax({
        type: "POST",
        url: "/saveChangeComplex?idEmployee=" + idEmployee + "&idRecord="
        + idRecord + "&date=" + date,
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
    countComplexes();
}

function countComplexes() {
    $.ajax({
        type: "POST",
        url: "/countComplexes?date="+document.getElementById("calendarD").value,
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data) {
            document.getElementById("countComplexFirst").innerText = data[0];
            document.getElementById("countComplexSecond").innerText = data[1];
            document.getElementById("countComplexThird").innerText = data[2];
        }
    })
}

function changeDate(obj) {


    var selectedDate = obj.value;
    var curDate = obj.alt;
    var admin = document.getElementById("button").value;

    var last = lastDate();
if  (last<=selectedDate) {


    if (admin == "Войти") {
        if (selectedDate <= curDate) {
            getAllByDate(selectedDate);
        } else {
            alert("Нельзя смотреть в будущее");
            document.getElementById("calendarD").value = curDate;
        }
    } else {
        if (selectedDate < curDate) {
            deleteButtonPages();
            getAllByDate(selectedDate);
        } else if (selectedDate == curDate) {
            addButtonPages();
            getAllByDate(selectedDate);
        } else if (selectedDate > curDate) {
            var lastDat = lastDate();
            var raz = new Date(selectedDate) - new Date(curDate);
            if (raz > 432000000) {   //5 дней в мс = 432 000 000
                alert("Нельзя создать меню более чем на 5 дней вперед");
            } else {
                if (selectedDate > lastDat) {
                    if (confirm("Вы действительно хотите создать меню на это число?")) {
                        getAllByDateAdmin(selectedDate);
                        addButtonPages();
                    }
                    else {
                        document.getElementById("calendarD").value = last;
                    }
                }
                else {
                    getAllByDateAdmin(selectedDate);
                    addButtonPages();
                }
            }
        }
    }
}else {
    deleteButtonPages();
    getAllByDate(selectedDate);
}
}
function lastDate(){
    var dat;
    $.ajax({
        type: "POST",
        url: "/lastDate",
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
        dat = data;
        }
    })
    return dat;
}
function checkDate(returnDate,selectedDate) {

    if (returnDate.date!=selectedDate){
        alert("Нету такой даты, дата будет установлена на последнее существующее")

    }
    document.getElementById("calendarD").value = returnDate.date;

}

function getAllByDate(selectedDate) {

    var dataJson = {
        date: selectedDate
    }

    $.ajax({
        type: "POST",
        url: "/getAllByDate",
        data: JSON.stringify(dataJson),

        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            checkDate(data.myDate,selectedDate);
            loadComplexes(data);
            loadEmployees(data);
            setRadioButton(data.numberList);
            var status = checkBlocked();
            if (!status){
                disabledRadioButton();
            }


        },
        error: function (data) {


         }
    })


}

function getAllByDateAdmin(selectedDate) {
    var dataJson = {
        date: selectedDate
    }

    $.ajax({
        type: "POST",
        url: "/getAllByDateAdmin",
        data: JSON.stringify(dataJson),

        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            loadComplexes(data);
            loadEmployees(data);
            setRadioButton(data.numberList);
            var status = checkBlocked();
            if (!status){
                disabledRadioButton();
            }

        },
        error: function (data) {
            alert("Нет такого числа, дата будет установлена на последнее существующее");
            document.getElementById("calendarD").value = curDate;
        }
    })


}

function disabledRadioButton() {
 var radio = document.getElementsByClassName("radioButton");
 for (var i=0; i< radio.length;i++){
     radio[i].disabled = true;
 }

}

function setRadioButton(listNumber) {
    var table = document.getElementById("employees");
    for (var i = 1; i < table.rows.length; i++) {
        var radio = table.rows[i].cells[3].children;
        for (var j = 0; j < radio.length; j++) {

            if (radio[j].value == listNumber[i - 1]) {
                radio[j].checked = true;
            }

        }

    }
    countComplexes();
}


function blockedDate() {
    $.ajax({
        type: "POST",
        url: "/setStatusDateFalse?date=" + document.getElementById("calendarD").value,
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    })
    deleteButtonPages();
    disabledRadioButton();

}

function checkBlocked() {
    var status;
    $.ajax({
        type: "POST",
        url: "/blockedDate?date=" + document.getElementById("calendarD").value,
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            status = data.blocked;
        },
        error: function (data) {
        }
    })
    return status;
}



String.prototype.hashCode = function() {
    var hash = 0, i, chr, len;
    if (this.length === 0) return hash;
    for (i = 0, len = this.length; i < len; i++) {
        chr   = this.charCodeAt(i);
        hash  = 31*hash + chr;
        hash |= 0; // Convert to 32bit integer
    }
    return hash;
};