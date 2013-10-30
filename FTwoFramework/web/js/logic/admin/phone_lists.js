$(document).ready(function()
{
    $("body").hide();
    $(".search").hide();
    init();
    initUpload();
    $("body").show();
});

function loadLists()
{
    var url_string = base_app_url+"/Admin?cmd=get_lists";

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $("root > code",xml).text();
            //alert("auth code: "+code);
            if(code=="100000")// success
            {
                var index = 1;
                var current_item = null;
                var black_html = "";
                var white_html = "";
                while((current_item=$("root > phone_list > item_"+index,xml)).text()!="")
                {
                    var is_black = $("is_black",current_item).text();
                    var phone = $("phone",current_item).text();
                    if(is_black=="true")
                    {
                        black_html+="<tr><td>"+phone+"</td></tr>";
                    }
                    else
                    {
                        white_html+="<tr><td>"+phone+"</td></tr>";
                    }
                    index ++;
                }
                $("#black_list").html(black_html);
                $("#white_list").html(white_html);
            }
            else
            {
                alert("Server error");
            }
        }
    });
}
function initUpload()
{
    $("#butt_white_submit").click(function(){
        var formData = new FormData($("#white_form")[0]);

        var url_string = base_app_url+"/UploadServlet";
        alert(url_string);
        $.ajax({
            async:false,
            cache:false,
            type: "POST",
            url: url_string,
            contentType: false,
            processData: false,
            data: formData,
            success: function(xml){
                alert("success");
                var code = $("root > code",xml).text();
                //alert("auth code: "+code);
                if(code=="100000")// success
                {
                    alert($("root",xml).text());
                }
                else
                {
                    customAlert("Не удалось добавить продукт в корзину",4000);
                }
            }
        });
    });

}
