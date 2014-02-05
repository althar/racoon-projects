<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="widget" id='folder'>
	<input type="hidden" id="current-folder-id" value="${folder.getLongValue('id')}">
  <div class="widget-header light">
    <span class="title">Менеджер файлов</span>
    <div id="progress-bar" class="hide">
		  <div id="upload-details"></div>
		  <div id="upload-progress-bar"></div>
		</div>
		<img src="/img/loader.gif" class="library-loader hide">
    <div class="toolbar">
      <span class="btn" data-toggle="collapse" data-target="#toolbar-ex">
        <i class="icon-search"></i>
      </span>
    </div>
  </div>
  <div id="toolbar-ex" class="toolbar form-toolbar collapse">
    <form class="search-form">
      <input type="text" class="span6 search-query" placeholder="Search...">
      <div class="pull-right">
        <select class='span6'>
          <option selected='selected' value='Все уровни'>Все уровни</option>
          <option value='Beginner'>Beginner</option>
          <option value='Elementary'>Elementary</option>
          <option value='Pre-intermediate'>Pre-intermediate</option>
          <option value='Intermediate'>Intermediate</option>
          <option value='Upper-Intermediate'>Upper-Intermediate</option>
          <option value='Advance'>Advance</option>
        </select>
        <select class='span6'>
          <option selected='selected' value='New English File '>New English
            File
          </option>
          <option value='New Headway'>New Headway</option>
          <option value='In company'>In company</option>
          <option value='Straightforward'>Straightforward</option>
          <option value='Face2face'>Face2face</option>
          <option value='Language Leader'>Language Leader</option>
        </select>
      </div>
    </form>
  </div>
  <div class="toolbar btn-toolbar">
    <div class="btn-group">
    	<span class="btn add-folder-link"><i class="icol-application-add"></i></span>
      <span class="btn add-file-link"><i class="icol-page-white-get"></i></span>
      <span class="btn add-test-link"><i class="icol-clipboard-text"></i></span>
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
		        <span class='item_name'><input class="new-folder-name" onClick='this.select();' tabIndex='1' type='text' value='Новая папка'></span>
		      </td>
		      <td></td>
	      </tr>
	      <c:forEach items="${folder.getRecords('folders')}" var="folder">
	        <tr class='folder-body-node' entity-type="folder" entity-id="${folder.getLongValue('id')}">
	        	<td>
		          <span class="icol-folder"></span>
		          <span class='item_name'>
		            <a class="entity-link" folder-id="${folder.getID()}" entity-type="folder" entity-id="${folder.getLongValue('id')}">${folder.getStringValue('name')}</a>
		            <input type="text" class="hide entity-input" entity-type="folder" entity-id="${folder.getLongValue('id')}" value="${folder.getStringValue('name')}">
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
	        <tr class='folder-body-node' entity-type="material" entity-id="${material.getLongValue('id')}">
	        	<td>
		        	<span class="icol-doc-pdf"></span>
		          <span class='item_name'>
		            <a class="entity-link" folder-id="${folder.getID()}" entity-type="material" entity-id="${material.getLongValue('id')}">${material.getStringValue('name')}</a>
		            <input type="text" class="hide entity-input" entity-type="material" entity-id="${material.getLongValue('id')}" value="${material.getStringValue('name')}">
		          </span>
		        </td>
	          <td class='folder-body-controls'>
	          	<a onclick="$.fileDownload('/service/download_material?id=${material.getLongValue('id')}')">
	              <span class='icol-page-white-put'></span>
	            </a>
	            <a class="rename-link" entity-type="material" entity-id="${material.getLongValue('id')}">
	              <span class="icol-pencil"></span>
	            </a>
	            <a class="delete-link" entity-type="material" entity-id="${material.getLongValue('id')}">
	              <span class="icol-cross"></span>
	            </a>
	          </td>
	        </tr>
	      </c:forEach>
	    </tbody>
    </table>

  </div>
  <div class="toolbar btn-toolbar">
  	<div class="btn-group">
  		<c:forEach items="${folder.getRecords('path')}" var="node" varStatus="loop">
  			<a class="entity-link btn" entity-type="folder" entity-id="${node.getLongValue('id')}" folder-id="${node.getLongValue('id')}">${node.getStringValue('name')}</a>
  		</c:forEach>
  	</div>
  </div>
</div>