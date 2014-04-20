$(document).ready(function() {
  $('.show-featured').click(function(){
    $('.features,.show-featured').toggleClass('active');
  });
  
  $('input[placeholder], textarea[placeholder]').placeholder();

	$('.fancybox').fancybox({
	  helpers: {
	    title: { type: 'inside' }
	  }
	});
	
	$('.fancybox-gallery').fancybox({
		prevEffect: 'none',
		nextEffect: 'none',
		closeBtn: false,
		helpers: {
			title: { type: 'inside' },
			buttons: {}
		},
    beforeShow: function() {
      $('<div class="watermark"><img src="/images/bg/copyright.png" alt=""></div>').appendTo($.fancybox.inner);
    }
	});

	$('.delivery').each(function () {
		if (this.checked) {
			var price = parseFloat(this.getAttribute('data-price').replace(/[,]+/g, '.'));
			$('#delivery_price').text(price);
			check_payment();
		}
	});

	$('.way').each(function () {
		var id     = this.value;
		var sel    = document.getElementById('ds_' + id);

		if (this.checked) {
			sel.disabled = false;
			var tmp   = sel.options[sel.selectedIndex].getAttribute('data-price')
    		var price = parseFloat(tmp.replace(/[,]+/g, '.'));
			$('#delivery_price').text(price);
			check_payment();
		} else {
			sel.disabled = true;
		}
	});

	$('.delivery').change(function() {

		$('.delivery-select').each(function () {
			this.disabled = true;
		});
		
		if (this.checked) {
			var price = parseFloat(this.getAttribute('data-price').replace(/[,]+/g, '.'));
			$('#delivery_price').text(price);
			check_payment();
		}
	});

	$('.way').change(function() {

		$('.delivery-select').each(function () {
			this.disabled = true;
		});

		if (this.checked) {
			var id     = this.value;
			var sel    = document.getElementById('ds_' + id);
			sel.disabled = false;
			var tmp   = sel.options[sel.selectedIndex].getAttribute('data-price')
    		var price = parseFloat(tmp.replace(/[,]+/g, '.'));
			$('#delivery_price').text(price);
			check_payment();
		}
	});

    $(".delivery-select").change(function() {
    	var tmp   = this.options[this.selectedIndex].getAttribute('data-price');
        var price = parseFloat(tmp.replace(/[,]+/g, '.'));
        $('#delivery_price').text(price);
        check_payment();

        var radio = document.getElementById('r' + this.id);
        radio.checked = true;
    });

    function check_payment() {
    	$.post(
			"/order/payment/",
			$("#order_payment").serialize(),
			function (response){
				console.log(response);
				var submit = document.getElementById('submit');
				var status = document.getElementById('status');
				if (response.status == 'OK') {
					submit.disabled = false;
					status.style.visibility  = 'hidden';
				} else {
					status.style.visibility = 'visible';
					submit.disabled   = true;
				}
		});
    }
});