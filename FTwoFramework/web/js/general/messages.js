function formatPrice(value)
{
    var head = "";
    var body = "";
    var tail = "";
    var val_str = new String(value)+"";
    var len = val_str.length*1;
    if(len>6)
    {
        head = val_str.substr(0, len-6);
        body = val_str.substr(len-6, 3);
        tail = val_str.substr(len-3, 3);
        return head+"&thinsp;"+body+"&thinsp;"+tail+" &#8399";
    }
    else if(len>3)
    {
        head = val_str.substr(0, len-3);
        tail = val_str.substr(len-3, 3);
        return head+"&thinsp;"+tail+" &#8399";
    }
    else
    {
        return (new String(value))+" &#8399";
    }
}
function customAlert(text,duration)
{
    var opened = $("#alert").attr("opened");
    if(opened!="true")
    {
        $("#alert").attr("opened","true");
        $("#alert-text").html(text);
        $("#replenishment-overlay").show();
        $("#alert").fadeIn(300).delay(duration).fadeOut(300,function(){
            $("#replenishment-overlay").hide();
            $("#alert").attr("opened","false");
        });
    }
}

var time_to_show_messgaebox = 0;
function showRegistrationMessage(type,text,period)
{
    $("#error_label").html(text);
}
function showMessage(type,text,period)
{
    var messagebox_html = "<div class=\"overlay\" id=\"message_box\">";
    messagebox_html += "<div class=\"modal_window window_shadow\"  id=\"message_box_inner\"><div class=\"padding_20\">";
    messagebox_html += "<span class=\"font_14_normal\">";
    messagebox_html += text;
    messagebox_html += "</span>";
    messagebox_html += "</div></div></div>";
    $("#message_box").remove();
    $("body").append(messagebox_html);
    $("#message_box_inner").hide();
    $("#message_box").show();

    $("#message_box_inner").fadeIn(800);
    time_to_show_messgaebox = period;
    $(document).everyTime(1000,"message_box_timer",function()
    {
        time_to_show_messgaebox = time_to_show_messgaebox-1000;
        if(time_to_show_messgaebox<=0)
        {
            $(document).stopTime("message_box_timer");
            $("#message_box").remove();
        }
    });
}