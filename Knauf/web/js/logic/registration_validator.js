//$.validate({
//    modules : 'location, date, security, file',
//    onModulesLoaded : function() {
//        $('#country').suggestCountry();
//    }
//});
//
//// Restrict presentation length
//$('#presentation').restrictLength( $('#pres-max-length') );
//
$.validate({
    modules : 'security'
    });
$("#phone_field").inputmask("+7(999)999-99-99");
$("#employer_phone_field").inputmask("+7(999)999-99-99");

$.extend($.inputmask.defaults.aliases, {
    'non-negative-integer': {
        regex: {
            number: function (groupSeparator, groupSize) { return new RegExp("^(\\d*)$"); }
        },
        alias: "decimal"
    }
});