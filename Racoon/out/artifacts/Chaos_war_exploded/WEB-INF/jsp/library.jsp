<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Chaos administration</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="/js/logic/library.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.2.custom.js"></script>
    <script type="text/javascript" src="/js/swfobject.js"></script>
    <script type="text/javascript" src="/js/jquery.timer.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#info").everyTime(20, function () {
                getStatus();
            });
        });
    </script>
    <script type="text/javascript">
        function getStatus() {
            $("#info").html("!");
            document.getElementById("SocketFileUploader").GetStatus();

        }

        function onStatus(progress, status, file, filePath, fileId, statusDescription) {
            if (status == "reading") {
                $("#info").html("Scanning file... ("+progress / 100 + "%)");
            }
            else if (status == "in_progress") {
                $("#info").html(progress / 100 + "%");
            }
            else if (status == "uploaded") {
                $("#info").html("Done");
            }
            else if (status == "cancelled_by_client") {
                $("#info").html("cancelled_by_client: "+statusDescription);
            }
            else if (status == "cancelled_by_server") {
                $("#info").html("cancelled_by_server: "+statusDescription);
            }
            else {
                //$("#info").html(progress);
            }
        }
        function stopUpload() {
            document.getElementById("SocketFileUploader").StopUpload();
        }
        var swfVersionStr = "11.1.0";
        // To use express install, set to playerProductInstall.swf, otherwise the empty string.
        var xiSwfUrlStr = "/swf/playerProductInstall.swf";
        var flashvars = {};
        flashvars.fileDescription = "Books";
        flashvars.fileExtensions = "*.*";
        flashvars.parameters = "book_id=1";
        flashvars.chunkSize = 10240;
        flashvars.chunksPerTick = 5;
        flashvars.serverAddress = "localhost:8070";
        flashvars.buttonLabel = "Загрузить книгу"
        var params = {};
        params.quality = "high";
        params.bgcolor = "#ffffff";
        params.wmode = "transparent";
        params.allowscriptaccess = "sameDomain";
        params.allowfullscreen = "true";
        var attributes = {};
        attributes.id = "SocketFileUploader";
        attributes.name = "SocketFileUploader";
        attributes.align = "middle";
        swfobject.embedSWF(
                "/swf/SocketFileUploader.swf", "flashContent",
                "150", "50",
                swfVersionStr, xiSwfUrlStr,
                flashvars, params, attributes);
        // JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
        swfobject.createCSS("#flashContent", "display:block;text-align:left;");
    </script>
</head>
<section id="page">
    <div class="add-book">
        <div class="book-fields">
            <h2>Книга</h2>
            <input type="text" id="book-name"><br>
            Выбрать уже созданную книгу:

            <div id="books">

            </div>

            Автор: <input type="text" id="book-author">
            <br>
            Тип:
            <select id="book-type">
                <option value="Учебная литература">Учебная литература</option>
                <option value="Профессиональная литература" selected="true">Профессиональная литература</option>
                <option value="Жудожественная литература">Жудожественная литература</option>
                <option value="Журнал">Журнал</option>
            </select>
            <br>
            Тема: <select id="book-genre">
            <option value="Бизнес" selected="true">Бизнес</option>
            <option value="Наука">Наука</option>
            <option value="Медицина">Медицина</option>
            <option value="Спорт">Спорт</option>
            <option value="Культура">Культура</option>
            <option value="Туризм">Туризм</option>
            <option value="Музыка">Музыка</option>
            <option value="Фантастика">Фантастика</option>
            <option value="История">История</option>
            <option value="Поэзия">Поэзия</option>
        </select>
            <br>
            <input type="hidden" id="book-id">
            <input type="button" id="save-book" value="Сохранить книгу">
            <input type="button" id="new-book" value="Новая книга">
        </div>
        <br>
        <div id="book-uploader">
            <div id="flashContent">
            </div>
            <div id="info"></div>
        </div>
        <div class="chapter-fields" id="chapter-fields">
            <br>

            <br>
            <h2>Глава</h2><img id="loader" class="hidden" src="/img/gui/loader.gif">
            <input type="text" id="chapter-name">
            Ключевые слова через запятую: <input type="text" id="chapter-keywords">
        </div>
        <div class="chapter" id="chapter">
            <textarea id="text" rows="30" cols="145">

            </textarea>
            <input type="button" id="save-chapter" value="Сохранить главу">
        </div>

        <div class="chapter-images" id="chapter-images">

        </div>

    </div>

    <div class="favorite-topics"></div>

    <div class="content-block"></div>

    <div class="links">

    </div>
</section>
<%@ include file="footer.jsp" %>