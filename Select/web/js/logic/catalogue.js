$(document).ready(function(){
    $(".search").keypress(function(e){
       if(e.keyCode==13)
       {
           var key = $(".search").val();
           var url = "/Каталог?keywords="+encodeURI(key);
           window.location = url;
       }
    });
    $(".good-detail-link").click(function(){
        var _this = $(this);
        $.ajax({
                url:"/good?id="+$(this).attr("good-id"),
                context: document.body,
                async: true,
                success:function(data){
                    $("#detail").remove();
                    var threeCol = $(_this).closest(".threecolumn");
                    $(threeCol).after(data);
                    if($(_this).attr("index")=="0")
                    {
                        $("#detail").addClass("left");
                    }
                    if($(_this).attr("index")=="1")
                    {
                        $("#detail").addClass("center");
                    }
                    if($(_this).attr("index")=="2")
                    {
                        $("#detail").addClass("right");
                    }
                    $("#detail").show();
                    initDetailBox();
                },
                failure:function(data){
                }
            }
        )
    });
    $(".buy-button").click(function(){
        var good_id = $(this).attr("good-id");
        var _this = $(this);
        $.ajax({
                url:"/Корзина/Добавить?good_id="+good_id,
                context: document.body,
                async: true,
                success:function(data){
                    $("#count").html($("totalCount",data).text());
                    blink(_this);
                },
                failure:function(data){
                }
            }
        )
    });
    $("#brand-select").change(function(){
        var category = $(this).attr("category");
        var brand = $(this).find("option:selected").val();
        var url = "/Каталог/"+category+"?brand="+brand;
        window.location = url;
    });
});
function initDetailBox()
{
    $(".detail_bottom .buy-button").click(function(){
        var good_id = $(this).attr("good-id");
        var _this = $(this);
        $.ajax({
                url:"/Корзина/Добавить?good_id="+good_id,
                context: document.body,
                async: true,
                success:function(data){
                    $("#count").html($("totalCount",data).text());
                    blink(_this);
                },
                failure:function(data){
                }
            }
        )
    });
    $(".detail_images").click(function(){
        var img = $(this).attr("src");
        $(".detail-main-image").attr("src",img);
    });
    $(".close").click(function(){
        $("#detail").remove();
    });
}
function blink(button)
{
    $(".cart").fadeOut(300).fadeIn(300);
    $(button).fadeOut(300).fadeIn(300);
}
