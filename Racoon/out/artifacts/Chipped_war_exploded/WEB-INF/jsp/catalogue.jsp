<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript" src="/js/logic/catalogue.js"></script>
<div class="catalog">
    <div class="row-fluid">
        <div class="span3">
            <ul class='category unstyled'>
                <c:forEach var="category" items="${catalogue.categories}" varStatus="rowStatus">
                    <c:if test="${fn:length(category.subcategories)>0}">
                    <li><a href="#" category_name="${category.name}">${category.name}</a>
                        <ul class="categoty_sub-menu unstyled hidden">
                            <c:forEach var="subcategory" items="${category.subcategories}" varStatus="rowStatus">
                                <li><a href="#" subcategory_name="${subcategory.name}" subcategory_id="${subcategory.id}">${subcategory.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="span9">
            <h2 style="font-size:21px;"> Выбери подарок для себя или своего друга</h2>		   
            <h2 id="category"></h2><img src="../../img/loader.gif" id="loader">
            <ul class="breadcrumb" id="navigation">
            </ul>
           
            <div id="catalogue_body">
            </div>
            <div class="row-fluid button-bar">
                <div class='span6'>
                    <button class="btn button button-medium" id="more_butt">Еще</button>
                </div>
                <div class='span7'>
                    <img id="more-loader" src="/img/loader.gif" class="hidden">
                </div>
            </div>
        </div>
    </div>
</div>
</section>
<div id="cont">
<img src="../../img/line.png">
    <a href="/user/terms_of_service" target="_blank">Политиа конфиденциальности</a>
    <a href="/user/terms_of_payment" target="_blank">Условия пользования сервиса</a>
    <a href="mailto:support@v-skladchinu.ru">support@v-skladchinu.ru</a>
    <span></span>
    <a href="http://www.newmethod.ru" target="_blank">Сделано в Newmethod</a>
</div>
</body>
</html>	