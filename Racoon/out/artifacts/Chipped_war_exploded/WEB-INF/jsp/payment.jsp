<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="payment">
    <script type="text/javascript" src="/js/logic/payment.js"></script>
    <p>Введите данные своей банковской карты</p>
    <input type="hidden" id="wish_id" value="${wish_id}">
    <input type="hidden" id="user_id" value="${user_id}">
    <input type="hidden" id="friends_wish" value="${friends_wish}">
    <input type="hidden" id="for_user_id" value="${for_user_id}">

    <input type="hidden" id="payment_type" value="${payment_type}">
    <input type="hidden" id="guid" value="${guid}">
    <input type="hidden" id="address_id" value="${addressId}">
    <input type="hidden" id="delivery_variant_id" value="${deliveryVariantId}">
    <input type="hidden" id="address" value="${address}">
    <input type="hidden" id="first_name" value="${firstName}">
    <input type="hidden" id="last_name" value="${lastName}">
    <input type="hidden" id="phone" value="${phone}">
    <div class="payment_block">
        <div class="row-fluid">
            <form class="span7">
                <label>Имя (как на карте):</label>
                <input id="name" type="text" class="span12"/>
                <label>Фамилия (как на карте):</label>
                <input id="last-name" type="text" class="span12"/>
                <label>Карта:</label>

                <div class="row-fluid">
                    <input type="text" class="span3 card-number-part part-1"/>
                    <input type="text" class="span3 card-number-part part-2"/>
                    <input type="text" class="span3 card-number-part part-3"/>
                    <input type="text" class="span3 card-number-part part-4"/>
                </div>
                <label>Срок действия карты:</label>

                <div class="row-fluid">
                    <input id="card-expires" type="text" class="span4" placeholder="mm/yyyy"/>
                    <i class="icon icon-calendar icon-gray"></i>
                </div>
                <label>CVC:</label>
                <input id="cvv" type="text" class="span4" placeholder="xxx"/>
                <label>Сумма</label>
                <input id="amount" type="text" class="span4" value="${to_pay}"/>
            </form>
        </div>
        <a href="/user/terms_of_payment">Условия оплаты подарков</a>
        <div class="payment-system"></div>
    </div>

    <button id="make-payment" class="btn button" style="margin-top: 15px;">Оплатить</button>
    <img id="loader" class="hidden" src="/img/loader.gif">
</div>
</section>
</body>
</html>	