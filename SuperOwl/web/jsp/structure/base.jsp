<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en-us">

<head>
    <%@ include file="/jsp/structure/head-info.jsp" %>
</head>

<body class="">

<header id="header">
    <%@ include file="/jsp/structure/ajax-dropdown.jsp" %>
    <%@ include file="/jsp/structure/project-dropdown.jsp" %>
    <%@ include file="/jsp/structure/icon-and-search.jsp" %>

</header>

<aside id="left-panel">
    <%@ include file="/jsp/structure/user-info.jsp" %>
    <%@ include file="/jsp/structure/aside-menu.jsp" %>
</aside>

<!-- MAIN PANEL -->
<div id="main">
    <!-- RIBBON -->
    <%@ include file="/jsp/structure/bread-crumb.jsp" %>
    <%@ include file="/jsp/structure/page-content.jsp" %>
</div>

<div id="shortcut">
    <%@ include file="/jsp/structure/metro-shortcut.jsp" %>
</div>
<%@ include file="/jsp/structure/common-scripts.jsp" %>
</body>
</html>