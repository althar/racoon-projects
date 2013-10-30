// ----------- Initialization of basic controls --------------
function initControlEditableTextbox(mainElementSelector
	,staticElementsSelector
	,hoverElementsSelector
	,editElementsSelector
	,editInputSelector
	,staticInputSelector
	,emptyHint
	,emptyHintCss
	,hoverCss
	,value
	,applyChangesElementSelector
	,validationFunction
	,applyFunction)
{
	var current_value = value;
	$(staticInputSelector).html(current_value);
	$(editInputSelector).attr("inner_value",current_value);
	$(hoverElementsSelector).hide();
	$(editElementsSelector).hide();
	$(staticElementsSelector).show();

    
	// Empty or not
	if(current_value==emptyHint||current_value=="")// Empty
	{
		$(staticInputSelector).attr("style",emptyHintCss);
		$(staticInputSelector).html(emptyHint);
	}
	else
	{
		$(staticInputSelector).attr("style","");
	}
    
	// Hover animation
	$(staticElementsSelector).mouseover(function(){
		$(hoverElementsSelector).show();
		if(current_value==emptyHint||current_value=="")// Empty
		{
			$(staticInputSelector).attr("style",emptyHintCss+" "+hoverCss);
		}
		else
		{
			$(staticInputSelector).attr("style",hoverCss);
		}
	});
	$(staticElementsSelector).mouseleave(function(){
		var display_state = "";
		if($(staticElementsSelector).css("display")=="none")
		{
			display_state = "display: none;";
		}
		$(hoverElementsSelector).hide();
		if(current_value==emptyHint||current_value=="")// Empty
		{
			$(staticInputSelector).attr("style",emptyHintCss+display_state);
		}
		else
		{
			$(staticInputSelector).attr("style",display_state);
		}
	
	});

	// Click to make editable
	$(staticElementsSelector).click(function(){
		$(editElementsSelector).attr("value",current_value);
		$(staticElementsSelector).hide();
		$(staticInputSelector).hide();
		$(hoverElementsSelector).hide();
		$(editElementsSelector).show();
		$(editElementsSelector).focus();
	});

	// Apply changes
	$(editInputSelector).keypress(function(e){
		if(e.keyCode == 13)
		{
			if(validationFunction($(editInputSelector).attr("value")))
			{
				if(applyFunction($(editInputSelector).attr("value")))
				{
					// Change value...
					current_value = $(editInputSelector).attr("value");
					if(current_value==emptyHint)
					{
						current_value = "";
					}
					$(editInputSelector).attr("inner_value",current_value);
				}
				// Return to static state...
				$(staticInputSelector).html(current_value);
				$(hoverElementsSelector).hide();
				$(editElementsSelector).hide();
				$(staticElementsSelector).show();
				if(current_value==emptyHint||current_value=="")// Empty
				{
					$(staticInputSelector).attr("style",emptyHintCss);
					$(staticInputSelector).html(emptyHint);
				}
				else
				{
					$(staticInputSelector).attr("style","");
				}
			}
			else
			{
		
		}
		}
	});
	$(applyChangesElementSelector).click(function(){
		if(validationFunction())
		{
			applyFunction();
		}
	});

	// Blur control
	$(editInputSelector).blur(function(){
		$(staticInputSelector).html(current_value);
		$(hoverElementsSelector).hide();
		$(editElementsSelector).hide();
		$(staticElementsSelector).show();
		if(current_value==emptyHint||current_value=="")// Empty
		{
			$(staticInputSelector).attr("style",emptyHintCss);
			$(staticInputSelector).html(emptyHint);
		}
		else
		{
			$(staticInputSelector).attr("style","");
		}
	});
}
function initControlTextBox(inputElementSelector
	,emptyHint
	,emptyHintCss
	,value
	,applyChangesElementSelector
	,applyChangesKeyCode
	,applyFunction)
{
	//alert(value);
	$(inputElementSelector).attr("value",value);
	if(value==""||value==emptyHint)
	{
		$(inputElementSelector).attr("style",emptyHintCss);
		$(inputElementSelector).attr("value",emptyHint);
	}
	else
	{
		$(inputElementSelector).attr("style","");
	}
    
	$(inputElementSelector).focus(function(){
		if($(inputElementSelector).attr("value")==""||$(inputElementSelector).attr("value")==emptyHint)
		{
			$(inputElementSelector).attr("style","");
			$(inputElementSelector).attr("value","");
		}
	});
	$(inputElementSelector).blur(function(){
		if($(inputElementSelector).attr("value")==""||$(inputElementSelector).attr("value")==emptyHint)
		{
			$(inputElementSelector).attr("style",emptyHintCss);
			$(inputElementSelector).attr("value",emptyHint);
		}
	});
	$(applyChangesElementSelector).click(function(){
		applyFunction();
		$(inputElementSelector).blur();
	});
	$(inputElementSelector).keyup(function(e)
	{
		if(e.keyCode == applyChangesKeyCode)
		{
			applyFunction();
			$(inputElementSelector).blur();
		}
	});
}
function initControlTextArea(inputElementSelector
	,emptyHint
	,emptyHintCss
	,value
	,applyChangesElementSelector
	,applyFunction)
{
	//alert(value);
	$(inputElementSelector).html(value);
	if(value==""||value==emptyHint)
	{
		$(inputElementSelector).attr("style",emptyHintCss);
		$(inputElementSelector).html(emptyHint);
	}
	else
	{
		$(inputElementSelector).attr("style","");
	}

	$(inputElementSelector).focus(function(){
		if($(inputElementSelector).html()==""||$(inputElementSelector).html()==emptyHint)
		{
			$(inputElementSelector).attr("style","");
			$(inputElementSelector).html("");
		}
	});
	$(inputElementSelector).blur(function(){
		if($(inputElementSelector).html()==""||$(inputElementSelector).html()==emptyHint)
		{
			$(inputElementSelector).attr("style",emptyHintCss);
			$(inputElementSelector).html(emptyHint);
		}
	});
	$(applyChangesElementSelector).click(function(){
		applyFunction();
		$(inputElementSelector).blur();
	});
}


// ----------- Animation functions ---------------------------
function initClickOutClose(controlSelector)
{
	$(controlSelector).mouseover(function(){
		$(this).attr("is_hovered","true");
	//alert("");
	});
	$(controlSelector).mouseleave(function(){
		$(this).attr("is_hovered","false");
	});
	$("body").mouseup(function(){
		var is_hovered = $(controlSelector).attr("is_hovered");
		//alert(is_hovered);
		if(is_hovered!="true")
		{
			$(controlSelector).hide();
		}
	});
}
function hoverControl(controlSelector,changingControlSelector,hoverClass,leaveClass)
{
	$(controlSelector).mouseover(function(){
		$(changingControlSelector).removeClass(leaveClass);
		$(changingControlSelector).addClass(hoverClass);
	});
	$(controlSelector).mouseleave(function(){
		$(changingControlSelector).removeClass(hoverClass);
		$(changingControlSelector).addClass(leaveClass);
	});
}


function showFadingMessage(message_box_selector,message_text_element,message_html,message_duration,fade_duration)
{
	$(message_text_element).html(message_html);
	$(message_box_selector).fadeIn(fade_duration).delay(message_duration).fadeOut(fade_duration);
}