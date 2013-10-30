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
<div class="admin-order">
    <h1>Заказы (желания)</h1>
    <form action="/admin/orders">
        <label>ID: </label><input name="id">
        <label>ID юзера: </label><input name="userId">
        <input type="submit" value="Отбор по ID">
    </form>
    <br>
    <table>
        <thead>
        <tr>
            <%--wl.id, wl.user_id, wl.good_id, wl.price, ws.name AS status, wl.created, wl.reason, wl.good_name--%>
            <td>ID</td>
            <td>ID юзера</td>
            <td>Пользователь</td>
            <td>ID товара</td>
            <td>Цена</td>
            <td>Статус</td>
            <td>Дата создания</td>
            <td>Повод</td>
            <td>Товар</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.getLongValue('id')}</td>
                <td>${order.getLongValue('user_id')}</td>
                <td>${order.getStringValue('name')}</td>
                <td>${order.getLongValue('good_id')}</td>
                <td>${order.getDoubleValue('price')}</td>
                <td>${order.getStringValue('status')}</td>
                <td><fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${user.getDateValue('created')}"/></td>
                <td>${order.getStringValue('reason')}</td>
                <td>${order.getStringValue('good_name')}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</section>
</body>
</html>	