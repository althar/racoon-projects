$(document).ready(function(){
    loadRegions();
    initOrderInfo();
    initMakeOrder();
});
var regionMap = null;

function loadRegions() {
    var guid = $("#guid").val();
    $("#loader").show();
    $(".button-bar").hide();
    $.ajax({
        url: "/user/get_regions?guid=" + guid,
        context: document.body,
        async: true,
        dataTypeString: "xml",
        success: function (xml) {
            regionMap = xml;
            if ($("Status", regionMap).first().text() == "2") {
                $("#region").html("");
                $("AreaGroupCollection > item", regionMap).each(function () {
                    var name = $(this).find("Name").first().text();
                    var id = $(this).find("Id").first().text();
                    if(id!="4")
                    {
                        $("#region").append('<option region-id=' + id + '>' + name + '</option>');
                    }
                });
                $("#region").change(function () {
                    // Load subRegions
                    $("#subregion").html("");
                    var region = $("#region option:selected").html();
                    $("AreaGroupCollection>item", regionMap).filter(function () {
                        return $("name", this).first().html() == region;
                    }).find("AreaAddressCollection>AddressCollection>item").each(function () {
                            var name = $("Name", this).text();
                            var areaId = $("AreaId", this).text();
                            if(areaId!="0")
                            {
                                $("#subregion").append('<option subregion-id=' + areaId + '>' + name + '</option>');
                            }
                        });

                    $("#subregion").unbind('change');
                    $("#subregion").change(function () {
                        getDeliveryPrice($("#subregion option:selected").attr("subregion-id"), $("guid", regionMap).text());
                    });
                    $("#subregion").change();
                });
                $("#region").change();
            }
            else {
                alert("Серверная ошибка. Попробуйте позже.");
            }

        },
        failure: function () {
            $("#loader").hide();
            $(".button-bar").show();
            alert("Серверная ошибка. Попробуйте позже");
        }
    });
}
var deliveryMap = null;
function initDeliveryChoose()
{
    $("#delivery-group").unbind("change");
    $("#delivery-group").change(function(){
        var html = "";
        var groupId = $(this).find("option:selected").attr("delivery-group-id");
        $("DeliveryModel>DeliveryGroups>item",deliveryMap).filter(function(){
            return $(this).find("DeliveryGroupID").text()==groupId;
        }).find("Delivery>item").each(function(){
                html +="<option ";
                html +="delivery-name=\""+$(this).find("Name").first().text()+"\"";
                html +=" delivery-variant-id=\""+$(this).find("DeliveryVariantID").first().text()+"\"";
                html +=" full-order-summ=\""+$(this).find("FullOrderSumm").first().text()+"\"";
                html +=" item-summ=\""+$(this).find("ItemSumm").first().text()+"\"";
                html +=" delivery-summ=\""+$(this).find("DeliverySumm").first().text()+"\"";
                html +=">";
                html +=$(this).find("Name").first().text();
                html +="</option>";
            });
        $("#delivery-variant").html(html);
        $("#loader").hide();
        $(".button-bar").show();
        $(".button-bar").removeClass("hidden");

        $("#delivery-variant").unbind("change");
        $("#delivery-variant").change(function(){
            var deliveryPrice = $(this).find("option:selected").attr("delivery-summ");
            var fullOrder = $(this).find("option:selected").attr("full-order-summ");
            var itemSumm = $(this).find("option:selected").attr("item-summ");
            $("#good-price").html(itemSumm+" рублей");
            $("#delivery-price").html(deliveryPrice+" рублей");
            $("#total-price").html(fullOrder+" рублей");
            var amount = $("#user_amount").val();
            var delta = fullOrder*1-amount*1;
            //alert(delta);
            if(delta*1>0)
            {
                $("#make_order").html("Оформить (доплатить "+(delta)+" рублей.");
            }
        });
        $("#delivery-variant option:first").attr("selected",true);
        $("#delivery-variant").change();
    });


}
function getDeliveryPrice(subregionId, guid) {
    //alert(subregionId + "   " + guid);
    $(".button-bar").hide();
    $("#loader").show();
    $.ajax({
        url: "/user/delivery_choose?area_id=" + subregionId+"&guid="+guid,
        context: document.body,
        async: true,
        dataTypeString: "xml",
        success: function (xml) {
            deliveryMap = xml;
            if ($("Status", deliveryMap).first().text() == "2") {
                var html = "";
                $("DeliveryModel>DeliveryGroups>item",deliveryMap).each(function(){
                    if($(this).find("DeliveryGroupID").first().text()!="3")
                    {
                        html +="<option ";
                        html +="delivery-group-name=\""+$(this).find("DeliveryGroupName").first().text()+"\"";
                        html +="delivery-group-id=\""+$(this).find("DeliveryGroupID").first().text()+"\"";
                        html +=">";
                        html +=$(this).find("DeliveryGroupName").first().text();
                        html +="</option>";
                    }
                });
                $("#delivery-group").html(html);
                initDeliveryChoose();
                $("#delivery-group").change();
            }
            else
            {
                alert("Серверная ошибка. Попробуйте позже");
            }
        },
        failure: function () {
            $("#loader").hide();
            alert("Серверная ошибка. Попробуйте позже");
        }
    });
}
function initOrderInfo()
{
    $("#delivery-variant").change(function(){
        var param_string = "guid="+$("#guid").val();
        param_string += "&area_id="+$("#subregion option:selected").attr("subregion-id");
        param_string += "&delivery_variant_id="+$("#delivery-variant option:selected").attr("delivery-variant-id");
        param_string += "&zip="+$("#zip").val();
        param_string += "&address="+($("#street").val()+$("#house").val()+$("#room").val());
        param_string += "&country="+$("#country").val();
        param_string += "&district="+$("#district").val();
        param_string += "&region="+$("#reg").val();
        param_string += "&city="+$("#city").val();
        param_string += "&first_name="+$("#name").val();
        param_string += "&middle_name="+$("#soname").val();
        param_string += "&phone="+$("#phone").val();
        $.ajax({
            url: "/user/order_info?" + param_string,
            context: document.body,
            async: true,
            dataTypeString: "xml",
            success: function (xml) {
                regionMap = xml;
                if ($("Status", regionMap).first().text() == "2") {
                    $("#region").html("");
                    $("AreaGroupCollection > item", regionMap).each(function () {
                        var name = $(this).find("Name").first().text();
                        var id = $(this).find("Id").first().text();
                        $("#region").append('<option region-id=' + id + '>' + name + '</option>');
                    });
                    $("#region").change(function () {
                        // Load subRegions
                        $("#subregion").html("");
                        var region = $("#region option:selected").html();
                        $("AreaGroupCollection>item", regionMap).filter(function () {
                            return $("name", this).first().html() == region;
                        }).find("AreaAddressCollection>AddressCollection>item").each(function () {
                                var name = $("Name", this).text();
                                var areaId = $("AreaId", this).text();
                                $("#subregion").append('<option subregion-id=' + areaId + '>' + name + '</option>');
                            });

                        $("#subregion").unbind('change');
                        $("#subregion").change(function () {
                            getDeliveryPrice($("#subregion option:selected").attr("subregion-id"), $("guid", regionMap).text());
                        });
                        $("#subregion").change();
                    });
                    $("#region").change();
                }
                else {
                    alert("Серверная ошибка. Попробуйте позже.");
                }

            },
            failure: function () {
                $("#loader").hide();
                $(".button-bar").show();
                alert("Серверная ошибка. Попробуйте позже");
            }
        });
    });
}
function initMakeOrder()
{
    $("#make_order").click(function(){
        var param_string = "guid="+$("#guid").val();
        param_string += "&wish_id="+$("#wish_id").val();
        param_string += "&area_id="+$("#subregion option:selected").attr("subregion-id");
        param_string += "&delivery_variant_id="+$("#delivery-variant option:selected").attr("delivery-variant-id");
        param_string += "&zip="+$("#zip").val();
        param_string += "&address="+($("#street").val()+$("#house").val()+$("#room").val());
        param_string += "&country="+$("#country").val();
        param_string += "&district="+$("#district").val();
        param_string += "&region="+$("#reg").val();
        param_string += "&city="+$("#city").val();
        param_string += "&first_name="+$("#name").val();
        param_string += "&middle_name="+$("#soname").val();
        param_string += "&phone="+$("#phone").val();
        $.ajax({
            url: "/user/make_order?" + param_string,
            context: document.body,
            async: false,
//            dataTypeString: "xml",
            success: function (xml) {
//                regionMap = xml;
//                if ($("Status", regionMap).first().text() == "2") {
//                    $("#region").html("");
//                    $("AreaGroupCollection > item", regionMap).each(function () {
//                        var name = $(this).find("Name").first().text();
//                        var id = $(this).find("Id").first().text();
//                        $("#region").append('<option region-id=' + id + '>' + name + '</option>');
//                    });
//                    $("#region").change(function () {
//                        // Load subRegions
//                        $("#subregion").html("");
//                        var region = $("#region option:selected").html();
//                        $("AreaGroupCollection>item", regionMap).filter(function () {
//                            return $("name", this).first().html() == region;
//                        }).find("AreaAddressCollection>AddressCollection>item").each(function () {
//                                var name = $("Name", this).text();
//                                var areaId = $("AreaId", this).text();
//                                $("#subregion").append('<option subregion-id=' + areaId + '>' + name + '</option>');
//                            });
//
//                        $("#subregion").unbind('change');
//                        $("#subregion").change(function () {
//                            getDeliveryPrice($("#subregion option:selected").attr("subregion-id"), $("guid", regionMap).text());
//                        });
//                        $("#subregion").change();
//                    });
//                    $("#region").change();
//                }
//                else {
//                    alert("Серверная ошибка. Попробуйте позже.");
//                }
                $("body").html(xml);
            },
            failure: function () {
                $("#loader").hide();
                $(".button-bar").show();
                alert("Серверная ошибка. Попробуйте позже");
            }
        });
    });
}