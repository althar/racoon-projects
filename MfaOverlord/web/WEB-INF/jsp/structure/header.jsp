<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1 class="sr-only">${title}</h1>
<nav id="header" class="header-navbar" role="navigation">

<div class="header-navbar-inner container">

<div id="brand" class="navbar-brand">
    <a href="index.html" rel="bookmark">

        <!-- to disable lazy loading, remove data-src and data-src-retina -->
        <img src="../img/light-logo.png" data-src="../img/light-logo.png" data-src-retina="../img/logo-retina.png" width="244" height="56" alt="">

        <!--fallback for no javascript browsers-->
        <noscript>
            <img src="../img/light-logo.png" alt="">
        </noscript>
    </a>
</div>

<ul class="nav nav-pages hidden-xs">
    <li><a href="#">About</a></li>
    <li><a href="#">Contact</a></li>
    <li><a href="#">Advertise </a></li>
    <li><a href="#">Terms &amp; Conditions</a></li>
    <li><a href="#">Privacy</a></li>
</ul>

<ul class="nav nav-icons">
    <li>
        <a href="#" class="btn-icon" data-toggle=".header-navbar-inner" data-toggle-class="search-toggled-in">
            <span class="search-toggled-out-icon glyphicon glyphicon-search"></span>
            <span class="search-toggled-in-icon glyphicon glyphicon-remove"></span>
        </a>
    </li>
</ul>

<div class="search-wrapper js-stoppropagation">
    <div class="search-wrapper-inner">
        <form>
            <input type="text" value="" placeholder="search ... ">
            <button class="btn-icon" type="submit"><span class="glyphicon glyphicon-search"></span></button>
        </form>
    </div>
</div>

<ul class="nav navbar-nav">
<li class="nav-all pull-right full-subnav-wrapper">

</li>
<li class="active">
    <a href="#" data-toggle="li">Категории</a>
    <div class="subnav-wrapper">
        <ul class="subnav">
            <c:forEach items="${related_semantics}" var="sem" end="10">
                <li><a href="?semantics_id=${sem.getValue('id')}">${sem.getValue('word')}</a></li>
            </c:forEach>
        </ul>
    </div>
</li>
</ul>

</div>

</nav>