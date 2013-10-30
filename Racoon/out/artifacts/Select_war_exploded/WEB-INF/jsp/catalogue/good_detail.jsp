<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="detail-view" id="detail">
    <div class="detail-inner">
        <div class="close" align="right">
            <a href="javascript:void(0)"></a>
        </div>

        <div class="detail_img">
            <img src="${good.images[0]}" class="detail_images detail-main-image" width="256" alt="${good.getStringValue('name')}" />
        </div>

        <div class="detail_info">

            <h2 class="item_name"><a href="#">${good.getStringValue('name')}</a></h2>

            <div class="description">
                <p>${good.getStringValue('description')}</p>
                <ul>
                    <li>
                        <p class="label">Бренд</p>
                        <span>${good.getStringValue('brand')}</span>
                    </li>

                    <c:forEach items="${good.characteristics}" var="characteristic">
                        <li>
                            <p class="label">${characteristic.key}</p>
                            <span>${characteristic.value}</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="detail_bottom">
                <div class="detail_img-prewiew pull-left">
                    <ul>
                        <c:forEach items="${good.images}" var="image" varStatus="status" begin="0" end="5">
                            <c:if test="${fn:contains(image, '256x256')}">
                                <li>
                                    ${fn:contains(image, '256x256')}
                                    <a class="good-detail-image" title="Выбрать ${good.getStringValue('category')}"><img src="${image}" class="detail_images" width="64" height="64" alt="${good.getStringValue('name')} - изображение ${status.index}" /></a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
                <div class="pull-right">
                    <div class="price">
                        <span class="finalPrice">${good.getDoubleValue('sell_price')} руб.</span>
                    </div>
                    <div class="controls">
                        <div class="pull-right">
                            <button class="button cian buy-button" good-id="${good.getLongValue('id')}">Добавить в корзину</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>