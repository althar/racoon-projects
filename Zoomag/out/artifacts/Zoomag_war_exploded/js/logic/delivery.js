$(document).ready(function()
{
    $("#content_div").hide();
    init();
    loadBrandsAndCategories();
    initChooseCategory();
    loadDiscount();
    checkBasket();
    initSearch();
    initAuthForm();
    loadContent();
    $("#content_div").show();
});