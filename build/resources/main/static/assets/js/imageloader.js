'use strict';
var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");
var  $imgSpinner = $('<div class="loader"><img alt="loader" src="/assets/images/ring2.gif" />Please wait...</div>');
$("body").append($imgSpinner);
$(document).ready(function(){
$.ajaxSetup({
    beforeSend:function(xhr){
   if (header && token) {
           xhr.setRequestHeader(header, token);
       }
        // show gif here, eg:
        $('.loader').show();
    },
    complete:function(){
        // hide gif here, eg:
        $('.loader').hide();
    }
   });
});


/*
$(document).ready(function(){
$('.loading').ajaxStart(function(){
  $(this).show();
}).ajaxStop(function(){
  $(this).hide();
    });
});*/
