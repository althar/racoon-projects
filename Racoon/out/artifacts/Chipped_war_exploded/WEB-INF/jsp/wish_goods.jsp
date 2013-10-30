<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript" src="/js/logic/wish_edit.js"></script>
<input type="hidden" id="ids" value="${ids}">
<input type="hidden" id="to-friends" value="${toFriends}">

<div class="catalog product-preview">
    <div class="row-fluid">
        <div class="span12">
            <c:forEach var="good" items="${goods}" varStatus="rowStatus">
                <div class="product_block">
                    <div class='row-fluid'>
                        <div class='span7'>
                            <b>${good.name}</b>
                            <img class="product-image-big" src="${good.imageMediumPath}"/>
                        </div>
                        <div class="span5">
                            <p class="product-text">
                                <c:forEach var="characteristic" items="${good.characteristics}">
                                    ${characteristic.key} : ${characteristic.value}<br>
                                </c:forEach>
                            </p>

                            <p class="product-price">
                                <span class="price">${good.price},-</span><br/>
                                Цена с доставкой
                            </p>
                        </div>
                    </div>
                    <div class="tape"></div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span9">
            <form class="form-inline">
                <label>Дата:</label>
                <input type="text" class="span3 datepicker" id="date-picker">
                <i id="calendar-icon" class="icon icon-calendar"></i>
                <label>Повод:</label>
                <select class="span4" id="reason">
                    <option>День рождения</option>
                    <option>8 марта</option>
                    <option>Новый год</option>
                    <option>Просто так</option>
                </select>
                <%--<label>Город доставки:</label>--%>
                <%--<select class="span4" id="region">--%>
                <%--</select>--%>
                <%--<label>Регион доставки:</label>--%>
                <%--<select class="span4" id="subregion">--%>
                <%--</select>--%>
                <%--<label>Тип доставки:</label>--%>
                <%--<select class="span4" id="delivery-group">--%>
                <%--</select>--%>
                <%--<label>Вариант доставки:</label>--%>
                <%--<select class="span4" id="delivery-variant">--%>
                <%--</select>--%>
                <%--<img id="loader" class="hidden" src="/img/loader.gif">--%>
            </form>
            <div class="row-fluid button-bar">
                <div class='span6'>
                    <button class="btn button button-medium" id="wish-it">Хочу в подарок</button>
                </div>
                <div class='span6'>
                    <button class="btn button button-medium" id="friend-wish">Подарить другу</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</section>
</body>
</html>	