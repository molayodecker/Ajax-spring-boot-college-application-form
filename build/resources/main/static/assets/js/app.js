//create overlay
var $overlay = $('<div class="overlay"></div>');
var $close   = $('<div class="close-classic"></div>');
//Add overlay to body
$("body").append($overlay).append($close);

$(".login a").click(function(event){
  event.preventDefault();
   $overlay.load('login');
   $overlay.show();
   $close.show();
});


$('.close-classic').on('click',function() {
        $('.overlay').hide();
 });

$('.close-classic').on('click',function() {
        $(this).hide();
 });

/*$(function() {
  var chk = $('#check');
  var btn = $('#btncheck');

  chk.on('change', function(event) {
    btn.prop("disabled", !this.checked);//true: disabled, false: enabled
  }).trigger('change'); //page load trigger event
});*/


$('.todo-item label').click(function(){
        $(this).parent('form').submit();
 });

$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year
  });


let availableTags = [
  "Ghanaian",
  "Nigerian"
];

$('.autocomplete').autocomplete({
  source: availableTags
});

$('#certificate').change(function() {
  $('#program option').hide();
  $('#program option[class="' + $(this).val() + '"]').show();
  // add this code to select 1'st of streets automaticaly
  // when city changed
  if ($('#program option[class="' + $(this).val() + '"]').length) {
    $('#program option[class="' + $(this).val() + '"]').first().prop('selected', true);
  }
  // in case if there's no corresponding street:
  // reset select element
  else {
    $('#program').val('');
  };
});

$('#program').change(function() {
  $('#school option').val('');
  $('#school option').hide();
  $('#school option[class="' + $(this).val() + '"]').show();
  // add this code to select 1'st of streets automaticaly
  // when city changed
  if ($('#school option[class="' + $(this).val() + '"]').length) {
    $('#school option[class="' + $(this).val() + '"]').first().prop('selected', true);
  }
  // in case if there's no corresponding street:
  // reset select element
  else {
    $('#school').val('');
  };
});




var $password = $(".showpassword");
$(".toggle").click(function () {
  var change = "";
  if ($(this).html() === "Hide Pin") {
    $(this).html("Hide Pin")
    change = "text";
    $password.attr("type", "password");
  } else {
    $(this).html("Show Pin");
    change = "password";
    $password.attr("type", "text");
  }
});


