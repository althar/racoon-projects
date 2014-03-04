<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/css/lex.css" media="screen">



<div class="span4 widget">
    <div class="widget-header">
        <span class="title">Для учеников</span>
    </div>
    <div class="widget-content">
        <div class="control-group">
            <label class="control-label">Выполнение домашних заданий</label>
        </div>
        <div class="control-group">
            <label class="control-label">Доступ к курсам через интернет</label>
        </div>
        <div class="control-group">
            <label class="control-label">Возможность заниматься: на работе, в дороге, дома.</label>
        </div>
        <div class="control-group">
            <label class="control-label">Приобретение курсов</label>
        </div>
        <div class="control-group">
            <label class="control-label">Возможность самостоятельного обучения</label>
        </div>
        <div class="control-group">
            <label class="control-label">Измерение роста знаний</label>
        </div>
        <div class="control-group">
            <label class="control-label">Геймификация процесса обучения</label>
        </div>
        <div class="control-group">
            <label class="control-label">Удобное взаимодействие с репетитором</label>
        </div>
        <div class="form-actions">
            <a class='button' href='/registration?role=STUDENT&do_registration=false'>
                <button class="btn btn-danger btn-block btn-large">Регистрация</button>
            </a>

        </div>
    </div>
</div>
<div class="span4 widget">
    <div class="widget-header">
        <span class="title">Для репетиторов</span>
    </div>
    <div class="widget-content">
        <div class="control-group">
            <label class="control-label">Создание курсов</label>
        </div>
        <div class="control-group">
            <label class="control-label">Продажа курсов</label>
        </div>
        <div class="control-group">
            <label class="control-label">Приобретение готовых методик</label>
        </div>
        <div class="control-group">
            <label class="control-label">Вся библиотека в одном месте</label>
        </div>
        <div class="control-group">
            <label class="control-label">Удобное взаимодействие с учеником</label>
        </div>
        <div class="form-actions">
            <a class='button' href='/registration?role=TUTOR&do_registration=false'>
                <button class="btn btn-danger btn-block btn-large">Регистрация</button>
            </a>

        </div>
    </div>
</div>
<div class="span4 widget">
    <div class="widget-header">
        <span class="title">Для языковых школ</span>
    </div>
    <div class="widget-content">
        <div class="control-group">
            <label class="control-label">Создание курсов</label>
        </div>
        <div class="control-group">
            <label class="control-label">Продажа курсов</label>
        </div>
        <div class="control-group">
            <label class="control-label">Приобретение готовых методик</label>
        </div>
        <div class="control-group">
            <label class="control-label">Геймификация процесса обучения</label>
        </div>
        <div class="control-group">
            <label class="control-label">Маркетинговый бонус для  ученика</label>
        </div>
        <div class="control-group">
            <label class="control-label">Удобное взаимодействие с учеником</label>
        </div>
        <div class="form-actions">
            <a class='button' href='/registration?role=SCHOOL&do_registration=false'>
                <button class="btn btn-danger btn-block btn-large">Регистрация</button>
            </a>

        </div>
    </div>
</div>
<div class="span12 widget">
    <div class="widget-header">
        <span class="title">Как работает сервис</span>
    </div>
    <div class="widget-content">
        <div>
            Репетитор или методолог школы английского языка загруждает на сервис контент и создает из него собственную библиотеку.
            Далее из библиотеки создается курс. Созданный курс ученики приобретают напрямую на сайте.
            Далее ученики работают с курсом сомостоятельно или в созданной группе.
        </div>
        <div>
            <img src="/img/main/test.jpg" style="width: 100%;">
        </div>
    </div>
</div>
<div class="span12 widget">
    <div class="widget-header">
        <span class="title">Кто уже использует сервис</span>
    </div>
    <div class="widget-content">
        <div>
        <div style="display: inline-block; width: 200px; vertical-align: top;">
            <img src="/img/main/comp1.png" width="200px; vertical-align: top;">
        </div>
        <div style="display: inline-block; width: 600px;">
            <h2>American British Center</h2>
            <p>American British Center – это международная команда,
                состоящая из дипломированных преподавателей носителей языка
                (native-speaker) и преподавателей bilingual (двуязычные),
                а также внимательного Американо-Британского
            </p>
        </div>
    </div>

        <div>
            <div style="display: inline-block; width: 200px; vertical-align: top;">
                <img src="/img/main/comp2.png" width="200px; vertical-align: top;">
            </div>
            <div style="display: inline-block; width: 600px;">
                <h2>American British Center</h2>
                <p>American British Center – это международная команда,
                    состоящая из дипломированных преподавателей носителей языка
                    (native-speaker) и преподавателей bilingual (двуязычные),
                    а также внимательного Американо-Британского
                </p>
            </div>
        </div>

    </div>
</div>
<div class="span12 widget">
    <div class="widget-header">
        <span class="title">Часто задаваемые вопросы</span>
        <div class="toolbar">
            <div class="btn-group">
                <span class="btn">Для умеников</span>
                <span class="btn">Для репититоров</span>
                <span class="btn">Для языковых школ</span>
            </div>
        </div>
    </div>
    <div class="widget-content">
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>        <div style="width: 200px; margin: 20px; display: inline-block;">
        <h4>На компьютер нужно устанавливать программу</h4>
        Нет не нужно достаточно просто браузера
    </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>
        <div style="width: 200px; margin: 20px; display: inline-block;">
            <h4>На компьютер нужно устанавливать программу</h4>
            Нет не нужно достаточно просто браузера
        </div>

    </div>
</div>
<script>
    function showform(){
        $('.summon-review-form').hide();
        $('.review-form').show();
    }
</script>