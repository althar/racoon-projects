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
      <img src="/assets/images/logo.png" alt="" style="width: 117px; height: 21px;">
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
    <div id="dropdown-lists">
      <div class="item-wrap">
      <a class="item" href="#" data-toggle="dropdown">
        <span class="item-icon"><i class="icon-exclamation-sign"></i></span>
        <span class="item-label">Оповещения</span>
        <span class="item-count">4</span>
      </a>
      <ul class="dropdown-menu">
        <li class="dropdown-item-wrap">
        <ul>
          <li>
          <a href="#">
            <span class="thumbnail"><img src="/assets/images/pp.jpg" alt=""></span>
            <span class="details">
            <strong>John Doe</strong> commented on your photo
            <span class="time">13 minutes ago</span>
            </span>
          </a>
          </li>
          <li>
          <a href="#">
            <span class="thumbnail"><img src="/assets/images/pp.jpg" alt=""></span>
            <span class="details">
            <strong>Jane Roe</strong> commented on your photo
            <span class="time">27 minutes ago</span>
            </span>
          </a>
          </li>
          <li>
          <a href="#">
            <span class="thumbnail"><img src="/assets/images/pp.jpg" alt=""></span>
            <span class="details">
            <strong>Billy John</strong> commented on your photo
            <span class="time">43 minutes ago</span>
            </span>
          </a>
          </li>
        </ul>
        </li>
        <li><a href="#">View all notifications</a></li>
      </ul>
      </div>
      <div class="item-wrap">
      <a class="item" href="#" data-toggle="dropdown">
        <span class="item-icon"><i class="icon-envelope"></i></span>
        <span class="item-label">Сообщения</span>
        <span class="item-count">16</span>
      </a>
      <ul class="dropdown-menu">
        <li class="dropdown-item-wrap">
        <ul>
          <li>
          <a href="#">
            <span class="thumbnail"><img src="/assets/images/pp.jpg" alt=""></span>
            <span class="details">
            <strong>John Doe</strong><br> Hello, do you have time to go out tomorrow?
            <span class="time">13 minutes ago</span>
            </span>
          </a>
          </li>
          <li>
          <a href="#">
            <span class="thumbnail"><img src="/assets/images/pp.jpg" alt=""></span>
            <span class="details">
            <strong>Jane Roe</strong><br> Hey, the reports said that you were...
            <span class="time">27 minutes ago</span>
            </span>
          </a>
          </li>
          <li>
          <a href="#">
            <span class="thumbnail"><img src="/assets/images/pp.jpg" alt=""></span>
            <span class="details">
            <strong>Billy John</strong><br> Can I borrow your new camera for taking...
            <span class="time">About an hour ago</span>
            </span>
          </a>
          </li>
        </ul>
        </li>
        <li><a href="/service/messages">View all messages</a></li>
      </ul>
      </div>
    </div>

    <div id="header-functions" class="pull-right">
      <div id="user-info" class="clearfix">
      <span class="info">
        Welcome
        <span class="name">${user.getStringValue('name')}</span>
      </span>
      <div class="avatar">
        <a class="dropdown-toggle" href="#" data-toggle="dropdown">
        <img src="/assets/images/pp.jpg" alt="Avatar">
        </a>
        <ul class="dropdown-menu pull-right">
        <li><a href="/service/teacher/profile"><i class="icol-user"></i> Профиль</a></li>
        <li><a href="/service/help"><i class="icol-help"></i> Помощь</a></li>
        <li class="divider"></li>
        <li><a href="/logout"><i class="icol-key"></i> Выход</a></li>
        </ul>
      </div>
      </div>
      <div id="logout-ribbon">
      <a href="/logout"><i class="icon-off"></i></a>
      </div>
    </div>
    </div>
  </div>
  </div>
</header>