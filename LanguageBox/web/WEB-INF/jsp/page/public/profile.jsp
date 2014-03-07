<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--><html lang="en"><!--<![endif]-->

<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Stylesheet -->
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css" media="all">

    <!-- jquery-ui Stylesheets -->
    <link rel="stylesheet" href="/assets/jui/css/jquery-ui.css" media="screen">
    <link rel="stylesheet" href="/assets/jui/jquery-ui.custom.css" media="screen">
    <link rel="stylesheet" href="/assets/jui/timepicker/jquery-ui-timepicker.css" media="screen">

    <!-- Uniform Stylesheet -->
    <link rel="stylesheet" href="/plugins/uniform/css/uniform.default.css" media="screen">

    <!-- Rating Plugin -->
    <link rel="stylesheet" href="/plugins/rating/jquery.rating.css" media="screen">

    <!-- Main Layout Stylesheet -->
    <link rel="stylesheet" href="/assets/css/fonts/icomoon/style.css" media="screen">
    <link rel="stylesheet" href="/assets/css/main-style.css" media="screen">

    <link rel="stylesheet" href="/custom_css/custom.css" media="screen">
    <link rel="stylesheet" href="/css/lex.css" media="screen">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <title>LanguageBox - Профиль</title>

</head>

<body data-show-sidebar-toggle-button="false" data-fixed-sidebar="true">
<div id="dialog-message" class="hide" title="Внимание">
    <p>Уверены?</p>
</div>
<div id="dialog-message-delete-course" class="hide" title="Внимание">
    <p>Уверены?</p>
</div>


<div id="wrapper">
    <%@include file="/WEB-INF/jsp/section/header.jsp" %>

    <div id="content-wrap">
        <div id="content">
            <div id="content-outer">
                <div id="content-inner">
                    <jsp:include page="/WEB-INF/jsp/section/aside_menu.jsp" >
                        <jsp:param name="page" value="courses" />
                    </jsp:include>

                    <!-- <div id="sidebar-separator"></div> -->

                    <section id="main" class="clearfix">
                        <div id="main-header" class="page-header">
                            <ul class="breadcrumb">
                                <li>
                                    <i class="icon-home"></i>LanguageBox
                                    <span class="divider">&raquo;</span>
                                </li>
                                <li>
                                    <a href="#">Редактирование пользователя</a>
                                </li>
                            </ul>

                            <h1 id="main-heading">
                                Редактирование пользователя <span>Редактируйте свои пользовательские данные</span>
                            </h1>
                        </div>

                        <div id="main-content">
                            <div class="widget span3">
                                <div class="widget-header">
                                    <span class="title">Фотография</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <div class="profile-image">
                                        <img src="/img/teacher/rep.png">
                                    </div>
                                    <div class="profile-send-message">
                                        <button class="btn btn-info">Загрузить фотографию</button>
                                    </div>
                                    <div class="profile-send-message">
                                        <button class="btn btn-info">Удалить фотографию</button>
                                    </div>
                                </div>
                            </div>
                            <div class="widget span6">
                                <div class="widget-header">
                                    <span class="title">Иванова Анна Сергеевна</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <table>
                                        <tr>
                                            <td>Имя</td>
                                            <td><input style="margin-left: 30px;" type="text" placeholder="Имя"></td>
                                        </tr>
                                        <tr>
                                            <td>Фамилия</td>
                                            <td><input style="margin-left: 30px;" type="text" placeholder="Фамилия"></td>
                                        </tr>
                                        <tr>
                                            <td>Отчество</td>
                                            <td><input style="margin-left: 30px;" type="text" placeholder="Отчество"></td>
                                        </tr>
                                        <tr>
                                            <td>Дата рождения</td>
                                            <td><input style="margin-left: 30px;" type="date"></td>
                                        </tr>
                                        <tr>
                                            <td>Пол</td>
                                            <td>
                                                <select style="margin-left: 30px;">
                                                    <option>Мужчина</option>
                                                    <option>Женщина</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td><input style="margin-left: 30px;" type="email"></td>
                                        </tr>
                                        <tr>
                                            <td>Телефон</td>
                                            <td><input style="margin-left: 30px;" type="tel"></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><button style="margin-left: 30px;" class="btn btn-info">Сохранить</button>
                                                <button style="margin-left: 30px;" class="btn btn-info">Сменить пароль</button>
                                            </td>
                                        </tr>
                                    </table>
                                    <div>
                                        <img src="/img/social/social_1.png"></img>
                                        <img src="/img/social/social_2.png"></img>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>

    <%@include file="/WEB-INF/jsp/section/footer.jsp" %>

</div>

<!-- Core Scripts -->
<script src="/assets/js/libs/jquery-1.8.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/js/libs/jquery.placeholder.min.js"></script>
<script src="/assets/js/libs/jquery.mousewheel.min.js"></script>

<!-- Template Script -->
<script src="/assets/js/template.js"></script>
<script src="/assets/js/setup.js"></script>

<!-- Customizer, remove if not needed -->
<script src="/assets/js/customizer.js"></script>

<!-- Uniform Script -->
<script src="/plugins/uniform/jquery.uniform.min.js"></script>

<!-- jquery-ui Scripts -->
<script src="/assets/jui/js/jquery-ui-1.9.2.min.js"></script>
<script src="/assets/jui/jquery-ui.custom.min.js"></script>
<script src="/assets/jui/timepicker/jquery-ui-timepicker.min.js"></script>
<script src="/assets/jui/jquery.ui.touch-punch.min.js"></script>

<!-- Plugin Scripts -->

<!-- Rating  -->
<script src="/plugins/rating/jquery.rating.min.js"></script>

<!-- SparkLine -->
<script src="/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Demo Scripts -->
<script src="/assets/js/demo/dashboard.js"></script>


<script src="/js/common/all.js" type="text/javascript"></script>

</body>

</html>


