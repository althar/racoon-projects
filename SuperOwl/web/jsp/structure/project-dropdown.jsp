<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="project-context">

    <span class="label">Projects:</span>
    <span id="project-selector" class="popover-trigger-element dropdown-toggle" data-toggle="dropdown">Recent projects <i class="fa fa-angle-down"></i></span>

    <!-- Suggestion: populate this list with fetch and push technique -->
    <ul class="dropdown-menu">
        <li>
            <a href="javascript:void(0);">Online e-merchant management system - attaching integration with the iOS</a>
        </li>
        <li>
            <a href="javascript:void(0);">Notes on pipeline upgradee</a>
        </li>
        <li>
            <a href="javascript:void(0);">Assesment Report for merchant account</a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="javascript:void(0);"><i class="fa fa-power-off"></i> Clear</a>
        </li>
    </ul>
    <!-- end dropdown-menu-->

</div>
