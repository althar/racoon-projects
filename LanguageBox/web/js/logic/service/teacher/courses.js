var isCheckingUpload = true;
var checkInterval = 1000;
var uploadDelay = 0;
var currentCourseSection = "courses_list";

$(document).ready(function () {
    library.bindLibraryControls();
    courses.bindCoursesControls();

    library.checkUploadFiles();
    courses.showCourses();
});

library =
{
    bindLibraryControls: function () {
        // Browse folders
        $(".entity-link[entity-type=\"folder\"]").unbind("click");
        $(".entity-link[entity-type=\"folder\"]").bind("click", function () {
            var folder_id = $(this).attr("folder-id");
            library.showFolder(folder_id);
        });

        // Search
        var search = function () {

            var category = $(".library-search-select option:selected").val();
            var text = $(".library-search-input").val();
            console.log("Search: " + category + "  :  " + text);
            $("tr[entity-type=\"material\"]").hide();
            $("tr[entity-type=\"material\"]").filter(function () {
                console.log($(this).attr("entity-category-type") + "!");
                console.log($(this).attr("entity-category-type") + "!!");
                console.log($(this).attr("entity-category-type") + "!!!");
                return ($(this).attr("entity-category-type") == category || category == "ALL") && $(this).attr("entity-name").toLowerCase().indexOf(text.toLowerCase()) != -1;
            }).show();
        }
        $(".library-search-input").keyup(function () {
            search();
        });
        $(".library-search-select").change(function () {
            search();
        });

        $(".add-folder-link").unbind("click");
        $(".add-folder-link").bind("click", function () {
            library.createFolder();
        })
        $(".new-folder-name").unbind("keypress");
        $(".new-folder-name").keypress(function (e) {
            var name = $(this).val();
            var folder_id = $("#current-folder-id").val();
            if (e.which == 13 && name.length > 0) {
                $.ajax({
                    url: "/service/create_folder?folder_name=" + name + "&folder_id=" + folder_id,
                    context: document.body,
                    async: true,
                    success: function (html) {
                        library.showFolder(folder_id);
                    },
                    failure: function () {
                        alert("Серверная ошибка. Попробуйте позже");
                        $(".library-loader").show();
                    }
                });
            }
        });

        // Delete folder or material
        $(".delete-link").unbind("click");
        $(".delete-link").click(function () {
            var link = $(this);
            $("#dialog-message").dialog({
                buttons: {
                    'Да': function () {
                        var type = $(link).attr("entity-type");
                        var id = $(link).attr("entity-id");
                        $(this).dialog('close');
                        $(".library-loader").show();
                        $.ajax({
                            url: "/service/delete?type=" + type + "&id=" + id,
                            context: document.body,
                            async: true,
                            success: function (html) {
                                $("tr[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").hide();
                                $(".library-loader").hide();
                            },
                            failure: function () {
                                alert("Серверная ошибка. Попробуйте позже");
                                $(".library-loader").hide();
                            }
                        });
                    },
                    'Нет': function () {
                        $(this).dialog('close');
                    }
                }
            });
        });

        // Rename folder or material
        $(".entity-input").unbind("keypress");
        $(".entity-input").keypress(function (e) {
            var name = $(this).val();
            if (e.which == 13 && name.length > 0) {
                var type = $(this).attr("entity-type");
                var id = $(this).attr("entity-id");

                $(".library-loader").show();
                $.ajax({
                    url: "/service/rename?id=" + id + "&type=" + type + "&name=" + name,
                    context: document.body,
                    async: true,
                    success: function (html) {
                        if (html == "ok") {
                            $(".entity-link[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").html(name);
                            $(".entity-input[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").hide();
                            $(".entity-link[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").show();
                        }
                        else {
                            alert("Серверная ошибка. Попробуйте позже");
                        }
                        $(".library-loader").hide();
                    },
                    failure: function () {
                        alert("Серверная ошибка. Попробуйте позже");
                        $(".library-loader").hide();
                    }
                });
            }
        });
        $(".rename-link").unbind("click");
        $(".rename-link").click(function () {
            var type = $(this).attr("entity-type");
            var id = $(this).attr("entity-id");
            $(".entity-link[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").hide();
            $(".entity-input[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").show();
            $(".entity-input[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").select();
        })

        // Add file
        $(".add-file-link").unbind("click");
        $(".add-file-link").bind("click", function () {
            library.showAddFile();
        });

        $(".cancel-add-file").unbind('click');
        $(".cancel-add-file").click(function () {
            var currentFolderId = $("#current-folder-id").val();
            library.showFolder(currentFolderId);
        });
        $(".accept-add-file").unbind("click");
        $(".accept-add-file").click(function () {
            var currFolderId = $("#current-folder-id").val();
            $("#form-folder-id").val(currFolderId);
            var formData = new FormData($("#add-file-form")[0]);
            $(".library-loader").show();
            $.ajax({
                url: "/service/upload_files",  //Server script to process data
                type: 'POST',
                success: function () {
                    isCheckingUpload = true;
                },
                error: function () {
                    //alert("Что-то пошло не так. Попробуйте снова через час.");
                    //$(".library-loader").hide();
                },
                // Form data
                data: formData,
                //Options to tell jQuery not to process data or worry about content-type.
                cache: false,
                contentType: false,
                processData: false
            });
            $("#add-file").hide();
            var currentFolderId = $("#current-folder-id").val();
            library.showFolder(currentFolderId);
        });

        // Show files controls
        courses.showFilesControls();

        // Add to lesson
        $(".add-to-lesson-link").unbind("click");
        $(".add-to-lesson-link").click(function(){
            var name = $(this).attr("entity-name");
            var id = $(this).attr("entity-id");
            var cla = $(this).attr("icon");
            var item = $("#lesson-material-template").clone();
            $(item).find(".icol-doc-pdf").attr("class",cla);
            $(item).attr("entity-id",id);
            $(item).find(".lesson-item-name").html(name);
            $(item).removeClass("hidden");
            $(item).addClass("lesson-material-item");

            $(".lesson-material-list").append(item);
        });
    },
    bindAddFileChange: function () {
        $(".add-file-input").unbind('change');
        $(".add-file-input").change(function () {
            for (var i = 0; i < this.files.length; ++i) {
                var name = this.files.item(i).name;
                var fileItem = "<li class='library_list-item'><div class='item_icon'><i class='fa-music'></i></div><div class='item_name'>"
                    + name.split('\\').pop();
                +"<div class='item_delete'><a><i class='fa-times'></i></a></div></div></li>";
                $(".add-file-list").append(fileItem);
            }
            $(this).appendTo("#add-file-form");
            $("#add-file-form").after("<input type='file' class='add-file-input' name='files' multiple>");
            $("#add-file-form>input[type=\"file\"]").hide();

            library.bindAddFileChange();
        });
    },
    showFolder: function (folder_id) {
        //1. Hide all other library sections
        $(".library-section").hide();
        //2. Show loader
        $(".library-loader").show();
        //3. Request folder-body, and insert it
        var folderParam = "";
        if (folder_id != null) {
            folderParam = "?folder_id=" + folder_id;
            $("#current-folder-id").val(folder_id);
        }
        $.ajax({
            url: "/service/get_folder" + folderParam,
            context: document.body,
            async: true,
            success: function (html) {
                $("#folder").replaceWith(html);
                library.bindLibraryControls();
                $(".library-loader").hide();
                $("#folder").show();
            },
            failure: function () {
                alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    },
    showAddFile: function (folder_id) {
        //1. Hide all other library sections
        $(".library-section").hide();
        //2 . Remove previous
        $(".add-file-list li").remove();
        $("#add-file-form>input[type=\"file\"]").remove();
        $(".add-file-input").replaceWith("<input type='file' class='add-file-input' name='files' multiple>");
        library.bindAddFileChange();
        //3. Show add file form
        $("#add-file").show();
    },
    createFolder: function () {
        //1. Clear new folder control
        $(".new-folder-name").val("Новая папка");

        //2. Show new folder control
        $(".new-folder").show();
        $(".new-folder-name").select();


    },
    checkUploadFiles: function () {
        // For progress bar
        var check = function () {
            $.ajax({
                url: "/service/upload_progress",
                async: true,
                dataType: "xml",
                success: function (xml) {

                    if (isCheckingUpload) {
                        var uploaded = $("root>progress", xml).text();
                        var isUploading = $("root>is_uploading", xml).text();

                        if (isUploading == "true") {
                            uploadDelay = 0;
                            uploaded = uploaded / 100.0;
                            fileProgressBar(true);
                            $(".upload-progress-description").html(" Загрузка файлов " + uploaded + "%");
                            $(window).on('beforeunload', function () {
                                return 'Файлы в процессе закачки. Вы уверены, что хотите оборвать процесс?';
                            });
                        }
                        else {
                            uploadDelay++;
                            showNewFiles();
                            if (uploadDelay > 3) {
                                fileProgressBar(false);
                                $(window).unbind('beforeunload');
                            }
                        }
                    }
                    setTimeout(check, checkInterval);
                },
                failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                    $(".library-loader").show();
                }
            });
        };
        check();
        // For appearing new files
        var showNewFiles = function () {
            var folder_id = $("#current-folder-id").val();
            var folderParam = "";
            if (folder_id != null) {
                folderParam = "?folder_id=" + folder_id;
            }
            $.ajax({
                url: "/service/get_folder" + folderParam,
                context: document.body,
                async: true,
                success: function (html) {

                    var wasUpdate = false;
                    $(".library_list-item[entity-type=\"material\"]", html).each(function () {
                        var material_id = $(this).attr("entity-id");
                        var new_li = $(this);
                        var found = $(".library_list-item[entity-type=\"material\"][entity-id=\"" + material_id + "\"]");
                        if ($(found).length == 0)// New one
                        {
                            $(".folder-body").append($(this));
                            wasUpdate = true;
                        }
                    });
                    if (wasUpdate) {
                        library.bindLibraryControls();
                    }
                },
                failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                    $(".library-loader").show();
                }
            });
        };

    }
}
courses =
{
    bindCoursesControls: function () {
        // Add new course
        $(".add-course-button").unbind("click");
        $(".add-course-button").click(function () {
            courses.showEditCourse();
        });

        // Select course
        $(".edit-course-button").unbind("click");
        $(".edit-course-button").click(function () {
            var courseId = $(this).attr("course-id");
            courses.showEditCourse(courseId);
        });

        // Save course
        $(".add-course-preview-butt").unbind("click");
        $(".add-course-preview-butt").click(function () {
            // Send picture and return id of picture
            // Show picture
        });
        $(".remove-course-preview-butt").unbind("click");
        $(".remove-course-preview-butt").click(function () {
            $(".add-course-preview-butt").removeAttr("preview-id");
        });
        $(".save-course-butt").unbind("click");
        $(".save-course-butt").click(function () {

            var formData = new FormData($(".course-edit-form")[0]);
            $.ajax({
                url: "/service/save_course",  //Server script to process data
                type: 'POST',
                success: function (course_id) {
                    console.log("Course save response id=: " + course_id);
                    courses.showCourseLessons(course_id);
                },
                error: function () {
                    //alert("Что-то пошло не так. Попробуйте снова через час.");
                    //$(".library-loader").hide();
                },
                data: formData,
                cache: false,
                contentType: false,
                processData: false
            });
        });
        // Delete course
        $(".delete-course-button").unbind("click");
        $(".delete-course-button").click(function () {
            var course_id = $(this).attr("course-id");
            $("#dialog-message-delete-course").dialog({
                buttons: {
                    'Да': function () {
                        $(this).dialog('close');
                        $(".library-loader").show();
                        $.ajax({
                            url: "/service/delete?type=course&id=" + course_id,
                            context: document.body,
                            async: true,
                            success: function (html) {
                                $(".course-item[id=\""+course_id+"\"]").remove();
                            },
                            failure: function () {
                                alert("Серверная ошибка. Попробуйте позже");
                                $(".library-loader").hide();
                            }
                        });
                    },
                    'Нет': function () {
                        $(this).dialog('close');
                    }
                }
            });
        });

        // Remove lesson material
        $(".remove-lesson-material-link").unbind("click");
        $(".remove-lesson-material-link").click(function(){

        });

        // Add controls for files
        courses.showFilesControls();

        // HTML Editor
        $("#cleditor").cleditor({
            width: '100%'
        });

        // Save lesson
        $(".save-lesson-butt").unbind("click");
        $(".save-lesson-butt").click(function(){
            $(this).attr("disabled","disabled");
            var butt = $(this);
            var name = $("#lesson-name").val();
            var type = $("#lesson-type").val();
            var id = $("#lesson-id").val();
            var course_id = $("#course-id").val();
            var trial = $("#trial-access").attr("checked")!=null;
            var task = $($("#cleditor").cleditor()[0].$area).val();

            var postData = new Object();
            postData.name=name;
            postData.trial=trial;
            postData.task=task;
            postData.type=type;
            postData.id=id;
            postData.courseId=course_id;
            var mats = new Array();
            var index = 0;
            $(".lesson-material-item").each(function(){
                var material_id = $(this).attr("entity-id");
                mats[index] = material_id;
                index++;
            });
            postData.material = mats;

            $.ajax({
                type: 'POST',
                url: '/service/save_lesson',
                data: JSON.stringify(postData),
                contentType: "application/json",
                success: function(msg){
                    $("#lesson-id").val(msg);
                    $(butt).removeAttr("disabled");
                }
            });
        });

        // Delete lesson
        $(".lesson-delete-link").click(function () {
            var link = $(this);
            $("#dialog-message-delete-lesson").dialog({
                buttons: {
                    'Да': function () {
                        var type = $(link).attr("entity-type");
                        var id = $(link).attr("entity-id");
                        $(this).dialog('close');
                        $.ajax({
                            url: "/service/delete?type=" + type + "&id=" + id,
                            context: document.body,
                            async: true,
                            success: function (html) {
                                $(link).parents("tr").remove();
                            },
                            failure: function () {
                                alert("Серверная ошибка. Попробуйте позже");
                                $(".library-loader").hide();
                            }
                        });
                    },
                    'Нет': function () {
                        $(this).dialog('close');
                    }
                }
            });
        });
    },
    showFilesControls: function(){
        if(currentCourseSection=="course_lesson_edit")
        {
            $(".material-controls").hide();
            $(".add-to-lesson-controls").show();
        }
        else
        {
            $(".add-to-lesson-controls").hide();
            $(".material-controls").show();
        }
    },
    showCourses: function () {
        $(".courses-section").hide();
        $.ajax({
            url: "/service/get_courses",
            async: true,
            success: function (html) {
                $(".courses-section").replaceWith(html);
                currentCourseSection = "courses_list";
                courses.bindCoursesControls();
                $(".courses-section").show();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
        $(".courses-list-section").show();
    },
    showEditCourse: function (courseId) {
        $(".courses-section").hide();
        var params = "";
        if (courseId != null) {
            params += "course_id=" + courseId;
        }
        $.ajax({
            url: "/service/get_course?" + params,
            async: true,
            success: function (html) {
                $(".courses-section").replaceWith(html);
                $(".courses-section").show();
                alert("!");
                $(".numeric").autoNumeric('init', {mDec: 2, aSep: '', vMax: '999999.00', vMin: '0.00'});
                currentCourseSection = "courses_edit";
                courses.bindCoursesControls();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    },
    showCourseLessons: function (courseId) {
        $(".courses-section").hide();
        $.ajax({
            url: "/service/get_course_lessons?course_id=" + courseId,
            async: true,
            success: function (html) {
                $(".courses-section").replaceWith(html);
                $(".courses-section").show();
                currentCourseSection = "course_lesson_list";
                courses.bindCoursesControls();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    },
    showEditLessons: function (courseId, lessonId, main_material) {
        $(".courses-section").hide();
        var params = "course_id=" + courseId;
        if (lessonId != null) {
            params += "&lesson_id=" + lessonId;
        }
        if (main_material != null) {
            params += "&main_material=" + main_material;
        }
        $.ajax({
            url: "/service/get_lesson?" + params,
            async: true,
            success: function (html) {
                $(".courses-section").replaceWith(html);
                $(".courses-section").show();
                currentCourseSection = "course_lesson_edit";
                courses.bindCoursesControls();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    }
}

function fileProgressBar(show) {
    if (show) {
        $(".upload-progress-link").show();
        $(".library-loader").show();
    }
    else {
        $(".upload-progress-description").html(" Загрузка файлов 100%");
        $(".upload-progress-link").delay(500).hide(0);
    }
}