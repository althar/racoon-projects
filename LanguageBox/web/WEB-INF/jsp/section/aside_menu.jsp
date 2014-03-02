<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<aside id="sidebar">
    <nav id="navigation" class="collapse">
        <ul>
            <li <c:if test="${param.page=='main'}">class="active"</c:if>>
                <a href="/" title="Главная">
                    <i class="icon-home"></i>
                    <span class="nav-title">Основное</span>
                </a>
            </li>
            <c:if test="${roles.contains('TUTOR')}">
                <li <c:if test="${param.page=='main_teacher'}">class="active"</c:if>>
                    <a href="/service/teacher" title="Преподаватель">
                        <i class="icon-home"></i>
                        <span class="nav-title">Преподаватель</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${roles.contains('STUDENT')}">
                <li <c:if test="${param.page=='main_student'}">class="active"</c:if>>
                    <a href="/service/student" title="Студент">
                        <i class="icon-home"></i>
                        <span class="nav-title">Студент</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${roles.contains('TUTOR')}">
                <li <c:if test="${param.page=='library'}">class="active"</c:if>>
                    <a href="/service/teacher/courses" title="Библиотека">
                        <i class="icon-list"></i>
                        <span class="nav-title">Библиотека</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${roles.contains('TUTOR')}">
                <li <c:if test="${param.page=='students'}">class="active"</c:if>>
                    <a href="/service/teacher/students" title="Ученики">
                        <i class="icon-users"></i>
                        <span class="nav-title">Ученики</span>
                    </a>
                </li>
            </c:if>
            <li <c:if test="${param.page=='market'}">class="active"</c:if>>
                <a href="/market" title="Магазин курсов">
                    <i class="icon-bag"></i>
                    <span class="nav-title">Магазин</span>
                </a>
                <ul class="inner-nav" style="background: rgb(253, 253, 253); width: 245px;">
                    <li><a href="#"><span class="bold">Все языки</span><span class="right-menu-item-bold">124</span></a>
                    </li>
                    <li class="active">
                        <a href="#"><span class="item-en">Английский</span><span class="right-menu-item">5</span></a>
                    </li>
                    <li>
                        <a href="#"><span class="item-ger">Немецкий</span><span class="right-menu-item">15</span></a>
                    </li>
                    <li>
                        <a href="#"><span class="item-fr">Французкий</span><span class="right-menu-item">55</span></a>
                    </li>
                    <li><a href="#"><span class="item-it">Итальянский</span> <span class="right-menu-item">12</span></a>
                    </li>
                    <li><a href="#"><span class="item-sp">Испанский</span><span class="right-menu-item">4</span></a>
                    </li>
                    <li class="menu-empty"></li>
                    <li class="active">
                        <a href="#"><span class="bold">Все урони</span><span class="right-menu-item-bold">124</span></a>
                    </li>
                    <li><a href="#"><span>Beginner</span><span class="right-menu-item">5</span></a></li>
                    <li><a href="#"><span>Elementary</span><span class="right-menu-item">15</span></a></li>
                    <li><a href="#"><span>Pre-intermediate</span><span class="right-menu-item">55</span></a></li>
                    <li><a href="#"><span>Intermediate</span><span class="right-menu-item">12</span></a></li>
                    <li><a href="#"><span>Upper-intermediate</span><span class="right-menu-item">12</span></a></li>
                    <li><a href="#"><span>Advanced</span><span class="right-menu-item">12</span></a></li>
                </ul>
            </li>
            <c:if test="${roles.contains('TUTOR')||roles.contains('STUDENT')}">
                <li <c:if test="${param.page=='settings'}">class="active"</c:if>>
                    <a href="/profile" title="Профиль">
                        <i class="icon-cogs"></i>
                        <span class="nav-title">Профиль</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${roles.contains('STUDENT')}">
                <li <c:if test="${param.page=='courses'}">class="active"</c:if>>
                    <a href="/service/student/courses" title="Курсы">
                        <i class="icon-list"></i>
                        <span class="nav-title">Курсы</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</aside>