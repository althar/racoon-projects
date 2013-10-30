<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<select multiple id="books-select">
    <c:forEach var="book" items="${books}">
        <option value="${book.fields["name"]}" book-id="${book.fields["id"]}" type="${book.fields["type"]}" genre="${book.fields["genre"]}" author="${book.fields["author"]}">${book.fields["name"]}</option>
        <%--<option value="${book.name}" book-id="${book.id}" type="${book.type}" genre="${book.genre}" author="${book.author}">${book.name}</option>--%>
    </c:forEach>
</select>
