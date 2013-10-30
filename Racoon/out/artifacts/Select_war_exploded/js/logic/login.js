$(document).ready(function(){
    $("#phone-input").mask("(999)999-99-99");
    $("#password-recover").click(function(){
        $.ajax(
            {
                url:"/user/password_recover?login="+$("#phone-input").val(),
                context: document.body,
                async: false,
                success:function(data){
                    $(".authorization").html("Ваш пароль выслан вам на телефон.");
                },
                failure:function(data){
                    alert("Сбой на сервере, приносим извинения.");
                }
            }
        );
    });
    $("#phone-input").keyup(function(){
        var phone = $(this).val();
        if(phone.replace("_","").length==14)
        {
            $.ajax(
                {
                    url:"/user/check?login="+phone,
                    context: document.body,
                    async: true,
                    success:function(data){
                        if(data=="true")// Exists
                        {
                            $("#password-recover").show();
                            $(".authorization").show();
                            $(".registration").hide();
                            $("#password-div").show();
                            $(".form-actions").show();
                        }
                        else
                        {
                            $("#password-recover").hide();
                            $(".registration").show();
                            $(".authorization").hide();
                            $("#password-div").show();
                            $(".form-actions").show();
                        }
                    },
                    failure:function(data){
                    }
                }
            );
        }
        else
        {
            $("#password-div").hide();
            $(".form-actions").hide();
        }
    });
    $(".form-actions button").click(function(){
        var password = $("#password-input").val();
        var login = $("#phone-input").val();
        if(password.length<3)
        {
            $("#password-alert").html("Пароль должен быть минимум 3 символа");
            $("#password-alert").fadeIn(500).delay(2000).fadeOut(500);
            return;
        }
        else
        {
            $.ajax(
                {
                    url:"/Корзина/РегистрацияАвторизация?login="+login+"&password="+password,
                    context: document.body,
                    async: true,
                    success:function(data){
                        if(data=="true")// Registered
                        {
                            window.location = "/Корзина/Доставка";
                        }
                        else
                        {
                            $("#password-alert").html("Не верный пароль.");
                            $("#password-alert").fadeIn(500).delay(2000).fadeOut(500);
                        }
                    },
                    failure:function(data){
                    }
                }
            );
        }
    });
});
