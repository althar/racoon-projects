<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset='utf-8' />
    <title>Вскладчину</title>
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/js/jq/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="/js/jq/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/jq/jquery.mask.js"></script>
    <script type="text/javascript" src="/js/jq/jquery.jquery-numeric.js"></script>

    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" >
    <link href="/css/style.css" rel="stylesheet" type="text/css" >

</head>
<body>
<%String getURL=request.getRequestURL().toString();%>
<div id="main-header2">
  </div>
<section class="main">
    <header id="main-header">
        <nav>
            <h1><a class="logo" href="/user/main_choose">Вскладчину</a></h1>
            <ul class="nav nav-pills main-header_nav">
				<%
					String m1="https://v-skladchinu.ru:8443/WEB-INF/jsp/main_choose.jsp";
					if(getURL.equals(m1)){
				%>
					<li><a style="font-weight: bold;" href="/user/main_choose">Главная</a></li>
				<%}else{%>
					<li><a href="/user/main_choose">Главная</a></li>
				<%}%>
				<li class="divider">|</li>
				    <% String s1=request.getParameter("friends_wish");%>
					<% String s2 = "false";
					   String s3 = "true";
					   String s4 = "some";
					%>
					<% if (s1==null){
					s1 = "some";
					}
					if (s1.equals(s2)) { %> 
						<li><a style="font-weight: bold;" href="/user/wish_list?friends_wish=false">Мои желания</a></li>
						<li class="divider">|</li>
						<li><a href="/user/wish_list?friends_wish=true">Желания друзей</a></li>
				    <% } if (s1.equals(s3)) { %>
						<li><a href="/user/wish_list?friends_wish=false">Мои желания</a></li>
				   		<li class="divider">|</li>
						<li><a style="font-weight: bold;" href="/user/wish_list?friends_wish=true">Желания друзей</a></li>
					<% } if (s1.equals(s4)){ %>
						<li><a href="/user/wish_list?friends_wish=false">Мои желания</a></li>
				   		<li class="divider">|</li>
						<li><a href="/user/wish_list?friends_wish=true">Желания друзей</a></li>
					<%}%>
                
                <li class="divider">|</li>
				
				
				<%
					String m2="https://v-skladchinu.ru:8443/WEB-INF/jsp/terms_of_service.jsp";
					if(getURL.equals(m2)){
				%>
					<li><a style="font-weight: bold;" href="/user/terms_of_service">Как это работает?</a></li>
				<%}else{%>
					<li><a href="/user/terms_of_service">Как это работает?</a></li>
				<%}%>
				
				<li class="divider">|</li>
				
                <%
					String m3="https://v-skladchinu.ru:8443/WEB-INF/jsp/catalogue.jsp";
					if(getURL.equals(m3)){
				%>
					<li><a style="font-weight: bold;" href="/user/catalogue">Подарки</a></li>
				<%}else{%>
					<li><a href="/user/catalogue">Подарки</a></li>
				<%}%>
				
            </ul>
        </nav>
    </header>