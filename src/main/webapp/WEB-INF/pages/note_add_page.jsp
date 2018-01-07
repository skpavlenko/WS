<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8" %>

<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script src="<c:url value='/resources/js/pdfobject.min.js' />"></script>
<style>
    .pdfobject-container { height: 500px;}
    .pdfobject { border: 1px solid #666; }
</style>

<div class="container">
    <c:if test = "${id eq null}">
        <h3>New note</h3>
    </c:if>
    <c:if test = "${id ne null}">
        <h3>${name}</h3>
    </c:if>
    <div id="exTab2" class="container">
        <form role="form" class="form-horizontal" action=${(id eq null)?"/note/add":"/note/edit"} method="post">
            <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
            <select class="selectpicker form-control form-group" name="group">
                <option value="-1">Default</option>
                <c:forEach items="${groups}" var="group">
                    <option value="${group.id}">${group.name}</option>
                </c:forEach>
            </select>
            <input class="form-control form-group" type="text" name="name" placeholder="Name" value="${name}" required>
            <textarea class="form-control form-group" rows="30" name="description">${description}</textarea>
            <input class="form-control form-group" type="text" name="url" placeholder="URL" value="${url}">
            <input class="form-control form-group" type="date" name="date" placeholder="Date" value="${date}">
            <c:if test="${id ne null}">
                <input class="form-control form-group" type="text" name="customUser" placeholder="CustomUser"
                       value="${customUser}" readonly>
            </c:if>
            <input type="submit" class="btn btn-primary" value="Add">
        </form>
    </div>
</div>
<br>
<br>
<script>
    $('.selectpicker').val((${grp.id} == null) ? '-1' : '${grp.id}'
    )
    ;
    $('.selectpicker').selectpicker('render');
</script>
