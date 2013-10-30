$(document).ready(function(){
    initChooseFriend();
});

function initChooseFriend()
{
    $(".friend-item").click(function(){
        $(".friend-item").find("input[type='checkbox']").not($(this).find("input[type='checkbox']")).removeAttr("checked");
        //$(this).find("input[type='checkbox']").attr("checked","checked");
        $(".friend-item").removeClass("selected");
        $(this).addClass("selected");
    });
    $("#present-butt").click(function(){
        if($(".selected").length>0)
        {
            var friend_id = $(".selected").find(".friend-id").val();
            var id_price = $("#ids").val();
            var reason = $("#reason").val();
            var date = $("#date").val();
            var delivery_summ = $("#delivery_summ").val();
            var delivery_region_id = $("#delivery_region_id").val();
            var delivery_area_id = $("#delivery_area_id").val();
            var delivery_group_id = $("#delivery_group_id").val();
            var delivery_variant_id = $("#delivery_variant_id").val();
            window.location = "/user/make_friend_wish?friend_facebook_id="+friend_id+"&id_price="+id_price+"&reason="+reason+"&date="+date
            +"&delivery_summ="+delivery_summ
                +"&delivery_region_id="+delivery_region_id
                +"&delivery_area_id="+delivery_area_id
                +"&delivery_group_id="+delivery_group_id
                +"&delivery_variant_id="+delivery_variant_id;
        }
        else
        {
            alert("Выбери друга");
        }
    });
}