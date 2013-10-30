<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content">
    <script src="/js/logic/order.js"></script>
    <div class="content-inner">
        <div class="create-order">
            <ul class="order-nav">
                <li><a href="/Корзина/Авторизация?logout=true">авторизация</a></li>
                <li><a href="/Корзина/Доставка">адрес доставки</a></li>
                <%--<li><a href="/Корзина/Сертификаты">подарочные сертификаты</a></li>--%>
                <li class="active"><a href="#">посмотреть заказ</a></li>
            </ul>
            <div id="order">
                <h2>Подтверждение заказа</h2>
                <div>
                    <table class="shipping-view">
                        <tbody>
                        <tr>
                            <th>адрес</th>
                            <td>${basket.address}</td>
                        </tr>
                        <tr>
                            <th>телефон</th>
                            <td>${user.getStringValue('login')}</td>
                        </tr>
                        <tr>
                            <th>дата доставки</th>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${basket.deliveryDate}" /></td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="item-view">
                        <colgroup>
                            <col style="width: 280px">
                            <col style="width: 80px">
                            <col style="width: 100px">
                        </colgroup>
                        <tbody>
                            <c:forEach items="${basket.goods}" var="goodEntry">
                            <tr>
                                <c:set var="good" value="${goodEntry.value}"></c:set>
                                <td><span class="itemTitle">${good.getStringValue('name')}</span></td>
                                <td><span class="quantity">Кол-во: ${good.count}</span></td>
                                <td class="right"><span class="itemPrice">${good.getDoubleValue('sell_price')} руб.</span></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                        <tbody>
                        <tr>
                            <td colspan="2">Стоимость доставки</td>
                            <td class="right">${basket.deliveryPrice} руб.</td>
                        </tr>
                        <tr>
                            <td colspan="2">Скидка по сертификатам</td>
                            <td class="right">${basket.totalCertificatesDiscount} руб.</td>
                        </tr>
                        <%--<tr>--%>
                            <%--<td colspan="2">Скидка за первый заказ</td>--%>
                            <%--<td class="right">${basket.firstOrderDiscount} руб.</td>--%>
                        <%--</tr>--%>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="3" class="right"><span class="totalPrice">Итого: ${basket.totalPrice} руб.</span></td>
                        </tr>
                        </tfoot>
                    </table>
                    <div class="form-actions success">
                        <button type="button" class="button green make-order">подтвердить заказ</button>
                        <img src="/img/loader.gif" class="hidden loader">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>