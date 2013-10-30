<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<header class="main-header">
    <nav class="main-header_inner row">
        <h1 class="main-header_logo">
            <a href="" class="link_type-logotype">Select</a>
            <span class="logotype_descr">Магазин европейской сантехники</span>
        </h1>
        <div class="main-header_contacts">
            <h2 class="title_type-contact">Заказ в интернет-магазине</h2>
            <div class="contact-phones">
                <p>
                    <strong>+7 499 390-73-51</strong> Пн-Пт  с 8.00 - 22.00
                </p>
                <p>
                    <strong>8 800 245-74-21</strong> Сб-Вс с 8.00 - 18.00
                </p>
            </div>
        </div>
    </nav>
    <nav class="main-header_inner row">
        <form class="main-header_search">
            <input type="search" class="search-field" placeholder="более 3000 наименований">
            <button type="submit" class="button search-button">Найти</button>
        </form>
        <div class="main-header_cart">
            <button class="button blue cart-button"><i class="icon cart"></i> Моя корзина</button>
        </div>
    </nav>
</header>