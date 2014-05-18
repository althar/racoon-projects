<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="col-md-4 col-lg-4 md-pull-right">

    <aside class="widget">

        <h3 class="widget-title ribbon ribbon-focus"><span>Теги</span></h3>

        <div class="tagcloud">
            <c:forEach items="${semantics}" var="sem">
                <a href="?semantics_id=${sem.getValue('id')}">${sem.getValue('word')}</a>
            </c:forEach>
        </div>

    </aside>

</div>