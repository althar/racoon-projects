var is_authorized = false;
var current_catalogue_id = 0;

$(document).ready(function()
{
    $("body").hide();
    init();
    this.title = "Каталог";
    loadCatalogue();
    is_authorized = authorization();
    initRegistrationForm();
    initAuthorizationForm();
    loadMoney();
    initLogout();
    initAddMoneyForm();
    start();
    //initActivateCard("#code_input","#code_butt");
    $("body").show();
});
function start()
{
    var first_menu_item = $("li[top_level=\"true\"]").first();
    first_menu_item.click();
    //$(first_menu_item).find("ul li").first().click();
    var cmd = getURLParameter("cmd");
    if(cmd=="reg")
    {
        $("#registration_butt").click();
    }
    if(cmd=="ok_reg")
    {
        customAlert("Вы успешно зарегистрированы",4000);
    }
}
function loadCatalogue()
{
    loader(true);
    var url_string = base_app_url+"/Catalogue?cmd=get_catalogue_structure";
    //$("body").append(url_string);
    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            //alert("auth code: "+code);
            if(code=="100000")// success
            {
                //alert("got catalogue");
                var index = 1;
                var current_top_level = null;
                var catalog_html = "";
                while((current_top_level = $("root > data > WebSection > Childs > Item_"+index+" > DisplayName",xml)).text()!="")
                {
                    var inner_index = 1;
                    var current_low_level ="";
                    var top_level_name = $(current_top_level).text();
                    if(top_level_name!="Обувь"
                        &&top_level_name!="Аксессуары и сувениры"
                        &&top_level_name!="Антиквариат и винтаж"
                        &&top_level_name!="OZON.travel"
                        &&top_level_name!="Театры и концерты"
                        &&top_level_name!="Ювелирные изделия"
                        &&top_level_name!="Модный бутик"
                        &&top_level_name!="Скачать")
                    {
                        //alert(index);
                        if(index==1)
                        {
                            top_level_name = "TOP";
                            catalog_html += "<li top=\"true\" class=\"leaf first\" top_level=\"true\"><a href=\"#\" title=\""+top_level_name+" \">"+top_level_name+" </a><ul class=\"submenu\">";
                        }
                        else
                        {
                            catalog_html += "<li class=\"leaf first\" top_level=\"true\"><a href=\"#\" title=\""+top_level_name+" \">"+top_level_name+" </a><ul class=\"submenu\">";
                        }
                        

                        while((current_low_level = $("root > data > WebSection > Childs > Item_"+index+" > Childs > Item_"+inner_index+" > DisplayName",xml)).text()!="")
                        {
                            var display_name = $("root > data > WebSection > Childs > Item_"+index+" > Childs > Item_"+inner_index+" > DisplayName",xml).text();
                            var short_name = display_name.substr(0, 17)+"...";
                            if(display_name.length<=17)
                            {
                                short_name = display_name;
                            }
                            var catalog_id = $("root > data > WebSection > Childs > Item_"+index+" > Childs > Item_"+inner_index+" > CatalogId",xml).text();
                            catalog_html+="<li class=\"leaf first\" catalogue_id=\""+catalog_id+"\"><a href=\"#\" title=\""+display_name+"\">"+short_name+"</a></li>";
                            inner_index++;
                        }
                        catalog_html+="</ul></li>";
                        $("#good_menu").html(catalog_html);
                    }
                    index++;
                }
                $("li[top=\"true\"]>ul>li").each(function(){
                   $(this).hide();
                });
            }
            else if(code=="-5")
            {
        }
        }
    });
    
    $("li[top_level=\"true\"]").click(function(){
        if($(this).attr("opened")!="true")
        {
            current_catalogue_id = parseInt($(this).find("ul li").first().attr("catalogue_id"));
            loadGoods(1);
            if(is_authorized)
            {
                showPrices();
            }
            $("li[top_level=\"true\"]").attr("opened","false");
            $(this).attr("opened","true");
            $("li[top_level=\"true\"]").find("ul").slideUp(0);
            $(this).find("ul").slideDown(0);
        }
    });
    $("li[top_level=\"true\"] ul li").click(function(){
        current_catalogue_id = parseInt($(this).attr("catalogue_id"));
        loadGoods(1);
        if(is_authorized)
        {
            showPrices();
        }
    //alert("!!: "+$(this).html());
    });
    loader(false);
}

