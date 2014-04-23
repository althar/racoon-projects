<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="/js/logic/basket.js"></script>
<c:choose>
    <c:when test="${not empty cart.get('CartItems')}">
        <div class="content layout">

            <ul class="breadcrumbs">
                <li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li>
                <li class="item">Корзина</li>
            </ul>
            <div class="container section dark-title">

                <hr class="separation">
                <div class="container white" id="basket-inner">
                    <%@ include file="/WEB-INF/jsp/widget/basket-inner.jsp" %>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="content layout">

            <div class="container section dark-title">

                <div class="container white">
                    <h3>Сообщение сайта</h3>

                    <p>Ваша корзина пуста.</p>
                </div>

            </div>
        </div>
    </c:otherwise>
</c:choose>