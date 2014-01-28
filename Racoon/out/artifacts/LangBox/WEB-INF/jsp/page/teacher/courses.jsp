<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>Language Box</title>
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" media="screen" rel="stylesheet"
          type="text/css"/>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic"
          media="screen" rel="stylesheet" type="text/css"/>

    <link href="/css/normalize.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="/css/application.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="/css/ui-lightness/jquery-ui-1.10.4.custom.css" media="screen" rel="stylesheet" type="text/css"/>
    <%--<link href="/css/jquery-ui.css" media="screen" rel="stylesheet" type="text/css"/>--%>

    <script src="/js/jq/jquery-2.0.3.js" type="text/javascript"></script>
    <script src="/js/common/all.js" type="text/javascript"></script>
    <script src="/js/logic/service/teacher/courses.js" type="text/javascript"></script>
    <script src="/js/jq/jquery-ui-1.10.4.js" type="text/javascript"></script>
    <script src="/js/jq/ajax-addon.js" type="text/javascript"></script>
    <script src="/js/jq/ajax-download-addon.js" type="text/javascript"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/section/header_teacher.jsp" %>
<div class='main_content'>
    <div class='main_content-inner row'>
        <div class='white_block tabs_wrapper'>
            <div class='row'>

                <%-- LIBRARY --%>
                <%@include file="/WEB-INF/jsp/section/courses/library.jsp" %>

                <%-- COURSES --%>
                <%@include file="/WEB-INF/jsp/section/courses/courses.jsp" %>

            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>
