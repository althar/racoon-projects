<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="catalog product-preview">
    <h2>${good.name}</h2>

    <div class="row-fluid">
        <div class="span13">
            <div class="product_block">
                <div class='row-fluid'>
                    <div class='span7'>
					    <p style="text-align:center; width:300px;">
                        <img class="product-image-big" src="${good.imageMediumPath}"/>
						</p>
                    </div>
                    <div class="span5">
                        ${good.description}
                        <p class="product-text">
                            <c:forEach var="characteristic" items="${characteristics}">
                                ${characteristic.key} : ${characteristic.value}<br>
                            </c:forEach>
                        </p>
                        <p class="product-price">
                            <span class="price">${good.price},-</span><br/>
                            Цена с доставкой
                        </p>
                    </div>
                </div>
                <div class="tape"></div>
            </div>
		</div>
        </div>
    </div>
    <div class="friends-line">
        В подарке участвуют:
        <div class="friends_block friends_line-wrapper">
            <ul class="friends_line unstyled">
                <c:forEach var="member" items="${members}" varStatus="rowStatus">
                    <li>
                        <a class="friends_line-item"
                           href="http://facebook.com/${member.getStringValue('facebook_id')}/">
                            <img src="http://graph.facebook.com/${member.getStringValue('facebook_id')}/picture">
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="total-sum_range">
        Набранная сумма, руб:
        <div class="range_bg">
            <div class="range_space" style="width:${progress}%;"></div>
            <div class="range_begin">0,-</div>
            <div class="range_end">${good.price},-</div>
            <c:if test="${progress>5&&progress<95}">
                <div class="range_int" style="position: absolute; left:${progress}%;">${amount} руб.
                </div>
            </c:if>
        </div>
    </div>
    <div class="row-fluid">
        <div class='button-bar span9'>
            <div class='span6'>
                <a href="/user/payment?wish_id=${wish_id}&to_pay=${to_pay}&friends_wish=${friends_wish}" target="_blank">
                    <button class="btn button button-medium pay-wish">Скинуться</button>
                </a>
            </div>
            <c:if test="${(!friends_wish) && (progress==100)}">
                <div class='span6'>
                    <a href="/user/order?wish_id=${wish_id}">
                        <button class="btn button button-medium get-wish">Получить подарок</button>
                    </a>
                </div>
            </c:if>
        </div>
    </div>

</div>
</div>
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