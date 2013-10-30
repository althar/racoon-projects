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

                        <h2>Курьерская доставка</h2>
                        <p>Сделайте заказ и получите его не выходя из дома или офиса. У службы доставки нет выходных.</p>

                        <h2>Стоимость доставки</h2>
                        <p>Доставка по Москве в пределах МКАД — 350 руб.<br>
                            Стоимость доставки по Московской области 26 руб./км от МКАД + стоимость доставки по Москве.
                            Доставка в другие регионы России осуществляется с помощью услуг транспортной компании Pony Express или почту России с использованием наложенного платежа. По мимо Pony Express мы можем осуществить доставку в любую другую транспортную компанию по вашему желанию, находящуюся на территории Москвы.<br>
                            При сумме заказа свыше 10 000 рублей доставка в пределах МКАД осуществляется бесплатно. Подъем на этаж — бесплатно при наличии лифта. В случае невозможности подъёма заказа на лифте стоимость подъема оговаривается с оператором при подтверждении зака</p>

                        <h2>Что необходимо сделать при доставке?</h2>
                        <p>При доставке внимательно осмотрите товар на наличие внешних повреждений и проверьте комплектность.
                            После проверки товара и его комплектности Вам необходимо расписаться в документах, подтверждающих оплату и получения вами товара надлежащего качества и в полной комплектности.</p>

                    </div>
                </article>

            </div>
        </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>