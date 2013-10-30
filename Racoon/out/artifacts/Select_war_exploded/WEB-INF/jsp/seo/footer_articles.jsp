<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="content-inner">
    <h1>${articlesTitle}</h1>
    <article>
        <c:forEach items="${articles}" var="article">
        <p>
            <a href="${article.getStringValue('link')}"><h2>${article.getStringValue('title')}</h2></a>
            ${article.getStringValue('text')}
        </p>
        </c:forEach>
    </article>
</div>