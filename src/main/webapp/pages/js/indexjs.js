
var password = 1;
function dbClickTable(ev) {
var row = ev.target.parentElement.rowIndex;
var cell = ev.target.cellIndex;
var table = ev.currentTarget;
 if ((table.id == "employees")&(row==0 || cell==0 || cell==3)) {
     alert("КУДА ТЫКАЕШЬ");
     }else {
     table.rows[row].cells[cell].setAttribute("contenteditable", "true");
     table.rows[row].cells[cell].focus();
     table.rows[row].cells[cell].onblur = function () {

         table.rows[row].cells[cell].setAttribute("contenteditable", "false");
     }
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
        hideCover();
        container.style.display = 'none';
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

function authentication() {
    var name = document.getElementById("button").value;

    if (name=="Войти") {
        showPrompt("Введите что-нибудь<br>...умное :)", function (value) {
            if (value != null) {
                if (value == password) {
                    addButtonPages();
                    document.getElementById("button").value = "Выйти";
                } else {
                    alert("Не угодал ");
                }
            } else {
            }
        });
    }else{
        document.getElementById("button").value = "Войти";
        deleteButtonPages();
    }
}

function loadEmployees(listEmployees) {

    var template = document.getElementById('templateTable').innerHTML.trim();
    template = _.template(template);
    document.getElementById('tableEmployees').innerHTML = template({
        listEmployees: listEmployees


    });
}

function loadComplexes(listComplex) {

    var template = document.getElementById('templateComplex').innerHTML.trim();
    template = _.template(template);
    for (var i=0;i<listComplex.length;i++) {
        var str = 'complex'+(i+1);
        document.getElementById(str).innerHTML = template({
            listComplex: listComplex[i]


        });
    }
}

function addButtonPages() {
    var div = document.getElementById("addEmployees-container");
    div.style.display = "block";
    var table = document.getElementsByTagName("table");
    for (var i=0;i<table.length;i++){
        table[i].setAttribute("ondblclick","dbClickTable(event)");
    }



    var masdiv = document.getElementsByClassName("compl");
    for (var i=0;i<masdiv.length;i++){
        masdiv[i].style.display = "block";
    }




}

function deleteButtonPages() {
    var div = document.getElementById("addEmployees-container");
    div.style.display = "none";
    var table = document.getElementsByTagName("table");
    for (var i=0;i<table.length;i++){
        table[i].setAttribute("ondblclick","");
    }

    var masdiv = document.getElementsByClassName("compl");
    for (var i=0;i<masdiv.length;i++){
        masdiv[i].style.display = "none";
    }

}

function addEmployee() {
    var dataJson = {
        fio: document.getElementById("inputFIO").value,
        positionHeld: document.getElementById("inputPositionHeld").value,

    };

    $.ajax({
        type: "POST",
        url: "/addEmployee",
        data: JSON.stringify(dataJson)

        ,
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
        },
        error: function (data) {
            alert(data);
        }
    })

}

function deleteTable() {
    var table = document.getElementById("employees")

    for (var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

}