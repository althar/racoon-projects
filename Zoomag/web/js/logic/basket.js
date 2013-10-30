var discount = 0;
var metroNames = new Array();
var metroIDs = new Array();
var left_grabbed = false;
var right_grabbed = false;
var l_delta = 0;
var r_delta = 0;
var auth_flag = true;

$(document).ready(function () {
    $("body").hide();
    init();
    initMenu();
    initOrderAuthForm();
    initAddressForm();
    authorization(true);
    checkBasket();
    loadBrandsAndCategories();
    initChooseCategory();
    initGeneralElements();
    var cmd = getURLParameter("cmd");
    showBasket();

    initProfile();
    initSearch();
    canMakeOrder();
    initAuthForm();
    checkOrder();
    initTimePicker();
    if (cmd == "history") {
        $("#history_link").click();
    }
    if (cmd == "profile") {
        $("#profile_menu_link").click();
    }
    loadContent();
    $("body").show();
});

function blinkBasket(){
    blink("#basket",0.2,200);
}
function initOrderAuthForm() {
    initControlTextBox("#order_phone_input", "Номер телефона", "color:#999;", "Номер телефона", "", 13, null, "(999)999-99-99");
    hoverControl("#order_phone_input", "#phone_reg_input", "focus", "");
    initControlTextBox("#order_password_input", "Пароль", "color:#999;", "Пароль", "", 13, null);
    hoverControl("#order_password_input", "#pass_reg_input", "focus", "");
    initControlTextBox("#order_email_input", "Электронная почта", "color:#999;", "Электронная почта", "", 13, null);
    hoverControl("#order_email_input", "#email_reg_input", "focus", "");
    setOrderStep(1, false);

}
function checkUserExists() {
    var phone = $("#order_phone_input").val();
    var url_string = base_app_url + "/Registration?cmd=check_registration&phone=" + phone;
    var res = false;
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
                res = $("root>data>exists", xml).text() == "true";
            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
    return res;
}
function showHint(text, need_recover_link) {
    var html = text;
    if (need_recover_link) {
        html += "Если вы забыли пароль, воспользуйтесь <a id=\"recover_password_link\">этой ссылкой</a>.";

    }
    $("#recover_link_block").html(html);
    $("#recover_password_link").unbind("click");

    $("#recover_password_link").click(function () {
        var phone = $("#order_phone_input").val();
        recoverPassword(phone);
        showMessage("Пароль", "Ваш пароль выслан вам на мобильный.");
    });
    $("#recover_link_block").show();
}
function setOrderStep(step, authorization_flag) {
    auth_flag = authorization_flag;
    if (step == 1) {
        $("#summary").show();

        canMakeOrder();
        $("#make_order_butt").show();
        $("#address_form").hide();
        $("#make_order_butt").unbind("click");
        $("#make_order_butt").bind("click", function () {
            if($(this).hasClass("act"))
            {
                setOrderStep(2, false);
            }
        });
        showHint("Введенный вами телефон - будет являться вашим логином.", false);
    }
    if (step == 2) {

        if (auth_res == 2) {
            setOrderStep(3, false);
        }
        else {
            $("#make_order_butt").hide();

            $("#reg_auth_form").show();
            $("#order_phone_input").keyup(function () {
                var val = $(this).val().replace(/_/g, "");
                //document.title = val+" "+val.length;
                if (val.length == 14)// Check exist...
                {
                    var exists = checkUserExists();
                    auth_flag = exists;
                    $("#password_col").show();
                    if (exists) {
                        showHint("Судя по всему вы уже зарегистрированы на сайте. Введите пароль.", true);
                    }
                    else {
                        showHint("Вы еще не зарегистрированы на сайте. Предлагаем вам ввести адрес эл. почты и придумать пароль.", false);
                        $("#email_col").show();
                    }
                    $("#next").show();
                }
                else {
                    $("#password_col").hide();
                    $("#email_col").hide();
                    $("#next").hide();
                }
            });
            $("#next").bind("click", function () {
                if (auth_flag) {
                    auth();
                }
                else {
                    reg();
                }
            });
        }
    }
    if (step == 3) {
        $("#reg_auth_form").hide();
        $("#next_butt").hide();
//        $("div[delivery_variant=\"true\"]").first().click();
        initAddressForm();
        $("#address_form").show();
        $("#make_order_butt").unbind("click");
        $("#make_order_butt").show();
        $("#make_order_butt").bind("click", function () {
            setOrderStep(4, false);
        });
    }
    if (step == 4) {
        saveOrder();
    }
}
function showBasket() {

    $("#profile_div").hide();
    $("#history_div").hide();
    $("#step_reg_auth").hide();
    $("#step_address").hide();
    $("#next").hide();
    $("#next_2").hide();
    $("#step_make_order").show();
    loadBasket();
    canMakeOrder();
    $("#step_make_order button").show();
    $("#basket_div").show();
}
function showHistory() {
    if (auth_res == "2") {
        loadHistory();
        $("#profile_div").hide();
        $("#basket_div").hide();
        $("#history_div").show();
    }
    else {
        $("#profile_link").click();
    }
}
function showProfile() {
    if (auth_res == "2") {
        loadProfile();
        $("#basket_div").hide();
        $("#history_div").hide();
        $("#profile_div").show();
    }
    else {
        $("#profile_link").click();
    }
}

