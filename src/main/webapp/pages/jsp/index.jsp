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
    listEmployees = ${listEmployees};
    listComplexes = ${listComplexes};
</script>
<script>
    _.templateSettings = {
        evaluate: /{{([\s\S]+?)}}/g,
        interpolate: /{{=([\s\S]+?)}}/g,
        escape: /{{-([\s\S]+?)}}/g
    };
</script>
<body onload="loadEmployees(listEmployees); loadComplexes(listComplexes)">



<div class="Main">

    <div class = "allComplexes">
        <div class="head">
            <div class = "titleMenu"> <font face="Comic Sans MS" size="+2.5" color=#4F4F4F>Меню для котиков на </font></div>
            <div class = "calendar"><input type="date" id="calendarD" value="${currentDate}"></div>
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
        <input name="text" type="password">
        <input type="submit" value="Ок">
        <input type="button" name="cancel" value="Отмена">
    </form>
</div>

<div id="addEmployees-container">
    <p>ФИО</p> <input type="text" id="inputFIO">
    <p>Должность</p> <input type="text" id="inputPositionHeld">
    <p></p> <input type="button" value="Добавить сотрудника" id="buttonAdd" onclick="addEmployee()">
</div>

<div class="header">
    <div>Режим админа</div>
    <div> <input id="button" type="button" value="Войти" onclick=authentication()></div>
</div>

<div id="windowDel">
    <button class="buttonDelete">Delete</button>
</div>

<div class="images">
    <img src="/pages/css/tiger.png" width="50%">
</div>

</body>

</html>

<script type="text/template" id="templateTable">
    <table id="employees" class="employees" rules="Cols">
        <tr >
            <th >№</th>
            <th >ФИО</th>
            <th >Должность</th>
            <th >Комплекс</th>
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

<script type="text/template" id="templateComplex">

        <tr class="stroka">
            <td>{{=listComplex.firstCourse}}</td>
        </tr>
        <tr class="stroka">
            <td>{{=listComplex.secondCourse}}</td>
        </tr>
        <tr class="stroka">
            <td>{{=listComplex.salad}}</td>
        </tr>
        <tr class="stroka">
            <td class="td1">{{=listComplex.drinks}}</td>
        </tr>


</script>