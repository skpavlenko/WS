<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="shortcut icon" href="resources/images/icon.png">
    <title>1С ЦЕНТР</title>
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <meta name="description" content="1С Центр. Внедрение Управление производственным предприятием 8<br>Бюджетирование, бухгалтерский и управленческий учет">
    <meta name="Keywords" content="1С, УПП, Павленко Сергей, Pavlenko Sergey, Коржов Михаил, бухгалтерия">
</head>

<body>
<%@ include file="/resources/pages/navbar.jsp" %>

<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/partner/add" method="post">
        <h3>Партнер</h3>
        <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
        <label class="control-label" for="ord">Порядок (только цифры)<span class="required">*</span></label>
        <input class="form-control form-group" type="text" id="ord" name="ord" placeholder="Порядок (только цифры)"
               value="${ord}" required pattern="[0-9]{1,3}">
        <label class="control-label" for="name">Имя<span class="required">*</span></label>
        <input class="form-control form-group" type="text" id="name" name="name" placeholder="Имя" value="${name}">
        <label class="control-label" for="topic">Описание<span class="required">*</span></label>
        <textarea rows="4" cols="45" class="form-control form-group" id="topic" name="topic"
                  placeholder="Описание">${topic}</textarea>
        <label class="control-label" for="url">Ссылка<span class="required">*</span></label>
        <input class="form-control form-group" type="text" id="url" name="url" placeholder="Ссылка" value="${url}">
        <label class="control-label" for="logo">Логотип<span class="required">*</span></label>
        <img id="logo" src="/photo/${id}" width="300" height="170" alt="" class="img-responsive">
        <input class="form-control-file form-group" type="file" name="logo" placeholder="Логотип">
        <input type="submit" class="btn btn-primary" value="Записать">
    </form>
</div>
<br>
<br>
<%@ include file="/resources/pages/footer.jsp" %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/salvattore.min.js"></script>
</body>

</html>