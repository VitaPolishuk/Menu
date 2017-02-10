
var password = 1;
function f(th) {

    for (var i = 0; i < th.cells.length; i++) {
        th.cells[i].setAttribute("contenteditable", "true");

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

function addButtonPages() {
    var div = document.getElementById("addEmployees-container");
    div.style.display = "block";
    var masdiv = document.getElementsByClassName("compl");
    for (var i=0;i<masdiv.length;i++){
        masdiv[i].style.display = "block";
    }




}

function deleteButtonPages() {
    var div = document.getElementById("addEmployees-container");
    div.style.display = "none";
    var masdiv = document.getElementsByClassName("compl");
    for (var i=0;i<masdiv.length;i++){
        masdiv[i].style.display = "none";
    }

}