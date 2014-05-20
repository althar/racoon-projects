<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
    span.form-error.help-block {
        display: block;
        color: red;
        margin-top: 6px;
        padding-left: 0;
</style>
<script src="/js/logic/registration.js"></script>
<script src="js/mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="js/mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<script src="js/mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="js/mask/jquery.inputmask.numeric.extensions.js" type="text/javascript"></script>
<script src="js/mask/jquery.inputmask.custom.extensions.js" type="text/javascript"></script>
<div class="content layout">

    <ul class="breadcrumbs"><li class="item"><a href="/" class="link">Главная</a><span class="arrow"></span></li><li class="item"><a href="/my/" class="link">Личный кабинет</a><span class="arrow"></span></li><li class="item">Регистрация</li></ul>
    <div class="container section dark-title">


        <div class="boxed-group">
            <h2>Регистрация в ТеплоКлубе</h2>
            <div class="boxed-group-inner">
                <form id="registration_form" novalidate="" action="/auth/registration" class="form-horizontal" method="post">
                    <div class="alert">Все поля обязательны для заполнения.</div>
                    <div class="step-no-1">
                        <legend>Шаг 1 <c:if test="${not empty registration_error}"><span style="color: red;"> - ${registration_error}</span></c:if></legend>
                        <div class="control-group">
                            <label class="control-label" for="first_name_field">Имя</label>
                            <div class="controls">
                                <input required="" name="first_name" id="first_name_field" type="text" value="${first_name}" data-validation="length alphanumeric" data-validation-length="3-12" data-validation-error-msg="Имя должно составлять 3-12 символов">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="last_name_field">Фамилия</label>
                            <div class="controls">
                                <input required="" name="last_name" id="last_name_field" type="text" value="${last_name}" data-validation="length alphanumeric" data-validation-length="3-12" data-validation-error-msg="Фамилия должна составлять 3-12 символов">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Пол</label>
                            <div class="controls">
                                <label class="radio inline"><input required="" type="radio" name="gender" checked<c:if test="${gender=='М'}">checked</c:if> value="М"> мужской</label>
                                <label class="radio inline"><input required="" type="radio" name="gender" <c:if test="${gender=='Ж'}">checked</c:if> value="Ж"> женский</label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="birthday">Дата рождения</label>
                            <div class="controls">
                                <input required="" name="birthday" id="birthday" type="text" value="" data-validation="date" data-validation-format="dd.mm.yyyy" data-validation-error-msg="Дата была введена неверно! Пример даты: 01.01.2000">
                                <span class="help-inline">ДД.ММ.ГГГГ</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="email">E-mail</label>
                            <div class="controls">
                                <input required="" name="login" id="email" type="email" value="${login}" data-validation="email" data-validation-error-msg="E-mail был ввыден не верно!">
                                <span class="help-inline">для рассылки сертификатов</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="phone_field">Контактный телефон</label>
                            <div class="controls">
                                <input required="" name="phone" id="phone_field" type="text" value="${phone}" data-validation="number" data-validation-length="1-100" data-validation-error-msg="Номер был ввыден не верно!" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="employer_field">Название компании работодателя</label>
                            <div class="controls">
                                <input required="" name="company" id="employer_field" type="text" value="${company}" data-validation="length alphanumeric" data-validation-length="3-12" data-validation-error-msg="Название компании должно составлять 3-12 символов">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="employer_address_field">Адрес компании работодателя</label>
                            <div class="controls">
                                <input required="" name="address" id="employer_address_field" type="text" value="${address}" data-validation="length alphanumeric" data-validation-length="5-30" data-validation-error-msg="Адрес компании должн составлять 5-30 символов" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="employer_phone_field">Телефон работодателя</label>
                            <div class="controls">
                                <input required="" name="phone_work" id="employer_phone_field" type="text" value="${phone}" data-validation="number" data-validation-length="1-100" data-validation-error-msg="Номер был ввыден не верно!">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="position_field">Ваша должность</label>
                            <div class="controls">
                                <input required="" name="post" id="position_field" type="text" value="${post}" data-validation="length alphanumeric" data-validation-length="3-12" data-validation-error-msg="Должность должна составлять 3-12 символов">
                            </div>
                        </div>
                    </div>
                    <div class="step-no-2">
                        <legend>Шаг 2</legend>
                        <div class="control-group">
                            <label class="control-label">Как давно Вы занимаетесь продажей теплоизоляционных материалов</label>
                            <div class="controls">
                                <label class="radio"><input required="" type="radio" name="sale_how_long" value="меньше года"> меньше года</label>
                                <label class="radio"><input required="" type="radio" name="sale_how_long" value="1-3 года"> 1-3 года</label>
                                <label class="radio"><input required="" type="radio" name="sale_how_long" value="3-5 лет"> 3-5 лет</label>
                                <label class="radio"><input required="" type="radio" name="sale_how_long" value="более 5 лет"> более 5 лет</label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Как давно Вы работаете в указанной компании</label>
                            <div class="controls">
                                <label class="radio"><input required="" type="radio" name="work_how_long" value="меньше года"> меньше года</label>
                                <label class="radio"><input required="" type="radio" name="work_how_long" value="1-3 года"> 1-3 года</label>
                                <label class="radio"><input required="" type="radio" name="work_how_long" value="более 3 лет"> более 3 лет</label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Продажами каких материалов Вы занимаетесь</label>
                            <div class="controls">
                                <label class="checkbox"><input type="checkbox" name="sale_what[]" value="теплоизоляция"> теплоизоляция</label>
                                <label class="checkbox"><input type="checkbox" name="sale_what[]" value="строительные смеси"> строительные смеси</label>
                                <label class="checkbox"><input type="checkbox" name="sale_what[]" value="кровельные покрытия"> кровельные покрытия</label>
                                <label class="checkbox"><input type="checkbox" name="sale_what[]" value="сайдинг"> сайдинг</label>
                                <label class="checkbox">другое <input type="text" name="sale_what_other" value=""></label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Каким клиентам продает теплоизоляцию отдел, в котором Вы работаете</label>
                            <div class="controls">
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="оптовым компаниям"> оптовым компаниям</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="строительным компаниям"> строительным компаниям</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="розничным магазинам"> розничным магазинам</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="сетевым строительным гипермаркетам"> сетевым строительным гипермаркетам</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="конечным потребителям"> конечным потребителям</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="мелким строительным бригадам"> мелким строительным бригадам</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="производителям домов"> производителям домов</label>
                                <label class="checkbox"><input type="checkbox" name="sale_whom[]" value="кровельным компаниям"> кровельным компаниям</label>
                                <label class="checkbox">другое <input type="text" name="sale_whom_other" value=""></label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Теплоизоляцию каких производителей Вы продаете</label>
                            <div class="controls">
                                <label class="checkbox"><input type="checkbox" name="sale_brand[]" value="Knauf Insulation"> Knauf Insulation</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand[]" value="Isover"> Isover</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand[]" value="Ursa"> Ursa</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand[]" value="Технониколь"> Технониколь</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand[]" value="Rockwool"> Rockwool</label>
                                <label class="checkbox">другие <input type="text" name="sale_brand_other" value=""></label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Теплоизоляцию какого производителя Вы продаете больше остальных</label>
                            <div class="controls">
                                <label class="checkbox"><input type="checkbox" name="sale_brand_most[]" value="Knauf Insulation"> Knauf Insulation</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand_most[]" value="Isover"> Isover</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand_most[]" value="Ursa"> Ursa</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand_most[]" value="Технониколь"> Технониколь</label>
                                <label class="checkbox"><input type="checkbox" name="sale_brand_most[]" value="Rockwool"> Rockwool</label>
                                <label class="checkbox">другие <input type="text" name="sale_brand_most_other" value=""></label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Почему Вы продаете данную теплоизоляцию больше, чем продукцию других производителей</label>
                            <div class="controls">
                                <label class="checkbox"><input type="checkbox" name="why_sale_brand_most[]" value="более известная марка"> более известная марка</label>
                                <label class="checkbox"><input type="checkbox" name="why_sale_brand_most[]" value="лучшее качество"> лучшее качество</label>
                                <label class="checkbox"><input type="checkbox" name="why_sale_brand_most[]" value="лучшая цена"> лучшая цена</label>
                                <label class="checkbox"><input type="checkbox" name="why_sale_brand_most[]" value="выше плотность"> выше плотность</label>
                                <label class="checkbox"><input type="checkbox" name="why_sale_brand_most[]" value="указания руководства"> указания руководства</label>
                                <label class="checkbox">другое <input type="text" name="why_sale_brand_most_other" value=""></label>
                            </div>
                        </div>
                    </div>
                    <div class="step-no-3">
                        <legend>Шаг 3</legend>
                        <div class="control-group">
                            <label class="control-label" for="pass_field">Укажите свой пароль</label>
                            <div class="controls">
                                <%--<input required="" autocomplete="off" name="password" id="password_field" type="password" data-validation="strength" data-validation-strength="2">--%>
                                <input type="password" id="pass_field" name="password_confirmation" data-validation="length" value="" data-validation-length="min4" data-validation-error-msg="Минимум 4 символа.">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="password_confirmation_field">Повторите пароль</label>
                            <div class="controls">
                                <%--<input required="" name="password_confirmation" id="password_confirmation_field" type="password" value="" data-validation="confirmation">--%>
                                    <input type="password" id="password_confirmation_field" name="password" value="" data-validation="confirmation" data-validation-error-msg="Пароли не совпадают.">
                            </div>
                        </div>
                        <div class="control-group">
                            <p class="disclaimer">Любая информация об условиях участия в программе «Теплоклуб» не подлежит разглашению третьим лицам. Ответственность за последствия передачи информации третьим лицам принимаю на себя.</p>
                            <div class="controls">
                                <button class="btn btn-blue" type="submit" name="submit">Я согласен</button>
                                &nbsp; &nbsp;
                                <a class="btn btn-blue" href="/">Я не согласен</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>


<script src="/js/validator.js"></script>
<script src="/js/logic/registration_validator.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/security.js"></script>
