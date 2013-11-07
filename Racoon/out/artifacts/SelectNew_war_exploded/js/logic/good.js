$(document).ready(function(){
    $("#order-call").click(function(){
       orderCall();
    });
    $("#order-button").click(function(){
        order();
    });
});
function orderCall()
{
    var phone = $("#phone-input").val();
    $.ajax({
            url:"/order_call?phone="+phone,
            context: document.body,
            async: true,
            success:function(data){
                $("#order-call-info").fadeIn(400).delay(4000).fadeOut(400);
            },
            failure:function(data){
                alert("Fail");
            }
        }
    )
}
function order()
{
    var id = $("#order-button").attr("good_id");
    $.ajax({
            url:"/cart/add?good_id="+id+"&count=1",
            context: document.body,
            async: true,
            success:function(data){
                $("#order-button").fadeOut(300).fadeIn(400,function(){
                    window.location="/cart/";
                });
            },
            failure:function(data){
                alert("Fail");
            }
        }
    )
}