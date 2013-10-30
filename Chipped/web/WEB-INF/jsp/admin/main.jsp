<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>

<div class="admin-main">
    <h1>Добро пожаловать админ</h1>
    <h2>Ваш баланс: ${balance} баллов.</h2>
    <form action="/admin/certificate">
        <label>Сертификат</label>
        <input type="text" name="cert">
        <br>
        <input type="submit">
        <label>${activationResult}</label>
    </form>
</div>
</section>
</body>
</html>	