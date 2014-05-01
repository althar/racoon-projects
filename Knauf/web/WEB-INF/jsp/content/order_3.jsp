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
            <h2>Оформление заказа · Получатель и адрес доставки</h2>
            <div class="boxed-group-inner">
                <p>Товары: ${cart.get('CartSummary').get('FullSum')} теплуноса · Доставка: ${delivery_price} теплуноса</p>
                <form class="form-horizontal" method="post" action="/order/confirm">
                    <input type="hidden" name="guid" value="${guid}">
                    <input type="hidden" name="area_id" value="${area_id}">
                    <input type="hidden" name="delivery_variant_id" value="${delivery_variant_id}">
                    <input type="hidden" name="delivery_point_address_id" value="${delivery_point_address_id}">
                    <h4>Получатель</h4>
                    <div class="control-group">
                        <label class="control-label required" for="lastName_field">Фамилия</label>
                        <div class="controls">
                            <input required="" type="text" name="last_name" id="lastName_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label required" for="firstName_field">Имя</label>
                        <div class="controls">
                            <input required="" type="text" name="first_name" id="firstName_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label required" for="middleName_field">Отчество</label>
                        <div class="controls">
                            <input required="" type="text" name="middle_name" id="middleName_field" value="">
                        </div>
                    </div>

                    <h4>Адрес доставки</h4>
                    <div class="control-group">
                        <label class="control-label " for="zipcode_field">Индекс</label>
                        <div class="controls">
                            <input type="text" name="zip" id="zipcode_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="country_field">Страна</label>
                        <div class="controls">
                            <input type="text" name="country" id="country_field" value="Россия">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="region_field">Регион</label>
                        <div class="controls">
                            <input type="text" name="region" id="region_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="district_field">Район</label>
                        <div class="controls">
                            <input type="text" name="district" id="district_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="city_field">Город</label>
                        <div class="controls">
                            <input type="text" name="city" id="city_field" value="МОСКВА">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="addressee_field">Получатель</label>
                        <div class="controls">
                            <input type="text" name="addressee" id="addressee_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="addressTail_field">Улица, квартира</label>
                        <div class="controls">
                            <input type="text" name="address" id="addressTail_field" value="ул. Скаковая, д.36" readonly="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label required" for="phone_field">Телефон</label>
                        <div class="controls">
                            <input required="" type="text" name="phone" id="phone_field" value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="comment_field">Комментарий</label>
                        <div class="controls">
                            <textarea name="comment" id="comment_field"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <input class="btn btn-blue" type="submit" name="submit" value="Далее...">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>