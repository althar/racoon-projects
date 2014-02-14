<!DOCTYPE html>
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
    <link href="/img/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic|PT+Sans+Narrow:400,700|Open+Sans:300italic,400italic,400,300,600,700&subset=latin,cyrillic'
          rel='stylesheet' type='text/css'>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/datepicker.css" rel="stylesheet" type="text/css">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.ui.js"></script>
    <script type="text/javascript" src="/js/jquery.carouFredSel-6.2.1-packed.js"></script>
    <script type="text/javascript" src="/js/bootstrap-collapse.js"></script>
    <script src="/js/mask.js"></script>
    <script src="/js/bootstrap-datepicker.ru.js"></script>
    <script src="/js/bootstrap-datepicker.js"></script>
    <script src="/js/bootstrap-collapse.js"></script>
    <script src="/js/jquery-numeric.js"></script>

    <meta name="keywords" content="${metaKeywords}сантехника, интернет магазин сантехники, сантехника со скидкой">
    <meta name="description"
          content="Широкий выбор сантехники. Вы можете купить сантехнику и ${category} со скидкой. Доставка по Москве и области.">

    <meta name="geo.region" content="RU"/>
    <meta name="geo.placename" content="Москва"/>
    <meta name="geo.position" content="55.751242;37.618422"/>
    <meta name="ICBM" content="55.751242, 37.618422"/>

    <meta http-equiv="Content-Language" content="ru"/>
    <meta name='yandex-verification' content='4092d262164ace17'/>
    <meta name="google-site-verification" content="u704LbupGKrI4L-13xG9Q78e0mPlLvqTqxcLfOUlzVw"/>

</head>
<body>
<!-- begin of Top100 code -->

<script id="top100Counter" type="text/javascript" src="http://counter.rambler.ru/top100.jcn?2911284"></script>
<noscript>
    <a href="http://top100.rambler.ru/navi/2911284/">
        <img src="http://counter.rambler.ru/top100.cnt?2911284" alt="Rambler's Top100" border="0"/>
    </a>

</noscript>
<!-- end of Top100 code -->
<script type="text/javascript">

    <%-- Google analytics--%>
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-41292830-1']);
    _gaq.push(['_setDomainName', 'select-st.ru']);
    _gaq.push(['_setAllowLinker', true]);
    _gaq.push(['_trackPageview']);

    (function () {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();

    <%-- Yandex metrix --%>
    (function (d, w, c) {
        (w[c] = w[c] || []).push(function () {
            try {
                w.yaCounter21380827 = new Ya.Metrika({id: 21380827,
                    webvisor: true,
                    clickmap: true,
                    trackLinks: true,
                    accurateTrackBounce: true});
            } catch (e) {
            }
        });

        var n = d.getElementsByTagName("script")[0],
                s = d.createElement("script"),
                f = function () {
                    n.parentNode.insertBefore(s, n);
                };
        s.type = "text/javascript";
        s.async = true;
        s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

        if (w.opera == "[object Opera]") {
            d.addEventListener("DOMContentLoaded", f, false);
        } else {
            f();
        }
    })(document, window, "yandex_metrika_callbacks");
</script>
<noscript>
    <div><img src="//mc.yandex.ru/watch/21380827" style="position:absolute; left:-9999px;" alt=""/></div>
</noscript>
<div class="body-bg"></div>
<header id="global-header" class="fixed">
    <nav>
        <h1>
            <a class="logotype" href="/" rel="home"><strong>САНТЕХНИКА</strong></a>
        </h1>

        <div class="pull-right">
					<span class="phone-number">
						<i class="icon icon-phone"></i> 8 499 390 73 51
					</span>
            <a class="cart" href="/Корзина/">
                <jsp:include page="/Корзина/Вкорзине"/>
            </a>
        </div>
    </nav>
    <nav id="site-menu">
        <a href="/" class="menu-item active">Главная</a>
        <a href="/Каталог" class="menu-item">Каталог товаров</a>
        <a href="/Доставка" class="menu-item">Доставка и оплата</a>
        <a href="/Скидки" class="menu-item">Скидки</a>
    </nav>
</header>
