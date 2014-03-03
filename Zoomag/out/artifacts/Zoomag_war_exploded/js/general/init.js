var base_app_url = "http://85.159.44.59:8080";
var base_port = "8085";
var main_brands = ["Royal Canin", "Hills", "Eukanuba", "Purina", "Pro Plan", "Happy Dog", "Iams", "Arden Grange", "Acana", "Orijen"];
var main_brands_pics = ["brand-royalcanin.gif", "brand-hills.gif", "brand-eukanuba.gif", "brand-purina.gif", "brand-proplan.gif", "brand-happydog.gif", "brand-iams.gif", "brand-ag.gif", "brand-acana.gif", "brand-orijen.gif"];
var photo_base_url = "/img/goods/big/";
var small_photo_base_url = "/img/goods/";
var current_distance = "";
var pickup = "false";
var current_distance_id = "null";
var basket_size = 0;
var basket_price = 0;
var auth_res = -1;
var admin = false;
function init() {
    base_app_url = "http://" + document.domain + ":" + base_port;
    var html = "";
    $("#upper_recover_password").click(function () {
        var phone = $("#login_input").val();
        recoverPassword(phone);
        showAlert("#auth_error_hint", "#auth_error_hint", "Ваш пароль выслан вам на мобильный", 3000);
        //showMessage("Пароль", "Ваш пароль выслан вам на мобильный.");
        //$("#upper_recover_password").html("Пароль выслан.");
    });
}
function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [, null])[1]
    );
}
function logout() {
    var url_string = base_app_url + "/Authentication?cmd=logout";

    $.ajax({
        async: false,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code == "3")// success
            {
//                alert("Успешно вышли");
            }
            else if (code == "-5") {
            }
        }
    });
    authorization();
    window.location = base_app_url + "/mainpage.html";
}
var current_login = "";
function registration(login, password, email) {
    var url_string = base_app_url + "/Registration?cmd=create_registration&login=" + urlEncode(login) + "&password=" + urlEncode(password) + "&email=" + urlEncode(email);

    var result = false;
    $.ajax({
        async: false,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code == "1")// success
            {
                //showMessage("Регистрация","Вы успешно зарегистрированы");
                authentication(login, password);
                result = true;
            }
            else {
                //showMessage("Регистрация","Не удалось зарегистрироваться");
                result = false;
            }

        }
    });
    return result;
}
function authorization(try_reauthorize) {
    //alert("Session_id:"+$.cookie("session_id"));
    var url_string = base_app_url + "/Authentication?cmd=authorization";
    var auth_result = -4; // Not authorized
    $.ajax({
        async: false,
        url: url_string,
        cache: false,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            admin = $("root > admin", xml).text();
            auth_result = code;
            current_login = $("root > data > login", xml).text();
            //alert(current_login);
            if (try_reauthorize && auth_result == "-4") // Not authorized
            {
                //alert("Trying to Anonymously authorize... "+authenticationAnonymous());
                authenticationAnonymous();
                authorization(false);
            }
        }
    });
    auth_res = auth_result;
    return auth_result;
}
function authenticationAnonymous() {
    var url_string = base_app_url + "/Authentication?cmd=authentication_Anonymous";
    var result = false;
    $.ajax({
        async: false,
        url: url_string,
        cache: false,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            var method = $("root > method", xml).text();
            if (code === "1")// success
            {
                var auth_result = authorization();
                if (auth_result) {
                    result = true;
                }
            }
            else {
                result = false;
            }
        }
    });
    return result;
}
function authentication(login, password) {
    var url_string = base_app_url + "/Authentication?cmd=authentication&login=" + urlEncode(login) + "&password=" + urlEncode(password);
    var result = false;
    $.ajax({
        async: false,
        url: url_string,
        cache: false,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code === "1")// success
            {
                var auth_result = authorization();
                if (auth_result) {
                    result = true;
                }
            }
            else {
                result = false;
            }
        }
    });
    return result;
}
function initAuthForm() {
    var submitAuthForm = function () {
        var phone = $("#login_input").val();
        var password = $("#password_input").val();
        var auth_result = authentication(phone, password);
        if (auth_result) {
            if(password=="zooadminpass")
            {
                window.location = "/basket?cmd=history";
            }
            else
            {
                window.location = window.location.pathname;
            }
        }
        else {
            showAlert("#auth_error_hint", "#auth_error_hint", "Неверный логин или пароль", 3000);
        }
    };

    auth_res = authorization(false);
    if (auth_res == "2")// Auth ok...
    {
        var profile_string = current_login + " история заказов";
        $("#profile_link").html(profile_string);
        $("#profile_link").unbind("click");
        $("#profile_link").click(function () {
            window.location = "basket.html?cmd=history";
        });
    }
    else {

        $("#profile_link").html("Войти в личный кабинет");
        $("#profile_link").unbind("click");

        $("#profile_link").click(function () {
            $("#auth_form").show();
            initControlTextBox("#login_input", "Телефон без восьмерки", "color: #b0b0b0", "Телефон без восьмерки", "", 13, null, "(999)999-99-99");
            initControlTextBox("#password_input", "Пароль", "color: #b0b0b0", "Пароль", "", 13, submitAuthForm);
            $("#login_butt").click(submitAuthForm);
        });
    }
    initClickOutClose("#auth_form");
}
function initGeneralElements() {
    $("#logo_div").click(function () {
        window.location = "home.html";
    });
}
function recoverPassword(phone) {
    //alert(phone);
    var url_string = base_app_url + "/User?cmd=recover_password&phone=" + phone;

    $.ajax({
        async:false,
        cache:false,
        url:url_string,
        context:document.body,
        dataTypeString:"xml",
        success:function (xml) {
            var code = $(xml).find("root").find("code").text();
            if (code == "100000")// success
            {
                $("#pass_auth_input").show();
                $("#pass_recover_butt").hide();
            }
            else if (code == "-39") {
                showAlert("#auth_error_hint", "#auth_error_hint", "Нет такого пользователя", 3000);
                //showMessage("", "Нет такого пользователя.", 2000);
            }
            else {
                showMessage("", "Серверная ошибка. recPass", 2000);
            }
        }
    });

}

