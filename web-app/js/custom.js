/**
 * Return true if the entityID string is of the expected format
 **/
function validateID(entityID) {
//    var len = entityID.length;
//    return (len == 13 || len == 19) && entityID.substr(0, 2) == '00' && 
//                entityID.substr(li-3, 2) == '00';
    var matcher = /\d{2}\.\d{6}|\d{2}\.\d{6}\.\d{4}/
    return matcher.test(entityID)
}

function validateQuantity(quantity) {
    var matcher = /\d+/
    var matcher2 = ~ "-"
    return matcher.test(quantity) || matcher2.test(quantity)
}

(function($) {
    $.fn.quantityValidator = function() {
        var quantity = $(this).val();

        if (quantity.length == 0 || quantity == 0) {
            $('[type=submit]').attr('disabled', true);
            control.addClass('error');
            return false;
        }

        var control = $(this).closest('.control-group');

        $.each(quantity, function(i, o) {

            if ([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '-'].indexOf(parseInt(o)) == -1) {
                control.addClass('error');
                return false;
            } else {
                control.removeClass('error');
            }
        });

        if (validateQuantity(quantity)) {
            control.removeClass('error');
            control.addClass('success');
            $('[type=submit]').removeAttr('disabled');
        } else {
            control.removeClass('success');
            $('[type=submit]').attr('disabled', true);
        }

    };

    $.fn.dateValidator = function() {
        var today = new Date();
        var dateArray = $(this).val().split("/");
        var sDate  = new Date(dateArray[2], dateArray[1]-1, dateArray[0]);
        return sDate > today;

    };

    $.fn.entityIDValidator = function(accept) {

        // The parameter passed through the method makes the validator accepting
        // empty barcodes
        // true == it accepts empty barcodes
        // false == it disables the submit button while the box is empty

        var barcode = $(this).val();

        if (!accept && barcode.length == 0) {
            $('[type=submit]').attr('disabled', true);
            return false;
        }

        var control = $(this).closest('.control-group');

        $.each(barcode, function(i, o) {

            if ([0, 1, 2, 3, 4, 5, 6, 7, 8, 9].indexOf(parseInt(o)) == -1) {
                control.addClass('error');
                return false;
            } else {
                control.removeClass('error');
            }
        });

        if (validateID(barcode)) {
            control.removeClass('error');
            control.addClass('success');
            $('[type=submit]').removeAttr('disabled');
        } else {
            control.removeClass('success');
            $('[type=submit]').attr('disabled', true);
        }
    };
})(jQuery);

/*$(document).ready(function() {

    $( document ).ajaxError(function(e, jqxhr, settings, exception) {

            alert('Ajax Error ('+ jqxhr.status +') ' + exception)

    });

});*/

