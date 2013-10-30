<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Chaos administration</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.2.custom.js"></script>
</head>
<section id="page">
<div class="auth-form">
    <div>
        <span>Логин</span><input type="text" value="" id="login">
        <span>Пароль</span><input type="text" value="" id="password">
    </div>
    <div>
        <input type="button" id="login-butt" value="Войти">
    </div>
</div>
</section>

<%@ include file="footer.jsp"%>