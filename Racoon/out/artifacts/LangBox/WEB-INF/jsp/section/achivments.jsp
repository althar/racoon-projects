<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="span6 widget">
  <div class="widget-header">
    <span class="title">Мои достижения</span>
  </div>
  <div class="widget-content">

    <div class="user-box">
      <div class="thumbnail">
        <img alt="" src="/assets/images/pp.jpg">
      </div>
      <div class="info">
        <span class="name">User Name <sup><i class="icol-crown"></i></sup><small>1 место из 52 человек (+5 позиций)</small></span>
        <ul class="attributes">
          <li>
            <select>
              <option value="Школьник">Школьник</option>
              <option value="Абитуриент">Абитуриент</option>
              <option selected="" value="Студент">Студент</option>
              <option value="Магистр">Магистр</option>
              <option value="Бакалавр">Бакалавр</option>
              <option value="Кандидат наук">Кандидат наук</option>
              <option value="Профессор">Профессор</option>
            </select>
          </li>
          <li><i class="icol-award-star-gold"></i> Лучший месяца. Апрель</li>
          <li><i class="icol-medal-gold-1"></i> Лучший недели. Июль</li>
          <li><i class="icol-rosette"></i> Выученных слов - <strong>156</strong></li>
        </ul>
        <div class="btn-group">
          <a href="#" class="btn" style="color: #3B5998;" title="Share on Facebook" rel="tooltip" data-placement="bottom"><i class="icon-facebook"></i></a>
          <a href="#" class="btn" style="color: #9AE4E8;" title="Share on Twitter" rel="tooltip" data-placement="bottom"><i class="icon-twitter"></i></a>
        </div>
      </div>
    </div>
    <hr/>
    <div class="row-fluid stats-container user-stats">
      <div class="span3">
        <span class="stat summary">
          <span class="icon icon-square bg-orange">
            <i class="icon-feather"></i>
          </span>
          <span class="digit">
            <span class="text">Writing</span>
            30
          </span>
        </span>
      </div>
      <div class="span3">
        <span class="stat summary">
          <span class="icon icon-square bg-red">
            <i class="icon-book"></i>
          </span>
          <span class="digit">
            <span class="text">Reading</span>
            10
          </span>
        </span>
      </div>
      <div class="span3">
        <span class="stat summary">
          <span class="icon icon-square bg-blue">
            <i class="icon-bullhorn"></i>
          </span>
          <span class="digit">
            <span class="text">Listening</span>
            56
          </span>
        </span>
      </div>
      <div class="span3">
        <span class="stat summary">
          <span class="icon icon-square bg-green">
            <i class="icon-pacman"></i>
          </span>
          <span class="digit">
            <span class="text">Speaking</span>
            12
          </span>
        </span>
      </div>
    </div>
  </div>
</div>