$(document).ready(function()
{
	$("body").hide();
        init();
	this.title = "Помощь";
	authorization();
	initAddMoneyForm();
	initAuthorizationForm();
	initRegistrationForm();
	loadMoney();
	initLogout();
	$("body").show();
});




