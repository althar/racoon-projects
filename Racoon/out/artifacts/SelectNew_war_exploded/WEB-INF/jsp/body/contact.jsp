<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
                        <p>Для Москвы: +7 (495) 785-72-82 Пн-Пт  с 8.00 - 22.00 Сб-Вс с 8.00 - 18.00<br>
                            Для регионов: 8 (800) 250-08-78<br>
                            feedback@mainbaher.ru — для ваших вопросов и предложений</p>

                        <h2>Отдел контроля качества</h2>
                        <p>quality.control@mainbaher.ru</p>

                        <h2>Отправить вопрос</h2>
                        <form>
                            <div>
                                <label>Имя</label>
                                <input type="text">
                            </div>
                            <div>
                                <label>Телефон</label>
                                <input type="text">
                            </div>
                            <div>
                                <label>Сообщение</label>
                                <input type="text">
                            </div>
                            <button class="button">Отправить</button>
                        </form>

                        <h2>Департамент по закупкам:</h2>
                        <p>procurement@mainbaher<br>
                            +7 (495) 785-72-82 Пн-Пт  с 8.00 - 22.00 Сб-Вс с 8.00 - 18.00</p>

                        <h2>Адрес офиса</h2>
                        <p>Россия, Москва, Варшавское шоссе, д.9, стр.1,
                            корпус «Ряды Солдатенкова», 1 этаж, БЦ «Даниловская мануфактура».</p>

                    </div>
                </article>

                <div class="map"></div>


            </div>
        </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>