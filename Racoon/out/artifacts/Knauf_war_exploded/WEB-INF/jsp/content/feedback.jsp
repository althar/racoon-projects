<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="content layout">

    <ul class="breadcrumbs">
        <li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li>
        <li class="item">Обратная связь</li>
    </ul>
    <div class="container section dark-title">

        <h2>Обратная связь</h2>
        &nbsp;

        <form novalidate="" action="/service/feedback" class="" enctype="application/x-www-form-urlencoded" id="feedback_form" method="post">
            <div class="control-group ">
                <label class="control-label" for="feedback_fio">
                    Ваше имя
                    <span class="input-required"></span>
                </label>

                <div class="controls">
                    <div class=" ">
                        <input type="text" id="feedback_fio" name="feedback_fio" value="Рут Рутович" placeholder="Василий" style="width: 400px; " required="" maxlength="255">
                    </div>
                </div>
            </div>
            <div class="control-group ">
                <label class="control-label" for="feedback_email">
                    Ваш электронный адрес
                    <span class="input-required"></span>
                </label>

                <div class="controls">
                    <div class=" ">
                        <input type="email" id="feedback_email" name="feedback_email" value="vacuum@korden.ru" placeholder="mail@example.com" style="width: 400px; " required="" maxlength="255">
                    </div>
                </div>
            </div>
            <div class="control-group ">
                <label class="control-label" for="feedback_title">
                    Тема
                    <span class="input-required"></span>
                </label>

                <div class="controls">
                    <div class=" ">
                        <input type="text" id="feedback_title" name="feedback_title" value="" placeholder="Тема сообщения" style="width: 400px; " required="" maxlength="255">
                    </div>
                </div>
            </div>
            <div class="control-group ">
                <label class="control-label" for="feedback_text">
                    Ваше сообщение
                    <span class="input-required"></span>
                </label>

                <div class="controls">
                    <textarea id="feedback_text" name="feedback_text" placeholder="Введите текст сообщения" style="width: 400px; height: 8em;" required="" maxlength="2000"></textarea>
                </div>
            </div>
            <div class="control-group">
                <div class="controls"><input class="btn btn-blue" type="submit" name="submit" value="Оставить отзыв"></div>
            </div>
        </form>
    </div>
</div>