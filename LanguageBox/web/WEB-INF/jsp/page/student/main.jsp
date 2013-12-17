<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>Language Box</title>
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic" media="screen" rel="stylesheet" type="text/css" />

    <link href="/css/normalize.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="/css/application.css" media="screen" rel="stylesheet" type="text/css" />

    <script src="/js/jq/jquery-2.0.3.js" type="text/javascript"></script>
    <script src="/js/common/all.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/section/header_student.jsp" %>
<div class='main_content'>
<div class='main_content-inner row'>
<div class='row'>
    <div class='small-4 column'>
        <div class='white_block'>
            <h3>Мои достижения</h3>
            <div class='words_progress'>
                <p>
                    В словаре 32 слова
                </p>
                <p>
                    Изучено 15
                </p>
                <p>
                    Всего слов 123
                </p>
            </div>
            <div class='lesson_progress'>
                <p>
                    Пройдено 4 урока
                </p>
                <p>
                    Всего 9 уроков
                </p>
            </div>
            <div class='task_progress'>
                <p>
                    Выполнено  27 заданий
                </p>
                <p>
                    Правильно 22
                </p>
                <p>
                    Не правильно 5
                </p>
            </div>
        </div>
    </div>
    <div class='small-8 column'>
        <div class='white_block'>
            <h3>Как пользоваться сервисом</h3>
            <div class='flex-video'>
                <img src="/images/fake-video.png" />
            </div>
            <a href="#">Вся документация</a>
        </div>
    </div>
</div>
<div class='row'>
<div class='small-4 column'>
    <div class='white_block'>
        <h3>Новости</h3>
        <table class='table'>
            <tbody>
            <tr>
                <td>
                    Возможность добавления pdf файлов
                </td>
                <td>
                    <i class='icons-star'></i>
                </td>
                <td>
                    55
                </td>
            </tr>
            <tr>
                <td>
                    Возможность добавления pdf файлов
                </td>
                <td>
                    <i class='icons-star'></i>
                </td>
                <td>
                    55
                </td>
            </tr>
            <tr>
                <td>
                    Возможность добавления pdf файлов
                </td>
                <td>
                    <i class='icons-star'></i>
                </td>
                <td>
                    55
                </td>
            </tr>
            <tr>
                <td>
                    Возможность добавления pdf файлов
                </td>
                <td>
                    <i class='icons-star'></i>
                </td>
                <td>
                    55
                </td>
            </tr>
            <tr>
                <td>
                    Возможность добавления pdf файлов
                </td>
                <td>
                    <i class='icons-star'></i>
                </td>
                <td>
                    55
                </td>
            </tr>
            </tbody>
        </table>
        <a href="#">Все новости</a>
    </div>
