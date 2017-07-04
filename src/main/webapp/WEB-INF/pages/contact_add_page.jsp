<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/font-awesome.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>

<script src="resources/js/html5shiv.min.js"></script>
<script src="resources/js/respond.min.js"></script>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/contact/add" method="post">
        <h3>New contact</h3>
        <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
        <select class="selectpicker form-control form-group" name="group">
            <option value="-1">Default</option>
            <c:forEach items="${groups}" var="group">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
        <input class="form-control form-group" type="text" name="name" placeholder="Name" value="${name}">
        <input class="form-control form-group" type="text" name="surname" placeholder="Short description" value="${surname}">
        <input class="form-control form-group" type="text" name="phone" placeholder="Long description" value="${phone}">
        <input class="form-control form-group" type="text" name="email" placeholder="Phone" value="${email}">
        <input type="submit" class="btn btn-primary" value="Add">
    </form>
</div>
<br>
<br>
<script>
    $('.selectpicker').val((${grp.id}==null)?'-1':'${grp.id}');
    $('.selectpicker').selectpicker('render');
</script>
