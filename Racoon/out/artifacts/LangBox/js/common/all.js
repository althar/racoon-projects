/*
 * Dropit v1.0
 * http://dev7studios.com/dropit
 *
 * Copyright 2012, Dev7studios
 * Free to use and abuse under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 */


;(function($) {

    $.fn.dropit = function(method) {

        var methods = {

            init : function(options) {
                this.dropit.settings = $.extend({}, this.dropit.defaults, options);
                return this.each(function() {
                    var $el = $(this),
                         el = this,
                         settings = $.fn.dropit.settings;
                    
                    // Hide initial submenus     
                    $el.addClass('dropit')
                    .find('>'+ settings.triggerParentEl +':has('+ settings.submenuEl +')').addClass('dropit-trigger')
                    .find(settings.submenuEl).addClass('dropit-submenu').hide();
                    
                    // Open on click
                    $el.on(settings.action, settings.triggerParentEl +':has('+ settings.submenuEl +') > '+ settings.triggerEl +'', function(){
                        if($(this).parents(settings.triggerParentEl).hasClass('dropit-open')) return false;
                        settings.beforeHide.call(this);
                        $('.dropit-open').removeClass('dropit-open').find('.dropit-submenu').hide();
                        settings.afterHide.call(this);
                        settings.beforeShow.call(this);
                        $(this).parents(settings.triggerParentEl).addClass('dropit-open').find(settings.submenuEl).show();
                        settings.afterShow.call(this);
                        return false;
                    });
                    
                    // Close if outside click
                    $(document).on('click', function(){
                        settings.beforeHide.call(this);
                        $('.dropit-open').removeClass('dropit-open').find('.dropit-submenu').hide();
                        settings.afterHide.call(this);
                    });
                    
                    settings.afterLoad.call(this);
                });
            }
            
        }

        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error( 'Method "' +  method + '" does not exist in dropit plugin!');
        }

    }

    $.fn.dropit.defaults = {
        action: 'click', // The open action for the trigger
        submenuEl: 'ul', // The submenu element
        triggerEl: 'a', // The trigger element
        triggerParentEl: 'li', // The trigger parent element
        afterLoad: function(){}, // Triggers when plugin has loaded
        beforeShow: function(){}, // Triggers before submenu is shown
        afterShow: function(){}, // Triggers after submenu is shown
        beforeHide: function(){}, // Triggers before submenu is hidden
        afterHide: function(){} // Triggers before submenu is hidden
    }

    $.fn.dropit.settings = {}

})(jQuery);
/*

Uniform v2.1.0
Copyright © 2009 Josh Pyles / Pixelmatrix Design LLC
http://pixelmatrixdesign.com

Requires jQuery 1.3 or newer

Much thanks to Thomas Reynolds and Buck Wilson for their help and advice on
this.

Disabling text selection is made possible by Mathias Bynens
<http://mathiasbynens.be/> and his noSelect plugin.
<https://github.com/mathiasbynens/jquery-noselect>, which is embedded.

Also, thanks to David Kaneda and Eugene Bond for their contributions to the
plugin.

Tyler Akins has also rewritten chunks of the plugin, helped close many issues,
and ensured version 2 got out the door.

License:
MIT License - http://www.opensource.org/licenses/mit-license.php

Enjoy!

*/
/*global jQuery, window, document, navigator*/


