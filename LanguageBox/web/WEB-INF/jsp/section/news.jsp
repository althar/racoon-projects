<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class='small-4 column'>
    <div class='white_block'>
        <h3>Новости</h3>
        <table class='table'>
            <tbody>
            <c:forEach items="${news}" var="item">
                <tr>
                    <td>
                        <b>
                            ${item.getStringValue('title')}
                        </b>
                        <br>
                            ${item.getStringValue('text')}
                    </td>
                    <td>
                        <i class='icons-star'></i>
                    </td>
                    <td>
                        <fmt:formatDate pattern="dd.MM.yyyy" value="${item.getDateValue('created')}" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="#">Все новости</a>
    </div>
</div>