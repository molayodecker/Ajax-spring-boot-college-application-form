$('#program').hide();
$("#programtype").on('change',function(){
        $('#program').show();
        $('#program option').hide();
        $('#program option[class="'+$(this).val()+'"]').show();
     });

//iterate and find all select with a href
$('.programForm button[type="submit"]').on('click',function(e){
  e.preventDefault();
   var loader = $('#program option[value="'+$('#program').val()+'"]');
   console.log(loader);
});