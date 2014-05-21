<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${not empty catalogue}">
    <aside class="sidebar">
        <div class="container">
            <div class="catalog section dark-title">
                <h2 class="title">Каталог</h2>
                <ul>

                    <%-- Simple --%>
                    <%--<c:forEach items="${catalogue.get('WebSection').get('Childs')}" var="cat">--%>
                        <%--&lt;%&ndash;<c:if test="${catalogue_categories.contains(cat.get('Name'))}">&ndash;%&gt;--%>
                        <%--<c:if test="${cat.get('Name')!='div_travel'&&cat.get('Name')!='div_luxury_gifts'}">--%>
                            <%--&lt;%&ndash;${fn:toUpperCase(fn:substring(linkName, 0, 1))}${fn:toLowerCase(fn:substring(linkName, 1,fn:length(linkName)))}&ndash;%&gt;--%>
                            <%--<li class="item ">--%>
                                <%--<a class="link" href="/catalogue/items/?catalogue=${cat.get('Name')}&title=${cat.get('DisplayName')}">${cat.get('DisplayName')}</a>--%>
                                <%--<c:if test="${subcatalogue_name==cat.get('Name')}">--%>
                                    <%--<ul class="subsection">--%>
                                        <%--<c:forEach items="${subcatalogue.get('CatalogItems')}" var="subcat">--%>

                                            <%--<c:set var="subcat_name" value="${fn:toUpperCase(fn:substring(subcat.get('Name'), 0, 1))}${fn:toLowerCase(fn:substring(subcat.get('Name'), 1,fn:length(subcat.get('Name'))))}"></c:set>--%>
                                            <%--<c:if test="${subcat_name!='Аудио-видео техника'}">--%>
                                                <%--<li class="item ">--%>
                                                    <%--<a class="link" href="/catalogue/items/?catalogue=${cat.get('Name')}&catalogue_id=${subcat.get('Id')}&title=${subcat_name}">${subcat_name}</a>--%>
                                                <%--</li>--%>
                                            <%--</c:if>--%>
                                        <%--</c:forEach>--%>
                                    <%--</ul>--%>
                                <%--</c:if>--%>
                            <%--</li>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>

                    <%-- Facet --%>
                    <c:forEach items="${catalogue.get('Sections')}" var="cat">
                        <%--<c:if test="${catalogue_categories.contains(cat.get('Name'))}">--%>
                        <c:if test="${cat.get('SectionName')!='div_travel'&&cat.get('SectionName')!='div_luxury_gifts'}">
                            <%--${fn:toUpperCase(fn:substring(linkName, 0, 1))}${fn:toLowerCase(fn:substring(linkName, 1,fn:length(linkName)))}--%>
                            <li class="item ">
                                <a class="link" href="/catalogue/items/?catalogue=${cat.get('SectionName')}&title=${cat.get('DisplayName')}">${cat.get('DisplayName')}</a>
                                <c:if test="${subcatalogue_name==cat.get('SectionName')||parent_catalogue==cat.get('SectionName')}">
                                    <ul class="subsection">
                                        <c:forEach items="${cat.get('Childs')}" var="subcat">

                                            <c:set var="subcat_name" value="${fn:toUpperCase(fn:substring(subcat.get('DisplayName'), 0, 1))}${fn:toLowerCase(fn:substring(subcat.get('DisplayName'), 1,fn:length(subcat.get('DisplayName'))))}"></c:set>
                                            <c:if test="${subcat_name!='Аудио-видео техника'}">
                                                <li class="item ">
                                                    <a class="link" href="/catalogue/items/?catalogue=${subcat.get('SectionName')}&parent_catalogue=${parent_catalogue}&catalogue_id=&title=${subcat_name}">${subcat_name}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <c:if test="${not empty catalogue_name&&page!='login'}">
                <div class="price section dark-title">
                    <h2 class="title">Цены</h2>
                    <ul>
                        <li class="item "><a class="link" href="/catalogue/by_price?catalogue=${catalogue_name}&price=0-500000&title=${title}">Любые</a></li>
                        <li class="item "><a class="link" href="/catalogue/by_price?catalogue=${catalogue_name}&price=0-500&title=${title}"> до 500 теплуносов
                        </a></li>
                        <li class="item "><a class="link" href="/catalogue/by_price?catalogue=${catalogue_name}&price=500-1000&title=${title}"> 500 - 1000 теплуносов
                        </a></li>
                        <li class="item "><a class="link" href="/catalogue/by_price?catalogue=${catalogue_name}&price=1000-2000&title=${title}"> 1000 - 2000 теплуносов
                        </a></li>
                        <li class="item "><a class="link" href="/catalogue/by_price?catalogue=${catalogue_name}&price=2000-500000&title=${title}"> выше 2000 теплуносов
                        </a></li>
                    </ul>
                </div>
            </c:if>
        </div>
        <div class="social">
            <ul>
                <li class="item fb">
                    <a class="link" href="http://www.facebook.com/pages/Knauf-Insulation/118013834987163" title="Facebook"></a>
                </li>
                <li class="item yt">
                    <a class="link" href="http://www.youtube.com/user/knaufinsulationru" title="Youtube"></a>
                </li>
            </ul>
        </div>
        <div class="learn-more">
            <a href="http://www.knaufinsulation.ru/mineralovatnyj-uteplitel">Узнать больше о продуктах<br>KNAUF
                Insulation</a>
        </div>
    </aside>
</c:if>