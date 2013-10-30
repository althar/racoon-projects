$(document).ready(function(){
    initCatalogue();
});
var currentPage = 0;
var currentSize = 30;
var currentSubcategoryId;
function initCatalogue()
{
    $(".category>li").click(function(){
        $("#category").html($(this).find("a").html());
        $("#navigation")
        $(".category>li>ul").hide();
        $(this).find("ul").show();
    });
    $(".category>li>ul>li").click(function(){
        var subcategory_html="<li>"+$(this).find("a").attr("subcategory_name")+"</li>";
        $("#navigation").html(subcategory_html);
        currentSubcategoryId = $(this).find("a").attr("subcategory_id");
        loadGoods(currentSubcategoryId);
    });
    $("#more_butt").click(function(){
        loadMoreGoods(currentSubcategoryId);
    });

    $(".category>li>ul>li").first().click();
}
var canLoadGoods = true;
function loadMoreGoods(categoryId)
{
    $("#more-loader").show();
    $.ajax({
        url: "/user/get_goods?category_id="+categoryId+"&page="+currentPage+"&size="+currentSize,
        context: document.body,
        async: true,
        success: function (html)
        {
            currentPage+=1;
            $("#catalogue_body").append(html);
            $("#loader").hide();
            $(".category>li a").css("cursor","pointer");
            $(".category>li>ul>li a").css("cursor","pointer");
            $(".category").fadeTo("fast",1,function(){
                canLoadGoods = true;
            });
            $("#more-loader").hide();
        },
        failure: function()
        {
            alert("Серверная ошибка. Попробуйте позже");
            $("#loader").hide();
            $(".category>li a").css("cursor","pointer");
            $(".category>li>ul>li a").css("cursor","pointer");
            $(".category").fadeTo("fast",1,function(){
                canLoadGoods = true;
            });
            $("#more-loader").hide();
            $("#more_butt").hide();
        }
    });
}
function loadGoods(categoryId)
{
    $("#loader").show();
    $(".category").fadeTo("fast",0.2);
    $(".category>li a").css("cursor","default");
    $(".category>li>ul>li a").css("cursor","default");
    canLoadGoods = false;
    $.ajax({
        url: "/user/get_goods?category_id="+categoryId+"&page=0&size="+currentSize,
        context: document.body,
        async: true,
        success: function (html)
        {
            currentPage = 0;
            $("#catalogue_body").html(html);
            $("#loader").hide();
            $(".category>li a").css("cursor","pointer");
            $(".category>li>ul>li a").css("cursor","pointer");
            $(".category").fadeTo("fast",1,function(){
                canLoadGoods = true;
            });
        },
        failure: function()
        {
            alert("Серверная ошибка. Попробуйте позже");
            $("#loader").hide();
            $(".category>li a").css("cursor","pointer");
            $(".category>li>ul>li a").css("cursor","pointer");
            $(".category").fadeTo("fast",1,function(){
                canLoadGoods = true;
            });
        }
    });
}
function chooseGifts(to_friend)
{
    var ids_prices = "";
    $(".product_block").filter(function()
    {
        return $(this).find("input[type=\"checkbox\"]:checked").length==1;
    }).each(function(){
            ids_prices+=$(this).attr("good_id")+"_"+$(this).attr("good_price")+"_"+$(this).attr("good_name").replace("\\_","")+"|";
    });
    window.location = "/user/wish_goods?id_price="+ids_prices+"&to_friend="+to_friend;
}
