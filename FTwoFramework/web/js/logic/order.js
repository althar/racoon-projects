var area_names = new Array();
var area_ids = new Array();

$(document).ready(function()
{
	$("body").hide();
        init();
	this.title = "Заказ";
	processAnonimous();
	initLogout();
	loadMoney();
	initAddMoneyForm();
        initControls();
	loadStep1();
	$("body").show();
});
function showStep1()
{
	$("#step_1").show();
	$("#step_2").hide();
	$("#step_3").hide();
	$("#step_4").hide();
}
function showStep2()
{
	$("#step_1").show();
	$("#step_2").show();
	$("#step_3").hide();
	$("#step_4").hide();
}
function showStep3()
{
	$("#step_1").show();
	$("#step_2").show();
	$("#step_3").show();
	$("#step_4").hide();
}
function showStep4()
{
	$("#step_1").show();
	$("#step_2").show();
	$("#step_3").show();
	$("#step_4").show();
}
function loadStep1()
{
    loader(true);
	var url_string = base_app_url+"/Order?cmd=order_start";

	$.ajax({
		async:true,
		cache:false,
		url: url_string,
		context: document.body,
		dataTypeString:"xml",
		success: function(xml){
			var code = $("root > Status",xml).text();
			//alert("!");
			if(code=="2")// success
			{
				var index = 1;
				var current_item = null;
				area_names[0] = new Array();
				area_names[1] = new Array();
				area_names[2] = new Array();

				area_ids[0] = new Array();
				area_ids[1] = new Array();
				area_ids[2] = new Array();
				while((current_item = $("root AreaGroupCollection item_1 item_"+index,xml)).text()!="")
				{
					var itt = $("root > AreaGroupCollection > item_1 > Name",xml);
					if($("Name",current_item).text()!="")
					{
						if($(itt).text().indexOf("Москва") != -1)
						{
							//alert("Москва: "+$("Name",current_item).text());
							area_names[0][index-1] = $("Name",current_item).text();
							area_ids[0][index-1] = $("AreaId",current_item).text();
						}
						if($(itt).text().indexOf("Санкт-Петербург") != -1)
						{
							//alert("Питер: "+$("Name",current_item).text());
							area_names[1][index-1] = $("Name",current_item).text();
							area_ids[1][index-1] = $("AreaId",current_item).text();
						}
						if($(itt).text().indexOf("Россия") != -1)
						{
							//alert("Рашка: "+$("Name",current_item).text());
							area_names[2][index-1] = $("Name",current_item).text();
							area_ids[2][index-1] = $("AreaId",current_item).text();
						}
					}
			
					index++;
				}

				index = 1;
				current_item = null;
				while((current_item = $("root AreaGroupCollection item_2 item_"+index,xml)).text()!="")
				{
					itt = $("root > AreaGroupCollection > item_2 > Name",xml);
					if($("Name",current_item).text()!="")
					{
						if($(itt).text().indexOf("Москва") != -1)
						{
							//alert("Москва: "+$("Name",current_item).text());
							area_names[0][index-1] = $("Name",current_item).text();
							area_ids[0][index-1] = $("AreaId",current_item).text();
						}
						if($(itt).text().indexOf("Санкт-Петербург") != -1)
						{
							//alert("Питер: "+$("Name",current_item).text());
							area_names[1][index-1] = $("Name",current_item).text();
							area_ids[1][index-1] = $("AreaId",current_item).text();
						}
						if($(itt).text().indexOf("Россия") != -1)
						{
							//alert("Рашка: "+$("Name",current_item).text());
							area_names[2][index-1] = $("Name",current_item).text();
							area_ids[2][index-1] = $("AreaId",current_item).text();
						}
					}
					index++;
				}

				index = 1;
				current_item = null;
				while((current_item = $("root AreaGroupCollection item_3 item_"+index,xml)).text()!="")
				{
					itt = $("root > AreaGroupCollection > item_3 > Name",xml);

					if($("Name",current_item).text()!="")
					{
						if($(itt).text().indexOf("Москва") != -1)
						{
							area_names[0][index-1] = $("Name",current_item).text();
							area_ids[0][index-1] = $("AreaId",current_item).text();
						}
						if($(itt).text().indexOf("Санкт-Петербург") != -1)
						{
							area_names[1][index-1] = $("Name",current_item).text();
							area_ids[1][index-1] = $("AreaId",current_item).text();
						}
						if($(itt).text().indexOf("Россия") != -1)
						{
							area_names[2][index-1] = $("Name",current_item).text();
							area_ids[2][index-1] = $("AreaId",current_item).text();
						}
					}
					index++;
				}
				$("#select").html("<option value=\"1\">Москва</option><option value=\"2\">Питер</option><option value=\"3\">Россия</option>");
				showStep1();

				$("#select").unbind("change");
				$("#select").change(function(){
					var current_area_id = $("#select").val();
					loadStep2(current_area_id);
				});
				$("#select").change();
			}
			else
			{
				window.location = "index.html";
			}
		}
	});
}
function loadStep2(area_id)
{
	var inner_html = "";
	for(i=0; i<area_names[area_id-1].length; i++)
	{
		inner_html+="<option value=\""+area_ids[area_id-1][i]+"\">"+area_names[area_id-1][i]+"</option>";
	}
	$("#select2").html(inner_html);
	showStep2();

	$("#select2").unbind("change");
	$("#select2").change(function(){
		var current_area_id = $("#select2").val();
		loadStep3(current_area_id);
	});
	$("#select2").change();
}
function loadStep3(current_area_id)
{
    loader(true);
	var url_string = base_app_url+"/Order?cmd=get_delivery_variants&area_id="+current_area_id;
	//$("body").append(url_string);
	$.ajax({
		async:true,
		cache:false,
		url: url_string,
		context: document.body,
		dataTypeString:"xml",
		success: function(xml){
			var code = $("root Status",xml).text();
			if(code=="2")// success
			{
				var group_1 = $("root > DeliveryModel > DeliveryGroups > item_1",xml);
				var group_2 = $("root > DeliveryModel > DeliveryGroups > item_2",xml);

				var delivery_type_html = "";
				if($("Delivery DeliveryVariantGroupName",group_1).first().text() == "Курьерская"||$("Delivery DeliveryVariantGroupName",group_1).first().text() == "Почта")
				{
					var index = 1;
					var current_item = null;
					while((current_item = $("Delivery item_"+index,group_1)).text() != "")
					{
						var name = $("Name",current_item).text();
						var id = $("DeliveryVariantId",current_item).text();
						var goods_sum = $("ItemSumm",current_item).text();
						var delivery_sum = $("DeliverySumm",current_item).text();
						var total_sum = $("FullOrderSumm",current_item).text();
						delivery_type_html+="<option value=\""
						+id
						+"\" goods_sum=\""
						+goods_sum
						+"\" delivery_sum=\""
						+delivery_sum
						+"\" total_sum=\""
						+total_sum
						+"\">"
						+name
						+"</option>"
							
						index++;
					}
				}
				if($("Delivery DeliveryVariantGroupName",group_2).first().text() == "Курьерская"||$("Delivery DeliveryVariantGroupName",group_2).first().text() == "Почта")
				{
					index = 1;
					current_item = null;
					while((current_item = $("Delivery item_"+index,group_2)).text() != "")
					{
						name = $("Name",current_item).text();
						id = $("DeliveryVariantId",current_item).text();
						goods_sum = $("ItemSumm",current_item).text();
						delivery_sum = $("DeliverySumm",current_item).text();
						total_sum = $("FullOrderSumm",current_item).text();
						delivery_type_html+="<option value=\""
						+id
						+"\" goods_sum=\""
						+goods_sum
						+"\" delivery_sum=\""
						+delivery_sum
						+"\" total_sum=\""
						+total_sum
						+"\">"
						+name
						+"</option>"

						index++;
					}
				}
				$("#select3").html(delivery_type_html);
				showStep3();

				$("#select3").unbind("change");
				$("#select3").change(function(){
					var delivery_variant_id = $("#select3").val();
					loadStep4(delivery_variant_id);
				});
				$("#select3").change();
			}
			else
			{
				customAlert($("root",xml).text(),4000);
				window.location = "index.html";
			}
            loader(false);
		}
	});
}
function loadStep4()
{
	var goods_price = $("#select3 option:selected").attr("goods_sum");
	var delivery_price = $("#select3 option:selected").attr("delivery_sum");
	var total_price = $("#select3 option:selected").attr("total_sum");
	$("#goods_price").html(goods_price);
	$("#delivery_price").html(delivery_price);
	$("#total_price").html(total_price);
	showStep4();

	$("#save_butt").unbind("click");
	$("#save_butt").click(function(){
		saveOrder();
	});
}
function saveOrder()
{
    loader(true);
	var zip = $("#zip").val();
	var country = $("#country").val();
	var region = $("#region").val();
	var area = $("#area").val();
	var city = $("#city").val();
	var adressee = $("#adressee").val();
	var address = $("#address").val();
	var phone = $("#phone").val();
	var name = $("#name").val();
	var second_name = $("#second_name").val();
	var delivery_variant_id = $("#select3").val();
	var total_price = $("#select3 option:selected").attr("total_sum");
	if(!checkOrderData())
	{
		return;
	}
	var url_string = base_app_url+"/Order?cmd=save_order&delivery_variant_id="+delivery_variant_id;
	var params = "&zip="+zip
	+"&zip="+zip
	+"&country="+country
	+"&region="+region
	+"&district=district"
	+"&area="+area
	+"&city="+city
	+"&addressee="+adressee
	+"&address_tail="+address
	+"&metro_id=8"
	+"&phone="+phone
	+"&first_name="+name
	+"&middle_name="+second_name
	+"&total_price="+total_price
	+"&last_name=смит";
	url_string+=params;
	
	$.ajax({
		async:false,
		cache:false,
		url: url_string,
		context: document.body,
		dataTypeString:"xml",
		success: function(xml){
			var code = $(xml).find("root").find("code").text();
			code = code+"";
            //alert("code:"+code);
            loader(false);
			if(code=="100000")// success
			{
				var order_id = $("root data order_number",xml).text();
				window.location = "check.html?order_id="+order_id+"&price="+$("#total_price").html();
            }
			else if(code=="-88")// Money!!!
            {
				customAlert("Не достаточно денег",4000);
			}
			else if(code=="-4")// unauth...
			{
				window.location = "index.html";
			}
			else
			{
				customAlert("Не удалось создать заказ. Вы ввели все данные корректно?",4000);
			}
    	}
	});
}
function checkOrderData(zip,country,region,area,city,adressee,address,phone,name,second_name)
{
	return true;
}
function initControls()
{
    initControlTextBox("#zip","Например: 298568","color:#999;","Например: 298568","",13,null);
    initControlTextBox("#country","Например: Россия","color:#999;","Например: Россия","",13,null);
    initControlTextBox("#region","Регион","color:#999;","Регион","",13,null);
    initControlTextBox("#area","Например: Московская","color:#999;","Например: Московская","",13,null);
    initControlTextBox("#city","Например: Москва","color:#999;","Например: Москва","",13,null);
    initControlTextBox("#adressee","Тот, кому адресован товар","color:#999;","Тот, кому адресован товар","",13,null);
    initControlTextBox("#address","Улица, дом, квартира","color:#999;","Улица, дом, квартира","",13,null);
    initControlTextBox("#phone","Телефон получателя","color:#999;","Телефон получателя","",13,null);
    initControlTextBox("#name","Имя получателя","color:#999;","Имя получателя","",13,null);
    initControlTextBox("#second_name","Фамилия получателя","color:#999;","Фамилия получателя","",13,null);
}