</div>
<div class='small-8 column'>
    <div class='white_block'>
        <h3>Курсы</h3>
        <div class='courses_list'>
            <div class='courses_list-item'>
                <div class='courses-image'>
                    <img src="/images/course_preview.png" />
                </div>
                <div class='courses-features'>
                    <ul class='features_list no-bullet'>
                        <li>
                            <label>Курс:</label>
                            <span class='feature'>Straightforward</span>
                        </li>
                        <li>
                            <label>Уровень:</label>
                            <span class='feature'>Pre-intermediate</span>
                        </li>
                        <li>
                            <label>Автор:</label>
                            <span class='feature'>Иванова Анна</span>
                        </li>
                        <li>
                            <label>Дата покупки:</label>
                            <span class='feature'>01.10.2013</span>
                        </li>
                        <li>
                            <label>Баллы:</label>
                            <span class='feature'>145 из 475</span>
                        </li>
                        <li>
                            <label>Цена:</label>
                            <span class='feature'>2'000,00 руб</span>
                        </li>
                    </ul>
                </div>
                <div class='courses-status'>
                    <button class='button small secondary' disabled>Оплачено</button>
                </div>
            </div>
            <div class='courses_list-item'>
                <div class='courses-image'>
                    <img src="/images/course_preview.png" />
                </div>
                <div class='courses-features'>
                    <ul class='features_list no-bullet'>
                        <li>
                            <label>Курс:</label>
                            <span class='feature'>Straightforward</span>
                        </li>
                        <li>
                            <label>Уровень:</label>
                            <span class='feature'>Pre-intermediate</span>
                        </li>
                        <li>
                            <label>Автор:</label>
                            <span class='feature'>Иванова Анна</span>
                        </li>
                        <li>
                            <label>Дата покупки:</label>
                            <span class='feature'>01.10.2013</span>
                        </li>
                        <li>
                            <label>Баллы:</label>
                            <span class='feature'>145 из 475</span>
                        </li>
                        <li>
                            <label>Цена:</label>
                            <span class='feature'>2'000,00 руб</span>
                        </li>
                    </ul>
                </div>
                <div class='courses-status'>
                    <button class='button small secondary' disabled>Оплачено</button>
                </div>
            </div>
            <div class='courses_list-item'>
                <div class='courses-image'>
                    <img src="/images/course_preview.png" />
                </div>
                <div class='courses-features'>
                    <ul class='features_list no-bullet'>
                        <li>
                            <label>Курс:</label>
                            <span class='feature'>Straightforward</span>
                        </li>
                        <li>
                            <label>Уровень:</label>
                            <span class='feature'>Pre-intermediate</span>
                        </li>
                        <li>
                            <label>Автор:</label>
                            <span class='feature'>Иванова Анна</span>
                        </li>
                        <li>
                            <label>Дата покупки:</label>
                            <span class='feature'>01.10.2013</span>
                        </li>
                        <li>
                            <label>Баллы:</label>
                            <span class='feature'>145 из 475</span>
                        </li>
                        <li>
                            <label>Цена:</label>
                            <span class='feature'>2'000,00 руб</span>
                        </li>
                    </ul>
                </div>
                <div class='courses-status'>
                    <button class='button small secondary' disabled>Оплачено</button>
                </div>
            </div>
            <div class='courses_list-item'>
                <div class='courses-image'>
                    <img src="/images/course_preview.png" />
                </div>
                <div class='courses-features'>
                    <ul class='features_list no-bullet'>
                        <li>
                            <label>Курс:</label>
                            <span class='feature'>Straightforward</span>
                        </li>
                        <li>
                            <label>Уровень:</label>
                            <span class='feature'>Pre-intermediate</span>
                        </li>
                        <li>
                            <label>Автор:</label>
                            <span class='feature'>Иванова Анна</span>
                        </li>
                        <li>
                            <label>Дата покупки:</label>
                            <span class='feature'>01.10.2013</span>
                        </li>
                        <li>
                            <label>Баллы:</label>
                            <span class='feature'>145 из 475</span>
                        </li>
                        <li>
                            <label>Цена:</label>
                            <span class='feature'>2'000,00 руб</span>
                        </li>
                    </ul>
                </div>
                <div class='courses-status'>
                    <button class='button small secondary' disabled>Оплачено</button>
                </div>
            </div>
            <div class='courses_list-item'>
                <div class='courses-image'>
                    <img src="/images/course_preview.png" />
                </div>
                <div class='courses-features'>
                    <ul class='features_list no-bullet'>
                        <li>
                            <label>Курс:</label>
                            <span class='feature'>Straightforward</span>
                        </li>
                        <li>
                            <label>Уровень:</label>
                            <span class='feature'>Pre-intermediate</span>
                        </li>
                        <li>
                            <label>Автор:</label>
                            <span class='feature'>Иванова Анна</span>
                        </li>
                        <li>
                            <label>Дата покупки:</label>
                            <span class='feature'>01.10.2013</span>
                        </li>
                        <li>
                            <label>Баллы:</label>
                            <span class='feature'>145 из 475</span>
                        </li>
                        <li>
                            <label>Цена:</label>
                            <span class='feature'>2'000,00 руб</span>
                        </li>
                    </ul>
                </div>
                <div class='courses-status'>
                    <button class='button small secondary' disabled>Оплачено</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>
