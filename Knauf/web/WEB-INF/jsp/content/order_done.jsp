<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="content layout">

    <div class="container section dark-title">

        <div class="container white">
            <h3>Сообщение сайта</h3>
            <p>Ваш заказ принят в обработку. Его номер ${order_number}. Состояние заказа Вы можете отслеживать в личном кабинете.</p>
        </div>

    </div>
</div>