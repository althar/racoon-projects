$(document).ready(function()
	{
        init();
		initGeneralElements();
		initAuthForm();
		authorization(true);
		loadCategories();
		loadBrands();
		//loadBanner();
		initSearch();
		initNextBrands();
        loadDiscount();
        checkBasket();
        loadContent();
		$("#content_div").show();
	});
function loadCategories()
{
	var url_string = base_app_url+"/Catalogue?cmd=get_categories";
	$.ajax({
		async:false,
		cache:false,
		url: url_string,
		context: document.body,
		dataTypeString:"xml",
		success: function(xml)
		{
			var code = $(xml).find("root").find("code").text();
			if(code=="100000")// success
			{
				var dog = $(xml).find("root").find("data").find("Собака");
				var cat = $(xml).find("root").find("data").find("Кошка");
				var etc = $(xml).find("root").find("data").find("Прочее");

				var dog_html = "<div class=\"title\">Для собак</div>";
				var index = 1;
				var item = null;
				while((item=$(dog).find("item_"+index).text())!="")
				{
					dog_html+="<a href=\"products.html?animal=Собака&category="+item+"\">"+item+"</a>";
					index ++;
				}
				$("#dog_categories").html(dog_html);

				var cat_html = "<div class=\"title\">Для кошек</div>";
				index = 1;
				item = null;
				while((item=$(cat).find("item_"+index).text())!="")
				{
					cat_html+="<a href=\"products.html?animal=Кошка&category="+item+"\">"+item+"</a>";
					index ++;
				}
				$("#cat_categories").html(cat_html);

				var etc_html = "<div class=\"title\">Прочее</div>";
				index = 1;
				item = null;
				while((item=$(etc).find("item_"+index).text())!="")
				{
					etc_html+="<a href=\"products.html?animal=Прочее&category="+item+"\">"+item+"</a>";
					index ++;
				}
				$("#etc_categories").html(etc_html);
			}
			else
			{
				showMessage("", "Серверная ошибка.", 2000);
			}
		}
	});
}
function loadBrands()
{
	main_brands_pics = ["brand-royalcanin.gif","brand-hills.gif","brand-eukanuba.gif","brand-purina.gif","brand-proplan.gif","brand-happydog.gif","brand-iams.gif","brand-ag.gif","brand-acana.gif","brand-orijen.gif"];
	var brands = "";
	for(b=0; b<main_brands.length; b++)
	{
		brands += main_brands[b]+"|";
	}
	var url_string = base_app_url+"/Catalogue?cmd=get_brand_categories&brands="+brands;
	$.ajax({
		async:false,
		cache:false,
		url: url_string,
		context: document.body,
		dataTypeString:"xml",
		success: function(xml)
		{
			var code = $(xml).find("root").find("code").text();
			if(code=="100000")// success
			{
				var items_html = "";
		
				for(i=0; i<main_brands.length; i++)
				{
					var brand = main_brands[i].replace(" ", "_");
					//alert(brand);
					//		<div class="container" id="brand_container">
					//			<div class="item hidden">
					//			    <img src="img/gui/brand_1.gif" />
					//			    <div class="nav_wrap">
					//				<div class="nav home_brands_nav_3col shadow_10 corners_10" id="brand_menu">
					//				    <div class="arrow"><img src="img/gui/brands_nav_arrow.png" alt="" /></div>
					//				    <div class="padding_rl10" >
					//
					//					<div class="col">
					//					    <div class="padding_rl10">
					//						<a href="1">Сухой корм</a>
					//						<a href="2">Влажный корм</a>
					//						<a href="3">Лакомства</a>
					//						<a href="4">Диетическое питание</a>
					//					    </div>
					//					</div>
					//					<div class="col">
					//					    <div class="padding_rl10">
					//						<a href="5">Сухой корм</a>
					//						<a href="6">Влажный корм</a>
					//						<a href="7">Лакомства</a>
					//						<a href="8">Диетическое питание</a>
					//						<a href="9">Наполнители для&nbsp;туалета</a>
					//					    </div>
					//					</div>
					//					<div class="col">
					//					    <div class="padding_rl10">
					//						<a href="5">Сухой корм</a>
					//						<a href="6">Влажный корм</a>
					//						<a href="7">Лакомства</a>
					//						<a href="8">Диетическое питание</a>
					//						<a href="9">Наполнители для&nbsp;туалета</a>
					//					    </div>
					//					</div>
					//				    </div>
					//				</div>
					//			    </div>
					//			</div>
					//			<div class="item"><img src="img/gui/brand_2.gif" /></div>
					//			<div class="item"><img src="img/gui/brand_3.gif" /></div>
					//			<div class="item"><img src="img/gui/brand_4.gif" /></div>
					//			<div class="item"><img src="img/gui/brand_5.gif" /></div>
					//		    </div>

					var brand_html = "";

					var brand_node = $(xml).find("root").find("data").find(brand);
					var cols = 0;

		    

					var brand_dogs = $(brand_node).find("Собака");
					if($(brand_dogs).text()!="")
					{
						category = null;
						cols+=1;
						index = 1;
						brand_html += "<div class=\"col\"><div class=\"padding_rl10\"><img src=\"img/gui/icon_dog.gif\" alt=\"\" />";
						while((category = $(brand_dogs).find("item_"+index).text())!="")
						{
							brand_html += "<a href=\"products.html?animal=Собака&brand="+brand+"&category="+category+"\">"+category+"</a>";
							index ++;
						}
						brand_html += "</div></div>";
					}
					var brand_cats = $(brand_node).find("Кошка");
					if($(brand_cats).text()!="")
					{
						var category = null;
						cols+=1;
						var index = 1;
						brand_html += "<div class=\"col\"><div class=\"padding_rl10\"><img src=\"img/gui/icon_cat.gif\" alt=\"\" />";
						while((category = $(brand_cats).find("item_"+index).text())!="")
						{
							brand_html += "<a href=\"products.html?animal=Кошка&brand="+brand+"&category="+category+"\">"+category+"</a>";
							index ++;
						}
						brand_html += "</div></div>";
					}
					var brand_etc = $(brand_node).find("Прочее");
					if($(brand_etc).text()!="")
					{
						category = null;
						cols+=1;
						index = 1;
						brand_html += "<div class=\"col\"><div class=\"padding_rl10\"><img src=\"img/gui/icon_elephant.gif\" alt=\"\" />";
						while((category = $(brand_etc).find("item_"+index).text())!="")
						{
							brand_html += "<a href=\"products.html?animal=Прочее&brand="+brand+"&category="+category+"\">"+category+"</a>";
							index ++;
						}
						brand_html += "</div></div>";
					}
					brand_html += "</div></div></div></div>";
					brand_html ="<div class=\"item pointer hidden\" index=\""+i+"\" >"
					+"<img src=\"img/gui/"+main_brands_pics[i]+"\" />"
					+"<div class=\"nav_wrap hidden\"  brand_item=\"true\">"
					+"<div class=\"nav home_brands_nav_"+cols+"col shadow_10 corners_10\" id=\"brand_menu\">"
					+"<div class=\"arrow\"><img src=\"img/gui/brands_nav_arrow.png\" alt=\"\" /></div>"
					+"<div class=\"padding_rl10\" >"+brand_html;
					items_html+=brand_html;
				}
				$("#brand_container").html(items_html);
				initClickOutClose(".nav_wrap[brand_item=\"true\"]");
				$(".item img").mouseover(function(){
					$(this).animate({
						opacity: 0.6
					}, 200,function(){
						});
				});
				$(".item img").mouseout(function(){
					$(this).animate({
						opacity: 1
					}, 200,function(){
						});
				});
				$(".item").click(function(){
					$(".nav_wrap[brand_item=\"true\"]").hide();
					$(this).find(".nav_wrap").show();

				});
			}
			else
			{
				showMessage("", "Серверная ошибка.", 2000);
			}
		}
	});
}
function initNextBrands()
{
	$(".item[index=\"5\"]").hide();
	$(".item[index=\"6\"]").hide();
	$(".item[index=\"7\"]").hide();
	$(".item[index=\"8\"]").hide();
    $(".item[index=\"9\"]").hide();

	$("#brands_prev,#brands_next").click(function(){
		if($("#brand_container").attr("brand_count")=="5"||$("#brand_container").attr("brand_count")=="4")
		{
			$("#brand_container").attr("brand_count","0");
			if($("#brand_container").attr("page")=="1")
			{
				$(".item[index=\"0\"]").fadeOut(600
					,function(){
						$(".item[index=\"5\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"1\"]").fadeOut(600
					,function(){
						$(".item[index=\"6\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"2\"]").fadeOut(600
					,function(){
						$(".item[index=\"7\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"3\"]").fadeOut(600
					,function(){
						$(".item[index=\"8\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"4\"]").fadeOut(600
					,function(){
                        $(".item[index=\"9\"]").fadeIn(600,function(){
                            $("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
                        });
					});
				$("#brand_container").attr("page","2");
			}
			else
			{
				$(".item[index=\"5\"]").fadeOut(600
					,function(){
						$(".item[index=\"0\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"6\"]").fadeOut(600
					,function(){
						$(".item[index=\"1\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"7\"]").fadeOut(600
					,function(){
						$(".item[index=\"2\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
				$(".item[index=\"8\"]").fadeOut(600
					,function(){
						$(".item[index=\"3\"]").fadeIn(600,function(){
							$("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
						});
					});
                $(".item[index=\"9\"]").fadeOut(600
                    ,function(){
                        $(".item[index=\"4\"]").fadeIn(600,function(){
                            $("#brand_container").attr("brand_count",$("#brand_container").attr("brand_count")*1.0+1.0);
                        });
                    });
				$("#brand_container").attr("page","1");
	    
			}
		}
	});
    
}
function loadBanner()
{
	var url_string = base_app_url+"/Content?cmd=get_content&content=banner";
	$.ajax({
		async:false,
		cache:false,
		url: url_string,
		context: document.body,
		dataTypeString:"xml",
		success: function(xml)
		{
			var code = $(xml).find("root").find("code").text();
//            alert($(xml).text());
			if(code=="100000")// success
			{
				var html = $("root data value",xml).text();
				$("#banner_place").html(html);
			}
			else
			{
				showMessage("", "Серверная ошибка.", 2000);
			}
		}
	});
}


