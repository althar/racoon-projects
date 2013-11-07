var current_animal = "";
var current_brand = "Все бренды";
var current_id = "";
var current_category = "";
var current_age_category = "";
var current_keywords = "";
var good_list_columns = 3;

$(document).ready(function()
{
    $("body").hide();
    init();
    initGeneralElements();
    authorization(true);
    loadBrandsAndCategories();
    initChooseBrand();
    initChooseCategory();
    initSearch();
    initFilter("Поиск по названию");
    initCurrentParameters();
    loadCurrentGoods();
    initProductForm();
    loadDiscount();
    checkBasket();
    initAuthForm();
    loadContent();
    $("body").keydown(function(e)
    {
        if(e.keyCode == 40)// down
        {
            //$("#product_form").scrollTop(4);
            e.preventDefault();
        }
        if(e.keyCode == 38)// up
        {
            //$("#product_form").scrollTop(-4);
            e.preventDefault();
        }
    });
    $("body").show();
});

function blinkBasket()
{
    blink("#basket",0.2,200);
}
function initCurrentParameters()
{

    current_animal = getURLParameter("animal");
    current_id = getURLParameter("id");
    current_brand = getURLParameter("brand");

    current_category = getURLParameter("category");
    current_keywords = getURLParameter("keywords");
    good_list_columns = 2;
    if(screen.width>1280)
    {
        good_list_columns = 3;
    }
    if(screen.width>1600)
    {
        good_list_columns = 4;
    }
    $("#content_div").addClass("products_catalogue_"+good_list_columns+"col");

    if(current_brand!=null)
    {
        $("#brands_bar a").each(function(){
            if($(this).html()==current_brand.replace("_", " "))
            {
                //alert(current_brand.replace("_", " "));
                $(this).click();
            }
        });
    }

    if(current_keywords!=null&&current_keywords!="null")
    {
        //alert($("#filter_input"));
        $("#filter_input").attr("value",current_keywords);
        //alert(":1");
    }
    if(current_animal!=null&&current_category!=null)
    {
	
        if(current_animal=="Собака")
        {
            $("#dogs_category a").each(function(){
                if($(this).html()==current_category)
                {
                    $(this).click();
                }
            });
        }
        if(current_animal=="Кошка")
        {
            $("#cats_category a").each(function(){
                if($(this).html()==current_category)
                {
                    $(this).click();
                }
            });
        }
        if(current_animal=="Прочее")
        {
            $("#etc_category a").each(function(){
                if($(this).html()==current_category)
                {
                    $(this).click();
                }
            });
        }
    }


}
function sortBrands()
{
//    var curr_link = null;
//    for(i=0; i<main_brands.length; i++)
//    {
//
//        var curr_brand = main_brands[i];
//        var link = $("#brands_bar a").filter(function(){return $(this).html()==curr_brand});
//        if(curr_link==null)
//        {
//            $("#brands_bar a").first().after($(link));
//            //alert("first");
//        }
//        else
//        {
//            $(curr_link).after($(link));
//            //alert("second");
//        }
//        curr_link = link;
//    }
}
function loadBrandsAndCategories()
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

            var code = $("root > code",xml).text();
            if(code=="100000")// success
            {
                var dog = $("root > data > Собака",xml);
                var cat = $("root > data > Кошка",xml);
                var etc = $("root > data > Прочее",xml);

                var dog_html = "<div class=\"title\">Для собак</div>";
                var index = 1;
                var item = null;
                while((item=$(dog).find("item_"+index).text())!="")
                {
                    dog_html+="<a href=\"#\" class=\"level-1\">"+item+"</a>";
                    index ++;
                }
                $("#dogs_category").html(dog_html);


                var cat_html = "<div class=\"title\">Для кошек</div>";
                index = 1;
                item = null;
                while((item=$(cat).find("item_"+index).text())!="")
                {
                    cat_html+="<a href=\"#\" class=\"level-1\">"+item+"</a>";
                    index ++;
                }
                $("#cats_category").html(cat_html);

                var etc_html = "<div class=\"title\">Прочее</div>";
                index = 1;
                item = null;
                while((item=$(etc).find("item_"+index).text())!="")
                {
                    etc_html+="<a href=\"#\" class=\"level-1\">"+item+"</a>";
                    index ++;
                }
                $("#etc_category").html(etc_html);
            }
            else
            {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });

    var url_string = base_app_url+"/Catalogue?cmd=get_brands";
    $.ajax({
        async:false,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml)
        {

            var code = $("root > code",xml).text();
            if(code=="100000")// success
            {
                var index = 1;
                var item = "";
                var br_html = "<a class=\"act corners_3 shadow_inset\">Все бренды</a>";
                while((item=$("root > data > item_"+index,xml)).text()!="")
                {
                    var b = $(item).text();
                    br_html += "<a>"+b+"</a>";
                    index ++;
                }

                $("#brands_bar").html(br_html);
                sortBrands();
            }
            else
            {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
}
function initProductForm()
{
    $("a[good_link=\"true\"]").click(function(){
        var scrollTop = $(window).scrollTop();

        $("#product_form").animate({
            top: "+"+scrollTop+"px"
        }, 0);
        $("#current_good_img").hide();
		$("#current_good_img_loader").show();
        $("#current_good_img").load(function(){
            $("#current_good_img_loader").hide();
            $("#current_good_img").show();
        });

        var photo_url = $(this).find(":hidden[good_info=\"photo_url\"]").attr("value");

        $("#current_good_img").attr("src","");
        $("#current_good_img").attr("src",photo_url);

        var name_for_shop = $(this).find(":hidden[good_info=\"name_for_shop\"]").attr("value");
        var description = $(this).find(":hidden[good_info=\"description\"]").attr("value");
        var weight_product = $(this).find(":hidden[good_info=\"weight_product\"]").attr("value");
        var price = $(this).find(":hidden[good_info=\"price\"]").attr("value");
        var id = $(this).find(":hidden[good_info=\"id\"]").attr("value");
        var animal = $(this).find(":hidden[good_info=\"animal\"]").attr("value");
        var brand = $(this).find(":hidden[good_info=\"brand\"]").attr("value");
        var food_type = $(this).find(":hidden[good_info=\"food_type\"]").attr("value");
        var category = $(this).find(":hidden[good_info=\"category\"]").attr("value");
        var food_type_age = $(this).find(":hidden[good_info=\"food_type_age\"]").attr("value");
		
        $("#in_basket_link").unbind("click");
        $("#in_basket_link").click(function(){
            putInBasket(id);
            $("#product_form").hide();
        });
        //alert(description);
        $("#current_good_price_span").html(price);
        $("#current_good_img").attr("src",photo_url);
        $("#current_good_name").html(name_for_shop);
        $("#current_good_description").html(description);

        var price_weight_html = "";
        var item_div = $(this).parent();
        $(item_div).find(".price").each(function(){
            //alert($(this).html());
            var w = $(this).find("div[good_info=\"weight\"]").attr("value");
            var p = $(this).find("div[good_info=\"price\"]").attr("value");
            var id = $(this).find("input[good_info=\"id\"]").attr("value");
            var art = $(this).find("input[good_info=\"article\"]").attr("value");
            price_weight_html += "<div class=\"price\">";
            price_weight_html += "<input type=\"hidden\" good_info=\"id\" value=\""+id+"\"/>";
            price_weight_html += "<div class=\"weight_tag\"><div class=\"value\"  good_info=\"weight\" value=\""+w+"\">"+w+"</div></div>";
            price_weight_html += "<div class=\"price_tag\"><div class=\"value\"  good_info=\"price\" value=\""+p+"\">"+p+"<span class=\"rur\"> c</span></div></div>";
            price_weight_html += "<a class=\"buy corners_3\" fast_link_in_basket=\"true\" good_id=\""+id+"\">";
            price_weight_html += "<img src=\"img/gui/basket_l.gif\" alt=\"\"/>";
            price_weight_html += "";
            price_weight_html += "</a><div class=\"article-tag\" good_info=\"article\" > артикул "+art+"</div></div>";
        });


        $("#good_weights").html(price_weight_html);
        var details = brand;
        if(animal!=null&&animal!="")
        {
            details+=" → "+animal;
        }
        if(category!=null&&category!="")
        {
            details+=" → "+category;
        }
        if(food_type_age!=null&&food_type_age!="")
        {
            details+=" → "+food_type_age;
        }
        $("#current_good_details").html(details);
        $("#price_span").html();

        initBasketClick();
        $("body").css("overflow","hidden");
        $("#product_form").show();
    });
    $("#product_form_close").click(function(){
        $("#product_form").hide();
        $("body").css("overflow","auto");
    });
}
function loadCurrentGoods()
{
    $("#preloader").show();
    var url_params = "";
    if(current_animal!=="null"&&current_animal!=null)
    {
        url_params+="animal="+urlEncode(current_animal)+"&";
    }
    if(current_brand!=="null"&&current_brand!=null)
    {
        url_params+="brand="+urlEncode(current_brand)+"&";
    }
    if(current_category!=="null"&&current_category!=null)
    {
        url_params+="type="+urlEncode(current_category)+"&";
    }
    if(current_age_category!=="null"&&current_age_category!=null)
    {
        url_params+="age="+urlEncode(current_age_category)+"&";
    }
    if($("#filter_input").val()!=null&&$("#filter_input").val()!=""&&$("#filter_input").val()!="Поиск по названию")
    {
        current_keywords = $("#filter_input").val();
    }
    if(current_keywords!=="null"&&current_keywords!=null)
    {
        url_params+="keywords="+urlEncode(current_keywords)+"&";
    }
    if(current_id!=="null"&&current_id!=null)
    {
        url_params+="id="+current_id+"&";
    }
    $("#goods_list").hide();

    filterBrands(false);
    encodeURIComponent(url_string);
    var url_string = base_app_url+"/Catalogue?cmd=get_goods&";
    //var url_params = urlEncode(url_params);
    url_string+=url_params;

    //$("#content_div").append(url_string);
    $.ajax({
        async:true,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml)
        {
            var code = $(xml).find("root").find("code").text();
            var admin = $(xml).find("root").find("admin").text();
            if(code=="100000")// success
            {
                //$("#spam").html(url_string);
                var index = 1;
                var current_item = null;
                var item_list_html = "";
                var item_html = "";
                //alert($(xml).find("root").find("data").text());
                var current_name_for_order = "";
                while($(xml).find("root").find("data").find("item_"+index).text()!="")
                {
                    current_item = $(xml).find("root").find("data").find("item_"+index);
                    var category = $(current_item).find("food_type_category").text();
                    var animal = $(current_item).find("animal").text();
                    var food_type = $(current_item).find("food_type").text();
                    var description = $(current_item).find("description").text();
                    description = htmlDecode(description);
                    var food_type_age = $(current_item).find("food_type_age").text();
                    var name_for_shop = $(current_item).find("name_for_shop").text();
                    name_for_shop = htmlDecode(name_for_shop);
                    var name_for_order = $(current_item).find("name_for_order").text(); // For groups!!!
                    name_for_order = htmlDecode(name_for_order);
                    var photo_url = $(current_item).find("photo_url").text();
                    var brand = $(current_item).find("company").text();
                    var weight_product = $(current_item).find("weight_for_site").text();
                    var price = $(current_item).find("price").text();
                    var articul = $(current_item).find("articul").text();
                    var id = $(current_item).find("id").text();
                    var left = $(current_item).find("left").text();


                    if(name_for_order!=current_name_for_order)// New good...
                    {
                        current_name_for_order = name_for_order;
                        // Close previous good (if any). Add it to html
                        if(item_html!="")
                        {
                            // Close previous...
                            item_html+="</div></div>";
                            // Add it...
                            item_list_html+=item_html;
                            //alert(item_html);
                            item_html = "";
                        }

                        // Open new good...
                        item_html += "<div class=\"item\">";

                        item_html += "<div class=\"padding_rl20\">";
                        item_html += "<a good_link=\"true\">";
                        item_html +=" <input type=\"hidden\" good_info=\"photo_url\" value=\""+photo_base_url+photo_url+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"name_for_shop\" value=\""+name_for_shop+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"description\" value=\""+description+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"weight_product\" value=\""+weight_product+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"price\" value=\""+price+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"animal\" value=\""+animal+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"id\" value=\""+id+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"article\" value=\""+articul+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"food_type\" value=\""+food_type+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"category\" value=\""+removeDigits(category)+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"brand\" value=\""+brand+"\"/>";
                        item_html +=" <input type=\"hidden\" good_info=\"food_type_age\" value=\""+food_type_age+"\"/>";
                        item_html +="<div class=\"photo\"><img src=\""+small_photo_base_url+photo_url+"\" alt=\"\" /></div>";
                        item_html +=" <span class=\"title\">"+name_for_shop+" <span class=\"art\"></span></span>";
                        item_html += "</a>";
                        item_html += "<p class=\"desc\">"+""+"</p>";
                        // Add it`s price div

                        item_html += "<div class=\"price\">";
                        item_html += "<input type=\"hidden\" good_info=\"id\" value=\""+id+"\"/>";
                        item_html += "<input type=\"hidden\" good_info=\"article\" value=\""+articul+"\"/>";
                        item_html += "<div class=\"weight_tag\"><div class=\"value\"  good_info=\"weight\" value=\""+weight_product+"\">"+weight_product+"</div>";
                        if(admin=="true")
                        {
                            item_html += "<b style='";
                            if(left*1<=0)
                            {
                                item_html+="color:red;";
                            }
                            item_html+=" alignment:center; font-size: medium; float:right;'>"+left+"</b>";
                        }
                        item_html += "</div>";
                        item_html += "<div class=\"price_tag\"><div class=\"value\"  good_info=\"price\" value=\""+price+"\">"+price+"<span class=\"rur\"> c</span></div></div>";
                        item_html += "<a class=\"buy corners_3\" fast_link_in_basket=\"true\" good_id=\""+id+"\">";
                        item_html += "<img src=\"img/gui/basket_s.gif\" alt=\"\"/>";

                        item_html += "</a></div>";
                    }
                    else // Need to add price and weight tags only
                    {
                        //alert("Same good..."+name_for_order);
                        item_html += "<div class=\"price\">";
                        item_html += "<input type=\"hidden\" good_info=\"id\" value=\""+id+"\"/>";
                        item_html += "<input type=\"hidden\" good_info=\"article\" value=\""+articul+"\"/>";
                        item_html += "<div class=\"weight_tag\"><div class=\"value\"  good_info=\"weight\" value=\""+weight_product+"\">"+weight_product+"</div>";

                        if(admin=="true")
                        {
                            item_html += "<b style='";
                            if(left*1<=0)
                            {
                                item_html+="color:red;";
                            }
                            item_html+=" alignment:center; font-size: medium; float:right;'>"+left+"</b>";
                        }
                        item_html += "</div>";
                        item_html += "<div class=\"price_tag\"><div class=\"value\"  good_info=\"price\" value=\""+price+"\">"+price+"<span class=\"rur\"> c</span></div></div>";
                        item_html += "<a class=\"buy corners_3\" fast_link_in_basket=\"true\" good_id=\""+id+"\">";
                        item_html += "<img src=\"img/gui/basket_s.gif\" alt=\"\"/>";
                        item_html += "</a></div>";
                    }
                    index++;
                }

                //alert("item_html: "+item_html);
                if(item_html!="")// Need to close good...
                {
                    item_html+="</div></div>";
                }
                item_list_html+=item_html;
                //alert(item_list_html);
                if(index==1)
                {
                    $("#goods_list").html("<h2>По Вашему запросу ничего не найдено, попробуйте изменить параметры поиска</h2>");
                    $("#brands_bar").hide();
                }
                else
                {
                    $("#goods_list").html(item_list_html);
                    $("#brands_bar").show();
                }
                $("#goods_list").show();
				
                initProductForm();
                initBasketClick();
                if(current_id!=="null"&&current_id!=null)
                {
                    $("a[good_link=\"true\"]").first().click();
                    current_id = "null";
                }
            }
            else
            {
                showMessage("", "Серверная ошибка.", 2000);
            }
            $("#preloader").hide();
        }
    });
}

function initChooseBrand()
{
    $("#brands_bar a").click(function(){
        $("#brands_bar a").removeClass("act");
        $("#brands_bar a").removeClass("corners_3");
        $("#brands_bar a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");
        var brand = $(this).html();
        if(brand=="Все бренды")
        {
            current_brand = "null";
        }
        else
        {
            current_brand = brand;
        }
        loadCurrentGoods();
        showSearchHint();
    });
}
function showSearchHint()
{
    var brands = "Все бренды";
    var animal = "Все животные";
    var category = "Все категории";
    var subcategory = "Все подкатегории";
    if(current_brand!="null"&&current_brand!=null&&current_brand!="")
    {
        brands = current_brand;
    }
    if(current_animal!="null"&&current_animal!=null&&current_animal!="")
    {
        animal = current_animal;
    }
    if(current_category!="null"&&current_category!=null&&current_category!="")
    {
        category = current_category;
    }
    if(current_age_category!="null"&&current_age_category!=null&&current_age_category!="")
    {
        subcategory = current_age_category;
    }
    var html = brands+"<span class=\"arrow\"> → </span>"+animal+"<span class=\"arrow\"> → </span>"+category+"<span class=\"arrow\"> → </span>"+subcategory;
    $("#filter_hint").html(html);
    return html;
}
function clearKeywords()
{
    current_keywords = null;
    $("#filter_input").val("Поиск по названию");
    $("#filter_input").change();
}
function initChooseCategory()
{
    $("#dogs_category a").click(function(){
        clearKeywords();
        $("#logo_img").attr("src","img/gui/logo_dog.gif");
        $("div[subcategory=\"true\"]").remove();
        $("#dogs_category a").removeClass("act");
        $("#dogs_category a").removeClass("corners_3");
        $("#dogs_category a").removeClass("shadow_inset");
        $("#cats_category a").removeClass("act");
        $("#cats_category a").removeClass("corners_3");
        $("#cats_category a").removeClass("shadow_inset");
        $("#etc_category a").removeClass("act");
        $("#etc_category a").removeClass("corners_3");
        $("#etc_category a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");
		
        var ages = getAges($(this).html(),"Собака");
        if(ages.length>0)
        {
            var under_html = "<div class=\"subsection\" subcategory=\"true\">";
            for(i=0; i<ages.length; i++)
            {
                under_html+="<a href=\"#\" class=\"level-2\" subcategory=\"true\">";
                under_html+=ages[i];
                under_html+="</a>";
            }
            under_html+="</div>";
            $(this).after(under_html);

        }
        current_animal = "Собака";
        current_category = $(this).html();
        current_age_category = "null";

        showSearchHint();
        filterBrands(true);
        initChooseSubcategory();
        loadCurrentGoods();
    });
    $("#cats_category a").click(function(){
        clearKeywords();
        //alert("");
        $("#logo_img").attr("src","img/gui/logo_cat.gif");
        $("div[subcategory=\"true\"]").remove();
        $("#dogs_category a").removeClass("act");
        $("#dogs_category a").removeClass("corners_3");
        $("#dogs_category a").removeClass("shadow_inset");
        $("#cats_category a").removeClass("act");
        $("#cats_category a").removeClass("corners_3");
        $("#cats_category a").removeClass("shadow_inset");
        $("#etc_category a").removeClass("act");
        $("#etc_category a").removeClass("corners_3");
        $("#etc_category a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");

        var ages = getAges($(this).html(),"Кошка");
        if(ages.length>0)
        {
            var under_html = "<div class=\"subsection\" subcategory=\"true\">";
            for(i=0; i<ages.length; i++)
            {
                under_html+="<a href=\"#\" class=\"level-2\" subcategory=\"true\">";
                under_html+=ages[i];
                under_html+="</a>";
            }
            under_html+="</div>";
            $(this).after(under_html);

        }
        current_animal = "Кошка";
        current_category = $(this).html();
        current_age_category = "null";

        showSearchHint();
        filterBrands(true);
        initChooseSubcategory();
        loadCurrentGoods();
    });
    $("#etc_category a").click(function(){
        clearKeywords();
        $("#logo_img").attr("src","img/gui/logo_else.gif");
        $("div[subcategory=\"true\"]").remove();
        $("#dogs_category a").removeClass("act");
        $("#dogs_category a").removeClass("corners_3");
        $("#dogs_category a").removeClass("shadow_inset");
        $("#cats_category a").removeClass("act");
        $("#cats_category a").removeClass("corners_3");
        $("#cats_category a").removeClass("shadow_inset");
        $("#etc_category a").removeClass("act");
        $("#etc_category a").removeClass("corners_3");
        $("#etc_category a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");

        var ages = getAges($(this).html(),"Прочее");
        if(ages.length>0)
        {
            var under_html = "<div class=\"subsection\" subcategory=\"true\">";
            for(i=0; i<ages.length; i++)
            {
                under_html+="<a href=\"#\" class=\"level-2\" subcategory=\"true\">";
                under_html+=ages[i];
                under_html+="</a>";
            }
            under_html+="</div>";
            $(this).after(under_html);
        }
        current_animal = "Прочее";
        current_category = $(this).html();
        current_age_category = "null";

        showSearchHint();
        filterBrands(true);
        initChooseSubcategory();
        loadCurrentGoods();
    });
}
function initChooseSubcategory()
{
    $("#dogs_category a[subcategory=\"true\"]").click(function(){
        clearKeywords();
        $("#dogs_category a").removeClass("act");
        $("#dogs_category a").removeClass("corners_3");
        $("#dogs_category a").removeClass("shadow_inset");
        $("#cats_category a").removeClass("act");
        $("#cats_category a").removeClass("corners_3");
        $("#cats_category a").removeClass("shadow_inset");
        $("#etc_category a").removeClass("act");
        $("#etc_category a").removeClass("corners_3");
        $("#etc_category a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");

        current_age_category = $(this).html();
        loadCurrentGoods();
        showSearchHint();
    });
    $("#cats_category a[subcategory=\"true\"]").click(function(){
        clearKeywords();
        $("#dogs_category a").removeClass("act");
        $("#dogs_category a").removeClass("corners_3");
        $("#dogs_category a").removeClass("shadow_inset");
        $("#cats_category a").removeClass("act");
        $("#cats_category a").removeClass("corners_3");
        $("#cats_category a").removeClass("shadow_inset");
        $("#etc_category a").removeClass("act");
        $("#etc_category a").removeClass("corners_3");
        $("#etc_category a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");

        current_age_category = $(this).html();
        loadCurrentGoods();
        showSearchHint();
    });
    $("#etc_category a[subcategory=\"true\"]").click(function(){
        clearKeywords();
        $("#dogs_category a").removeClass("act");
        $("#dogs_category a").removeClass("corners_3");
        $("#dogs_category a").removeClass("shadow_inset");
        $("#cats_category a").removeClass("act");
        $("#cats_category a").removeClass("corners_3");
        $("#cats_category a").removeClass("shadow_inset");
        $("#etc_category a").removeClass("act");
        $("#etc_category a").removeClass("corners_3");
        $("#etc_category a").removeClass("shadow_inset");
        $(this).addClass("act");
        $(this).addClass("corners_3");
        $(this).addClass("shadow_inset");

        current_age_category = $(this).html();
        loadCurrentGoods();
        showSearchHint();
    });
}

function filterBrands(init_click)
{
    var url_string = base_app_url+"/Catalogue?cmd=get_brands_by_category&category="+current_category+"&animal="+current_animal+"&keywords="+current_keywords+"&subcategory="+current_age_category;
    //alert(url_string);
    $.ajax({
        async:true,
        cache:false,
        url: url_string,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml)
        {
            var code = $("root > code",xml).text();
            //alert("Brand_filter: "+code);
            if(code=="100000")// success
            {
                var index = 1;
                var item = null;
                var must_select_all_brands = true;
                $("#brands_bar a").hide();
                $("#brands_bar a").first().show();

                while((item=$("root > data > item_"+index,xml)).text()!="")
                {
                    var brand = $("company",item).text();

                    // Find it in page_list
                    var list_brand = $("#brands_bar a").filter(function(){
                        return $(this).html()==brand;
                    });
                    if($(list_brand).html()!=null)
                    {
                        $(list_brand).show();
                        if($(list_brand).hasClass("act"))
                        {
                            must_select_all_brands = false;
                        }
                    }

                    index ++;
                }
                //alert("Searching brands... count: "+index+"   "+url_string);
                if(must_select_all_brands&&init_click)
                {
                    $("#brands_bar a").first().click();
                }

            }
            else
            {
                showMessage("", "Серверная ошибка.", 2000);
            }
        }
    });
}
function initFilter(filter_value)
{
    initControlTextBox("#filter_input", filter_value, "font-style: italic; color:#999;", filter_value, "#filter_butt", 13, function(){

        current_keywords = $("#filter_input").attr("value");
        loadCurrentGoods();
    });
    $("#link_clear_filter").click(function(){
        clearKeywords();
        loadCurrentGoods();
    });
}
function getAges(category,animal)
{
    var ages = new Array();
    var url_string = base_app_url+"/Catalogue?cmd=get_ages&category="+category+"&animal="+animal;
	
    $.ajax({
        async:false,
        url: url_string,
        cache:false,
        context: document.body,
        dataTypeString:"xml",
        success: function(xml){
            var code = $(xml).find("root").find("code").text();
            if(code==="100000")// success
            {
                var index = 1;
                while($(xml).find("root").find("data").find("item_"+index).find("food_type_age").text()!="")
                {
                    ages[index-1] = $(xml).find("root").find("data").find("item_"+index).find("food_type_age").text();
                    index++;
                }
            }
            else
            {

        }
        }
    });
    return ages;
}