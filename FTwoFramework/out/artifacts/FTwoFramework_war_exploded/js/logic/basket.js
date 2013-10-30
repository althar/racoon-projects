var full_sum = 0;
$(document).ready(function()
{
    $("body").hide();
    init();
    this.title = "Корзина";
    processAnonimous();
    initAddMoneyForm();
    initLinks();
    
    loadMoney();
    
    loadBasket();
    
    initClearBasket();
    initMakeOrder();
    initLogout();
    $("body").show();
});
function loadBasket()
{
    if(!authorization())
    {
        window.location = "index.html";
        return;
    }
    var url_string = base_app_url+"/Order?cmd=get_cart";

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("Status").text();
            //alert("auth code: "+code);
            if(code=="2")// success
            {
                var table_html = "";
                full_sum = $("root CartSummary FullSum",xml).text();
				
                if(full_sum=="")
                {
                    full_sum = 0;
                }
                var index = 1;
                var current_item = null;
                var items_string = "";
                while((current_item = $("root CartItems item_"+index,xml)).text()!="")
                {
                                    
                    var count = $(current_item).find("Quantity").text();
                    var name = $(current_item).find("Name").text();
                    var price = $(current_item).find("ClientPrice").text();
                    var item_id = $(current_item).find("ItemId").text();
                    var path = $(current_item).find("Path").text();
                    path = path.replace("\\/small\\", "").replace(".gif", ".jpg");
                    table_html+="<div class=\"td\"><div class=\"name\">"
                    +"<a href=\"product.html?id="+item_id+"&img_path="+path+"\">"
                    +name
                    +"</a></div><div class=\"amount\">"
                    +count
                    +"</div><div class=\"cost\">"
                    +price
                    +" бонусов</div>"
                    +"<div remove-item=\"true\" item-id=\""+item_id+"\">Удалить</div>"
                    +"</div>";
                    
					
                    if(index!=1)
                    {
                        items_string+=",";
                    }
                    items_string+=item_id;
                    index++;
                    
                }
                
                $("#basket_items").html(table_html);
                $("#basket_items").attr("basket_items_str",items_string);
                $("#total_price").html(full_sum+" бонусов");
                $("div[remove-item=\"true\"]").unbind("click");
                $("div[remove-item=\"true\"]").click(function(){
                    var item_id = $(this).attr("item-id");
                    removeItem(item_id);
                    loadBasket();
                });
                if(index==1)
                {
                    $("#no-orders").show();
                }
                else
                {
                    $("#no-orders").hide();
                }
            }
            else
            {
                window.location = "index.html";
            }
        }
    });
}
function removeItem(item_id)
{
    var url_string = base_app_url+"/Order?cmd=clear_cart&items="+item_id;

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("Status").text();
            //alert("auth code: "+code);
            if(code=="2")// success
            {
                
            }
            else
            {
                
            }
        }
    });
}
function initMakeOrder()
{
    $("#make_order").click(function(){
        if(full_sum>$("#money").html()*1.0)
        {
            customAlert("Недостаточно баллов для оформления заказа.",4000);
            return;
        }
        else if($("#basket_items").html()=="")
        {
            customAlert("Корзина пуста.");
            return;
        }
        else
        {
            window.location = "order.html";
        }
    });
}
function initClearBasket()
{
    $("#clear_cart").click(function(){
        var items_str = $("#basket_items").attr("basket_items_str");
        var url_string = base_app_url+"/Order?cmd=clear_cart&items="+items_str;

        $.ajax({
            async:false,
            cache:false,
            url: url_string,
            context: document.body,
            dataTypeString:"xml",
            success: function(xml){
                var code = $(xml).find("root").find("Status").text();
                //alert("auth code: "+code);
                if(code=="2")// success
                {
                    window.location = "basket.html";
                }
                else
                {
                    window.location = "index.html";
                }
            }
        });
    });
}
function initLinks()
{
    $("#logout_link").click(function(){
        logout();
        window.location = "index.html";
    });
}