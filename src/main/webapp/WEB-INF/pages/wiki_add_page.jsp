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
    <c:if test = "${id eq null}">
        <h3>New article</h3>
    </c:if>
    <c:if test = "${id ne null}">
        <h3>${name}</h3>
    </c:if>
    <div id="exTab2" class="container">
        <ul class="nav nav-tabs">
            <li class="active">
                <a  href="#1" data-toggle="tab">Text</a>
            </li>
            <li><a href="#2" data-toggle="tab">PDF</a>
            </li>
        </ul>

        <div class="tab-content ">
            <div class="tab-pane active" id="1">
                <form id="text_view" role="form" enctype="multipart/form-data" class="form-horizontal" action=${(id eq null)?"/wiki/add":"/wiki/edit"} method="post">
                    <input class="form-control form-group" type="text" name="id" placeholder="id" value="${id}" readonly>
                    <select class="selectpicker form-control form-group" name="group">
                        <option value="-1">Default</option>
                        <c:forEach items="${groups}" var="group">
                            <option value="${group.id}">${group.name}</option>
                        </c:forEach>
                    </select>
                    <input class="form-control form-group" type="text" name="name" placeholder="Name" value="${name}">
                    <textarea class="form-control form-group" rows="30" name="description">${description}</textarea>
                    <input class="form-control form-group" type="text" name="url" placeholder="URL" value="${url}">
                    <input class="form-control form-group" type="date" name="date" placeholder="Date" value="${date}">
                    <c:if test = "${id ne null}">
                        <input class="form-control form-group" type="text" name="customUser" placeholder="CustomUser" value="${customUser}" readonly>
                    </c:if>
                    <security:authorize access="hasAnyRole('ADMIN')">
                        <div class="container">
                            <p>PDF display mode</p>
                            <input class="form-control-file form-group" type="file" name="pdf" placeholder="PDF">
                        </div>

                        <input type="submit" class="btn btn-primary" value="Add">
                    </security:authorize>
                </form>
            </div>
            <div class="tab-pane" id="2">
                <c:if test = "${id ne null}">
                    <div id="pdf_view"></div>
                    <br>
                    <button class="btn btn-default btn-xs" onclick="enterFullscreen('pdf_view')">Toggle frame content fullscreen</button>
                    <br>
                </c:if>
            </div>
        </div>
    </div>
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
<script>
    document.cancelFullScreen = document.cancelFullScreen || document.webkitCancelFullScreen ||      document.mozCancelFullScreen;
    function onFullScreenEnter() {
        console.log("Enter fullscreen initiated from iframe");
    };
    function onFullScreenExit() {
        console.log("Exit fullscreen initiated from iframe");
    };
    // Note: FF nightly needs about:config full-screen-api.enabled set to true.
    function enterFullscreen(id) {
        onFullScreenEnter(id);
        var el =  document.getElementById(id);
        var onfullscreenchange =  function(e){
            var fullscreenElement = document.fullscreenElement || document.mozFullscreenElement || document.webkitFullscreenElement;
            var fullscreenEnabled = document.fullscreenEnabled || document.mozFullscreenEnabled || document.webkitFullscreenEnabled;
            console.log( 'fullscreenEnabled = ' + fullscreenEnabled, ',  fullscreenElement = ', fullscreenElement, ',  e = ', e);
        }
        el.addEventListener("webkitfullscreenchange", onfullscreenchange);
        el.addEventListener("mozfullscreenchange",     onfullscreenchange);
        el.addEventListener("fullscreenchange",             onfullscreenchange);
        if (el.webkitRequestFullScreen) {
            el.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
        } else {
            el.mozRequestFullScreen();
        }
        document.querySelector('#'+id + ' button').onclick = function(){
            exitFullscreen(id);
        }
    }
    function exitFullscreen(id) {
        onFullScreenExit(id);
        document.cancelFullScreen();
        document.querySelector('#'+id + ' button').onclick = function(){
            enterFullscreen(id);
        }
    }
</script>