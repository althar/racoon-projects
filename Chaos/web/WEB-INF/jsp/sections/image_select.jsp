<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="image" items="${imageList.images}">
    <div class="image-preview">
        <input type="checkbox" style="float: left;" class="image-checkbox">
        <img class="image" style="width: 100px; height: 100px; float: left;" src="${image.unescapedUrl}">
        <input type="hidden" class="content-no-formatting" value="${image.contentNoFormatting}">
        <input type="hidden" class="tb-url" value="${image.tbUrl}">
        <input type="hidden" class="title-no-formatting" value="${image.titleNoFormatting}">
        <input type="hidden" class="width" value="${image.width}">
        <input type="hidden" class="height" value="${image.height}">
        <input type="hidden" class="keyword" value="${image.keyword}">
    </div>
</c:forEach>