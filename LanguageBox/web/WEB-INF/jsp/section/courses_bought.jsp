<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="widget span6 courses-section courses-list-section">
  <div class="widget-header">
    <span class="title">Курсы</span>

    <div class="toolbar">
      <a class="btn" href="#">Show more</a>
    </div>
  </div>
  <div class="widget-content courseslist">
    <ul class="thumbnails">
      <c:forEach items="${bought_courses}" var="item">
        <li>
          <div class="head">
            <h2 class="title"><a href="">${item.getStringValue('name')}</a></h2>
            <span class="note">${item.getStringValue('level')}</span>
          </div>
          <div class="thumbnail">
            <img src="/img/course_preview.png" />
          </div>
          <div class="info">
            <p>Автор: ${item.getStringValue('author')}</p>
            <p>Дата заказа: <fmt:formatDate pattern="dd.MM.yyyy" value="${item.getDateValue('created')}" /></p>
            <p>Цена: <strong>${item.getDoubleValue('price')} руб</strong></p>
            <c:choose>
              <c:when test="${item.getStringValue('purchase_status')=='APPROVED'}">
                <button class='btn' disabled>Оплачено</button>
              </c:when>
              <c:otherwise>
                <button class='btn'>Оплатить</button>
              </c:otherwise>
            </c:choose>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>