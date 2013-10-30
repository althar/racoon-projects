<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content">
    <script src="/js/logic/basket.js"></script>
    <div class="content-inner">
        <c:choose>
            <c:when test="${basket.isEmpty()}">Корзина пуста. <a href="/Каталог/"><b>Перейти в каталог.</b></a></c:when>
            <c:otherwise>
            <div id="cart">
                <button class="button green pull-right" onclick="window.location='/Корзина/Авторизация'">оформить
                    заказ
                </button>
                <div class="clearfix"></div>
                <table class="cartItems">
                    <colgroup>
                        <col style="width: 160px">
                        <col>
                        <col style="width: 140px">
                        <col style="width: 180px">
                        <col style="width: 140px">
                    </colgroup>
                    <thead>
                    <tr>
                        <th colspan="2"></th>
                        <th class="right">Цена за ед.</th>
                        <th class="itemQty">Кол-во</th>
                        <th class="right total">Общая стоимость</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${basket.goods}" var="goodEntry">
                        <c:set var="good" value="${goodEntry.value}"></c:set>
                        <tr class="itemRow" good-id="${good.getLongValue('id')}">
                            <td>
                                <a class="itemImage" href="/Товар?id=${good.getLongValue('id')}"><img src="${good.getStringValue('good_image')}"
                                                                  height='130' good-id="${good.getLongValue('id')}"></a>
                            </td>
                            <td class="itemShipping">
                                <div class="tableRowTop">
                                    <a class="itemTitle"
                                       href="/Каталог/Товар?id=${good.getLongValue('id')}">${good.getStringValue('name')}</a>

                                    <p class="artNum">Артикул: ${good.getStringValue('article')}</p>
                                </div>
                                <div class="tableRowBottom">
                                        <%--<span><i class="icon icon-pickup"></i> Возможен самовывоз</span>--%>
                                </div>
                            </td>
                            <td class="right">
                                <div class="tableRowTop">
                                    <span><span class="reg"><span class="regPrice">${good.getDoubleValue('sell_price')} руб.</span></span></span>
                                </div>
                            </td>
                            <td>
                                <div class="tableRowTop">
                                    <div>
                                        <input type="text" pattern="[0-9]*" maxlength="2" class="good-count-input"
                                               good-id="${good.getLongValue('id')}" value="${good.count}">
                                    </div>
                                </div>
                            </td>
                            <td class="right">
                                <div class="tableRowTop">
                                    <p class="totalPrice total">${good.count*good.getDoubleValue('sell_price')} руб.</p>
                                </div>
                                <div class="tableRowBottom">
                                    <a class="blue underline delete-good" href="" good-id="${good.getLongValue('id')}">Удалить</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="cartTotal">
                    <div class="cartTotalsInner">
                        <p class="summaryLine orderTotalSum">
                            <label for="orderTotalSum">Стоимость всех товаров:</label>
			                    <span class="amount">
			                        <span class="value" id="orderTotalSum">${basket.totalGoodPrice} руб.</span>
			                    </span>
                        </p>
                    </div>
                </div>
                <div class="clearfix"></div>
                <button class="button green pull-right" onclick="window.location='/Корзина/Авторизация'">оформить
                    заказ
                </button>
                <div class="clearfix"></div>
            </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%--<c:when test="${basket.isEmpty()}">Корзина пуста. <a href="/Каталог/">Перейти в каталог.</a></c:when>--%>
<%--<c:otherwise><button class="button green pull-right create-order" onclick="window.location='/Корзина/Авторизация'">оформить заказ</button></c:otherwise>--%>