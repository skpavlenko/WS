<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

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
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script src="<c:url value='/resources/js/salvattore.min.js' />"></script>

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
