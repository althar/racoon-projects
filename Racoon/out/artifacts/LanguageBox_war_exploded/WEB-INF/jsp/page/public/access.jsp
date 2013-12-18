<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic" media="screen" rel="stylesheet" type="text/css" />

    <link href="/css/normalize.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="/css/devise.css" media="screen" rel="stylesheet" type="text/css" />

    <script src="/js/jq/jquery-2.0.3.js" type="text/javascript"></script>
    <script src="/js/common/all.js" type="text/javascript"></script>
</head>
<body class='devise devise_registration'>
<%@ include file="/WEB-INF/jsp/section/header.jsp" %>
<div class='main_content'>
    Форма регистрации... ${widget} ${role}
</div>
<%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>