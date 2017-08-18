<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/dataTables.bootstrap.min.css' />"></link>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery.dataTables.min.js' />">
</script>

<div class="errorPage">
    <span class="oops">Oops!</span><br/>
    <img src="resources/images/MissingPage.png"></img>
    <p>There seems to be a problem with the page you requested
    <p>Details: ${message}</p>
    <br>
</div>
