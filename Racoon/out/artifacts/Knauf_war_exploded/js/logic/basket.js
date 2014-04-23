$(document).ready(function(){

    init();
});
function init()
{
    $(".cart-delete").click(function(){
        var good_id = $(this).attr("good-id");
        $.ajax({
            url: "/service/remove_good?good_id="+good_id,
            context: document.body,
            async: true,
            success: function (html)
            {
                $("#basket-inner").html(html);
                init();
            },
            failure: function()
            {

            }
        });
    });
    $(".good-quantity").change(function(){
        var good_id = $(this).attr("good-id");
        var good_count = $(this).val();
        $.ajax({
            url: "/service/change_good?good_id="+good_id+"&count="+good_count,
            context: document.body,
            async: true,
            success: function (html)
            {
                $("#basket-inner").html(html);
                init();
            },
            failure: function()
            {

            }
        });
    });
}