<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню</title>
    <link rel="stylesheet" href="/pages/css/indexCSs.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
    <script src="/pages/js/indexJs.js"></script>
</head>
<script>
    listEmployees = ${listEmployees};
</script>
<script>
    _.templateSettings = {
        evaluate: /{{([\s\S]+?)}}/g,
        interpolate: /{{=([\s\S]+?)}}/g,
        escape: /{{-([\s\S]+?)}}/g
    };
</script>
<body onload="loadEmployees(listEmployees)">
<div class = "header">
    <font face="Comic Sans MS" size="+3" color=#632d03 >Меню для котиков</font></div>

<div id = "windowDel">
    <button class = "buttonDelete">Delete</button>

</div>
<div class="Main">
    <div class="complexes">
        <table class = "tableComplexes" >
            <thead>
            <th>Комплекс 1</th>
            <th>Комплекс 2</th>
            <th>Комплекс 3</th>
            </thead>
            <tbody>
            <tr ondblclick="f(this)" onclick="ff(this)">
                <td>Супчик1</td>
                <td>Супчик2</td>
                <td>Супчик3</td>
            </tr>
            <tr ondblclick="f(this)" onclick="ff(this)">
                <td>Пюре</td>
                <td>Пюре</td>
                <td>Пюре</td>
            </tr>
            <tr ondblclick="f(this)" onclick="ff(this)">
                <td>Салатик</td>
                <td>Салатик</td>
                <td>Салатик</td>
            </tr>
            <tr ondblclick="f(this)" onclick="ff(this)">
                <td class="td1">Сок</td>
                <td class="td1">Сок</td>
                <td class="td1">Сок</td>
            </tr>
            </tbody>
        </table>
    </div>
    <input id="button" type="button" value="Войти" onclick=authentication()>
    <div class="tableEmployees" id="tableEmployees"></div>
    <div id  = "newButton"></div>
</div>
<div id="prompt-form-container">
    <form id="prompt-form">
        <div id="prompt-message"></div>
        <input name="text" type="text">
        <input type="submit" value="Ок">
        <input type="button" name="cancel" value="Отмена">
    </form>
</div>
<div id="addEmployees-container">
   <p>ФИО</p> <input type="text" id="inputFIO">
    <p>Должность</p> <input type="text" id="inputPositionHeld">
    <p></p> <input type="button" value="Добавить сотрудника" id="buttonAdd" onclick="addEmployees()">
</div>

<script type="text/template" id="templateTable">
    <table id="employees" class="employees">
        <tr class="tr1 stroka">
            <th class="tr1">№</th>
            <th class="tr1">ФИО</th>
            <th class="tr1">Должность</th>
            <th class="tr1">Комплекс</th>
        </tr>
        {{ for (var i = 0; i < listEmployees.length; i++) { }}
        <tr class="tr1 stroka">
            <td class="tr1">{{= listEmployees[i].number }}</td>
            <td class="tr1">{{= listEmployees[i].fio }}</td>
            <td class="tr1">{{= listEmployees[i].positionHeld }}</td>
            <td class="tr1"><input type="radio" name="{{= i}}" value="1">1
                <input type="radio" name="{{= i}}" value="2">2
                <input type="radio" name="{{= i}}" value="3">3
        </tr>
        {{ } }}
    </table>
</script>
</body>

</html>

