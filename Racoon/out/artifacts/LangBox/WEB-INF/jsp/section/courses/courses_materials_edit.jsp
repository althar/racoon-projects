<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="widget span6  courses-section course-lesson-edit-section">
  <div class="widget-header">
    <span class="title">New Headway</span>
    <div class="toolbar">
      <span class="btn" rel="tooltip" data-original-title="Back">Назад</span>
    </div>
  </div>
  <div class="widget-content">
    <div class="user-box">
      <div class="thumbnail">
        <img alt="" src="/img/course_preview.png">
      </div>
      <div class="info">
        <span class="name">New Headway <small>Pre-intermediate</small></span>
        <ul class="attributes">
          <li>Профессиональный, Аудио книга</li>
          <li>14 Уроков</li>
          <li><span class="muted">Дата создания: 19.02.2014</span></li>
        </ul>
      </div>
    </div>
    <hr/>

    <div class="tabbable analytics-tab">
      <ul class="nav nav-tabs">
        <li class="active"><a href="#" data-target="#materials" data-toggle="tab"><i class="icon-attachment"></i> Основной материал к курсу</a></li>
      </ul>
      <div class="tab-content">
        <div id="materials" class="tab-pane active verticalist">
          <ul class="thumbnails">

            <li>
              <span class="info">
                <span class="head">
                  <h2><span class="icol-doc-pdf"></span> Item: 1</h2>
                  <span class="order">19.02.2014</span>
                </span>
              </span>
              <div class="actions">
                <a href="#"><i class="icon-remove"></i></a>
              </div>
            </li>
            <li>
              <span class="info">
                <span class="head">
                  <h2><span class="icol-doc-music"></span> Item: 2</h2>
                  <span class="order">19.02.2014</span>
                </span>
              </span>
              <div class="actions">
                <a href="#"><i class="icon-remove"></i></a>
              </div>
            </li>
            <li>
              <span class="info">
                <span class="head">
                  <h2><span class="icol-doc-offlice"></span> Item: 3</h2>
                  <span class="order">19.02.2014</span>
                </span>
              </span>
              <div class="actions">
                <a href="#"><i class="icon-remove"></i></a>
              </div>
            </li>

            <li>
              <span class="info">
                <span class="head">
                  <h2><span class="icol-doc-film"></span> Item: 4</h2>
                  <span class="order">19.02.2014</span>
                </span>
              </span>
              <div class="actions">
                <a href="#"><i class="icon-remove"></i></a>
              </div>
            </li>

          </ul>

        </div>
      </div>

    </div>

    <a class="btn btn-primary" href="">Сохранить</a>

  </div>
</div>