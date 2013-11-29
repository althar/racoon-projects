<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="payment">
    <script type="text/javascript" src="/js/logic/payment.js"></script>
    <p>Введите данные своей банковской карты</p>
    <input type="hidden" id="wish_id" value="${wish_id}">
    <input type="hidden" id="user_id" value="${user_id}">
    <input type="hidden" id="friends_wish" value="${friends_wish}">
    <input type="hidden" id="for_user_id" value="${for_user_id}">

    <input type="hidden" id="payment_type" value="${payment_type}">
    <input type="hidden" id="guid" value="${guid}">
    <input type="hidden" id="address_id" value="${addressId}">
    <input type="hidden" id="delivery_variant_id" value="${deliveryVariantId}">
    <input type="hidden" id="address" value="${address}">
    <input type="hidden" id="first_name" value="${firstName}">
    <input type="hidden" id="last_name" value="${lastName}">
    <input type="hidden" id="phone" value="${phone}">
    <div class="payment_block">
        <div class="row-fluid">
            <form class="span7">
                <label>Имя (как на карте):</label>
                <input id="name" type="text" class="span12"/>
                <label>Фамилия (как на карте):</label>
                <input id="last-name" type="text" class="span12"/>
                <label>Карта:</label>

                <div class="row-fluid">
                    <input type="text" class="span3 card-number-part part-1"/>
                    <input type="text" class="span3 card-number-part part-2"/>
                    <input type="text" class="span3 card-number-part part-3"/>
                    <input type="text" class="span3 card-number-part part-4"/>
                </div>
                <label>Срок действия карты:</label>

                <div class="row-fluid">
                    <input id="card-expires" type="text" class="span4" placeholder="mm/yyyy"/>
                    <i class="icon icon-calendar icon-gray"></i>
                </div>
                <label>CVC:</label>
                <input id="cvv" type="text" class="span4" placeholder="xxx"/>
                <label>Сумма</label>
                <input id="amount" type="text" class="span4" value="${to_pay}"/>
            </form>
        </div>
        <!--<a href="/user/terms_of_payment">Условия оплаты подарков</a>-->
		<div style="margin-bottom: 100px; font: 12px Arial, 'Helvetica Neue', sans-serif;">
			<div style="display: inline-block;">
				<input type="checkbox" name="checkbox" value="checked" style="margin:5px;" /> 
			</div>
			<div style="display: inline-block; margin-left: 5px; font-weight: bold;">
				Я согласен с 
				<a href="#">условиями оплаты услуг </a> 
				и 
				<a href="#">публичной офертой</a>
			</div>
		</div>
		<div>
			<div>
				<div style="display: inline-block;">
					<img style="margin-top:10px; width:50px;" src="../../img/Visa.png"/>
					<img style="margin-top:10px; width:50px;" src="../../img/MasterCard.png" />
					<img style="margin-top:10px; width:40px;" src="../../img/american_express.png" />
					<img style="margin-top:10px; width:40px;" src="../../img/JCB.png" />
					<img style="margin-top:10px; width:40px;" src="../../img/diners-club-international.png" /> 
				</div>
				<div style="display: inline-block; float: right;">
					<a href="http://www.visaeurope.com/en/cardholders/verified_by_visa.aspx"><img style="margin-top:10px; width:50px;" src="../../img/VerifiedVasa.jpg" /></a>
					<a href="http://www.mastercard.com/ru/consumer/secure-code.html"><img style="margin-top:10px; width:60px;" src="../../img/mastercardSecure.jpg" /></a>
					<a href="https://www.americanexpress.com/uk/content/membership-benefits-cardmember/american-express-safekey.html"><img style="margin-top:10px; width:60px;" src="../../img/safekey.jpg" /></a>
					<a href="http://partner.jcbcard.com/security/jsecure/"><img style="margin-top:10px; width:45px;" src="../../img/JCB.jpg" /></a>
				</div>
			</div>
			<div>
			<center>
				<div style="font: 10px Arial, 'Helvetica Neue', sans-serif; color: #000; "> 
					В случае возникновения вопросов по поводу данной конфиденциальности предоставляемой Вами информации,
				</div> 
				<div style="font: 10px Arial, 'Helvetica Neue', sans-serif; color: #000; ">
					<div style="display: inline-block;">Вы можете связаться с ЗАО "Банк Русский стандарт" по телефону:</div><div style="display: inline-block; font-weight: bold;">8 (800)</div><div style="display: inline-block;">200-6-200</div>.
				</div>
				<div style="font: 10px Arial, 'Helvetica Neue', sans-serif; color: #000; ">
					<a href="terms_of_service#sequre">Подробнее о безопасности онлайн платежей</a>
				</div>
			</center>
			</div>
		</div>
        <!--<div class="payment-system"></div>-->
    </div>

    <button id="make-payment" class="btn button" style="margin-top: 15px;">Оплатить</button>
    <img id="loader" class="hidden" src="/img/loader.gif">
</div>
</section>
<div id="cont">
	<img src="../../img/line.png">
	<center>
		<div>По вопросам технической поддержки обращайтесь на <a href="mailto:support@v-skladchinu.ru">support@v-skladchinu.ru</a> или по телефону +7 (495) 648 6251</div> 
		<div>ООО "Креативные концепции"</div>
	</center>
</div>
</body>
</html>	