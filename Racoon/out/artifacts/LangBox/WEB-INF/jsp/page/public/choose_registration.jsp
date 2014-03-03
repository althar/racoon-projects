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
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css" media="screen">

    <!-- Uniform Stylesheet -->
    <link rel="stylesheet" href="/plugins/uniform/css/uniform.default.css" media="screen">

    <!-- Plugin Stylsheets first to ease overrides -->

    <!-- End Plugin Stylesheets -->

    <!-- Main Layout Stylesheet -->
    <link rel="stylesheet" href="/assets/css/fonts/icomoon/style.css" media="screen">
    <link rel="stylesheet" href="/assets/css/login.css" media="screen">
    <link rel="stylesheet" href="/plugins/zocial/zocial.css" media="screen">

    <link rel="stylesheet" href="/custom_css/custom.css" media="screen">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>
<body class='devise devise_registration'>
<div style="width: 1000px; margin: 0 auto;">
<div id="login-wrap" style="display: inline-block; vertical-align: top; margin: 10px;">
    <div id="login-ribbon"><i class="icon-lock"></i></div>

    <div id="login-buttons">


        <div class="btn-wrap">
            <button type="button" class="btn btn-inverse disabled" data-target="#register-form"><i class="icon-edit"></i></button>
        </div>



    </div>

    <div id="login-inner" class="login-inset rotating" style="height: 472px;">
        <div id="login-circle">
            <section class="login-inner-form active" data-angle="0" style="-webkit-transform: rotate(0deg);">
                <h1>Ученик</h1>
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
                    <div class="form-actions">
                        <a class='button' href='/registration?role=STUDENT&do_registration=false'>
                            <button class="btn btn-danger btn-block btn-large">Регистрация</button>
                        </a>

                    </div>
            </section>



        </div>
    </div>
</div>
<div id="login-wrap" style="display: inline-block; vertical-align: top; margin: 10px;">
    <div id="login-ribbon"><i class="icon-lock"></i></div>

    <div id="login-buttons">


        <div class="btn-wrap">
            <button type="button" class="btn btn-inverse disabled" data-target="#register-form"><i class="icon-edit"></i></button>
        </div>



    </div>

    <div class="login-inset rotating">
        <div id="login-circle">
            <section id="register-form" class="login-inner-form active" data-angle="0" style="-webkit-transform: rotate(0deg);">
                <h1>Репетитор</h1>
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
                <div class="form-actions">
                    <a class='button' href='/registration?role=TUTOR&do_registration=false'>
                        <button class="btn btn-danger btn-block btn-large">Регистрация</button>
                    </a>

                </div>
            </section>



        </div>
    </div>
</div>
<div id="login-wrap" style="display: inline-block; vertical-align: top; margin: 10px;">
    <div id="login-ribbon"><i class="icon-lock"></i></div>

    <div id="login-buttons">


        <div class="btn-wrap">
            <button type="button" class="btn btn-inverse disabled" data-target="#register-form"><i class="icon-edit"></i></button>
        </div>



    </div>

    <div class="login-inset rotating">
        <div id="login-circle">
            <section id="register-form" class="login-inner-form active" data-angle="0" style="-webkit-transform: rotate(0deg);">
                <h1>Школа иностранных языков</h1>
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
                <div class="form-actions">
                    <a class='button' href='/registration?role=SCHOOL&do_registration=false'>
                        <button class="btn btn-danger btn-block btn-large">Регистрация</button>
                    </a>

                </div>
            </section>



        </div>
    </div>
</div>
</div>
<!-- Core Scripts -->
<script src="/assets/js/libs/jquery-1.8.3.min.js"></script>
<script src="/assets/js/libs/jquery.placeholder.min.js"></script>

<!-- Login Script -->
<script src="/assets/js/login.js"></script>

<!-- Validation -->
<script src="/plugins/validate/jquery.validate.min.js"></script>

<!-- Uniform Script -->
<script src="/plugins/uniform/jquery.uniform.min.js"></script>
</body>
</html>