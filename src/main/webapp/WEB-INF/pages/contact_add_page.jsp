<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>
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
