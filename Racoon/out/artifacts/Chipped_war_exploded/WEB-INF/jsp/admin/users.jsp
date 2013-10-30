<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/admin/header.jsp" %>
<style>
    table, td, th {
        border: 1px solid green;
        padding: 2px;
    }

    th {
        background-color: green;
        color: white;
    }
</style>
<div class="admin-users">
    <h1>Пользователи</h1>
    <form action="/admin/users">
        <label>ID юзера: </label><input name="id">
        <label>Facebook id: </label><input name="facebookId">
        <input type="submit" value="Отбор по критериям">
    </form>
    <br>
    <table>
        <thead>
        <tr>
            <td>ID юзера</td>
            <td>ID facebook</td>
            <td>Имя юзера</td>
            <td>Дата регистрации</td>
            <td>Баланс</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.getLongValue('id')}</td>
                <td>${user.getStringValue('facebook_id')}</td>
                <td>${user.getStringValue('facebook_name')}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${user.getDateValue('created')}"/></td>
                <td>${user.getLongValue('amount')}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</section>
</body>
</html>	