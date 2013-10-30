$(document).ready(function(){
    numeric(".good-count-input");
    $(".good-count-input").keyup(function(){
        var good_id = $(this).attr("good-id");
        var count = $(this).val();
        if(count==""||count==null||count=="0")
        {
            count = 1;
            $(this).val("1");
        }
        $.ajax({
                url:"/Корзина/Добавить?good_id="+good_id+"&count="+count,
                context: document.body,
                async: true,
                success:function(data){
                    // Rerun basket...
                    populateBasket(data);
                    blink(null);
                },
                failure:function(data){
                }
            }
        )
    });
    $(".delete-good").click(function(){
       var good_id = $(this).attr("good-id");
        $.ajax({
                url:"/Корзина/Добавить?good_id="+good_id+"&count=0",
                context: document.body,
                async: true,
                success:function(data){
                    // Rerun basket...
                    populateBasket(data);
                    blink(null);
                },
                failure:function(data){
                }
            }
        )
    });
});
function populateBasket(data)
{
    $("goods good",data).each(function(){
        var good_id = $(this).find("id").text();
        if($(".itemRow[good-id=\""+good_id+"\"]").size()==0)// New good
        {
            window.location = "/Корзина/";
        }
        var total_sell_price = $(this).find("total_sell_price").text();
        $(".itemRow[good-id=\""+good_id+"\"]").find(".totalPrice").html(total_sell_price+" руб.");
    });
    $(".itemRow").each(function(){
        var good_id = $(this).attr("good-id");
        if($("goods good",data).filter(function()
        {
            return $(this).find("id").text()==good_id&&$(this).find("count").text()*1>0;
        }).size()==0)// No such good
        {
            $(this).remove();
        }
    });
    var orderTotalPrice = $("totalGoodPrice",data).text();
    $("#orderTotalSum").html(orderTotalPrice+" руб.");
    $("#count").html($("totalCount",data).text());
}
var blinkDone = true;
function blink(button)
{
    if(blinkDone)
    {
        blinkDone = false;
        $(".cart").fadeOut(300).fadeIn(300,function(){
            blinkDone = true;
        });
        $(button).fadeOut(300).fadeIn(300);
    }
}