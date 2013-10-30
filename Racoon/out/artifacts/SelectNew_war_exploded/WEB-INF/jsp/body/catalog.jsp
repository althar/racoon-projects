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
            <div class="categories row">
                <a href="" class="link_type-category">
                    <figure class="category_item">
                        <img src="img/category/1.jpg" class="img_type-category"/>
                        <figcaption>
                            <h2 class="title_type-category">Мойки для кухни</h2>
                        </figcaption>
                    </figure>
                </a>
                <a href="" class="link_type-category">
                    <figure class="category_item">
                        <img src="img/category/2.jpg" class="img_type-category"/>
                        <figcaption>
                            <h2 class="title_type-category">Смесители</h2>
                        </figcaption>
                    </figure>
                </a>
                <a href="" class="link_type-category">
                    <figure class="category_item">
                        <img src="img/category/3.jpg" class="img_type-category"/>
                        <figcaption>
                            <h2 class="title_type-category">Измельчители</h2>
                        </figcaption>
                    </figure>
                </a>
                <a href="" class="link_type-category">
                    <figure class="category_item">
                        <img src="img/category/4.jpg" class="img_type-category"/>
                        <figcaption>
                            <h2 class="title_type-category">Сантехника</h2>
                        </figcaption>
                    </figure>
                </a>
            </div>
            <div class="top-products row">
                <h2 class="title_type-topproducts">Лидеры продаж</h2>

                <a href="" class="link_type-topproducts">
                    <figure class="top-products_item">
                        <img src="img/products/1.jpg" class="img_type-topproduct"/>
                        <figcaption>
                            <h2 class="title_type-topproduct">Blanco Metra 6S Compact</h2>
                        </figcaption>
                    </figure>
                </a>

                <a href="" class="link_type-topproducts">
                    <figure class="top-products_item">
                        <img src="img/products/2.jpg" class="img_type-topproduct"/>
                        <figcaption>
                            <h2 class="title_type-topproduct">ZorG INOX ванна SENSE-W</h2>
                        </figcaption>
                    </figure>
                </a>

                <a href="" class="link_type-topproducts">
                    <figure class="top-products_item">
                        <img src="img/products/3.jpg" class="img_type-topproduct"/>
                        <figcaption>
                            <h2 class="title_type-topproduct">Измельчитель пищевых отходов Cover Control</h2>
                        </figcaption>
                    </figure>
                </a>

                <a href="" class="link_type-topproducts">
                    <figure class="top-products_item">
                        <img src="img/products/4.jpg" class="img_type-topproduct"/>
                        <figcaption>
                            <h2 class="title_type-topproduct">Ванна Roca Continental (170 x 70)</h2>
                        </figcaption>
                    </figure>
                </a>

            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>