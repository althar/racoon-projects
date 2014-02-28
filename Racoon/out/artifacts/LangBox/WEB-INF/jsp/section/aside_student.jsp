<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<aside id="sidebar">
  <nav id="navigation" class="collapse">
    <ul style="width: 294px;">
      <li <c:if test="${param.page=='main'}">class="active"</c:if>>
        <a href="/service/student" title="Главная">
          <i class="icon-home"></i>
          <span class="nav-title">Основное</span>
        </a>
      </li>
      <li <c:if test="${param.page=='courses'}">class="active"</c:if>>
        <a href="/service/student/courses" title="Курсы">
          <i class="icon-list"></i>
          <span class="nav-title">Курсы</span>
        </a>
      </li>
      <li <c:if test="${param.page=='market'}">class="active"</c:if>>
        <a href="#" title="Магазин курсов">
          <i class="icon-bag"></i>
          <span class="nav-title">Магазин</span>
        </a>
          <ul class="inner-nav" style="background: rgb(253, 253, 253);">
              <li><a href="#"><span style="font-weight: bold;">Все языки</span><span style="float: right; padding: 2px; font-weight: bold;">124</span></a></li>
              <li class="active"><a href="#"><span style="background: url(/img/icons/en.png) left no-repeat; padding-left: 20px;">Английский</span><span style="float: right; padding: 2px;">5</span></a></li>
              <li><a href="#"><span style="background: url(/img/icons/ger.png) left no-repeat; padding-left: 20px;">Немецкий</span><span style="float: right; padding: 2px;">15</span></a></li>
              <li><a href="#"><span style="background: url(/img/icons/fr.png) left no-repeat; padding-left: 20px;">Французкий</span><span style="float: right; padding: 2px;">55</span></a></li>
              <li><a href="#"><span style="background: url(/img/icons/it.png) left no-repeat; padding-left: 20px;">Итальянский</span> <span style="float: right; padding: 2px; ">12</span></a></li>
              <li><a href="#"><span style="background: url(/img/icons/sp.png) left no-repeat; padding-left: 20px;">Испанский</span><span style="float: right; padding: 2px;">4</span></a></li>
              <li style="margin-top: 40px; border-bottom: 1px solid #bae0f2;"></li>
              <li><a href="#"><span style="font-weight: bold;">Все урони</span><span style="float: right; padding: 2px; font-weight: bold;">124</span></a></li>
              <li><a href="#"><span>Beginner</span><span style="float: right; padding: 2px;">5</span></a></li>
              <li><a href="#"><span>Elementary</span><span style="float: right; padding: 2px;">15</span></a></li>
              <li><a href="#"><span>Pre-intermediate</span><span style="float: right; padding: 2px;">55</span></a></li>
              <li><a href="#"><span>Intermediate</span><span style="float: right; padding: 2px;">12</span></a></li>
              <li><a href="#"><span>Upper-intermediate</span><span style="float: right; padding: 2px;">12</span></a></li>
              <li><a href="#"><span>Advanced</span><span style="float: right; padding: 2px;">12</span></a></li>
          </ul>
      </li>
      <li <c:if test="${param.page=='settings'}">class="active"</c:if>>
        <a href="#" title="Настройки">
          <i class="icon-cogs"></i>
          <span class="nav-title">Настройки</span>
        </a>
      </li>
    </ul>
  </nav>
</aside>