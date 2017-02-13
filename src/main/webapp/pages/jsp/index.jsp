<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню</title>
    <link rel="stylesheet" href="/pages/css/indexCSS.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
    <script src="/pages/js/indexJS.js"></script>
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


<div class="Main">

    <div class = "allComplexes">
        <div class="head">
            <div class = "titleMenu"> <font face="Comic Sans MS" size="+2.5" color=#4F4F4F>Меню для котиков на </font></div>
            <div class = "currentDate"> <font face="Comic Sans MS" size="+2.8" color=#4F4F4F> ${currentDate}</font></div>
        </div>

        <div class="complexes1">

            <table class="tableComplexes1">
                <th>Комплекс 1</th>
                <tr>
                    <td>пюре+минтай "верасневый", в сметане с какашками и яйцами</td>
                </tr>
                <tr>
                    <td>Пюре ываыва ываыва вава выа ва ваыаваыва</td>
                </tr>
                <tr>
                    <td>Салатик</td>
                </tr>
                <tr>
                    <td class="td1">Сок</td>
                </tr>
            </table>

        </div>


        <div class="complexes2">

            <table class="tableComplexes2">
                <th>Комплекс 2</th>
                <tr>
                    <td>Супчик2</td>
                </tr>
                <tr>
                    <td>Пюре</td>
                </tr>
                <tr>
                    <td>Салатик</td>
                </tr>
                <tr>
                    <td class="td1">Сок</td>
                </tr>
            </table>

        </div>


        <div class="complexes3">

            <table class="tableComplexes3">
                <th>Комплекс 3</th>
                <tr>
                    <td>Супчик3</td>
                </tr>
                <tr>
                    <td>Пюре</td>
                </tr>
                <tr>
                    <td>Салатик</td>
                </tr>
                <tr>
                    <td class="td1">Сок</td>
                </tr>
            </table>
        </div>
    </div>
<div class="tableHead"></div>
    <div class="tableEmployees" id="tableEmployees"></div>

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
    <p></p> <input type="button" value="Добавить сотрудника" id="buttonAdd" onclick="addEmployees()">
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