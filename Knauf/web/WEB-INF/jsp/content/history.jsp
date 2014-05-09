<%@ page import="java.util.Calendar" %>
<%@ page import="racoonsoft.library.helper.Helper" %>
<%@ page import="racoonsoft.library.helper.StringHelper" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <ul class="breadcrumbs">
        <li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li>
        <li class="item"><a href="/profile" class="link">Личный кабинет</a><span class="arrow"></span></li>
        <li class="item">История заказов</li></ul>
    <div class="container section dark-title">

        <div class="boxed-group">
            <h2>История заказов</h2>
            <div class="boxed-group-inner">

                <table class="table">
                    <thead>
                    <tr class="muted">
                        <th>№ заказа</th>
                        <th>Дата размещения</th>
                        <th>Сумма заказа</th>
                        <th>Текущий статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders.get('OrderDetails')}" var="order">
                        <c:set var="date" value="${fn:replace(order.get('DateOrder').get('DateTime'),'/Date(', '')}"></c:set>
                        <c:set var="date" value="${fn:replace(date,')', '')}"></c:set>
                        <c:set var="date" value="${fn:replace(date,'/', '')}"></c:set>
                        <c:set var="date" value="${fn:replace(date,'\\\\', '')}" property="time"></c:set>
                        <jsp:useBean id="dat" class="java.util.Date"/>
                        <c:set target="${dat}" property="time" value="${date}"></c:set>
                    <tr class="history-done">
                        <td>${order.get('Number')}</td>
                        <td><fmt:formatDate pattern="dd MMMM yyyy, hh:mm" value="${dat}" /></td>
                        <td>${order.get('Summ')} теплуноса</td>
                        <td>${order.get('State')}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>