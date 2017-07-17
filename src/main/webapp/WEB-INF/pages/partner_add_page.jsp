<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/partner/add" method="post">
        <h3>Партнер</h3>
        <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
        <label class="control-label" for="ord">Порядок (только цифры)<span class="required">*</span></label>
        <input class="form-control form-group" type="text" id="ord" name="ord" placeholder="Порядок (только цифры)"
               value="${ord}" required pattern="[0-9]{1,3}">
        <label class="control-label" for="name">Имя<span class="required">*</span></label>
        <input class="form-control form-group" type="text" id="name" name="name" placeholder="Имя" value="${name}">
        <label class="control-label" for="topic">Описание<span class="required">*</span></label>
        <textarea rows="4" cols="45" class="form-control form-group" id="topic" name="topic"
                  placeholder="Описание">${topic}</textarea>
        <label class="control-label" for="url">Ссылка<span class="required">*</span></label>
        <input class="form-control form-group" type="text" id="url" name="url" placeholder="Ссылка" value="${url}">
        <label class="control-label" for="logo">Логотип<span class="required">*</span></label>
        <img id="logo" src="/photo/${id}" width="300" height="170" alt="" class="img-responsive">
        <input class="form-control-file form-group" type="file" name="logo" placeholder="Логотип">
        <input type="submit" class="btn btn-primary" value="Записать">
    </form>
</div>
<br>
<br>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script src="<c:url value='/resources/js/salvattore.min.js' />"></script>
