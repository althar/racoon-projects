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
                            <div class="widget span2">
                                <div class="widget-header">
                                    <span class="title">Курс</span>
                                    <div class="toolbar" style="float: none;">
                                        <div class="btn-group">
                                            <div class="btn" style="display: block; border: none; text-align: left;">Группа</div>
                                            <div class="btn" style="display: block; border: none; text-align: left;">Учитель</div>
                                            <div class="btn" style="display: block; border: none; text-align: left;">Рассписание</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <div class="books-block-left">
                                        <img src="/img/books/u61_normal.png">
                                    </div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>НАчальный тест</span><span style="float: right; margin-right: 5px;"><img src="/img/true.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №1</span><span style="float: right; margin-right: 5px;"><img src="/img/true.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №2</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №3</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №4</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №5</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №6</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №7</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №8</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №9</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Урок №10</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>
                                    <div style="padding-top: 5px; padding-bottom: 5px; border: 1px solid #d7d7d7;"><span>Финальный тест</span><span style="float: right; margin-right: 5px;"><img src="/img/quad.png"></img></span></div>

                                </div>
                            </div>
                            <div class="widget span8">
                                <div class="widget-header">
                                    <span class="title">Рейтиг курса</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <table class="maters">
                                        <tr>
                                            <td>Одногруппник</td>
                                            <td>Баллы</td>
                                            <td>Место</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr style="background-color: #e5e4e4;">
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="user-logo" style="vertical-align: middle;">
                                                    <img src="/img/avatar.png">
                                                </div>
                                                <div class="user-info-text" style="vertical-align: middle;">
                                                    <div>Прокольева Екатерина</div>
                                                </div>
                                            </td>
                                            <td style="background: url(/img/icons/star.png) left no-repeat; padding-left: 20px;">200</td>
                                            <td>1</td>
                                            <td><button style="margin-left: 30px; margin-top: 7px;" class="btn btn-info">Отправить сообщение</button></td>
                                        </tr>

                                    </table>
                                </div>
                            </div>
                            <div class="span8 widget">
                                <div class="widget-header">
                                    <span class="title">Обсуждение</span>
                                </div>
                                <div class="widget-content">
                                    <div class="review">
                                        <div class="review-author">
                <span class="review-author-img">
                    <img src="/img/courses_review/review_1.png"/>
                </span>
                                            <span class="review-author-name">Мариан Воробъева</span>
                <span class="right">
                    <span class="review-author-date">30 мая 2013</span>
                </span>
                                        </div>
                                        <div class="review-message">
                                            Слова самые распространенные,т.е. для изучения языка очень полезно,а вот для высокого уровня знания языка будет уже не достаточно.
                                        </div>
                                    </div>


                                    <div class="review">
                                        <div class="review-author">
                <span class="review-author-img">
                    <img src="/img/courses_review/review_2.png"/>
                </span>
                                            <span class="review-author-name">Калинин Николай</span>
                <span class="right">
                    <span class="review-author-date">1 июля 2013</span>
                </span>
                                        </div>
                                        <div class="review-message">
                                            Хоть в учебнике нет ни слова по-русски, в нем настолько просто и понятно изложен материал,
                                            что получаешь удовольствие при изучении грамматики и выполнении упражнений! Если приобретёте этот учебник - не пожалеете.
                                        </div>
                                    </div>


                                    <div class="review">
                                        <div class="review-author">
                <span class="review-author-img">
                    <img src="/img/courses_review/review_1.png"/>
                </span>
                                            <span class="review-author-name">Бойко Мария</span>
                <span class="right">
                    <span class="review-author-date">2 июля 2013</span>
                </span>
                                        </div>
                                        <div class="review-message">
                                            Очень удобно изучать язык, имея перед глазами слова и их перевод, а также использование этих слов в контексте.
                                            Можно не носить с собой словари или учебники, а взять несколько карточек и в дороге,
                                            в очереди, в транспорте изучать и повторять слова. Единственное, что не удобно, так это то,
                                            что они представлены не разрезанными, а в виде брошюры. Хочу попробовать еще и разрезанные карточки.
                                        </div>
                                    </div>

                                    <div class="summon-review-form">
                                        <div class="form-actions review-btn">
                                            <button class="btn btn-primary" onclick="showform();">Оставить сообщение</button>
                                        </div>
                                    </div>

                                    <div class="review-form">
                                        <form class="form-vertical">
                                            <div class="control-group">
                                                <div class="controls">
                                                    <textarea id="review" name="review" class="review-text" placeholder="Ваш отзыв"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-actions review-btn">
                                                <button type="submit" class="btn btn-primary">Сохранить</button>
                                                <button class="btn" type="reset">Очистить</button>
                                            </div>
                                        </form>
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


<script>
    function showform(){
        $('.summon-review-form').hide();
        $('.review-form').show();
    }
</script>
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


