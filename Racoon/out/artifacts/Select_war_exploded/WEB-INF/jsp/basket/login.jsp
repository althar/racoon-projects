<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content">
    <script src="/js/logic/login.js"></script>
    <div class="content-inner">
        <div class="create-order">
            <ul class="order-nav">
                <li class="active"><a href="#">авторизация</a></li>
                <li class="disabled"><a href="#">адрес доставки</a></li>
                <%--<li class="disabled"><a href="#">подарочные сертификаты</a></li>--%>
                <li class="disabled"><a href="#">посмотреть заказ</a></li>
            </ul>
            <div id="login">
                <h2>Для оформления заказа,<br/>Вам необходимо авторизоваться</h2>
                <div>
                    <div class="control-group">
                        <label class="control-label" for="phone">Пожалуйста, введите свой номер телефона</label>
                        <div class="controls">
                            <input type="text" name="phone" id="phone-input">
                        </div>
                    </div>
                    <div class="control-group hidden" id="password-div">
                        <label class="control-label authorization hidden" for="password">Вы уже есть в нашей базе покупателей. Введите пароль, указанный при регистрации. <a href="#" id="password-recover">Если вы его забыли, нажмите сюда</a></label>
                        <label class="control-label registration hidden" for="password">Вы новенький в нашем магахине. Пожалуйста, придумайте пароль. Для вас будет создан аккаунт.</label>
                        <div class="controls">
                            <input type="password" name="password" id="password-input">
                            <div id="password-alert"></div>
                        </div>
                    </div>
                    <div class="form-actions hidden">
                        <button type="button" class="button green">продолжить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>