$(document).ready(function(){
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
});

function blink(button)
{
    $(".cart").fadeOut(300).fadeIn(300);
    $(button).fadeOut(300).fadeIn(300);
}