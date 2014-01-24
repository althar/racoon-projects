var fileCount = 0;

$(document).ready(function () {
    library.bindFolderControls();
});
function uploadProcess(e) {
    alert(e);
}
library =
{
    showFolder: function (folder_id) {
        //1. Hide all other library sections
        $(".library-section").hide();
        //2. Show loader
        $(".library-loader").show();
        //3. Request folder-body, and insert it
        var folderParam = "";
        if (folder_id != null) {
            folderParam = "?folder_id=" + folder_id;
        }
        $.ajax({
            url: "/service/teacher/get_folder" + folderParam,
            context: document.body,
            async: true,
            success: function (html) {
                $("#folder").replaceWith(html);
                library.bindFolderControls();
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
        //3. Show add file form
        $("#add-file").show();
    },
    bindFolderControls: function () {
        // Browse folders
        $(".entity-link[entity-type=\"folder\"]").unbind("click");
        $(".entity-link[entity-type=\"folder\"]").bind("click", function () {
            var folder_id = $(this).attr("folder-id");
            library.showFolder(folder_id);
        });

        // Add folder or material
        $('.dropdown').dropit({
            triggerParentEl: '.dropdown_inner'
        });
        $(".add-folder-link").unbind("click");
        $(".add-folder-link").bind("click", function () {
            library.createFolder();
        })
        $(".new-folder-name").keypress(function (e) {
            var name = $(this).val();
            var folder_id = $("#current-folder-id").val();
            if (e.which == 13 && name.length > 0) {
                $.ajax({
                    url: "/service/teacher/create_folder?folder_name=" + name + "&folder_id=" + folder_id,
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
                            url: "/service/teacher/delete?type=" + type + "&id=" + id,
                            context: document.body,
                            async: true,
                            success: function (html) {
                                $("li[entity-type=\"" + type + "\"][entity-id=\"" + id + "\"]").hide();
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
        $(".entity-input").keypress(function (e) {
            var name = $(this).val();
            if (e.which == 13 && name.length > 0) {
                var type = $(this).attr("entity-type");
                var id = $(this).attr("entity-id");

                $(".library-loader").show();
                $.ajax({
                    url: "/service/teacher/rename?id=" + id + "&type=" + type + "&name=" + name,
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
        library.bindAddFileChange();
        $(".cancel-add-file").unbind('click');
        $(".cancel-add-file").click(function () {
            var currentFolderId = $("#current-folder-id").val();
            library.showFolder(currentFolderId);
        });
        $(".accept-add-file").click(function () {
            var currFolderId = $("#current-folder-id").val();
            $("#form-folder-id").val(currFolderId);
            var formData = new FormData($("#add-file-form")[0]);
            $(".library-loader").show();
            $.ajax({
                url: "/service/teacher/upload_files",  //Server script to process data
                type: 'POST',
                success: function () {
                    alert("done upload");
                    $(".library-loader").hide();
                },
                error: function () {
                    alert("error upload");
                    $(".library-loader").hide();
                },
                uploadProgress: function(evt) {
                    if (evt.lengthComputable) {
                        document.title = "Loaded " + parseInt( (evt.loaded / evt.total * 100), 10) + "%";
                    }
                    else {
                        console.log("Length not computable.");
                    }
                },

                // Form data
                data: formData,
                //Options to tell jQuery not to process data or worry about content-type.
                cache: false,
                contentType: false,
                processData: false
            });
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
            $("#add-file-form").prepend("<input type='file' class='add-file-input' name='files' multiple>");
            $(this).hide();

            library.bindAddFileChange();
        });
    },
    createFolder: function () {
        //1. Clear new folder control
        $(".new-folder-name").val("Новая папка");

        //2. Show new folder control
        $(".new-folder").show();
        $(".new-folder-name").select();


    }
}