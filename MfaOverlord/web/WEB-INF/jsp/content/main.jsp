<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="main" class="container">

<%@ include file="/WEB-INF/jsp/structure/title_widget.jsp" %>

<div id="content" class="content section row">

<div class="col-md-9 col-lg-9">

<div class="row">

<%@ include file="/WEB-INF/jsp/structure/tags.jsp"%>
<!--/.col-md-4.col-lg-4.md-pull-right-->

<div class="bg-base col-md-8 col-lg-8">

    <h2 class="section-title ribbon ribbon-highlight"><span>Статья - ${current_semantics.getValue('word')}</span></h2>
    <div class="entries">
        <article class="entry style-large type-post">

            <header class="entry-header">

                <h3 class="entry-title">
                    <a href="?search_id=${current_content.getValue('search_item_id')}" rel="bookmark">${current_content.getValue('title')}</a>
                </h3>

                <div class="entry-meta">
                    <span class="author">С <a href="${current_content.getValue('url')}">${current_content.getValue('domain')}</a></span>
                    <span class="entry-date">
                        дата:<a>
                        <fmt:formatDate value="${current_content.getValue('date')}"></fmt:formatDate>
                    </a></span>
                    <span class="category">по запросу <a href="http://google.com/search?q=${current_semantics.get('word').replace(" ","+")}">${current_semantics.get('word')}</a></span>
                </div>

            </header>
            <p>${current_content.getValue('text')}</p>
        </article>
    </div>

</div>
<!--/.bg-base.col-md-8.col-lg-8-->

</div>
<!--/.colheight-->

</div>
<!--/.col-md-8.col-lg-4-->

<div class="sidebar col-md-3 col-lg-3">

<aside class="widget">

    <h2 class="widget-title ribbon"><span>Последние добавления</span></h2>

    <div class="entries row">
        <c:forEach items="${search_items}" var="search_item">
            <article class="type-post style-media-list style-review-list media col-sm-6 col-md-12">

            <div class="style-review-score">

            </div>

            <!-- to disable lazy loading, remove data-src and data-src-retina -->
            <img src="/img/placeholder.gif" data-src="http://placehold.it/80x80" data-src-retina="http://placehold.it/160x160" width="80" height="80" class="media-object pull-left" alt="">

            <!--fallback for no javascript browsers-->
            <noscript>
                <img src="http://placehold.it/80x80" alt="">
            </noscript>

            <div class="media-body">
                <h3 class="entry-title">
                    <a href="?search_id=${search_item.get('id')}" rel="bookmark">${search_item.get('title')}</a>
                </h3>

                <p class="small">${search_item.get('domain')}</p>
            </div>

        </article>
        </c:forEach>
    </div>

</aside>

</div>
<!--/.sidebar.col-md-3.col-lg-3-->

</div>
<!--/.row.content-->


<div class="section content bg-base">

<h2 class="section-title ribbon"><span>Контент</span></h2>

<div class="entries">

<div class="row">

    <c:forEach items="${content}" var="cont">
        <article class="entry type-post style-thumbnail-text col-sm-6 col-md-2 colheight-sm-1">

            <div class="entry-meta">
                <h4><a href="?content_id=${cont.get('id')}">${fn:substring(cont.get('title'), 0, 40)}</a></h4>
            </div>

            <span class="entry-title">
                    ${fn:substring(cont.get('text'), 0, 100)}...
            </span>

        </article>
    </c:forEach>
</div>
<!--row.-->

</div>
<!--entries.-->

</div>
<!--.section.content.content-secondary-->

</div>
<!--#main.container-->

<footer id="footer" class="footer-area">

    <div class="footer-top container">

        <div class="row">

            <div class="widget col-xs-12 col-sm-4">

                <h4 class="widget-title">Categories</h4>

                <ul class="entries links links-2-cols">
                    <li><a href="blog.html">Entertainment</a></li>
                    <li><a href="blog.html">Event</a></li>
                    <li><a href="blog.html">Technology</a></li>
                    <li><a href="blog.html">Social Media</a></li>
                    <li><a href="blog.html">Tactical</a></li>
                    <li><a href="reviews.html">Notebook</a></li>
                    <li><a href="reviews.html">Smartphone</a></li>
                    <li><a href="reviews.html">Tablet</a></li>
                </ul>

            </div>
            <!--/.col-3-->

            <div class="clearfix visible-xs"></div>

            <div class="widget col-xs-6 col-sm-2">

                <h4 class="widget-title">Information</h4>

                <ul class="entries links links">
                    <li><a href="#">About</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#">Advertise </a></li>
                    <li><a href="#">Terms &amp; Conditions</a></li>
                    <li><a href="#">Privacy</a></li>
                </ul>

            </div>
            <!--/.col-3-->

            <div class="widget col-xs-6 col-sm-2">

                <h4 class="widget-title">Follow Us</h4>

                <ul class="entries links">
                    <li><a href="blog.html"><i class="icon-facebook-sign icon-2x"></i> Facebook</a></li>
                    <li><a href="blog.html"><i class="icon-twitter-sign icon-2x"></i> Twitter</a></li>
                    <li><a href="blog.html"><i class="icon-google-plus-sign icon-2x"></i> Google Plus</a></li>
                    <li><a href="blog.html"><i class="icon-linkedin-sign icon-2x"></i> Linkedin</a></li>
                </ul>

            </div>
            <!--/.col-3-->

            <div class="clearfix visible-xs"></div>

            <div class="widget col-xs-12 col-sm-4">

                <h4 class="widget-title">Subscribe</h4>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="subscribeEmail" class="col-lg-2 control-label">EMAIL</label>

                        <div class="col-lg-10">
                            <input type="text" class="form-control" id="subscribeEmail" placeholder="Your Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">RSS</label>

                        <div class="col-lg-10">
                            <p class="form-control-static">
                                <a href=""><i class="icon-rss-sign"></i> Posts</a> &nbsp; &nbsp;
                                <a href=""><i class="icon-rss-sign"></i> Comments</a>
                            </p>
                        </div>
                    </div>
                </form>

            </div>
            <!--/.col-3-->

        </div>
        <!--row.-->

    </div>

    <div class="footer-bottom">

        <div class="container aligncenter">

            <p>&copy;2013 by <a href="http://coolorize.com">Coolorize Network</a>. All Right Reserved.

            <p>

        </div>

    </div>

</footer>

<script src="/js/jq/jquery-1.10.1.min.js"></script>

<script src="/js/jq/bootstrap.min.js"></script>

<script src="/js/jq/jquery.prettyPhoto.js"></script>


<script src="/js/jq/jquery.unveil.min.js"></script>

<script src="/js/main.js"></script>
