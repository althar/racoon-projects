$(document).ready(function()
{
	$("body").hide();
        init();
	this.title = "Товар";
	authorization();
	initAddMoneyForm();
	initAuthorizationForm();
	initRegistrationForm();
	loadMoney();
	initLogout();
	initLinks();
	loadItem();
	initAddToCart();
	$("body").show();
});

function loadItem()
{
	var item_id = getURLParameter("id");
	var img_path = getURLParameter("img_path");
	//alert(item_id);
	var url_string = base_app_url+"/Catalogue?cmd=get_item&item_id="+item_id;
	//$("body").append(url_string);
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
				var item_type = $("root Item ItemType",xml).text();
				var item_name = $("root Item Name",xml).text();
				var item_description = $("root Item Annotation",xml).text();
				item_description = cleanString(item_description);
				item_type = cleanString(item_type);
				item_name = cleanString(item_name);
				var item_price = $("root Item DiscountPrice",xml).text();
				$("#item_pic").attr("src",img_path);
				$("#item_name").html(item_name);
				$("#item_description").html(item_description);
				$("#item_type").html(item_type);
				if(!authorization())
				{
					$("#item_price").html("толька для зарегистрированных пользователей.");
				}
				else
				{
					$("#item_price").html(item_price+" бонусов");
				}
				
				$("#add_butt").attr("item_id",item_id);
			}
			else
			{
				alert("Товара с таким ID нет...");
				window.location = "index.html";
			}
		}
	});
}
function initAddToCart()
{
	$("#add_butt").click(function(){
		if(!authorization())
		{
			customAlert("Вы должны зарегистрироваться.",4000);
			window.location = "index.html?cmd=reg";
			return;
		}
		var item_id = $("#add_butt").attr("item_id");
		var url_string = base_app_url+"/Order?cmd=add_cart&item="+item_id;

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
					customAlert("Не удалось добавить продукт в корзину",4000);
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
