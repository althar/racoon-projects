<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/css/lex.css" media="screen">



<div class="span9 widget">
    <div class="widget-header">
        <span class="title">Курс разговорного английского</span>
        <div class="toolbar">
            <div class="btn-group">
                <span class="btn">Попробывать демо</span>
                <span class="btn">Купить</span>
            </div>
        </div>
    </div>
    <div class="widget-content">
        <div class="right-column-left-block">
            <div class="shop-item">
                <img src="/img/shop/courses/course_1.png" class="img">
            </div>
            <div class="shop-item">
                <span>Отзывы об курсе</span>
                            <span>
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                                <img src="/img/icons/star.png">
                            </span>
                <span>4.8</span>
            </div>
            <div class="shop-item">
                <span>Дата публикации</span>
                <span>01.08.2013</span>
            </div>
            <div class="shop-item">
                <span>Автор</span>
                <span class="shop-author">Иваннова Анна Петровна</span>
            </div>
        </div>

        <div class="right-column-right-block">
            <div>
                <span class="shop-item-title">Описание</span>
                            <span class="shop-price">
                                <span class="shop-item-price-text">Цена</span>
                                <span class="shop-item-price-number">5'000,00 руб</span>
                            </span>
            </div>
            <div class="shop-item-text">
                Курс разговорного английского языка для взрослых включает в себя 6 языковых уровней:
                от начального до профессионального. Быстрое развитие языковых навыков происходит
                благодаря разговорной практике с англоязычными педагогами и интерактивной обучающей
                платформе. Курс направлен на изучение современного английского для повседневной жизни.
                Каждый раздел курса разработан так, чтобы помочь студентам достичь желаемой цели
                - заговорить по-английски свободно и уверенно
            </div>
        </div>
        <div class="mt-10">
            <button class="btn btn-info">Задать вопрос автору</button>

            <img src="/img/social/social_1.png"></img>
            <img src="/img/social/social_2.png"></img>
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