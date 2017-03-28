'use strict';


var $formUploader = $("#upload-file-input");
  $formUploader.on("submit", function(e){
   e.preventDefault();

   var data1 = new FormData(this);
   $.each($formUploader.serializeArray(), function(i, field) {
          data1[field.name] = field.value;
   });
   //next line will add content of file
   data1.append('uploadfile', $('#file')[0].files[0]);
   for(var i of data1.entries()){
        console.log(i[0] + ' '+ i[1]);
   }

   //var data = new FormData(this);
   /*var data={};
   $.each($formUploader.serializeArray(), function(i, field) {
           data[field.name] = field.value;
       });*/
   //var formData = $('#file').val();
     if(!$('#file').val()){
            alert("empty input file");
        return false;
     }



     var $form = $("#upload-file-input");
     function collectFormData(fields) {
         var formData = new FormData();

         for (var i = 0; i < fields.length; i++) {
             var $item = $(fields[i]);

             if ($item.attr('type') =="file"){
                 var file = $('input[type=file]')[0].files[0];
                 formData.append( $item.attr('name') , file);

             } else {
                 formData.append( $item.attr('name') , $item.val());
             }
         }
         return formData;
     }
         var fields = $form.find('input');
         var formData = collectFormData(fields);
         var obj = "";
         for (var pair of formData.entries()) {
             console.log(pair[0]+ ', ' + pair[1]);
             obj = pair[1];
         }


   $.ajax({
           //dataType: 'json',
           url: $formUploader.prop('action'),
           type: "POST",
           data: data1,
           //data: data,
           //data: new FormData(document.getElementById('#upload-file-input').file),
           enctype: 'multipart/form-data',
           processData: false,
           contentType: false,
           cache: false,
           timeout: 10000,
           success: function (data) {
           console.log(data);
             // Handle upload success
             $("#upload-file-message").text("File succesfully uploaded");
           },
           error: function (response) {
             console.log(response);
             // Handle upload error
             $("#upload-file-message").text("File not uploaded (File might be big, size needed.)");
           }
         });
});

/*
beforeSend:function(xhr){
    xhr.setRequestHeader('X-CSRFToken',
                   '{{ csrf_token }}');*/


