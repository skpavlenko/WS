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
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action=${(id eq null)?"/wiki/add":"/wiki/edit"} method="post">
    <h3>New article</h3>
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
        <input class="form-control form-group" type="date" name="date" placeholder="Date" value="${date}">
        <input class="form-control-file form-group" type="file" name="pdf" placeholder="PDF">
        <input type="submit" class="btn btn-primary" value="Add">
    </form>
    <c:if test = "${id ne null}">
        <br>
        <br>
        <div id="pdf_view"></div>
        <br>
        <br>
    </c:if>
    <c:if test = "${id ne null}">
        <br>
        <span class="well pull-left">
            <a href="<c:url value='/add-document-${id}' />">Click here to upload/manage your documents</a>
        </span>
    </c:if>
</div>
<br>
<br>
<script>
    $('.selectpicker').val((${grp.id} == null) ? '-1' : '${grp.id}'
    )
    ;
    $('.selectpicker').selectpicker('render');
</script>
<c:if test = "${id ne null}">
    <script type="text/javascript">
    $(document).ready(function(){
    PDFObject.embed("/pdf/${id}", "#pdf_view");
    });
    </script>
</c:if>