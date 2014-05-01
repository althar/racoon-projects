$(document).ready(function(){
    // Init address
    $(".address-select").click(function(){
        var areaId = $(this).attr("area_id");
        $("input[area_id=\""+areaId+"\"]").attr("checked","checked");
    });
    //Init delivery
    $("input[name=\"delivery_group_id\"]").click(function(){
        $("#delivery_price").html($(this).attr("delivery-price"));
        $("input[name=\"delivery_price\"]").val($(this).attr("delivery-price"));
    });
    $("input[name=\"delivery_group_id\"]").eq(0).click();
});
