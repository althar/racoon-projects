<%@ page import="java.util.Calendar" %>
<%@ page import="racoonsoft.library.helper.Helper" %>
<%@ page import="racoonsoft.library.helper.StringHelper" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item">Личный кабинет</li></ul>
    <div class="container section dark-title">

        <div class="boxed-group">
            <h2>Личный кабинет</h2>
            <div class="boxed-group-inner">
                <p>Здравствуйте, ${user.getStringValue('first_name')} ${user.getStringValue('last_name')}!</p>
                <p>На вашем счету ${amount} теплуноса.</p>
                <p><a href="/info">Изменить личные данные и пароль</a></p>
                <p><a href="/certificate">Активировать сертификат</a></p>
                <p><a href="/history">История заказов</a></p>
            </div>
        </div>

    </div>
</div>