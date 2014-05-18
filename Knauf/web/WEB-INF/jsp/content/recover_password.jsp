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

    <ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item"><a href="/my/" class="link">Личный кабинет</a><span class="arrow"></span></li><li class="item">Восстановление пароля</li></ul>
    <div class="container section dark-title">

        <hr class="separation">
        <div class="container white">
            <div class="registration form section dark-title">
                <h2>Восстановление пароля</h2>
                <c:choose>
                    <c:when test="${result=='success'}">
                        <h3 style="color: #009496; text-align: center;">Пароль отправлен вам на почту!</h3>
                    </c:when>
                    <c:when test="${result=='no_email'}">
                        <h3 style="color: #b22222; text-align: center;">Пользователя с таким email не существует</h3>
                    </c:when>
                    <c:otherwise>
                        <form action="/service/recover_password" class="form-centered" method="post">
                            <div class="control-group">
                                <label class="control-label" for="email_field">Email</label>
                                <div class="controls">
                                    <input required="" type="email" id="email_field" class="input-block-level" name="email" tabindex="1">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <button class="btn btn-blue btn-primary" type="submit" name="submit" tabindex="2">Начать процедуру восстановления</button>
                                </div>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

    </div>
</div>