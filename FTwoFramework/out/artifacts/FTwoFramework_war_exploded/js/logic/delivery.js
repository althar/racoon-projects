$(document).ready(function()
    {
        $("body").hide();
        init();
        this.title = "Заказы";
        processAnonimous();
        initAddMoneyForm();
        initLogout();
        loadMoney();
        loadOrders();
        $("body").show();
    });
function loadOrders()
{
    var url_string = base_app_url+"/User?cmd=get_orders";

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $("root code",xml).text();
            if(code=="100000")// success
            {
                var index = 1;
                var current_order = null;
                var orders_html = "<div class=\"th_delivery \"><div class=\"number\">Номер заказа</div><div class=\"status\">Статус доставки</div></div>";
                while((current_order = $("root data OrderDetails item_"+index,xml)).text()!="")
                {
                    var number = $(current_order).find("Number").text();
                    var state = $(current_order).find("State").text();
                    orders_html+="<div class=\"td_delivery\"><div class=\"number\">"
                    +number
                    +"</div><div class=\"status\">"
                    +state
                    +"</div></div>";
                    index++;
                }
                $("#orders_table").html(orders_html);
                if(index==1)
                {
                    $("#no-orders").show();
                }
                else
                {
                    $("#no-orders").hide();
                }
            }
            else if(code=="-88")// Money!!!
            {
                customAlert("Не достаточно денег",4000);
            }
            else if(code=="-4")// unauth...
            {
                window.location = "index.html";
            }
            else
            {
                customAlert("Не удалось загрузить заказы...",4000);
            }
        }
    });
}
