<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
    <h3><a href="/wiki">Contacts List</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_contact" class="btn btn-default navbar-btn">Add Contact</button>
                    </li>
                    <li>
                        <button type="button" id="add_group" class="btn btn-default navbar-btn">Add Group</button>
                    </li>
                    <li>
                        <button type="button" id="delete_contact" class="btn btn-default navbar-btn">Delete Contact
                        </button>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Groups <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/wiki">Default</a></li>
                            <c:forEach items="${groups}" var="group">
                                <li><a href="/group?group=${group.id}">${group.name}</a></li>
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

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Name</b></td>
            <td><b>Surname</b></td>
            <td><b>Phone</b></td>
            <td><b>E-mail</b></td>
            <td><b>Group</b></td>
        </tr>
        </thead>
        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${contact.id}" id="checkbox_${contact.id}"/></td>
                <td>${contact.name}</td>
                <td>${contact.surname}</td>
                <td>${contact.phone}</td>
                <td>${contact.email}</td>
                <c:choose>
                    <c:when test="${contact.group ne null}">
                        <td>${contact.group.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td>Default</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
    $('.dropdown-toggle').dropdown();

    $('#add_contact').click(function () {
        window.location.href = '/contact_add_page';
    });

    $('#add_group').click(function () {
        window.location.href = '/group_add_page';
    });

    $('#delete_contact').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/contact/delete", data, function (data, status) {
            window.location.reload();
        });
    });
</script>
