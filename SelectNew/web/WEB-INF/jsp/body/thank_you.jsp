<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="utf-8">
<%@ include file="/WEB-INF/jsp/section/head.jsp" %>
<body>
<section class="main">
    <%@ include file="/WEB-INF/jsp/section/header.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/main_menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/banner.jsp" %>
    <div class="main-content">
        <div class="main-content_inner">
            Большое спасибо за заказ (№ ${order_id}). Очень скоро с вами свяжется наш оператор.
            <br>
            <b><strong>После доставки <a href="http://market.yandex.ru/shop/163489/reviews/add?cmid=GjdE0ar3Z7MEwgH6SdB8gA&retpath=http%3A%2F%2Fmarket.yandex.ru%2Foffers.xml">поставьте нашему магазину 5 звезд на яндекс маркете</a> и оставьте хороший подробный отзыв и получите 300 рублей на ваш мобильный телефон.</strong>
            Удачи!</b>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>