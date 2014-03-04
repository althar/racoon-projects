<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="widget span6  courses-section course-edit-section">
<c:if test="${course==null}">
    <div class="widget-header">
        <span class="title">Новый курс</span>

        <div class="toolbar">
            <span class="btn" rel="tooltip" data-original-title="Back" onclick="courses.showCourses()">Назад</span>
        </div>
    </div>
    <div class="widget-content form-container">

        <form  class="form-horizontal course-edit-form" method="post" enctype="multipart/form-data" action="/service/upload_image" accept-charset="UTF-8">

            <div class="control-group">
                <label class="control-label" for="name">Название курса</label>

                <div class="controls">
                    <input type="text" id="name" name="name" class="span12">

                    <p class="help-block"></p>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lvl">Уровень</label>

                <div class="controls">
                    <select id="lvl" name="lvl" class="span12">
                        <option selected value="Все уровни">Все уровни</option>
                        <option value="BEGINNER">Beginner</option>
                        <option value="ELEMENTARY">Elementary</option>
                        <option value="PRE-INTERMEDIATE">Pre-intermediate</option>
                        <option value="INTERMEDIATE">Intermediate</option>
                        <option value="UPPER-INTERMEDIATE">Upper-Intermediate</option>
                        <option value="ADVANCED">Advance</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="sale">Язык</label>

                <div class="controls">
                    <select id="language" name="language" class="span12">
                        <option selected="selected" value="Английский">Английский</option>
                        <option value="Немецкий">Немецкий</option>
                        <option value="Французский">Французский</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="target">Для кого</label>

                <div class="controls">
                    <select id="target" name="target" class="span12">
                        <%--<option selected value="Любой">Любой</option>--%>
                        <option selected value="Для взрослых">Для взрослых</option>
                        <option value="Для детей">Для детей</option>
                        <option value="Для подростков">Для подростков</option>
                        <option value="Бизнес">Бизнес</option>
                        <option value="Профессиональный">Профессиональный</option>
                        <option value="Для всех">Для всех</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="type">Вид курса</label>

                <div class="controls">
                    <select id="type" name="type" class="span12">
                        <%--<option value="Любой">Не выбрано</option>--%>
                        <option value="Аудио курс">Аудио курс</option>
                        <option value="Видео курс">Видео курс</option>
                        <option value="Лингафонный курс">Лингафонный курс</option>
                        <option selected value="Стандартный курс">Стандартный курс</option>
                        <option value="Аудио книга">Аудио книга</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="price">Цена</label>

                <div class="controls">
                    <input type="text" id="price" name="price" class="span12 numeric" data-accept="numbers" value="0.00">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="descr">Описание</label>

                <div class="controls">
                    <textarea id="descr" name="description" class="span12 autosize">Описание курса</textarea>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="pic">Обложка</label>

                <div class="controls">
                    <input type="file" id="pic" name="files" accept="image/*">
                    <input type="hidden" id="course_preview_id" name="course_preview_id">
                    <br>
                    <img id="course_preview_pic">
                    <br>
                    <button type="button" class="btn btn-success add-course-preview-butt">Загрузить</button>
                    <button type="button" class="btn btn-danger remove-course-preview-butt">Удалить</button>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="link">Внешняя ссылка</label>

                <div class="controls">
                    <input type="text" id="link" name="link" class="span12">
                </div>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-primary save-course-butt">Сохранить</button>
                <button type="button"  onclick="courses.showCourses()" class="btn cancel-course-butt">Отменить</button>
            </div>
        </form>


    </div>
