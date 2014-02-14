<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="widget span6  courses-section course-lessons-section">
  <div class="widget-header">
    <span class="title">${course.getStringValue('name')}</span>
    <div class="toolbar">
      <span class="btn-group">
        <span class="btn" rel="tooltip" onclick="courses.showEditLessons(${course.getLongValue('id')})" data-original-title="Add lesson">Добавить урок</span>
        <span class="btn" rel="tooltip" onclick="courses.showCourses()" data-original-title="Back">Назад</span>
      </span>
    </div>
  </div>
  <div class="widget-content">
    <div class="user-box">
      <div class="thumbnail">
        <img alt="" src="/img/course_preview.png">
      </div>
      <div class="info">
        <span class="name">${course.getStringValue('name')} <small>${course.getStringValue('level')}</small></span>
        <ul class="attributes">
          <li>${course.getStringValue('target')}, ${course.getStringValue('type')}</li>
          <li><strong>${course.getDoubleValue('price')} руб.</strong></li>
        </ul>
        <div class="btn-group">
          <a onclick="courses.showEditCourse(${course.getLongValue('id')})" class="btn"><i class="icon-pencil"></i></a>
        </div>
      </div>
    </div>
    <hr/>
    <h5 class="materials">
      <a href="#"><i class="icon-pencil"></i> Основные материалы (${course.getRecord('main_material').getLongValue('material_count')} файла)</a>
    </h5>
    <hr/>

    <h4>Уроки курса</h4>
    <table class="table table-striped course-lessons">
      <colgroup>
        <col span="2">
      </colgroup>
      <tbody>
      <c:forEach items="${course.getRecords('lessons')}" var="lesson">
          <tr>
              <td>
                  <a onclick="courses.showEditLessons(${course.getLongValue('id')}, ${lesson.getLongValue('id')})">
                      <span class="icol-page"></span> ${lesson.getStringValue('name')}
                  </a>
              </td>
              <td>
                  ${lesson.getLongValue('material_count')} файлов
              </td>
          </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>