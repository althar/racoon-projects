<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class='small-8 column'>
    <div class='white_block'>
        <h3>Курсы</h3>
        <div class='courses_list'>
            <c:forEach items="${bought_courses}" var="item">
                <div class='courses_list-item'>
                    <div class='courses-image'>
                        <img src="/img/course_preview.png" />
                    </div>
                    <div class='courses-features'>
                        <ul class='features_list no-bullet'>
                            <li>
                                <label>Курс:</label>
                                <span class='feature'>${item.getStringValue('name')}</span>
                            </li>
                            <li>
                                <label>Уровень:</label>
                                <span class='feature'>${item.getStringValue('level')}</span>
                            </li>
                            <li>
                                <label>Автор:</label>
                                <span class='feature'>${item.getStringValue('author')}</span>
                            </li>
                            <li>
                                <label>Дата заказа:</label>
                                <span class='feature'><fmt:formatDate pattern="dd.MM.yyyy" value="${item.getDateValue('created')}" /></span>
                            </li>
                            <%--<li>--%>
                                <%--<label>Баллы:</label>--%>
                                <%--<span class='feature'>145 из 475</span>--%>
                            <%--</li>--%>
                            <li>
                                <label>Цена:</label>
                                <span class='feature'>${item.getDoubleValue('price')} руб</span>
                            </li>
                        </ul>
                    </div>
                    <div class='courses-status'>
                        <c:choose>
                            <c:when test="${item.getStringValue('purchase_status')=='APPROVED'}">
                                <button class='button small secondary' disabled>Оплачено</button>
                            </c:when>
                            <c:otherwise>
                                <button class='button small secondary'>Оплатить</button>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>