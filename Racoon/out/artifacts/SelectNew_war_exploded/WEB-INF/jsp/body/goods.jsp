<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<meta charset="utf-8">
<%@ include file="/WEB-INF/jsp/section/head.jsp" %>
<body>
<section class="main">
    <%@ include file="/WEB-INF/jsp/section/header.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/main_menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/section/banner.jsp" %>
    <div class="main-content">
    <div class="main-content_inner">
    <div class="main-content_top catalog">
        <form class="main-content_search">
            <input type="search" class="search-field" placeholder="более 3000 наименований">
            <button type="submit" class="button search-button">Найти</button>
        </form>
        <div class="main-content_breadcrumb">
            <ul class="breadcrumb">
                <li class="breadcrumb_item">
                    <a href="catalog.html" class="link_type-breadcrumb">Каталог</a>
                </li>
                <li class="breadcrumb_item">
                    <span class="breadcrumb-divider">-></span>
                </li>
                <li class="breadcrumb_item">
                    Мойки
                </li>
            </ul>
        </div>
    </div>
    <div class="row">
    <aside class="sidebar">
        <div class="filters">
            <div class="filters_inner">
                <h3 class="title_type-products">Подбор по параметрам</h3>
                <form>
                    <div class="filters_form_row filters_price">
                        <h3 class="title_type-filters">Цена</h3>
                        <div class="filters_price_choose">
                            от
                            <input type="text" class="text-field field_type-from">
                            до
                            <input type="text" class="text-field field_type-to">
                            руб.
                        </div>
                    </div>
                    <div class="filters_form_row filters_maker">
                        <h3 class="title_type-filters">Производитель</h3>
                        <ul class="filters_maker_list">
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    Blanco
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    GROHE
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    Franke
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    Wasser Kraft
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    Florentina
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    GranFest
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="filters_form_row filters_placing">
                        <h3 class="title_type-filters">Установка</h3>
                        <ul class="filters_placing_list">
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    врезная
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    интегрированная
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    накладная
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="filters_form_row filters_material">
                        <h3 class="title_type-filters">Материал</h3>
                        <ul class="filters_material_list">
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    гранит
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    керамика
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    нержавеющая сталь
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="filters_form_row filters_form">
                        <h3 class="title_type-filters">Форма</h3>
                        <ul class="filters_form_list">
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    квадратная
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    круглая
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="checkbox">
                                    прямоугольная
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="filters_form_row filters_angle">
                        <h3 class="title_type-filters">Угловая</h3>
                        <ul class="filters_angle_list">
                            <li>
                                <label class="checkbox">
                                    <input type="radio" name="filters_angle">
                                    да
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="radio" name="filters_angle">
                                    нет
                                </label>
                            </li>
                            <li>
                                <label class="checkbox">
                                    <input type="radio" name="filters_angle">
                                    неважно
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="filters_form_row_last">
                        <label class="checkbox">
                            <input type="checkbox">
                            Измельчитель пищевых отходов
                        </label>
                    </div>
            </div>
            </form>
        </div>
    </aside>
    <div class="products-list">
        <div class="products-list_inner">
            <div class="products-list_item">
                <h3 class="title_type-products"><a href="product.html" class="link_type-header">Blanco Metra 6S Compact</a></h3>
                <div class="products-list_item_images">
                    <img src="images/products/small_pic/1.png" class="img_type-products">
                    <img src="images/products/small_pic/2.png" class="img_type-products">
                    <img src="images/products/small_pic/3.png" class="img_type-products">
                </div>
                <p class="product-list_item_price">Цена 9 651 руб.</p>
                <p class="product-list_item_descr">
                    Врезная полуторная мойка, гранит, прямоугольная форма, оборачиваемая, размеры мойки (ШхГ): 78х50 см
                </p>
            </div>
            <div class="products-list_item">
                <h3 class="title_type-products"><a href="product.html" class="link_type-header">Blanco Metra 6S Compact</a></h3>
                <div class="products-list_item_images">
                    <img src="images/products/small_pic/2.png" class="img_type-products">
                </div>
                <p class="product-list_item_price">Цена 9 651 руб.</p>
                <p class="product-list_item_descr">
                    Врезная полуторная мойка, гранит, прямоугольная форма, оборачиваемая, размеры мойки (ШхГ): 78х50 см
                </p>
            </div>
            <div class="products-list_item">
                <h3 class="title_type-products"><a href="product.html" class="link_type-header">Blanco Metra 6S Compact</a></h3>
                <div class="products-list_item_images">
                    <img src="images/products/small_pic/3.png" class="img_type-products">
                </div>
                <p class="product-list_item_price">Цена 9 651 руб.</p>
                <p class="product-list_item_descr">
                    Врезная полуторная мойка, гранит, прямоугольная форма, оборачиваемая, размеры мойки (ШхГ): 78х50 см
                </p>
            </div>
        </div>
        <div class="products-list_inner">
            <div class="pagination">
                <ul class="pages">
                    <li>Страницы:</li>
                    <li>
                        <a href="" class="page">1</a>
                    </li>
                    <li>
                        <span class="divider">|</span>
                    </li>
                    <li>
                        <a href="" class="page">2</a>
                    </li>
                    <li>
                        <span class="divider">|</span>
                    </li>
                    <li>
                        <a href="" class="page">3</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    </div>
    </div>
    </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>