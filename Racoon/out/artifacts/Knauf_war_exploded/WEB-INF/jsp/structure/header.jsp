<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://racoonsoft.ru/funcs" prefix="fns" %>

    <header class="layout">
        <div class="block">
            <div class="container">
                <%@ include file="/WEB-INF/jsp/widget/basket.jsp" %>

                <form class="point authorization <c:if test="${!anonymous}">hidden</c:if>" action="/auth/login" method="post" id="header_auth_form">
                    <div class="container">
                        <div class="item">
                            <input type="text" placeholder="e-mail" name="login">
                        </div>
                        <div class="item">
                            <input type="password" placeholder="Пароль" name="password">
                            <input type="submit" value="Войти" name="submit" class="btn btn-blue login-button">
                        </div>
                        <ul class="tools">
                            <li class="item">
                                <a href="/registration">Регистрация</a>
                            </li>
                            <li class="item">
                                <a href="/recover_password">Забыли пароль?</a>
                            </li>
                            <c:if test="${not empty login_error}">
                                <li class="item" style="color: red;">
                                    Нет доступа.
                                </li>
                            </c:if>

                        </ul>
                    </div>
                </form>
                <div class="point user-panel <c:if test="${anonymous}">hidden</c:if>">
                    <div class="block">
                        <div class="border">
                            <span class="name"><a href="/profile">${user.getStringValue('first_name')} ${user.getStringValue('last_name')}</a></span>

                            <%--<div class="price">на счету: <span class="color">${amount}</span> теплуноса</div>--%>
                            <div class="price">на счету: <span class="color">${fns:price(amount)}</span> теплуноса</div>

                        </div>
                    </div>
                    <div class="container">
                        &nbsp;
                        <a href="/certificate">активировать сертификат</a>
                        <a class="btn btn-blue logout" href="/auth/logout">Выйти</a>
                    </div>
                </div>
            </div>
            <a class="logo" href="/"><img src="/img/bg/logo.png" alt="Теплоклуб" width="373" height="125"></a>
        </div>

        <div class="container navigation">
            <form class="search" action="/catalogue/items" method="get">
                <input type="text" value="" name="search" placeholder="Поиск по каталогу">
                <input type="hidden" value="Результат" name="title">
                <input type="submit" value="Найти" class="button-blue">
            </form>
            <nav>
                <ul class="items">
                    <li class="item">
                        <a class="link" href="/help_order/">Как заказать</a>
                    </li>
                    <li class="item">
                        <a class="link" href="/help_payment/">Оплата</a>
                    </li>
                    <li class="item">
                        <a class="link" href="/help_delivery/">Доставка</a>
                    </li>
                </ul>
            </nav>
        </div>
    </header>