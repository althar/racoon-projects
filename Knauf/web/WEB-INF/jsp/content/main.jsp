<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="content layout">


    <div class="container section dark-title">


        <div class="welcome">
            <h3>Дорогой Друг!</h3>

            <p>Рады приветствовать Вас в клубе охотников за теплуносами. Теперь, продавая теплоизоляционные материалы KNAUF Insulation, Вы имеете возможность в течение всего года выбирать и заказывать понравившиеся подарки из нашего огромного каталога в обмен на честно заработанные теплуносы.</p>

            <p>Чтобы подтвердить свое членство в клубе, необходимо пройти
                <a href="/registration">регистрацию</a> и обязательно ответить на все вопросы анкеты.</p>

            <p>Пожалуйста, не теряйте свой логин и пароль для входа в клуб и не предоставляйте информацию о них третьим лицам.</p>

            <p>Не забудьте ознакомиться с условиями <a href="help_order/">заказа</a> и
                <a href="help_delivery/">доставки</a> подарков на соответствующих разделах сайта.</p>

            <p>О том, как зачислять теплуносы себе на счет, Вас проинформирует по e-mail менеджер KNAUF Insulation во время рассылки сертификатов с накопленными теплуносами.</p>

            <p>Если у Вас возникли вопросы, пожалуйста, напишите нам, мы обязательно ответим в течение нескольких дней.</p>

            <p>Желаем успешных продаж и удачной охоты!</p>
        </div>

        <c:if test="${not login}">
            <h2>Самые популярные товары</h2>
            <ul class="products" id="catalog_items">
                <%@ include file="/WEB-INF/jsp/widget/price_section.jsp" %>
            </ul>
        </c:if>
    </div>

</div>