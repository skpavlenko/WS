<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/dataTables.bootstrap.min.css' />"></link>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery.dataTables.min.js' />">
</script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/dataTables.bootstrap.min.js' />">
</script>
<script type="text/javascript" class="init">
    $(document).ready(function () {
        $('#example').DataTable({
            language: {
                "processing": "Подождите...",
                "search": "Поиск:",
                "lengthMenu": "Показать _MENU_ записей",
                "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
                "infoEmpty": "Записи с 0 до 0 из 0 записей",
                "infoFiltered": "(отфильтровано из _MAX_ записей)",
                "infoPostFix": "",
                "loadingRecords": "Загрузка записей...",
                "zeroRecords": "Записи отсутствуют.",
                "emptyTable": "В таблице отсутствуют данные",
                "paginate": {
                    "first": "Первая",
                    "previous": "Предыдущая",
                    "next": "Следующая",
                    "last": "Последняя"
                },
                "aria": {
                    "sortAscending": ": активировать для сортировки столбца по возрастанию",
                    "sortDescending": ": активировать для сортировки столбца по убыванию"
                }
            }

        });
    });
</script>

<div class="container">
    <h3><a href="/wiki">Wiki's List</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_wiki" class="btn btn-default navbar-btn">Add Wiki</button>
                    </li>
                    <li>
                        <button type="button" id="add_group" class="btn btn-default navbar-btn">Add Group</button>
                    </li>
                    <li>
                        <button type="button" id="delete_wiki" class="btn btn-default navbar-btn">Delete Wiki
                        </button>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Groups <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/wiki">Default</a></li>
                            <c:forEach items="${groups}" var="group">
                                <%--<li><a href="/group?group=${group.id}">${group.name}</a></li>--%>
                                <li><a href="/group/${group.id}">${group.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
                <form id="search" class="navbar-form navbar-left" role="search" action="/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table id="example" class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Name</b></td>
            <td><b>Description</b></td>
            <td><b>URL</b></td>
            <%--<td><b>Date</b></td>--%>
            <td><b>Group</b></td>
        </tr>
        </thead>
        <c:forEach items="${wikis}" var="wiki">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${wiki.id}" id="checkbox_${wiki.id}"/></td>
                <td><a href=/wiki_edit_page?id=${wiki.id}>${wiki.name}</a></td>
                <td>${wiki.description}</td>
                <td>${wiki.url}</td>
                <%--<td>${wiki.date}</td>--%>
                <c:choose>
                    <c:when test="${wiki.group ne null}">
                        <td>${wiki.group.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td>Default</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<br>
<script>
    $('.dropdown-toggle').dropdown();

    $('#add_wiki').click(function () {
        window.location.href = '/wiki_add_page';
    });

    $('#add_group').click(function () {
        window.location.href = '/group_add_page';
    });

    $('#delete_wiki').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/wiki/delete", data, function (data, status) {
            window.location.reload();
        });
    });
</script>
