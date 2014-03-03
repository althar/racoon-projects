<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/css/lex.css" media="screen">



<div class="widget span3">
    <div class="widget-header">
        <span class="title">Репититор</span>
    </div>
    <div class="widget-content" style="padding-top: 10px;">
        <div class="profile-image">
            <img src="/img/teacher/rep.png">
        </div>
        <div class="profile-reputation">
            <span>4 отзыва</span>
                            <span>
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                            </span>
                            <span>
                                4.8
                            </span>
        </div>
        <div class="profile-email">
            <span>Email </span>
            <span>anna2000@mail.ru</span>
        </div>
        <div class="profile-phone">
            <span>Телефон </span>
            <span>89165900080</span>
        </div>
        <div class="profile-send-message">
            <button class="btn btn-info">Отправить сообщение</button>
        </div>
    </div>
</div>


<div class="span9 widget">
    <div class="widget-header">
        <span class="title">Отзывы</span>
    </div>
    <div class="widget-content">
        <div class="review">
            <div class="review-author">
                <span class="review-author-img">
                    <img src="/img/courses_review/review_1.png"/>
                </span>
                <span class="review-author-name">Мариан Воробъева</span>
                <span class="review-author-date">30 мая 2013</span>
                <span class="review-author-star">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                </span>
                <span class="review-author-rating">5.0</span>
            </div>
            <div class="review-message">
                Слова самые распространенные,т.е. для изучения языка очень полезно,а вот для высокого уровня знания языка будет уже не достаточно.
            </div>
        </div>


        <div class="review">
            <div class="review-author">
                <span class="review-author-img">
                    <img src="/img/courses_review/review_2.png"/>
                </span>
                <span class="review-author-name">Калинин Николай</span>
                <span class="review-author-date">1 июля 2013</span>
                <span class="review-author-star">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                </span>
                <span class="review-author-rating">5.0</span>
            </div>
            <div class="review-message">
                Хоть в учебнике нет ни слова по-русски, в нем настолько просто и понятно изложен материал,
                что получаешь удовольствие при изучении грамматики и выполнении упражнений! Если приобретёте этот учебник - не пожалеете.
            </div>
        </div>


        <div class="review">
            <div class="review-author">
                <span class="review-author-img">
                    <img src="/img/courses_review/review_1.png"/>
                </span>
                <span class="review-author-name">Бойко Мария</span>
                <span class="review-author-date">2 июля 2013</span>
                <span class="review-author-star">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                    <img src="/img/icons/star.png">
                </span>
                <span class="review-author-rating">4.0</span>
            </div>
            <div class="review-message">
                Очень удобно изучать язык, имея перед глазами слова и их перевод, а также использование этих слов в контексте.
                Можно не носить с собой словари или учебники, а взять несколько карточек и в дороге,
                в очереди, в транспорте изучать и повторять слова. Единственное, что не удобно, так это то,
                что они представлены не разрезанными, а в виде брошюры. Хочу попробовать еще и разрезанные карточки.
            </div>
        </div>

        <div class="summon-review-form">
            <div class="form-actions review-btn">
                <button class="btn btn-primary" onclick="showform();">Оставить отзыв</button>
            </div>
        </div>

        <div class="review-form">
            <form class="form-vertical">
                <div class="control-group">
                    <div class="controls">
                        <textarea id="review" name="review" class="review-text" placeholder="Ваш отзыв"></textarea>
                    </div>
                </div>
                <div class="form-actions review-btn">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <button class="btn" type="reset">Очистить</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    function showform(){
        $('.summon-review-form').hide();
        $('.review-form').show();
    }
</script>