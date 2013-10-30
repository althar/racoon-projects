<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="/js/logic/admin/extract-goods.js"></script>
</head>
<body>
Импорт товаров:
<select>
    <option>grohe</option>
    <option>franke</option>
    <option>wasserkraft</option>
    <option>blanco</option>
</select>
<input type="button" id="extract-goods" value="Поехали">
<br>
<div id="memo"></div>
</body>

</html>