<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>

<div class="container">
    <c:url value="/newuser" var="regUrl"/>

    <form action="${regUrl}" role="form" class="form-horizontal" method="POST">
        <input class="form-control form-group" type="text" name="login" placeholder="Login">
        <input class="form-control form-group" type="password" name="password" placeholder="Password">
        <input class="form-control form-group" type="email" name="email" placeholder="E-mail">
        <input class="form-control form-group" type="text" name="skype" placeholder="Skype">

        <input type="submit" class="btn btn-primary" value="Add new user"/>

        <c:if test="${exists ne null}">
            <p>User already exists!</p>
        </c:if>
    </form>
</div>
<br>


