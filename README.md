# Сайт компании-разработчика

<h2>Технический справочник</h2> <br>
<a href="http://1c-centre.com/"><img style="max-width:100%;" alt="WebSite" src="https://github.com/skpavlenko/WS/blob/master/screenshots/Title.jpg"></a>
<br><br>
Идея сайта была взята у <a href="https://www.mkyong.com/">https://www.mkyong.com/</a>. Данное приложение, предназначенное для хранения технической информации в виде каталогизированных заметок, с поиском и связанными с ними файлами библиотек и обработок.
Имеется возможность запуска артефакта как в режиме Embedded Web Server Deployment, так и Java EE Application Server.
<a href="http://1c-centre.com/wiki"><img style="max-width:100%;" alt="Wiki's" src="https://github.com/skpavlenko/WS/blob/master/screenshots/wikislist.jpg"></a>
<br> 
Заметки создаются в режиме администрирования, содержат информацию о дате внесения, пользователе, а так же ссылку на первоисточник. 
Заметка может быть отображена в виде простого текста:
<img style="max-width:100%;" alt="Partners" src="https://github.com/skpavlenko/WS/blob/master/screenshots/PlainText.jpg">
Либо, при необходимости, с графической информацией, с помощью PDFObject v2.0.201604172 from Mozilla:
<img style="max-width:100%;" alt="Partners" src="https://github.com/skpavlenko/WS/blob/master/screenshots/PDFViewing.jpg">

Заметки могут иметь присоединенные файлы, которыми можно легко управлять в режиме администрирования, либо только загружать из режима гостя. Интерфейс панели управления связанными файлами:
<img style="max-width:100%;" alt="Partners" src="https://github.com/skpavlenko/WS/blob/master/screenshots/ManageDocuments.jpg">

Так же имеется рекламный блок - на странице Партнеры отображается адаптивная сетка 
masonry с компаниями, с которыми мы сотрудничаем.<br> 

<a href="http://1c-centre.com/partners"><img style="max-width:100%;" alt="WebSite" src="https://github.com/skpavlenko/WS/blob/master/screenshots/Partners.jpg"></a>
<br> 

При введении логина/пароля администратора (admin/admin) пользователь получает возможность администрирования сетки, 
где можно добавлять, удалять выделенные элементы (флажок в правом нижнем углу записей сетки): <br> 
<img style="max-width:100%;" alt="Partners" src="https://github.com/skpavlenko/WS/blob/master/screenshots/Partners_edit.jpg"><br> <br>
Есть возможность определять порядок следования элементов, с помощью поля Порядок. <br>

Для выхода из режима администрирования нажать <h4>О нас – Выход</h4><br>

Изображения логотипов, связанные файлы и PDF-отображение заметок сохраняются в базе данных MySQL, в полях с типом BLOB.<br>

<h4>Сайт работает на VPS с Debian Linux 2.6.32-37-pve + Spring Boot 1.3.5 и MySQL 5.6<br><br>
Following technologies being used:<br>
- Spring Boot 1.3.5<br>
- Spring MVC 4.1.0<br>
- Spring Security 4.1.0<br>
- Apache Tiles 3.0.7<br>
- JPA/Hibernate 4.3.10.Final<br>
- MySQL Server 5.6<br>
- validation-api 1.1.0.Final<br>
- hibernate-validator 5.1.3.Final<br>
- Bootstrap v3.3.2<br>
- Maven 3<br>
- JDK 1.8<br>
- Tomcat 8.0.33<br>
- Salvattore 1.0.9<br>
- PDFObject v2.0.201604172 from Mozilla<br>
- DataTables Bootstrap 3 integreation<br>
- Java EE<br>
- JSP/JSTL<br>
- CSS, JavaScript, jQuery.<br>
</h4>
