<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript" src="/js/logic/gift_friends_list.js"></script>
<div class="find-friends">
    <p>Выбери друга, которому хочешь подарить подарок:</p>
    <input type="hidden" id="ids" value="${ids}">
    <input type="hidden" id="reason" value="${reason}">
    <input type="hidden" id="date" value="<fmt:formatDate value="${date}" pattern="dd/MM/yyyy"/>">

    <input type="hidden" id="delivery_summ" value="${delivery_summ}">
    <input type="hidden" id="delivery_region_id" value="${delivery_region_id}">
    <input type="hidden" id="delivery_area_id" value="${delivery_area_id}">
    <input type="hidden" id="delivery_group_id" value="${delivery_group_id}">
    <input type="hidden" id="delivery_variant_id" value="${delivery_variant_id}">

    <div class="row-fluid">
        <div class="span12">
            <div class="friends_block friends-grid">

                <div class="row-fluid">
                    <c:forEach var="friend" items="${friends.friends}" varStatus="rowStatus">
                    <div class="friend-item span4">
                        <label class="checkbox"><input type="checkbox"/></label>
                        <figure>
                            <img class="friend-photo" src="http://graph.facebook.com/${friend.id}/picture"/>
                            <figcaption>${friend.name}</figcaption>
                            <input type="hidden" class="friend-id" value="${friend.id}">
                        </figure>
                    </div>

                        <%--<div class="friend">--%>
                        <%--<img src="http://graph.facebook.com/${friend.id}/picture">--%>
                        <%--${friend.name}--%>
                        <%--<input type="hidden" class="friend-id" value="${friend.id}">--%>
                        <%--</div>--%>
                    <c:if test="${rowStatus.index % 3 == 2}">
                </div>
                <div class="row-fluid">
                    </c:if>
                    </c:forEach>
                </div>
            </div>
            </div>
        </div>
            <div class="row-fluid button-bar">
                <div class='span6'>
                    <button class="btn button button-medium" id="present-butt">Подарить</button>
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