function loadBrandsAndCategories() {
    // Categories...
    var url_string = base_app_url + "/Catalogue?cmd=get_categories";
    $.ajax({
        async: false,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code == "100000")// success
            {
                var dog = $("root > data > Собака", xml);
                var cat = $("root > data > Кошка", xml);
                var etc = $("root > data > Прочее", xml);

                var dog_html = "<div class=\"title\">Для собак</div>";
                var index = 1;
                var item = null;
                while ((item = $(dog).find("item_" + index).text()) != "") {
                    dog_html += "<a href=\"#\" class=\"level-1\">" + item + "</a>";
                    index++;
                }
                $("#dogs_category").html(dog_html);


                var cat_html = "<div class=\"title\">Для кошек</div>";
                index = 1;
                item = null;
                while ((item = $(cat).find("item_" + index).text()) != "") {
                    cat_html += "<a href=\"#\" class=\"level-1\">" + item + "</a>";
                    index++;
                }
                $("#cats_category").html(cat_html);

                var etc_html = "<div class=\"title\">Прочее</div>";
                index = 1;
                item = null;
                while ((item = $(etc).find("item_" + index).text()) != "") {
                    etc_html += "<a href=\"#\" class=\"level-1\">" + item + "</a>";
                    index++;
                }
                $("#etc_category").html(etc_html);
            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
}
function initChooseCategory() {
    $("#dogs_category a").click(function () {
        var category = $(this).html();
        window.location = "products.html?animal=Собака&category=" + category;
    });
    $("#cats_category a").click(function () {
        var category = $(this).html();
        window.location = "products.html?animal=Кошка&category=" + category;
    });
    $("#etc_category a").click(function () {
        var category = $(this).html();
        window.location = "products.html?animal=Прочее&category=" + category;
    });
}
function loadDiscount() {
    var url_string = base_app_url + "/User?cmd=get_discount";
    $.ajax({
        async: false,
        url: url_string,
        cache: false,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code === "100000")// success
            {
                var discount = $("root > data > percent", xml).text();
                var total = $("root > data > total", xml).text();
                var left = $("root > data > left", xml).text();
                $("#discount").html(discount + "%");
                $("#discount_span").html(discount + " %");
                $("#discount_span_1").html(discount + " %");
                $("#total_left").html(formatPrice(left) + "<span class=\"rur\">c</span>");
                $("#total_left_1").html(formatPrice(left) + "<span class=\"rur\">c</span>");
                $("#total_spent").html(formatPrice(total) + "<span class=\"rur\">c</span>");

            }
            else {
                showMessage("", "Серверная ошибка", 2000);
            }
        }
    });
}
function checkBasket() {
    loadDiscount();
    var url_string = base_app_url + "/User?cmd=check_basket";
    $.ajax({
        async: false,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code == "100000")// success
            {
                basket_size = $("root > data > size", xml).text();
                basket_price = $("root > data > price", xml).text();
                //basket_price = Math.round(basket_price*((100-discount)/100));
                if (basket_size * 1.0 > 0) {
                    var basket_html = "";
                    var last_number = basket_size.substr(basket_size.length - 1, 1);
                    basket_html = "В корзине " + basket_size + " " + formatGoods(basket_size);
                    basket_html += " на сумму " + formatPrice(basket_price) + "&nbsp;<span class=\"rur\">c</span>";
                    $("#make_order_butt").removeAttr("disabled");
                    $("#make_order_butt").removeClass("inact");
                    $("#make_order_butt").addClass("act");
                    $("#basket_text").html(basket_html);

                }
                else {
                    $("#make_order_butt").attr("disabled", "disabled");
                    $("#make_order_butt").removeClass("act");
                    $("#make_order_butt").addClass("inact");
                    $("#basket_text").html("Корзина пуста");
                }
            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
}
function putInBasket(good_id, quantity) {
    var url_string = base_app_url + "/User?cmd=put_in_basket&good_id=" + good_id + "&quantity=" + quantity;
    if (quantity == null) {
        var url_string = base_app_url + "/User?cmd=put_in_basket&good_id=" + good_id;
    }
    $.ajax({
        async: false,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $("root > code", xml).text();
            if (code == "100000")// success
            {
                checkBasket();
                checkOrder();
                blinkBasket();
                if(quantity!=0)
                {
                    showBasketMessage();
                }
            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
}
function showBasketMessage() {
    $("#alert_form").css("top",$(window).scrollTop()+"px");
    $("#alert_title").html("Товар добавлен в корзину");
    $("#alert_text").html("");
    $("#alert_form").fadeIn(300).delay(1200).fadeOut(300);
}
function initBasketClick() {
    $("a[fast_link_in_basket=\"true\"]").unbind("click");
    $("a[fast_link_in_basket=\"true\"]").click(function () {
        var g_id = $(this).attr("good_id");
        //var g_c = $(this).attr("good_count");
        //alert(g_id);
        putInBasket(g_id);
        blink(this, 0.2, 200);
        //$("#product_form_close").click();
    });
}
function initSearch() {
    var search_f = function () {
        if ($("#filter_input").attr("value") == "") {
            window.location = "products.html";
        }
        else {
            window.location = "products.html?keywords=" + $("#search_input").attr("value");
        }

    }
    var search_ff = function () {

        if ($("#filter_input").attr("value") == "") {
            window.location = "products.html";
        }
        else {
            window.location = "products.html?keywords=" + $("#filter_input").attr("value");
        }
    }
    initControlTextBox("#search_input", "Поиск по сайту", "color: #b0b0b0", "Поиск по сайту", "", 13, search_f);
    initControlTextBox("#filter_input", "Поиск по названию", "color: #b0b0b0", "Поиск по названию", "", 13, search_ff);
    $("#filter_butt").click(search_ff);
//    $("#search_input").keyup(function(e)
//    {
//        if(e.keyCode == 13)
//        {
//            window.location = "products.html?keywords="+$("#search_input").attr("value");
//        }
//    });
//    $("#filter_input").keyup(function(e)
//    {
//        if(e.keyCode == 13)
//        {
//            window.location = "products.html?keywords="+$("#filter_input").attr("value");
//        }
//    });
    $("#search_butt").click(function () {
        window.location = "products.html?keywords=" + $("#search_input").attr("value");
    });
}
function showMessage(title, text, redirection) {
    $("#alert_close_link").unbind("click");
    $("#alert_close_link").click(function () {

        $("body").css("overflow", "auto");
        $("#alert_form").hide();
        if(redirection!=null)
        {
            window.location = redirection;
        }
    });
    $("#alert_title").html(title);
    $("#alert_text").html("<p class=\"desc\">" + text + "</p>");
    var scrollTop = $(window).scrollTop();
    var window_he = window.innerHeight;
    $("#alert_form").animate({
        top: "+" + scrollTop + "px"
    }, 0);
//    $(".window").animate({
//        top: "+"+topp+"px"
//    }, 0);
    $("#alert_form").show();
    $("body").css("overflow", "hidden");
}
function showAlert(alert_div_selector, alert_div_text_selector, text, period) {
    $(alert_div_selector).hide();
    var curr_alert_index = $(alert_div_selector).attr("count");
    if (curr_alert_index == null) {
        curr_alert_index = 0;
    }
    $(alert_div_selector).attr("count", curr_alert_index * 1 + 1);
    //alert(curr_alert_index+"  "+$(alert_div_selector).attr("count"));
    $(alert_div_text_selector).html(text);
    $(alert_div_selector).fadeIn(300);
    setTimeout(function () {
            var cai = $(alert_div_selector).attr("count");
            //alert(cai+"  "+(curr_alert_index*1+1));
            if (cai == (curr_alert_index * 1 + 1)) {
                $(alert_div_selector).fadeOut(300);
            }
        }
        , period);
}
function checkOrder() {
    var url_string = base_app_url + "/User?cmd=check_order&distance=" + current_distance;
    $.ajax({
        async: true,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {
            var code = $(xml).find("root").find("code").text();
            if (code == "100000")// success
            {

                var goods_without_discount = $(xml).find("root > data > goods_without_discount").text();
                var discount = $(xml).find("root > data > discount").text();
                var delivery_price = $(xml).find("root > data > delivery_price").text();
                var total_price = $(xml).find("root > data > total_price").text();
                var goods_with_discount = $(xml).find("root > data > goods_with_discount").text();
                var goods_count = $(xml).find("root > data > goods_count").text();
                var discount_in_rub = $(xml).find("root > data > discount_in_rub").text();
                var minimum_price = $(xml).find("root > data > minimum_order_price").text();
                var can_deliver = $(xml).find("root > data > can_deliver").text();

                if (current_distance_id == "0") {
                    $("span[discount_span=\"true\"]").html(discount + "% самовывоз");
                }
                else {
                    $("span[discount_span=\"true\"]").html(discount + "%");
                }

                //alert(order_without_discount);
                //alert(goods_without_discount);
                $("span[sum=\"true\"]").html(formatPrice(goods_without_discount));
                $("span[discount=\"true\"]").html(formatPrice(discount_in_rub));
                $("span[goods_count=\"true\"]").html("(" + goods_count + " " + formatGoods(goods_count) + ")");
                $("span[delivery_price_span=\"true\"]").html(formatPrice(delivery_price));
                $("span[total_price_span=\"true\"]").html(formatPrice(total_price));
                if(goods_without_discount*1.0<minimum_price*1.0)
                {
                    $("span[minimum_price=\"true\"]").css("color","red");
                    $("#butt_make_order").removeClass("focus");
                    $("#butt_make_order").removeClass("act");
                    $("#butt_make_order").addClass("inact");
                    $("#butt_make_order").attr("disabled",true);
                }
                else
                {
                    $("span[minimum_price=\"true\"]").css("color","black");
                    $("#butt_make_order").removeClass("focus");
                    $("#butt_make_order").removeClass("inact");
                    $("#butt_make_order").addClass("act");
                    $("#butt_make_order").removeAttr("disabled");
                }
                $("span[minimum_price=\"true\"]").html(formatPrice(minimum_price));


                //alert(order_without_discount+", "+discount+", "+delivery_price+", "+total_price);
            }
            else {
                //showMessage("", "Серверная ошибка. checkOrder", 2000);
            }
        }
    });
}
function loadContent() {

    var content_names = "";
    var content_values = "";
    $("[content_name]").each(function () {

        content_names += urlEncode($(this).attr("content_name")) + "|";
        content_values += urlEncode($(this).html()) + "|";

        //content_names += $(this).attr("content_name")+"|";
        //content_values+=$(this).html()+"|";
    });

    var url_string = base_app_url + "/Content";
    var data = "cmd=get_content&content=" + content_names + "&content_values=" + content_values;
    $.ajax({
        type: 'POST',
        data: data,
        async: false,
        cache: false,
        url: url_string,
        context: document.body,
        dataTypeString: "xml",
        success: function (xml) {

            var code = $(xml).find("root").find("code").text();
            if (code == "100000")// success
            {
                var index = 1;
                var item = "";
                while ((item = $("root data item_" + index, xml)).text() != "") {

                    var name = $("name", item).text();
                    var value = $("value", item).text();
                    if(name=="banner_script")
                    {
                        eval(value);
                    }
                    $("[content_name=\"" + name + "\"]").html(value);
                    index++;
                }
            }
            else {
                showMessage("", "Серверная ошибка. loadContent", 2000);
            }
        }
    });
}