<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset='utf-8' />
    <title>Вскладчину</title>
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" >
    <link href="/css/style.css" rel="stylesheet" type="text/css" >

</head>
<body>
<section class="main">
    <header id="main-header">
        <nav>
            <h1><a class="logo" href="/admin/">Админка</a></h1>
            <ul class="nav nav-pills main-header_nav">
                <li><a href="/admin/">Баланс</a></li>
                <li class="divider">|</li>
                <li><a href="/admin/orders">Заказы</a></li>
                <li class="divider">|</li>
                <li><a href="/admin/users">Пользователи</a></li>
                <li class="divider">|</li>
                <li><a href="/admin/transactions">Транзакции</a></li>
            </ul>
        </nav>
    </header>