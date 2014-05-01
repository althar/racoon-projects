<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <div class="container section dark-title">

        <div class="boxed-group">
            <h2>Оформление заказа · Подтверждение</h2>

            <div class="boxed-group-inner">
                <form class="order-confirm" method="post" action="/order/done">
                    <c:if test="${delivery_summ>=0}">
                        <input type="hidden" name="guid" value="${guid}">
                        <input type="hidden" name="address_id" value="${address_id}">
                        <input type="hidden" name="delivery_variant_id" value="${delivery_variant_id}">
                        <input type="hidden" name="first_name" value="${first_name}">
                        <input type="hidden" name="last_name" value="${last_name}">
                        <input type="hidden" name="addressee" value="${addressee}">
                        <input type="hidden" name="phone" value="${phone}">
                        <input type="hidden" name="comment" value="${comment}">

                        <dl class="dl-horizontal">
                            <dt>Способ доставки</dt>
                            <dd>${delivery_name}<strong> (${delivery_summ}</strong> теплуноса)</dd>
                            <dt>Получатель</dt>
                            <dd>${addressee}</dd>
                            <dt>Адрес доставки</dt>
                            <dd> ${address} </dd>
                            <dt>Телефон</dt>
                            <dd>${phone}</dd>
                            <dt>Комментарий к заказу</dt>
                            <dd>${comment}</dd>
                            <dt></dt>
                            <dd>&nbsp;</dd>
                            <dt></dt>
                            <dd><input class="btn btn-blue" type="submit" value="Разместить заказ" name="submit"></dd>
                        </dl>
                    </c:if>
                    <c:if test="${delivery_summ<0}">
                        Не достаточно средств (на товары с доставкой). Вернитесь <a href="/basket">в корзину.</a>
                    </c:if>
                </form>
            </div>
        </div>

    </div>
</div>