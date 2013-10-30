$(document).ready(function () {
    $("#date-picker").datepicker();
    $("#date-picker").datepicker("option", "dateFormat", "dd/mm/yy");
    $("#date-picker").datepicker({ showOptions: { direction: "up" } });

    $("#wish-it").click(function () {
        var date = $("#date-picker").val();
        var ids = $("#ids").val();
        var reason = $("#reason option:selected").val();
        var delivery_region_id = $("#region option:selected").attr("region-id");
        var delivery_area_id = $("#subregion option:selected").attr("subregion-id");
        var delivery_group_id = $("#delivery-group option:selected").attr("delivery-group-id");
        var delivery_variant_id = $("#delivery-variant option:selected").attr("delivery-variant-id");
        var delivery_summ = $("#delivery-variant option:selected").attr("delivery-summ");
        var toFriends = $("#to-friends").val();
        window.location = "/user/make_wish?id_price="
            + ids
            + "&reason="
            + reason
            + "&date="
            + date
            + "&to_friends=false&"
            + "&delivery_summ="+delivery_summ
            + "&delivery_region_id="+delivery_region_id
            + "&delivery_area_id="+delivery_area_id
            + "&delivery_group_id="+delivery_group_id
            + "&delivery_variant_id="+delivery_variant_id;
//        window.location = "/user/make_wish?id_price=" + ids + "&reason=" + reason + "&date=" + date + "&to_friends=false";
    });
    $("#friend-wish").click(function () {
        var date = $("#date-picker").val();
        var ids = $("#ids").val();
        var reason = $("#reason option:selected").val();
        var delivery_region_id = $("#region option:selected").attr("region-id");
        var delivery_area_id = $("#subregion option:selected").attr("subregion-id");
        var delivery_group_id = $("#delivery-group option:selected").attr("delivery-group-id");
        var delivery_variant_id = $("#delivery-variant option:selected").attr("delivery-variant-id");
        var delivery_summ = $("#delivery-variant option:selected").attr("delivery-summ");
        var toFriends = $("#to-friends").val();
        window.location = "/user/make_wish?id_price="
            + ids
            + "&reason="
            + reason
            + "&date="
            + date
            + "&to_friends=true";
//            + "&delivery_summ="+delivery_summ
//            + "&delivery_region_id="+delivery_region_id
//            + "&delivery_area_id="+delivery_area_id
//            + "&delivery_group_id="+delivery_group_id
//            + "&delivery_variant_id="+delivery_variant_id;
    });
//    $("#wish-it").click(function () {
//        var date = $("#date-picker").val();
//        var ids = $("#ids").val();
//        var reason = $("#reason option:selected").val();
//        var toFriends = $("#to-friends").val();
//        window.location = "/user/make_wish?id_price=" + ids + "&reason=" + reason + "&date=" + date + "&to_friends=false";
//    });

//delivery_choose

    $("#calendar-icon").click(function () {
        $("#date-picker").datepicker("show");
    });
    //loadRegions();
});
var regionMap = null;

//function loadRegions() {
//    var ids = $("#ids").val();
//    var id = (ids.split("\|")[0]).split("\_")[0];
//    $("#loader").show();
//    $(".button-bar").hide();
//    $.ajax({
//        url: "/user/region_choose?good_id=" + id,
//        context: document.body,
//        async: true,
//        dataTypeString: "xml",
//        success: function (xml) {
//            regionMap = xml;
//            if ($("Status", regionMap).first().text() == "2") {
//                $("#region").html("");
//                $("AreaGroupCollection > item", regionMap).each(function () {
//                    var name = $(this).find("Name").first().text();
//                    var id = $(this).find("Id").first().text();
//                    $("#region").append('<option region-id=' + id + '>' + name + '</option>');
//                });
//                $("#region").change(function () {
//                    // Load subRegions
//                    $("#subregion").html("");
//                    var region = $("#region option:selected").html();
//                    $("AreaGroupCollection>item", regionMap).filter(function () {
//                        return $("name", this).first().html() == region;
//                    }).find("AreaAddressCollection>AddressCollection>item").each(function () {
//                            var name = $("Name", this).text();
//                            var areaId = $("AreaId", this).text();
//                            $("#subregion").append('<option subregion-id=' + areaId + '>' + name + '</option>');
//                        });
//
//                    $("#subregion").unbind('change');
//                    $("#subregion").change(function () {
//                        getDeliveryPrice($("#subregion option:selected").attr("subregion-id"), $("guid", regionMap).text());
//                    });
//                    $("#subregion").change();
//                });
//                $("#region").change();
//            }
//            else {
//                alert("Серверная ошибка. Попробуйте позже.");
//            }
//
//        },
//        failure: function () {
//            $("#loader").hide();
//            $(".button-bar").show();
//            alert("Серверная ошибка. Попробуйте позже");
//        }
//    });
//}
//var deliveryMap = null;
//function initDeliveryChoose()
//{
//    $("#delivery-group").unbind("change");
//    $("#delivery-group").change(function(){
//        var html = "";
//        var groupId = $(this).find("option:selected").attr("delivery-group-id");
//        $("DeliveryModel>DeliveryGroups>item",deliveryMap).filter(function(){
//            return $(this).find("DeliveryGroupID").text()==groupId;
//        }).find("Delivery>item").each(function(){
//                html +="<option ";
//                html +="delivery-name=\""+$(this).find("Name").first().text()+"\"";
//                html +=" delivery-variant-id=\""+$(this).find("DeliveryVariantID").first().text()+"\"";
//                html +=" delivery-summ=\""+$(this).find("DeliverySumm").first().text()+"\"";
//                html +=">";
//                html +=$(this).find("Name").first().text();
//                html +="</option>";
//            });
//        $("#delivery-variant").html(html);
//        $("#loader").hide();
//        $(".button-bar").show();
//        $(".button-bar").removeClass("hidden");
//    });
//
//}
//function getDeliveryPrice(subregionId, guid) {
//    //alert(subregionId + "   " + guid);
//    $(".button-bar").hide();
//    $("#loader").show();
//    $.ajax({
//        url: "/user/delivery_choose?area_id=" + subregionId+"&guid="+guid,
//        context: document.body,
//        async: true,
//        dataTypeString: "xml",
//        success: function (xml) {
//            deliveryMap = xml;
//            if ($("Status", deliveryMap).first().text() == "2") {
//                var html = "";
//                $("DeliveryModel>DeliveryGroups>item",deliveryMap).each(function(){
//                    html +="<option ";
//                    html +="delivery-group-name=\""+$(this).find("DeliveryGroupName").first().text()+"\"";
//                    html +="delivery-group-id=\""+$(this).find("DeliveryGroupID").first().text()+"\"";
//                    html +=">";
//                    html +=$(this).find("DeliveryGroupName").first().text();
//                    html +="</option>";
//                });
//                $("#delivery-group").html(html);
//                initDeliveryChoose();
//                $("#delivery-group").change();
//            }
//            else
//            {
//                alert("Серверная ошибка. Попробуйте позже");
//            }
//        },
//        failure: function () {
//            $("#loader").hide();
//            alert("Серверная ошибка. Попробуйте позже");
//        }
//    });
//}
