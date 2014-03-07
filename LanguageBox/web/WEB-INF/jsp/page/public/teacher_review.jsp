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

    <title>LanguageBox - Dashboard</title>

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
                        <jsp:param name="page" value="profile" />
                    </jsp:include>

                    <!-- <div id="sidebar-separator"></div> -->

                    <section id="main" class="clearfix" >
                        <div id="main-header" class="page-header">
                            <ul class="breadcrumb">
                                <li>
                                    <i class="icon-home"></i>LanguageBox
                                    <span class="divider">&raquo;</span>
                                </li>
                                <li>
                                    <a href="#">Отзывы о преподавателе</a>
                                </li>
                            </ul>

                        </div>

                        <div id="main-content">
                            <div class="widget span3">
                                <div class="widget-header">
                                    <span class="title">Репититор</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <div class="profile-image">
                                        <img src="/img/teacher/rep.png">
                                    </div>
                                    <div class="profile-reputation">
                                        <span>4 отзыва</span>
                            <span>
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                            </span>
                            <span>
                                4.8
                            </span>
                                    </div>
                                    <div class="profile-email">
                                        <span>Email </span>
                                        <span>anna2000@mail.ru</span>
                                    </div>
                                    <div class="profile-phone">
                                        <span>Телефон </span>
                                        <span>89165900080</span>
                                    </div>
                                    <div class="profile-send-message">
                                        <button class="btn btn-info">Отправить сообщение</button>
                                    </div>
                                </div>
                            </div>
                            <div class="widget span9">
                                <div class="widget-header">
                                    <span class="title">Иванова Анна Сергеевна</span>
                                </div>
                                <div class="widget-content" style="padding-top: 10px;">
                                    <div>
                                        <p>Репетитор английского языка.</p>
                                        <p>Образование: МГЛУ (МГПИИЯ им. М. Тореза), педагогический факультет, специальность – преподаватель иностранных языков (английский, шведский, французский). </p>
                                        <p>Стажировки в Cambridge University, Meridian International School, Uppsala University.  </p>
                                        <p>Опыт преподавания – 17 лет:     </p>
                                        <p>Британский языковой центр, корпоративный преподаватель («Райффайзен», «Номос банк», ТНК-BP, Rolf) (с 2007 г.); </p>
                                        <p>преподаватель МосГУ (2007-2010 гг.);  </p>
                                        <p>преподаватель Московского института лингвистики и Британской языковой школы (2004-2008 гг.); </p>
                                        <p>преподаватель английского и шведского языков в МГИМО (1997-2000 гг.). </p>
                                        <p>Работала в международных организациях и компаниях, в том числе в качестве переводчика в британской компании в Лондоне в течение 5 лет. </p>
                                        <p>Опыт работы переводчиком английского и шведского языков (синхронный, последовательный, письменный перевод) – более 20 лет.  </p>
                                        <p>Репетиторская деятельность – 9 лет.  </p>
                                        <p>Район: Кутузовская, Парк Победы.  </p>
                                        <p>Плюс: ГИА и ЕГЭ по английскому языку, IELTS, FCE, CAE, бизнес-курс, разговорный курc, подготовка к внутренним экзаменам в ВУЗы, к обучению за рубежом, к собеседованию в иностранные компании. </p>
                                        <p>2500 руб. / 90 мин. (6-9 класс).  </p>
                                        <p>3000 руб. / 90 мин. (10-11 класс, взрослые).  </p>
                                    </div>
                                    <div>
                                        <img src="/img/social/social_1.png"></img>
                                        <img src="/img/social/social_2.png"></img>
                                    </div>
                                </div>
                            </div>








                            <!--Отзывы-->
                            <div class="widget span3" style="visibility: hidden;"></div>
                            <div class="span9 widget">
                                <div class="widget-header">
                                    <span class="title">Отзывы</span>
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
                    <span class="review-author-star">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                    </span>
                    <span class="review-author-rating">5.0</span>
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
                    <span class="review-author-star">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                    </span>
                    <span class="review-author-rating">5.0</span>
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
                    <span class="review-author-star">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                        <img src="/img/icons/star.png">
                    </span>
                    <span class="review-author-rating">4.0</span>
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
                                            <button class="btn btn-primary" onclick="showform();">Оставить отзыв</button>
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
<script>
    function showform(){
        $('.summon-review-form').hide();
        $('.review-form').show();
    }
</script>
<!-- Rating  -->
<script src="/plugins/rating/jquery.rating.min.js"></script>

<!-- SparkLine -->
<script src="/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Demo Scripts -->
<script src="/assets/js/demo/dashboard.js"></script>


<script src="/js/common/all.js" type="text/javascript"></script>

</body>

</html>


