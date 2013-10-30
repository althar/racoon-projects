<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div class="find-friends">
    <p>Посмотри, какие подарки хотят получить Ваши друзья:</p>

    <div class="row-fluid">
        <div class="span12">
            <div class="friends_block">
                <div class="wish-list">
                    <div class="wish-list_item">
                        <div class="wish-list_image">
                            <img src="img/photo1.jpg"/>
                        </div>
                        <div class="wish-list_text">
                            <h3>Юрий Авинов
                                <small>Yuri Avinov</small>
                            </h3>
                            <h4>Москва</h4>

                            <p>
                                Xочет получить 24 декабря на свой День рождения
                                <a href="#">Мотоцикл ИЖ Планета-5</a>
                            </p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="wish-list_item">
                        <div class="wish-list_image">
                            <img src="img/photo2.jpg"/>
                        </div>
                        <div class="wish-list_text">
                            <h3>Андрей Попов
                                <small>Andr Ppv</small>
                            </h3>
                            <h4>Москва</h4>

                            <p>
                                Xочет получить 1 января на Новый год<br/>
                                <a href="#">Автомат АК-147</a>
                            </p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="wish-list_item last">
                        <div class="wish-list_image">
                            <img src="img/photo3.jpg"/>
                        </div>
                        <div class="wish-list_text">
                            <h3>Ilya Merenzon</h3>
                            <h4>Работает в Russia! magazine</h4>

                            <p>
                                Xочет получить 23 февраля на День защитника отечества
                                <a href="#">Цветные карандаши</a> и <a href="#">Буденовку</a>
                            </p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>
</body>
</html>	