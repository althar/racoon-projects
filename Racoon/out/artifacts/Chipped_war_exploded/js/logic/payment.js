$(document).ready(function () {
    $("#card-expires").mask("99/9999");
    $("#cvc").mask("999");
    $(".card-number-part").mask("9999");
    $(".card-number-part").keyup(function () {
        if ($(this).val().length == 4) {
            $(this).next().focus();
            $(this).next().select();
        }
    });
    $("#amount").numeric();

    $("#make-payment").click(function () {

        makePayment();
    });
});

function makePayment() {
    var friends_wish = $("#friends_wish").val();
    var param_string = getValidatedParams();
    if (!param_string) {
        return;
    }
    $("#loader").show();
    $.ajax({
        url: "/user/make_payment?" + param_string,
        context: document.body,
        async: true,
        success: function (html) {
            var res = html.split('\|')[0];
            var wish_id = html.split('\|')[1];
            //alert(res + " " + wish_id);
            $("#loader").hide();
            if (res.toLowerCase() == "true") {
                alert("Платеж принят");
                if($("#payment_type").val()=="preorder")
                {
                    var guid = $("#guid").val();
                    var address_id = $("#address_id").val();
                    var wish_id = $("#wish_id").val();
                    var delivery_variant_id = $("#delivery_variant_id").val();
                    var address = $("#address").val();
                    var first_name = $("#first_name").val();
                    var last_name = $("#last_name").val();
                    var phone = $("#phone").val();
                    var params = "guid=" + guid
                        + "&address_id=" + address_id
                        + "&delivery_variant_id=" + delivery_variant_id
                        + "&address=" + address
                        + "&wish_id=" + wish_id
                        + "&first_name=" + first_name
                        + "&last_name=" + last_name
                        + "&phone=" + phone;
                    //alert("Redir: "+"/user/create_order?" + params);
                    window.location = "/user/create_order?" + params;
                }
                else
                {
                    window.location = "/user/good_details?wish_id=" + wish_id + "&friend_wish=" + friends_wish;
                }
            }
            else {
                alert("Платеж не принят");
            }
        },
        failure: function () {
            $("#loader").hide();
            alert("Серверная ошибка. Попробуйте позже");
        }
    });
}

function getValidatedParams() {
    $("input").attr("style", "");

    //    wish_id,name,amount,card_number,cvv,month,year
    var wish_id = $("#wish_id").val();
    var name = $("#name").val() + $("#last-name").val();
    var amount = Math.round($("#amount").val() * 100);
    var cvv = $("#cvv").val();
    if ($(".part-1").val().length != 4 || $(".part-2").val().length != 4 || $(".part-3").val().length != 4 || $(".part-4").val().length != 4) {
        $("input[class*=\"part\"]").attr("style", "background-color: #d59392;");
        return false;
    }
    var card_number = $(".part-1").val() + $(".part-2").val() + $(".part-3").val() + $(".part-4").val();
    if (($("#card-expires").val() + "").split("\/").length != 2) {
        $("#card-expires").attr("style", "background-color: #d59392;");
        return false;
    }
    var month = ($("#card-expires").val() + "").split("\/")[0];
    var year = ($("#card-expires").val() + "").split("\/")[1];

    var payment_type = $("#payment_type").val();
    var guid = $("#guid").val();
    var address_id = $("#address_id").val();
    var delivery_variant_id = $("#delivery_variant_id").val();
    var address = $("#address").val();
    var first_name = $("#first_name").val();
    var last_name = $("#last_name").val();
    var phone = $("#phone").val();
    var params = "wish_id=" + wish_id + "&name=" + name + "&amount=" + amount + "&cvv=" + cvv + "&month=" + month + "&year=" + year + "&card_number=" + card_number
        + "&payment_type=" + payment_type
        + "&guid=" + guid
        + "&address_id=" + address_id
        + "&delivery_variant_id=" + delivery_variant_id
        + "&address=" + address
        + "&first_name=" + first_name
        + "&last_name=" + last_name
        + "&phone=" + phone;
    return params;
}
