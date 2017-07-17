<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>

