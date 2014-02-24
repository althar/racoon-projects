<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<aside id="sidebar">
  <nav id="navigation" class="collapse">
    <ul>
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