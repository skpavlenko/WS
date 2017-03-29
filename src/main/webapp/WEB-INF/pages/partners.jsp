<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="shortcut icon" href="resources/images/icon.png">
    <title>1С ЦЕНТР</title>
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
    <meta name="description"
          content="1С Центр. Внедрение Управление производственным предприятием 8<br>Бюджетирование, бухгалтерский и управленческий учет">
    <meta name="Keywords" content="1С, УПП, Павленко Сергей, Pavlenko Sergey, Коржов Михаил, бухгалтерия">
</head>

<body>
<%@ include file="/resources/pages/navbar.jsp" %>

<security:authorize access="hasAnyRole('ADMIN')">
    <div class="container">
        <div class="btn-toolbar">
            <div class="btn-group">
                <button type="button" id="add_partner" class="btn btn-dark"><i class="fa fa-plus-circle"></i> Добавить
                </button>
            </div>
            <div class="btn-group">
                <button id="delete_partner" type="button" class="btn btn-danger"><i class="fa fa-times-circle"></i>
                    Удалить
                </button>
            </div>
        </div>
    </div>
</security:authorize>
<br>
<div id="masgrid" class="container">
    <div class="row masonry" data-columns>
        <c:forEach items="${partners}" var="partner">
            <div class="item">
                <div class="thumbnail"><img src="/photo/${partner.id}" alt="" class="img-responsive">
                    <div id="text" class="caption">
                        <h3><a href=${partner.url}>${partner.name}</a></h3>${partner.topic} <br>
                        <div class="todelete">
                            <security:authorize access="!hasAnyRole('ADMIN')">
                                <div>
                                    <button type="button" class="btn btn-link">
                                        <a href=${partner.url}>Подробнее</a> <i
                                            class="fa fa-arrow-right"></i></button>
                                </div>
                            </security:authorize>
                            <security:authorize access="hasAnyRole('ADMIN')">
                                <div>
                                    <button type="button" class="btn btn-default edit_partner"
                                            value="${partner.id}">
                                        Редактировать <i
                                            class="fa fa-arrow-right"></i></button>
                                </div>
                                <div><input type="checkbox" name="toDelete[]" value="${partner.id}"
                                            id="checkbox_${partner.id}"/></div>
                            </security:authorize>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<br>
<br>
<%@ include file="/resources/pages/footer.jsp" %>
<script src="resources/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/salvattore.min.js"></script>
<script>

    $('#add_partner').click(function () {
        window.location.href = '/partner_add_page?id=0';
    });

    $('.edit_partner').click(function () {
        window.location.href = '/partner_add_page?id=' + $(this).val();
    });

    $('#delete_partner').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/partner/delete", data, function (data, status) {
            window.location.reload();
        });
    });
</script>

</body>

</html>