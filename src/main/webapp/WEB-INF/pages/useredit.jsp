<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>

<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>

<div class="container">
    <h3>Edit user credential</h3>

    <form role="form" class="form-horizontal"
          action="/user/edit" method="post">
        <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
        <input class="form-control form-group" type="text" name="login" placeholder="Login" value="${login}" readonly>
        <select class="selectpicker form-control form-group" name="role">
            <c:forEach items="${roles}" var="role">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
        <input class="form-control form-group" type="password" name="password" placeholder="Password">
        <input class="form-control form-group" type="text" name="email" placeholder="E-mail" value="${email}">
        <input class="form-control form-group" type="text" name="phone" placeholder="Phone" value="${phone}">
        <input class="form-control form-group" type="text" name="skype" placeholder="Skype" value="${skype}">
        <input type="submit" class="btn btn-primary" value="Add">
     </form>
</div>
<br>
<br>
<script>
    $('.selectpicker').val("${rle}");
    $('.selectpicker').selectpicker('render');
</script>
