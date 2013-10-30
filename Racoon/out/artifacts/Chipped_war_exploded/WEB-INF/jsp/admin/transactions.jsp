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
<div class="admin-transactions">
    <h1>Транзакции</h1>
    <form action="/admin/transactions">
        <label>ID: </label><input name="id">
        <label>ID юзера: </label><input name="userId">
        <%--<label>ID в payture: </label><input name="facebookId">--%>
        <input type="submit" value="Отбор по критериям">
    </form>
    <br>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>ID пользователя</td>
            <td>Имя получателя денег</td>
            <td>ID в payture</td>
            <td>Дата создания</td>
            <td>Сумма</td>
            <td>Статус</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td>${transaction.getLongValue('id')}</td>
                <td>${transaction.getLongValue('user_id')}</td>
                <td>${transaction.getStringValue('receiver')}</td>
                <td>${transaction.getStringValue('order_id')}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${transaction.getDateValue('created')}"/></td>
                <td>${transaction.getLongValue('amount')}</td>
                <td>${transaction.getStringValue('status_name')}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</section>
</body>
</html>	