$(document).ready(function(){
    // Init address
    $(".address-select").click(function(){
        var areaId = $(this).attr("area-id");
        $("input[area-id=\""+areaId+"\"]").attr("checked","checked");
    });
    //Init delivery
    $("input[name=delivery-id]").click(function(){
        var deliveryId = $(this).val();
        $(".delivery-select").attr("disabled","disabled");
        $(".delivery-select[delivery-id=\""+deliveryId+"\"]").removeAttr("disabled");
    });
//    $(".delivery-select").click(function(){
//        $(this).removeAttr("")
//        var deliveryId = $(this).attr("delivery-id");
//        $("input[delivery-id=\""+deliveryId+"\"]").attr("checked","checked");
//    });

});
