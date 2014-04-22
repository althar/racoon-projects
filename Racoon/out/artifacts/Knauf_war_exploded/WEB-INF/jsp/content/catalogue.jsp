<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="content layout">

<ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item"><a href="/catalog/1/" class="link">Электроника</a><span class="arrow"></span></li><li class="item">Электронные Книги</li></ul>
<div class="container section dark-title">
<h2>
    ${title}
</h2>
<ul class="products" id="catalog_items">
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
            <em class="price"><span class="color">121,8</span> теплуноса</em>
            <a href="/cart/" class="buy btn btn-blue cart-add" data-id="12832197" rel="cart-add">в корзину</a>
        </div>
    </li>
    <c:if test="${status.index%3==2}"></ol><ol></c:if>
</c:forEach>
</ol>
</ul>
</div>
</div>