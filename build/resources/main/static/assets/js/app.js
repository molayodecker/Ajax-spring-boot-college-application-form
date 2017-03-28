//create overlay
var $overlay = $('<div class="overlay"></div>');
var $close   = $('<div class="close-classic"></div>');
//Add overlay to body
$("body").append($overlay);

$(".login").on('click',function(event){
  event.preventDefault();
   $overlay.load('login');
   $overlay.show().append($close);
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
    autoclose: true,
    format: 'dd/mm/yyyy',
    formatSubmit: 'dd/mm/yyyy',
     hiddenPrefix: 'prefix__',
     hiddenSuffix: '__suffix'
  });


let availableTags = [
  "Ghanaian",
  "Nigerian"
];
var $autoform = $('.autocomplete');

$autoform.addClass('.failure');
$autoform.autocomplete({
  source: availableTags
});

$("#certificate").change(function() {
  if ($(this).data('options') == undefined) {
    $(this).data('options', $('#program option').clone());
    $("#program").next('select').val("disabled", "true");
  }
  var id = $(this).val();
  var options = $(this).data('options').filter('[class=' + id + ']');
  $('#program').html(options).show();
});

$("#program").change(function() {
  if ($(this).data('options') == undefined) {
    $(this).data('options', $('#school option').clone());

  }
  var id = $(this).val();
  var options = $(this).data('options').filter('[class=' + id + ']');
  $('#school').html(options).show();
});

$('#selectEl').change(function() {
  window.location = $(this).val();
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
/*  var formData = new FormData();
    var file = $("#upload-file-input input[type='file']").val();
    formData.append('uploadfile', file);*/



//var $form = $('#cert_id').attr('action');
var $form = $('a.overlayedit').prop('href');
var $overlay1 = $('<div class="second_overlay"></div>');
var $close1   = $('<div class="close-classic"></div>');
var certificate ='${certificateProgrammes.id}';
//Add overlay to body
$("body").append($overlay1).append($close1);
$(".overlayedit").on('click',function(event){
  event.preventDefault();
   $overlay1.load($form);
   $('.second_overlay').show();
   $close1.show();
   $('.blurout').addClass('blur');
});

$close1.on('click',function() {
        $overlay1.hide();
 });

$close1.on('click',function() {
        $(this).hide();
        $('.second_overlay').hide();
        $('.blurout').removeClass('blur');
 });




