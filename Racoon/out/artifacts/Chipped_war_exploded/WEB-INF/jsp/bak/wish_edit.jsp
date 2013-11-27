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
        <div class="span3">
        </div>
        <div class="span9">

            <div class="row-fluid">
                <div class="span12">
                    <c:forEach var="good" items="${goods}" varStatus="rowStatus">
                    <div class="product_block">

                            <div class='row-fluid'>
                                <div class='span8'>
                                    <h4>${good.name}</h4>
                                    <img class="product-image-big" src="${good.imageMediumPath}"/>
                                </div>
                                <div class="span4">
                                    <p class="product-text">
                                        Диагональ: 42"<br/>
                                        Разрешение: 1366x768<br/>
                                        Яркость: 450 кд/мІ<br/>
                                        Угол обзора: 176° <br/>
                                        Цвет: черный<br/>
                                        Формат: 16:9<br/>
                                        Экранное меню: Рус<br/>
                                        Телетекст: Есть<br/>
                                        Звук: Стерео NICAM<br/>
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
            <form class="form-inline">
                <label>Дата:</label>
                <input type="text" class="span3 datepicker" id="date-picker">
                <i class="icon icon-calendar"></i>
                <label>Повод:</label>
                <select class="span4" id="reason">
                    <option>День рождения</option>
                    <option>8 марта</option>
                    <option>Новый год</option>
                    <option>Просто так</option>
                </select>
            </form>
            <div class="row-fluid button-bar">
                <div class='span6'>
                    <button class="btn button button-medium" style="z-index: 0;" id="wish-it">Хочу в подарок</button>
                </div>
                <div class='span6'>
                    <button class="btn button button-medium" style="z-index: 0;" id="friend-wish">Подарить другу</button>
                </div>
            </div>
        </div>
    </div>
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