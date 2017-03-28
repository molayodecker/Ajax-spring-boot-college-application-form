// SUBMIT FORM
// PREPARE FORM DATA
// DO POST
var $form = $('.editCert');
var url = $form.attr('action');
var storage = {};

$form.on('submit',function(e){
    e.preventDefault();
    $.each($(this).serializeArray(), function(i, field) {
        storage[field.name] = field.value;
    });
    var $myerror = "";
   console.log(storage);
    $.ajax(url,{
            //dataType: 'json', // Json already parse by using @RespondBody in controller
            data: storage,
            type: "POST",
        }).done(function(response){
                $('.flash').empty().removeClass();
                 console.log('I am Okay' + response);
        }).fail(function(jqXHR, textStatus, errorThrown){
        console.log(jqXHR);
          if(jqXHR.status === 400){
          var errorInfo="";
          for(i =0 ; i <jqXHR.responseJSON.length ; i++){
                           errorInfo += "<br>" + (i + 1) +". " + jqXHR.responseJSON[i];
                       }
                    $myerror =  $form.find('.flash').addClass(' failure');
                    $myerror.html("Please correct following errors: " + errorInfo);
            }
           });
    });


 /*$.each(res,function(index, val){
             console.log(val.errorMessageList);
             });*/

/*
success: function(response){
        if(response.status === 'SUCCESS'){
        console.log('I am Okay' + response);
        }else{
        console.log('Holly F$%k');
         }
        },
        error: function(e){
         console.log('Something is not right!' + e);
        }*/

/*for (var i = 0; i < e.errorMessageList.length; i++) {
            var item = e.errorMessageList[i];
            var $controlGroup = $('#' + item.fieldName + 'row');
            $controlGroup.addClass('error');
            $controlGroup.find('.error-message').html(item.message);
        }*/

/*
$.ajax(url,{
        dataType: 'json',
        data: data,
        type: "POST",
    }).done(function(response){
       if(response.status === 'SUCCESS'){
             console.log('I am Okay' + response);
              }else{
              console.log('Holly Molly');
               }
    }).fail(function(res){
        errorInfo = "";
             for(i =0 ; i < res.errorMessageList.length ; i++){
                 errorInfo += "<br>" + (i + 1) +". " + response.errorMessageList[i].code;
             }
             $('.error').html("Please correct following errors: " + errorInfo);
        });
});*/


/*
var formData = $form.serialize();
function collectFormData(fields) {
				var data = {};
				for (var i = 0; i < fields.length; i++) {
					var $item = $(fields[i]);
					data[$item.attr('name')] = $item.val();
				}
				return data;
			}
var $inputs = $form.find('input');
var data = collectFormData($inputs);
console.log(data);*/

