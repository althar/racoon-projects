<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h3>${title}</h3>
<ul class="fast-links">
    <c:forEach items="${specialTags}" var="special">
        <li><div class="column">
            <div class="prodInfo new">
                <h3 class="prodName"><b><a href="/Товар?id=${good.getLongValue('id')}">${good.getStringValue('name')}</a></b></h3>
                <div class="prodImg">
                    <img src="${good.mainImageUrl}" height="130" alt='${good.getStringValue("name")}' class="img-stretch">
                </div>
                <div class="prodDetail">
                    <div class="description">
                        <p>${good.getStringValue('description')}</p>
                        <ul>
                            <li>
                                <p class="label">Бренд</p>
                                <span>${good.getStringValue('brand')}</span>
                            </li>
                            <li>
                                <p class="label">Артикул</p>
                                <span>${good.getStringValue('article')}</span>
                            </li>
                            <c:forEach var="characteristic" items="${good.characteristics}" varStatus="rowStatus">
                                <li>
                                    <p class="label">${characteristic.key}</p>
                                    <span>${characteristic.value}</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="prodBottom">
                    <div class="price pull-right">
                        <span class="finalPrice">${good.getDoubleValue('sell_price')} руб.</span>
                    </div>
                    <br class="clearfix">

                    <div class="controls">
                        <div class="pull-left">
                            <a class="blue good-detail-link" index="${(theCount.count-1) % 3}" good-id="${good.getLongValue('id')}" >Посмотреть</a>
                        </div>
                        <div class="pull-right">
                            <button class="button green buy-button" good-id="${good.getLongValue('id')}">Купить</button>
                        </div>

                    </div>
                </div>
            </div>
        </div></li>
    </c:forEach>
</ul>