function initMenu() {
    $("#brands_bar a").click(function () {
        $("#brands_bar a").removeClass("act");
        $("#brands_bar a").removeClass("corners_3");
        $("#brands_bar a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");
    });
    $("#basket_link").click(function () {
        showBasket();
    });
    $("#history_link").click(function () {
        showHistory();
    });
    $("#profile_menu_link").click(function () {
        showProfile();
    });
}

function loadBrandsAndCategories() {
    // Categories...
    var url_string = base_app_url + "/Catalogue?cmd=get_categories";
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
                var dog = $(xml).find("root").find("data").find("Собака");
                var cat = $(xml).find("root").find("data").find("Кошка");
                var etc = $(xml).find("root").find("data").find("Прочее");

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
function loadBasket() {
    var url_string = base_app_url + "/User?cmd=get_basket";
    $.ajax({
        async:false,
        url:url_string,
        cache:false,
        context:document.body,
        dataTypeString:"xml",
        success:function (xml) {
            var code = $(xml).find("root").find("code").text();
            if (code === "100000")// success
            {

                var index = 1;
                var curr_item = null
                var table_html = "";
                var total_price = $(xml).find("root sum").text();
                var discount = $(xml).find("root discount").text();
                while ((curr_item = $(xml).find("root data item_" + index)).text() != "") {
                    var id = $("good_id", curr_item).text();
                    var name = $("name", curr_item).text();
                    var quantity = $("quantity", curr_item).text();
                    var left = $("left", curr_item).text();
                    var photo_url = $("photo_url", curr_item).text();
                    var price = $("price", curr_item).text();
                    var description = $("description", curr_item).text();
                    var weight = $("weight", curr_item).text();

                    table_html += "<tr item_id=\"" + id + "\">";
                    table_html += "<td class=\"photo\"><img src=\"" + photo_base_url + photo_url + "\" alt=\"\" />";
                    table_html += "<td class=\"desc\">";
                    table_html += "<p class=\"title\"><a href=\"products.html?id=" + id + "\">" + name + "</a> <span class=\"article\"></span></p>";
                    table_html += "<p class=\"info\">" + description.substr(0, 260) + "...</p></td>";
                    table_html += "<td class=\"quantity\"><a class=\"minus\" item_id=\"" + id + "\"></a>";
                    table_html += "<input type=\"text\" maxlength=\"2\" class=\"input_quantity quantity_regular\" q=\"" + quantity + "\" item_id=\"" + id + "\" value=\"" + quantity + "\"/>";
                    table_html += "<a class=\"plus\" item_id=\"" + id + "\"></a>";
                    if(left!=null&&left!="")
                    {
                        table_html+="<b ";
                        if(left*1<quantity*1)
                        {
                            table_html+=" style=\"color:red;\""
                        }
                        table_html+=">доступно "+left+"</b>";
                    }
                    table_html += "</td><td class=\"price\">";
                    table_html += formatPrice(price) + "<span class=\"rur\">c</span></td><td class=\"delete\" item_id=\"" + id + "\"><a></a></td></tr>";

                    index++;
                }
                $("#basket_table").html(table_html);
                $(".quantity_regular").each(function () {
                    hoverControl(this, this, "quantity_focus", "quantity_regular");
                });

                initChangeItems();
                $("#step_make_order button").unbind("click");
                $("#step_make_order button").click(function () {
                    createOrder();
                });

                checkBasket();
            }
            else {

            }
        }
    });
}
function canMakeOrder() {
    $("#sum").html(formatPrice(basket_price));
    if (basket_size * 1 > 0) {
        $("#butt_make_order").removeClass("focus");
        $("#butt_make_order").removeClass("inact");
        $("#butt_make_order").addClass("act");
        hoverControl("#butt_make_order", "#butt_make_order", "focus", "");
        hoverControl("#next_butt", "#next_butt", "focus", "");
        hoverControl("#next_2_butt", "#next_2_butt", "focus", "");
        $("#butt_make_order").removeAttr("disabled");
    }
    else {
        $("#butt_make_order").removeClass("focus");
        $("#butt_make_order").removeClass("act");
        $("#butt_make_order").addClass("inact");
        $("#butt_make_order").attr("disabled", "disabled");
    }
}
function initChangeItems() {
    $(".minus").click(function () {
        var good_id = $(this).attr("item_id");
        var quantity = $(this).parent().find("input").val();
        quantity = quantity * 1.0 - 1;
        if (quantity > 0) {
            putInBasket(good_id, quantity);
            refreshItem(good_id, quantity);
        }
    });
    $(".plus").click(function () {
        var good_id = $(this).attr("item_id");
        var quantity = $(this).parent().find("input").val();
        quantity = quantity * 1.0 + 1;

        if (quantity > 0) {
            putInBasket(good_id, quantity);
            refreshItem(good_id, quantity);
        }
    });
    $(".delete").click(function () {
        var good_id = $(this).attr("item_id");
        var quantity = 0;
        putInBasket(good_id, quantity);
        refreshItem(good_id, quantity);
    });
    $(".input_quantity").keyup(function () {
        var good_id = $(this).attr("item_id");
        var quantity = $(this).val();
        if (!validateNumber(quantity)) {
            var last = $(this).attr("q");
            $(this).val(last);
        }
        else {
            var ok_val = $(this).val();
            $(this).attr("q", ok_val);
        }
        quantity = $(this).val();
        putInBasket(good_id, quantity);
        refreshItem(good_id, quantity);

    });
}
function refreshItem(id, new_quantity) {
    var item = $("tr[item_id=\"" + id + "\"]");
    if (new_quantity == 0) {
        $(item).remove();
    }

    $(item).find(".quantity input").val(new_quantity);
    canMakeOrder();
}

function reg() {
    var login = $("#order_phone_input").val();
    if (!validatePhone(login)) {
        $("#phone_reg_input").focus().select();
        return;
    }
    login = login.replace(/\(/g, "");
    login = login.replace(/\)/g, "");
    login = login.replace(/\-/g, "");
    login = "(" + login.substr(0, 3) + ")" + login.substr(3, 3) + "-" + login.substr(6, 2) + "-" + login.substr(8, 2);
    var pass = $("#order_password_input").val();
    if (!validatePassword(pass)) {
        showAlert("#alert_div", "#alert_div span", "Неверный пароль. Минимум 6 знаков", 4000);
        $("#pass_reg_input").focus().select();
        return;
    }
    var email = $("#order_email_input").val();
    if (!validateEmail(email)) {
        showAlert("#alert_div", "#alert_div span", "Неверная почта.", 4000);
        $("#email_reg_input").focus().select();
        return;
    }
    registration(login, pass, email);
    auth_res = authorization(false);
    if (auth_res == "2") {
        setOrderStep(3, false);
        checkBasket();
    }
    else {
        showAlert("#alert_div", "#alert_div span", "Пользователь с такми номером уже существует.", 4000);
    }
}
function auth() {
    var login = $("#order_phone_input").val();
    var pass = $("#order_password_input").val();
    //alert(login+pass);
    if (!validatePhone(login)) {
        $("#phone_auth_input").focus().select();
        showAlert("#alert_div", "#alert_div span", "Неверный телефон.", 4000);
        return;
    }
    //login = "("+login.substr(0, 3)+")"+login.substr(3, 3)+"-"+login.substr(6, 2)+"-"+login.substr(8, 2);
    if (!validatePassword(pass)) {
        $("#pass_auth_input").focus().select();
        showAlert("#alert_div", "#alert_div span", "Неверный пароль.", 4000);
        return;
    }
    //
    authentication(login, pass);
    auth_res = authorization(false);
    if (auth_res == "2") {
        //showMessage("Авторизация успешна", "Офоормление заказа");
        setOrderStep(3, false);
        checkBasket();

        if(admin == "true")
        {
            loadBasket();
        }
    }
    else {
        showAlert("#alert_div", "#alert_div span", "Неверный телефон или пароль.", 4000);
    }
}

function setValNotEmpty(value, selector) {
    if (value != null && value != "") {
        $(selector).val(value);
    }
}
function initAddressForm() {
    $("#input_city").focus(function(){
        if($(this).val()==""&&!$(this).hasClass("red_error"))
        {
            $(this).val("Москва");
        }
    });
    $("#input_city, #input_street, #house, #building, #room, #porch, #domophone, #floor, #distance, #phone_1, #phone_2, #description").keydown(function () {
        $(this).css("color", "black");
    });
    $("#date").mask("9999-99-99");
    $("#date").datepicker({ dateFormat:"d MM yy (DD)",
        beforeShow:function (input, inst) {
            setTimeout(function () {
                $(inst.dpDiv).css('zIndex', 100);
            }, 10);
        },
        minDate: new Date()

    });
    //$("#date").datepicker.setDefaults( $.datepicker.regional[ "ru" ] );
    $(".cal").click(function(){
        $("#date").focus();
    });
    initControlTextBox("#description", "Комментарии к заказу", "color:#999;", "Комментарии к заказу", "", 13, null);
    initControlTextBox("#phone_1", "Телефон", "color:#999;", "Телефон", "", 13, null,"(999)999-99-99");
    initControlTextBox("#phone_2", "Телефон", "color:#999;", "Телефон", "", 13, null,"(999)999-99-99");

    var url_string = base_app_url + "/User?cmd=get_metros";
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
                var index = 1;
                var current = "";

                while ((current = $("root > data > item_" + index, xml)).text() != "") {
                    var name = $(current).find("name").text();
                    var id = $(current).find("id").text();
                    metroNames[index - 1] = name;
                    metroIDs[index - 1] = id;
                    index++;
                }
                $("#metro").autocomplete({source:metroNames, appendTo:"#metro_autocompleat", minLength:2, select:function (event, ui) {
                    for (i = 0; i < metroIDs.length; i++) {
                        if (metroNames[i] + "" == ui.item.value + "") {
                            $("#metro").attr("metro_id", metroIDs[i]);
                            //alert($("#metro").attr("metro_id"));
                        }
                    }
                }});
                $("#input_street").keyup(function () {
                    var street_part = $(this).val();
                    if (street_part.length > 2) {
                        var street_arr = getStreets(street_part);
                        $("#input_street").autocomplete({source:street_arr, appendTo:"#street_autocompleat", minLength:3, select:function (event, ui) {
                            for (i = 0; i < street_arr.length; i++) {
                                if (street_arr[i] + "" == ui.item.value + "") {
                                    //alert(street_arr[i]);
                                    $("#input_street").autocomplete("search");
                                }
                            }
                        }});
                    }
                });
            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
    // Load deliver distances...
    var url_string = base_app_url + "/User?cmd=get_address";
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
                var phone_2 = $("root > data > phone_2", xml).text();
                var phone_3 = $("root > data > phone_3", xml).text();
                var street = $("root > data > street", xml).text();
                var house = $("root > data > house", xml).text();
                var building = $("root > data > building", xml).text();
                var porch = $("root > data > porch", xml).text();
                var floor = $("root > data > floor", xml).text();
                var domophone = $("root > data > domophone", xml).text();
                var room = $("root > data > room", xml).text();
                var city = $("root > data > city", xml).text();
                var metro_id = $("root > data > metro_id", xml).text();

                setValNotEmpty(city, "#input_city");
                setValNotEmpty(street, "#input_street");
                setValNotEmpty(house, "#house");
                setValNotEmpty(building, "#building");
                setValNotEmpty(room, "#room");
                setValNotEmpty(porch, "#porch");
                setValNotEmpty(domophone, "#domophone");
                setValNotEmpty(floor, "#floor");
                setValNotEmpty(phone_2, "#phone_1");
                setValNotEmpty(phone_3, "#phone_2");


                $("#input_city").keyup();
                $("#input_street").keyup();
                $("#house").keyup();
                $("#building").keyup();
                $("#room").keyup();
                $("#porch").keyup();
                $("#domophone").keyup();
                $("#floor").keyup();
                $("#phone_1").keyup();
                $("#phone_2").keyup();

                for (i = 0; i < metroIDs.length; i++) {
                    if (metroIDs[i] + "" == metro_id + "") {
                        $("#metro").val(metroNames[i]);
                        $("#metro").attr("metro_id", metro_id);
                        $("#metro").keyup();
                    }
                }
            }
            else {
                //showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });

    $("div[delivery_variant=\"true\"]").click(function () {
        $("div[delivery_variant=\"true\"]").html("");
        $(this).html("<div class=\"dot corners_5\">");
        current_distance = $(this).attr("distance");
        $("#distance_group").attr("distance", current_distance);
        //current_distance = $("#distance_group").attr("distance");
        current_distance_id = $(this).attr("distance_id");
        if(current_distance_id=="0")
        {
            $(".padding_l10 .group input").not("#phone_1,#phone_2").attr("disabled","true");
        }
        else
        {
            $(".padding_l10 .group input").not("#phone_1,#phone_2").removeAttr("disabled");
        }
        //alert(current_distance+"  "+current_distance_id);
        checkOrder(current_distance_id);
    });
}
function getStreets(street_part) {
    var streetNames = new Array();
    var url_string = base_app_url + "/User?cmd=get_streets&street_part=" + urlEncode(street_part);
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
                var index = 1;
                var current = "";

                while ((current = $("root > data > item_" + index, xml)).text() != "") {
                    //alert($(current).find("name").text());
                    var name = $(current).find("name").text();
                    streetNames[index - 1] = name;
                    index++;
                }
            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
    return streetNames;
}
function saveOrder() {
    if (validateOrder()) {
        var deliver_date = $("#date").datepicker("getDate").getTime();
        var city = $("#input_city").val();
        var street = $("#input_street").val();
        var from_hour = $("#from_hour").html();
        var from_minute = $("#from_minutes").html();
        var to_hour = $("#to_hour").html();
        var to_minute = $("#to_minutes").html();
        var house = $("#house").val();
        var building = $("#building").val();
        var room = $("#room").val();
        var porch = $("#porch").val();
        var domophone = $("#domophone").val();
        var floor = $("#floor").val();
        var distance = current_distance;
        var distance_id = current_distance_id;
        var phone_2 = $("#phone_1").val();
        var phone_3 = $("#phone_2").val();
        var metro_id = $("#metro").attr("metro_id");
        var description = $("#description").val();
        if (metro_id == null) {
            metro_id = 0;
        }
        if (phone_2 == "Номер телефона") {
            phone_2 = "";
        }
        if (phone_3 == "Номер телефона") {
            phone_3 = "";
        }
        if (building == "Корп./стр.") {
            building = "";
        }
        if (porch == "Подъезд") {
            porch = "";
        }
        if (domophone == "Домофон") {
            domophone = "";
        }
        if (floor == "Этаж") {
            floor = "";
        }
        if (description == "Комментарии к заказу") {
            description = "";
        }

        $("#loader").show();
        var url_string = base_app_url + "/User?cmd=save_order&city=" + urlEncode(city)
            + "&street=" + urlEncode(street)
            + "&deliver_date=" + urlEncode(deliver_date)
            + "&from_hour=" + urlEncode(from_hour)
            + "&from_minute=" + urlEncode(from_minute)
            + "&to_hour=" + urlEncode(to_hour)
            + "&to_minute=" + urlEncode(to_minute)
            + "&house=" + urlEncode(house)
            + "&building=" + urlEncode(building)
            + "&room=" + urlEncode(room)
            + "&porch=" + urlEncode(porch)
            + "&domophone=" + urlEncode(domophone)
            + "&floor=" + urlEncode(floor)
            + "&distance=" + urlEncode(distance)
            + "&distance_id=" + urlEncode(distance_id)
            + "&phone_2=" + urlEncode(phone_2)
            + "&phone_3=" + urlEncode(phone_3)
            + "&metro_id=" + urlEncode(metro_id)
            + "&description=" + urlEncode(description);

        $.ajax({
            async:true,
            cache:false,
            url:url_string,
            context:document.body,
            dataTypeString:"xml",
            success:function (xml) {
                var code = $(xml).find("root").find("code").text();
                var admin = $(xml).find("root").find("admin").text();
                if (code == "100000")// success
                {
                    current_distance_id = "0";
                    current_distance = "Самовывоз";
                    if(admin=="true")
                    {
                        alert("Заказ принят");
                        window.location = "/products.html";
                    }
                    showBasket();
                    checkOrder();
                    $("#loader").hide();
//                    «Спасибо!
//                    Ваш заказ 500000 успешно оформлен.
//                    В ближайшее время наши операторы с вами свяжутся,
//                    но в любом случае ваш заказ уже передан в сборку и доставку.»
                    showMessage("Спасибо!", "Ваш заказ №" + $(xml).find("root").find("order_id").text() + " успешно оформлен.<br>" +
                        "В ближайшее время наши операторы с вами свяжутся," +
                        "но в любом случае ваш заказ уже передан в сборку и доставку.");
                        $("#alert_close_link").click(function () {
                        $("#history_link").click();
                        setOrderStep(1, false);
                    });
                }
                else {
                    showMessage("", "Серверная ошибка.", 2000);
                }
            }
        });
    }
}
function validateOrder() {
    $("input").change(function(){
        $(this).removeClass("red_error");
    });
    $("input").keyup(function(){
       $(this).removeClass("red_error");
    });
    var res = true;
    $("input").removeClass("red_error");
    if ($("#date").datepicker("getDate") == null) {
        $("#date").focus();
        $("#date").addClass("red_error");
        res = false;
    }
    if(current_distance_id!=0)
    {
        if (!validateNotEmpty($("#input_city").val())) {
            $("#input_city").addClass("red_error");
            $("#input_city").focus();
            res = false;
        }
        if (!validateNotEmpty($("#input_street").val())) {
            $("#input_street").addClass("red_error");
            $("#input_street").focus();
            res = false;
        }
        if (!validateNotEmpty($("#house").val())) {
            $("#house").addClass("red_error");
            $("#house").focus();
            res = false;
        }

        if (!validateNotEmpty($("#metro").val())) {
        $("#metro").addClass("red_error");
        $("#metro").focus();
        res = false;
    }
    }
    return res;
}


function loadHistory() {
    if (authorization(false) == "2") {
        var url_string = base_app_url + "/User?cmd=get_order_history";

        $("#no_orders_comment").hide();
        $("#order_list").hide();
        $("#history_preloader").show();
        $.ajax({
            async:true,
            cache:false,
            url:url_string,
            context:document.body,
            dataTypeString:"xml",
            success:function (xml) {
                var code = $(xml).find("root").find("code").text();
                if (code == "100000")// success
                {
                    //alert($("root",xml).text());
                    var index = 1;
                    var current_row = "";
                    var current_order_id = "";
                    var list_html = "";
                    var order_html = "";
                    var goods_in_order = 0;
                    while ((current_row = $("root > data > item_" + index, xml)).text() != "") {
                        goods_in_order++;

                        var order_id = $(current_row).find("id").text();

                        if (current_order_id != order_id)// New order
                        {

                            var deliver_date = $(current_row).find("deliver_date").text();
                            var total_price = $(current_row).find("total_price").text();

                            list_html += order_html.replace("rowspan=x", "rowspan=\"" + goods_in_order + "\"");
                            order_html = "<tr>";
                            current_order_id = order_id;
                            order_html += "<td rowspan=x class=\"order_info\">";
                            order_html += "<p class=\"info\">Заказ</p>";
                            order_html += "<p class=\"value\">№ " + current_order_id + "</p>";
                            order_html += "<p class=\"info\">Дата доставки</p>";
                            order_html += "<p class=\"value\">" + deliver_date + "</p>";
                            order_html += "<p class=\"info\">Сумма заказа</p>";
                            order_html += "<p class=\"value\">" + total_price + " Р</p>";
                            order_html += "</td>";
                            goods_in_order = 0;
                        }
                        else {
                            order_html += "<tr>";
                        }


                        var photo_url = $(current_row).find("photo_url").text();
                        var name_for_shop = $(current_row).find("name_for_shop").text();
                        var description = $(current_row).find("descr").text();
                        var count = $(current_row).find("count").text();
                        var articul = $(current_row).find("articul").text();
                        var good_id = $(current_row).find("good_id").text();
                        var goods_price = $(current_row).find("goods_price").text();
                        var good_price = $(current_row).find("good_price").text();
                        //alert(photo_url+" "+name_for_shop+" "+count+goods_price);
                        order_html += "<td class=\"photo\"><img src=\"" + photo_base_url + photo_url + "\" alt=\"\" /></td>";
                        order_html += "<td class=\"desc\"><p class=\"title\"><a href=\"products.html?id=" + good_id + "\">" + name_for_shop + "</a> <span class=\"article\">(арт.&nbsp;" + articul + ")</span></p>";
                        order_html += "<p class=\"info\">" + description.substr(0, 80) + "...</p></td>";
                        order_html += "<td class=\"quantity\">" + count + " шт. </td>";
                        order_html += "<td class=\"price\">" + formatPrice(good_price) + "<span class=\"rur\"> c</span></td>";
                        order_html += "<td class=\"buy\"><a class=\"corners_3\" fast_link_in_basket=\"true\" good_id=\"" + good_id + "\"><img src=\"img/gui/basket_s.gif\" alt=\"\"/></a></td>";
                        order_html += "</tr>";


                        index++;
                    }
                    if (index > 1) {
                        list_html += order_html.replace("rowspan=x", "rowspan=\"" + (goods_in_order + 1) + "\"");
                        $("#history_table").html(list_html);
                        initBasketClick();
                        $("#no_orders_comment").hide();
                        $("#order_list").show();
                    }
                    else {
                        $("#order_list").hide();
                        $("#no_orders_comment").show();
                    }
                }
                else if (code == "-39") {
                    showMessage("", "Нет такого пользователя.", 2000);
                }
                else {
                    showMessage("", "Серверная ошибка.", 2000);
                }
            }
        });

    }
    else {
        $("#order_list").hide();
        $("#no_orders_comment").show();
    }
    $("#history_preloader").hide();
}

function loadProfile() {
    var url_string = base_app_url + "/User?cmd=get_profile";

    $.ajax({
        async:true,
        cache:false,
        url:url_string,
        context:document.body,
        dataTypeString:"xml",
        success:function (xml) {
            var code = $(xml).find("root").find("code").text();
            if (code == "100000")// success
            {
                var email = $("root > data > email", xml).text();
                var phone_1 = $("root > data > phone_1", xml).text();
                var password = $("root > data > password", xml).text();
                //alert(phone_1);
                if (password != null && password != "") {
                    $("#input_profile_password").focus();
                    $("#input_profile_password").val(password);

                }
                if (email != null && email != "") {
                    $("#input_profile_email").focus();
                    $("#input_profile_email").val(email);
                }

                $("#input_profile_phone").focus();
                $("#input_profile_phone").val(phone_1);


            }
            else {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
    loadDiscount();
}
function initProfile() {
    if (auth_res == "2") {
        $("#discount_more").hide();
    }
    initControlTextBox("#input_profile_phone", "Номер телефона без восьмерки", "color: #b0b0b0", "Номер телефона без восьмерки", "", 13, saveProfile, "(999)999-99-99");
    initControlTextBox("#input_profile_password", "Пароль", "color: #b0b0b0", "Пароль", "", 13, saveProfile);
    initControlTextBox("#input_profile_email", "Адрес электронной почты", "color: #b0b0b0", "Адрес электронной почты", "", 13, saveProfile);

    $("#save_profile_butt").click(saveProfile);
    loadProfile();

}
function saveProfile() {
    var login = $("#input_profile_phone").val();
    if (!validatePhone(login)) {
        showAlert("#profile_alert_div", "#profile_alert_div span", "Неверный телефон.", 4000);
        $("#input_profile_phone").focus().select();
        return;
    }
    //login = "("+login.substr(0, 3)+")"+login.substr(3, 3)+"-"+login.substr(6, 2)+"-"+login.substr(8, 2);
    var pass = $("#input_profile_password").val();
    var repass = $("#input_profile_password").val();
    if (!validatePassword(pass)) {
        showAlert("#profile_alert_div", "#profile_alert_div span", "Неверный пароль. Минимум 6 знаков", 4000);
        $("#input_profile_password").focus().select();
        return;
    }
    var email = $("#input_profile_email").val();
    if (!validateEmail(email)) {
        showAlert("#profile_alert_div", "#profile_alert_div span", "Неверная почта.", 4000);
        $("#input_profile_email").focus().select();
        return;
    }
    if (repass != pass) {
        showAlert("#profile_alert_div", "#profile_alert_div span", "Пароли не совпадают.", 4000);
        $("#repass_reg_input").focus().select();
        return;
    }

    var url_string = base_app_url + "/User?cmd=save_profile&login=" + urlEncode(login) + "&password=" + urlEncode(pass) + "&email=" + urlEncode(email);
    $.ajax({
        async:true,
        cache:false,
        url:url_string,
        context:document.body,
        dataTypeString:"xml",
        success:function (xml) {
            var code = $(xml).find("root").find("code").text();
            if (code == "100000")// success
            {
                showAlert("#profile_alert_div", "#profile_alert_div span", "Данные успешно сохранены", 3000);
            }
            else {
                showAlert("#profile_alert_div", "#profile_alert_div span", "Не удалось обновить данные. Пользователь с таким телефоном уже существует", 3000);
            }
        }
    });


}
function initTimePicker() {
    /*<div class="time">
     <div class="from">13:00</div>
     <div class="to">23:00</div>
     <div class="padding">
     <div class="bg-1">
     <div class="bg-2"></div>
     <div class="slider_l">
     <div class="slider corners_10 shadow_emboss"></div>
     </div>
     <div class="slider_r">
     <div class="slider corners_10 shadow_emboss"></div>
     </div>
     </div>
     </div>
     </div>*/
    var mini_step = 100.0 / 48.0;
    var timePickerWidth = $(".bg-1").width();
    var mouse_start_pos = 0;

    $(".slider_l").mousedown(function (e) {
        left_grabbed = true;
        mouse_start_pos = e.pageX;
        //document.title = 'left '+mouse_start_pos;
    });
    $(".slider_r").mousedown(function (e) {
        right_grabbed = true;
        mouse_start_pos = e.pageX;
        //document.title = 'right '+mouse_start_pos;
    });
    $("#sliding_bg").mousemove(function (e) {
        if (left_grabbed) {
            //var delta = $(".slider_l").attr("curr_delta");
            timePickerWidth = $("#sliding_bg").width();
            var x = e.pageX;
            var delta = l_delta * 1 + Math.round(100 * (x - mouse_start_pos) / timePickerWidth);
            if (delta > 90) {
                delta = 90;
            }
            if (delta < 0) {
                delta = 0;
            }
            delta = Math.round(delta / mini_step);
            delta = delta * mini_step;
            //document.title = (100-(r_delta*1+delta*1));
            if ((100 - (r_delta * 1 + delta * 1)) >= (mini_step * 6)) {
                $(".slider_l").css("left", delta + "%");
                $(".bg-2").css("left", delta + "%");
                $(".slider_l").attr("c_delta", delta);

                //var from_hour = Math.floor(delta/10)+13;
                //document.title = delta/mini_step;

                var from_hour = Math.floor(delta / (mini_step * 2.0));
                var from_minutes = "30";
                if (delta / mini_step % 2 == 0) {
                    from_minutes = "00";
                }
                $("#from_hour").html(from_hour);
                $("#from_minutes").html(from_minutes);
            }
        }
        if (right_grabbed) {

            timePickerWidth = $("#sliding_bg").width();
            var x = e.pageX;

            var delta = r_delta * 1 + Math.round(100 * (mouse_start_pos - x) / timePickerWidth);

            if (delta > 90) {
                delta = 90;
            }
            if (delta < 0) {
                delta = 0;
            }
            delta = Math.round(delta / mini_step);
            delta = delta * mini_step;
            //document.title = (100-(l_delta*1+delta*1));
            if ((100 - (l_delta * 1 + delta * 1)) >= (mini_step * 6)) {
                $(".slider_r").css("right", delta + "%");
                $(".bg-2").css("right", delta + "%");
                $(".slider_r").attr("c_delta", delta);

                //var to_hour = 23-(Math.ceil(delta/10));
                var to_hour = 24 - (Math.ceil(delta / (mini_step * 2)));
                var to_minutes = "00";
                if (delta / mini_step % 2 != 0) {
                    to_minutes = "30";
                }
                $("#to_hour").html(to_hour);
                $("#to_minutes").html(to_minutes);
            }
        }
    });
    $("body").mouseup(function () {
        left_grabbed = false;
        right_grabbed = false;
        l_delta = $(".slider_l").attr("c_delta");
        if (l_delta == null) {
            l_delta = 0;
        }
        r_delta = $(".slider_r").attr("c_delta");
        if (r_delta == null) {
            r_delta = 0;
        }
    });
}