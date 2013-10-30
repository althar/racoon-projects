$(document).ready(function () {
    $("#new-book").click(function () {
        newBook();
    });
    $("#save-book").click(function () {
        saveBook();
    });
    $("#save-chapter").click(function () {
        saveChapter();
    });
    $("#new-book").click();
});
function getBooks() {
    alert("get books");
    $.ajax(
        {
            url: '/admin/library/get_books',
            method: 'GET',
            success: function (data) {
                $("#books").html(data);
                $("#books-select").change(function () {
                    var id = $(this).find("option:selected").attr("book-id");
                    var name = $(this).find("option:selected").val();
                    var author = $(this).find("option:selected").attr("author");
                    var type = $(this).find("option:selected").attr("type");
                    var genre = $(this).find("option:selected").attr("genre");
                    $("#book-name").val(name);
                    $("#book-type").val(type);
                    $("#book-genre").val(genre);
                    $("#book-author").val(author);
                    $("#book-id").val(id);
                    alert("got_books_ready");
                    document.getElementById("SocketFileUploader").initialize("Add","*.txt","book_id="+$("#book-id").val());
                    alert("got_books");
                });
            }
        }
    );
}
function saveBook() {
    var name = $("#book-name").val();
    var type = $("#book-type option:selected").val();
    var genre = $("#book-genre option:selected").val();
    var author = $("#book-author").val();
    var id = $("#book-id").val();
    if (id == null || id == "") {
        id = 0;
    }
    if (author == null) {
        author = "";
    }
    $.ajax(
        {
            url: '/admin/library/save_book?name=' + name + "&type=" + type + "&genre=" + genre + "&id=" + id + "&author=" + author,
            method: 'GET',
            success: function (data) {
                var book_id = data;
                $("#book-id").val(data);
                $("#chapter-fields").show();
                $("#chapter").show();
                $("#chapter-name").val("Название главы");
                $("#chapter-name").focus();
                $("#chapter-name").select();
                $("#chapter-keywords").val("");
                $("#text").html("");
                getBooks();
                disableBook(true);
                document.getElementById("SocketFileUploader").initialize("Add","*.txt","book_id="+$("#book-id").val());
            }
        }
    );
}
function newBook() {
    $("#chapter-fields").hide();
    $("#chapter").hide();
    $("#book-id").val(null);
    $("#book-name").val("Имя книги");
    $("#book-author").val("");
    $("#book-name").focus();
    $("#book-name").select();
    getBooks();
    disableBook(false);
}
function saveChapter() {
    disableBook(true);
    var name = $("#chapter-name").val();
    var keywords = $("#chapter-keywords").val();
    var text = $("#text").val();
    var id = $("#book-id").val();
    var book_name = $("#book-name").val();
    if (id == null || id == "") {
        id = 0;
    }
    var params = {name: name, keywords: keywords, text: text, book_id: id,book_name:book_name};
    $("#loader").show();
    disablePage(true);
    $.ajax(
        {
            url: '/admin/library/save_chapter',
            method: 'POST',
            async: true,
            data: params,
            success: function (data) {
                $("#text").val("");
                $("#chapter-keywords").val("");
                $("#chapter-name").val("Название главы");
                $("#chapter-name").focus();
                $("#chapter-name").select();
                $("#loader").hide();
                $("#chapter-fields").hide();
                $("#chapter").hide();
                $("#chapter-images").html(data);
                $("#chapter-images").show();
                $("#chapter-images").append("<input type=\"button\" value=\"Сохранить картинки\" id=\"save-images\">");
                $("#save-images").click(function(){
                   saveChapterImages();
                });
                disablePage(false);

            }
        }
    );
}
function saveChapterImages() {
    $("#chapter-images .image-preview").filter(function () {
          return $(this).find(".image-checkbox").is(':checked');
    }).each(function () {
            var unescaped_url = $(this).find(".image").attr("src");
            var content_no_formatting = $(this).find(".content-no-formatting").val();
            var tb_url = $(this).find(".tb-url").val();
            var title_no_formatting = $(this).find(".title-no-formatting").val();
            var width = $(this).find(".width").val();
            var height = $(this).find(".height").val();
            var keyword = $(this).find(".keyword").val();

            var params = {unescaped_url: unescaped_url
                , content_no_formatting: content_no_formatting
                , tb_url: tb_url
                , title_no_formatting: title_no_formatting
                , width:width
                , height:height
                , keyword:keyword};
            $.ajax(
                {
                    url: '/admin/library/save_chapter_image',
                    method: 'POST',
                    async: false,
                    data: params,
                    success: function (data) {
                        //<input type="button" value="Сохранить картинки" id="save-images" class="hidden">
                        $("#text").val("");
                        alert("!");
                        $("#chapter-keywords").val("");
                        $("#chapter-name").val("Название главы");
                        $("#chapter-name").focus();
                        $("#chapter-name").select();
                        $("#loader").hide();
                        $("#chapter-fields").hide();
                        $("#chapter").hide();
                        $("#chapter-images").show();

                        $("#chapter-images").html(data);
                        disablePage(false);
                        $("#save-images").show();
                    }
                }
            );
        });
    $("#save-images").remove();
    $("#chapter-fields").show();
    $("#chapter").show();
    $("#chapter-images").hide();

}
function disableBook(disable) {
    if (disable) {
        $(".book-fields input").attr("disabled", "true");
        $(".book-fields select").attr("disabled", "true");
        $("#new-book").removeAttr("disabled");
        return;
    }
    $(".book-fields input").removeAttr("disabled");
    $(".book-fields select").removeAttr("disabled");
    $("#new-book").attr("disabled","true");
    alert("shown");
}

function disablePage(disable) {
    if (disable) {
        $("body input").attr("disabled", "true");
        $("body textarea").attr("disabled", "true");
        $("body select").attr("disabled", "true");
    }
    else {
        $("body input").removeAttr("disabled");
        $("body textarea").removeAttr("disabled");
        $("body select").removeAttr("disabled");
    }
}