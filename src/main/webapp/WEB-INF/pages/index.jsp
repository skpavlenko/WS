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
    <meta name="description" content="1С Центр. Внедрение Управление производственным предприятием 8<br>Бюджетирование, бухгалтерский и управленческий учет">
    <meta name="Keywords" content="1С, УПП, Павленко Сергей, Pavlenko Sergey, Коржов Михаил, Spring, Spring Security, Java, Hibernate, бухгалтерский учет, управленческий учет">

</head>

<body>
<%@ include file="/resources/pages/navbar.jsp" %>
    <div id="carousel" class="carousel slide" data-ride="carousel">
        <!--Индикаторы слайдов-->
        <ol class="carousel-indicators hidden-xs">
            <li class="active" data-target="#carousel" data-slide-to="0"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
            <li data-target="#carousel" data-slide-to="3"></li>
            <li data-target="#carousel" data-slide-to="4"></li>
            <li data-target="#carousel" data-slide-to="5"></li>
            <li data-target="#carousel" data-slide-to="6"></li>
            <li data-target="#carousel" data-slide-to="7"></li>
            <li data-target="#carousel" data-slide-to="8"></li>
            <li data-target="#carousel" data-slide-to="9"></li>
            <li data-target="#carousel" data-slide-to="10"></li>
            <li data-target="#carousel" data-slide-to="11"></li>
            <li data-target="#carousel" data-slide-to="12"></li>
        </ol>
        <!--Слайды        -->
        <div class="carousel-inner" role="listbox" style="font-family: MagistralC, sans-serif">
            <div class="item active"><img src="resources/images/slide-1.jpg" class="center-block" alt="">
                <div class="carousel-caption">
                    <h3 class="hidden-xs">Регулярное сопровождение включает комплекс услуг и сервисов по сопровождению
                        систем на базе 1С от компании 1С ЦЕНТР.</h3>
                    <h4 class="hidden-xs">от 150 грн/час</h4>
                    <p class="hidden-sm hidden-md hidden-lg">Сопровождение 1С от 150 грн/час</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-2.png" class="center-block" alt="">
                <div id="bowl" class="carousel-caption">
                    <h3 class="hidden-xs">Специализируемся на внедрении и поддержке</h3>
                    <h4 class="hidden-xs">1С Управление производственным предприятием 8</h4>
                    <p class="hidden-sm hidden-md hidden-lg">1С Управление производственным предприятием 8 (УПП)</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-3.png" class="center-block" alt="">
                <div id="bow" class="carousel-caption">
                    <h3 class="hidden-xs">«1С:ERP Управление предприятием 2» – инновационное решение для построения
                        комплексных информационных систем управления деятельностью многопрофильных предприятий с учетом
                        лучших мировых и отечественных практик автоматизации крупного и среднего бизнеса.</h3>
                    <p class="hidden-sm hidden-md hidden-lg">«1С:ERP Управление предприятием 2»</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-4.jpg" class="center-block" alt="">
                <div id="wobl" class="carousel-caption">
                    <h3 class="hidden-xs">Обучение работе в типовых программных продуктах Фирмы "1С"</h3>
                    <h4 class="hidden-xs">от 150 грн/час</h4>
                    <p class="hidden-sm hidden-md hidden-lg">Обучение 1С</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-5.jpg" class="center-block" alt="">
                <div class="carousel-caption">
                    <h3 class="hidden-xs">Постановка бюджетирования и управленческого учета</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Постановка бюджетирования</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-6.jpg" class="center-block" alt="">
                <div id="bow" class="carousel-caption">
                    <h3 class="hidden-xs">Комплексные бухгалтерские услуги - профессиональное обслуживание и дальнейшее
                        сопровождение фирм</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Комплексные бухгалтерские услуги</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-7.jpg" class="center-block" alt="">
                <div id="bow" class="carousel-caption">
                    <h3 class="hidden-xs">Консультирование в сфере бухгалтерского, налогового учета и трудового
                        законодательства</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Консультирование</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-8.jpg" class="center-block" alt="">
                <div class="carousel-caption">
                    <h3 class="hidden-xs">Ведение бухгалтерского учета предприятия на постоянной основе</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Ведение бухгалтерского учета</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-9.jpg" class="center-block" alt="">
                <div id="wobl" class="carousel-caption">
                    <h3 class="hidden-xs">Администрирование и настройка серверов </h3>
                    <p class="hidden-sm hidden-md hidden-lg">Администрирование серверов</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-10.jpg" class="center-block" alt="">
                <div id="wobl" class="carousel-caption">
                    <h3 class="hidden-xs">FTP хостинг - удобен тем, что его удобно использовать для обмена данными со
                        своими коллегами или для хранения бекапов важных данных.</h3>
                    <p class="hidden-sm hidden-md hidden-lg">FTP хостинг</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-11.jpg" class="center-block" alt="">
                <div class="carousel-caption">
                    <h3 class="hidden-xs">В случае необходимости иметь выделенный сервер можем предоставить отдельный
                        аппаратный сервер с различными параметрами. Имеется как серверное так и десктопное железо
                        различных конфигураций.</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Выделенные сервера</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-12.jpg" class="center-block" alt="">
                <div id="bowr" class="carousel-caption">
                    <h3 class="hidden-xs">Среди наших работ есть и  сервера приложений на различных операционных
                        системах (как Windows так и Linux), терминальные сервера (аппаратные и виртуальные), сервера
                        VoIP телефонии и сервера\маршрутизаторы VPN\proxy.</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Сервера приложений</p>
                </div>
            </div>
            <div class="item"><img src="resources/images/slide-13.jpg" class="center-block" alt="">
                <div class="carousel-caption">
                    <h3 class="hidden-xs">Так же проводим комплектное администрирование полностью всего компьютерного
                        парка на предприятии.</h3>
                    <p class="hidden-sm hidden-md hidden-lg">Администрирование компьютерного парка</p>
                </div>
            </div>
        </div>
        <!--Стрелки переключения слайдов-->
        <a href="#carousel" class="left carousel-control" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span> </a>
        <a href="#carousel" class="right carousel-control" data-slide="next"> <span class="glyphicon glyphicon-chevron-right"></span> </a>
    </div>
    <br>
    <br>
<%@ include file="/resources/pages/blog.jsp" %>
<br>
    <br>
<%@ include file="/resources/pages/footer.jsp" %>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="resources/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.js"></script>
</body>

</html>