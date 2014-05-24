<%@ page import="racoonsoft.circos.settings.MainConfig" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header>
    <c:choose>
        <c:when test="${anonymous}">
            <a href="/login">Войти</a>
            /
            <a href="https://oauth.vk.com/authorize?client_id=4378615&scope=friends,video,offline&redirect_uri=http://<%=MainConfig.host%>/vklogin&display=page">Войти через вконтакте </a>
            или
            <a href="/registration"> Зарегистрироваться</a>
        </c:when>
        <c:otherwise>
            Добро пожаловать, ${user.get('name')} - ${user.get('login')}
            <a href="/logout">Выйти</a>
        </c:otherwise>
    </c:choose>
    <c:forEach items="${hints}" var="hint">
        <h3>${hint}</h3>
    </c:forEach>
</header>