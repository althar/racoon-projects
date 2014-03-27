<?php require_once("inc/init.php"); ?>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-bar-chart-o fa-fw "></i> 
			Graph 
			<span>> 
				Morris Charts
			</span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5> My Income <span class="txt-color-blue">$47,171</span></h5>
				<div class="sparkline txt-color-blue hidden-mobile hidden-md hidden-sm">
					1300, 1877, 2500, 2577, 2000, 2100, 3000, 2700, 3631, 2471, 2700, 3631, 2471
				</div>
			</li>
			<li class="sparks-info">
				<h5> Site Traffic <span class="txt-color-purple"><i class="fa fa-arrow-circle-up" data-rel="bootstrap-tooltip" title="Increased"></i>&nbsp;45%</span></h5>
				<div class="sparkline txt-color-purple hidden-mobile hidden-md hidden-sm">
					110,150,300,130,400,240,220,310,220,300, 270, 210
				</div>
			</li>
			<li class="sparks-info">
				<h5> Site Orders <span class="txt-color-greenDark"><i class="fa fa-shopping-cart"></i>&nbsp;2447</span></h5>
				<div class="sparkline txt-color-greenDark hidden-mobile hidden-md hidden-sm">
					110,150,300,130,400,240,220,310,220,300, 270, 210
				</div>
			</li>
		</ul>
	</div>
</div>


<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- row -->
	<div class="row">
		
		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Sales Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						<input type="text">
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="sales-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

		</article>
		<!-- WIDGET END -->
		
	</div>

	<!-- end row -->

	<!-- row -->

	<!-- row -->
	<div class="row">
		
		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-1" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Area Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="area-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-3" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Normal Bar Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="normal-bar-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-5" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Year Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="year-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-7" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Time Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="time-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-9" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>No Grid Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="nogrid-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->
			
		</article>
		<!-- WIDGET END -->

		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-2" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Bar Graph </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="bar-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-4" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Stacked Bar Graph </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="stacked-bar-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-6" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Donut Graph</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="donut-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-8" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Line Graph A </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="graph-B" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-10" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Line Graph B </h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="non-continu-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->
									
		</article>
		<!-- WIDGET END -->
		
	</div>

	<!-- end row -->

	<!-- row -->
	<div class="row">
		
		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-11" data-widget-editbutton="false">
				<!-- widget options:
					usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
					
					data-widget-colorbutton="false"	
					data-widget-editbutton="false"
					data-widget-togglebutton="false"
					data-widget-deletebutton="false"
					data-widget-fullscreenbutton="false"
					data-widget-custombutton="false"
					data-widget-collapsed="true" 
					data-widget-sortable="false"
					
				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
					<h2>Line Graph C</h2>				
					
				</header>

				<!-- widget div-->
				<div>
					
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						
					</div>
					<!-- end widget edit box -->
					
					<!-- widget content -->
					<div class="widget-body no-padding">
						
						<div id="non-date-graph" class="chart no-padding"></div>
						
					</div>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
			<!-- end widget -->

		</article>
		<!-- WIDGET END -->
		
	</div>

	<!-- end row -->
	
</section>
<!-- end widget grid -->

