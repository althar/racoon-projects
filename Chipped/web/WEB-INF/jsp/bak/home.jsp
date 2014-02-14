<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset='utf-8' />
    <title>Вскладчину</title>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/js/jq/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="/js/jq/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/jq/jquery.mask.js"></script>
    <script type="text/javascript" src="/js/jq/jquery.jquery-numeric.js"></script>

    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <%--<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" >--%>
    <link href="/css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<section class="welcome-screen">
    <div class="welcome-screen_description">
        <h1><a class="logo-big">Вскладчину</a></h1>
        <p class="welcome-screen_text">
            Подари совместно с друзьями
            что-нибудь другу, или предложи
            друзьям сделать общий подарок тебе.
            Подарки будут доставлены адресату!
        </p>
        <a href="/user/terms_of_service" target="_blank">Подробнее</a>
        <div class="control-bar">
            <div class="input-prepend button-prepend">
                <a href="/facebook/login"><span class="add-on"><i class="icon icon-facebook"></i></span><button class="btn button">Войти через Facebook</button></a>
            </div>
            <div class="input-prepend button-prepend">
                <span class="add-on"><i class="icon icon-vk"></i></span><button class="btn button">Войти через Vkontakte</button>
            </div>
        </div>
    </div>
</section>
<div id="cont">
    <a href="/user/terms_of_service" target="_blank">Политиа конфиденциальности</a>
    <a href="/user/terms_of_payment" target="_blank">Условия пользования сервиса</a>
    <a href="mailto:support@v-skladchinu.ru">support@v-skladchinu.ru</a>
    <span></span>
    <a href="http://www.newmethod.ru" target="_blank">Сделано в Newmethod</a>
</div>
</body>

</html>