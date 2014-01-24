<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class='tabs-content library-section hidden' id='add-file'>
    <div class='content active'>
        <div class='addfile_block'>
            <h2>Добавить файл</h2>
            <ul class='library_list vertical_list add-file-list'>
            </ul>
            <form:form method="post" modelAttribute="add-file-form" enctype="multipart/form-data" action="/service/teacher/upload_files" >
                <input type="hidden" id="form-folder-id" value="" name="folder_id">
                <input type='file' class="add-file-input" name="files" multiple>
                <a class='button tiny accept-add-file'>Загрузить</a>
                <a class='button tiny secondary cancel-add-file'>Отменить</a>
            </form:form>
        </div>
    </div>
</div>