(function ($, undef) {
	"use strict";

	/**
	 * Use .prop() if jQuery supports it, otherwise fall back to .attr()
	 *
	 * @param jQuery $el jQuery'd element on which we're calling attr/prop
	 * @param ... All other parameters are passed to jQuery's function
	 * @return The result from jQuery
	 */
	function attrOrProp($el) {
		var args = Array.prototype.slice.call(arguments, 1);

		if ($el.prop) {
			// jQuery 1.6+
			return $el.prop.apply($el, args);
		}

		// jQuery 1.5 and below
		return $el.attr.apply($el, args);
	}

	/**
	 * For backwards compatibility with older jQuery libraries, only bind
	 * one thing at a time.  Also, this function adds our namespace to
	 * events in one consistent location, shrinking the minified code.
	 *
	 * The properties on the events object are the names of the events
	 * that we are supposed to add to.  It can be a space separated list.
	 * The namespace will be added automatically.
	 *
	 * @param jQuery $el
	 * @param Object options Uniform options for this element
	 * @param Object events Events to bind, properties are event names
	 */
	function bindMany($el, options, events) {
		var name, namespaced;

		for (name in events) {
			if (events.hasOwnProperty(name)) {
				namespaced = name.replace(/ |$/g, options.eventNamespace);
				$el.bind(namespaced, events[name]);
			}
		}
	}

	/**
	 * Bind the hover, active, focus, and blur UI updates
	 *
	 * @param jQuery $el Original element
	 * @param jQuery $target Target for the events (our div/span)
	 * @param Object options Uniform options for the element $target
	 */
	function bindUi($el, $target, options) {
		bindMany($el, options, {
			focus: function () {
				$target.addClass(options.focusClass);
			},
			blur: function () {
				$target.removeClass(options.focusClass);
				$target.removeClass(options.activeClass);
			},
			mouseenter: function () {
				$target.addClass(options.hoverClass);
			},
			mouseleave: function () {
				$target.removeClass(options.hoverClass);
				$target.removeClass(options.activeClass);
			},
			"mousedown touchbegin": function () {
				if (!$el.is(":disabled")) {
					$target.addClass(options.activeClass);
				}
			},
			"mouseup touchend": function () {
				$target.removeClass(options.activeClass);
			}
		});
	}

	/**
	 * Remove the hover, focus, active classes.
	 *
	 * @param jQuery $el Element with classes
	 * @param Object options Uniform options for the element
	 */
	function classClearStandard($el, options) {
		$el.removeClass(options.hoverClass + " " + options.focusClass + " " + options.activeClass);
	}

	/**
	 * Add or remove a class, depending on if it's "enabled"
	 *
	 * @param jQuery $el Element that has the class added/removed
	 * @param String className Class or classes to add/remove
	 * @param Boolean enabled True to add the class, false to remove
	 */
	function classUpdate($el, className, enabled) {
		if (enabled) {
			$el.addClass(className);
		} else {
			$el.removeClass(className);
		}
	}

	/**
	 * Updating the "checked" property can be a little tricky.  This
	 * changed in jQuery 1.6 and now we can pass booleans to .prop().
	 * Prior to that, one either adds an attribute ("checked=checked") or
	 * removes the attribute.
	 *
	 * @param jQuery $tag Our Uniform span/div
	 * @param jQuery $el Original form element
	 * @param Object options Uniform options for this element
	 */
	function classUpdateChecked($tag, $el, options) {
		var c = "checked",
			isChecked = $el.is(":" + c);

		if ($el.prop) {
			// jQuery 1.6+
			$el.prop(c, isChecked);
		} else {
			// jQuery 1.5 and below
			if (isChecked) {
				$el.attr(c, c);
			} else {
				$el.removeAttr(c);
			}
		}

		classUpdate($tag, options.checkedClass, isChecked);
	}

	/**
	 * Set or remove the "disabled" class for disabled elements, based on
	 * if the 
	 *
	 * @param jQuery $tag Our Uniform span/div
	 * @param jQuery $el Original form element
	 * @param Object options Uniform options for this element
	 */
	function classUpdateDisabled($tag, $el, options) {
		classUpdate($tag, options.disabledClass, $el.is(":disabled"));
	}

	/**
	 * Wrap an element inside of a container or put the container next
	 * to the element.  See the code for examples of the different methods.
	 *
	 * Returns the container that was added to the HTML.
	 *
	 * @param jQuery $el Element to wrap
	 * @param jQuery $container Add this new container around/near $el
	 * @param String method One of "after", "before" or "wrap"
	 * @return $container after it has been cloned for adding to $el
	 */
	function divSpanWrap($el, $container, method) {
		switch (method) {
		case "after":
			// Result:  <element /> <container />
			$el.after($container);
			return $el.next();
		case "before":
			// Result:  <container /> <element />
			$el.before($container);
			return $el.prev();
		case "wrap":
			// Result:  <container> <element /> </container>
			$el.wrap($container);
			return $el.parent();
		}

		return null;
	}


	/**
	 * Create a div/span combo for uniforming an element
	 *
	 * @param jQuery $el Element to wrap
	 * @param Object options Options for the element, set by the user
	 * @param Object divSpanConfig Options for how we wrap the div/span
	 * @return Object Contains the div and span as properties
	 */
	function divSpan($el, options, divSpanConfig) {
		var $div, $span, id;

		if (!divSpanConfig) {
			divSpanConfig = {};
		}

		divSpanConfig = $.extend({
			bind: {},
			divClass: null,
			divWrap: "wrap",
			spanClass: null,
			spanHtml: null,
			spanWrap: "wrap"
		}, divSpanConfig);

		$div = $('<div />');
		$span = $('<span />');

		// Automatically hide this div/span if the element is hidden.
		// Do not hide if the element is hidden because a parent is hidden.
		if (options.autoHide && $el.is(':hidden') && $el.css('display') === 'none') {
			$div.hide();
		}

		if (divSpanConfig.divClass) {
			$div.addClass(divSpanConfig.divClass);
		}

		if (options.wrapperClass) {
			$div.addClass(options.wrapperClass);
		}

		if (divSpanConfig.spanClass) {
			$span.addClass(divSpanConfig.spanClass);
		}

		id = attrOrProp($el, 'id');

		if (options.useID && id) {
			attrOrProp($div, 'id', options.idPrefix + '-' + id);
		}

		if (divSpanConfig.spanHtml) {
			$span.html(divSpanConfig.spanHtml);
		}

		$div = divSpanWrap($el, $div, divSpanConfig.divWrap);
		$span = divSpanWrap($el, $span, divSpanConfig.spanWrap);
		classUpdateDisabled($div, $el, options);
		return {
			div: $div,
			span: $span
		};
	}


	/**
	 * Wrap an element with a span to apply a global wrapper class
	 *
	 * @param jQuery $el Element to wrap
	 * @param object options
	 * @return jQuery Wrapper element
	 */
	function wrapWithWrapperClass($el, options) {
		var $span;

		if (!options.wrapperClass) {
			return null;
		}

		$span = $('<span />').addClass(options.wrapperClass);
		$span = divSpanWrap($el, $span, "wrap");
		return $span;
	}


	/**
	 * Test if high contrast mode is enabled.
	 *
	 * In high contrast mode, background images can not be set and
	 * they are always returned as 'none'.
	 *
	 * @return boolean True if in high contrast mode
	 */
	function highContrast() {
		var c, $div, el, rgb;

		// High contrast mode deals with white and black
		rgb = 'rgb(120,2,153)';
		$div = $('<div style="width:0;height:0;color:' + rgb + '">');
		$('body').append($div);
		el = $div.get(0);

		// $div.css() will get the style definition, not
		// the actually displaying style
		if (window.getComputedStyle) {
			c = window.getComputedStyle(el, '').color;
		} else {
			c = (el.currentStyle || el.style || {}).color;
		}

		$div.remove();
		return c.replace(/ /g, '') !== rgb;
	}


	/**
	 * Change text into safe HTML
	 *
	 * @param String text
	 * @return String HTML version
	 */
	function htmlify(text) {
		if (!text) {
			return "";
		}

		return $('<span />').text(text).html();
	}

	/**
	 * If not MSIE, return false.
	 * If it is, return the version number.
	 *
	 * @return false|number
	 */
	function isMsie() {
		return navigator.cpuClass && !navigator.product;
	}

	/**
	 * Return true if this version of IE allows styling
	 *
	 * @return boolean
	 */
	function isMsieSevenOrNewer() {
		if (typeof window.XMLHttpRequest !== 'undefined') {
			return true;
		}

		return false;
	}

	/**
	 * Test if the element is a multiselect
	 *
	 * @param jQuery $el Element
	 * @return boolean true/false
	 */
	function isMultiselect($el) {
		var elSize;

		if ($el[0].multiple) {
			return true;
		}

		elSize = attrOrProp($el, "size");

		if (!elSize || elSize <= 1) {
			return false;
		}

		return true;
	}

	/**
	 * Meaningless utility function.  Used mostly for improving minification.
	 *
	 * @return false
	 */
	function returnFalse() {
		return false;
	}

	/**
	 * noSelect plugin, very slightly modified
	 * http://mths.be/noselect v1.0.3
	 *
	 * @param jQuery $elem Element that we don't want to select
	 * @param Object options Uniform options for the element
	 */
	function noSelect($elem, options) {
		var none = 'none';
		bindMany($elem, options, {
			'selectstart dragstart mousedown': returnFalse
		});

		$elem.css({
			MozUserSelect: none,
			msUserSelect: none,
			webkitUserSelect: none,
			userSelect: none
		});
	}

	/**
	 * Updates the filename tag based on the value of the real input
	 * element.
	 *
	 * @param jQuery $el Actual form element
	 * @param jQuery $filenameTag Span/div to update
	 * @param Object options Uniform options for this element
	 */
	function setFilename($el, $filenameTag, options) {
		var filename = $el.val();

		if (filename === "") {
			filename = options.fileDefaultHtml;
		} else {
			filename = filename.split(/[\/\\]+/);
			filename = filename[(filename.length - 1)];
		}

		$filenameTag.text(filename);
	}


	/**
	 * Function from jQuery to swap some CSS values, run a callback,
	 * then restore the CSS.  Modified to pass JSLint and handle undefined
	 * values with 'use strict'.
	 *
	 * @param jQuery $el Element
	 * @param object newCss CSS values to swap out
	 * @param Function callback Function to run
	 */
	function swap($elements, newCss, callback) {
		var restore, item;

		restore = [];

		$elements.each(function () {
			var name;

			for (name in newCss) {
				if (Object.prototype.hasOwnProperty.call(newCss, name)) {
					restore.push({
						el: this,
						name: name,
						old: this.style[name]
					});

					this.style[name] = newCss[name];
				}
			}
		});

		callback();

		while (restore.length) {
			item = restore.pop();
			item.el.style[item.name] = item.old;
		}
	}


	/**
	 * The browser doesn't provide sizes of elements that are not visible.
	 * This will clone an element and add it to the DOM for calculations.
	 *
	 * @param jQuery $el
	 * @param String method
	 */
	function sizingInvisible($el, callback) {
		var targets;

		// We wish to target ourselves and any parents as long as
		// they are not visible
		targets = $el.parents();
		targets.push($el[0]);
		targets = targets.not(':visible');
		swap(targets, {
			visibility: "hidden",
			display: "block",
			position: "absolute"
		}, callback);
	}


	/**
	 * Standard way to unwrap the div/span combination from an element
	 *
	 * @param jQuery $el Element that we wish to preserve
	 * @param Object options Uniform options for the element
	 * @return Function This generated function will perform the given work
	 */
	function unwrapUnwrapUnbindFunction($el, options) {
		return function () {
			$el.unwrap().unwrap().unbind(options.eventNamespace);
		};
	}

	var allowStyling = true,  // False if IE6 or other unsupported browsers
		highContrastTest = false,  // Was the high contrast test ran?
		uniformHandlers = [  // Objects that take care of "unification"
			{
				// Buttons
				match: function ($el) {
					return $el.is("a, button, :submit, :reset, input[type='button']");
				},
				apply: function ($el, options) {
					var $div, defaultSpanHtml, ds, getHtml, doingClickEvent;
					defaultSpanHtml = options.submitDefaultHtml;

					if ($el.is(":reset")) {
						defaultSpanHtml = options.resetDefaultHtml;
					}

					if ($el.is("a, button")) {
						// Use the HTML inside the tag
						getHtml = function () {
							return $el.html() || defaultSpanHtml;
						};
					} else {
						// Use the value property of the element
						getHtml = function () {
							return htmlify(attrOrProp($el, "value")) || defaultSpanHtml;
						};
					}

					ds = divSpan($el, options, {
						divClass: options.buttonClass,
						spanHtml: getHtml(),
					});
					$div = ds.div;
					bindUi($el, $div, options);
					doingClickEvent = false;
					bindMany($div, options, {
						"click touchend": function () {
							var ev, res, target, href;

							if (doingClickEvent) {
								return;
							}

							if ($el.is(':disabled')) {
								return;
							}

							doingClickEvent = true;

							if ($el[0].dispatchEvent) {
								ev = document.createEvent("MouseEvents");
								ev.initEvent("click", true, true);
								res = $el[0].dispatchEvent(ev);

								if ($el.is('a') && res) {
									target = attrOrProp($el, 'target');
									href = attrOrProp($el, 'href');

									if (!target || target === '_self') {
										document.location.href = href;
									} else {
										window.open(href, target);
									}
								}
							} else {
								$el.click();
							}

							doingClickEvent = false;
						}
					});
					noSelect($div, options);
					return {
						remove: function () {
							// Move $el out
							$div.after($el);

							// Remove div and span
							$div.remove();

							// Unbind events
							$el.unbind(options.eventNamespace);
							return $el;
						},
						update: function () {
							classClearStandard($div, options);
							classUpdateDisabled($div, $el, options);
							$el.detach();
							ds.span.html(getHtml()).append($el);
						}
					};
				}
			},
			{
				// Checkboxes
				match: function ($el) {
					return $el.is(":checkbox");
				},
				apply: function ($el, options) {
					var ds, $div, $span;
					ds = divSpan($el, options, {
						divClass: options.checkboxClass
					});
					$div = ds.div;
					$span = ds.span;

					// Add focus classes, toggling, active, etc.
					bindUi($el, $div, options);
					bindMany($el, options, {
						"click touchend": function () {
							classUpdateChecked($span, $el, options);
						}
					});
					classUpdateChecked($span, $el, options);
					return {
						remove: unwrapUnwrapUnbindFunction($el, options),
						update: function () {
							classClearStandard($div, options);
							$span.removeClass(options.checkedClass);
							classUpdateChecked($span, $el, options);
							classUpdateDisabled($div, $el, options);
						}
					};
				}
			},
			{
				// File selection / uploads
				match: function ($el) {
					return $el.is(":file");
				},
				apply: function ($el, options) {
					var ds, $div, $filename, $button;

					// The "span" is the button
					ds = divSpan($el, options, {
						divClass: options.fileClass,
						spanClass: options.fileButtonClass,
						spanHtml: options.fileButtonHtml,
						spanWrap: "after"
					});
					$div = ds.div;
					$button = ds.span;
					$filename = $("<span />").html(options.fileDefaultHtml);
					$filename.addClass(options.filenameClass);
					$filename = divSpanWrap($el, $filename, "after");

					// Set the size
					if (!attrOrProp($el, "size")) {
						attrOrProp($el, "size", $div.width() / 10);
					}

					// Actions
					function filenameUpdate() {
						setFilename($el, $filename, options);
					}

					bindUi($el, $div, options);

					// Account for input saved across refreshes
					filenameUpdate();

					// IE7 doesn't fire onChange until blur or second fire.
					if (isMsie()) {
						// IE considers browser chrome blocking I/O, so it
						// suspends tiemouts until after the file has
						// been selected.
						bindMany($el, options, {
							click: function () {
								$el.trigger("change");
								setTimeout(filenameUpdate, 0);
							}
						});
					} else {
						// All other browsers behave properly
						bindMany($el, options, {
							change: filenameUpdate
						});
					}

					noSelect($filename, options);
					noSelect($button, options);
					return {
						remove: function () {
							// Remove filename and button
							$filename.remove();
							$button.remove();

							// Unwrap parent div, remove events
							return $el.unwrap().unbind(options.eventNamespace);
						},
						update: function () {
							classClearStandard($div, options);
							setFilename($el, $filename, options);
							classUpdateDisabled($div, $el, options);
						}
					};
				}
			},
			{
				// Input fields (text)
				match: function ($el) {
					if ($el.is("input")) {
						var t = (" " + attrOrProp($el, "type") + " ").toLowerCase(),
							allowed = " color date datetime datetime-local email month number password search tel text time url week ";
						return allowed.indexOf(t) >= 0;
					}

					return false;
				},
				apply: function ($el, options) {
					var elType, $wrapper;

					elType = attrOrProp($el, "type");
					$el.addClass(options.inputClass);
					$wrapper = wrapWithWrapperClass($el, options);
					bindUi($el, $el, options);

					if (options.inputAddTypeAsClass) {
						$el.addClass(elType);
					}

					return {
						remove: function () {
							$el.removeClass(options.inputClass);

							if (options.inputAddTypeAsClass) {
								$el.removeClass(elType);
							}

							if ($wrapper) {
								$el.unwrap();
							}
						},
						update: returnFalse
					};
				}
			},
			{
				// Radio buttons
				match: function ($el) {
					return $el.is(":radio");
				},
				apply: function ($el, options) {
					var ds, $div, $span;
					ds = divSpan($el, options, {
						divClass: options.radioClass
					});
					$div = ds.div;
					$span = ds.span;

					// Add classes for focus, handle active, checked
					bindUi($el, $div, options);
					bindMany($el, options, {
						"click touchend": function () {
							// Find all radios with the same name, then update
							// them with $.uniform.update() so the right
							// per-element options are used
							$.uniform.update($(':radio[name="' + attrOrProp($el, "name") + '"]'));
						}
					});
					classUpdateChecked($span, $el, options);
					return {
						remove: unwrapUnwrapUnbindFunction($el, options),
						update: function () {
							classClearStandard($div, options);
							classUpdateChecked($span, $el, options);
							classUpdateDisabled($div, $el, options);
						}
					};
				}
			},
			{
				// Select lists, but do not style multiselects here
				match: function ($el) {
					if ($el.is("select") && !isMultiselect($el)) {
						return true;
					}

					return false;
				},
				apply: function ($el, options) {
					var ds, $div, $span, origElemWidth;

					if (options.selectAutoWidth) {
						sizingInvisible($el, function () {
							origElemWidth = $el.width();
						});
					}

					ds = divSpan($el, options, {
						divClass: options.selectClass,
						spanHtml: ($el.find(":selected:first") || $el.find("option:first")).html(),
						spanWrap: "before"
					});
					$div = ds.div;
					$span = ds.span;

					if (options.selectAutoWidth) {
						// Use the width of the select and adjust the
						// span and div accordingly
						sizingInvisible($el, function () {
							// Force "display: block" - related to bug #287
							swap($([ $span[0], $div[0] ]), {
								display: "block"
							}, function () {
								var spanPad;
								spanPad = $span.outerWidth() - $span.width();
								$div.width(origElemWidth + spanPad);
								$span.width(origElemWidth);
							});
						});
					} else {
						// Force the select to fill the size of the div
						$div.addClass('fixedWidth');
					}

					// Take care of events
					bindUi($el, $div, options);
					bindMany($el, options, {
						change: function () {
							$span.html($el.find(":selected").html());
							$div.removeClass(options.activeClass);
						},
						"click touchend": function () {
							// IE7 and IE8 may not update the value right
							// until after click event - issue #238
							var selHtml = $el.find(":selected").html();

							if ($span.html() !== selHtml) {
								// Change was detected
								// Fire the change event on the select tag
								$el.trigger('change');
							}
						},
						keyup: function () {
							$span.html($el.find(":selected").html());
						}
					});
					noSelect($span, options);
					return {
						remove: function () {
							// Remove sibling span
							$span.remove();

							// Unwrap parent div
							$el.unwrap().unbind(options.eventNamespace);
							return $el;
						},
						update: function () {
							if (options.selectAutoWidth) {
								// Easier to remove and reapply formatting
								$.uniform.restore($el);
								$el.uniform(options);
							} else {
								classClearStandard($div, options);

								// Reset current selected text
								$span.html($el.find(":selected").html());
								classUpdateDisabled($div, $el, options);
							}
						}
					};
				}
			},
			{
				// Select lists - multiselect lists only
				match: function ($el) {
					if ($el.is("select") && isMultiselect($el)) {
						return true;
					}

					return false;
				},
				apply: function ($el, options) {
					var $wrapper;

					$el.addClass(options.selectMultiClass);
					$wrapper = wrapWithWrapperClass($el, options);
					bindUi($el, $el, options);

					return {
						remove: function () {
							$el.removeClass(options.selectMultiClass);

							if ($wrapper) {
								$el.unwrap();
							}
						},
						update: returnFalse
					};
				}
			},
			{
				// Textareas
				match: function ($el) {
					return $el.is("textarea");
				},
				apply: function ($el, options) {
					var $wrapper;

					$el.addClass(options.textareaClass);
					$wrapper = wrapWithWrapperClass($el, options);
					bindUi($el, $el, options);

					return {
						remove: function () {
							$el.removeClass(options.textareaClass);

							if ($wrapper) {
								$el.unwrap();
							}
						},
						update: returnFalse
					};
				}
			}
		];

	// IE6 can't be styled - can't set opacity on select
	if (isMsie() && !isMsieSevenOrNewer()) {
		allowStyling = false;
	}

	$.uniform = {
		// Default options that can be overridden globally or when uniformed
		// globally:  $.uniform.defaults.fileButtonHtml = "Pick A File";
		// on uniform:  $('input').uniform({fileButtonHtml: "Pick a File"});
		defaults: {
			activeClass: "active",
			autoHide: true,
			buttonClass: "button",
			checkboxClass: "checker",
			checkedClass: "checked",
			disabledClass: "disabled",
			eventNamespace: ".uniform",
			fileButtonClass: "action",
			fileButtonHtml: "Choose File",
			fileClass: "uploader",
			fileDefaultHtml: "No file selected",
			filenameClass: "filename",
			focusClass: "focus",
			hoverClass: "hover",
			idPrefix: "uniform",
			inputAddTypeAsClass: true,
			inputClass: "uniform-input",
			radioClass: "radio",
			resetDefaultHtml: "Reset",
			resetSelector: false,  // We'll use our own function when you don't specify one
			selectAutoWidth: true,
			selectClass: "selector",
			selectMultiClass: "uniform-multiselect",
			submitDefaultHtml: "Submit",  // Only text allowed
			textareaClass: "uniform",
			useID: true,
			wrapperClass: null
		},

		// All uniformed elements - DOM objects
		elements: []
	};

	$.fn.uniform = function (options) {
		var el = this;
		options = $.extend({}, $.uniform.defaults, options);

		// If we are in high contrast mode, do not allow styling
		if (!highContrastTest) {
			highContrastTest = true;

			if (highContrast()) {
				allowStyling = false;
			}
		}

		// Only uniform on browsers that work
		if (!allowStyling) {
			return this;
		}

		// Code for specifying a reset button
		if (options.resetSelector) {
			$(options.resetSelector).mouseup(function () {
				window.setTimeout(function () {
					$.uniform.update(el);
				}, 10);
			});
		}

		return this.each(function () {
			var $el = $(this), i, handler, callbacks;

			// Avoid uniforming elements already uniformed - just update
			if ($el.data("uniformed")) {
				$.uniform.update($el);
				return;
			}

			// See if we have any handler for this type of element
			for (i = 0; i < uniformHandlers.length; i = i + 1) {
				handler = uniformHandlers[i];

				if (handler.match($el, options)) {
					callbacks = handler.apply($el, options);
					$el.data("uniformed", callbacks);

					// Store element in our global array
					$.uniform.elements.push($el.get(0));
					return;
				}
			}

			// Could not style this element
		});
	};

	$.uniform.restore = $.fn.uniform.restore = function (elem) {
		if (elem === undef) {
			elem = $.uniform.elements;
		}

		$(elem).each(function () {
			var $el = $(this), index, elementData;
			elementData = $el.data("uniformed");

			// Skip elements that are not uniformed
			if (!elementData) {
				return;
			}

			// Unbind events, remove additional markup that was added
			elementData.remove();

			// Remove item from list of uniformed elements
			index = $.inArray(this, $.uniform.elements);

			if (index >= 0) {
				$.uniform.elements.splice(index, 1);
			}

			$el.removeData("uniformed");
		});
	};

	$.uniform.update = $.fn.uniform.update = function (elem) {
		if (elem === undef) {
			elem = $.uniform.elements;
		}

		$(elem).each(function () {
			var $el = $(this), elementData;
			elementData = $el.data("uniformed");

			// Skip elements that are not uniformed
			if (!elementData) {
				return;
			}

			elementData.update($el, elementData.options);
		});
	};
}(jQuery));
/*
 *  jQuery OwlCarousel v1.31
 *
 *  Copyright (c) 2013 Bartosz Wojciechowski
 *  http://www.owlgraphic.com/owlcarousel/
 *
 *  Licensed under MIT
 *
 */


