<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
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
						<security:authorize access="hasAnyRole('ADMIN')">
							<td><a href="<c:url value='/delete-document-${user.id}-${doc.id}' />"
								   class="btn btn-danger custom-width">delete</a></td>
						</security:authorize>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<security:authorize access="hasAnyRole('ADMIN')">
		<div class="panel panel-default">

			<div class="panel-heading"><span class="lead">Upload New Document</span></div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
						   class="form-horizontal">

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
								<form:input type="text" path="description" id="description"
											class="form-control input-sm"/>
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
	</security:authorize>
	<div class="well">
		Go to <a href="<c:url value='/wiki' />">Wiki's List</a>
	</div>
</div>
