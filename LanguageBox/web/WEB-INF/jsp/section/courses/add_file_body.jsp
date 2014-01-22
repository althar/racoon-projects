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
                <%--<li class='library_list-item'>--%>
                    <%--<div class='item_icon'>--%>
                        <%--<i class='fa-music'></i>--%>
                    <%--</div>--%>
                    <%--<div class='item_name'>--%>
                        <%--Some_file.mp3--%>
                    <%--</div>--%>
                <%--</li>--%>
            </ul>
            <form:form method="post" modelAttribute="add-file-form" enctype="multipart/form-data" action="/service/teacher/upload_files" >
                <input type='file' class="add-file-input" name="files" multiple>
                <a class='button tiny accept-add-file'>Загрузить</a>
                <input type="submit">
                <a class='button tiny secondary cancel-add-file'>Отменить</a>
            </form:form>
            <%--<form:form method="post" action="/service/teacher/upload_files" modelAttribute="uploadForm" enctype="multipart/form-data">--%>

                <%--<p>Select files to upload. Press Add button to add more file inputs.</p>--%>

                <%--<input id="addFile" type="button" value="Add File" />--%>
                <%--<table id="fileTable">--%>
                    <%--<tr>--%>
                        <%--<td><input name="files[0]" type="file" /></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><input name="files[1]" type="file" /></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <%--<br/><input type="submit" value="Upload" />--%>
            <%--</form:form>--%>
            <%--<form id="add-file-form" enctype="multipart/form-data" method="post">--%>
                <%--<input type='file' class="add-file-input" multiple>--%>
                <%--<a class='button tiny accept-add-file'>Загрузить</a>--%>
                <%--<a class='button tiny secondary cancel-add-file'>Отменить</a>--%>
            <%--</form>--%>

        </div>
    </div>
</div>