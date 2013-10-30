<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content">
    <script src="/js/logic/shipping.js"></script>
    <div class="content-inner">
        <div class="create-order">
            <ul class="order-nav">
                <li><a href="/Корзина/Авторизация?logout=true">авторизация</a></li>
                <li class="active"><a href="#">адрес доставки</a></li>
                <%--<li class="disabled"><a href="#">подарочные сертификаты</a></li>--%>
                <li class="disabled"><a href="#">посмотреть заказ</a></li>
            </ul>
            <div id="shipping">
                <h2>Выберите адрес доставки</h2>
                <div>
                    <div class="twocolumn">
                        <div class="column">
                            <div class="control-group">
                                <label class="control-label" for="type">Тип доставки</label>
                                <div class="controls">
                                    <div class="styled-select">
                                        <select name="type" id="delivery-select">
                                            <c:forEach items="${deliveryVariants}" var="variant">
                                                <option value="${variant.getLongValue('id')}" <c:if test="${deliveryVariant==variant.getStringValue('name')}">selected</c:if>>${variant.getStringValue('name')} (${variant.getDoubleValue('price')} рублей)</option>
                                            </c:forEach>
                                            <%--<option value="1" distance="В пределах МКАД" selected>В пределах МКАД (300 рублей)</option>--%>
                                            <%--<option value="2" distance="До 10км от МКАД">до 10км от МКАД (400 рублей)</option>--%>
                                            <%--<option value="3" distance="До 30км от МКАД">до 30км от МКАД (500 рублей)</option>--%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="address">Адрес</label>
                                <div class="controls">
                                    <input type="text" value="${basket.address}" name="address" id="address-input">
                                    <div id="alert"></div>
                                </div>

                            </div>
                            <div class="control-group">
                                <label class="control-label" for="date">Дата доставки</label>
                                <div class="controls">
                                    <input type="text" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${basket.deliveryDate}" />" name="date" data-date-format="dd.mm.yyyy" class="datepicker_" id="date-input">
                                </div>
                                <script>
                                    (function(){
                                        $('.datepicker_').datepicker({
                                            weekStart: 1,
                                            autoclose: true,
                                            language: 'ru'
                                        });
                                    })();
                                </script>
                            </div>
                        </div>
                        <div class="column">
                            <div class="control-group">
                                <label class="control-label">Дополнительная информация</label>
                                <div class="controls">
                                    <textarea name="comment" rows="5" id="comment-input">${basket.comment}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button type="button" class="button green">продолжить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>