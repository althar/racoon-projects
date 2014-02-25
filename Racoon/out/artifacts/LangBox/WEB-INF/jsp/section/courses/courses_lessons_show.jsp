<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="course" scope="session" value="${lesson.getRecord('course')}"/>
<div class="widget span6  courses-section course-lesson-edit-section" lesson-id="${lesson.getLongValue(id)}" course-id="${course.getLongValue('id')}">
    <div class="widget-header">
        <span class="title">${course.getStringValue('name')}</span>

        <div class="toolbar">
            <span class="btn" rel="tooltip" data-original-title="Back"
                  onclick="courses.showCourseLessons(${course.getLongValue('id')})">Назад</span>
        </div>
    </div>
    <div class="widget-content">
        <div class="user-box">
            <div class="thumbnail">
                <img alt="" src="/img/course_preview.png">
            </div>
            <div class="info">
                <span class="name">${course.getStringValue('name')}
                    <small>${course.getStringValue('level')}</small></span>
                <ul class="attributes">
                    <li>${course.getStringValue('target')}, ${course.getStringValue('type')}</li>
                    <li>${course.getRecords('lessons').size()} Уроков</li>
                    <li><span class="muted">Дата создания: <fmt:formatDate pattern="dd:MM:yyyy"
                                                                           value="${course.getDateValue('created')}"/></span>
                    </li>
                </ul>
            </div>
        </div>
        <hr/>

        <form class="form-vertical">
            <div class="control-group">
                <label class="control-label" for="lesson-name">Название</label>

                <div class="controls">
                    <input type="text" id="lesson-name" name="name" class="span12" value="${lesson.getStringValue('name')}"
                           style="margin-bottom: 10px">
                    <label class="checkbox" for="trial-access">
                        <input type="checkbox" id="trial-access"
                               <c:if test="${lesson.getBooleanValue('trial')}">checked</c:if> name="access"
                               class="uniform" style="opacity: 1;">
                        Доступен в неоплаченном курсе (демо урок)
                    </label>
                </div>
            </div>
        </form>
        <hr/>

        <div class="tabbable analytics-tab">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#" data-target="#materials" data-toggle="tab"><i
                        class="icon-attachment"></i> Содержание урока</a></li>
                <li><a href="#" data-target="#dictionary" data-toggle="tab"><i class="icon-book"></i> Словарь</a></li>
            </ul>
            <div class="tab-content">
                <div id="materials" class="tab-pane active verticalist">
                    <div class="task-block">
                        <form class="form-vertical">
                            <div class="control-group">
                                <label class="control-label">Задание</label>

                                <div class="controls">
                                    <textarea id="cleditor">${lesson.getStringValue('task')}</textarea>
                                </div>
                            </div>
                        </form>
                    </div>


                    <ul class="thumbnails lesson-material-list">
                        <li class="lesson-material hidden" id="lesson-material-template">
                              <span class="info">
                                <span class="head">
                                  <h2><span class="icol-doc-pdf"></span><span class="lesson-item-name"></span></h2>
                                  <span class="order"></span>
                                </span>
                              </span>

                            <div class="actions">
                                <a onclick="$(this).parents('li').remove()"><i
                                        class="icon-remove remove-lesson-material-link"></i></a>
                            </div>
                        </li>

                        <c:forEach items="${lesson.getRecords('materials')}" var="material">
                            <li class="lesson-material lesson-material-item" entity-id="${material.getLongValue('id')}">
                              <span class="info">
                                <span class="head">
                                  <h2><span
                                          class="<c:if test="${material.getStringValue('type')=='CUSTOM_FILE'}">icol-box</c:if><c:if test="${material.getStringValue('type')=='DOCUMENT_FILE'}">icol-bookmark-document</c:if><c:if test="${material.getStringValue('type')=='AUDIO_FILE'}">icol-music-beam</c:if><c:if test="${material.getStringValue('type')=='VIDEO_FILE'}">icol-films</c:if><c:if test="${material.getStringValue('type')=='IMAGE_FILE'}">icol-image-1</c:if>"></span> ${material.getStringValue('name')}
                                  </h2>
                                  <span class="order"> -- </span>
                                </span>
                              </span>

                                <div class="actions">
                                    <a onclick="$(this).parents('li').remove()"><i
                                            class="icon-remove remove-lesson-material-link"></i></a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                </div>

                <div id="dictionary" class="tab-pane">

                    <%@include file="/WEB-INF/jsp/section/courses/courses_lessons_dictionary.jsp" %>
                    <%-- <%@include file="/WEB-INF/jsp/section/courses/courses_lessons_dictionary_edit.jsp" %>--%>

                </div>

            </div>

        </div>

        <a class="btn btn-primary save-lesson-butt" lesson-id="${lesson.getLongValue(id)}" course-id="${course.getLongValue('id')}">Сохранить</a>

    </div>
</div>