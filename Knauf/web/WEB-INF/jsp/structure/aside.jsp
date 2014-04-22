<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<aside class="sidebar">
    <div class="container">
        <div class="catalog section dark-title">
            <h2 class="title">Каталог</h2>
            <ul>
                <c:forEach items="${catalogue.get('WebSection').get('Childs')}" var="cat">
                    <c:if test="${catalogue_categories.contains(cat.get('Name'))}">
                        <li class="item "><a class="link" href="/catalogue/items/?catalogue=${cat.get('Name')}&title=${cat.get('DisplayName')}">${cat.get('DisplayName')}</a>
                            <c:if test="${subcatalogue_name==cat.get('Name')}">
                                <ul class="subsection">
                                    <c:forEach items="${subcatalogue.get('CatalogItems')}" var="subcat">
                                        <li class="item "><a class="link" href="/catalogue/items/?catalogue=${cat.get('Name')}&catalogue_id=${subcat.get('Id')}&title=${subcat.get('Name')}">${subcat.get('Name')}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>

    </div>
    <div class="social">
        <ul>
            <li class="item fb">
                <a class="link" href="http://www.facebook.com/pages/Knauf-Insulation/118013834987163"
                   title="Facebook"></a>
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