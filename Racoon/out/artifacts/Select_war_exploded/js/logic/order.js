$(document).ready(function(){
    $(".make-order").click(function(){
        $(".loader").show();
        $.ajax(
            {
                url:"/Корзина/Оформить",
                context: document.body,
                async: true,
                success:function(data){
                        $(".success").html(data);
                },
                failure:function(data){
                    alert("Сбой на сервере. Приносим свои извинения.");
                }
            }
        );
    });
});
