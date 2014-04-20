<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <ul class="breadcrumbs">
        <li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li>
        <li class="item"><a href="/my/" class="link">Личный кабинет</a><span class="arrow"></span></li>
        <li class="item">Активация сертификата</li>
    </ul>
    <div class="container section dark-title">


        <div class="boxed-group">
            <h2>Активация сертификата</h2>

            <div class="boxed-group-inner">

                <c:if test="${success}">
                    <p>Сертификат успешно активирован!</p>

                    <p>На ваш счет начислено <strong>${amount} теплуносов</strong>.</p>
                </c:if>
                <c:if test="${!success}">
                    <p>Неправильно введен код сертификата.</p>

                    <p><a href="/certificate">Попробовать снова</a></p>
                </c:if>


                <p><a href="/">На главную страницу</a></p>
            </div>
        </div>


    </div>
</div>