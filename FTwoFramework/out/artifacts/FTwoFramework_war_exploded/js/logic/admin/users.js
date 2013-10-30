$(document).ready(function()
{
    $("body").hide();
    $(".search").hide();
    init();
    if(admin_authorization()!=true)
    {
        window.location="admin/admin_auth.html";
    }
    loadUsers(1);
    $("body").show();
});
function loadUsers(page)
{
    var url_string = base_app_url+"/Admin?cmd=get_users&page="+page+"&page_size=15";

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
                var id_html = "<tr><th class=\"th\">ID</th></tr>";
                var login_html = "<tr><th class=\"th\">Login</th></tr>";
                var email_html = "<tr><th class=\"th\">Email</th></tr>";
                var created_html = "<tr><th class=\"th\">Created</th></tr>";
                var password_html = "<tr><th class=\"th\">Password</th></tr>";
                while((current_item=$("root > table > item_"+index,xml)).text()!="")
                {
                    var id = $("id",current_item).text();
                    var login = $("login",current_item).text();
                    var email = $("email",current_item).text();
                    var created = $("created",current_item).text();
                    var password = $("password",current_item).text();
                    id_html+="<tr><td class=\"td\">"+id+"</td></tr>";
                    login_html+="<tr><td class=\"td\">"+login+"</td></tr>";
                    email_html+="<tr><td class=\"td\">"+email+"</td></tr>";
                    created_html+="<tr><td class=\"td\">"+created+"</td></tr>";
                    password_html+="<tr><td class=\"td\">"+password+"</td></tr>";
                    index ++;
                }
                $("#user_id").html(id_html);
                $("#user_login").html(login_html);
                $("#user_email").html(email_html);
                $("#user_created").html(created_html);
                $("#user_password").html(password_html);

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
                    loadUsers(p);
                });
            }
            else
            {
                alert("Server error");
            }
        }
    });
}