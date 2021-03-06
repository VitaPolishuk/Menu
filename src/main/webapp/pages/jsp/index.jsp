<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню</title>
    <link rel="stylesheet" href="/pages/css/indexCSs.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="/pages/js/indexJS.js"></script>
</head>
<script>

    objectModel = ${objectModel};

</script>
<script>
    _.templateSettings = {
        evaluate: /{{([\s\S]+?)}}/g,
        interpolate: /{{=([\s\S]+?)}}/g,
        escape: /{{-([\s\S]+?)}}/g
    };
</script>
<body onload="loadEmployees(objectModel); loadComplexes(objectModel); setRadioButton(objectModel.numberList)">

<div class="Main">

    <div class="allComplexes">
        <div class="head">
            <div class="titleMenu"><font face="Comic Sans MS" size="+2.5" color=#4F4F4F>Меню для котиков на </font>
            </div>
            <div class="calendar"><input type="date" id="calendarD" name="${idDate}" value="${currentDate}" alt="${currentDate}" onchange="changeDate(this)"></div>
        </div>

        <div class="complexes1">
            Комплекс 1
            <table id="complex1" class="tableComplexes1">
            </table>
        </div>
        <div class="complexes2">
            Комплекс 2
            <table id="complex2" class="tableComplexes2">
            </table>
        </div>
        <div class="complexes3">
            Комплекс 3
            <table id="complex3" class="tableComplexes3">
            </table>
        </div>
    </div>
    <div class="tableHead"></div>
    <div class="tableEmployees" id="tableEmployees">

    </div>

</div>

<div id="prompt-form-container">
    <form id="prompt-form">
        <div id="prompt-message"></div>
        <input id = "password" name="text" type="password">
        <input type="submit" value="Ок">
        <input type="button" name="cancel" value="Отмена">
    </form>
</div>

<div id="addEmployees-container">
    <p>ФИО</p> <input type="text"  class="" id="inputFIO" onclick="checkFIO()" >
    <p>Должность</p> <input type="text" class="" id="inputPositionHeld" onclick="checkPositionHeld()" >
    <p></p> <input type="submit" value="Добавить сотрудника" id="buttonAdd" onclick="addEmployee()">
</div>


<div class="header">
    <div>Режим админа</div>
    <div><input id="button" type="button" name = "${password}" value="Войти" onclick=authentication(this.name)></div>
    <div id="changePasswordLink"><a href="#" onclick="clickLink()">Сменить пароль</a></div>
    <br>

</div>
<div id="changePassword">
    <div>Старый пароль</div>
    <div><input type="password" class="" id="oldPassword" size="20px" onclick="checkoldPassword()" ></div>
    <div>Новый пароль</div>
    <div><input type="password"  class="" id="newPassword" size="20px" onclick="checknewPassword()"></div>
    <div>
        <input type="button" value="Сохранить" id="buttonSavePassword" name = "${password}" onclick="savePassword(this.name)">
        <input type="button" value="Отмена" id="buttonCancelPassword"  onclick="cancelPassword()">
    </div>

</div>

    <div id="windowDel">
        <button class="buttonDelete" onclick="deleteEmployees()">Delete</button>
    </div>

    <div class="images">
        <img src="/pages/css/tiger.png" width="50%">
    </div>

<div id="blockPages">
    <input type="button" value="Заблокировать меню" onclick="blockedDate()">

</div>


<div class="counter">
    <ol>Количество комплексов
        <li id = "countComplexFirst"></li>
        <li id = "countComplexSecond"></li>
        <li id = "countComplexThird"></li>
    </ol>
</div>


</body>

</html>

<script type="text/template" id="templateTable">
    <table id="employees" class="employees" rules="Cols">
        <tr>
            <th>№</th>
            <th>ФИО</th>
            <th>Должность</th>
            <th>Комплекс</th>
        </tr>

        {{ for (var i = 0; i < listEmployees.length; i++) { }}
        <tr class="tr1 stroka">
            <td class="tr1">{{= i + 1}}</td>
            <td class="tr1" id="idEmployee" abbr="{{= listEmployees[i].idEmployee}}">{{= listEmployees[i].fio }}</td>
            <td class="tr1">{{= listEmployees[i].positionHeld }}</td>
            <td class="tr1" abbr="{{=idRecordList[3]}}">
                <input type="radio" class="radioButton" name="{{= i}}" value="1" alt="{{=idRecordList[0]}}" onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)" >1
                <input type="radio" class="radioButton" name="{{= i}}" value="2" alt="{{=idRecordList[1]}}" onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">2
                <input type="radio" class="radioButton" name="{{= i}}" value="3" alt="{{=idRecordList[2]}}" onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">3
        </tr>
        {{ } }}
    </table>

</script>

<script type="text/template" id="templateComplex">

    <tr class="stroka">
        <td abbr="{{=listComplex.idComplex}}">{{=listComplex.firstCourse}}</td>
    </tr>
    <tr class="stroka">
        <td abbr="{{=listComplex.number}}">{{=listComplex.secondCourse}}</td>
    </tr>
    <tr class="stroka">
        <td>{{=listComplex.salad}}</td>
    </tr>
    <tr class="stroka">
        <td class="td1">{{=listComplex.drinks}}</td>
    </tr>


</script>