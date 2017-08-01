<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <input class="form-control form-group" type="date" name="date" placeholder="Date" value="${date}">
    <input type="submit" class="btn btn-primary" value="Add">
    </form>
    <br>
    <br>
    <div class="generic-container">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Documents </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>No.</th>
                        <th>File Name</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${documents}" var="doc" varStatus="counter">
                        <tr>
                            <td>${counter.index + 1}</td>
                            <td>${doc.name}</td>
                            <td>${doc.type}</td>
                            <td>${doc.description}</td>
                            <td><a href="<c:url value='/download-document-${user.id}-${doc.id}' />" class="btn btn-success custom-width">download</a></td>
                            <td><a href="<c:url value='/delete-document-${user.id}-${doc.id}' />" class="btn btn-danger custom-width">delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel panel-default">

            <div class="panel-heading"><span class="lead">Upload New Document</span></div>
            <div class="uploadcontainer">
                <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="file">Upload a document</label>
                            <div class="col-md-7">
                                <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="file" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="file">Description</label>
                            <div class="col-md-7">
                                <form:input type="text" path="description" id="description" class="form-control input-sm"/>
                            </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="Upload" class="btn btn-primary btn-sm">
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
        <div class="well">
            Go to <a href="<c:url value='/list' />">Users List</a>
        </div>
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
