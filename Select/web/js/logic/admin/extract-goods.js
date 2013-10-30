$(document).ready(function () {
    $("#extract-goods").click(function () {
        var brand = $("select option:selected").html();
        var xml = "";
        $("#memo").html("");
        $.ajax({
            url: "/xml/" + brand + ".xml",
            context: document.body,
            dataType: "xml",
            async: false,
            success: function (data) {
                xml = data;
            },
            failure: function (data) {
                alert("fail");
            }
        });
        $("Товары>Товар", xml).each(function () {
            var article = $(this).find("Артикул").text();
            setTimeout(function () {
                $("body").append("|");
            }, 1000);
            deleteCharacteristics(brand, article);
            $("Картинка", this).each(function () {
                var url = "img/goods/" + brand + "/" + $(this).text().replace("\\", "/");
                //alert(url);
                addImageUrl(brand, article, url);
            });
            var charDiv = $("Описание", this).text();
            //alert(charDiv);
            $("tr", charDiv).each(function () {
                var name = $(".feature_name", this).text();
                var value = $(".feature_value", this).text();
                if (name != null && name != "") {
                    extractCharacteristics(brand, article, name, value);
                }
            });
            $("#memo").append(brand + ": " + article + "<br>");
        });
    });
});

function extractCharacteristics(brand, article, name, value) {
    $.ajax({
        url: "/admin/goods_extract?cmd=extract_characteristics&brand=" + brand + "&article=" + article + "&name=" + name + "&value=" + value,
        context: document.body,
        dataType: "xml",
        async: false,
        success: function (data) {
            return true;
        },
        failure: function (data) {
            return false;
        }
    });
}
function deleteCharacteristics(brand, article) {
    $.ajax({
        url: "/admin/goods_extract?cmd=delete_characteristics&brand=" + brand + "&article=" + article,
        context: document.body,
        dataType: "xml",
        async: false,
        success: function (data) {
            $("#memo").append(".");
            return true;
        },
        failure: function (data) {
            return false;
        }
    });
}
function addImageUrl(brand, article, url) {
    $.ajax({
        url: "/admin/goods_extract?cmd=add_image&brand=" + brand + "&article=" + article + "&url=" + url,
        context: document.body,
        dataType: "xml",
        async: false,
        success: function (data) {
            $("#memo").append("!");
            return true;
        },
        failure: function (data) {
            return false;
        }
    });
}