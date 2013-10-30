$(document).ready(function()
{
    $("body").hide();
    $(".search").hide();
    init();
    if(admin_authorization()!=true)
    {
        window.location="../admin/admin_auth.html";
    }
    loadCards(1);
    $("body").show();
});
function loadCards(page)
{
    var url_string = base_app_url+"/Admin?cmd=get_cards&page="+page+"&page_size=5";

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
                var card_id_html = "<tr><th class=\"th\">ID</th></tr>";
                var card_code_html = "<tr><th class=\"th\">Code</th></tr>";
                var card_value_html = "<tr><th class=\"th\">Value</th></tr>";
                var card_user_id = "<tr><th class=\"th\">User id</th></tr>";
                var card_activation_time = "<tr><th class=\"th\">Activation time</th></tr>";
                while((current_item=$("root > table > item_"+index,xml)).text()!="")
                {
                    var id = $("id",current_item).text();
                    var code = $("code",current_item).text();
                    var value = $("value",current_item).text();
                    var activation_time = $("activation_time",current_item).text();
                    var activator_user_id = $("activator_user_id",current_item).text();
                    card_id_html+="<tr><td class=\"td\">"+id+"</td></tr>";
                    card_code_html+="<tr><td class=\"td\">"+code+"</td></tr>";
                    card_value_html+="<tr><td class=\"td\">"+value+"</td></tr>";
                    card_user_id+="<tr><td class=\"td\">"+activator_user_id+"</td></tr>";
                    card_activation_time+="<tr><td class=\"td\">"+activation_time+"</td></tr>";
                    index ++;
                }
                $("#card_id").html(card_id_html);
                $("#card_code").html(card_code_html);
                $("#card_value").html(card_value_html);
                $("#card_user_id").html(card_user_id);
                $("#card_activation_time").html(card_activation_time);

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
            }
            else
            {
                alert("Server error");
            }
        }
    });
}