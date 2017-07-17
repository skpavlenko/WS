<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<div id="headerwrap">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <c:if test="${param.error ne null}">
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Неверный логин или пароль!</strong> Повторите попытку еще раз.
                    </div>
                </c:if>
                <c:if test="${param.logout ne null}">
                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>Chao!</strong></div>
                </c:if>
                <h1 style="font-family: MagistralC, sans-serif">1С ЦЕНТР</h1>
                <h3 style="font-family: MagistralC, sans-serif; color:#FFFFFF">Специализируемся на внедрении</h3>
                <h3 style="font-family: MagistralC, sans-serif; color:#FFFFFF">1С:Управление производственным
                    предприятием 8</h3>
                <div class="spacer"></div>
                <i class="fa fa-angle-down"></i></div>
        </div>
        <!-- row -->
    </div>
    <!-- /container -->
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
