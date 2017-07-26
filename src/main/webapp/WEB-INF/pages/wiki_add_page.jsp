<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>

<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action=${(id eq null)?"/wiki/add":"/wiki/edit"} method="post">
    <h3>New contact</h3>
    <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
    <select class="selectpicker form-control form-group" name="group">
        <option value="-1">Default</option>
        <c:forEach items="${groups}" var="group">
            <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select>
    <input class="form-control form-group" type="text" name="name" placeholder="Name" value="${name}">
    <input class="form-control form-group" type="text" name="description" placeholder="Description"
           value="${description}">
    <input class="form-control form-group" type="text" name="url" placeholder="URL" value="${url}">
    <%--<input class="form-control form-group" type="text" name="date" placeholder="Date" value="${date}">--%>
    <input type="submit" class="btn btn-primary" value="Add">
    </form>
</div>
<br>
<br>
<script>
    $('.selectpicker').val((${grp.id} == null) ? '-1' : '${grp.id}'
    )
    ;
    $('.selectpicker').selectpicker('render');
</script>
