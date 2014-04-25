<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="content layout">
    <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
        <%--<c:set var="salary" scope="session" value="${2000*2}"/>--%>
        <c:if test="${attribute.get('Name')=='Название'}">
            <c:set var="name" scope="session" value="${attribute.get('Value')}"/>
        </c:if>
        <c:if test="${attribute.get('Name')=='Изображения'}">
            <c:set var="image" scope="session" value="'http://static.ozone.ru/multimedia/'+${attribute.get('Value')}"/>
        </c:if>
        <%--<c:if test="${attribute.get('Name')=='Изображения'}">--%>
        <%--<c:set var="Annotation" scope="session" value="${attribute.get('Value')}"/>--%>
        <%--</c:if>--%>
        <%--<c:if test="${attribute.get('Name')=='Изображения'}">--%>
        <%--<c:set var="name" scope="session" value="${attribute.get('Value')}"/>--%>
        <%--</c:if>--%>
        <%--<c:if test="${attribute.get('Name')=='Изображения'}">--%>
        <%--<c:set var="name" scope="session" value="${attribute.get('Value')}"/>--%>
        <%--</c:if>--%>
    </c:forEach>
    <%--<ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item"><a href="/catalog/1/" class="link">Электроника</a><span class="arrow"></span></li><li class="item"><a href="/catalog/1168486/" class="link">Аксессуары</a><span class="arrow"></span></li><li class="item"><a href="/catalog/1160831/" class="link">Карты памяти</a><span class="arrow"></span></li><li class="item">Transcend microSDHC Class 10 32GB + адаптер</li></ul>--%>
    <div class="container ">

        <hr class="separation">
        <div class="container white">
            <div class="profile-goods">
                <div class="path-left">
                    <div class="picture-container">
                        <a class="fancybox-gallery block-picture" href="http://static.ozone.ru/multimedia/spare_covers/1004418023.jpg" rel="thumbnails">
                            <img src="http://knauf.ozone.ru/multimedia/spare_covers/c200/1004418023.jpg" alt="">
                        </a>
                    </div>
                    <ul class="inline item-images">
                        <li><a class="fancybox-gallery" href="${image}" rel="thumbnails"><img src="${image}" alt=""></a></li>
                    </ul>

                    <div class="price"><span class="color">${good_short.get('Price')}</span> теплуносов</div>
                    <a href="/cart/" class="buy btn btn-blue cart-add" data-id="8154373" rel="cart-add">в корзину</a>
                </div>
                <div class="info">
                    <section class="title">
                        <h1>${name}</h1>

                        <p class="intro">${good_short.get('Item').get('Annotation')}
                            <br><br>
                            <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
                                <c:choose>
                                    <c:when test="${attribute.get('Name')=='Название'}"></c:when>
                                    <c:when test="${attribute.get('Name')=='Изображения'}"></c:when>
                                    <c:when test="${not empty attribute.get('Name')}"></c:when>
                                    <c:otherwise>
                                        ${attribute.get('Name')} : ${attribute.get('Value')} <br>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                            <%--Все microSDHC карты прошли строгие тестирования на совместимость и надежность, и имеют ограниченную гарантию от компании Transcend. Каждая карта снабжена встроенным ECC (корректирующим кодом), который отвечает за автоматические обнаружение и устранение ошибок в процессе--%>
                            <%--передачи данных.--%>
                            <%--<br><br>--%>
                            <%--Внимание: перед оформлением заказа, убедитесь в поддержке Вашим электронным устройством карт памяти данного объема.--%>
                            <%--<br>--%>
                            <%--<br>Полная совместимость со стандартами SD 3.0--%>
                            <%--<br>Совместимость класса 10--%>
                            <%--<br>Простота в обращении, работа в режиме plug-and-play--%>
                            <%--<br>Корректирующий код (ECC) для обнаружения и устранения ошибок при передаче информации--%>
                            <%--<br>Возможность внутрисистемного программирования (ISP) для обновления микропрограммы--%>
                            <%--<br>Поддержка автоматического ждущего режима, режима выключенного питания и спящего режима--%>
                            <%--<br>Механическое переключение защиты записи--%>
                            <%--<br>Соответствие стандартам RoHS--%>
                        </p>
                    </section>
                    <section class="features">
                        <c:forEach items="${good.get('Detail').get('ClassAttributes')}" var="attribute">
                            <c:if test="${attribute.get('Name')=='Характеристики'}">
                                <h2>Характеристики</h2>
                                <ul>
                                    <c:forEach items="${attribute.get('Detail').get('ClassAttributes')}" var="inner_attribute">
                                        <li class="item cleared">
                                            <span class="title">${inner_attribute.get('Name')}</span>

                                            <div class="description">${inner_attribute.get('Value')}</div>
                                        </li>
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