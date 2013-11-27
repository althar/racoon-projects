<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="terms">
    <div class="row-fluid">
        <div class="span12">
            <div class="terms_block">
                <h2>Условия оплаты подарков</h2>

                <h3>1. Определение терминов</h3>

                <p>Исполнитель - лицо, предоставляющее услуги Пользователям.<br/>
                    Пользователь - сторона по договору с Исполнителем, а также третьи лица, не состоящие в договорных
                    отношениях с Исполнителем, но обращающиеся к услугам Исполнителя, либо взаимодействующие с сетью
                    Исполнителя.<br/>
                    Услуги - услуги, предоставляемые Исполнителем Пользователю на основании договора с Исполнителем, а
                    также другие поименованные и непоименованные в настоящих Условиях сетевые сервисы Исполнителя,
                    носящие вспомогательный характер или используемые неограниченным кругом лиц.</p>

                <h3>2. Общие условия пользования услугами</h3>
                <ul class="unstyled">
                    <li>2.1. Ограничения на действия Пользователя</li>
                    <li>2.1.1 Пользователь не вправе совершать действия, которые могут повлечь:</li>
                    <ul class="unstyled">
                        <li>А) нарушение функционирования оборудования и сети Исполнителя;</li>
                        <li>Б) нарушение предоставления услуг Исполнителя или ограничение возможностей
                            других пользователей сети Интернет в их получении;
                        </li>
                        <li>В) несанкционированный доступ к информационно-вычислительным и сетевым ресурсам
                            Исполнителя;
                        </li>
                        <li>Г) причинение, либо угрозу причинения убытков иным Пользователям и любым третьим лицам;</li>
                        <li>Д) введение в заблуждение третьих лиц относительно источника информации (отправителя
                            сообщений любого характера, программ,
                            запросов), если за источник информации выдается Исполнитель, коим он не является.
                        </li>
                    </ul>
                </ul>
            </div>
        </div>
    </div>

    <button class="btn button" style="margin-top: 15px;">Перейти к оплате</button>

</div>
</section>
<div id="cont">
<img src="../../img/line.png">
    <a href="/user/terms_of_service" target="_blank">Политиа конфиденциальности</a>
    <a href="/user/terms_of_payment" target="_blank">Условия пользования сервиса</a>
    <a href="mailto:support@v-skladchinu.ru">support@v-skladchinu.ru</a>
    <span></span>

    <div style="text-align:center;">
    <img src="../../img/pay.png" style="margin-top:10px;">
    </div>
</div>
</body>
</html>	