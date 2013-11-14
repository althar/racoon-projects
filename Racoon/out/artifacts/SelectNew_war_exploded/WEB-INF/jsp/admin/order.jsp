<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>Заказ №${order_id}</h2>

<b>Клиент: </b>${order.getStringValue('user_name')}<br>
<b>Телефон: </b>${order.getStringValue('phone')}<br>
<b>Комментарий: </b>${order.getStringValue('comment')}<br>
<b>Доставка: </b>${order.getStringValue('address')}: (${order.getDoubleValue('delivery_price')} рублей)<br>

<h3>Товары: </h3>

<c:forEach var="good" items="${order.goods}" varStatus="rowStatus">
    ${good.getStringValue('name')}
    ${good.getDoubleValue('price')} руб. x (${good.getIntValue('count')})
    <br>

</c:forEach>
<br>

<h3>Цена товаров: ${order.getDoubleValue('good_price')} рублей.</h3>

<h3>Цена доставки: ${order.getDoubleValue('delivery_price')} рублей.</h3>

<h3>Скидка: ${order.getDoubleValue('discount')} рублей.</h3>

<h3>Итого: ${order.getDoubleValue('total_price')} рублей.</h3>

