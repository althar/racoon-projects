<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row-fluid">
<c:forEach var="good" items="${goods.goods}" varStatus="rowStatus">

    <div class="span4">
        <a href="/user/wish_goods?id_price=${good.id}_${good.price}_${good.name}">
        <div class="product_block" good_id="${good.id}" good_price="${good.price}" good_name="${good.name}">
                <input type="hidden" name="good_id" value="${good.id}">
                <img class="product-image" src="${good.imageUrl}"/>
            <label class="checkbox">
                    <%--<input type="checkbox"/>--%>
                    ${good.itemType}
            </label>
            <span class="price">${good.price},-</span>
        </div>
        </a>
    </div>
    <c:if test="${rowStatus.index % 3 == 2}">
        </div>
        <div class="row-fluid">
    </c:if>
</c:forEach>
</div>
