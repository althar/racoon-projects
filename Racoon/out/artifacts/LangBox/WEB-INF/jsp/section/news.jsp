<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="span6 widget">
  <div class="widget-header">
    <span class="title">Новости cервиса</span>
    <div class="toolbar">
      <div class="btn-group">
        <span class="btn" rel="tooltip" title="Refresh"><i class="icon-refresh"></i></span>
        <a class="btn" href="#">Show more</a>
      </div>
    </div>
  </div>
  <div class="widget-content news">
    <ul class="thumbnails">
      <c:forEach items="${news}" var="item">
      <li>
        <div class="head">
          <h2 class="title"><a href="#">${item.getStringValue('title')}</a></h2>
          <span class="date"><fmt:formatDate pattern="dd.MM.yyyy" value="${item.getDateValue('created')}" /></span>
          <!-- <span class="date">September 06, 2012</span> <span class="tags"><a href="#">Technology</a>, <a href="#">General</a></span> -->
        </div>
        <div class="thumbnail">
          <img alt="" src="/sample/05.jpg">
        </div>
        <div class="info">
          <p>${item.getStringValue('text')}</p>
        </div>
      </li>
      </c:forEach>
    </ul>
  </div>
</div>