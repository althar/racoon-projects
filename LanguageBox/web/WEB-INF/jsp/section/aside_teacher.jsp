<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<aside id="sidebar">
  <nav id="navigation" class="collapse">
    <ul>
      <li>
        <span title="Основное">
          <i class="icon-home"></i>
          <span class="nav-title">Основное</span>
        </span>
        <ul class="inner-nav">
          <li><a href="/service/teacher"><i class="icol-dashboard"></i> Главная</a></li>
          <li><a href="#"><i class="icol-newspaper"></i> Новости сервиса</a></li>
        </ul>
      </li>
      <li class="active">
        <span title="Курсы">
          <i class="icon-list"></i>
          <span class="nav-title">Курсы</span>
        </span>
        <ul class="inner-nav">
          <li class="active"><a href="/service/teacher/courses.html"><i class="icol-slides-stack"></i> Курсы</a></li>
          <li><a href="#"><i class="icol-book"></i> Библиотека</a></li>
        </ul>
      </li>
      <li>
        <span title="Ученики">
          <i class="icon-users"></i>
          <span class="nav-title">Ученики</span>
        </span>
        <ul class="inner-nav">
          <li><a href="#"><i class="icol-user-thief-baldie"></i> Список учеников</a></li>
          <li><a href="#"><i class="icol-coins"></i> Оплата</a></li>
          <li><a href="#"><i class="icol-group"></i> Группы</a></li>
        </ul>
      </li>
      <li>
        <span title="Магазин курсов">
          <i class="icon-bag"></i>
          <span class="nav-title">Магазин</span>
        </span>
        <ul class="inner-nav">
          <li><a href="#"><i class="icol-paper-bag"></i> Магазин</a></li>
        </ul>
      </li>
      <li>
        <span title="Настройки">
          <i class="icon-cogs"></i>
          <span class="nav-title">Настройки</span>
        </span>
        <ul class="inner-nav">
          <li><a href="#"><i class="icol-vcard"></i> Настройки профиля</a></li>
        </ul>
      </li>
    </ul>
  </nav>
</aside>