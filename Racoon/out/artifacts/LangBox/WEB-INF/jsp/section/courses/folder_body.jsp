<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class='tabs-content library-section' id='folder'>
    <input type="hidden" id="current-folder-id" value="${folder.getLongValue('id')}">
    <div class='content active'>
        <ul class='breadcrumbs'>
            <c:forEach items="${folder.getRecords('path')}" var="node" varStatus="loop">
                <li <c:if test="${loop.last}">class='current'</c:if>>
                    <a class="entity-link" entity-type="folder" entity-id="${node.getLongValue('id')}" folder-id="${node.getLongValue('id')}">${node.getStringValue('name')}</a>
                </li>
            </c:forEach>
        </ul>
        <div class='library_controls'>
            <div class='row'>
                <div class='small-6 column'>
                    <div class='dropdown'>
                        <div class='dropdown_inner'>
                            <a class='button tiny' href='#'>
                                Добавить
                                <i class='fa-sort-down'></i>
                            </a>
                            <ul class='f-dropdown add-button'>
                                <li>
                                    <a class="add-file-link">Файл</a>
                                </li>
                                <li>
                                    <a class="add-folder-link">Папку</a>
                                </li>
                                <li>
                                    <a class="add-test-link">Тест</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class='small-6 column text-right'>
                    <button class='tiny'>Поиск</button>
                    <button class='tiny secondary'>
                        <i class='fa-th-large'></i>
                    </button>
                    <button class='tiny secondary'>
                        <i class='fa-list-ul'></i>
                    </button>
                </div>
            </div>
        </div>
        <!-- change class "vertical_list" to "horizontal_list" -->
        <ul class='library_list vertical_list folder-body'>
            <li class='library_list-item new-folder hidden'>
                <div class='item_icon'>
                    <i class='fa-folder-open'></i>
                </div>
                <div class='item_name'>
                    <input class="new-folder-name" onClick='this.select();' tabIndex='1' type='text' value='Новая папка'>
                </div>
            </li>
            <c:forEach items="${folder.getRecords('folders')}" var="folder">
                <li class='library_list-item' entity-type="folder" entity-id="${folder.getLongValue('id')}">
                    <div class='item_icon'>
                        <i class='fa-folder-open'></i>
                    </div>
                    <div class='item_name'>
                        <a class="entity-link" folder-id="${folder.getID()}" entity-type="folder" entity-id="${folder.getLongValue('id')}">${folder.getStringValue('name')}</a>
                        <input type="text" class="hidden entity-input" entity-type="folder" entity-id="${folder.getLongValue('id')}" value="${folder.getStringValue('name')}">
                        <div class='library_list-controls'>
                            <a class="delete-link" entity-type="folder" entity-id="${folder.getLongValue('id')}">Удалить
                                <i class='fa-arrow-right'></i>
                            </a>
                            <a class="rename-link" entity-type="folder" entity-id="${folder.getLongValue('id')}">Переименовать
                                <i class='fa-arrow-right'></i>
                            </a>
                        </div>
                    </div>
                </li>
            </c:forEach>
            <c:forEach items="${folder.getRecords('materials')}" var="material">
                <li class='library_list-item' entity-type="material" entity-id="${material.getLongValue('id')}">
                    <%--<div class='item_icon'>--%>
                        <%--<i class='fa-folder-open'></i>--%>
                    <%--</div>--%>
                    <div class='item_name'>
                        <a class="entity-link" folder-id="${folder.getID()}" entity-type="material" entity-id="${material.getLongValue('id')}">${material.getStringValue('name')}</a>
                        <input type="text" class="hidden entity-input" entity-type="material" entity-id="${material.getLongValue('id')}" value="${material.getStringValue('name')}">
                        <div class='library_list-controls'>
                            <a class="delete-link" entity-type="material" entity-id="${material.getLongValue('id')}">Удалить
                                <i class='fa-arrow-right'></i>
                            </a>
                            <a class="rename-link" entity-type="material" entity-id="${material.getLongValue('id')}">Переименовать
                                <i class='fa-arrow-right'></i>
                            </a>
                            <%--<a href="/service/teacher/download_material?id=${material.getLongValue('id')}">Скачать--%>
                            <a onclick="$.fileDownload('/service/teacher/download_material?id=${material.getLongValue('id')}')">Скачать
                                <i class='fa-arrow-right'></i>
                            </a>
                        </div>
                    </div>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>