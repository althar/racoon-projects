<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="ru">
<head>
    <title>Главная · ТеплоКлуб</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="//yandex.st/bootstrap/2.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/expansion.css">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" async="" src="http://mc.yandex.ru/metrika/watch.js"></script>
    <script src="//yandex.st/jquery/1.8.3/jquery.min.js"></script>
    <style type="text/css"></style>
    <link rel="stylesheet" href="//yandex.st/jquery/fancybox/2.1.4/jquery.fancybox.min.css">
    <link rel="stylesheet" href="//yandex.st/jquery/fancybox/2.1.4/helpers/jquery.fancybox-buttons.min.css">

    <script src="//yandex.st/jquery/fancybox/2.1.4/jquery.fancybox.min.js"></script>
    <script src="//yandex.st/jquery/fancybox/2.1.4/helpers/jquery.fancybox-buttons.min.js"></script>

    <script src="//yandex.st/bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <%--<script src="js/placeholder.js"></script>--%>
    <%--<script src="js/ready.js?1"></script>--%>

</head>
<body>
<div class="wrapper layout">
    <%@ include file="/WEB-INF/jsp/structure/header.jsp" %>
        <div class="container layout">
    <%@ include file="/WEB-INF/jsp/structure/aside.jsp" %>
    <%@ include file="/WEB-INF/jsp/structure/body.jsp" %>
            </div>
</div>
<%@ include file="/WEB-INF/jsp/structure/footer.jsp" %>
</body>
</html>