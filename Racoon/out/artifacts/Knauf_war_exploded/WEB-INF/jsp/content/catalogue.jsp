<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="/js/logic/catalogue.js"></script>
<div class="content layout">

<ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item"><a href="/catalog/1/" class="link">Электроника</a><span class="arrow"></span></li><li class="item">Электронные Книги</li></ul>
<div class="container section dark-title">
<h2>
    ${title}
</h2>
<ul class="products" id="catalog_items">
    <%@ include file="/WEB-INF/jsp/widget/catalogue.jsp" %>
</ul>
    <button class="btn btn-block btn-blue" id="more_products"
            <c:if test="${not empty page_number}">page="${page_number}"</c:if>
            <c:if test="${empty page_number}">page="1"</c:if>
            <c:if test="${not empty catalogue_name}">catalogue="${catalogue_name}"</c:if>
            <c:if test="${not empty catalogue_id}">catalogue-id="${catalogue_id}"</c:if>
            <c:if test="${not empty search}">search="${search}"</c:if>
            >Ещё</button>
</div>
</div>