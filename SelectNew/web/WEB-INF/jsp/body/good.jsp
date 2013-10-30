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
    <div class="main-content_breadcrumb">
        <ul class="breadcrumb">
            <li class="breadcrumb_item">
                <a href="catalog.html" class="link_type-breadcrumb">Каталог</a>
            </li>
            <li class="breadcrumb_item">
                <span class="breadcrumb-divider">-></span>
            </li>
            <li class="breadcrumb_item">
                <a href="goods.html" class="link_type-breadcrumb">Мойки</a>
            </li>
            <li class="breadcrumb_item">
                <span class="breadcrumb-divider">-></span>
            </li>
            <li class="breadcrumb_item">
                Blanco Zia 40s Silgranit PuraDur
            </li>
        </ul>
    </div>
    <div class="product-char row">
        <div class="product-char_inner">

            <div class="product-char_foto">
                <div class="product_main-foto">
                    <a href="">
                        <img src="images/products/1.jpg" class="img_type-products">
                    </a>
                </div>
                <div class="product_main-foto_preview">
                    <ul class="row">
                        <li class="main-foto_preview-item">
                            <a href="">
                                <img src="images/products/small_pic/1.png" class="img_type-products">
                            </a>
                        </li>
                        <li class="main-foto_preview-item">
                            <a href="">
                                <img src="images/products/small_pic/1.png" class="img_type-products">
                            </a>
                        </li>
                        <li class="main-foto_preview-item">
                            <a href="">
                                <img src="images/products/small_pic/1.png" class="img_type-products">
                            </a>
                        </li>
                        <li class="main-foto_preview-item">
                            <a href="">
                                <img src="images/products/small_pic/1.png" class="img_type-products">
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="product-char_descr">
                <h1>Blanco Zia 40s Silgranit PuraDur</h1>
                <div class="product-buy">
                    <div class="product-price">Цена 8 000 руб.</div>
                    <button class="button blue button-buy">Купить</button>
                    <span class="product-delivery"><i class="icon icon-delivery"></i> Бесплатная доставка</span>
                </div>
                <div class="product-description">
                    <p>
                        Blanco Zia 40s Silgranit PuraDur- небольшого размера мойка, в которой сочетаются вместительная чаша и удобное крыло. Устанавливается в шкаф шириной 40 см
                    </p>
                </div>

                <div class="product-features">
                    <h2>Характеристики Blanco Zia 40s Silgranit PuraDur</h2>
                    <ul>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Размер (Высота х Ширина х Глубина)</label>
                      </span>
                            <span>19 х 61.05 х 50 см</span>
                        </li>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Цвет</label>
                      </span>
                            <span>Черный</span>
                        </li>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Форма</label>
                      </span>
                            <span>Нестандартная</span>
                        </li>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Материал</label>
                      </span>
                            <span>Гранит</span>
                        </li>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Расположение крыла</label>
                      </span>
                            <span>Универсальная</span>
                        </li>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Вес</label>
                      </span>
                            <span>12.55 кг</span>
                        </li>
                        <li class="product-features_item">
                      <span class="features-label">
                        <label>Страна производитель</label>
                      </span>
                            <span>Германия</span>
                        </li>
                    </ul>
                    <button class="button button-small">Подробное описание</button>
                </div>

                <div class="product-additional">
                    <div class="product-additional_inner">

                        <form class="color-choice">
                            <label>Доступные цвета:</label>
                            <span class="color"></span>
                            <select>
                                <option>Черный</option>
                                <option>Черный</option>
                                <option>Черный</option>
                                <option>Черный</option>
                            </select>
                        </form>

                        <div class="warehouse">
                            <!-- <i class="icon icon-warehouse"></i> -->
                            <strong>На складе.</strong>
                            <p>Ожидаемая дата передачи в службу доставки: 29 августа</p>
                        </div>

                        <div class="support">
                            <!-- <i class="icon icon-support"></i> -->
                            <strong>Помощь в выборе</strong>
                            <p>Вы еще не определились, какую модель выбрать?<br>Обращайтесь к нашим консультантам.</p>
                            <p>
                                <strong class="phone">+7 499 390-73-51</strong>
                                Пн-Пт с 08:00 до 22:00 Сб-Вс с 08:00 до 18:00</p>
                        </div>

                        <div class="feedback">
                            <!-- <i class="icon icon-feedback"></i> -->
                            <strong>Заказать обратный звонок</strong>
                            <form>
                                <input type="text">
                                <button class="button">Заказать</button>
                            </form>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="similar-products">
        <div class="similar-products_inner">
            <h2>С этим товаром чаще всего покупают</h2>
            <ul class="row">
                <li class="similar-products_item">
                    <h3>Фильтр для воды</h3>
                    <a href="">
                        <figure>
                            <img src="images/products/4.jpg">
                            <figcaption>
                                <p class="name">GROHE ST001-01</p>
                                <p class="price">4 600 руб.</p>
                            </figcaption>
                        </figure>
                    </a>
                </li>
                <li class="similar-products_item">
                    <h3>Смеситель</h3>
                    <a href="">
                        <figure>
                            <img src="images/products/2.jpg">
                            <figcaption>
                                <p class="name">GROHE ST001-01</p>
                                <p class="price">4 600 руб.</p>
                            </figcaption>
                        </figure>
                    </a>
                </li>
                <li class="similar-products_item">
                    <h3>Измельчитель</h3>
                    <a href="">
                        <figure>
                            <img src="images/products/3.jpg">
                            <figcaption>
                                <p class="name">GROHE ST001-01</p>
                                <p class="price">4 600 руб.</p>
                            </figcaption>
                        </figure>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="terms">
        <div class="terms_inner">
            <ul class="row">
                <li class="terms_item">
                    <i class="icon icon-free_delivery"></i>
                    <strong>Бесплатная доставка</strong> по всей России
                </li>
                <li class="terms_item">
                    <i class="icon icon-refresh"></i>
                    <strong>Возврат товара</strong> в течении 365 дней
                </li>
                <li class="terms_item">
                    <i class="icon icon-support"></i>
                    <strong>Служба поддержки</strong> 8-800-980-80-80
                </li>
                <li class="terms_item">
                    <i class="icon icon-brand"></i>
                    Более 900 подлинных брендов
                </li>
            </ul>
        </div>
    </div>

    </div>
    </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>