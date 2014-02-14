<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="widget library-section" id='folder'>
  <input type="hidden" id="current-folder-id" value="${folder.getLongValue('id')}">

  <div class="widget-header light">
    <span class="title">Менеджер файлов</span>

    <%--<div id="progress-bar" class="hide">--%>
      <%--<div id="upload-details"></div>--%>
      <%--<div id="upload-progress-bar"></div>--%>
    <%--</div>--%>
    <%--<img src="/img/loader.gif" class="library-loader hide">--%>

    <div class="toolbar">
      <span class="btn" data-toggle="collapse" data-target="#toolbar-ex1">
        <i class="icon-search"></i>
      </span>
    </div>
  </div>
  <div id="toolbar-ex1" class="toolbar form-toolbar collapse">
    <form class="search-form">
      <input type="text" class="span6 search-query library-search-input" placeholder="Поиск">

      <div class="pull-right">
        <select class='span6 library-search-select' style="width:100%;">
          <option selected='selected' value='ALL'>Все</option>
          <option value='AUDIO_FILE'>Аудио</option>
          <option value='VIDEO_FILE'>Видео</option>
          <option value='DOCUMENT_FILE'>Документ</option>
          <option value='CUSTOM_FILE'>Прочее</option>
        </select>
      </div>
    </form>
  </div>
  <div class="toolbar btn-toolbar">
    <div class="btn-group">
      <span class="btn add-folder-link"><i class="icol-application-add"></i></span>
      <span class="btn add-file-link"><i class="icol-page-white-get"></i></span>
      <span class="btn add-test-link"><i class="icol-clipboard-text"></i></span>
      <span class="btn upload-progress-link" style="display: none;"><i><img src="/img/loader.gif" class="library-loader"><span class="upload-progress-description"></span></i></span>
    </div>
  </div>
  <div class="widget-content table-container">

    <table class='table folder-body'>
      <colgroup>
        <col>
        <col style="width: 100px">
      </colgroup>
      <tbody>
      <tr class='folder-body-node new-folder hide'>
        <td>
          <span class="icol-folder"></span>
          <span class='item_name'><input class="new-folder-name" onClick='this.select();' tabIndex='1'
                           type='text' value='Новая папка'></span>
        </td>
        <td></td>
      </tr>
      <c:forEach items="${folder.getRecords('folders')}" var="folder">
        <tr class='folder-body-node' entity-type="folder" entity-id="${folder.getLongValue('id')}">
          <td>
            <span class="icol-folder"></span>
          <span class='item_name'>
          <a class="entity-link" folder-id="${folder.getID()}" entity-type="folder"
             entity-id="${folder.getLongValue('id')}">${folder.getStringValue('name')}</a>
          <input type="text" class="hide entity-input" entity-type="folder"
               entity-id="${folder.getLongValue('id')}" value="${folder.getStringValue('name')}">
          </span>
          </td>
          <td class='folder-body-controls'>
            <a class="rename-link" entity-type="folder" entity-id="${folder.getLongValue('id')}">
              <span class="icol-pencil"></span>
            </a>
            <a class="delete-link" entity-type="folder" entity-id="${folder.getLongValue('id')}">
              <span class="icol-cross"></span>
            </a>
          </td>
        </tr>
      </c:forEach>
      <c:forEach items="${folder.getRecords('materials')}" var="material">
        <tr class='folder-body-node library_list-item' entity-type="material" entity-id="${material.getLongValue('id')}" entity-category-type="${material.getStringValue('type')}" entity-name="${material.getStringValue('name')}">
          <td>
            <span class="
            <c:if test="${material.getStringValue('type')=='CUSTOM_FILE'}">icol-box</c:if>
            <c:if test="${material.getStringValue('type')=='DOCUMENT_FILE'}">icol-bookmark-document</c:if>
            <c:if test="${material.getStringValue('type')=='AUDIO_FILE'}">icol-music-beam</c:if>
            <c:if test="${material.getStringValue('type')=='VIDEO_FILE'}">icol-films</c:if>
            <c:if test="${material.getStringValue('type')=='IMAGE_FILE'}">icol-image-1</c:if>
            "></span>
          <span class='item_name'>
          <a class="entity-link" folder-id="${folder.getID()}" entity-type="material"
             entity-id="${material.getLongValue('id')}">${material.getStringValue('name')}</a>
          <input type="text" class="hide entity-input" entity-type="material"
               entity-id="${material.getLongValue('id')}" value="${material.getStringValue('name')}">
          </span>
          </td>
          <td class='folder-body-controls'>

            <div id="replace" style="display: none">

              <a href="#"><small class="muted">Перенести</small> <span class='icol-arrow-right'></span></a>

            </div>

            <div id="replaced" style="display: none">

              <small class="muted">Добавлен...</small>

            </div>

            <div id="main_controls" style="display: block">

              <a onclick="$.fileDownload('/service/download_material?id=${material.getLongValue('id')}')">
                <span class='icol-page-white-put'></span>
              </a>
              <a class="rename-link" entity-type="material" entity-id="${material.getLongValue('id')}">
                <span class="icol-pencil"></span>
              </a>
              <a class="delete-link" entity-type="material" entity-id="${material.getLongValue('id')}">
                <span class="icol-cross"></span>
              </a>

            </div>
          </td>
        </tr>
      </c:forEach>

      </tbody>
    </table>

  </div>
  <div class="toolbar btn-toolbar">
    <div class="btn-group">
      <c:forEach items="${folder.getRecords('path')}" var="node" varStatus="loop">
        <a class="entity-link btn" entity-type="folder" entity-id="${node.getLongValue('id')}"
           folder-id="${node.getLongValue('id')}">${node.getStringValue('name')}</a>
      </c:forEach>
    </div>
  </div>
</div>