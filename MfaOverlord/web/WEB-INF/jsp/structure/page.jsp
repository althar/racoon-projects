<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="ru">
<head>

    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="description" content="">
    <!-- Mobile Devices Viewport Resset-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <!--[if IE 8]>
    <link rel="stylesheet" href="/css/font-awesome-ie7.min.css">
    <![endif]-->

    <link rel="stylesheet" href="/css/prettyPhoto.css">

    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700|Lato:300,400,700,400italic,700italic|Droid+Serif' rel='stylesheet' type='text/css'>

    <!-- base layout styling -->
    <link rel="stylesheet" href="/css/base.css">

    <!-- components styling -->
    <link rel="stylesheet" href="/css/components.css">

    <!-- template colorschemes -->
    <link rel="stylesheet" href="/css/light.css">

    <script src="/js/jq/modernizr-2.6.2-respond-1.1.0.min.js"></script>

</head>
<body>

    <%@ include file="/WEB-INF/jsp/structure/header.jsp" %>
    <%@ include file="/WEB-INF/jsp/structure/aside.jsp" %>
    <%@ include file="/WEB-INF/jsp/structure/body.jsp" %>
    <%@ include file="/WEB-INF/jsp/structure/footer.jsp" %>
</body>
</html>