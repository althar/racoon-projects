$(document).ready(function()
{
    $("body").hide();
    $(".search").hide();
    init();
    if(admin_authorization()!=true)
    {
        window.location="../admin/admin_auth.html";
    }
    $("#show_butt").click(function(){
        loadStatistics();
    });
    loadStatistics();
    $("#from").mask("9999-99-99");
    $("#to").mask("9999-99-99");
    $("body").show();
});
function loadStatistics()
{
    var from = $("#from").val();
    var to = $("#to").val();
    var url_string = base_app_url+"/Admin?cmd=get_statistics&from="+from+"&to="+to;

    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml)
        {
            var code = $("root > code",xml).text();
            //alert("auth code: "+code);
            if(code=="100000")// success
            {
                var reg_count = $("root > reg_count",xml).text();
                var orders_sum = $("root > orders_sum",xml).text();
                var orders_count = $("root > orders_count",xml).text();
                var cards_sum = $("root > cards_sum",xml).text();
                var cards_count = $("root > cards_count",xml).text();
                if(cards_sum=="")
                {
                    cards_sum = "0";
                }
                if(orders_sum=="")
                {
                    orders_sum = "0";
                }
                $("#reg_count").html(reg_count);
                $("#cards_sum").html(cards_sum+" руб.");
                $("#cards_count").html(cards_count);
                $("#orders_sum").html(orders_sum+" руб.");
                $("#orders_count").html(orders_count);
            }
            else
            {
                alert("Server error");
            }
        }
    });
}