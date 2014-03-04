<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="widget hide library-section" id='add-file'>
  <div class="widget-header">
    <span class="title">Добавить файл</span>
  </div>
  <div class="widget-content">
    <form:form method="post" modelAttribute="add-file-form" enctype="multipart/form-data" action="/service/upload_files" accept-charset="UTF-8">
      <input type="hidden" id="form-folder-id" value="" name="folder_id">
    </form:form>
    <input type="file" class="add-file-input" name="files" multiple>
    <a class='btn btn-primary accept-add-file'>Загрузить</a>
    <a class='btn cancel-add-file'>Отменить</a>
    <ul class='add-file-list unstyled'>
    </ul>

  </div>
</div>