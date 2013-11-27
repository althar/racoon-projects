<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="find-friends">
    <p>Выбери друзей, которым интересно скинуться тебе на подарок:</p>

    <div class="row-fluid">
        <div class="span12">
            <div class="friends_block">
                <img class="friends-img" src="../../img/friends.jpg"/>
            </div>
        </div>
    </div>
    <div class="pull-right">
        <div class="input-prepend button-prepend">
            <span class="add-on"><i class="icon icon-vk"></i></span>
            <button class="btn button">Выбрать друзей VK</button>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
</section>
<div id="cont">
<img src="../../img/line.png">
    <a href="/user/terms_of_service" target="_blank">Политиа конфиденциальности</a>
    <a href="/user/terms_of_payment" target="_blank">Условия пользования сервиса</a>
    <a href="mailto:support@v-skladchinu.ru">support@v-skladchinu.ru</a>
    <span></span>
    <a href="http://www.newmethod.ru" target="_blank">Сделано в Newmethod</a>
</div>
</body>
</html>	