</c:if>
<c:if test="${course != null}">
    <div class="widget-header">
        <span class="title">Редактирование курса</span>

        <div class="toolbar">
            <span class="btn" rel="tooltip" data-original-title="Back" onclick="courses.showCourses()">Назад</span>
        </div>
    </div>
    <div class="widget-content form-container">
        <%--<form method="post" id="add-image-form" enctype="multipart/form-data" action="/service/upload_image" accept-charset="UTF-8">--%>
            <%--<input type="file" id="pic" name="files">--%>
        <%--</form>--%>
        <form class="form-horizontal course-edit-form" method="post" enctype="multipart/form-data" action="/service/upload_image" accept-charset="UTF-8">
            <input type="hidden" name="course_id" value="${course.getLongValue('id')}">
            <div class="control-group">
                <label class="control-label" for="name">Название курса</label>

                <div class="controls">
                    <input type="text" id="name" name="name" class="span12" value="${course.getStringValue('name')}">
                    <p class="help-block"></p>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lvl">Уровень</label>

                <div class="controls">
                    <select id="lvl" name="lvl" class="span12">
                        <option
                                <c:if test="${course.getStringValue('level')=='ANY'}">selected="selected"</c:if>
                                value="Все уровни">Все уровни
                        </option>
                        <option
                                <c:if test="${course.getStringValue('level')=='BEGINNER'}">selected="selected"</c:if>
                                value="BEGINNER">Beginner
                        </option>
                        <option
                                <c:if test="${course.getStringValue('level')=='ELEMENTARY'}">selected="selected"</c:if>
                                value="ELEMENTARY">Elementary
                        </option>
                        <option
                                <c:if test="${course.getStringValue('level')=='PRE-INTERMEDIATE'}">selected="selected"</c:if>
                                value="PRE-INTERMEDIATE">Pre-intermediate
                        </option>
                        <option
                                <c:if test="${course.getStringValue('level')=='INTERMEDIATE'}">selected="selected"</c:if>
                                value="INTERMEDIATE">Intermediate
                        </option>
                        <option
                                <c:if test="${course.getStringValue('level')=='UPPER-INTERMEDIATE'}">selected="selected"</c:if>
                                value="UPPER-INTERMEDIATE">Upper-Intermediate
                        </option>
                        <option
                                <c:if test="${course.getStringValue('level')=='ADVANCED'}">selected="selected"</c:if>
                                value="ADVANCED">Advance
                        </option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="language">Язык</label>

                <div class="controls">
                    <select id="language" name="language" class="span12">
                        <option value="Английский" <c:if test="${course.getStringValue('language')=='Английский'}">selected="selected"</c:if>>Английский</option>
                        <option value="Немецкий"<c:if test="${course.getStringValue('language')=='Немецкий'}">selected="selected"</c:if>>Немецкий</option>
                        <option value="Французский"<c:if test="${course.getStringValue('language')=='Французский'}">selected="selected"</c:if>>Французский</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="target">Для кого</label>

                <div class="controls">
                    <select id="target" name="target" class="span12">
                        <%--<option--%>
                                <%--<c:if test="${course.getStringValue('target')=='Любой'}">selected="selected"</c:if>--%>
                                <%--value="Любой">Любой--%>
                        <%--</option>--%>
                        <option
                                <c:if test="${course.getStringValue('target')=='Для взрослых'}">selected="selected"</c:if>
                                value="Не выбрано">Для взрослых
                        </option>
                        <option
                                <c:if test="${course.getStringValue('target')=='Для детей'}">selected="selected"</c:if>
                                value="Не выбрано">Для детей
                        </option>
                        <option
                                <c:if test="${course.getStringValue('target')=='Для подростков'}">selected="selected"</c:if>
                                value="Не выбрано">Для подростков
                        </option>
                        <option
                                <c:if test="${course.getStringValue('target')=='Бизнес'}">selected="selected"</c:if>
                                value="Не выбрано">Бизнес
                        </option>
                        <option
                                <c:if test="${course.getStringValue('target')=='Профессиональный'}">selected="selected"</c:if>
                                value="Не выбрано">Профессиональный
                        </option>
                        <option
                                <c:if test="${course.getStringValue('target')=='Для всех'}">selected="selected"</c:if>
                                value="Не выбрано">Для всех
                        </option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="type">Вид курса</label>

                <div class="controls">
                    <select id="type" name="type" class="span12">
                        <%--<option--%>
                                <%--<c:if test="${course.getStringValue('type')=='Любой'}">selected="selected"</c:if>--%>
                                <%--value="Любой">Любой--%>
                        <%--</option>--%>
                        <option
                                <c:if test="${course.getStringValue('type')=='Аудио курс'}">selected="selected"</c:if>
                                value="Аудио курс">Аудио курс
                        </option>
                        <option
                                <c:if test="${course.getStringValue('type')=='Видео курс'}">selected="selected"</c:if>
                                value="Видео курс">Видео курс
                        </option>
                        <option
                                <c:if test="${course.getStringValue('type')=='Лингафонный курс'}">selected="selected"</c:if>
                                value="Лингафонный курс">Лингафонный курс
                        </option>
                        <option
                                <c:if test="${course.getStringValue('type')=='Стандартный курс'}">selected="selected"</c:if>
                                value="Стандартный курс">Стандартный курс
                        </option>
                        <option
                                <c:if test="${course.getStringValue('type')=='Аудио книга'}">selected="selected"</c:if>
                                value="Аудио книга">Аудио книга
                        </option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="price">Цена</label>

                <div class="controls">
                    <input type="text" id="price" name="price" class="span12 numeric" data-accept="numbers"
                           value="<fmt:formatNumber type="number" pattern="##.00" value="${course.getDoubleValue('price')}" />">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="descr">Описание</label>

                <div class="controls">
                    <textarea id="descr" name="description"
                              class="span12 autosize">${course.getStringValue('description')}</textarea>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="pic">Обложка</label>

                <div class="controls">
                    <input type="file" id="pic" name="files" accept="image/*">
                    <input type="hidden" id="course_preview_id" name="course_preview_id" value="${course.getLongValue('preview_image_id')}">
                    <br>
                    <img id="course_preview_pic" src="/get_image/${course.getLongValue('preview_image_id')}">
                    <br>
                    <button type="button" class="btn btn-success add-course-preview-butt">Загрузить</button>
                    <button type="button" class="btn btn-danger remove-course-preview-butt">Удалить</button>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="link">Внешняя ссылка</label>

                <div class="controls">
                    <input type="text" id="link" name="link" class="span12">
                </div>
            </div>
            <div class="form-actions">
                <button type="button" class="btn btn-primary save-course-butt">Сохранить</button>
                <button type="button"  onclick="courses.showCourses()" class="btn cancel-course-butt">Отменить</button>
            </div>
        </form>
    </div>
</c:if>
</div>
