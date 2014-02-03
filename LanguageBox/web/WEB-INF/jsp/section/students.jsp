<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="span6 widget">
  <div class="widget-header">
    <span class="title">Новые ученики</span>
    <div class="toolbar">
      <div class="btn-group">
        <a class="btn" href="#">Show all</a>
      </div>
    </div>
  </div>
  <div class="widget-content studentslist">
    <ul class="thumbnails">
      <li>
        <div class="thumbnail">
          <img alt="" src="/sample/01.jpg">
        </div>
        <div class="info">
          <span class="name">Username</span>
          <span class="lvl">Elementary</span>
        </div>
        <div class="rating">
          <input type="radio" name="st1" class="star" checked="checked" disabled>
          <input type="radio" name="st1" class="star" checked="checked" disabled>
          <input type="radio" name="st1" class="star" disabled>
          <input type="radio" name="st1" class="star" disabled>
          <input type="radio" name="st1" class="star" disabled>
        </div>
      </li>
      <li>
        <div class="thumbnail">
          <img alt="" src="/sample/02.jpg">
        </div>
        <div class="info">
          <span class="name">Username</span>
          <span class="lvl">Elementary</span>
        </div>
        <div class="rating">
          <input type="radio" name="st2" class="star" checked="checked" disabled>
          <input type="radio" name="st2" class="star" disabled>
          <input type="radio" name="st2" class="star" disabled>
          <input type="radio" name="st2" class="star" disabled>
          <input type="radio" name="st2" class="star" disabled>
        </div>
      </li>
      <li>
        <div class="thumbnail">
          <img alt="" src="/sample/03.jpg">
        </div>
        <div class="info">
          <span class="name">Username</span>
          <span class="lvl">Elementary</span>
        </div>
        <div class="rating">
          <input type="radio" name="st3" class="star" checked="checked" disabled>
          <input type="radio" name="st3" class="star" checked="checked" disabled>
          <input type="radio" name="st3" class="star" disabled>
          <input type="radio" name="st3" class="star" disabled>
          <input type="radio" name="st3" class="star" disabled>
        </div>
      </li>
      <li>
        <div class="thumbnail">
          <img alt="" src="/sample/04.jpg">
        </div>
        <div class="info">
          <span class="name">Username</span>
          <span class="lvl">Elementary</span>
        </div>
        <div class="rating">
          <input type="radio" name="st4" class="star" checked="checked" disabled>
          <input type="radio" name="st4" class="star" checked="checked" disabled>
          <input type="radio" name="st4" class="star" checked="checked" disabled>
          <input type="radio" name="st4" class="star" checked="checked" disabled>
          <input type="radio" name="st4" class="star" disabled>
        </div>
      </li>
    </ul>
  </div>
</div>