function showPrices()
{
    $(".catalog_item").each(function(){
        var price = $(this).attr("price");
        // Show price somewhere...
        $(this).append("Цена:"+price);
    });
}
function loadGoods(p)
{
    loader(true);
    var url_string = base_app_url+"/Catalogue?cmd=get_catalogue_items&catalogue_id="+current_catalogue_id+"&items_on_page=30&page_number="+p;
    //$("body").append(url_string);
    $.ajax({
        async:true,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            //alert("code: "+$(xml).find("root").text());
            if(code!="100000")// success
            {
                //alert($("root GoodsItems item_"+index,xml).not("root GoodsItems item_"+index+" ClassAttributes",xml).text());
                var index = 1;
			
                var current_good = null;
                var catalog_html = "<div id=\"catalog-inner\"><div id=\"content-header\"><h1 class=\"title\">Каталог</h1></div>";
                while((current_good = $("root > GoodsItems > item_"+index,xml)).text()!="")
                {
                    var type = $(current_good).find("Detail ItemType Name").first().text().substr(0, 20);
                    type = cleanString(type);
                    var id = $("root > GoodsItems > item_"+index+" > Id",xml).text();
                    //alert("id: "+id);
                    var img_path = "";
                    var name = "";
                    for(i=1; i<6; i++)
                    {
                        var part_name = $(current_good).find("Detail ClassAttributes item_"+i+" Name").first().text();
                        if(part_name == "Название")
                        {
                            name = $(current_good).find("Detail ClassAttributes item_"+i+" Value").first().text();
                        }
                        if(part_name == "Изображение")
                        {
                            var curr = $(current_good).find("Detail ClassAttributes item_"+i+" Value");
                            var img_tail = $(current_good).find("Detail ClassAttributes item_"+i+" Value").first().text();
                            img_tail = img_tail.replace("\\", "/c120");
                            img_path = "http://static.ozone.ru/multimedia/"+img_tail;
                        }
                        var price = $(current_good).find("DiscountPrice").text();
                    }
                    name = cleanString(name).substr(0, 60);
                    if(name!="")
                    {
                        catalog_html+="<div class=\"catalog_item pointer\" item_id=\""+id+"\" img_path=\""+img_path+"\" price=\""+price+"\">";
                        catalog_html+="<div class=\"catalog_item-inner\">";
                        catalog_html+="<div class=\"rating\"></div>";
                        catalog_html+="<img class=\"pic_catalog\" src=\""+img_path+"\" alt=\""+name+"\" title=\"\" >";
                        catalog_html+="<h2 class=\"title\"><a href=\"#\" title=\""+name+"\">"+name+"</a></h2>";
                        catalog_html+="<p class=\"description\">"+type+"</p>";
                        catalog_html+="</div ></div>";
                    }
                    index++;
                }
				
                // paging...
                var total_p = $("root total_pages",xml).text()*1.0;

                var first_p = 1;
                if(p*1.0>5)
                {
                    first_p = p-5;
                }
                var last_p = first_p+9;
                if(total_p+1.0<last_p*1.0)
                {
                    last_p = total_p;
                }
                var pager_html = "<div class=\"item-list\"><ul class=\"pager\">";
                if(p!=1)
                {
                    pager_html +="<li pager_item=\"true\" page_number=\"1\" class=\"pager-first first\"><a href=\"#\" title=\"На первую страницу\" class=\"active\">« первая</a></li>";
                    pager_html +="<li pager_item=\"true\" page_number=\""+(p-1)+"\" class=\"pager-previous\" ><a href=\"#\" title=\"На предыдущую страницу\" class=\"active\">‹ предыдущая</a></li>";
                }
                for(i=first_p; i<last_p+1; i++)
                {
                    pager_html+="<li pager_item=\"true\" page_number=\""+i+"\" class=\"pager-";
                    if(p == i)
                    {
                        pager_html+="current";
                    }
                    else
                    {
                        pager_html+="item";
                    }
                    pager_html+="\"><a href=\"#\" title=\"На страницу номер "+i+"\" class=\"active\">"+i+"</a></li>";
                }
                if(p!=last_p)
                {
                    pager_html +="<li pager_item=\"true\" page_number=\""+(p*1.0+1)+"\" class=\"pager-next\"><a href=\"#\" title=\"На следующую страницу\" class=\"active\">следующая ›</a></li>";
                    pager_html +="<li pager_item=\"true\" page_number=\""+total_p+"\" class=\"pager-last last\"><a href=\"#\" title=\"На последнюю страницу\" class=\"active\">последняя »</a></li>";
                }
                pager_html += "</ul></div>";

                catalog_html+=pager_html;
                catalog_html+="</div>";
                $("#catalog").html(catalog_html);

                $(".catalog_item").click(function(){
                    var id = $(this).attr("item_id");
                    var img_path = $(this).attr("img_path");
                    window.location = "product.html?item_id="+id+"&img_path="+img_path;
                });
                if(is_authorized)
                {
                    showPrices();
                }
                $("li[pager_item=\"true\"]").click(function(){
                    var c_p = $(this).attr("page_number");
                    loadGoods(c_p);
                    if(is_authorized)
                    {
                        showPrices();
                    }
                });
            }
            else if(code=="-5")
            {
            }
            loader(false);
        }
    });
	
}
function initActivateCard(inputSelector,commitElementSelector)
{
    var activation = function(){
        var code = $(inputSelector).attr("value");
        //showFadingMessage("#mess_box","#mess_box","act: "+code,3000,400);

        var url_string = base_app_url+"/User?cmd=activate_card&code="+code;

        $.ajax({
            async:false,
            cache:false,
            url: url_string,
            context: document.body,
            dataTypeString:"xml",
            success: function(xml){
                var code = $(xml).find("root").find("code").text();
                if(code=="1")// success
                {
                    showRegistrationMessage("Вы успешно активировали карту. ");
					
                    result = true;
                }
                if(code=="-1")
                {
                    showRegistrationMessage("Не удалось активировать карту");
                    result = false;
                }
                if(code=="-11")
                {
                    showRegistrationMessage("Активация карты разрешена не чаще чем раз в 4 секунды. Подождите.");
                    result = false;
                }
                if(code=="-3")
                {
                    showRegistrationMessage("Пользователь с таким номером уже есть в системе. Либо номер некорректен");
                    result = false;
                }
            }
        });
    };
    $(commitElementSelector).click(function(){
        activation();
    });
}


