<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--><html lang="en"><!--<![endif]-->

<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Stylesheet -->
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css" media="all">

<!-- jquery-ui Stylesheets -->
<link rel="stylesheet" href="/assets/jui/css/jquery-ui.css" media="screen">
<link rel="stylesheet" href="/assets/jui/jquery-ui.custom.css" media="screen">
<link rel="stylesheet" href="/assets/jui/timepicker/jquery-ui-timepicker.css" media="screen">

<!-- Uniform Stylesheet -->
<link rel="stylesheet" href="/plugins/uniform/css/uniform.default.css" media="screen">

<!-- Plugin Stylsheets first to ease overrides -->

<!-- End Plugin Stylesheets -->

<!-- Main Layout Stylesheet -->
<link rel="stylesheet" href="/assets/css/fonts/icomoon/style.css" media="screen">
<link rel="stylesheet" href="/assets/css/main-style.css" media="screen">

<link rel="stylesheet" href="/custom_css/custom.css" media="screen">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="/js/jq/ajax-addon.js"></script>
<script src="/js/jq/ajax-download-addon.js"></script>
<script src="/js/jq/jquery-2.0.3.js"></script>
<![endif]-->

<title>LanguageBox - Courses</title>

</head>

<body data-show-sidebar-toggle-button="false" data-fixed-sidebar="true">
  <div id="dialog-message" class="hide" title="Внимание">
    <p>Уверены?</p>
  </div>
  <div id="wrapper">
  <%@include file="/WEB-INF/jsp/section/header_teacher.jsp" %>

  <div id="content-wrap">
    <div id="content">
    <div id="content-outer">
      <div id="content-inner">
      <jsp:include page="/WEB-INF/jsp/section/aside_teacher.jsp" >
          <jsp:param name="page" value="courses" />
      </jsp:include>

      <section id="main" class="clearfix">
        <div id="main-header" class="page-header">
          <ul class="breadcrumb">
            <li>
            <i class="icon-home"></i>LanguageBox
            <span class="divider">&raquo;</span>
            </li>
            <li>
            <a href="#">Библиотека</a>
            </li>
          </ul>

          <h1 id="main-heading">
            Библиотека и курсы<span>Здесь Вы можете просматривать ваши файлы, и редактировать курсы</span>
          </h1>
        </div>

        <div id="main-content">
          <div class="row-fluid">
            <%-- LIBRARY --%>
            <%@include file="/WEB-INF/jsp/section/courses/library.jsp" %>
            <div id="sidebar-separator"></div>
            <%-- COURSES --%>
            <%-- <%@include file="/WEB-INF/jsp/section/courses/courses.jsp" %> --%>
            <%@include file="/WEB-INF/jsp/section/courses/courses_new.jsp" %>
          </div>
        </div>
      </section>
      </div>
    </div>
    </div>
  </div>

  <%@include file="/WEB-INF/jsp/section/footer.jsp" %>

  </div>

  <!-- Core Scripts -->
  <script src="/assets/js/libs/jquery-1.8.3.min.js"></script>
  <script src="/bootstrap/js/bootstrap.min.js"></script>
  <script src="/assets/js/libs/jquery.placeholder.min.js"></script>
  <script src="/assets/js/libs/jquery.mousewheel.min.js"></script>

  <!-- Template Script -->
  <script src="/assets/js/template.js"></script>
  <script src="/assets/js/setup.js"></script>

  <!-- Customizer, remove if not needed -->
  <script src="/assets/js/customizer.js"></script>

  <!-- Uniform Script -->
  <script src="/plugins/uniform/jquery.uniform.min.js"></script>

  <!-- jquery-ui Scripts -->
  <script src="/assets/jui/js/jquery-ui-1.9.2.min.js"></script>
  <script src="/assets/jui/jquery-ui.custom.min.js"></script>
  <script src="/assets/jui/timepicker/jquery-ui-timepicker.min.js"></script>
  <script src="/assets/jui/jquery.ui.touch-punch.min.js"></script>

  <!-- Bootstrap InputMask -->
  <script src="/custom-plugins/bootstrap-inputmask.min.js"></script>

  <!-- Custom sripts-->
  <script src="/js/logic/service/teacher/courses.js" type="text/javascript"></script>
  <script src="/js/jq/jquery-ui-1.10.4.js" type="text/javascript"></script>
  <script src="/js/jq/ajax-addon.js" type="text/javascript"></script>
  <script src="/js/jq/ajax-download-addon.js" type="text/javascript"></script>

</body>

</html>