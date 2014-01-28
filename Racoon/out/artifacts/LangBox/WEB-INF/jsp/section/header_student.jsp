<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                        <span class='top_menu-text'>Ульяна</span>
                    </li>
                    <li class='divider'>
                        <span class='top_menu-text'></span>
                    </li>
                    <li>
                        <a href="/service/messages">Сообщения</a>
                    </li>
                    <li class='divider'>
                        <span class='top_menu-text'></span>
                    </li>
                    <li>
                        <a href="/service/help">Помощь</a>
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
                <a class="main_menu-link" href="/service/student">Главная</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/service/market">Магазин курсов</a>
            </li>
            <li class='main_menu-item'>
                <a class="main_menu-link" href="/service/student/profile">Профиль</a>
            </li>
        </ul>
    </nav>
</header>