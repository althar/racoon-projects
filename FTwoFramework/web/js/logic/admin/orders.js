$(document).ready(function()
{
    $("body").hide();
    $(".search").hide();
    init();
    if(admin_authorization()!=true)
    {
        window.location="../admin/admin_auth.html";
    }
    loadOrders(1);
    $("body").show();
});
function loadOrders(page)
{
    var url_string = base_app_url+"/Admin?cmd=get_orders&page="+page+"&page_size=5";

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $("root > code",xml).text();
            //alert("auth code: "+code);
            if(code=="100000")// success
            {
                var index = 1;
                var current_item = null;
                var order_number_html = "<tr><th class=\"th\">Order number</th></tr>";
                var order_user_id_html = "<tr><th class=\"th\">User id</th></tr>";
                var order_sum_html = "<tr><th class=\"th\">Sum</th></tr>";
                var order_status = "<tr><th class=\"th\">Status</th></tr>";
                var order_show = "<tr><th class=\"th\"></th></tr>";
                while((current_item=$("root > table > item_"+index,xml)).text()!="")
                {
                    var order_number = $("order_number",current_item).text();
                    var user_id = $("user_id",current_item).text();
                    var sum = $("sum",current_item).text();

                    order_number_html+="<tr><td class=\"td\">"+order_number+"</td></tr>";
                    order_user_id_html+="<tr><td class=\"td\">"+user_id+"</td></tr>";
                    order_sum_html+="<tr><td class=\"td\">"+sum+"</td></tr>";
                    order_status+="<tr><td class=\"td\" order_number=\""+order_number+"\">"+""+"</td></tr>";
                    order_show+="<tr><td class=\"td\" ><a href=\"#\" order_number=\""+order_number+"\" user_id=\""+user_id+"\">Показать</a></td></tr>";
                    index ++;
                }
                $("#order_number").html(order_number_html);
                $("#user_id").html(order_user_id_html);
                $("#order_sum").html(order_sum_html);
                $("#order_status").html(order_status);
                $("#order_show").html(order_show);

                // Pager...
                var total_pages = $("root > total_pages",xml).text();
                var pager_html = "";
                for(i=1; i<total_pages*1+1; i++)
                {
                    if(i==page)
                    {
                        pager_html+="<li class=\"active\"><a href=\"#\">"+i+"</a></li>";
                    }
                    else
                    {
                        pager_html+="<li><a href=\"#\">"+i+"</a></li>";
                    }

                }
                $("#pager").html(pager_html);
                $("#pager > li > a").click(function(){
                    var p = $(this).html();
                    loadCards(p);
                });

                // Load order details
                $("#order_show a").click(function(){
                    var order_number = $(this).attr("order_number");
                    var user_id = $(this).attr("user_id");
                    loadOrderDetails(order_number,user_id);
                });
            }
            else
            {
                alert("Server error");
            }
        }
    });
}
function loadOrderDetails(order_number,user_id)
{
    var details = "WOW";
    $("#order_status td[order_number=\""+order_number+"\"]").html("<img src=\"../img/gui/ajax-loader.gif\"/>");

    var url_string = base_app_url+"/Admin?cmd=check_order&order_number="+order_number+"&user_id="+user_id;

    $.ajax({
        async:true,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $("root > code",xml).text();
            //alert("auth code: "+code);
            if(code=="100000")// success
            {
                details = $("root > Order > State ",xml).text();
            }
            else
            {
                details = "Недоступно";
            }
            $("#order_status td[order_number=\""+order_number+"\"]").html(details);
        }
    });

}