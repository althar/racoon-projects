<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<footer class="main-footer">
    <nav class="main-footer_inner">
        <div class="main-footer_menu row">
            <dl class="footer-menu_section">
                <dt class="footer-menu_title">ПОМОЩЬ</dt>
                <dd class="footer-menu_item">
                    <a href="/delivery" class="link_type-footer">Доставка товара</a>
                </dd>
                <dd class="footer-menu_item">
                    <a href="/payment" class="link_type-footer">Способы оплаты</a>
                </dd>
                <dd class="footer-menu_item">
                    <a href="/guarantees" class="link_type-footer">Возврат</a>
                </dd>
            </dl>
            <dl class="footer-menu_section">
                <dt class="footer-menu_title">SELECT</dt>
                <dd class="footer-menu_item">
                    <a href="/contact" class="link_type-footer">Реквизиты и контакты</a>
                </dd>
                <dd class="footer-menu_item">
                    <a href="mailto:selectsanteh@gmail.com?subject=Сотрудничество" class="link_type-footer">Сотрудничество</a>
                </dd>
            </dl>
            <%--<dl class="footer-menu_section">--%>
                <%--<dt class="footer-menu_title">ДОПОЛНИТЕЛЬНО</dt>--%>
                <%--<dd class="footer-menu_item">--%>
                    <%--<a href="/brands" class="link_type-footer">Бренды</a>--%>
                <%--</dd>--%>
            <%--</dl>--%>
        </div>
    </nav>
</footer>