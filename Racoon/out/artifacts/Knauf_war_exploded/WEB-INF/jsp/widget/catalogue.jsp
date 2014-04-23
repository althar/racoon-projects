<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ol>
<c:forEach items="${goods.get('GoodsItems')}" var="good" varStatus="status">
    <li class="item <c:if test="${status.index%3==2}">reset</c:if>">
        <div class="block">
            <div class="picture-container">
                <a class="b-picture" href="/catalog/item?id=${good.get('Id')}">
                    <img src='http://knauf.ozone.ru/multimedia/${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}' alt="" width="169" height="169">
                </a>
            </div>
            <h3><a href="/catalog/item?id=${good.get('Id')}">${good.get('Name')}</a></h3>
            <em class="price"><span class="color">${good.get('Price')}</span> теплуноса</em>
            <a class="buy btn btn-blue cart-add" data-id="12832197" rel="cart-add" good-id="${good.get('Id')}">
                <div class="add-good-title"><i class="icon-ok icon-white hidden inline" ></i> в корзину</div>
                <div class="add-good-loader hidden"><img src="/img/ajax-loader.gif"></div>
            </a>
        </div>
    </li>
    <c:if test="${status.index%3==2}"></ol><ol></c:if>
</c:forEach>
</ol>