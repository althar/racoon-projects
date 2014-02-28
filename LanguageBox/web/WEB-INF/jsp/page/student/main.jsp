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

<!-- Rating Plugin -->
<link rel="stylesheet" href="/plugins/rating/jquery.rating.css" media="screen">

<!-- Main Layout Stylesheet -->
<link rel="stylesheet" href="/assets/css/fonts/icomoon/style.css" media="screen">
<link rel="stylesheet" href="/assets/css/main-style.css" media="screen">

<link rel="stylesheet" href="/custom_css/custom.css" media="screen">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>LanguageBox - Dashboard</title>

</head>

<body data-show-sidebar-toggle-button="false" data-fixed-sidebar="true">
  <div id="dialog-message" class="hide" title="Внимание">
    <p>Уверены?</p>
  </div>
  <div id="dialog-message-delete-course" class="hide" title="Внимание">
      <p>Уверены?</p>
  </div>


  <div id="wrapper">
    <%@include file="/WEB-INF/jsp/section/header_student.jsp" %>

    <div id="content-wrap">
      <div id="content">
        <div id="content-outer">
          <div id="content-inner">
              <jsp:include page="/WEB-INF/jsp/section/aside_student.jsp" >
                  <jsp:param name="page" value="main" />
              </jsp:include>

            <!-- <div id="sidebar-separator"></div> -->

            <section id="main" class="clearfix">
              <div id="main-header" class="page-header">
                <ul class="breadcrumb">
                  <li>
                    <i class="icon-home"></i>LanguageBox
                    <span class="divider">&raquo;</span>
                  </li>
                  <li>
                    <a href="#">Главная</a>
                  </li>
                </ul>

                <h1 id="main-heading">
                  Dashboard <span>This is the place where everything started</span>
                </h1>
              </div>

              <div id="main-content">
                <!-- Stats -->
                <ul class="stats-container">
                  <li>
                    <a href="#" class="stat summary">
                      <span class="icon icon-circle bg-blue">
                        <i class="icon-history"></i>
                      </span>
                      <span class="digit">
                        <span class="text">Последнее Посещение</span>
                        15.02.2014
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="#" class="stat summary">
                      <span class="icon icon-circle bg-green">
                        <i class="icon-stats"></i>
                      </span>
                      <span class="digit">
                        <span class="text">Всего Курсов</span>
                        23
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="#" class="stat summary">
                      <span class="icon icon-circle bg-orange">
                        <i class="icon-user"></i>
                      </span>
                      <span class="digit">
                        <span class="text">Выполненных Уроков</span>
                        58
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="#" class="stat summary">
                      <span class="sparkline" sparkType="bar" sparkBarColor="#e95d3c" sparkBarWidth="6" sparkHeight="28">5,6,7,9,9,5,3,2,2,4,6,7</span>
                      <span class="digit">
                        <span class="text">Уровень Знаний</span>
                        + 100500
                      </span>
                    </a>
                  </li>
                </ul>
                <!-- Stats end -->

                <div class="row-fluid">
                  <!-- Profile Widget-->
                  <%@include file="/WEB-INF/jsp/section/achivments.jsp" %>
                  <!-- Profile Widget end -->

                  <!-- Video Widget -->
                  <%@include file="/WEB-INF/jsp/section/how_to_use.jsp" %>
                  <!-- Video Widget end -->
                </div>

                <div class="row-fluid">
                  <!-- News Widget -->
                  <%@include file="/WEB-INF/jsp/section/news.jsp" %>
                  <!-- News Widget end -->

                  <!-- Courses Widget -->
                  <%@include file="/WEB-INF/jsp/section/courses_bought.jsp" %>
                  <!-- Courses Widget end -->
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

  <!-- Plugin Scripts -->

  <!-- Rating  -->
  <script src="/plugins/rating/jquery.rating.min.js"></script>

  <!-- SparkLine -->
  <script src="/plugins/sparkline/jquery.sparkline.min.js"></script>

  <!-- Demo Scripts -->
  <script src="/assets/js/demo/dashboard.js"></script>


  <script src="/js/common/all.js" type="text/javascript"></script>

</body>

</html>


