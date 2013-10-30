<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content">
    <div class="content-inner">
        <div class="create-order">
            <ul class="order-nav">
                <li><a href="/Корзина/Авторизация?logout=true">авторизация</a></li>
                <li><a href="/Корзина/Доставка">адрес доставки</a></li>
                <li class="active"><a href="#">подарочные сертификаты</a></li>
                <li class="disabled"><a href="#">посмотреть заказ</a></li>
            </ul>
            <div id="gift">
                <h2>Воспользуйтесь Вашими<br/>скидочными сертификатами</h2>
                <div>
                    <table class="discountTable">
                        <colgroup>
                            <col style="width: 25%">
                            <col style="width: 30%">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>Наименование</th>
                            <th class="right">Скидка</th>
                            <th>Статус</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><!-- класс non-active для неактивного сертификата -->
                            <td>
                                <a class="discountTitle" href="">Сертификат №1</a>
                                <span class="promocode">промо-код: 5501</span>
                            </td>
                            <td class="right">
                                <p class="discountSum">500,00 руб.</p>
                            </td>
                            <td class="status attached"><!-- class "status detached" для не прикрепленных, просто "status" для неактивных -->
                                <p class="attachedDescr"><i class="icon icon-attach"></i> прикреплен</p>
                                <button class="button" id="detach">отменить</button>
                                <p class="detachedDescr">не прикреплен</p>
                                <button class="button cian" id="attach">прикрепить</button>
                                <p class="nonactiveDescr">станет доступен при следующей покупке</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a class="discountTitle" href="">Сертификат №2</a>
                                <span class="promocode">промо-код: 5501</span>
                            </td>
                            <td class="right">
                                <p class="discountSum">500,00 руб.</p>
                            </td>
                            <td class="status detached"><!-- class "status attached" для прикрепленных, просто "status" для неактивных -->
                                <p class="attachedDescr"><i class="icon icon-attach"></i> прикреплен</p>
                                <button class="button" id="detach">отменить</button>
                                <p class="detachedDescr">не прикреплен</p>
                                <button class="button green" id="attach">прикрепить</button>
                                <p class="nonactiveDescr">станет доступен при следующей покупке</p>
                            </td>
                        </tr>
                        <tr class="non-active">
                            <td>
                                <a class="discountTitle" href="">Сертификат №3</a>
                                <span class="promocode">промо-код: 5501</span>
                            </td>
                            <td class="right">
                                <p class="discountSum">500,00 руб.</p>
                            </td>
                            <td class="status">
                                <p class="attachedDescr"><i class="icon icon-attach"></i> прикреплен</p>
                                <button class="button" id="detach">отменить</button>
                                <p class="detachedDescr">не прикреплен</p>
                                <button class="button cian" id="attach">прикрепить</button>
                                <p class="nonactiveDescr">станет доступен при следующей покупке</p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="form-actions">
                        <button type="button" class="button green" onclick="window.location.href='create-order.html'">продолжить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>