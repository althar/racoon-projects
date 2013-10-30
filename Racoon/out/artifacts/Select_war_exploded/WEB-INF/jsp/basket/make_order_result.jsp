<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="make-order-result">
    <c:choose>
        <c:when test="${success}">Ваш заказ (№ ${orderId}) успешно оформлен. Оператор скоро с Вами свяжется для уточнения деталей.<br> <a href="/Каталог"><b>Вернуться в каталог</b></a></c:when>
        <c:otherwise>Сбой при оформлении заказа. ${failureReason}<br> <a href="/Каталог"><b>Вернуться в каталог</b></a></c:otherwise>
    </c:choose>
</div>