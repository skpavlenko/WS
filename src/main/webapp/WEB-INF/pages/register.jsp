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
        <c:url value="/newuser" var="regUrl"/>

        <form action="${regUrl}" role="form" class="form-horizontal" method="POST">
            Login:<br/><input type="text" name="login"><br/>
            Password:<br/><input type="password" name="password"><br/>
            E-mail:<br/><input type="text" name="email"><br/>
            Phone:<br/><input type="text" name="phone"><br/>
            Skype:<br/><input type="text" name="skype"><br/>
            <input type="submit"/>

            <c:if test="${exists ne null}">
                <p>User already exists!</p>
            </c:if>
        </form>
</div>
<br>
<%@ include file="/resources/pages/footer.jsp" %>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>
</body>
</html>
