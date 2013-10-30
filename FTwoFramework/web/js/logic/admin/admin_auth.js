$(document).ready(function()
{
    $("body").hide();
    init();
    initAuth();
    $("body").show();
});
function initAuth()
{
     $("#login").click(function(){
         var login = $("#login_input").val();
         var pass = $("#pass_input").val();
         var auth_res = admin_authentication(login,pass);
         if(auth_res=="1")// ok
         {
             window.location = "/admin/users.html";
         }
     });
}