<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://racoonsoft.ru/funcs" prefix="fns" %>
<div class="point basket">
    <span class="title"><a href="/basket">Ваша Корзина</a></span>
    <table>
        <tbody><tr>
            <td></td>
            <td></td>
        </tr>
        <tr class="product">
            <td>товаров:</td>
            <td class="sum"><span id="cart_items">
                            <c:if test="${not empty cart.get('CartItems')}">${cart.get('CartItems').size()}</c:if>
                            <c:if test="${empty cart.get('CartItems')}">0</c:if>
                            </span></td>
        </tr>
        <tr class="price">
            <td>на сумму:</td>
            <td class="sum"><span id="cart_price">
                                <c:if test="${not empty cart.get('CartSummary').get('FullSum')}">
                                    ${fns:price(cart.get('CartSummary').get('FullSum'))}
                                </c:if>
                                <c:if test="${empty cart.get('CartSummary').get('FullSum')}">0.0</c:if>
                                </span> <span id="cart_unit">теплуносов</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
        </tbody></table>
</div>