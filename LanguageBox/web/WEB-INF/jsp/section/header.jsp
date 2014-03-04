<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<header id="header" class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <div class="brand-wrap pull-left">
                <div class="brand-img">
                    <a class="brand" href="#">
                        <img src="/assets/images/logo.png" alt="" style="width: 220px; height: 38px;">
                    </a>
                </div>
            </div>

            <div id="header-right" class="clearfix">
                <div id="nav-toggle" data-toggle="collapse" data-target="#navigation" class="collapsed">
                    <i class="icon-caret-down"></i>
                </div>
                <div id="header-search">
      <span id="search-toggle" data-toggle="dropdown">
      <i class="icon-search"></i>
      </span>
                    <form class="navbar-search">
                        <input type="text" class="search-query" placeholder="Поиск">
                    </form>
                </div>


                <div id="header-functions" class="pull-right">
                    <div class='log_in column'>
                        <a href="/choose_registration">Регистрация</a>
                        <a href="/login">Вход</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>