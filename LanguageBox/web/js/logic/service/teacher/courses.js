var isCheckingUpload = true;
var checkInterval = 1000;
var uploadDelay = 0;

$(document).ready(function () {
    library.bindLibraryControls();
    library.bindCoursesControls();
    library.checkUploadFiles();

    courses.showCourses();
//    // Check uploading
//    $("#upload-progress-bar").progressbar();
});

library =
{
    bindLibraryControls: function () {
        // Close window
        //


        // Browse folders
        $(".entity-link[entity-type=\"folder\"]").unbind("click");
        $(".entity-link[entity-type=\"folder\"]").bind("click", function () {
            var folder_id = $(this).attr("folder-id");
            library.showFolder(folder_id);
        });

        // Search
        var search = function()
        {

            var category = $(".library-search-select option:selected").val();
            var text = $(".library-search-input").val();
            console.log("Search: "+category+"  :  "+text);
            $("tr[entity-type=\"material\"]").hide();
            $("tr[entity-type=\"material\"]").filter(function(){
                console.log($(this).attr("entity-category-type")+"!");
                console.log($(this).attr("entity-category-type")+"!!");
                console.log($(this).attr("entity-category-type")+"!!!");
                return ($(this).attr("entity-category-type")==category||category=="ALL")&&$(this).attr("entity-name").toLowerCase().indexOf(text.toLowerCase())!=-1;
            }).show();
        }
        $(".library-search-input").keyup(function(){
            search();
        });
        $(".library-search-select").change(function(){
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
        var check = function(){
            $.ajax({
                    url: "/service/upload_progress",
                    async: true,
                    dataType: "xml",
                    success: function (xml) {

                        if(isCheckingUpload)
                        {
                            var uploaded = $("root>progress",xml).text();
                            var isUploading = $("root>is_uploading",xml).text();

                            if(isUploading=="true")
                            {
                                uploadDelay = 0;
                                uploaded = uploaded/100.0;
                                fileProgressBar(true);
                                $(".upload-progress-description").html(" Загрузка файлов "+uploaded+"%");
                                $(window).on('beforeunload', function(){
                                    return 'Файлы в процессе закачки. Вы уверены, что хотите оборвать процесс?';
                                });
                            }
                            else
                            {
                                uploadDelay++;
                                showNewFiles();
                                if(uploadDelay>3)
                                {
                                    fileProgressBar(false);
                                    $(window).unbind('beforeunload');
                                }
                            }
                        }
                        setTimeout(check,checkInterval);
                    },
                failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                    $(".library-loader").show();
                }
            });
        };
        check();
        // For appearing new files
        var showNewFiles = function(){
            var folder_id =  $("#current-folder-id").val();
            var folderParam = "";
            if (folder_id != null)
            {
                folderParam = "?folder_id=" + folder_id;
            }
            $.ajax({
                url: "/service/get_folder" + folderParam,
                context: document.body,
                async: true,
                success: function (html) {

                    var wasUpdate = false;
                    $(".library_list-item[entity-type=\"material\"]",html).each(function(){
                        var material_id = $(this).attr("entity-id");
                        var new_li = $(this);
                        var found = $(".library_list-item[entity-type=\"material\"][entity-id=\""+material_id+"\"]");
                        if($(found).length==0)// New one
                        {
                            $(".folder-body").append($(this));
                            wasUpdate = true;
                        }
                    });
                    if(wasUpdate)
                    {
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
    bindCoursesControls: function(){
        // Add new course
        $(".add-course-button").unbind("click");
        $(".add-course-button").click(function(){
            courses.showEditCourse();
        });

        // Select course
        $(".edit-course-button").unbind("click");
        $(".edit-course-button").click(function(){
            var courseId = $(this).attr("course-id");
            courses.showEditCourse(courseId);
        });

        // Save course
        $(".add-course-preview-butt").unbind("click");
        $(".add-course-preview-butt").click(function(){
            // Send picture and return id of picture
            // Show picture
        });
        $(".remove-course-preview-butt").unbind("click");
        $(".remove-course-preview-butt").click(function(){
            $(".add-course-preview-butt").removeAttr("preview-id");
        });
        $(".save-course-butt").unbind("click");
        $(".save-course-butt").click(function(){

        });

    },
    showCourses: function(){
        $(".courses-section").hide();
        $.ajax({
            url: "/service/get_courses",
            async: true,
            success: function (html) {
                $(".courses-list-section").replaceWith(html);
                courses.bindCoursesControls();
                $(".courses-list-section").show();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
        $(".courses-list-section").show();
    },
    showEditCourse: function(courseId){
        $(".courses-section").hide();
        $.ajax({
            url: "/service/get_course?course_id="+courseId,
            async: true,
            success: function (html) {
                $(".course-edit-section").replaceWith(html);
                $(".course-edit-section").show();
                courses.bindCoursesControls();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    },
    showCourseLessons: function(courseId){
        $(".courses-section").hide();
        $.ajax({
            url: "/service/get_course_lessons?course_id="+courseId,
            async: true,
            success: function (html) {
                $(".course-lessons-section").replaceWith(html);
                $(".course-lessons-section").show();
                courses.bindCoursesControls();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    },
    showEditLessons: function(lessonId){
        $(".courses-section").hide();
        $.ajax({
            url: "/service/get_lesson?lesson_id="+lessonId,
            async: true,
            success: function (html) {
                $(".course-edit-section").replaceWith(html);
                $(".course-edit-section").show();
                courses.bindCoursesControls();
            },
            failure: function () {
//                    alert("Серверная ошибка. Попробуйте позже");
                $(".library-loader").show();
            }
        });
    }
}

function fileProgressBar(show)
{
    if(show)
    {
        $(".upload-progress-link").show();
        $(".library-loader").show();
    }
    else
    {
        $(".upload-progress-description").html(" Загрузка файлов 100%");
        $(".upload-progress-link").delay(500).hide(0);
    }
}
