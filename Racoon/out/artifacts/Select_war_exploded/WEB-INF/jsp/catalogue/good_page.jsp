<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content">
    <script type="text/javascript" src="/js/logic/good_detail.js"></script>
    <div id="details" class="content-inner">
        <section class="item-details">
            <article class="item-descr">
                <h1>${header1}</h1>
                <a href="/Товар?id=${good.getLongValue('id')}">
                    <h2 class="prodName"><b>${good.getStringValue('name')}</b></h2>

                    <div class="img-container">
                        <img src="${good.bigMainImageUrl}" class="item-img">
                    </div>
                </a>

                <h3>Описание товара</h3>

                <p>
                    ${good.getStringValue('description')}
                </p>
            </article>
            <aside class="item-char">
                <p class="price">${good.getDoubleValue('sell_price')} руб.</p>
                <ul>
                    <c:if test="${not empty good.getStringValue('equipment')}">
                        <li>
                            <p class="label">Комплектация:</p>
                            <span>${good.getStringValue('equipment')}</span>
                        </li>
                    </c:if>
                    <c:forEach items="${good.characteristics}" var="characteristic">
                        <c:if test="${not empty characteristic.value}">
                            <li>
                                <p class="label">${characteristic.key}:</p>
                                <span>${characteristic.value}</span>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
                <button class="button green buy-button" good-id="${good.getLongValue('id')}">Купить</button>
            </aside>
        </section>
        <section class="items">
            <aside>
                <%@ include file="/WEB-INF/jsp/seo/link_tab.jsp" %>
            </aside>
            <c:if test="${fn:length(related) gt 0}">
                <div class="similar-items_wrapper">
                    <h2>Сопутствующие товары</h2>
                    <c:forEach items="${related}" var="relatedGood">
                        <section class="item-details mini">
                            <article class="item-descr">
                                <a href="/Товар?id=${relatedGood.getLongValue('id')}">
                                    <h3 class="prodName"><b>${relatedGood.getStringValue('name')}</b>
                                    </h3>

                                    <div class="img-container">
                                        <img src="${relatedGood.mainImageUrl}" class="item-img">
                                    </div>
                                </a>

                                <h3>Описание товара</h3>

                                <p>
                                        ${relatedGood.getStringValue('description')}
                                </p>
                            </article>
                            <aside class="item-char">
                                <p class="price">${relatedGood.getDoubleValue('sell_price')} руб.</p>
                                <ul>
                                    <c:if test="${not empty relatedGood.getStringValue('equipment')}">
                                        <li>
                                            <p class="label">Комплектация:</p>
                                            <span>${relatedGood.getStringValue('equipment')}</span>
                                        </li>
                                    </c:if>
                                    <c:forEach items="${relatedGood.characteristics}" var="characteristic">
                                        <li>
                                            <p class="label">${characteristic.key}:</p>
                                            <span>${characteristic.value}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <button class="button green buy-button" good-id="${relatedGood.getLongValue('id')}">
                                    Добавить в корзину
                                </button>
                            </aside>
                        </section>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${fn:length(similar) gt 0}">
                <div class="similar-items_wrapper">
                    <h2>Похожие товары</h2>
                    <c:forEach items="${similar}" var="similarGood">
                        <section class="item-details mini">
                            <article class="item-descr">
                                <a href="/Товар?id=${similarGood.getLongValue('id')}">
                                    <h3 class="prodName"><b>${similarGood.getStringValue('name')}</b>
                                    </h3>

                                    <div class="img-container">
                                        <img src="${similarGood.mainImageUrl}" class="item-img">
                                    </div>
                                </a>

                                <h3>Описание товара</h3>

                                <p>
                                        ${similarGood.getStringValue('description')}
                                </p>
                            </article>
                            <aside class="item-char">
                                <p class="price">${similarGood.getDoubleValue('sell_price')} руб.</p>
                                <ul>
                                    <c:if test="${not empty similarGood.getStringValue('equipment')}">
                                        <li>
                                            <p class="label">Комплектация:</p>
                                            <span>${similarGood.getStringValue('equipment')}</span>
                                        </li>
                                    </c:if>
                                    <c:forEach items="${similarGood.characteristics}" var="characteristic">
                                        <li>
                                            <p class="label">${characteristic.key}:</p>
                                            <span>${characteristic.value}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <button class="button green buy-button" good-id="${similarGood.getLongValue('id')}">
                                    Добавить
                                    в корзину
                                </button>
                            </aside>
                        </section>
                    </c:forEach>
                </div>
            </c:if>
        </section>
    </div>
</div>

<div class="homepage-article">
    <div class="content-inner">
        <%@ include file="/WEB-INF/jsp/seo/footer_articles.jsp" %>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>