var base_app_url = "http://85.159.44.59:8085";
var base_port = "8080";
var is_registration = true;
var current_number = "";
function init()
{
    base_app_url = "http://"+document.domain+":"+base_port;
}
function initDinamicContent()
{
    var content_div_ids = "";
    $("[dinamic_content=\"true\"]").each(function(){
        content_div_ids += $(this).attr("id")+"|";
    });
    var url_string = base_app_url+"/Content?cmd=get_content&content="+content_div_ids;
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code=="100000")// success
            {
                var current = "";
                var index = 1;
                while((current = $("root > data > content > item_"+index,xml).text()!=""))
                {
                    var name = $("name",current).text();
                    var value = $("value",current).text();

                }
            }
            else
            {

        }
        }
    });
}

function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
        );
}
function loadMoney()
{
    var url_string = base_app_url+"/User?cmd=get_money_information";
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code=="100000")// success
            {
                var money = $("root data ClientAccountEntryInformationForWeb Accessible",xml).text();
                $("#money").html(money);
            }
            else
            {
				
        }
        }
    });
}
function authorization()
{
    var url_string = base_app_url+"/Authentication?cmd=authorization";
    var auth_success = false;
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code=="2")// success
            {
                auth_success = true;
                $("#authorization_butt").hide();
                $("#logout_butt").show();
                $("#login").html($(xml).find("root data login").text());
                $("#menu_for_unauth").hide();
                $(".help").hide();
                $("#menu_for_auth").show();
                $(".subscriber").show();
                var url = window.location+"";
                if((url).indexOf("index.html") !== -1)
                {
                    showPrices();
                }
                loadMoney();
            }
            else if(code=="-4")
            {
                auth_success = false;
                $("#menu_for_auth").hide();
                $(".help").show();
                $("#menu_for_unauth").show();
                $("#logout_butt").hide();
                $("#authorization_butt").show();
            }
            else
            {
        //alert("authorization = fail:"+code);
        }
        }
    });
    return auth_success;
}
function admin_authorization()
{
    var url_string = base_app_url+"/Authentication?cmd=authorization&admin=true";
    var auth_success = false;
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code=="2")// success
            {
                auth_success = true;
                $("#authorization_butt").hide();
                $("#logout_butt").show();
                $("#login").html($(xml).find("root data login").text());
                $("#menu_for_unauth").hide();
                $(".help").hide();
                $("#menu_for_auth").show();
                $(".subscriber").show();
                var url = window.location+"";
                if((url).indexOf("index.html") !== -1)
                {
                    showPrices();
                }
                loadMoney();
            }
            else if(code=="-4")
            {
                auth_success = false;
                $("#menu_for_auth").hide();
                $(".help").show();
                $("#menu_for_unauth").show();
                $("#logout_butt").hide();
                $("#authorization_butt").show();
            }
            else
            {
                //alert("authorization = fail:"+code);
            }
        }
    });
    return auth_success;
}
function authentication(login,password)
{
    var url_string = base_app_url+"/Authentication?cmd=authentication&login="+login+"&password="+password;
    var result = false;
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            result = code;
            if(code==="1")// success
            {
                current_number = login;
                $("#registration").hide();
                var opened = $("#alert").attr("opened");
                if(opened!="true")
                {
                    $("#replenishment-overlay").hide();
                }
                authorization();
            }
            if(code==="-1")
            {
                showRegistrationMessage("","Не задан номер телефона",1000);
            }
            if(code==="-2")
            {
                showRegistrationMessage("","Не задан пароль",1000);
            }
            if(code==="-3")
            {
                showRegistrationMessage("","Неверный логин или пароль",1000);
            }
        }
    });
    return result;
}
function admin_authentication(login,password)
{
    var url_string = base_app_url+"/Authentication?cmd=authentication&login="+login+"&password="+password+"&admin=true";
    var result = false;
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            result = code;
            if(code==="1")// success
            {
                current_number = login;
                $("#registration").hide();
                var opened = $("#alert").attr("opened");
                if(opened!="true")
                {
                    $("#replenishment-overlay").hide();
                }
                authorization();
            }
            if(code==="-1")
            {
                showRegistrationMessage("","Не задан номер телефона",1000);
            }
            if(code==="-2")
            {
                showRegistrationMessage("","Не задан пароль",1000);
            }
            if(code==="-3")
            {
                showRegistrationMessage("","Неверный логин или пароль",1000);
            }
        }
    });
    return result;
}
function registration(login,password)
{
    var url_string = base_app_url+"/Registration?cmd=create_registration&login="+login+"&password="+password;
    
    var result = false;
    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code=="1")// success
            {
                $("#registration").hide();
                //customAlert("Вы успешно зарегистрированы",4000);
                var pass = $(xml).find("root data password").text();
                authentication(login,password);
                window.location = "index.html?cmd=ok_reg";
                result = true;
            }
            if(code=="-1")
            {
                showRegistrationMessage("","Ваш номер в черном списке. Регистрация запрещена",1000);
                result = false;
            }
            if(code=="-2")
            {
                showRegistrationMessage("","Пользователь с таким номером уже есть в системе",1000);
                result = false;
            }
            if(code=="-3")
            {
                showRegistrationMessage("","Пользователь с таким номером уже есть в системе. Либо номер некорректен",1000);
                result = false;
            }
        }
    });
    return result;
}
function initLogout()
{
    $("#logout_butt, a[logoutButt=\"true\"]").click(function(){
        //alert("");
        $("body").hide();
        logout();
        window.location = "index.html";
    });
}

