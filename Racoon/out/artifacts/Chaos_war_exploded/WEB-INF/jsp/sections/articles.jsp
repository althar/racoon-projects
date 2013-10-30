<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="article" items="${articles}">
    <div class="article">
        <div class="title"></div>
        <div class="book-name">Источник:${article.bookName}
            <c:choose>
                <c:when test="${not empty article.author}">
                    Автор: ${article.author}
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="text">${article.text}</div>
    </div>
</c:forEach>