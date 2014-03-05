<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="widget span6 courses-section courses-list-section">
    <input type="hidden" id="current-course-id">
    <input type="hidden" id="current-lesson-id">

    <div class="widget-header">
        <span class="title">Список всех курсов</span>

        <div class="toolbar">
            <div class="btn-group">
                <span class="btn add-course-button" rel="tooltip" data-original-title="Add Item"><i class="icon-plus"></i></span>
        <span class="btn" data-toggle="collapse" data-target="#toolbar-ex">
          <i class="icon-search"></i>
        </span>
            </div>
        </div>
    </div>
    <div id="toolbar-ex" class="toolbar form-toolbar collapse">
        <form class="search-form">
            <input type="text" class="span7 search-query" placeholder="Search...">

            <div class="pull-right">
                <select class='span2'>
                    <option selected='selected' value='Все уровни'>Все уровни</option>
                    <option value='BEGINNER'>Beginner</option>
                    <option value='ELEMENTARY'>Elementary</option>
                    <option value='PRE-INTERMEDIATE'>Pre-intermediate</option>
                    <option value='INTERMEDIATE'>Intermediate</option>
                    <option value='UPPER-INTERMEDIATE'>Upper-Intermediate</option>
                    <option value='ADVANCED'>Advance</option>
                    <option value='PROFICIENT'>Proficient</option>
                </select>
                <select class='span2'>
                    <option selected='selected' value='New English File '>New English
                        File
                    </option>
                    <option value='New Headway'>New Headway</option>
                    <option value='In company'>In company</option>
                    <option value='Straightforward'>Straightforward</option>
                    <option value='Face2face'>Face2face</option>
                    <option value='Language Leader'>Language Leader</option>
                </select>
            </div>
        </form>
    </div>
    <div class="widget-content courseslist">
        <ul class="thumbnails">
            <c:forEach items="${courses}" var="course">
            <li class="course-item" id="${course.getLongValue('id')}">
                <div class="head">
                    <h2 class="title"><a onclick="courses.showCourseLessons(${course.getLongValue('id')})">${course.getStringValue('name')}</a></h2>
                    <span class="note">${course.getStringValue('level')}</span>
                </div>
                <div class="thumbnail" onclick="courses.showCourseLessons(${course.getLongValue('id')})">
                    <img alt="" src="/course_image?path=${course.getStringValue('image_url')}">
                </div>
                <div class="info">
                    <p>${course.getStringValue('description')}</p>
                </div>
                <div class="actions">
                    <ul>
                        <li><a title="Edit" rel="tooltip"><i class="icon-pencil edit-course-button" course-id="${course.getLongValue('id')}"></i></a></li>
                        <li><a title="Delete" rel="tooltip"><i class="icon-remove  delete-course-button"  course-id="${course.getLongValue('id')}"></i></a></li>
                    </ul>
                </div>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>