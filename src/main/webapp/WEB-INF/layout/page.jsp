<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="shortcut icon" href="<c:url value='/resources/images/icon.png' />"></link>
    <title>1С ЦЕНТР</title>
    <!-- Bootstrap -->
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/font-awesome.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet"></link>
    <script src="<c:url value='/resources/js/jquery.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.js' />"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<c:url value='/resources/js/html5shiv.min.js' />"></script>
    <script src="<c:url value='/resources/js/respond.min.js' />"></script>
    <![endif]-->
    <meta name="description"
          content="1С Центр. Внедрение Управление производственным предприятием 8<br>Бюджетирование, бухгалтерский и управленческий учет">
    <meta name="Keywords"
          content="1С, УПП, Павленко Сергей, Pavlenko Sergey, Коржов Михаил, Spring, Spring Security, Java, Hibernate, бухгалтерский учет, управленческий учет">
</head>
<body>
<t:insertAttribute name="navbar"/>
<t:insertAttribute name="body"/>
<t:insertAttribute name="footer"/>
</body>
</html>
