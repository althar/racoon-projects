<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="content">
    <p>
        Подари совместно с друзьями
        что-нибудь другу, или предложи
        друзьям сделать общий подарок тебе.
        Подарки будут доставлены адресату! 
    </p>
    <a href="/user/terms_of_service">Подробнее</a>

    <div class="row-fluid">
        <figure class="span6">
            <img src="../../img/happy_faces_1.png"/>
            <figcaption>
                <a href="/user/wish_list?friends_wish=true"><button class="btn button">Узнать желания друзей</button></a>
            </figcaption>
        </figure>
        <figure class="span6">
            <img src="../../img/happy_faces_2.png"/>
            <figcaption>
                <a href="/user/catalogue"><button class="btn button">Выбрать подарок</button></a>
            </figcaption>
        </figure>
    </div>
</div>
</section>
</body>
</html>	