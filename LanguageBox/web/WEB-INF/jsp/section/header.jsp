<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header class='main_header'>
    <div class='top_header'>
        <div class='top_header-inner row'>
            <div class='logotype_block column'>
                <h1 class='logotype'>
                    <a class="link_type-logo" href="/">Language
                        <span>Box</span>
                    </a>

                </h1>
            </div>
            <div class='contacts_block column'>
                <p>
                    Поддержка: 8-800-457-85-41, +7 (452) 415-74-89
                </p>
            </div>
            <div class='log_in column'>
                <a href="/choose_registration">Регистрация</a>
                <a href="/login">Вход</a>
            </div>
        </div>
    </div>
    <nav class='main_header-menu row'>
        <ul class='main_menu'>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/">Главная</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/functions">Возможности</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/price">Цены</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/about">Компания</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/faq">Вопросы и ответы</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/partners">Кто уже использует</a>
            </li>
        </ul>
    </nav>
</header>