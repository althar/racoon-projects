<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

<title>SmartAdmin</title>
<meta content="" name="description">
<meta content="" name="author">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">

<!-- Basic Styles -->
<link href="/css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css">
<link href="/css/font-awesome.min.css" media="screen" rel="stylesheet" type="text/css">

<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
<link href="/css/smartadmin-production.css" media="screen" rel="stylesheet" type="text/css">
<link href="/css/smartadmin-skins.css" media="screen" rel="stylesheet" type= "text/css">

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link href="/css/demo.css" media="screen" rel="stylesheet" type="text/css">

<!-- FAVICONS -->
<link href="/img/favicon/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="/img/favicon/favicon.ico" rel="icon" type="image/x-icon">

<!-- GOOGLE FONT -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700" rel="stylesheet">

<!-- Specifying a Webpage Icon for Web Clip
Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link href="/img/splash/sptouch-icon-iphone.png" rel="apple-touch-icon">
<link href="/img/splash/touch-icon-ipad.png" rel="apple-touch-icon" sizes="76x76">
<link href="/img/splash/touch-icon-iphone-retina.png" rel="apple-touch-icon" sizes="120x120">
<link href="/img/splash/touch-icon-ipad-retina.png" rel="apple-touch-icon" sizes="152x152">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">

<!-- Startup image for web apps -->
<link href="/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)" rel="apple-touch-startup-image">
<link href="/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)" rel="apple-touch-startup-image">
<link href="/img/splash/iphone.png" media="screen and (max-device-width: 320px)" rel="apple-touch-startup-image">