<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://racoonsoft.ru/funcs" prefix="fns" %>

<script type="text/javascript" src="/js/logic/catalogue.js"></script>
<li class="section">
    <h5 class="title">
        до <span class="color">500</span> теплуносов
        &nbsp; <a href="/catalogue/by_price?catalogue=${catalogue_name}&price=0-500&title=${title}">все в этой категории</a>
    </h5>
    <ol>
        <c:forEach items="${goods500.get('Items')}" var="good" varStatus="status">
            <li class="item <c:if test='${status.index==3}'> reset</c:if>">
                <div class="block">
                    <div class="picture-container">
                        <a class="b-picture" href="/good?id=${good.get('Id')}">
                            <img src='http://knauf.ozone.ru/multimedia/${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}' alt="" width="169" height="169">
                        </a>
                    </div>
                    <h3><a href="/catalog/item?id=${good.get('Id')}">${good.get('Name')}</a></h3>
                    <em class="price"><span class="color">${fns:price(good.get('Price'))}</span> теплуноса </em>
                    <a class="buy btn btn-blue cart-add" data-id="12832197" rel="cart-add" good-id="${good.get('Id')}">
                        <div class="add-good-title"><i class="icon-ok icon-white hidden inline" ></i> в корзину</div>
                        <div class="add-good-loader hidden"><img src="/img/ajax-loader.gif"></div>
                    </a>
                </div>
            </li>
        </c:forEach>
    </ol>
</li>

<li class="section">
    <h5 class="title">от <span class="color">500</span>
         до <span class="color">1000</span> теплуносов
        &nbsp; <a href="/catalogue//by_price?catalogue=${catalogue_name}&price=500-1000&title=${title}">все в этой категории</a>
    </h5>
    <ol>
        <c:forEach items="${goods1000.get('Items')}" var="good" varStatus="status">
            <li class="item <c:if test='${status.index==3}'> reset</c:if>">
                <div class="block">
                    <div class="picture-container">
                        <a class="b-picture" href="/good?id=${good.get('Id')}">
                            <img src='http://knauf.ozone.ru/multimedia/${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}' alt="" width="169" height="169">
                        </a>
                    </div>
                    <h3><a href="/catalog/item?id=${good.get('Id')}">${good.get('Name')}</a></h3>
                    <c:set var="price" value="${good.get('Price')}"></c:set>
                    <em class="price"><span class="color">${fns:price(good.get('Price'))}</span> теплуноса </em>
                    <a class="buy btn btn-blue cart-add" data-id="12832197" rel="cart-add" good-id="${good.get('Id')}">
                        <div class="add-good-title"><i class="icon-ok icon-white hidden inline" ></i> в корзину</div>
                        <div class="add-good-loader hidden"><img src="/img/ajax-loader.gif"></div>
                    </a>
                </div>
            </li>
        </c:forEach>
    </ol>
</li>

<li class="section">
    <h5 class="title">от <span class="color">1000</span>
        до <span class="color">2000</span> теплуносов
        &nbsp; <a href="/catalogue//by_price?catalogue=${catalogue_name}&price=1000-2000&title=${title}">все в этой категории</a>
    </h5>
    <ol>
        <c:forEach items="${goods2000.get('Items')}" var="good" varStatus="status">
            <li class="item <c:if test='${status.index==3}'> reset</c:if>">
                <div class="block">
                    <div class="picture-container">
                        <a class="b-picture" href="/good?id=${good.get('Id')}">
                            <img src='http://knauf.ozone.ru/multimedia/${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}' alt="" width="169" height="169">
                        </a>
                    </div>
                    <h3><a href="/catalog/item?id=${good.get('Id')}">${good.get('Name')}</a></h3>
                    <c:set var="price" value="${good.get('Price')}"></c:set>
                    <em class="price"><span class="color">${fns:price(good.get('Price'))}</span> теплуноса </em>
                    <a class="buy btn btn-blue cart-add" data-id="12832197" rel="cart-add" good-id="${good.get('Id')}">
                        <div class="add-good-title"><i class="icon-ok icon-white hidden inline" ></i> в корзину</div>
                        <div class="add-good-loader hidden"><img src="/img/ajax-loader.gif"></div>
                    </a>
                </div>
            </li>
        </c:forEach>
    </ol>
</li>

<li class="section">
    <h5 class="title">
        свыше <span class="color">1000</span> теплуносов
        &nbsp; <a href="/catalogue//by_price?catalogue=${catalogue_name}&price=2000-50000&title=${title}">все в этой категории</a>
    </h5>
    <ol>
        <c:forEach items="${goodsExpensive.get('Items')}" var="good" varStatus="status">
            <li class="item <c:if test='${status.index==3}'> reset</c:if>">
                <div class="block">
                    <div class="picture-container">
                        <a class="b-picture" href="/good?id=${good.get('Id')}">
                            <img src='http://knauf.ozone.ru/multimedia/${fn:replace(fn:replace(good.get("Path"),"gif", "jpg"),"small","c200")}' alt="" width="169" height="169">
                        </a>
                    </div>
                    <h3><a href="/catalog/item?id=${good.get('Id')}">${good.get('Name')}</a></h3>
                    <c:set var="price" value="${good.get('Price')}"></c:set>
                    <em class="price"><span class="color">${fns:price(good.get('Price'))}</span> теплуноса </em>
                    <a class="buy btn btn-blue cart-add" data-id="12832197" rel="cart-add" good-id="${good.get('Id')}">
                        <div class="add-good-title"><i class="icon-ok icon-white hidden inline" ></i> в корзину</div>
                        <div class="add-good-loader hidden"><img src="/img/ajax-loader.gif"></div>
                    </a>
                </div>
            </li>
        </c:forEach>
    </ol>
</li>
