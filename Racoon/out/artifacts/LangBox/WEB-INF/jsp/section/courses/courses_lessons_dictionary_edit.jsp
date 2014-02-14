<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="form-container">
  <form class="form-horizontal">

    <div class="control-group">
      <label class="control-label" for="word">Слово/фраза</label>
      <div class="controls">
        <input type="text" id="word" name="word" class="span12" value="begin">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="transcription">Транскрипция</label>
      <div class="controls">
        <input type="text" id="transcription" name="transcription" class="span12" value="[bɪ'gɪn]">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="translate">Перевод</label>
      <div class="controls">
        <input type="text" id="translate" name="translate" class="span12" value="начинать">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="audio">Произношение</label>
      <div class="controls">
        <input type="file" id="audio" name="audio">
        <button type="button" class="btn btn-success add-course-preview-butt">Загрузить</button>
        <button type="button" class="btn btn-danger remove-course-preview-butt">Удалить</button>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="descr_en">Описание на английском</label>
      <div class="controls">
        <textarea id="descr_en" class="span12 autosize" name="descr_en">I didn't realise the town had grown so much; I remember it as being just a small place.</textarea>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="examples">Примеры</label>
      <div class="controls">
        <textarea id="examples" class="span12 autosize" name="examples">I didn't realise the town had grown so much; I remember it as being just a small place. — Не думал, что город так разросся; я помню, что он был совсем небольшим. She continued to serve the old man faithfully, hoping to be remembered in his will. — Она продолжала верно служить старику, надеясь, что он что-нибудь ей оставит по завещанию.</textarea>
      </div>
    </div>

    <div class="form-actions">
      <a href="#" class="btn">Сохранить</a>
      <a href="#" class="btn">Отменить</a>
    </div>

  </form>
</div>