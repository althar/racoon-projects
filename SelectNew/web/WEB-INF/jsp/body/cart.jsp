<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="utf-8">
<%@ include file="/WEB-INF/jsp/section/head.jsp" %>
<body>
<section class="main">
    <%@ include file="/WEB-INF/jsp/section/header.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/main_menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/banner.jsp" %>
    <div class="main-content">
        <div class="main-content_inner">
            <div class="cart">
                <div class="cart_inner">
                    <h1>Корзина</h1>

                    <table class="cart-list_items">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox">
                            </th>
                            <th>Наименование</th>
                            <th class="cart_item-price">Цена</th>
                            <%--<th>Количество</th>--%>
                            <%--<th class="cart_item-price">Сумма</th>--%>
                            <%--<th class="cart_item-percent">Скидка</th>--%>
                            <%--<th class="cart_item-price">Сумма скидки</th>--%>
                            <%--<th class="cart_item-price">Всего</th>--%>
                            <th class="cart_item-price"></th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="3" class="cart_item-total">
                                <p>
                                    Итого
                                </p>
                            </td>
                            <td class="cart_item-price cart_item-total">
                                <p>
                                    ${basket.getTotalPrice()} руб.
                                </p>
                            </td>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${basket.goods}" var="good">
                            <tr class="cart_item">
                                <td>
                                    <input type="checkbox">
                                </td>
                                <td class="cart_item-name">
                                    <p>
                                            ${good.value.getStringValue('name')}
                                    </p>
                                </td>
                                <td class="cart_item-price">
                                    <p>
                                            ${good.value.getDoubleValue('sell_price')} руб.
                                    </p>
                                </td>
                                    <%--<td class="cart_item-quantity">--%>
                                    <%--<p>--%>
                                    <%--<input type="number" max="20" min="1" value="1">--%>
                                    <%--</p>--%>
                                    <%--</td>--%>
                                    <%--<td class="cart_item-price">--%>
                                    <%--<p>--%>
                                    <%--${good.value.getDoubleValue('sell_price')} руб.--%>
                                    <%--</p>--%>
                                    <%--</td>--%>
                                    <%--<td class="cart_item-percent">--%>
                                    <%--<p>--%>
                                    <%--0%--%>
                                    <%--</p>--%>
                                    <%--</td>--%>
                                    <%--<td class="cart_item-price">--%>
                                    <%--<p>--%>
                                    <%--0 руб.--%>
                                    <%--</p>--%>
                                    <%--</td>--%>
                                    <%--<td class="cart_item-price">--%>
                                    <%--<p>--%>
                                    <%--${good.value.getDoubleValue('sell_price')} руб. руб.--%>
                                    <%--</p>--%>
                                    <%--</td>--%>
                                <td class="cart_item-price">
                                    <p>
                                        <button class="button button-small">Удалить</button>
                                    </p>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="create-order">
                <div class="create-order_inner">
                    <%--<h2>Оформление заказа</h2>--%>

                    <form class="create-order_form" action="/cart/make_order" method="post" accept-charset="UTF-8">
                        <div class="control-group">
                            <label class="control-label">Имя</label>

                            <div class="controls">
                                <input type="text" value="" name="name">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Телефон</label>

                            <div class="controls">
                                <input type="text" value="" name="phone">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Город</label>

                            <div class="controls">
                                <input type="text" value="" name="city">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Улица, номер дома</label>

                            <div class="controls">
                                <input type="text" value="" name="address">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Комментарий к заказу</label>

                            <div class="controls">
                                <textarea rows="3" name="comment"></textarea>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="submit" class="button blue">Оформить заказ</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>