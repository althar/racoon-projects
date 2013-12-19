<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>Language Box</title>
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" media="screen" rel="stylesheet"
          type="text/css"/>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic"
          media="screen" rel="stylesheet" type="text/css"/>

    <link href="/css/normalize.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="/css/devise.css" media="screen" rel="stylesheet" type="text/css"/>

    <script src="/js/jq/jquery-2.0.3.js" type="text/javascript"></script>
    <script src="/js/common/all.js" type="text/javascript"></script>
</head>
<body class='devise devise_registration'>
<%@ include file="/WEB-INF/jsp/section/header.jsp" %>
<div class='main_content'>
<div class='main_content-inner row'>
<div class='page_body'>
<div class='page_body-inner'>
<c:choose>
<c:when test="${widget == 'login'}">
    <div class='student_registr small-6 column small-centered'>

        <h2 class='text-center'>Вход в сервис</h2>

        <form class='simple_form' action="/login">
            <h3>Данные для входа</h3>
            <input type="hidden" value="true" name="do_login">
            <div class='control-group'>
                <label class='control-label'>
                    E-mail
                </label>

                <div class='controls'>
                    <input name='login' type='email'>
                    <span class='error'>
                    </span>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='password'>
                    Пароль
                </label>

                <div class='controls'>
                    <input name='password' type='password'>
                </div>
            </div>
            <div class='form-action row'>
                <div class='small-8 small-offset-4 column'>
                    <div class='custom_checkbox'>
                        <label class='checkbox'>
                                ${error}
                        </label>
                    </div>
                    <button class='button' type='submit'>
                        Войти
                    </button>
                </div>
            </div>
        </form>
    </div>
</c:when>
<c:otherwise>
<c:choose>
<c:when test="${role == 'STUDENT'}">
    <div class='student_registr small-6 column small-centered'>

        <h2 class='text-center'>Регистрация нового ученика</h2>

        <form class='simple_form' action="/registration">
            <h3>Данные для входа</h3>
            <input type="hidden" value="STUDENT" name="role">
            <input type="hidden" value="true" name="do_registration">
            <div class='control-group error'>
                <label class='control-label'>
                    E-mail
                </label>

                <div class='controls'>
                    <input name='login' type='email'>
                    <span class='error'>
                      Введите корректный e-mail
                    </span>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='password'>
                    Пароль
                </label>

                <div class='controls'>
                    <input name='password' type='password'>
                </div>
            </div>
            <div class='control-group'>
                    <%--<label class='control-label' for='password_confirmation'>--%>
                    <%--Подтверждение пароля--%>
                    <%--</label>--%>

                    <%--<div class='controls'>--%>
                    <%--<input name='password_confirmation' type='password'>--%>
                    <%--</div>--%>
            </div>
            <h3>Личные данные</h3>

            <div class='control-group'>
                <label class='control-label' for='name'>
                    Имя
                </label>

                <div class='controls'>
                    <input name='name' type='text'>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='age'>
                    Возраст
                </label>

                <div class='controls'>
                    <input name='age' type='text'>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='tel'>
                    Номер телефона
                </label>

                <div class='controls'>
                    <input name='phone' type='tel'>
                </div>
            </div>
            <div class='form-action row'>
                <div class='small-8 small-offset-4 column'>
                    <div class='custom_checkbox'>
                        <label class='checkbox'>
                                ${error}
                        </label>
                    </div>
                    <button class='button' type='submit'>
                        Зарегистрироваться и войти
                    </button>
                    <div class='note'>
                        Используя сервис, вы соглашаетесь
                        соблюдать условия
                        <a href="#">Лицензионного договора</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</c:when>
<c:when test="${role == 'TUTOR'}">
    <div class='teacher_registr small-6 column small-centered'>
        <h2 class='text-center'>Регистрация репетитора</h2>

        <form class='simple_form' action="/registration">
            <h3>Данные для входа</h3>
            <input type="hidden" value="true" name="do_registration">
            <input type="hidden" value="TUTOR" name="role">
            <div class='control-group'>
                <label class='control-label'>
                    E-mail
                </label>

                <div class='controls'>
                    <input name='login' type='email'>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='password'>
                    Пароль
                </label>

                <div class='controls'>
                    <input name='password' type='password'>
                </div>
            </div>
            <div class='control-group'>
                    <%--<label class='control-label' for='password_confirmation'>--%>
                    <%--Подтверждение пароля--%>
                    <%--</label>--%>

                    <%--<div class='controls'>--%>
                    <%--<input name='password_confirmation' type='password'>--%>
                    <%--</div>--%>
            </div>
            <h3>Личные данные</h3>

            <div class='control-group'>
                <label class='control-label' for='name'>
                    Имя
                </label>

                <div class='controls'>
                    <input name='name' type='text'>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='tel'>
                    Номер телефона
                </label>

                <div class='controls'>
                    <input name='phone' type='tel'>
                </div>
            </div>
            <div class='form-action row'>
                <div class='small-8 small-offset-4 column'>
                    <div class='custom_checkbox'>
                        <label class='checkbox'>
                                ${error}
                        </label>
                    </div>
                    <button class='button' type='submit'>
                        Зарегистрироваться и войти
                    </button>
                    <div class='note'>
                        Используя сервис, вы соглашаетесь
                        соблюдать условия
                        <a href="#">Лицензионного договора</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</c:when>
<c:otherwise>
    <div class='school_registr small-6 column small-centered'>
        <h2 class='text-center'>Регистрация языковой школы</h2>

        <form class='simple_form' action="/registration">
            <h3>Данные для входа</h3>
            <input type="hidden" value="SCHOOL" name="role">
            <input type="hidden" value="true" name="do_registration">
            <div class='control-group'>
                <label class='control-label'>
                    E-mail
                </label>

                <div class='controls'>
                    <input name='login' type='email'>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='password'>
                    Пароль
                </label>

                <div class='controls'>
                    <input id='password' name='password' type='password'>
                </div>
            </div>
            <div class='control-group'>
                    <%--<label class='control-label' for='password_confirmation'>--%>
                    <%--Подтверждение пароля--%>
                    <%--</label>--%>

                    <%--<div class='controls'>--%>
                    <%--<input id='password_confirmation' name='password_confirmation' type='password'>--%>
                    <%--</div>--%>
            </div>
            <h3>Личные данные</h3>

            <div class='control-group'>
                <label class='control-label' for='name'>
                    Название
                </label>

                <div class='controls'>
                    <input id='name' name='school' type='text'>
                </div>
            </div>
            <div class='control-group'>
                <label class='control-label' for='tel'>
                    Номер телефона
                </label>

                <div class='controls'>
                    <input id='tel' name='tel' type='tel'>
                </div>
            </div>
            <div class='form-action row'>
                <div class='small-8 small-offset-4 column'>
                    <div class='custom_checkbox'>
                        <label class='checkbox'>
                                ${error}
                        </label>
                    </div>
                    <button class='button' type='submit'>
                        Зарегистрироваться и войти
                    </button>
                    <div class='note'>
                        Используя сервис, вы соглашаетесь
                        соблюдать условия
                        <a href="#">Лицензионного договора</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</c:otherwise>
</c:choose>
</c:otherwise>
</c:choose>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>