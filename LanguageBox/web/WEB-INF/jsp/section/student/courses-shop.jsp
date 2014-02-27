<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/css/lex.css" media="screen">
<div class="courses-shop">

    <div class="content-shop">
        <div class="left-column-shop">
            <div style="margin-bottom: 20px;">
                <div class="title-shop">Интернет-магазин Language Box</div>
            </div>
            <div><input type="text" placeholder="поиск по магазину"></div>
            <div class="shop-items">
                <div class="books-shop-all">
                    <span>Все языки</span>
                    <span style="float: right;">124</span>
                </div>
                <div class="books-shop books-shop-active">
                    <span>Английский</span>
                    <span style="float: right;">5</span>
                </div>
                <div class="books-shop">
                    <span>Немецкий</span>
                    <span style="float: right;">15</span>
                </div>
                <div class="books-shop">
                    <span>Французкий</span>
                    <span style="float: right;">55</span>
                </div>
                <div class="books-shop">
                    <span>Итальянский</span>
                    <span style="float: right;">12</span>
                </div>
                <div class="books-shop">
                    <span>Испанский</span>
                    <span style="float: right;">4</span>
                </div>
            </div>
            <div class="shop-items">
                <div class="books-shop-all">
                    <span>Все урони</span>
                    <span style="float: right;">124</span>
                </div>
                <div class="books-shop books-shop-active">
                    <span>Beginner</span>
                    <span style="float: right;">5</span>
                </div>
                <div class="books-shop">
                    <span>Elementary</span>
                    <span style="float: right;">15</span>
                </div>
                <div class="books-shop">
                    <span>Pre-intermediate</span>
                    <span style="float: right;">55</span>
                </div>
                <div class="books-shop">
                    <span>Intermediate</span>
                    <span style="float: right;">12</span>
                </div>
                <div class="books-shop">
                    <span>Upper-intermediate</span>
                    <span style="float: right;">12</span>
                </div>
                <div class="books-shop">
                    <span>Advanced</span>
                    <span style="float: right;">12</span>
                </div>
            </div>
        </div>
        <div class="right-column-shop">
        <div class="selector-shop">
            <span style="float: right;">
                <div>Сортировка</div>
                <div class="shop-arrow-down">Дата публикации</div>
                <div>Цена</div>
            </span>
        </div>
            <div class="widget right-column-border">
                <div class="widget-header">
                    <span class="title">Курс разговорного английского</span>
                    <span class="lex-header-right">
                        <span class="shop-try-demo">Попробывать демо</span>
                        <span class="shop-buy">Купить</span>
                    </span>
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
                </div>

            </div>
            <div class="widget right-column-border">
                <div class="widget-header">
                    <span class="title">Курс разговорного английского</span>
                    <span class="lex-header-right-btn">
                        <span class="shop-try-demo-btn"><button class="btn btn-info">Попробывать демо</button></span>
                        <span class="shop-buy-btn"><button class="btn btn-primary">Купить</button></span>
                    </span>
                </div>
                <div class="widget-content">
                    <div class="right-column-left-block">
                        <div class="shop-item">
                            <img src="/img/shop/courses/course_2.png" class="img">
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
                </div>

            </div>
            <div class="widget right-column-border">
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
                            <img src="/img/shop/courses/course_3.png" class="img">
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
                </div>

            </div>
            <div class="widget right-column-border">
                <div class="widget-header">
                    <span class="title">Курс разговорного английского</span>
                    <span class="lex-header-right">
                        <span class="shop-try-demo">Попробывать демо</span>
                        <span class="shop-buy">Купить</span>
                    </span>
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
                </div>

            </div>

        </div>
    </div>
</div>