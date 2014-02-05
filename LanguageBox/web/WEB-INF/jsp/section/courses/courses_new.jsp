<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="widget span6">
  <div class="widget-header">
    <span class="title">Новый курс</span>
    <div class="toolbar">
      <span class="btn" rel="tooltip" data-original-title="Back">Назад</span>
    </div>
  </div>
  <div class="widget-content form-container">
    <form class="form-horizontal">
      <div class="control-group error">
        <label class="control-label" for="name">Название</label>
        <div class="controls">
          <input type="text" id="name" name="name" class="span12">
          <p class="help-block">This field is required</p>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lvl">Уровень</label>
        <div class="controls">
          <select id="lvl" name="lvl" class="span12">
            <option selected="" value="Все уровни">Все уровни</option>
            <option value="Beginner">Beginner</option>
            <option value="Elementary">Elementary</option>
            <option value="Pre-intermediate">Pre-intermediate</option>
            <option value="Intermediate">Intermediate</option>
            <option value="Upper-Intermediate">Upper-Intermediate</option>
            <option value="Advance">Advance</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="sale">Продажа</label>
        <div class="controls">
          <select id="sale" name="sale" class="span12">
            <option selected="" value="Все уровни">Все уровни</option>
            <option value="Beginner">Beginner</option>
            <option value="Elementary">Elementary</option>
            <option value="Pre-intermediate">Pre-intermediate</option>
            <option value="Intermediate">Intermediate</option>
            <option value="Upper-Intermediate">Upper-Intermediate</option>
            <option value="Advance">Advance</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="for">Для кого</label>
        <div class="controls">
          <select id="for" name="for" class="span12">
            <option value="Не выбрано">Не выбрано</option>
            <option value="Для взрослых">Для взрослых</option>
            <option value="Для детей">Для детей</option>
            <option value="Для подростков">Для подростков</option>
            <option selected="" value="Бизнес">Бизнес</option>
            <option value="Профессиональный">Профессиональный</option>
            <option value="Для всех">Для всех</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="type">Вид курса</label>
        <div class="controls">
          <select id="type" name="type" class="span12">
            <option value="Не выбрано">Не выбрано</option>
            <option value="Аудио курс">Аудио курс</option>
            <option value="Видео курс">Видео курс</option>
            <option value="Лингафонный курс">Лингафонный курс</option>
            <option selected="" value="Стандартный курс">Стандартный курс</option>
            <option value="Аудио книга">Аудио книга</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="price">Цена</label>
        <div class="controls">
          <input type="text" id="price" name="price" class="span12" data-accept="numbers">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="descr">Описание</label>
        <div class="controls">
          <textarea id="descr" name="descr" class="span12 autosize"></textarea>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="pic">Обложка</label>
        <div class="controls">
          <input type="file" id="pic" name="pic">
          <button type="button" class="btn btn-success">Загрузить</button>
          <button type="button" class="btn btn-danger">Удалить</button>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="link">Внешняя ссылка</label>
        <div class="controls">
          <input type="text" id="link" name="link" class="span12">
        </div>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn btn-primary">Сохранить</button>
        <button type="reset" class="btn">Отменить</button>
      </div>
    </form>
  </div>
</div>