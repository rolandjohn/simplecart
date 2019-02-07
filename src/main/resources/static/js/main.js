$(document).ready(function () {
	
	getCartQtyCount();
    $(".btnAddCart").on('click', function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();
        var code = $(this).attr('href');
        fire_ajax_submit(code);

    });



});

$(document).on('click', '[data-toggle="lightbox"]', function(event){
    event.preventDefault();
    $(this).ekkoLightbox();
});

function fire_ajax_submit(code) {

    var product = {}
    product["code"] = code;
    //search["email"] = $("#email").val();
    
    $(".btnAddCart").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addToCart?code=" + code,
       // data: JSON.stringify(product),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	var message="Product Added to Cart!";
        	 $("#alert-area").append($("<div class='alert alert-success'><strong>Success!</strong> " + message + "</div>"));
        	    $(".alert-success").delay(2000).fadeOut("slow", function () { $(this).remove(); });
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            //$('#feedback').html(json);
            $('#itemCount').html(data.result.quantityTotal).css('display', 'block');
            console.log("SUCCESS : ", data);
            $(".btnAddCart").prop("disabled", false);

        },
        error: function (e) {
        	var message="Product No Added";
       	 	$("#alert-area").append($("<div class='alert alert-warning'><strong>Info!</strong> " + message + "</div>"));
       	    $(".alert-warning").delay(2000).fadeOut("slow", function () { $(this).remove(); });
            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            //$('#feedback').html(json);

            console.log("ERROR : ", e);
            $(".btnAddCart").prop("disabled", false);

        }
    });

}

function getCartQtyCount() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/getQtyCount",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        
            $('#itemCount').html(data.result.quantityTotal).css('display', 'block');
            console.log("SUCCESS : ", data);
           
        },
        error: function (e) {
            console.log("ERROR : ", e);

        }
    });

}