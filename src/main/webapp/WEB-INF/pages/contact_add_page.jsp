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
    <meta name="description"
          content="1С Центр. Внедрение Управление производственным предприятием 8<br>Бюджетирование, бухгалтерский и управленческий учет">
    <meta name="Keywords"
          content="1С, УПП, Павленко Сергей, Pavlenko Sergey, Коржов Михаил, Spring, Spring Security, Java, Hibernate, бухгалтерский учет, управленческий учет">

</head>
<body>
<%@ include file="/resources/pages/navbar.jsp" %>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/contact/add" method="post">
        <h3>New contact</h3>
        <select class="selectpicker form-control form-group" name="group">
            <option value="-1">Default</option>
            <c:forEach items="${groups}" var="group">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
        <input class="form-control form-group" type="text" name="name" placeholder="Name">
        <input class="form-control form-group" type="text" name="surname" placeholder="Short description">
        <input class="form-control form-group" type="text" name="phone" placeholder="Long description">
        <input class="form-control form-group" type="text" name="email" placeholder="Phone">
        <input type="submit" class="btn btn-primary" value="Add">
    </form>
</div>

<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>