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

    <ul class="breadcrumbs">
        <li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li>
        <li class="item"><a href="/profile" class="link">Личный кабинет</a><span class="arrow"></span></li>
        <li class="item">Изменение личных данных</li></ul>
    <div class="container section dark-title">

        <div class="boxed-group">
            <h2>Изменение личных данных</h2>
            <div class="boxed-group-inner">

                <form action="/service/change_info" class="form-horizontal" method="post">
                    <div class="control-group">
                        <label class="control-label" for="first_name_field">Имя</label>
                        <div class="controls">
                            <input type="text" id="first_name_field" name="first_name" value="${user.getStringValue('first_name')}" tabindex="1">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="last_name_field">Фамилия</label>
                        <div class="controls">
                            <input type="text" id="last_name_field" name="last_name" value="${user.getStringValue('last_name')}" tabindex="2">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email_field">Email (логин)</label>
                        <div class="controls">
                            <input required="" type="email" id="email_field" name="email" value="${user.getStringValue('login')}" tabindex="7">
                        </div>
                    </div>
                    <div class="well passwords-container">
                        <div class="control-group">
                            <label class="control-label" for="password_field">Новый пароль</label>
                            <div class="controls">
                                <input type="password" id="password_field" name="password" tabindex="8" value="${user.getStringValue('password')}">
                            </div>
                        </div>
                        <%--<div class="control-group">--%>
                            <%--<label class="control-label" for="password_confirmation_field">Повторите новый пароль</label>--%>
                            <%--<div class="controls">--%>
                                <%--<input type="password" id="password_confirmation_field" name="password_confirmation" tabindex="9">--%>
                                <%--<span class="help-block">Оставьте поля пустыми, если не хотите менять пароль</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-blue" type="submit" name="submit" tabindex="10">Сохранить изменения</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>