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

    <title>LanguageBox</title>

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
                        <jsp:param name="page" value="main" />
                    </jsp:include>

                    <!-- <div id="sidebar-separator"></div> -->

                    <section id="main" class="clearfix" >
                        <div style="background: url(/img/main/main.jpg) no-repeat; background-size: 100%;  width: 100%;  height: 300px; margin-bottom: 30px;"></div>

                        <div id="main-content">
                        <div class="span3 widget">
                            <div class="widget-header">
                                <span class="title">Для учеников</span>
                            </div>
                            <div class="widget-content">
                                <div class="control-group">
                                    <label class="control-label">Выполнение домашних заданий</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Доступ к курсам через интернет</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Возможность заниматься: на работе, в дороге, дома.</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Приобретение курсов</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Возможность самостоятельного обучения</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Измерение роста знаний</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Геймификация процесса обучения</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Удобное взаимодействие с репетитором</label>
                                </div>
                                <div class="button-registration">
                                    <a class='button' href='/registration?role=STUDENT&do_registration=false'>
                                        <button class="btn btn-danger btn-block btn-large">Регистрация</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="span3 widget">
                            <div class="widget-header">
                                <span class="title">Для репетиторов</span>
                            </div>
                            <div class="widget-content">
                                <div class="control-group">
                                    <label class="control-label">Создание курсов</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Продажа курсов</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Приобретение готовых методик</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Вся библиотека в одном месте</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Удобное взаимодействие с учеником</label>
                                </div>
                                <div class="button-registration">
                                    <a class='button' href='/registration?role=TUTOR&do_registration=false'>
                                        <button class="btn btn-danger btn-block btn-large">Регистрация</button>
                                    </a>

                                </div>
                            </div>
                        </div>
                        <div class="span3 widget">
                            <div class="widget-header">
                                <span class="title">Для языковых школ</span>
                            </div>
                            <div class="widget-content">
                                <div class="control-group">
                                    <label class="control-label">Создание курсов</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Продажа курсов</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Приобретение готовых методик</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Геймификация процесса обучения</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Маркетинговый бонус для  ученика</label>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Удобное взаимодействие с учеником</label>
                                </div>
                                <div class="button-registration">
                                    <a class='button' href='/registration?role=SCHOOL&do_registration=false'>
                                        <button class="btn btn-danger btn-block btn-large">Регистрация</button>
                                    </a>

                                </div>
                            </div>
                        </div>
                        <div class="span11 widget">
                            <div class="widget-header">
                                <span class="title">Как работает сервис</span>
                            </div>
                            <div class="widget-content">
                                <div>
                                    Репетитор или методолог школы английского языка загруждает на сервис контент и создает из него собственную библиотеку.
                                    Далее из библиотеки создается курс. Созданный курс ученики приобретают напрямую на сайте.
                                    Далее ученики работают с курсом сомостоятельно или в созданной группе.
                                </div>
                                <div>
                                    <img src="/img/main/test.jpg" style="width: 100%;">
                                </div>
                            </div>
                        </div>
                        <div class="span11 widget">
                            <div class="widget-header">
                                <span class="title">Кто уже использует сервис</span>
                            </div>
                            <div class="widget-content">
                                <div>
                                    <div style="display: inline-block; width: 200px; vertical-align: top;">
                                        <img src="/img/main/comp1.png" width="200px; vertical-align: top;">
                                    </div>
                                    <div style="display: inline-block; width: 600px;">
                                        <h2>American British Center</h2>
                                        <p>American British Center – это международная команда,
                                            состоящая из дипломированных преподавателей носителей языка
                                            (native-speaker) и преподавателей bilingual (двуязычные),
                                            а также внимательного Американо-Британского
                                        </p>
                                    </div>
                                </div>

                                <div>
                                    <div style="display: inline-block; width: 200px; vertical-align: top;">
                                        <img src="/img/main/comp2.png" width="200px; vertical-align: top;">
                                    </div>
                                    <div style="display: inline-block; width: 600px;">
                                        <h2>American British Center</h2>
                                        <p>American British Center – это международная команда,
                                            состоящая из дипломированных преподавателей носителей языка
                                            (native-speaker) и преподавателей bilingual (двуязычные),
                                            а также внимательного Американо-Британского
                                        </p>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="span11 widget">
                            <div class="widget-header">
                                <span class="title">Часто задаваемые вопросы</span>
                                <div class="toolbar">
                                    <div class="btn-group">
                                        <span class="btn">Для умеников</span>
                                        <span class="btn">Для репититоров</span>
                                        <span class="btn">Для языковых школ</span>
                                    </div>
                                </div>
                            </div>
                            <div class="widget-content">
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>        <div style="width: 200px; margin: 20px; display: inline-block;">
                                <h4>На компьютер нужно устанавливать программу</h4>
                                Нет не нужно достаточно просто браузера
                            </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>
                                <div style="width: 200px; margin: 20px; display: inline-block;">
                                    <h4>На компьютер нужно устанавливать программу</h4>
                                    Нет не нужно достаточно просто браузера
                                </div>

                            </div>
                        </div>
                        <script>
                            function showform(){
                                $('.summon-review-form').hide();
                                $('.review-form').show();
                            }
                        </script>
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
    if(screen.width<1300){
        $('#sidebar').css("width", "45") ;
        $('.clearfix').css("margin", "0px 0px 0px 45px");
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