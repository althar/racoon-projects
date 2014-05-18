$(document).ready(function(){
    initAddGood();
    $("#more_products").click(function(){
        $("#more_products").html("Идет загрузка...");
        var url = "/service/more_goods?";
        var catalogue = $(this).attr("catalogue");
        var catalogue_id = $(this).attr("catalogue-id");
        var search = $(this).attr("search");
        var page = $(this).attr("page")*1 + 1;
        if(catalogue!=null)
        {
            url+="&catalogue="+catalogue;
        }
        if(catalogue_id!=null)
        {
            url+="&catalogue_id="+catalogue_id;
        }
        if(search!=null)
        {
            url+="&search="+search;
        }
        if(page!=null)
        {
            url+="&page="+page;
        }
        $.ajax({
            url: url,
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
            url: "/service/add_good?good_id="+$(this).attr("good-id")+"&count=1",
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
                    var buttHtml = $(buttAdd).find(".add-good-title").html();
                    buttHtml = buttHtml.replace('в корзину','в корзине');
                    $(buttAdd).find(".add-good-title").html(buttHtml);
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
