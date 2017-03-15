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
                timeBlocked()">

<div class="Main">

    <div class="allComplexes">
        <div class="head">

            <div class="buttonSaveTime"><input type="button" id="buttonSaveTime" value="Сохранить время"
                                               onclick="buttonSaveTime()"></div>
            <div class="checkboxTime"><input type="checkbox" id="saveTime"></div>
            <div class="time"><input type="time" id="timeD" value="${time}" min="09:00"></div>
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
            <input type="submit" value="Сохранить" id="buttonSavePassword">
            <input type="button" value="Отмена" name="cancelChangePassword" id="buttonCancelPassword">
        </div>
    </form>
</div>
<!--для добавления блюда-->
<div id="prompt-form-container-dish">
    <form id="prompt-form-dish" action="" method="post" enctype="multipart/form-data">
        <div id="prompt-message-dish"></div>
        <div class="nameDish">Название <input type="text" name="nameDish" id="nameDish" required></div>
        <div class="typeDish">Тип блюда<select id="selectType" name="selectType">
            <option value="Первое" selected>Первое</option>
            <option value="Гарнир">Второе</option>
            <option value="Мясное">Мясное</option>
            <option value="Салат">Салат</option>
            <option value="Сок">Сок</option>
        </select>
        </div>
        <div class="fileform">
            <div id="fileformlabel"></div>
            <div class="selectbutton">Обзор</div>
            <input type="file" name="upload" id="upload" onchange="getName(this.value)" multiple accept="image/*">
        </div>
        <input type="submit" value="Добавить">
        <input type="button" name="cancelDish" value="Отмена">
    </form>
</div>
<!--для редактирования блюда-->
<div id="prompt-form-container-dishEdit">
    <form id="prompt-form-dishEdit" action="" method="post" enctype="multipart/form-data">
        <div id="prompt-message-dishEdit">Редактирование блюда</div>
        <div class="nameDishEdit">Название <input type="text" name="nameDishEdit" id="nameDishEdit" required></div>
       <div class = "typeAndImg">
        <div class="typeDishEdit">Тип блюда<select id="selectTypeEdit" name="selectTypeEdit">
            <option value="Первое" selected>Первое</option>
            <option value="Гарнир">Второе</option>
            <option value="Мясное">Мясное</option>
            <option value="Салат">Салат</option>
            <option value="Сок">Сок</option>
        </select>
        </div>
        <div id="imgDishOld"></div>
       </div>
        <div class="fileformEdit">
            <div id="fileformlabelEdit"></div>
            <div class="selectbuttonEdit">Обзор</div>
            <input type="file" name="uploadEdit" id="uploadEdit" onchange="getNameEdit(this.value)" multiple accept="image/*">
        </div>
        <input type="submit" value="Сохранить">
        <input type="button" name="cancelDishEdit" value="Отмена">
    </form>
</div>
<!--Кнопка добавления пользователя-->
<div id="addEmployees-container">
    <div><a href="#" class="addEmployee add" name="addEmployee" onclick="addEmployee()">Сотрудник</a></div>
</div>
<!--Кнопка добавления блюда-->
<div id="addDish-container">
    <div><a href="#" class="addDish add" name="addDish" onclick="addDish()">Блюдо</a></div>
</div>

<div class="header">
    <div>Режим админа</div>
    <div><input id="button" type="button" name="${password}" value="Войти" onclick=authentication(this.name)></div>
    <div id="changePasswordLink"><a href="#" name="${password}" onclick="savePassword(this.name)">Сменить пароль</a>
    </div>
    <br>

</div>
<div id="windowDel">
    <button class="buttonDelete" onclick="deleteEmployees()">[X]</button>
</div>

<div class="images">
    <img src="/pages/css/tiger.png" width="50%">
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
<!--всплывающее окошко-->
<div id="floatTip"></div>

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
            <td class="tr1" abbr="{{=idRecordList[0]}}">
                <input type="radio" class="radioButton" name="{{= i}}" value="1" alt="{{=idRecordList[1]}}"
                       onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">1
                <input type="radio" class="radioButton" name="{{= i}}" value="2" alt="{{=idRecordList[2]}}"
                       onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">2
                <input type="radio" class="radioButton" name="{{= i}}" value="3" alt="{{=idRecordList[3]}}"
                       onclick="changeRadioButton(this)" onmousedown="changeRadioButton1(this)">3
        </tr>
        {{ } }}
    </table>

