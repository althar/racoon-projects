<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<meta charset="utf-8">
<%@ include file="/WEB-INF/jsp/section/head.jsp" %>
<body>
<section class="main">
    <%@ include file="/WEB-INF/jsp/section/header.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/main_menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/banner.jsp" %>
        <div class="main-content">
            <div class="main-content_inner">

                <article class="article">
                    <div class="article-inner">

                        <h2>Возврат/обмен товара надлежащего качества</h2>
                        <p>Возврат товара, не подошедшего по размеру, фасону или цвету, производится в течение 14 календарных дней.
                            Условия для возврата товара Товар должен быть новым и находиться в заводской упаковке. В случае, если товар, подлежащий возврату, будет иметь следы установки или его использования, данный товар и денежные средства возврату не подлежат.</p>

                        <h2>Качество товара</h2>
                        <p>Мы торгуем только оригинальними товарами европейских брендов.</p>

                    </div>
                </article>

                <div class="certificates">
                    <ul class="row">
                        <li class="certificates_item">
                            <img src="images/cert1.jpg">
                        </li>
                        <li class="certificates_item">
                            <img src="images/cert2.jpg">
                        </li>
                        <li class="certificates_item">
                            <img src="images/cert3.jpg">
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>