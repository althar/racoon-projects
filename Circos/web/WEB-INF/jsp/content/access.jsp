<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:choose>
    <c:when test="${action=='registration'}">
        <form action="/registration" method="POST">
            <h3>Регистрация</h3>
            <br>${error}<br>
            <label for="login">Логин</label>
            <input type="text" id="login" name="login">
            <br>
            <label for="password">Пароль</label>
            <input type="text" id="password" name="password">
            <br>
            <label for="name">Имя</label>
            <input type="text" id="name" name="name">
            <br>
            <label for="phone">Телефон</label>
            <input type="text" id="phone" name="phone">
            <br>
            <label>Роль</label>
            <label><input type="checkbox" name="role" value="CLIENT">Пользователь</label>
            <label><input type="checkbox" name="role" value="PARTNER">Партнер</label>
            <br>
            <input type="submit" value="Зарегистрироваться">
        </form>
    </c:when>
    <c:otherwise>
        <form action="/login" method="POST">
            <h3>Вход</h3>
            <br>${error}<br>
            <label for="login">Логин</label>
            <input type="text" id="login" name="login">
            <br>
            <label for="password">Пароль</label>
            <input type="text" id="password" name="password">
            <br>
            <input type="submit" value="Войти">

            <h3>Вход через "Вконтакте"</h3>
            <a href="https://oauth.vk.com/authorize?client_id=4378615&scope=friends,wall,messages,notify,offline&redirect_uri=http://<%=MainConfig.host%>/vklogin&display=page">Войти</a>
        </form>
    </c:otherwise>
</c:choose>
