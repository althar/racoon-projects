<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="basket">
    <h1>Ваша Корзина</h1>
    <table class="products">
        <tbody>
        <tr>
            <th colspan="2"></th>
            <th align="left">Название товара</th>
            <th>Цена</th>
            <th>Количество</th>
            <th>Сумма</th>
        </tr>
        <tr>
            <td colspan="6">
                <hr class="separation">
            </td>
        </tr>
        <c:forEach items="${cart.get('CartItems')}" var="good">
            <tr class="cell-product" rel="item-8122266">
                <td class="delete">
                    <span class="button cart-delete" good-id="${good.get('ItemId')}" rel="cart-delete"></span>
                </td>
                <td class="product">
                    <a class="b-picture fancybox-gallery" href="${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}" rel="thumbnails">
                        <img src="${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}" alt="">
                    </a>
                </td>
                <td class="name">
                    <a href="/catalog/item/8122266/">${good.get('Name')}</a>
                </td>
                <td class="price">
                    <em class="style-prices">
                        <span class="color">${good.get('Price')}</span>
                        теплуноса
                    </em>
                </td>
                <td class="sum">
                    <input type="number" class="good-quantity" rel="quantity" good-id="${good.get('ItemId')}" min="1" value="${good.get('Quantity')}">
                </td>
                <td class="total">
                    <em class="style-prices">
                        <span class="color">${good.get('Price')*good.get('Quantity')}</span>
                        теплуноса
                    </em>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <hr class="separation">
                </td>
            </tr>
        </c:forEach>

        <%-- Goods not available --%>
        <c:forEach items="${cart.get('ProposalCartItems')}" var="good">
            <tr class="cell-product" rel="item-8122266" style="opacity:0.35;">
                <td class="delete">
                    <span class="button cart-delete" good-id="${good.get('ItemId')}" rel="cart-delete"></span>
                </td>
                <td class="product">
                    <a class="b-picture fancybox-gallery" href="${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}" rel="thumbnails">
                        <img src="${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}" alt="">
                    </a>
                </td>
                <td class="name">
                    <a href="/catalog/item/">${good.get('Name')}<br><p style="color: red;">Нет в наличии.</p></a>
                </td>
                <td class="price">
                    <em class="style-prices">
                        <span class="color">${good.get('Price')}</span>
                        теплуноса
                    </em>
                </td>
                <td class="sum">
                    <input type="number" class="good-quantity" rel="quantity" good-id="${good.get('ItemId')}" min="1" value="${good.get('Quantity')}">
                </td>
                <td class="total">
                    <em class="style-prices">
                        <span class="color">${good.get('Price')*good.get('Quantity')}</span>
                        теплуноса
                    </em>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <hr class="separation">
                </td>
            </tr>
        </c:forEach>

        <tr class="total-price">
            <td colspan="4"></td>
            <td align="right">Всего</td>
            <td>
                <em class="style-prices">
                    <span class="color" id="cart_summary_price">${cart.get('CartSummary').get('FullSum')}</span>
                    теплуноса
                </em>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="order">
        <div class="buttons">
            <c:choose>
                <c:when test="${cart.get('CartSummary').get('FullSum')>amount}">
                    <b style="color: red; font-size: large;">Недостаточно средств. Необходимо
                        <a href="/certificate" style="text-decoration: underline;">активировать сертификат.</a>
                    </b>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-blue" href="/order/">Купить</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>