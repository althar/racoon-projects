<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link href="/css/devise.css" media="screen" rel="stylesheet" type="text/css" />

    <script src="/js/jq/jquery-2.0.3.js" type="text/javascript"></script>
    <script src="/js/common/all.js" type="text/javascript"></script>
</head>
<body class='devise devise_registration'>
<%@ include file="/WEB-INF/jsp/section/header.jsp" %>
<div class='main_content'>
    <div class='main_content-inner row'>
        <div class='page_body'>
            <div class='page_body-inner'>
                <h1 class='text-center'>Выберите в качесте кого вы будете регистрироваться</h1>
                <div class='small-4 column'>
                    <ul class='pricing-table'>
                        <li class='title'>Ученик</li>
                        <li class='bullet-item'>Выполнение домашних заданий</li>
                        <li class='bullet-item'>Доступ к курсам через интернет</li>
                        <li class='bullet-item'>Возможность заниматься: на работе, в дороге, дома.</li>
                        <li class='bullet-item'>Приобретение курсов</li>
                        <li class='bullet-item'>Возможность самостоятельного обучения</li>
                        <li class='bullet-item'>Измерение роста знаний</li>
                        <li class='bullet-item'>Геймификация процесса обучения</li>
                        <li class='bullet-item'>Удобное взаимодействие с репетитором</li>
                        <li class='cta-button'>
                            <a class='button' href='../students/index.html'>Регистрация</a>
                        </li>
                    </ul>
                </div>
                <div class='small-4 column'>
                    <ul class='pricing-table'>
                        <li class='title'>Репетитор</li>
                        <li class='bullet-item'>Создание курсов</li>
                        <li class='bullet-item'>Продажа курсов</li>
                        <li class='bullet-item'>Приобретение готовых методик</li>
                        <li class='bullet-item'>Вся библиотека в одном месте</li>
                        <li class='bullet-item'>Удобное взаимодействие с учеником</li>
                        <li class='cta-button'>
                            <a class='button' href='../teacher/index.html'>Регистрация</a>
                        </li>
                    </ul>
                </div>
                <div class='small-4 column'>
                    <ul class='pricing-table'>
                        <li class='title'>Школа иностранных языков</li>
                        <li class='bullet-item'>Создание курсов</li>
                        <li class='bullet-item'>Продажа курсов</li>
                        <li class='bullet-item'>Приобретение готовых методик</li>
                        <li class='bullet-item'>Геймификация процесса обучения</li>
                        <li class='bullet-item'>Маркетинговый бонус для  ученика</li>
                        <li class='bullet-item'>Удобное взаимодействие с учеником</li>
                        <li class='cta-button'>
                            <a class='button' href='../school/index.html'>Регистрация</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/section/footer.jsp" %>
</body>
</html>