function blink(selector,deepness,period)
{
	$(selector).animate({
		opacity: deepness
	}, period).animate({
		opacity: 1
	}, period,function(){
            $("#product_form_close").click();
        });
}