<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню</title>
    <link rel="stylesheet" href="/pages/css/indexCSs.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="/pages/js/indexJs.js"></script>
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
<body onload="loadEmployees(objectModel);
                loadComplexes(objectModel);
                setRadioButton(objectModel.numberList);
                timeBlocked();">

<div class="Main">

    <div class="allComplexes">
        <div class="head">
            <div class="buttonSaveTime"><input type="button" id="buttonSaveTime" value="Сохранить время" onclick="buttonSaveTime()"></div>
            <div class="checkboxTime"><input type="checkbox" id="saveTime" ></div>
            <div class="time"><input type="time" id="timeD" value="${time}" min="09:00" ></div>
            <div class="titleMenu"><font face="Comic Sans MS" size="+2.5" color=#4F4F4F>Меню для котиков на </font>
            </div>
            <div class="calendar"><input type="date" id="calendarD" name="${idDate}" value="${currentDate}"
                                         alt="${currentDate}" onchange="changeDate(this)"></div>
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
<!--для пароля-->
<div id="prompt-form-container">
    <form id="prompt-form">
        <div id="prompt-message"></div>
        <input id="password" name="text" type="password">
        <input type="submit" value="Ок">
        <input type="button" name="cancel" value="Отмена">
    </form>
</div>
<!--для добавления пользователя-->
<div id="prompt-form-container-add">
    <form id="prompt-form-add">
        <div id="prompt-message-add"></div>
        <div>ФИО <input type="text" name="text1" class="" id="inputFIO" required></div>
        <div>Должность <input type="text" name="text2" class="" id="inputPositionHeld" required></div>
        <input type="submit" value="Добавить">
        <input type="button" name="cancelAdd" value="Отмена">
    </form>
</div>
<!--для смены пароля-->
<div id="prompt-form-container-changePassword">
    <form id="prompt-form-changePassword">
        <div id="prompt-message-changePassword"></div>
        <div>Старый пароль</div>
        <div><input type="password" name="password1" class="" id="oldPassword" size="20px" required></div>
        <div>Новый пароль</div>
        <div><input type="password" name="password2" class="" id="newPassword" size="20px" required></div>
        <div>
            <input type="submit" value="Сохранить" id="buttonSavePassword" >
            <input type="button" value="Отмена" name = "cancelChangePassword" id="buttonCancelPassword" >
        </div>
    </form>
</div>
<!--для добавления блюда-->
<div id="prompt-form-container-dish">
    <form id="prompt-form-dish">
        <div id="prompt-message-dish"></div>
        <div>Название <input type="text" name="nameDish"  id="nameDish" required></div>
        <div>Тип <select id = "selectType" name = "selectType">
            <option value = "Первое">Первое</option>
            <option value = "Второе">Второе</option>
            <option value = "Салат">Салат</option>
            <option value = "Сок">Сок</option>
        </select>
        </div>
        <div>Картинка <input type="file" name="photo" multiple accept="image/*"></div>
        <input type="submit" value="Добавить">
        <input type="button" name="cancelDish" value="Отмена">
    </form>
</div>
<!--Кнопка добавления пользователя-->
<div id="addEmployees-container">
    <div><a href="#" class="addEmployee add" name="addEmployee" onclick="addEmployee()">Сотрудник</a></div>
</div>
<!--Кнопка добавления блюда-->
<div id="addDisр">
    <div><a href="#" class="addDish dish" name="addDis" onclick="addDish()">Блюдо</a></div>
</div>


<div class="header">
    <div>Режим админа</div>
    <div><input id="button" type="button" name="${password}" value="Войти" onclick=authentication(this.name)></div>
    <div id="changePasswordLink"><a href="#" name="${password}" onclick="savePassword(this.name)">Сменить пароль</a></div>
    <br>

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
    <ol class="older">
        <li id="countComplexFirst"><a href="#"></a></li>
        <li id="countComplexSecond"><a href="#"></a></li>
        <li id="countComplexThird"><a href="#"></a></li>
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
                <input type="radio" class="radioButton" name="{{= i}}" value="1" alt="{{=idRecordList[0]}}"
                       onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">1
                <input type="radio" class="radioButton" name="{{= i}}" value="2" alt="{{=idRecordList[1]}}"
                       onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">2
                <input type="radio" class="radioButton" name="{{= i}}" value="3" alt="{{=idRecordList[2]}}"
                       onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">3
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