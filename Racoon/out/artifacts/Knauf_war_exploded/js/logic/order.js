$(document).ready(function(){
    // Init address
    $(".address-select").click(function(){
        var areaId = $(this).attr("area_id");
        $("input[area_id=\""+areaId+"\"]").attr("checked","checked");
    });
    //Init delivery
    $("input[name=delivery_id]").click(function(){
        var deliveryId = $(this).val();
        $(".delivery-select").attr("disabled","disabled");
        $(".delivery-select[delivery_id=\""+deliveryId+"\"]").removeAttr("disabled");
    });
//    $(".delivery-select").click(function(){
//        $(this).removeAttr("")
//        var deliveryId = $(this).attr("delivery_id");
//        $("input[delivery_id=\""+deliveryId+"\"]").attr("checked","checked");
//    });

});
