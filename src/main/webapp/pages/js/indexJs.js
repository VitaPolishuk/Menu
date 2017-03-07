function TEST() {

    $.ajax({
        type: "POST",
        url: "/asdfgh?qwe="+123,
        async: false,
        dataType: "json",
    contentType: "image/jpg",
        success: function (data, textStatus, jqXHR) {
           alert(data);
        },
        error: function (data) {
            alert(1);
        }
    })

}


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
            } else if (table.id == "complex1" || table.id == "complex2" || table.id == "complex3") {
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
    var del = document.getElementById("windowDel");
    var window = document.documentElement.clientWidth;
    var allTable = document.getElementsByTagName("table");
    for (var j = 0; j < allTable.length; j++) {
        for (var i = 0; i < allTable[j].rows.length; i++) {
            allTable[j].rows[i].setAttribute("class", "stroka");
        }
    }
    if (ev.target.parentElement.tagName == "TR" && row != 0) {
        ev.target.parentElement.setAttribute("class", "stroka selectColor");
        if (table.id = "employees"){
            var rect = table.rows[row].cells[3].getBoundingClientRect();
            del.style.top = rect.top;
            del.style.right = window - rect.right;

        }
    }
}
//функции для добавления диалогового окна на вход в админ
function showCover() {
    var coverDiv = document.createElement('div');
    coverDiv.id = 'cover-div';
    document.body.appendChild(coverDiv);
}
function showPrompt(text, callback) {
    showCover();
    var form = document.getElementById('prompt-form');
    var container = document.getElementById('prompt-form-container');
    document.getElementById('prompt-message').innerHTML = text;
    form.elements.text.value = '';

    function complete(value) {
       if (value == null) {
            container.style.display = "none";
        }
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
//функции для добавления диалогового окна для добавления пользователя
function showCoverAdd() {
    var coverDivAdd = document.createElement('div');
    coverDivAdd.id = 'cover-div-add';
    document.body.appendChild(coverDivAdd);
}
function showPromptAdd(text, callback) {
    showCoverAdd();
    var form = document.getElementById('prompt-form-add');
    var container = document.getElementById('prompt-form-container-add');
    document.getElementById('prompt-message-add').innerHTML = text;
    //form.elements.text.value1 = '';
    //form.elements.text.value2 = '';

    function completeAdd(value1, value2) {

        container.style.display = "none";
        document.body.removeChild(document.getElementById('cover-div-add'));
        document.onkeydown = null;
        callback(value1, value2);
    }
    form.onsubmit = function () {
        var value1 = form.elements.text1.value;
        if (value1 == '') return false; // игнорировать пустой submit
        var value2 = form.elements.text2.value;
        if (value2 == '') return false; // игнорировать пустой submit
        completeAdd(value1, value2);
        return false;
    };
    form.elements.cancelAdd.onclick = function () {
        completeAdd(null, null);
    };
    container.style.display = 'block';
    form.elements.text1.focus();
    //form.elements.text2.focus();
}
// функции для диалогового окна на смену пароля
function showCoverChangePassword() {
    var coverDivChangePassword = document.createElement('div');
    coverDivChangePassword.id = 'cover-div-changePassword';
    document.body.appendChild(coverDivChangePassword);
}
function showPromptChangePassword(text, callback) {
    showCoverChangePassword();
    var form = document.getElementById('prompt-form-changePassword');
    var container = document.getElementById('prompt-form-container-changePassword');
    document.getElementById('prompt-message-changePassword').innerHTML = text;
    form.elements.password1.value1 = null;
    form.elements.password2.value2 = null;

    function completeChangePassword(value1, value2) {

        if (value1 == null && value2 == null) {
            container.style.display = "none";
            document.body.removeChild(document.getElementById('cover-div-changePassword'));
        }
        document.onkeydown = null;
        callback(value1, value2);
    }
    form.onsubmit = function () {
        var value1 = form.elements.password1.value;
        if (value1 == '') return false; // игнорировать пустой submit
        var value2 = form.elements.password2.value;
        if (value2 == '') return false; // игнорировать пустой submit
        completeChangePassword(value1, value2);
        return false;
    };
    form.elements.cancelChangePassword.onclick = function () {
        completeChangePassword(null, null);
    };
    container.style.display = 'block';
    form.elements.password1.focus();
}
// функции для добавления диалогового окна блюда
function showCoverDish() {
    var coverDivDish = document.createElement('div');
    coverDivDish.id = 'cover-div-dish';
    document.body.appendChild(coverDivDish);
}
function showPromptDish(text, callback) {
    showCoverDish();
    var form = document.getElementById('prompt-form-dish');
    var container = document.getElementById('prompt-form-container-dish');
    document.getElementById('prompt-message-dish').innerHTML = text;
    //form.elements.text.value1 = '';
    //form.elements.text.value2 = '';

    function completeDish(nameDish, typeDish,imgDish) {

        container.style.display = "none";
        document.body.removeChild(document.getElementById('cover-div-dish'));
        document.onkeydown = null;
        callback(nameDish, typeDish, imgDish);
    }
    form.onsubmit = function () {
        var nameDish = form.elements.nameDish.value;
        if (nameDish == '') return false; // игнорировать пустой submit
        var typeDish = form.elements.selectType.value;
        if (typeDish == '') return false; // игнорировать пустой submit
        var imgDish = form.elements.photo.value;
        if (imgDish == '') return false; // игнорировать пустой submit
        completeDish(nameDish, typeDish, imgDish);
        return false;
    };
    form.elements.cancelDish.onclick = function () {
        completeDish(null, null, null);
    };
    container.style.display = 'block';
    form.elements.nameDish.focus();
    //form.elements.text2.focus();
}

function authentication(password) {
    var name = document.getElementById("button").value;
    var fl;
    if (name == "Войти") {
        showPrompt("Введите пароль<br>администратора :)", function (value) {
            if ((value + "") != null) {
                if ((value.hashCode() + "") == password) {
                    var last = lastDate();
                    if (document.getElementById("calendarD").value == last) {
                        addButtonPages();
                        openAdmin();
                        document.getElementById("button").value = "Выйти";
                        document.getElementById('prompt-form-container').style.display = "none";
                        document.body.removeChild(document.getElementById('cover-div'));
                    }
                    document.getElementById("button").value = "Выйти";
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

function openAdmin() {
    $.ajax({
        type: "POST",
        url: "/openAdmin",
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {

        }
    })
    dhjkl
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
    var listDishToOneComplex = [];
    var template = document.getElementById('templateComplex').innerHTML.trim();
    template = _.template(template);
    for (var i = 0; i < 3; i++) {
        for (var j = i;j<objectModel.dishList.length;j+=3){
              listDishToOneComplex.push(objectModel.dishList[j]);

        }

        var str = 'complex' + (i + 1);
        document.getElementById(str).innerHTML = template({
            listDishToOneComplex: listDishToOneComplex
        });
    }
}
function addButtonPages() {

    var div = document.getElementById("addEmployees-container");
    div.style.display = "block";
    document.getElementById("addDish").style.display = "block";
    var table = document.getElementsByTagName("table");
    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("ondblclick", "dbClickTable(event)");
    }

    for (var i = 0; i < table.length; i++) {
        table[i].setAttribute("onclick", "onClickTable(event)");
    }
    document.getElementById("windowDel").style.display = "block";
    document.getElementById("windowDel").style.right = "-200";
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
    document.getElementById("addDish").style.display = "none";

}

function savePassword(password) {
    showPromptChangePassword("Смена пароля", function(value1, value2){
    var oldPassword = value1;
    var newPassword = value2;
    if ((oldPassword+"") != null && (newPassword+"") != null)  {
            if ((oldPassword.hashCode() + "") == password) {
                $.ajax({
                    type: "POST",
                    url: "/changePassword?passwordNew=" + value2,
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

                        document.getElementById('prompt-form-container-changePassword').style.display = "none";
                        document.body.removeChild(document.getElementById('cover-div-changePassword'));
                    }
                })
            }
            else {
                alert("Неверный старый пароль!");
                document.getElementById('prompt-form-changePassword').elements.password1.value = null;
                document.getElementById('prompt-form-changePassword').elements.password2.value = null;
            }


    }});
}

function editComplex(table) {

    var dataJson = {
        idComplex: table.rows[0].cells[0].abbr,
        firstCourse: table.rows[0].cells[0].innerHTML,
        secondCourse: table.rows[1].cells[0].innerHTML,
        number: table.rows[1].cells[0].abbr,
        salad: table.rows[2].cells[0].innerHTML,
        drinks: table.rows[3].cells[0].innerHTML
    };

    $.ajax({
        type: "POST",
        url: "/editComplex?date=" + document.getElementById("calendarD").value,
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
            document.getElementById("timeD").value = data.timeBlocked.currentTime;

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

    showPromptAdd("Добавление сотрудника:)", function (value1, value2) {
        var inputFIO = value1;
        var inputPositionHeld = value2;
            if (inputFIO != null && inputPositionHeld != null) {
                var dataJson = {
                    fio: inputFIO,
                    positionHeld: inputPositionHeld
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
                        document.getElementById("timeD").value = data.timeBlocked.currentTime;
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

    });

}
function addDish(){
    showPromptDish("Новое блюдо", function (nameDish, typeDish, photo){
        alert(nameDish);
        alert(typeDish);
        alert(photo);
    })
}
function deleteEmployees() {

    var table = document.getElementById("employees");
    for (var i = 0; i < table.rows.length; i++) {
        var str = table.rows[i].getAttribute("class");
        if (str == "stroka selectColor") {
            var dataJson = {idEmployee: table.rows[i].cells[1].abbr};

        }
    }
    $.ajax({
        type: "POST",
        url: "/deleteEmployee?date=" + document.getElementById("calendarD").value,
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
            document.getElementById("timeD").value = data.timeBlocked.currentTime;
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
    document.getElementById("windowDel").style.right = "-200";
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
        url: "/editEmployee?date=" + document.getElementById("calendarD").value,
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
            document.getElementById("timeD").value = data.timeBlocked.currentTime;

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
    for (var i = 0; i < allTable.length; i++) {
        if (allTable[i].id == "complex1" || allTable[i].id == "complex2" || allTable[i].id == "complex3") {
            for (var j = allTable[i].rows.length - 1; j >= 0; j--) {
                allTable[i].deleteRow(j);
            }
        }
    }

}
function changeRadioButton(obj) {
    obj.checked = !obj.isChecked;


    if (obj.checked == true) {
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
    obj.isChecked = obj.checked;
}
function saveChangeComplex(idEmployee, idRecord, date) {
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
        url: "/countComplexes?date=" + document.getElementById("calendarD").value,
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
    if (last <= selectedDate) {


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
    } else {
        deleteButtonPages();
        getAllByDate(selectedDate);
    }
}
function lastDate() {
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
function checkDate(returnDate, selectedDate) {

    if (returnDate.date != selectedDate) {
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
            checkDate(data.myDate, selectedDate);
            loadComplexes(data);
            loadEmployees(data);
            setRadioButton(data.numberList);
            var status = checkBlocked();
            if (!status) {
                disabledRadioButton();
            }
            document.getElementById("timeD").value = data.timeBlocked.currentTime;


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

            document.getElementById("timeD").value = data.timeBlocked.currentTime;
        },
        error: function (data) {
            alert("Нет такого числа, дата будет установлена на последнее существующее");
            document.getElementById("calendarD").value = curDate;
        }
    })
}
function disabledRadioButton() {
    var radio = document.getElementsByClassName("radioButton");
    for (var i = 0; i < radio.length; i++) {
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
    var sqlDate = new Date().toISOString().slice(0, 10).replace('T', ' ');
    $.ajax({
        type: "POST",
        url: "/setStatusDateFalse?date=" + sqlDate,
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


function timeBlocked() {
    var time = document.getElementById("timeD").value;gggggggggg
    var curDate = new Date();
    var hour = parseInt(time.split(':')[0]);
    var minute = parseInt(time.split(':')[1]);
    if (curDate.getHours() <= hour && curDate.getMinutes() <= minute) {
    var timerId = setInterval(function() {

                if (curDate.getHours() >= hour && curDate.getMinutes() >= minute) {
                    blockedDate();
                    clearInterval(timerId);
        }

    }, 1000);
    }
}

function buttonSaveTime() {
    $.ajax({
        type: "POST",
        url: "/buttonSaveTime?status=" + document.getElementById("saveTime").checked+"&time="+document.getElementById("timeD").value,
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })
}

document.onmousemove = moveTip;
function moveTip(e) {
    var floatTipStyle = document.getElementById("floatTip").style;
    var w = 250; // Ширина слоя
    // Для браузера IE
    if (document.all) {
      var  x = event.x + document.body.scrollLeft;
        var y = event.y + document.body.scrollTop;
        // Для остальных браузеров
    } else {
        x = e.pageX; // Координата X курсора
        y = e.pageY; // Координата Y курсора
    }
    // Показывать слой справа от курсора
    if ((x + w + 10) < document.body.clientWidth) {
        floatTipStyle.left = x + 'px';
        // Показывать слой слева от курсора
    } else {
        floatTipStyle.left = x - w + 'px';
    }
    // Положение от верхнего края окна браузера
    floatTipStyle.top = y + 20 + 'px';
}
function toolTip(msg) {
    var floatTipStyle = document.getElementById("floatTip").style;

    if (msg) {
        img = document.createElement('img');
        img.src = "/image?id=1";
        img.width = 100;
        img.height = 100;
        document.getElementById("floatTip").appendChild(img);

        // Выводим текст подсказки
       // document.getElementById("floatTip").innerHTML = msg;
        floatTipStyle.display = "block"; // Показываем слой
    } else {
        floatTipStyle.display = "none"; // Прячем слой
        document.getElementById("floatTip").removeChild(img);
    }
}

String.prototype.hashCode = function () {
    var hash = 0, i, chr, len;
    if (this.length === 0) return hash;
    for (i = 0, len = this.length; i < len; i++) {
        chr = this.charCodeAt(i);
        hash = 31 * hash + chr;
        hash |= 0; // Convert to 32bit integer
    }
    return hash;
};

Date.prototype.compare = function(value) {
if (this.getFullYear()==value.getFullYear()){
    if (this.getMonth()==value.getMonth()){
        if (this.getDate()==value.getDate()){
            return true;
        }
    }
}
return false;
};