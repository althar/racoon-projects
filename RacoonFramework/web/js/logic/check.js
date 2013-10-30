$(document).ready(function()
{
	$("body").hide();
        init();
	this.title = "Квитанция";
	processAnonimous();
	initAddMoneyForm();
	loadMoney();
	initLogout();
	loadCheck();
	$("body").show();
});
function loadCheck()
{
	var order_id = getURLParameter("order_id");
	var order_price = getURLParameter("price");
	$("#order_id").html(order_id);
	$("#price").html(order_price+" бонусов");
}

