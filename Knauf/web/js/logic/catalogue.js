$(document).ready(function(){
    initAddGood();
    $("#more_products").click(function(){
        $("#more_products").html("Идет загрузка...");
        var catalogue = $(this).attr("catalogue");
        var catalogue_id = $(this).attr("catalogue-id");
        var page = $(this).attr("page")*1 + 1;
        $.ajax({
            url: "/service/more_goods?catalogue="+catalogue+"&catalogue_id="+catalogue_id+"&page="+page,
            context: document.body,
            async: true,
            success: function (html)
            {
                $("#catalog_items").append(html);
                $(".cart-add").unbind("click");
                initAddGood();
                $("#more_products").attr("page",page);
                $("#more_products").html("Еще");
            },
            failure: function()
            {

            }
        });
    });
});

function initAddGood()
{
    $(".cart-add").click(function(){
        $(this).find(".add-good-title").hide();
        $(this).find(".add-good-loader").show();
        var buttAdd = $(this);
        $.ajax({
            url: "/service/add_good?good_id="+$(this).attr("good-id")+"&count=2",
            context: document.body,
            async: true,
            success: function (html)
            {
                if(html=="need_registration")
                {
                    window.location = "/registration";
                }
                if(html=="ok")
                {
                    $(buttAdd).find(".add-good-loader").hide();
                    $(buttAdd).find(".add-good-title").show();
                    $(buttAdd).find(".icon-ok").css("display","inline-block");
                    $(buttAdd).find(".icon-ok").show();
                    getBasket();
                }
            },
            failure: function()
            {

            }
        });
    });
}
function getBasket()
{
    $.ajax({
        url: "/service/get_basket",
        context: document.body,
        async: true,
        success: function (html)
        {
            $(".basket").replaceWith(html);
        },
        failure: function()
        {

        }
    });
}
