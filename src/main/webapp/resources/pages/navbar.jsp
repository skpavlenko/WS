<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>
<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu"><span
                    class="sr-only">Открыть навигацию</span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="login"></a>
        </div>
        <div class="collapse navbar-collapse" id="responsive-menu">
            <ul class="nav navbar-nav" style="font-family: MagistralC, sans-serif">
                <li><a href="index">Услуги</a></li>
                <li><a href="partners">Партнеры</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Сервис<b
                        class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="wiki">Вики</a></li>
                        <li><a href="#">Зарегистрироваться</a></li>
                        <li><a href="#">Задать вопрос</a></li>
                        <security:authorize access="hasAnyRole('ADMIN')">
                            <li class="divider"></li>
                            <li><a href="/logout">Выход</a></li>
                        </security:authorize>
                    </ul>
                </li>
                <li><a href="#cf">Контакты</a></li>
            </ul>
            <c:url value="/j_spring_security_check" var="loginUrl"/>
            <form action="${loginUrl}" method="POST" class="navbar-form navbar-right"
                  style="font-family: MagistralC, sans-serif">
                <div id="login" class="form-group">
                    <input type="text" name="j_login" class="form-control" placeholder="E-mail" value="">
                    <input type="password" name="j_password" class="form-control" placeholder="Пароль" value="">
                    <input id="subm" type="submit" class="btn btn-primary" value="Войти"/></div>
            </form>
        </div>
    </div>
</div>

