<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item"><a href="/my/" class="link">Личный кабинет</a><span class="arrow"></span></li><li class="item">Активация сертификата</li></ul>
    <div class="container section dark-title">
        <div class="container white">
            <form action="/service/activate_certificate" class="activion form section dark-title" method="post">
                <h2>Активация сертификата</h2>
                <div class="container">
                    <p class="info">Пожалуйста, введите 10-значный код, указанный в правом нижнем углу полученного вами сертификата.</p>
                    <div class="item">
                        <label for="code_field">Код активации</label>
                        <input class="input-block-level" type="text" name="code" id="code_field" value="">
                    </div>
                    <input type="submit" name="submit" value="Активировать" class="btn btn-blue">
                    <img src="/img/bg/certificate_activation.png" alt="" width="440" height="311" class="picture">
                </div>
            </form>
        </div>
    </div>
</div>