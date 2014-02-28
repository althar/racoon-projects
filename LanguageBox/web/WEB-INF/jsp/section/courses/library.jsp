<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div id='teacher-library' class="span6">

<%-- Folder section --%>
<c:import url="/service/get_folder"></c:import>

<%-- Add file section --%>
<%@include file="/WEB-INF/jsp/section/courses/add_file_body.jsp" %>
<%--<c:import url="/service/teacher/get_add_file"></c:import>--%>

</div>