</script>

<script type="text/template" id="templateComplex">

    <tr>
        <td abbr="{{=listDishToOneComplex[0].idDish}}" colspan="2" onmouseover="toolTip(this.abbr)"
            onmouseout="toolTip()">{{=listDishToOneComplex[0].nameDish}}
        </td>
    </tr>
    <tr>
        <td abbr="{{=listDishToOneComplex[1].idDish}}" onmouseover="toolTip(this.abbr)" onmouseout="toolTip()">
            {{=listDishToOneComplex[1].nameDish}}
        </td>
        <td abbr="{{=listDishToOneComplex[2].idDish}}" onmouseover="toolTip(this.abbr)" onmouseout="toolTip()">
            {{=listDishToOneComplex[2].nameDish}}
        </td>
    </tr>
    <tr>
        <td abbr="{{=listDishToOneComplex[3].idDish}}" colspan="2" onmouseover="toolTip(this.abbr)"
            onmouseout="toolTip()">{{=listDishToOneComplex[3].nameDish}}
        </td>
    </tr>
    <tr>
        <td class="td1" abbr="{{=listDishToOneComplex[4].idDish}}" colspan="2" onmouseover="toolTip(this.abbr)"
            onmouseout="toolTip()">{{=listDishToOneComplex[4].nameDish}}
        </td>
    </tr>


</script>

<script type="text/template" id="templateComplexAdmin">
    <tr>
        <td colspan="2"><select onmouseover="toolTip(this.selectedOptions[0].label)" onmouseout="toolTip()">
            {{ for (var i=0;i < listDish.dishListFirst.length;i++) { }}
            <option label="{{=listDish.dishListFirst[i].idDish}}" >{{=listDish.dishListFirst[i].nameDish}}
            </option>
            {{ } }}
        </select>
            <a class="editDishButton" href="#"  onclick = "editDish(this)">
                <span class="editDishButtonIcon"></span>
            </a>
        </td>

    </tr>
    <tr>
        <td><select onmouseover="toolTip(this.selectedOptions[0].label)" onmouseout="toolTip()">
            {{ for (var i=0;i < listDish.dishListGarnor.length;i++) { }}
            <option label="{{=listDish.dishListGarnor[i].idDish}}" >{{=listDish.dishListGarnor[i].nameDish}}
            </option>
            {{ } }}
        </select>
            <a class="editDishButton" href="#"  onclick = "editDish(this)">
                <span class="editDishButtonIcon"></span>
            </a>
        </td>

        <td><select onmouseover="toolTip(this.selectedOptions[0].label)" onmouseout="toolTip()">
            {{ for (var i=0;i < listDish.dishListMeat.length;i++) { }}
            <option label="{{=listDish.dishListMeat[i].idDish}}" >{{=listDish.dishListMeat[i].nameDish}}
            </option>
            {{ } }}
        </select>
            <a class="editDishButton" href="#" onclick = "editDish(this)">
                <span class="editDishButtonIcon"></span>
            </a>
        </td>
    </tr>
    <tr>
        <td colspan="2"><select onmouseover="toolTip(this.selectedOptions[0].label)" onmouseout="toolTip()">
            {{ for (var i=0;i < listDish.dishListSalat.length;i++) { }}
            <option label="{{=listDish.dishListSalat[i].idDish}}" >{{=listDish.dishListSalat[i].nameDish}}
            </option>
            {{ } }}
        </select>
            <a class="editDishButton" href="#" onclick = "editDish(this)">
                <span class="editDishButtonIcon"></span>
            </a>
        </td>
    </tr>
    <tr>
        <td colspan="2"><select onmouseover="toolTip(this.selectedOptions[0].label)" onmouseout="toolTip()">
            {{ for (var i=0;i < listDish.dishListDrink.length;i++) { }}
            <option label="{{=listDish.dishListDrink[i].idDish}}">{{=listDish.dishListDrink[i].nameDish}}
            </option>
            {{ } }}
        </select>
            <a class="editDishButton" href="#"  onclick = "editDish(this)">
                <span class="editDishButtonIcon"></span>
            </a>
        </td>
    </tr>
</script>