$(document).ready(function(){
    if($("#delivery-select option:selected").lenght==null)
    {
        $("#delivery-select option").first().attr("selected",true);
    }
    $("#date-input").mask("99.99.9999");
    $("#date-input").keydown(function(e){
        e.preventDefault();
    });
    $(".form-actions button").click(function(){
        var address = $("#address-input").val();
        var date = $("#date-input").val();
        var comment = $("#comment-input").val();
        var delivery_variant = $("#delivery-select option:selected").val();
        if(address.length<10)
        {
            $("#alert").html("Укажите верный адрес");
            $("#alert").fadeIn(500).delay(2000).fadeOut(500);
            return;
        }
        else
        {
            $.ajax(
                {
                    url:"/Корзина/Сохранить?address="+address+"&date="+date+"&comment="+comment+"&delivery_variant="+delivery_variant,
                    context: document.body,
                    async: true,
                    success:function(data){
                        if(data=="true")// Registered
                        {
                            window.location = "/Корзина/Оформление";
                        }
                        else
                        {
                            $("#alert").html("Введите все данные.");
                            $("#alert").fadeIn(500).delay(2000).fadeOut(500);
                        }
                    },
                    failure:function(data){
                    }
                }
            );
        }
    });
});