$(document).ready(function(){
    $("#order-call").click(function(){
       orderCall();
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