/* Cached Variables *******************************************************************************/
var $allModals = $('.modal');
var $globalSmallModal = $('#global-small-modal');

/* Listeners **************************************************************************************/
$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
  
  $allModals.modal('hide');
  
  var isServerError = jqXHR.status >= 500 && jqXHR.status < 600;
  
  if(isServerError) {
    $globalSmallModal.find('.modal-dialog').html(jqXHR.responseText);
  }
  
  $globalSmallModal.modal('show');
  
});

/* Document Ready Stuff ***************************************************************************/
$(document).ready(function() {
  $('.table-container').removeClass('table-loading');
});



