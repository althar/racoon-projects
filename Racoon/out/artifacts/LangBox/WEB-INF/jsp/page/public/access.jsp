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

  <title>LanguageBox - Login</title>

</head>

<body>

  <div id="login-wrap">
    <div id="login-ribbon"><i class="icon-lock"></i></div>

    <div id="login-buttons">
      <div class="btn-wrap">
        <button type="button" class="btn btn-inverse" data-target="#login-form"><i class="icon-key"></i></button>
      </div>
      <div class="btn-wrap">
        <button type="button" class="btn btn-inverse" data-target="#register-form"><i class="icon-edit"></i></button>
      </div>
      <div class="btn-wrap">
        <button type="button" class="btn btn-inverse" data-target="#forget-form"><i class="icon-question-sign"></i></button>
      </div>
    </div>

    <div id="login-inner" class="login-inset">
      <div id="login-circle">

        <section id="login-form" class="login-inner-form" data-angle="0">
          <h1>Вход</h1>
          <form class="form-vertical" action="/login">
            <input type="hidden" value="true" name="do_login">
            <div class="control-group-merged">
              <div class="control-group">
                <input type="text" placeholder="Имя пользователя" name="login" id="input-username" class="big required">
              </div>
              <div class="control-group">
                <input type="password" placeholder="Пароль" name="password" id="input-password" class="big required">
              </div>
            </div>
            <div class="control-group">
              <label class="checkbox">
                <input type="checkbox" name="remember_me" class="uniform"> Запомнить меня
              </label>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn btn-success btn-block btn-large">Войти</button>
            </div>
          </form>
        </section>

        <section id="register-form" class="login-inner-form" data-angle="90">
          <h1>Регистрация</h1>
          <form class="form-vertical" action="/registration">
            <input type="hidden" value="true" name="do_registration">
            <input type="hidden" value="${role}" name="role">
            <div class="control-group">
              <label class="control-label">Email</label>
              <div class="controls">
                <input type="text" name="login" class="required email">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Пароль</label>
              <div class="controls">
                <input type="password" name="password" class="required">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Ваше имя</label>
              <div class="controls">
                <input type="text" name="name" class="required">
              </div>
            </div>


            <c:if test="${role == 'STUDENT'}">
              <div class="control-group">
                <label class="control-label">Возраст</label>
                <div class="controls">
                  <input type="text" name="age" class="required">
                </div>
              </div>
            </c:if>

            <div class="control-group">
              <label class="control-label">Телефон</label>
              <div class="controls">
                <input type="text" name="phone" class="required">
              </div>
            </div>

            <div class="form-actions">
              <button type="submit" class="btn btn-danger btn-block btn-large">Зарегистрироваться</button>
              <p class='note'>
                Используя сервис, вы соглашаетесь
                соблюдать условия
                <a href="#">Лицензионного договора</a>
              </p>
            </div>

          </form>
        </section>

        <section id="forget-form" class="login-inner-form" data-angle="180">
          <h1>Забыли пароль?</h1>
          <form class="form-vertical" action="dashboard.html">
            <div class="control-group">
              <div class="controls">
                <input type="text" name="login" class="big required email" placeholder="Введите свой Email...">
              </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn btn-danger btn-block btn-large">Восстановить</button>
            </div>
          </form>
        </section>

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