function recoverPassword()
{
    var login = $("#edit-pref").attr("value")+$("#edit-phone").attr("value");
    if(!validatePhone(login))
    {
        showRegistrationMessage("error", "Введите верный номер телефона (11 цифр с восьмеркой)", 5000);
        return;
    }
    var url_string = base_app_url+"/Authentication?cmd=recover&phone="+login;

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code=="100000")// success
            {
                $("#closer").click();
                customAlert("Пароль отправлен вам на телефон", 4000);
            }
            else if(code=="-6")
            {
                customAlert("Пользователь с таким номером не зарегистрирован в системе", 4000);
            }
        }
    });
}
function initRegistrationForm()
{
    $("#pass_recover").click(function(){
        recoverPassword();
    });
    
    $("#registration_butt").click(function(){
        $("#pass_recover").hide();
        //$("#edit-pass-wrapper").hide();
        $("#registration_header").html("Регистрация");
        $("#edit-submit").attr("value","Зарегистрироваться");
        $("#edit-submit").attr("src","img/gui/registration-button.png");
        is_registration = true;
        $("#error_label").html("");
        $("#replenishment-overlay").show();
        initControlTextBox("#edit-pref","xxxx","color:#999;","xxxx","",13,null);
        initControlTextBox("#edit-phone","xxxxxxx","color:#999;","xxxxxxx","",13,null);
        $("#registration").show();
        $("#edit-pref").focus();
    });
    $("#closer").click(function(){
        $("#replenishment-overlay").hide();
        $("#registration").hide();
    });
    $("#edit-pref").keyup(function(){
        if($(this).val().length==4)
        {
            $("#edit-phone").focus();
        }
    });
    $("#edit-submit").click(function(){
        if(is_registration)
        {
            var login = $("#edit-pref").attr("value")+$("#edit-phone").attr("value");
            var password = $("#edit-pass").attr("value");
            if(!validatePhone(login))
            {
                showRegistrationMessage("error", "Введите верный номер телефона (11 цифр с восьмеркой)", 5000);
                return;
            }
            if(!validatePassword(password))
            {
                showRegistrationMessage("error", "Пароль должен быть минимум 6 символов", 5000);
                return;
            }
            registration(login, password);
        }
    });
}
function initAuthorizationForm()
{
    $("#authorization_butt").click(function(){
        $("#pass_recover").show();
        //$("#edit-pass-wrapper").show();
        $("#registration_header").html("Вход");
        $("#edit-submit").attr("value","Войти");
        $("#edit-submit").attr("src","img/gui/in-button.png");
        is_registration = false;
        $("#error_label").html("");
        $("#replenishment-overlay").show();
        initControlTextBox("#edit-pref","xxxx","color:#999;","xxxx","",13,null);
        initControlTextBox("#edit-phone","xxxxxxx","color:#999;","xxxxxxx","",13,null);
        $("#registration").show();
        $("#edit-pref").focus();
    });
    $("#closer").click(function(){
        $("#replenishment-overlay").hide();
        $("#registration").hide();
    });
    $("#edit-submit").click(function(){
		
        if(!is_registration)
        {
            var login = $("#edit-pref").attr("value")+$("#edit-phone").attr("value");
            var password = $("#edit-pass").attr("value");
            if(!validatePhone(login))
            {
                showRegistrationMessage("error", "Введите верный номер телефона (10 цифр без восьмерки)", 5000);
                return;
            }
			
            if(!validatePassword(password))
            {
                showRegistrationMessage("error", "Пароль должен быть минимум 6 символов", 5000);
                return;
            }
			
            authentication(login, password);
        }
    });
}
function initAddMoneyForm()
{
    $("#replenishment").hide();
    $("#replenishment-overlay").hide();
    $("#add_money_butt").click(function(){
        $("#warning_div").html("");
        $("#replenishment").show();
        $("#replenishment-overlay").show();
    });
    $("#replenishment_closer").click(function(){
        $("#replenishment").hide();
        $("#replenishment-overlay").hide();
    });
    $("#scratch-submit").click(function(){
        var promo_code = $("#scratch-number").val();
        var url_string = base_app_url+"/User?cmd=activate_card&code="+promo_code;
        $("#replenishment_loader").show();
        $("#warning_div").html("");
        $.ajax({
            async:true,
            cache:false,
            url: url_string,
            context: document.body,
            dataTypeString:"xml",
            success: function(xml){
                var code = $(xml).find("root").find("code").text();
                if(code=="1")// success
                {
                    $("#replenishment").hide();
                    $("#replenishment-overlay").hide();
                    customAlert("Вы успешно активировали карту. Средства скоро поступят на счет.",4000);
                    
                }
                if(code=="-1")
                {
                    $("#warning_div").css("color","red");
                    $("#warning_div").html("Не удалось активировать карту");
                }
                if(code=="-11")
                {
                    $("#warning_div").css("color","red");
                    $("#warning_div").html("Активация карты разрешена не чаще чем раз в 4 секунды. Подождите.");
                }
                if(code=="-3")
                {
                    $("#warning_div").css("color","red");
                    $("#warning_div").html("Пользователь с таким номером уже есть в системе. Либо номер некорректен");
                }
                $("#replenishment_loader").hide();
            }
        });
    });
}
function cleanString(str)
{
    return str.replace(/<\\\/B>/g, "").replace(/<B>/g, "").replace(/\\u000d/g, "").replace(/\\u000a/g, "").replace(/\\/g, "\"");
}
function logout()
{
    var url_string = base_app_url+"/Authentication?cmd=logout";

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            //alert("auth code: "+code);
            if(code=="3")// success
            {

            }
            else if(code=="-5")
            {
        }
        }
    });
    authorization();
    window.location = "index.html";
}
function processAnonimous()
{
    if(!authorization())
    {
        window.location = "index.html";
    }
}

function loader(show)
{
    if(show)
    {
        $("#loader").show();
        $("#replenishment-overlay").show();
    }
    else
    {
        $("#loader").hide();
        //alert($("#registrationddd").css("display")==null);
        if(($("#registration").css("display")=="none"||$("#registration").css("display")==null)
            &&($("#alert").css("display")=="none"||$("#alert").css("display")==null))
        {
            $("#replenishment-overlay").hide();
        }
    }
}