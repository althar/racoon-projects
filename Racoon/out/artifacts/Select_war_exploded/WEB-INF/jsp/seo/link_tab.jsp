<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<ul style="background: #953cac; color: white; text-align: center; font-size: large;">
    <b>
        <br>
        Подарок!!!
        <br>
        При заказе от 2000 рублей.
        <br>
        <br>
    </b>
</ul>
<br>
<h3>Дополнительные категории сантехники</h3>
<ul class="fast-links">
    <c:forEach items="${specialTags}" var="special">
        <li><a href="/Каталог/${category}?special=${special}" class="blue" title="Специальный набор сантехники. ${fn:replace(special,'<cat>', category)}">${fn:replace(special,'<cat>', category)}</a></li>
    </c:forEach>
</ul>