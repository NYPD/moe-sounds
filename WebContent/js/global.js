/* Cached Variables *******************************************************************************/

/* Listeners **************************************************************************************/
$(document).ajaxError(function( event, request, settings ) {
  $( "#msg" ).append( "<li>Error requesting page " + settings.url + "</li>" );
});

/* Document Ready Stuff ***************************************************************************/
$(document).ready(function() {
  $('.table-container').removeClass('table-loading');
});



