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
                                    <a href="#">Выделенный урок учениика</a>
                                </li>
                            </ul>

                            <h1 id="main-heading">
                                Выделенный урок учениика <span>Выделенный урок учениика</span>
                            </h1>
                        </div>

                        <div id="main-content">
                            <div class="widget span6">
                                <div class="widget-header">
                                    <span class="title">Все ученики</span>
                                    <div class="toolbar">
                                        <div class="btn-group">
                                            <span class="btn">Продажа курсов</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <table>
                                        <tr>
                                            <td>Уровень</td>
                                            <td>
                                                <select style="margin-left: 30px;">
                                                    <option selected="">Все уровни</option>
                                                    <option>Beginer</option>
                                                    <option>Elementary</option>
                                                    <option>Pre-Intermediate</option>
                                                    <option>Intermediate</option>
                                                    <option>Upper-Intermediate</option>
                                                    <option>Advance</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Курс</td>
                                            <td>
                                                <select style="margin-left: 30px;">
                                                    <option selected="" value="New English File ">New English File </option>
                                                    <option value="New Headway">New Headway</option>
                                                    <option value="In company">In company</option>
                                                    <option value="Straightforward">Straightforward</option>
                                                    <option value="Face2face">Face2face</option>
                                                    <option value="Language Leader">Language Leader</option>>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Группы</td>
                                            <td>

                                                <select style="margin-left: 30px;">
                                                    <option selected="" value="Маяковка Январь - Март 2014  New English File Pre-intermediate ">Маяковка Январь - Март 2014&nbsp; New English File Pre-intermediate </option>
                                                    <option value="Маяковка Февраль - Апрель 2014  Market Leader Elementary ">Маяковка Февраль - Апрель 2014&nbsp; Market Leader Elementary </option>
                                                    <option value="Маяковка Январь - Март 2014  New English File Intermediate ">Маяковка Январь - Март 2014&nbsp; New English File Intermediate </option>
                                                    <option value="Маяковка Февраль - Апрель  Market Leader Pre-intermediate ">Маяковка Февраль - Апрель&nbsp; Market Leader Pre-intermediate </option>
                                                    <option value="Таганка Январь - Март 2014  Market Leader Pre-Elementary">Таганка Январь - Март 2014&nbsp; Market Leader Pre-Elementary</option>
                                                    <option value="Таганка Февраль - Апрель New English File Pre-Elementary">Таганка Февраль - Апрель New English File Pre-Elementary</option>
                                                    <option value="Таганка Апрель - Май 2014  New English File Pre-intermediate ">Таганка Апрель - Май 2014&nbsp; New English File Pre-intermediate </option>
                                                    <option value="Таганка Январь - Март 2014  Market Leader Pre-intermediate ">Таганка Январь - Март 2014&nbsp; Market Leader Pre-intermediate </option>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                    <div>
                                        <button style="margin-left: 30px; margin-top: 10px;" class="btn btn-info">Показать неактивных</button>
                                    </div>

                                        <div class="user-info">
                                    <div class="user-logo">
                                        <img src="/img/avatar.png"></img>
                                    </div>
                                    <div class="user-info-text">
                                        <div>Прокольева Екатерина</div>
                                        <div>20 лет</div>
                                    </div>
                                    <div class="user-info-text">
                                        <div>Market Leader</div>
                                        <div>Elementary</div>
                                    </div>
                                    <div class="user-info-send">
                                        <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                    </div>

                                </div>

                                    <div class="user-info">
                                        <div class="user-logo">
                                            <img src="/img/avatar.png"></img>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Прокольева Екатерина</div>
                                            <div>20 лет</div>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Market Leader</div>
                                            <div>Elementary</div>
                                        </div>
                                        <div class="user-info-send">
                                            <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                        </div>

                                    </div>

                                    <div class="user-info">
                                        <div class="user-logo">
                                            <img src="/img/avatar.png"></img>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Прокольева Екатерина</div>
                                            <div>20 лет</div>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Market Leader</div>
                                            <div>Elementary</div>
                                        </div>
                                        <div class="user-info-send">
                                            <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                        </div>

                                    </div>

                                    <div class="user-info">
                                        <div class="user-logo">
                                            <img src="/img/avatar.png"></img>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Прокольева Екатерина</div>
                                            <div>20 лет</div>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Market Leader</div>
                                            <div>Elementary</div>
                                        </div>
                                        <div class="user-info-send">
                                            <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                        </div>

                                    </div>

                                    <div class="user-info">
                                        <div class="user-logo">
                                            <img src="/img/avatar.png"></img>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Прокольева Екатерина</div>
                                            <div>20 лет</div>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Market Leader</div>
                                            <div>Elementary</div>
                                        </div>
                                        <div class="user-info-send">
                                            <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                        </div>

                                    </div>

                                    <div class="user-info">
                                        <div class="user-logo">
                                            <img src="/img/avatar.png"></img>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Прокольева Екатерина</div>
                                            <div>20 лет</div>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Market Leader</div>
                                            <div>Elementary</div>
                                        </div>
                                        <div class="user-info-send">
                                            <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                        </div>

                                    </div>

                                    <div class="user-info">
                                        <div class="user-logo">
                                            <img src="/img/avatar.png"></img>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Прокольева Екатерина</div>
                                            <div>20 лет</div>
                                        </div>
                                        <div class="user-info-text">
                                            <div>Market Leader</div>
                                            <div>Elementary</div>
                                        </div>
                                        <div class="user-info-send">
                                            <button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button>
                                        </div>

                                    </div>

                                </div>
                            </div>
                            <div class="widget span4">
                                <div class="widget-header">
                                    <span class="title">Урок курса</span>
                                    <div class="toolbar">
                                        <div class="btn-group">
                                            <span class="btn">Решение урока</span>
                                            <span class="btn">Вернуться к курсу</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content">
                                    <div>
                                        <span>Урок 4</span><span style="float: right;">5 файлов</span>
                                    </div>
                                    <div>
                                        <button style="margin-top: 7px;" class="btn btn-info">Добавить персональный материал для ученика</button>
                                    </div>
                                    <div>
                                        <button style="margin-top: 7px;" class="btn btn-info">Добавить материал к курсу</button>
                                    </div>
                                </div>

                            </div>
                            <div class="widget span4">
                                <div class="widget-header">
                                    <span class="title">Основной материал урока из курса</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <div class="folder file active-file">Материалы к разговорному курсу<span style="float: right; margin-right: 30px;"><img src="/img/close.png"></img></span></div>
                                    <div class="audio file">Материалы к разговорному курсу</div>
                                    <div class="video file">Материалы к разговорному курсу</div>
                                    <div class="text file">Материалы к разговорному курсу</div>
                                    <div class="psd file">Материалы к разговорному курсу</div>
                                </div>
                            </div>
                            <div class="widget span4">
                                <div class="widget-header">
                                    <span class="title">Дополнительный материал для ученика</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <div class="folder file">Материалы к разговорному курсу</div>
                                    <div class="audio file">Материалы к разговорному курсу</div>
                                    <div class="video file">Материалы к разговорному курсу</div>
                                    <div class="text file">Материалы к разговорному курсу</div>
                                    <div class="psd file">Материалы к разговорному курсу</div>
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


