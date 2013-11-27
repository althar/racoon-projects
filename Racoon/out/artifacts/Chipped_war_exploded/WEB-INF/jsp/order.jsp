<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript" src="/js/logic/order.js"></script>
<div class="shipping">
    <input type="hidden" id="guid" value="${guid}">
    <input type="hidden" id="wish_id" value="${wish_id}">
    <input type="hidden" id="user_amount" value="${amount}">
    <p>Укажите адрес доставки подарка</p>
    <div class="info">
        <div class="row-fluid">
            <span class="span8">Цена подарка:</span>
            <span class="span4" id="good-price">${goodPrice} рублей</span>
        </div>
        <div class="row-fluid">
            <span class="span8">Цена доставки:</span>
            <span class="span4" id="delivery-price">${deliveryPrice} рублей</span>
        </div>
        <div class="row-fluid">
            <span class="span8"><strong>Итого с учетом доставки:</strong></span>
            <span class="span4" ><strong id="total-price">${totalPrice}</strong></span>
        </div>
    </div>
    <div class="shipping_block">
        <div class="row-fluid">
            <img id="loader" class="hidden" src="/img/loader.gif">
            <form class="span7">
                <label>Местность:</label>
                <select class="span12" id="region">
                </select>
                <label>Населенный пункт:</label>
                <select class="span12" id="subregion">
                </select>
                <label>Тип Доставки:</label>
                <select class="span12" id="delivery-group">
                </select>
                <label>Тип Доставки:</label>
                <select class="span12" id="delivery-variant">
                </select>
                <label>Почтовый индекс:</label>
                <input type="text" class="span12" id="zip" value="143980"/>
                <label>Страна:</label>
                <input type="text" class="span12" id="country" value="Россия"/>
                </select>
                <label>Регион:</label>
                <input type="text" class="span12" id="reg" value="Приомурск"/>
                <label>Область:</label>
                <input type="text" class="span12" id="district" value="Чудовищная"/>
                <label>Город:</label>
                <input type="text" class="span12" id="city" value="Лосьск"/>
                <%--<label>Получатель:</label>--%>
                <%--<input type="text" class="span12" id="name"/>--%>
                <label>Адрес:</label>
                <div class="row-fluid">
                    <input type="text" class="span8" placeholder="улица" id="street" value="Партизанская"/>
                    <input type="text" class="span2" placeholder="дом" id="house" value="23"/>
                    <input type="text" class="span2" placeholder="кв." id="room" value="1"/>
                </div>
                <label>Телефон получателя:</label>
                <input type="text" class="span12" placeholder="8/___/_______" id="phone" value="89265713850"/>
                <label>Имя получателя</label>
                <input type="text" class="span12" id="name" value="Вовка"/>
                <label>Фамилия получателя:</label>
                <input type="text" class="span12" id="soname" value="Стыдный"/>
            </form>
        </div>
    </div>

    <button class="btn button" style="margin-top: 15px;" id="make_order">Готово</button>

</div>
</section>
<div id="cont">
<img src="../../img/line.png">
    <a href="/user/terms_of_service" target="_blank">Политиа конфиденциальности</a>
    <a href="/user/terms_of_payment" target="_blank">Условия пользования сервиса</a>
    <a href="mailto:support@v-skladchinu.ru">support@v-skladchinu.ru</a>
    <span></span>
    <a href="http://www.newmethod.ru" target="_blank">Сделано в Newmethod</a>
</div>
</body>
</html>