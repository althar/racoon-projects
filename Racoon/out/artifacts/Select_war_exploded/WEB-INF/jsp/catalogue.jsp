<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="catalog" class="content">
<div class="content-inner">
<aside class="sidebar">
    <div class="form-search">
        <input type="search" class="search" placeholder="Искать товар">
    </div>
    <ul class="catalog-menu">
        <c:forEach items="${categories.categories}" var="cat">
            <li class="category-li">
                <a href="/Каталог/${cat.name}?brand=${brand}&page=1">${cat.name}</a>
                <c:if test="${cat.name==category}">

                <ul class="catalog-submenu" style="height: auto">
                    <c:forEach items="${cat.categories}" var="subcat">
                        <li class="subcategory-li">
                            <a href="/Каталог/${cat.name}/${subcat.name}?brand=${brand}&page=1" title="Посмотреть ${cat.name} ${subcat.name}">${subcat.name}</a>
                        </li>
                    </c:forEach>
                </ul>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <h3 class="hidden">Полезные ссылки</h3>
    <ul class="fast-links">
        <li><a href="" class="blue">Аксессуары</a></li>
        <li><a href="" class="blue">Измельчители</a></li>
        <li><a href="" class="blue">Аксессуары</a></li>
        <li><a href="" class="blue">Измельчители</a></li>
    </ul>
    <h3  class="hidden">Полезные ссылки</h3>
    <ul class="fast-links">
        <li><a href="" class="blue">Аксессуары</a></li>
        <li><a href="" class="blue">Измельчители</a></li>
        <li><a href="" class="blue">Аксессуары</a></li>
        <li><a href="" class="blue">Измельчители</a></li>
    </ul>
</aside>
<section class="section">
<header>
    <hgroup>
        <h2>${category}</h2>
        <h3>${subcategory}</h3>
    </hgroup>
</header>
<script type="text/javascript" src="/js/logic/catalogue.js"></script>
<div class="filters">
    <div class="brands hidden">
        <ul>
            <li>
                <a href="#">
                    <img src="/img/brands/brand-1.png" height="50px">
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="/img/brands/brand-2.png" height="50px">
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="/img/brands/brand-3.png" height="50px">
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="/img/brands/brand-1.png" height="50px">
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="/img/brands/brand-2.png" height="50px">
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="/img/brands/brand-3.png" height="50px">
                </a>
            </li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="spec">
        <ul class="pages pull-right">
            <li>Стр.</li>
            <c:forEach begin="1" end="${pages}" var="val">
                <a class="underline" title="${category} ${brand} страница ${val}." href="/Каталог/<c:if test="${not empty category}">${category}/</c:if><c:if test="${not empty subcategory}">${subcategory}</c:if>?<c:if test="${not empty brand}">brand=${brand}&</c:if><c:if test="${not empty val}">page=${val}&</c:if><c:if test="${not empty keywords}">keywords=${keywords}</c:if><c:if test="${not empty id}">id=${id}</c:if>">${val}</a>
            </c:forEach>
        </ul>
        <div class="clearfix"></div>
    </div>
</div>
<div class="threecolumn">
<c:forEach var="good" items="${goods}" varStatus="theCount">
    <div class="column">
        <div class="prodInfo new">
            <h3 class="prodName"><b><a href="#">${good.getStringValue('name')}</a></b></h3>
            <div class="prodImg">
                <img src="${good.mainImageUrl}" height="130" alt='${good.getStringValue("name")}' class="img-stretch">
            </div>
            <div class="prodDetail">
                <div class="description">
                    <p>${good.getStringValue('description')}</p>
                    <ul>
                        <li>
                            <p class="label">Бренд</p>
                            <span>${good.getStringValue('brand')}</span>
                        </li>
                        <li>
                            <p class="label">Артикул</p>
                            <span>${good.getStringValue('article')}</span>
                        </li>
                        <c:forEach var="characteristic" items="${good.characteristics}" varStatus="rowStatus">
                            <li>
                                <p class="label">${characteristic.key}</p>
                                <span>${characteristic.value}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="prodBottom">
                <div class="price pull-right">
                    <span class="finalPrice">${good.getDoubleValue('sell_price')} руб.</span>
                </div>
                <br class="clearfix">

                <div class="controls">
                    <div class="pull-left">
                        <a class="blue good-detail-link" index="${(theCount.count-1) % 3}" good-id="${good.getLongValue('id')}" >Посмотреть</a>
                    </div>
                    <div class="pull-right">
                        <button style="display: none" class="button green">Купить</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <c:if test="${theCount.count % 3 == 0}">
        </div>
        <div class="threecolumn">
    </c:if>
</c:forEach>
<div class="spec">
    <ul class="pages pull-right">
        <li>Стр.</li>
        <c:forEach begin="1" end="${pages}" var="val">
            <a class="underline" title="${category} ${brand} страница ${val}." href="/Каталог/${category}/${subcategory}?brand=${brand}&page=${val}">${val}</a>
        </c:forEach>
    </ul>
    <div class="clearfix"></div>
</div>
</section>
<div class="clearfix"></div>
</div>
</div>
<div class="catalog-article">
    <div class="content-inner">
        <h2>Покупка сантехники с доставкой по Московской области</h2>
        <article>
            <p>
                На данный момент мы принимаем заказы по телефону. Доставку осуществляем на следующий день, посли принятия заказа. Наши спиециалисты обсудят с вами условия оплаты и доставки.
                При первом заказе скидка 10%. Не упустите этот шанс. Горячее предложение по <a href="/Каталог/Аксессуары">продаже аксессуаров</a>
            </p>
            <p>
                В наличии имеются <a href="/Каталог/Смесители"><u>Смесители</u></a>, <a href="/Каталог/Измельчители"><u>измельчители</u></a> и <a href="/Каталог/Мойки"><u>мойки фирмы Zorg</u></a>. В блюжайшее время ассортимен будет расширен в несколько раз. Кроме того заказ-онлайн находится в процессе разработки.
            </p>
        </article>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>