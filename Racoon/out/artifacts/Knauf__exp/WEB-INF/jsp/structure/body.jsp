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