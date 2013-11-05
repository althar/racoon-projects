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

            <article class="article">
                <div class="article-inner">

                    <h2>Телефон поддержки</h2>

                    <p>Для Москвы: ${static_data.getStringValue('phone')} Пн-Пт с 8.00 - 22.00 Сб-Вс с 8.00 - 18.00<br>
                        Для регионов: ${static_data.getStringValue('phone')}<br>
                        selectsanteh@gmail.com — для ваших вопросов и предложений</p>

                    <h2>Департамент по закупкам:</h2>

                    <p>selectsanteh@gmail.com<br>
                        ${static_data.getStringValue('phone')} Пн-Пт с 8.00 - 22.00 Сб-Вс с 8.00 - 18.00</p>

                    <h2>Адрес офиса</h2>

                    <p>127220, г. Москва, ул. Полтавская, д.6, кв.94</p>

                    <h2>Реквизиты</h2>

                    <p><strong>ООО «Лоубай»</strong>
                        Юридический и фактический адрес:
                        127220, г. Москва, ул. Полтавская, д.6, кв.94 Почтовый адрес:
                        127220, г. Москва, ул. Полтавская, д.6, кв.94 ИНН/КПП 7714853504 / 771401001 ОГРН 1117746810807
                        р/сч 40702810300005006558 в Московский филиал ООО ИКБ «Совкомбанк» к/сч 30101810700000000967 БИК
                        044583967</p>

                </div>
            </article>

            <div class="map"></div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>