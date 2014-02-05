<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="dialog-message" class="hidden" title="Внимание">
    <p>
        Уверены?
    </p>
</div>
<header class='main_header'>
    <div class='top_header'>
        <div class='top_header-inner row'>
            <div class='logotype_block small-4 column'>
                <h1 class='logotype'>
                    <a class="link_type-logo" href="/">Language
                        <span>Box</span>
                    </a>

                </h1>
            </div>
            <div class='log_in column push-4'>
                <ul class='top_menu inline-list right'>
                    <li>
                        <span class='top_menu-text'>${user.getStringValue('name')}</span>
                    </li>
                    <li class='divider'>
                        <span class='top_menu-text'></span>
                    </li>
                    <li>
                        <a href="/messages">Сообщения</a>
                    </li>
                    <li class='divider'>
                        <span class='top_menu-text'></span>
                    </li>
                    <li>
                        <a href="/help">Помощь</a>
                    </li>
                    <li class='divider'>
                        <span class='top_menu-text'></span>
                    </li>
                    <li>
                        <a href="/logout">Выход</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <nav class='main_header-menu row'>
        <ul class='main_menu'>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/">Главная</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/service/teacher/courses.html">Курсы</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="#">Ученики</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="#">Магазин курсов</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="#">Настройки</a>
            </li>
        </ul>
    </nav>
</header>