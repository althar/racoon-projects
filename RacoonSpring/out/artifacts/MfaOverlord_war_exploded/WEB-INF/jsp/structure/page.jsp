<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="ru">
<head>
    <title>Автострахование</title>


</head>
<body>
<div class="wrapper layout">
    <%@ include file="/WEB-INF/jsp/structure/header.jsp" %>
        <div class="container layout">
    <%@ include file="/WEB-INF/jsp/structure/aside.jsp" %>
    <%@ include file="/WEB-INF/jsp/structure/body.jsp" %>
            </div>
</div>
<%@ include file="/WEB-INF/jsp/structure/footer.jsp" %>
</body>
</html>