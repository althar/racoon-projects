<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class='small-7 column'>
    <div id='teacher-library'>
        <dl class='tabs'>
            <dd class='active'>
                <a onclick="library.showFolder();">Библиотека</a>
            </dd>
            <div id="progress-bar" class="hidden">
            <div id="upload-details"></div>
            <div id="upload-progress-bar"></div>
            </div>
        </dl>

        <img src="/img/loader.gif" class="library-loader hidden">

        <%-- Folder section --%>
        <c:import url="/service/get_folder"></c:import>

        <%-- Add file section --%>
        <%@include file="/WEB-INF/jsp/section/courses/add_file_body.jsp" %>
        <%--<c:import url="/service/teacher/get_add_file"></c:import>--%>

    </div>
</div>