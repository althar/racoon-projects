<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${page=='main'}">
    <%@ include file="/WEB-INF/jsp/content/main.jsp" %>
</c:if>
<c:if test="${page=='help_delivery'}">
    <%@ include file="/WEB-INF/jsp/content/help_delivery.jsp" %>
</c:if>
<c:if test="${page=='help_order'}">
    <%@ include file="/WEB-INF/jsp/content/help_order.jsp" %>
</c:if>
<c:if test="${page=='help_payment'}">
    <%@ include file="/WEB-INF/jsp/content/help_payment.jsp" %>
</c:if>
<c:if test="${page=='registration'}">
    <%@ include file="/WEB-INF/jsp/content/registration.jsp" %>
</c:if>
<c:if test="${page=='certificate'}">
    <%@ include file="/WEB-INF/jsp/content/certificate.jsp" %>
</c:if>
<c:if test="${page=='certificate_result'}">
    <%@ include file="/WEB-INF/jsp/content/certificate_result.jsp" %>
</c:if>
<c:if test="${page=='catalogue'}">
    <%@ include file="/WEB-INF/jsp/content/catalogue.jsp" %>
</c:if>
<c:if test="${page=='basket'}">
    <%@ include file="/WEB-INF/jsp/content/basket.jsp" %>
</c:if>
<c:if test="${page=='order_1'}">
    <%@ include file="/WEB-INF/jsp/content/order_1.jsp" %>
</c:if>
<c:if test="${page=='order_2'}">
    <%@ include file="/WEB-INF/jsp/content/order_2.jsp" %>
</c:if>
<c:if test="${page=='order_3'}">
    <%@ include file="/WEB-INF/jsp/content/order_3.jsp" %>
</c:if>
<c:if test="${page=='feedback'}">
    <%@ include file="/WEB-INF/jsp/content/feedback.jsp" %>
</c:if>
<c:if test="${page=='good'}">
    <%@ include file="/WEB-INF/jsp/content/good.jsp" %>
</c:if>
<c:if test="${page=='order_confirm'}">
    <%@ include file="/WEB-INF/jsp/content/order_confirm.jsp" %>
</c:if>

<c:if test="${page=='order_done'}">
    <%@ include file="/WEB-INF/jsp/content/order_done.jsp" %>
</c:if>
<c:if test="${page=='api_failed'}">
    <%@ include file="/WEB-INF/jsp/content/api_failed.jsp" %>
</c:if>
<c:if test="${page=='profile'}">
    <%@ include file="/WEB-INF/jsp/content/profile.jsp" %>
</c:if>
<c:if test="${page=='info'}">
    <%@ include file="/WEB-INF/jsp/content/info.jsp" %>
</c:if>
<c:if test="${page=='history'}">
    <%@ include file="/WEB-INF/jsp/content/history.jsp" %>
</c:if>
