<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class='small-5 column'>
    <input type="hidden" id="current-course-id">
    <input type="hidden" id="current-lesson-id">

    <div id='teacher-courses'>
        <dl class='tabs'>
            <dd class='active'>
                <a href="#tab1">Курсы</a>
            </dd>
        </dl>
        <div class='tabs-content'>
            <div class='content active' id='tab-courses'>
                <div class='library_controls'>
                    <div class='row'>
                        <div class='small-6 column'>
                            <button class='tiny'>Новый курс</button>
                        </div>
                        <div class='small-6 column text-right'>
                            <button class='tiny secondary'>
                                <i class='fa-th-large'></i>
                            </button>
                            <button class='tiny secondary'>
                                <i class='fa-list-ul'></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div id='filter'>
                    <div class='control-group'>
                        <div class='controls row'>
                            <div class='small-6 column'>
                                <select class='custom_selectbox small-10'>
                                    <option selected='selected' value='Все уровни'>Все уровни</option>
                                    <option value='Beginner'>Beginner</option>
                                    <option value='Elementary'>Elementary</option>
                                    <option value='Pre-intermediate'>Pre-intermediate</option>
                                    <option value='Intermediate'>Intermediate</option>
                                    <option value='Upper-Intermediate'>Upper-Intermediate</option>
                                    <option value='Advance'>Advance</option>
                                </select>
                            </div>
                            <div class='small-6 column'>
                                <select class='custom_selectbox small-10'>
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
                        </div>
                    </div>
                </div>
                <ul class='teacher_courses no-bullet small-block-grid-3'>
                    <li>
                        <div class='teacher_courses-item'>
                            <h3 class='title'>Straightforward Advance</h3>

                            <div class='teacher_courses-image'>
                                <img src="/images/course_preview.png"/>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class='teacher_courses-item'>
                            <h3 class='title'>Straightforward Advance</h3>

                            <div class='teacher_courses-image'>
                                <img src="/images/course_preview.png"/>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>