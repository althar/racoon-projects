<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="find-friends">
    <p>${title}</p>

    <div class="row-fluid">
        <div class="span12">
            <div class="friends_block">
                <div class="wish-list">
                    <c:forEach var="friend" items="${wishList}" varStatus="rowStatus">
                        <div class="wish-list_item">
                            <div class="wish-list_image">
                                <img src="http://graph.facebook.com/${friend.facebookId}/picture"/>
                            </div>
                            <div class="wish-list_text">
                                <h3>${friend.name}
                                    <small>${friend.name}</small>
                                </h3>
                                <%--<h4>Москва</h4>--%>
                                <c:forEach var="wish" items="${friend.wishes}" varStatus="rowStatus">
                                <p>
                                    Xочет получить ${wish.getStringValue('date')} на ${wish.getStringValue('reason')}
                                    <a href="/user/good_details?wish_id=${wish.getLongValue('id')}&friends_wish=${friends_wish}">${wish.getStringValue('good_name')}</a>
                                </p>
                                </c:forEach>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </c:forEach>
                    <c:if test="${empty wishList}">
                        <div class="wish-list_item">
                            <div class="wish-list_image">
                                <c:if test="${not friends_wish}">
                                <img src="http://graph.facebook.com/${user.getStringValue('facebook_id')}/picture"/>
                                </c:if>
                            </div>
                            <div class="wish-list_text">
                                <h3>${user.getStringValue('first_name')} ${user.getStringValue('last_name')}
                                    <small>${user.getStringValue('name')}</small>
                                </h3>
                                Нет желаний.
                            </div>
                            <div class="clearfix"></div>
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