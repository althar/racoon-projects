<%@ page import="java.util.Calendar" %>
<%@ page import="racoonsoft.library.helper.Helper" %>
<%@ page import="racoonsoft.library.helper.StringHelper" %>
<%@ page import="racoonsoft.knauf.service.OzonService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://racoonsoft.ru/funcs" prefix="fns" %>
<script type="text/javascript" src="/js/logic/catalogue.js"></script>
<div class="content layout">
    <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
        <c:if test="${attribute.get('Name')=='Название'}">
            <c:set var="name" value="${attribute.get('Value')}"/>
        </c:if>
        <c:if test="${(attribute.get('Name')=='Изображения'||attribute.get('Name')=='Изображение')&&(not image_set)}">
            <c:set var="image" value="http://static.ozone.ru/multimedia/${attribute.get('Value')}"/>
            <c:set var="image_set"  value="true"/>
        </c:if>
    </c:forEach>
    <div class="container ">

        <hr class="separation">
        <div class="container white">
            <div class="profile-goods">
                <div class="path-left">
                    <div class="picture-container">
                        <a class="fancybox-gallery block-picture" href="${image}" rel="thumbnails">
                            <img src="${image}" alt="">
                        </a>
                    </div>
                    <ul class="inline item-images">
                        <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
                            <c:if test="${attribute.get('Name')=='Изображения'||attribute.get('Name')=='Изображение'}">
                                <c:set var="image"  value="http://static.ozone.ru/multimedia/${attribute.get('Value')}"/>
                                <li><a class="fancybox-gallery" href="${image}" rel="thumbnails"><img src="${image}" alt=""></a></li>
                            </c:if>
                        </c:forEach>
                        <%--<li><a class="fancybox-gallery" href="${image}" rel="thumbnails"><img src="${image}" alt=""></a></li>--%>
                    </ul>

                    <div class="price"><span class="color">${fns:price(good_short.get('Item').get('Price'))} </span> теплуносов</div>
                    <a class="buy btn btn-blue cart-add" rel="cart-add" good-id="${good_short.get('Item').get('Id')}">
                        <div class="add-good-title"><i class="icon-ok icon-white hidden inline" ></i> в корзину</div>
                        <div class="add-good-loader hidden"><img src="/img/ajax-loader.gif"></div>
                    </a>
                    <%--<a class="buy btn btn-blue cart-add" data-id="8154373" rel="cart-add">в корзину</a>--%>
                </div>
                <div class="info">
                    <section class="title">
                        <h1>${name}</h1>

                        <%--good_short.get('Item').get('Annotation')--%>
                        <%--${fn:replace(good_short.get('Item').get('Annotation'),'\\\\', '')}--%>
                        <c:set var="annotation" value="${good_short.get('Item').get('Annotation')}"></c:set>
                        <p class="intro"><%=StringHelper.normalizeOzonString(pageContext.getAttribute("annotation"))%>
                            <br><br>
                            <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
                                <c:set var="attr_value" value="${attribute.get('Value')}"></c:set>
                                <c:choose>
                                    <c:when test="${attribute.get('Name')=='Название'}"></c:when>
                                    <c:when test="${attribute.get('Name')=='Изображения'}"></c:when>
                                    <c:when test="${not empty attribute.get('Name')}"></c:when>
                                    <c:otherwise>
                                        <%--${attribute.get('Name')} Foz: !!!<%StringHelper.getCharEncodedString(pageContext.getAttribute("attr_value").toString());%> <br>--%>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </p>
                    </section>
                    <section class="features">
                        <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
                            <c:if test="${attribute.get('Name')=='Характеристики'}">
                                <h2>Характеристики</h2>
                                <ul>
                                    <c:forEach items="${attribute.get('Detail').get('ClassAttributes')}" var="inner_attribute">
                                        <c:set var="attr_value_inner" value="${inner_attribute.get('Value')}"></c:set>
                                        <c:if test="${not empty attr_value_inner}">
                                        <li class="item cleared">
                                            <span class="title">${inner_attribute.get('Name')}</span>
                                            <%--<c:set var="inner_attribute" value="${fn:replace(inner_attribute,'\\\\u000d', '')}"></c:set>--%>
                                            <div class="description"><%=StringHelper.normalizeOzonString(pageContext.getAttribute("attr_value_inner").toString())%></div>
                                        </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </c:forEach>
                    </section>
                    <div class="warning">
                        <p>Информация о технических характеристиках, комплекте поставки, стране изготовления и внешнем виде товара носит справочный характер и основывается на последних доступных к моменту публикации сведениях.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>