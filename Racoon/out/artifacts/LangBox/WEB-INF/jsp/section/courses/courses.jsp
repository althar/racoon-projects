<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="widget span6">
  <input type="hidden" id="current-course-id">
  <input type="hidden" id="current-lesson-id">
  <div class="widget-header">
    <span class="title">Список всех курсов</span>
    <div class="toolbar">
      <div class="btn-group">
        <span class="btn" rel="tooltip" data-original-title="Add Item"><i class="icon-plus"></i></span>
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
          <option value='Beginner'>Beginner</option>
          <option value='Elementary'>Elementary</option>
          <option value='Pre-intermediate'>Pre-intermediate</option>
          <option value='Intermediate'>Intermediate</option>
          <option value='Upper-Intermediate'>Upper-Intermediate</option>
          <option value='Advance'>Advance</option>
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
      <li>
        <div class="head">
          <h2 class="title"><a href="#">New Headway</a></h2>
          <span class="note">Pre-intermediate</span>
        </div>
        <div class="thumbnail">
          <img alt="" src="/img/course_preview.png">
        </div>
        <div class="info">
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
        <div class="actions">
          <ul>
            <li><a href="#" title="Edit" rel="tooltip"><i class="icon-pencil"></i></a></li>
            <li><a href="#" title="Delete" rel="tooltip"><i class="icon-remove"></i></a></li>
          </ul>
        </div>
      </li>
      <li>
        <div class="head">
          <h2 class="title"><a href="#">New Headway</a></h2>
          <span class="note">Pre-intermediate</span>
        </div>
        <div class="thumbnail">
          <img alt="" src="/img/course_preview.png">
        </div>
        <div class="info">
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
        <div class="actions">
          <ul>
            <li><a href="#" title="Edit" rel="tooltip"><i class="icon-pencil"></i></a></li>
            <li><a href="#" title="Delete" rel="tooltip"><i class="icon-remove"></i></a></li>
          </ul>
        </div>
      </li>
      <li>
        <div class="head">
          <h2 class="title"><a href="#">New Headway</a></h2>
          <span class="note">Pre-intermediate</span>
        </div>
        <div class="thumbnail">
          <img alt="" src="/img/course_preview.png">
        </div>
        <div class="info">
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
        <div class="actions">
          <ul>
            <li><a href="#" title="Edit" rel="tooltip"><i class="icon-pencil"></i></a></li>
            <li><a href="#" title="Delete" rel="tooltip"><i class="icon-remove"></i></a></li>
          </ul>
        </div>
      </li>
      <li>
        <div class="head">
          <h2 class="title"><a href="#">New Headway</a></h2>
          <span class="note">Pre-intermediate</span>
        </div>
        <div class="thumbnail">
          <img alt="" src="/img/course_preview.png">
        </div>
        <div class="info">
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
        <div class="actions">
          <ul>
            <li><a href="#" title="Edit" rel="tooltip"><i class="icon-pencil"></i></a></li>
            <li><a href="#" title="Delete" rel="tooltip"><i class="icon-remove"></i></a></li>
          </ul>
        </div>
      </li>
      <li>
        <div class="head">
          <h2 class="title"><a href="#">New Headway</a></h2>
          <span class="note">Pre-intermediate</span>
        </div>
        <div class="thumbnail">
          <img alt="" src="/img/course_preview.png">
        </div>
        <div class="info">
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
        <div class="actions">
          <ul>
            <li><a href="#" title="Edit" rel="tooltip"><i class="icon-pencil"></i></a></li>
            <li><a href="#" title="Delete" rel="tooltip"><i class="icon-remove"></i></a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</div>