if ( typeof Object.create !== "function" ) {
  Object.create = function( obj ) {
    function F() {};
    F.prototype = obj;
    return new F();
  };
}
(function( $, window, document, undefined ) {

  var Carousel = {
    init :function(options, el){
      var base = this;

      base.$elem = $(el);

      // options passed via js override options passed via data attributes
      base.options = $.extend({}, $.fn.owlCarousel.options, base.$elem.data(), options);

      base.userOptions = options;
      base.loadContent();
    },

    loadContent : function(){
      var base = this;

      if (typeof base.options.beforeInit === "function") {
        base.options.beforeInit.apply(this,[base.$elem]);
      }

      if (typeof base.options.jsonPath === "string") {
        var url = base.options.jsonPath;

        function getData(data) {
          if (typeof base.options.jsonSuccess === "function") {
            base.options.jsonSuccess.apply(this,[data]);
          } else {
            var content = "";
            for(var i in data["owl"]){
              content += data["owl"][i]["item"];
            }
            base.$elem.html(content);
          }
          base.logIn();
        }
        $.getJSON(url,getData);
      } else {
        base.logIn();
      }
    },

    logIn : function(action){
      var base = this;

      base.$elem.data("owl-originalStyles", base.$elem.attr("style"))
            .data("owl-originalClasses", base.$elem.attr("class"));

      base.$elem.css({opacity: 0});
      base.orignalItems = base.options.items;
      base.checkBrowser();
      base.wrapperWidth = 0;
      base.checkVisible;
      base.setVars();
    },

    setVars : function(){
      var base = this;
      if(base.$elem.children().length === 0){return false}
      base.baseClass();
      base.eventTypes();
      base.$userItems = base.$elem.children();
      base.itemsAmount = base.$userItems.length;
      base.wrapItems();
      base.$owlItems = base.$elem.find(".owl-item");
      base.$owlWrapper = base.$elem.find(".owl-wrapper");
      base.playDirection = "next";
      base.prevItem = 0;
      base.prevArr = [0];
      base.currentItem = 0;
      base.customEvents();
      base.onStartup();
    },

    onStartup : function(){
      var base = this;
      base.updateItems();
      base.calculateAll();
      base.buildControls();
      base.updateControls();
      base.response();
      base.moveEvents();
      base.stopOnHover();
      base.owlStatus();

      if(base.options.transitionStyle !== false){
        base.transitionTypes(base.options.transitionStyle);
      }
      if(base.options.autoPlay === true){
        base.options.autoPlay = 5000;
      }
      base.play();

      base.$elem.find(".owl-wrapper").css("display","block")

      if(!base.$elem.is(":visible")){
        base.watchVisibility();
      } else {
        base.$elem.css("opacity",1);
      }
      base.onstartup = false;
      base.eachMoveUpdate();
      if (typeof base.options.afterInit === "function") {
        base.options.afterInit.apply(this,[base.$elem]);
      }
    },

    eachMoveUpdate : function(){
      var base = this;

      if(base.options.lazyLoad === true){
        base.lazyLoad();
      }
      if(base.options.autoHeight === true){
        base.autoHeight();
      }
      base.onVisibleItems();

      if (typeof base.options.afterAction === "function") {
        base.options.afterAction.apply(this,[base.$elem]);
      }
    },

    updateVars : function(){
      var base = this;
      if(typeof base.options.beforeUpdate === "function") {
        base.options.beforeUpdate.apply(this,[base.$elem]);
      }
      base.watchVisibility();
      base.updateItems();
      base.calculateAll();
      base.updatePosition();
      base.updateControls();
      base.eachMoveUpdate();
      if(typeof base.options.afterUpdate === "function") {
        base.options.afterUpdate.apply(this,[base.$elem]);
      }
    },

    reload : function(elements){
      var base = this;
      setTimeout(function(){
        base.updateVars();
      },0)
    },

    watchVisibility : function(){
      var base = this;

      if(base.$elem.is(":visible") === false){
        base.$elem.css({opacity: 0});
        clearInterval(base.autoPlayInterval);
        clearInterval(base.checkVisible);
      } else {
        return false;
      }
      base.checkVisible = setInterval(function(){
        if (base.$elem.is(":visible")) {
          base.reload();
          base.$elem.animate({opacity: 1},200);
          clearInterval(base.checkVisible);
        }
      }, 500);
    },

    wrapItems : function(){
      var base = this;
      base.$userItems.wrapAll("<div class=\"owl-wrapper\">").wrap("<div class=\"owl-item\"></div>");
      base.$elem.find(".owl-wrapper").wrap("<div class=\"owl-wrapper-outer\">");
      base.wrapperOuter = base.$elem.find(".owl-wrapper-outer");
      base.$elem.css("display","block");
    },

    baseClass : function(){
      var base = this;
      var hasBaseClass = base.$elem.hasClass(base.options.baseClass);
      var hasThemeClass = base.$elem.hasClass(base.options.theme);

      if(!hasBaseClass){
        base.$elem.addClass(base.options.baseClass);
      }

      if(!hasThemeClass){
        base.$elem.addClass(base.options.theme);
      }
    },

    updateItems : function(){
      var base = this;

      if(base.options.responsive === false){
        return false;
      }
      if(base.options.singleItem === true){
        base.options.items = base.orignalItems = 1;
        base.options.itemsCustom = false;
        base.options.itemsDesktop = false;
        base.options.itemsDesktopSmall = false;
        base.options.itemsTablet = false;
        base.options.itemsTabletSmall = false;
        base.options.itemsMobile = false;
        return false;
      }

      var width = $(base.options.responsiveBaseWidth).width();

      if(width > (base.options.itemsDesktop[0] || base.orignalItems) ){
        base.options.items = base.orignalItems;
      }

      if(typeof(base.options.itemsCustom) !== 'undefined' && base.options.itemsCustom !== false){
        //Reorder array by screen size
        base.options.itemsCustom.sort(function(a,b){return a[0]-b[0];});
        for(var i in base.options.itemsCustom){
          if(typeof(base.options.itemsCustom[i]) !== 'undefined' && base.options.itemsCustom[i][0] <= width){
            base.options.items = base.options.itemsCustom[i][1];
          }
        }
      } else {

        if(width <= base.options.itemsDesktop[0] && base.options.itemsDesktop !== false){
          base.options.items = base.options.itemsDesktop[1];
        }

        if(width <= base.options.itemsDesktopSmall[0] && base.options.itemsDesktopSmall !== false){
          base.options.items = base.options.itemsDesktopSmall[1];
        }

        if(width <= base.options.itemsTablet[0]  && base.options.itemsTablet !== false){
          base.options.items = base.options.itemsTablet[1];
        }

        if(width <= base.options.itemsTabletSmall[0]  && base.options.itemsTabletSmall !== false){
          base.options.items = base.options.itemsTabletSmall[1];
        }

        if(width <= base.options.itemsMobile[0] && base.options.itemsMobile !== false){
          base.options.items = base.options.itemsMobile[1];
        }
      }

      //if number of items is less than declared
      if(base.options.items > base.itemsAmount && base.options.itemsScaleUp === true){
        base.options.items = base.itemsAmount;
      }
    },

    response : function(){
      var base = this,
        smallDelay;
      if(base.options.responsive !== true){
        return false
      }
      var lastWindowWidth = $(window).width();

      base.resizer = function(){
        if($(window).width() !== lastWindowWidth){
          if(base.options.autoPlay !== false){
            clearInterval(base.autoPlayInterval);
          }
          clearTimeout(smallDelay);
          smallDelay = setTimeout(function(){
            lastWindowWidth = $(window).width();
            base.updateVars();
          },base.options.responsiveRefreshRate);
        }
      }
      $(window).resize(base.resizer)
    },

    updatePosition : function(){
      var base = this;
      base.jumpTo(base.currentItem);
      if(base.options.autoPlay !== false){
        base.checkAp();
      }
    },

    appendItemsSizes : function(){
      var base = this;

      var roundPages = 0;
      var lastItem = base.itemsAmount - base.options.items;

      base.$owlItems.each(function(index){
        var $this = $(this);
        $this
          .css({"width": base.itemWidth})
          .data("owl-item",Number(index));

        if(index % base.options.items === 0 || index === lastItem){
          if(!(index > lastItem)){
            roundPages +=1;
          }
        }
        $this.data("owl-roundPages",roundPages)
      });
    },

    appendWrapperSizes : function(){
      var base = this;
      var width = 0;

      var width = base.$owlItems.length * base.itemWidth;

      base.$owlWrapper.css({
        "width": width*2,
        "left": 0
      });
      base.appendItemsSizes();
    },

    calculateAll : function(){
      var base = this;
      base.calculateWidth();
      base.appendWrapperSizes();
      base.loops();
      base.max();
    },

    calculateWidth : function(){
      var base = this;
      base.itemWidth = Math.round(base.$elem.width()/base.options.items)
    },

    max : function(){
      var base = this;
      var maximum = ((base.itemsAmount * base.itemWidth) - base.options.items * base.itemWidth) * -1;
      if(base.options.items > base.itemsAmount){
        base.maximumItem = 0;
        maximum = 0
        base.maximumPixels = 0;
      } else {
        base.maximumItem = base.itemsAmount - base.options.items;
        base.maximumPixels = maximum;
      }
      return maximum;
    },

    min : function(){
      return 0;
    },

    loops : function(){
      var base = this;

      base.positionsInArray = [0];
      base.pagesInArray = [];
      var prev = 0;
      var elWidth = 0;

      for(var i = 0; i<base.itemsAmount; i++){
        elWidth += base.itemWidth;
        base.positionsInArray.push(-elWidth);

        if(base.options.scrollPerPage === true){
          var item = $(base.$owlItems[i]);
          var roundPageNum = item.data("owl-roundPages");
          if(roundPageNum !== prev){
            base.pagesInArray[prev] = base.positionsInArray[i];
            prev = roundPageNum;
          }
        }
      }
    },

    buildControls : function(){
      var base = this;
      if(base.options.navigation === true || base.options.pagination === true){
        base.owlControls = $("<div class=\"owl-controls\"/>").toggleClass("clickable", !base.browser.isTouch).appendTo(base.$elem);
      }
      if(base.options.pagination === true){
        base.buildPagination();
      }
      if(base.options.navigation === true){
        base.buildButtons();
      }
    },

    buildButtons : function(){
      var base = this;
      var buttonsWrapper = $("<div class=\"owl-buttons\"/>")
      base.owlControls.append(buttonsWrapper);

      base.buttonPrev = $("<div/>",{
        "class" : "owl-prev",
        "html" : base.options.navigationText[0] || ""
        });

      base.buttonNext = $("<div/>",{
        "class" : "owl-next",
        "html" : base.options.navigationText[1] || ""
        });

      buttonsWrapper
      .append(base.buttonPrev)
      .append(base.buttonNext);

      buttonsWrapper.on("touchstart.owlControls mousedown.owlControls", "div[class^=\"owl\"]", function(event){
        event.preventDefault();
      })

      buttonsWrapper.on("touchend.owlControls mouseup.owlControls", "div[class^=\"owl\"]", function(event){
        event.preventDefault();
        if($(this).hasClass("owl-next")){
          base.next();
        } else{
          base.prev();
        }
      })
    },

    buildPagination : function(){
      var base = this;

      base.paginationWrapper = $("<div class=\"owl-pagination\"/>");
      base.owlControls.append(base.paginationWrapper);

      base.paginationWrapper.on("touchend.owlControls mouseup.owlControls", ".owl-page", function(event){
        event.preventDefault();
        if(Number($(this).data("owl-page")) !== base.currentItem){
          base.goTo( Number($(this).data("owl-page")), true);
        }
      });
    },

    updatePagination : function(){
      var base = this;
      if(base.options.pagination === false){
        return false;
      }

      base.paginationWrapper.html("");

      var counter = 0;
      var lastPage = base.itemsAmount - base.itemsAmount % base.options.items;

      for(var i = 0; i<base.itemsAmount; i++){
        if(i % base.options.items === 0){
          counter +=1;
          if(lastPage === i){
            var lastItem = base.itemsAmount - base.options.items;
          }
          var paginationButton = $("<div/>",{
            "class" : "owl-page"
            });
          var paginationButtonInner = $("<span></span>",{
            "text": base.options.paginationNumbers === true ? counter : "",
            "class": base.options.paginationNumbers === true ? "owl-numbers" : ""
          });
          paginationButton.append(paginationButtonInner);

          paginationButton.data("owl-page",lastPage === i ? lastItem : i);
          paginationButton.data("owl-roundPages",counter);

          base.paginationWrapper.append(paginationButton);
        }
      }
      base.checkPagination();
    },
    checkPagination : function(){
      var base = this;
      if(base.options.pagination === false){
        return false;
      }
      base.paginationWrapper.find(".owl-page").each(function(i,v){
        if($(this).data("owl-roundPages") === $(base.$owlItems[base.currentItem]).data("owl-roundPages") ){
          base.paginationWrapper
            .find(".owl-page")
            .removeClass("active");
          $(this).addClass("active");
        }
      });
    },

    checkNavigation : function(){
      var base = this;

      if(base.options.navigation === false){
        return false;
      }
      if(base.options.rewindNav === false){
        if(base.currentItem === 0 && base.maximumItem === 0){
          base.buttonPrev.addClass("disabled");
          base.buttonNext.addClass("disabled");
        } else if(base.currentItem === 0 && base.maximumItem !== 0){
          base.buttonPrev.addClass("disabled");
          base.buttonNext.removeClass("disabled");
        } else if (base.currentItem === base.maximumItem){
          base.buttonPrev.removeClass("disabled");
          base.buttonNext.addClass("disabled");
        } else if(base.currentItem !== 0 && base.currentItem !== base.maximumItem){
          base.buttonPrev.removeClass("disabled");
          base.buttonNext.removeClass("disabled");
        }
      }
    },

    updateControls : function(){
      var base = this;
      base.updatePagination();
      base.checkNavigation();
      if(base.owlControls){
        if(base.options.items >= base.itemsAmount){
          base.owlControls.hide();
        } else {
          base.owlControls.show();
        }
      }
    },

    destroyControls : function(){
      var base = this;
      if(base.owlControls){
        base.owlControls.remove();
      }
    },

    next : function(speed){
      var base = this;

      if(base.isTransition){
        return false;
      }

      base.currentItem += base.options.scrollPerPage === true ? base.options.items : 1;
      if(base.currentItem > base.maximumItem + (base.options.scrollPerPage == true ? (base.options.items - 1) : 0)){
        if(base.options.rewindNav === true){
          base.currentItem = 0;
          speed = "rewind";
        } else {
          base.currentItem = base.maximumItem;
          return false;
        }
      }
      base.goTo(base.currentItem,speed);
    },

    prev : function(speed){
      var base = this;

      if(base.isTransition){
        return false;
      }

      if(base.options.scrollPerPage === true && base.currentItem > 0 && base.currentItem < base.options.items){
        base.currentItem = 0
      } else {
        base.currentItem -= base.options.scrollPerPage === true ? base.options.items : 1;
      }
      if(base.currentItem < 0){
        if(base.options.rewindNav === true){
          base.currentItem = base.maximumItem;
          speed = "rewind"
        } else {
          base.currentItem =0;
          return false;
        }
      }
      base.goTo(base.currentItem,speed);
    },

    goTo : function(position,speed,drag){
      var base = this;

      if(base.isTransition){
        return false;
      }
      if(typeof base.options.beforeMove === "function") {
        base.options.beforeMove.apply(this,[base.$elem]);
      }
      if(position >= base.maximumItem){
        position = base.maximumItem;
      }
      else if( position <= 0 ){
        position = 0;
      }

      base.currentItem = base.owl.currentItem = position;
      if( base.options.transitionStyle !== false && drag !== "drag" && base.options.items === 1 && base.browser.support3d === true){
        base.swapSpeed(0)
        if(base.browser.support3d === true){
          base.transition3d(base.positionsInArray[position]);
        } else {
          base.css2slide(base.positionsInArray[position],1);
        }
        base.afterGo();
        base.singleItemTransition();

        return false;
      }
      var goToPixel = base.positionsInArray[position];

      if(base.browser.support3d === true){
        base.isCss3Finish = false;

        if(speed === true){
          base.swapSpeed("paginationSpeed");
          setTimeout(function() {
            base.isCss3Finish = true;
          }, base.options.paginationSpeed);

        } else if(speed === "rewind" ){
          base.swapSpeed(base.options.rewindSpeed);
          setTimeout(function() {
            base.isCss3Finish = true;
          }, base.options.rewindSpeed);

        } else {
          base.swapSpeed("slideSpeed");
          setTimeout(function() {
            base.isCss3Finish = true;
          }, base.options.slideSpeed);
        }
        base.transition3d(goToPixel);
      } else {
        if(speed === true){
          base.css2slide(goToPixel, base.options.paginationSpeed);
        } else if(speed === "rewind" ){
          base.css2slide(goToPixel, base.options.rewindSpeed);
        } else {
          base.css2slide(goToPixel, base.options.slideSpeed);
        }
      }
      base.afterGo();
    },

    jumpTo : function(position){
      var base = this;
      if(typeof base.options.beforeMove === "function") {
        base.options.beforeMove.apply(this,[base.$elem]);
      }
      if(position >= base.maximumItem || position === -1){
        position = base.maximumItem;
      }
      else if( position <= 0 ){
        position = 0;
      }
      base.swapSpeed(0)
      if(base.browser.support3d === true){
        base.transition3d(base.positionsInArray[position]);
      } else {
        base.css2slide(base.positionsInArray[position],1);
      }
      base.currentItem = base.owl.currentItem = position;
      base.afterGo();
    },

    afterGo : function(){
      var base = this;

      base.prevArr.push(base.currentItem);
      base.prevItem = base.owl.prevItem = base.prevArr[base.prevArr.length -2];
      base.prevArr.shift(0)

      if(base.prevItem !== base.currentItem){
        base.checkPagination();
        base.checkNavigation();
        base.eachMoveUpdate();

        if(base.options.autoPlay !== false){
          base.checkAp();
        }
      }
      if(typeof base.options.afterMove === "function" && base.prevItem !== base.currentItem) {
        base.options.afterMove.apply(this,[base.$elem]);
      }
    },

    stop : function(){
      var base = this;
      base.apStatus = "stop";
      clearInterval(base.autoPlayInterval);
    },

    checkAp : function(){
      var base = this;
      if(base.apStatus !== "stop"){
        base.play();
      }
    },

    play : function(){
      var base = this;
      base.apStatus = "play";
      if(base.options.autoPlay === false){
        return false;
      }
      clearInterval(base.autoPlayInterval);
      base.autoPlayInterval = setInterval(function(){
        base.next(true);
      },base.options.autoPlay);
    },

    swapSpeed : function(action){
      var base = this;
      if(action === "slideSpeed"){
        base.$owlWrapper.css(base.addCssSpeed(base.options.slideSpeed));
      } else if(action === "paginationSpeed" ){
        base.$owlWrapper.css(base.addCssSpeed(base.options.paginationSpeed));
      } else if(typeof action !== "string"){
        base.$owlWrapper.css(base.addCssSpeed(action));
      }
    },

    addCssSpeed : function(speed){
      var base = this;
      return {
        "-webkit-transition": "all "+ speed +"ms ease",
        "-moz-transition": "all "+ speed +"ms ease",
        "-o-transition": "all "+ speed +"ms ease",
        "transition": "all "+ speed +"ms ease"
      };
    },

    removeTransition : function(){
      return {
        "-webkit-transition": "",
        "-moz-transition": "",
        "-o-transition": "",
        "transition": ""
      };
    },

    doTranslate : function(pixels){
      return {
        "-webkit-transform": "translate3d("+pixels+"px, 0px, 0px)",
        "-moz-transform": "translate3d("+pixels+"px, 0px, 0px)",
        "-o-transform": "translate3d("+pixels+"px, 0px, 0px)",
        "-ms-transform": "translate3d("+pixels+"px, 0px, 0px)",
        "transform": "translate3d("+pixels+"px, 0px,0px)"
      };
    },

    transition3d : function(value){
      var base = this;
      base.$owlWrapper.css(base.doTranslate(value));
    },

    css2move : function(value){
      var base = this;
      base.$owlWrapper.css({"left" : value})
    },

    css2slide : function(value,speed){
      var base = this;

      base.isCssFinish = false;
      base.$owlWrapper.stop(true,true).animate({
        "left" : value
      }, {
        duration : speed || base.options.slideSpeed ,
        complete : function(){
          base.isCssFinish = true;
        }
      });
    },

    checkBrowser : function(){
      var base = this;

      //Check 3d support
      var translate3D = "translate3d(0px, 0px, 0px)",
        tempElem = document.createElement("div");

      tempElem.style.cssText= "  -moz-transform:"    + translate3D +
                  "; -ms-transform:"     + translate3D +
                  "; -o-transform:"      + translate3D +
                  "; -webkit-transform:" + translate3D +
                  "; transform:"         + translate3D;
      var regex = /translate3d\(0px, 0px, 0px\)/g,
        asSupport = tempElem.style.cssText.match(regex),
        support3d = (asSupport !== null && asSupport.length === 1);

      var isTouch = "ontouchstart" in window || navigator.msMaxTouchPoints;

      base.browser = {
        "support3d" : support3d,
        "isTouch" : isTouch
      }
    },

    moveEvents : function(){
      var base = this;
      if(base.options.mouseDrag !== false || base.options.touchDrag !== false){
        base.gestures();
        base.disabledEvents();
      }
    },

    eventTypes : function(){
      var base = this;
      var types = ["s","e","x"];

      base.ev_types = {};

      if(base.options.mouseDrag === true && base.options.touchDrag === true){
        types = [
          "touchstart.owl mousedown.owl",
          "touchmove.owl mousemove.owl",
          "touchend.owl touchcancel.owl mouseup.owl"
        ];
      } else if(base.options.mouseDrag === false && base.options.touchDrag === true){
        types = [
          "touchstart.owl",
          "touchmove.owl",
          "touchend.owl touchcancel.owl"
        ];
      } else if(base.options.mouseDrag === true && base.options.touchDrag === false){
        types = [
          "mousedown.owl",
          "mousemove.owl",
          "mouseup.owl"
        ];
      }

      base.ev_types["start"] = types[0];
      base.ev_types["move"] = types[1];
      base.ev_types["end"] = types[2];
    },

    disabledEvents :  function(){
      var base = this;
      base.$elem.on("dragstart.owl", function(event) { event.preventDefault();});
      base.$elem.on("mousedown.disableTextSelect", function(e) {
        return $(e.target).is('input, textarea, select, option');
      });
    },

    gestures : function(){
      var base = this;

      var locals = {
        offsetX : 0,
        offsetY : 0,
        baseElWidth : 0,
        relativePos : 0,
        position: null,
        minSwipe : null,
        maxSwipe: null,
        sliding : null,
        dargging: null,
        targetElement : null
      }

      base.isCssFinish = true;

      function getTouches(event){
        if(event.touches){
          return {
            x : event.touches[0].pageX,
            y : event.touches[0].pageY
          }
        } else {
          if(event.pageX !== undefined){
            return {
              x : event.pageX,
              y : event.pageY
            }
          } else {
            return {
              x : event.clientX,
              y : event.clientY
            }
          }
        }
      }

      function swapEvents(type){
        if(type === "on"){
          $(document).on(base.ev_types["move"], dragMove);
          $(document).on(base.ev_types["end"], dragEnd);
        } else if(type === "off"){
          $(document).off(base.ev_types["move"]);
          $(document).off(base.ev_types["end"]);
        }
      }

      function dragStart(event) {
        var event = event.originalEvent || event || window.event;

        if (event.which === 3) {
          return false;
        }
        if(base.itemsAmount <= base.options.items){
          return;
        }
        if(base.isCssFinish === false && !base.options.dragBeforeAnimFinish ){
          return false;
        }
        if(base.isCss3Finish === false && !base.options.dragBeforeAnimFinish ){
          return false;
        }

        if(base.options.autoPlay !== false){
          clearInterval(base.autoPlayInterval);
        }

        if(base.browser.isTouch !== true && !base.$owlWrapper.hasClass("grabbing")){
          base.$owlWrapper.addClass("grabbing")
        }

        base.newPosX = 0;
        base.newRelativeX = 0;

        $(this).css(base.removeTransition());

        var position = $(this).position();
        locals.relativePos = position.left;

        locals.offsetX = getTouches(event).x - position.left;
        locals.offsetY = getTouches(event).y - position.top;

        swapEvents("on");

        locals.sliding = false;
        locals.targetElement = event.target || event.srcElement;
      }

      function dragMove(event){
        var event = event.originalEvent || event || window.event;

        base.newPosX = getTouches(event).x- locals.offsetX;
        base.newPosY = getTouches(event).y - locals.offsetY;
        base.newRelativeX = base.newPosX - locals.relativePos;

        if (typeof base.options.startDragging === "function" && locals.dragging !== true && base.newRelativeX !== 0) {
          locals.dragging = true;
          base.options.startDragging.apply(base,[base.$elem]);
        }

        if(base.newRelativeX > 8 || base.newRelativeX < -8 && base.browser.isTouch === true){
          event.preventDefault ? event.preventDefault() : event.returnValue = false;
          locals.sliding = true;
        }

        if((base.newPosY > 10 || base.newPosY < -10) && locals.sliding === false){
          $(document).off("touchmove.owl");
        }

        var minSwipe = function(){
          return  base.newRelativeX / 5;
        }
        var maxSwipe = function(){
          return  base.maximumPixels + base.newRelativeX / 5;
        }

        base.newPosX = Math.max(Math.min( base.newPosX, minSwipe() ), maxSwipe() );
        if(base.browser.support3d === true){
          base.transition3d(base.newPosX);
        } else {
          base.css2move(base.newPosX);
        }
      }

      function dragEnd(event){
        var event = event.originalEvent || event || window.event;
        event.target = event.target || event.srcElement;

        locals.dragging = false;

        if(base.browser.isTouch !== true){
          base.$owlWrapper.removeClass("grabbing");
        }

        if(base.newRelativeX<0){
          base.dragDirection = base.owl.dragDirection = "left"
        } else {
          base.dragDirection = base.owl.dragDirection = "right"
        }

        if(base.newRelativeX !== 0){
          var newPosition = base.getNewPosition();
          base.goTo(newPosition,false,"drag");
          if(locals.targetElement === event.target && base.browser.isTouch !== true){
            $(event.target).on("click.disable", function(ev){
              ev.stopImmediatePropagation();
              ev.stopPropagation();
              ev.preventDefault();
              $(event.target).off("click.disable");
            });
            var handlers = $._data(event.target, "events")["click"];
            var owlStopEvent = handlers.pop();
            handlers.splice(0, 0, owlStopEvent);
          }
        }
        swapEvents("off");
      }
      base.$elem.on(base.ev_types["start"], ".owl-wrapper", dragStart);
    },

    getNewPosition : function(){
      var base = this,
        newPosition;

      newPosition = base.closestItem();

      if(newPosition>base.maximumItem){
        base.currentItem = base.maximumItem;
        newPosition  = base.maximumItem;
      } else if( base.newPosX >=0 ){
        newPosition = 0;
        base.currentItem = 0;
      }
      return newPosition;
    },
    closestItem : function(){
      var base = this,
        array = base.options.scrollPerPage === true ? base.pagesInArray : base.positionsInArray,
        goal = base.newPosX,
        closest = null;

      $.each(array, function(i,v){
        if( goal - (base.itemWidth/20) > array[i+1] && goal - (base.itemWidth/20)< v && base.moveDirection() === "left") {
          closest = v;
          if(base.options.scrollPerPage === true){
            base.currentItem = $.inArray(closest, base.positionsInArray);
          } else {
            base.currentItem = i;
          }
        }
        else if (goal + (base.itemWidth/20) < v && goal + (base.itemWidth/20) > (array[i+1] || array[i]-base.itemWidth) && base.moveDirection() === "right"){
          if(base.options.scrollPerPage === true){
            closest = array[i+1] || array[array.length-1];
            base.currentItem = $.inArray(closest, base.positionsInArray);
          } else {
            closest = array[i+1];
            base.currentItem = i+1;
          }
        }
      });
      return base.currentItem;
    },

    moveDirection : function(){
      var base = this,
        direction;
      if(base.newRelativeX < 0 ){
        direction = "right"
        base.playDirection = "next"
      } else {
        direction = "left"
        base.playDirection = "prev"
      }
      return direction
    },

    customEvents : function(){
      var base = this;
      base.$elem.on("owl.next",function(){
        base.next();
      });
      base.$elem.on("owl.prev",function(){
        base.prev();
      });
      base.$elem.on("owl.play",function(event,speed){
        base.options.autoPlay = speed;
        base.play();
        base.hoverStatus = "play";
      });
      base.$elem.on("owl.stop",function(){
        base.stop();
        base.hoverStatus = "stop";
      });
      base.$elem.on("owl.goTo",function(event,item){
        base.goTo(item)
      });
      base.$elem.on("owl.jumpTo",function(event,item){
        base.jumpTo(item)
      });
    },

    stopOnHover : function(){
      var base = this;
      if(base.options.stopOnHover === true && base.browser.isTouch !== true && base.options.autoPlay !== false){
        base.$elem.on("mouseover", function(){
          base.stop();
        });
        base.$elem.on("mouseout", function(){
          if(base.hoverStatus !== "stop"){
            base.play();
          }
        });
      }
    },

    lazyLoad : function(){
      var base = this;

      if(base.options.lazyLoad === false){
        return false;
      }
      for(var i=0; i<base.itemsAmount; i++){
        var $item = $(base.$owlItems[i]);

        if($item.data("owl-loaded") === "loaded"){
          continue;
        }

        var itemNumber = $item.data("owl-item"),
          $lazyImg = $item.find(".lazyOwl"),
          follow;

        if( typeof $lazyImg.data("src") !== "string"){
          $item.data("owl-loaded","loaded");
          continue;
        }
        if($item.data("owl-loaded") === undefined){
          $lazyImg.hide();
          $item.addClass("loading").data("owl-loaded","checked");
        }
        if(base.options.lazyFollow === true){
          follow = itemNumber >= base.currentItem;
        } else {
          follow = true;
        }
        if(follow && itemNumber < base.currentItem + base.options.items && $lazyImg.length){
          base.lazyPreload($item,$lazyImg);
        }
      }
    },

    lazyPreload : function($item,$lazyImg){
      var base = this,
        iterations = 0;
        if ($lazyImg.prop("tagName") === "DIV") {
          $lazyImg.css("background-image", "url(" + $lazyImg.data("src")+ ")" );
          var isBackgroundImg=true;
        } else {
          $lazyImg[0].src = $lazyImg.data("src");
        }
        checkLazyImage();

      function checkLazyImage(){
        iterations += 1;
        if (base.completeImg($lazyImg.get(0)) || isBackgroundImg === true) {
          showImage();
        } else if(iterations <= 100){//if image loads in less than 10 seconds
          setTimeout(checkLazyImage,100);
        } else {
          showImage();
        }
      }
      function showImage(){
        $item.data("owl-loaded", "loaded").removeClass("loading");
        $lazyImg.removeAttr("data-src");
        base.options.lazyEffect === "fade" ? $lazyImg.fadeIn(400) : $lazyImg.show();
        if(typeof base.options.afterLazyLoad === "function") {
          base.options.afterLazyLoad.apply(this,[base.$elem]);
        }
      }
    },

    autoHeight : function(){
      var base = this;
      var $currentimg = $(base.$owlItems[base.currentItem]).find("img");

      if($currentimg.get(0) !== undefined ){
        var iterations = 0;
        checkImage();
      } else {
        addHeight();
      }
      function checkImage(){
        iterations += 1;
        if ( base.completeImg($currentimg.get(0)) ) {
          addHeight();
        } else if(iterations <= 100){ //if image loads in less than 10 seconds
          setTimeout(checkImage,100);
        } else {
          base.wrapperOuter.css("height", ""); //Else remove height attribute
        }
      }

      function addHeight(){
        var $currentItem = $(base.$owlItems[base.currentItem]).height();
        base.wrapperOuter.css("height",$currentItem+"px");
        if(!base.wrapperOuter.hasClass("autoHeight")){
          setTimeout(function(){
            base.wrapperOuter.addClass("autoHeight");
          },0);
        }
      }
    },

    completeImg : function(img) {
        if (!img.complete) {
            return false;
        }
        if (typeof img.naturalWidth !== "undefined" && img.naturalWidth == 0) {
            return false;
        }
        return true;
    },

    onVisibleItems : function(){
      var base = this;

      if(base.options.addClassActive === true){
        base.$owlItems.removeClass("active");
      }
      base.visibleItems = [];
      for(var i=base.currentItem; i<base.currentItem + base.options.items; i++){
        base.visibleItems.push(i);

        if(base.options.addClassActive === true){
          $(base.$owlItems[i]).addClass("active");
        }
      }
      base.owl.visibleItems = base.visibleItems;
    },

    transitionTypes : function(className){
      var base = this;
      //Currently available: "fade","backSlide","goDown","fadeUp"
      base.outClass = "owl-"+className+"-out";
      base.inClass = "owl-"+className+"-in";
    },

    singleItemTransition : function(){
      var base = this;
      base.isTransition = true;

      var outClass = base.outClass,
        inClass = base.inClass,
        $currentItem = base.$owlItems.eq(base.currentItem),
        $prevItem = base.$owlItems.eq(base.prevItem),
        prevPos = Math.abs(base.positionsInArray[base.currentItem]) + base.positionsInArray[base.prevItem],
        origin = Math.abs(base.positionsInArray[base.currentItem])+base.itemWidth/2;

            base.$owlWrapper
              .addClass('owl-origin')
              .css({
                "-webkit-transform-origin" : origin+"px",
                "-moz-perspective-origin" : origin+"px",
                "perspective-origin" : origin+"px"
              });
          function transStyles(prevPos,zindex){
        return {
          "position" : "relative",
          "left" : prevPos+"px"
        };
      }

          var animEnd = 'webkitAnimationEnd oAnimationEnd MSAnimationEnd animationend';

      $prevItem
      .css(transStyles(prevPos,10))
      .addClass(outClass)
      .on(animEnd, function() {
        base.endPrev = true;
        $prevItem.off(animEnd);
          base.clearTransStyle($prevItem,outClass);
      });

      $currentItem
      .addClass(inClass)
      .on(animEnd, function() {
        base.endCurrent = true;
        $currentItem.off(animEnd);
          base.clearTransStyle($currentItem,inClass);
        });
    },

    clearTransStyle : function(item,classToRemove){
      var base = this;
      item.css({
          "position" : "",
          "left" : ""
        })
        .removeClass(classToRemove);
      if(base.endPrev && base.endCurrent){
        base.$owlWrapper.removeClass('owl-origin');
        base.endPrev = false;
        base.endCurrent = false;
        base.isTransition = false;
      }
    },

    owlStatus : function(){
      var base = this;
      base.owl = {
        "userOptions" : base.userOptions,
        "baseElement"   : base.$elem,
        "userItems"   : base.$userItems,
        "owlItems"    : base.$owlItems,
        "currentItem" : base.currentItem,
        "prevItem"    : base.prevItem,
        "visibleItems"  : base.visibleItems,
        "isTouch"     : base.browser.isTouch,
        "browser"   : base.browser,
        "dragDirection" : base.dragDirection
      }
    },

    clearEvents : function(){
      var base = this;
      base.$elem.off(".owl owl mousedown.disableTextSelect");
      $(document).off(".owl owl");
      $(window).off("resize", base.resizer);
    },

    unWrap : function(){
      var base = this;
      if(base.$elem.children().length !== 0){
        base.$owlWrapper.unwrap();
        base.$userItems.unwrap().unwrap();
        if(base.owlControls){
          base.owlControls.remove();
        }
      }
      base.clearEvents();
      base.$elem
        .attr("style", base.$elem.data("owl-originalStyles") || "")
        .attr("class", base.$elem.data("owl-originalClasses"));
    },

    destroy : function(){
      var base = this;
      base.stop();
      clearInterval(base.checkVisible);
      base.unWrap();
      base.$elem.removeData();
    },

    reinit : function(newOptions){
      var base = this;
      var options = $.extend({}, base.userOptions, newOptions);
      base.unWrap();
      base.init(options,base.$elem);
    },

    addItem : function(htmlString,targetPosition){
      var base = this,
        position;

      if(!htmlString){return false}

      if(base.$elem.children().length === 0){
        base.$elem.append(htmlString);
        base.setVars();
        return false;
      }
      base.unWrap();
      if(targetPosition === undefined || targetPosition === -1){
        position = -1;
      } else {
        position = targetPosition;
      }
      if(position >= base.$userItems.length || position === -1){
        base.$userItems.eq(-1).after(htmlString)
      } else {
        base.$userItems.eq(position).before(htmlString)
      }

      base.setVars();
    },

    removeItem : function(targetPosition){
      var base = this,
        position;

      if(base.$elem.children().length === 0){return false}

      if(targetPosition === undefined || targetPosition === -1){
        position = -1;
      } else {
        position = targetPosition;
      }

      base.unWrap();
      base.$userItems.eq(position).remove();
      base.setVars();
    }

  };

  $.fn.owlCarousel = function( options ){
    return this.each(function() {
      if($(this).data("owl-init") === true){
        return false;
      }
      $(this).data("owl-init", true);
      var carousel = Object.create( Carousel );
      carousel.init( options, this );
      $.data( this, "owlCarousel", carousel );
    });
  };

  $.fn.owlCarousel.options = {

    items : 5,
    itemsCustom : false,
    itemsDesktop : [1199,4],
    itemsDesktopSmall : [979,3],
    itemsTablet : [768,2],
    itemsTabletSmall : false,
    itemsMobile : [479,1],
    singleItem : false,
    itemsScaleUp : false,

    slideSpeed : 200,
    paginationSpeed : 800,
    rewindSpeed : 1000,

    autoPlay : false,
    stopOnHover : false,

    navigation : false,
    navigationText : ["prev","next"],
    rewindNav : true,
    scrollPerPage : false,

    pagination : true,
    paginationNumbers : false,

    responsive : true,
    responsiveRefreshRate : 200,
    responsiveBaseWidth : window,


    baseClass : "owl-carousel",
    theme : "owl-theme",

    lazyLoad : false,
    lazyFollow : true,
    lazyEffect : "fade",

    autoHeight : false,

    jsonPath : false,
    jsonSuccess : false,

    dragBeforeAnimFinish : true,
    mouseDrag : true,
    touchDrag : true,

    addClassActive : false,
    transitionStyle : false,

    beforeUpdate : false,
    afterUpdate : false,
    beforeInit : false,
    afterInit : false,
    beforeMove : false,
    afterMove : false,
    afterAction : false,
    startDragging : false,
    afterLazyLoad: false

  };
})( jQuery, window, document );
(function() {
  $(document).ready(function() {
    $('.slider').owlCarousel({
      autoPlay: true,
      singleItem: true,
      slideSpeed: 500,
      paginationSpeed: 1000
    });
    $('.dropdown').dropit({
      triggerParentEl: '.dropdown_inner'
    });
    return $('.custom_selectbox').uniform();
  });

}).call(this);