<script type="text/javascript">
	
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();
	
	// PAGE RELATED SCRIPTS
	
	/*
	 * Load related scripts
	 * and append to header
	 */
	
	// Load morris dependency 1
	loadScript("<?php echo ASSETS_URL; ?>/js/plugin/morris/raphael.2.1.0.min.js", loadMorrisEngine);
	// Load morris dependency 2
	function loadMorrisEngine() {
		loadScript("<?php echo ASSETS_URL; ?>/js/plugin/morris/morris.min.js", runMorrisCharts);
	}
	
	/*
	 * Run all morris chart on this page
	 */
	function runMorrisCharts(){

		if ($('#sales-graph').length){ 

			 Morris.Area({
			    element: 'sales-graph',
			    data: [
			      {period: '2010 Q1', iphone: 2666, ipad: null, itouch: 2647},
			      {period: '2010 Q2', iphone: 2778, ipad: 2294, itouch: 2441},
			      {period: '2010 Q3', iphone: 4912, ipad: 1969, itouch: 2501},
			      {period: '2010 Q4', iphone: 3767, ipad: 3597, itouch: 5689},
			      {period: '2011 Q1', iphone: 6810, ipad: 1914, itouch: 2293},
			      {period: '2011 Q2', iphone: 5670, ipad: 4293, itouch: 1881},
			      {period: '2011 Q3', iphone: 4820, ipad: 3795, itouch: 1588},
			      {period: '2011 Q4', iphone: 15073, ipad: 5967, itouch: 5175},
			      {period: '2012 Q1', iphone: 10687, ipad: 4460, itouch: 2028},
			      {period: '2012 Q2', iphone: 8432, ipad: 5713, itouch: 1791}
			    ],
			    xkey: 'period',
			    ykeys: ['iphone', 'ipad', 'itouch'],
			    labels: ['iPhone', 'iPad', 'iPod Touch'],
			    pointSize: 2,
			    hideHover: 'auto'
			  });
			  
			}
			
			// area graph
			if ($('#area-graph').length){ 
				Morris.Area({
				  element: 'area-graph',
				  data: [
				    {x: '2011 Q1', y: 3, z: 3},
				    {x: '2011 Q2', y: 2, z: 0},
				    {x: '2011 Q3', y: 0, z: 2},
				    {x: '2011 Q4', y: 4, z: 4}
				  ],
				  xkey: 'x',
				  ykeys: ['y', 'z'],
				  labels: ['Y', 'Z']
				});
			}
			
			// bar graph color
			if ($('#bar-graph').length){ 
				
				Morris.Bar({
				  element: 'bar-graph',
				  data: [
				    {x: '2011 Q1', y: 0},
				    {x: '2011 Q2', y: 1},
				    {x: '2011 Q3', y: 2},
				    {x: '2011 Q4', y: 3},
				    {x: '2012 Q1', y: 4},
				    {x: '2012 Q2', y: 5},
				    {x: '2012 Q3', y: 6},
				    {x: '2012 Q4', y: 7},
				    {x: '2013 Q1', y: 8}
				  ],
				  xkey: 'x',
				  ykeys: ['y'],
				  labels: ['Y'],
				  barColors: function (row, series, type) {
				    if (type === 'bar') {
				      var red = Math.ceil(150 * row.y / this.ymax);
				      return 'rgb(' + red + ',0,0)';
				    }
				    else {
				      return '#000';
				    }
				  }
				});
			
			}
			
			
			
			// Use Morris.Bar
			if ($('#normal-bar-graph').length){ 
				
				Morris.Bar({
				  element: 'normal-bar-graph',
				  data: [
				    {x: '2011 Q1', y: 3, z: 2, a: 3},
				    {x: '2011 Q2', y: 2, z: null, a: 1},
				    {x: '2011 Q3', y: 0, z: 2, a: 4},
				    {x: '2011 Q4', y: 2, z: 4, a: 3}
				  ],
				  xkey: 'x',
				  ykeys: ['y', 'z', 'a'],
				  labels: ['Y', 'Z', 'A']
				});
			
			}
			
			
			// Use Morris.Bar 2
			if ($('#noline-bar-graph').length){ 
				Morris.Bar({
				  element: 'noline-bar-graph',
				  axes: false,
				  data: [
				    {x: '2011 Q1', y: 3, z: 2, a: 3},
				    {x: '2011 Q2', y: 2, z: null, a: 1},
				    {x: '2011 Q3', y: 0, z: 2, a: 4},
				    {x: '2011 Q4', y: 2, z: 4, a: 3}
				  ],
				  xkey: 'x',
				  ykeys: ['y', 'z', 'a'],
				  labels: ['Y', 'Z', 'A']
				});
			}
			
			/* data stolen from http://howmanyleft.co.uk/vehicle/jaguar_'e'_type */
			if ($('#year-graph').length){ 
				var day_data = [
				  {"period": "2012-10-01", "licensed": 3407, "sorned": 660},
				  {"period": "2012-09-30", "licensed": 3351, "sorned": 629},
				  {"period": "2012-09-29", "licensed": 3269, "sorned": 618},
				  {"period": "2012-09-20", "licensed": 3246, "sorned": 661},
				  {"period": "2012-09-19", "licensed": 3257, "sorned": 667},
				  {"period": "2012-09-18", "licensed": 3248, "sorned": 627},
				  {"period": "2012-09-17", "licensed": 3171, "sorned": 660},
				  {"period": "2012-09-16", "licensed": 3171, "sorned": 676},
				  {"period": "2012-09-15", "licensed": 3201, "sorned": 656},
				  {"period": "2012-09-10", "licensed": 3215, "sorned": 622}
				];
				Morris.Line({
				  element: 'year-graph',
				  data: day_data,
				  xkey: 'period',
				  ykeys: ['licensed', 'sorned'],
				  labels: ['Licensed', 'SORN']
				})
			}
			
			
			// decimal data
			if ($('#decimal-graph').length){ 
				var decimal_data = [];
				for (var x = 0; x <= 360; x += 10) {
				  decimal_data.push({
				    x: x,
				    y: Math.sin(Math.PI * x / 180).toFixed(4)
				  });
				}
				window.m = Morris.Line({
				  element: 'decimal-graph',
				  data: decimal_data,
				  xkey: 'x',
				  ykeys: ['y'],
				  labels: ['sin(x)'],
				  parseTime: false,
				  hoverCallback: function (index, options) {
				    var row = options.data[index];
				    return "sin(" + row.x + ") = " + row.y;
				  },
				  xLabelMargin: 10
				});
			}
			
			
			// donut
			if ($('#donut-graph').length){ 
				Morris.Donut({
				  element: 'donut-graph',
				  data: [
				    {value: 70, label: 'foo'},
				    {value: 15, label: 'bar'},
				    {value: 10, label: 'baz'},
				    {value: 5, label: 'A really really long label'}
				  ],
				  formatter: function (x) { return x + "%"}
				});
			}
			
			// time formatter
			if ($('#time-graph').length){ 
				var week_data = [
				  {"period": "2011 W27", "licensed": 3407, "sorned": 660},
				  {"period": "2011 W26", "licensed": 3351, "sorned": 629},
				  {"period": "2011 W25", "licensed": 3269, "sorned": 618},
				  {"period": "2011 W24", "licensed": 3246, "sorned": 661},
				  {"period": "2011 W23", "licensed": 3257, "sorned": 667},
				  {"period": "2011 W22", "licensed": 3248, "sorned": 627},
				  {"period": "2011 W21", "licensed": 3171, "sorned": 660},
				  {"period": "2011 W20", "licensed": 3171, "sorned": 676},
				  {"period": "2011 W19", "licensed": 3201, "sorned": 656},
				  {"period": "2011 W18", "licensed": 3215, "sorned": 622},
				  {"period": "2011 W17", "licensed": 3148, "sorned": 632},
				  {"period": "2011 W16", "licensed": 3155, "sorned": 681},
				  {"period": "2011 W15", "licensed": 3190, "sorned": 667},
				  {"period": "2011 W14", "licensed": 3226, "sorned": 620},
				  {"period": "2011 W13", "licensed": 3245, "sorned": null},
				  {"period": "2011 W12", "licensed": 3289, "sorned": null},
				  {"period": "2011 W11", "licensed": 3263, "sorned": null},
				  {"period": "2011 W10", "licensed": 3189, "sorned": null},
				  {"period": "2011 W09", "licensed": 3079, "sorned": null},
				  {"period": "2011 W08", "licensed": 3085, "sorned": null},
				  {"period": "2011 W07", "licensed": 3055, "sorned": null},
				  {"period": "2011 W06", "licensed": 3063, "sorned": null},
				  {"period": "2011 W05", "licensed": 2943, "sorned": null},
				  {"period": "2011 W04", "licensed": 2806, "sorned": null},
				  {"period": "2011 W03", "licensed": 2674, "sorned": null},
				  {"period": "2011 W02", "licensed": 1702, "sorned": null},
				  {"period": "2011 W01", "licensed": 1732, "sorned": null}
				];
				Morris.Line({
				  element: 'time-graph',
				  data: week_data,
				  xkey: 'period',
				  ykeys: ['licensed', 'sorned'],
				  labels: ['Licensed', 'SORN'],
				  events: [
				    '2011-04',
				    '2011-08'
				  ]
				});
			}
			
			// negative value
			if ($('#graph-B').length){ 
				var neg_data = [
				  {"period": "2011-08-12", "a": 100},
				  {"period": "2011-03-03", "a": 75},
				  {"period": "2010-08-08", "a": 50},
				  {"period": "2010-05-10", "a": 25},
				  {"period": "2010-03-14", "a": 0},
				  {"period": "2010-01-10", "a": -25},
				  {"period": "2009-12-10", "a": -50},
				  {"period": "2009-10-07", "a": -75},
				  {"period": "2009-09-25", "a": -100}
				];
				Morris.Line({
				  element: 'graph-B',
				  data: neg_data,
				  xkey: 'period',
				  ykeys: ['a'],
				  labels: ['Series A'],
				  units: '%'
				});
			}
			
			// no grid
			/* data stolen from http://howmanyleft.co.uk/vehicle/jaguar_'e'_type */
			if ($('#nogrid-graph').length){ 
				var day_data = [
				  {"period": "2012-10-01", "licensed": 3407, "sorned": 660},
				  {"period": "2012-09-30", "licensed": 3351, "sorned": 629},
				  {"period": "2012-09-29", "licensed": 3269, "sorned": 618},
				  {"period": "2012-09-20", "licensed": 3246, "sorned": 661},
				  {"period": "2012-09-19", "licensed": 3257, "sorned": 667},
				  {"period": "2012-09-18", "licensed": 3248, "sorned": 627},
				  {"period": "2012-09-17", "licensed": 3171, "sorned": 660},
				  {"period": "2012-09-16", "licensed": 3171, "sorned": 676},
				  {"period": "2012-09-15", "licensed": 3201, "sorned": 656},
				  {"period": "2012-09-10", "licensed": 3215, "sorned": 622}
				];
				Morris.Line({
				  element: 'nogrid-graph',
				  grid: false,
				  data: day_data,
				  xkey: 'period',
				  ykeys: ['licensed', 'sorned'],
				  labels: ['Licensed', 'SORN']
				});
			}
			
			// non-continus data
			/* data stolen from http://howmanyleft.co.uk/vehicle/jaguar_'e'_type */
			if ($('#non-continu-graph').length){ 
				var day_data = [
				  {"period": "2012-10-01", "licensed": 3407},
				  {"period": "2012-09-30", "sorned": 0},
				  {"period": "2012-09-29", "sorned": 618},
				  {"period": "2012-09-20", "licensed": 3246, "sorned": 661},
				  {"period": "2012-09-19", "licensed": 3257, "sorned": null},
				  {"period": "2012-09-18", "licensed": 3248, "other": 1000},
				  {"period": "2012-09-17", "sorned": 0},
				  {"period": "2012-09-16", "sorned": 0},
				  {"period": "2012-09-15", "licensed": 3201, "sorned": 656},
				  {"period": "2012-09-10", "licensed": 3215}
				];
				Morris.Line({
				  element: 'non-continu-graph',
				  data: day_data,
				  xkey: 'period',
				  ykeys: ['licensed', 'sorned', 'other'],
				  labels: ['Licensed', 'SORN', 'Other'],
				  /* custom label formatting with `xLabelFormat` */
				  xLabelFormat: function(d) { return (d.getMonth()+1)+'/'+d.getDate()+'/'+d.getFullYear(); },
				  /* setting `xLabels` is recommended when using xLabelFormat */
				  xLabels: 'day'
				});
			}
			
			// non date data
			if ($('#non-date-graph').length){ 
				var day_data = [
				  {"elapsed": "I", "value": 34},
				  {"elapsed": "II", "value": 24},
				  {"elapsed": "III", "value": 3},
				  {"elapsed": "IV", "value": 12},
				  {"elapsed": "V", "value": 13},
				  {"elapsed": "VI", "value": 22},
				  {"elapsed": "VII", "value": 5},
				  {"elapsed": "VIII", "value": 26},
				  {"elapsed": "IX", "value": 12},
				  {"elapsed": "X", "value": 19}
				];
				Morris.Line({
				  element: 'non-date-graph',
				  data: day_data,
				  xkey: 'elapsed',
				  ykeys: ['value'],
				  labels: ['value'],
				  parseTime: false
				});
			}
			
			//stacked bar
			if ($('#stacked-bar-graph').length){ 
				Morris.Bar({
				  element: 'stacked-bar-graph',
				  axes: false,
				  grid: false,
				  data: [
				    {x: '2011 Q1', y: 3, z: 2, a: 3},
				    {x: '2011 Q2', y: 2, z: null, a: 1},
				    {x: '2011 Q3', y: 0, z: 2, a: 4},
				    {x: '2011 Q4', y: 2, z: 4, a: 3}
				  ],
				  xkey: 'x',
				  ykeys: ['y', 'z', 'a'],
				  labels: ['Y', 'Z', 'A'],
				  stacked: true
				});
			}
			
			// interval 
			
			if ($('#interval-graph').length){ 
				
				var nReloads = 0;
				function data(offset) {
				  var ret = [];
				  for (var x = 0; x <= 360; x += 10) {
				    var v = (offset + x) % 360;
				    ret.push({
				      x: x,
				      y: Math.sin(Math.PI * v / 180).toFixed(4),
				      z: Math.cos(Math.PI * v / 180).toFixed(4)
				    });
				  }
				  return ret;
				}
				var graph = Morris.Line({
				    element: 'interval-graph',
				    data: data(0),
				    xkey: 'x',
				    ykeys: ['y', 'z'],
				    labels: ['sin()', 'cos()'],
				    parseTime: false,
				    ymin: -1.0,
				    ymax: 1.0,
				    hideHover: true
				});
				function update() {
				  nReloads++;
				  graph.setData(data(5 * nReloads));
				  $('#reloadStatus').text(nReloads + ' reloads');
				}
				setInterval(update, 100);
			}
		
	}
	
	//setup_flots();
	/* end flot charts */	
	
</script>
