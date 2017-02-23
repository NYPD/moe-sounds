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

$('#submit-bug-report').on('click', function() {
  popupCenter('https://gitreports.com/issue/moe-bugs/moe-sounds', 'Submit Bug Report', 650, 900);
});


/* Functions **************************************************************************************/
function popupCenter(url, title, w, h) {
  var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
  var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

  var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
  var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

  var left = ((width / 2) - (w / 2)) + dualScreenLeft;
  var top = ((height / 2) - (h / 2)) + dualScreenTop;
  var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

  // Puts focus on the newWindow
  if (window.focus) newWindow.focus();
  
}

/* Document Ready Stuff ***************************************************************************/
$(document).ready(function() {
  $('.table-container').removeClass('table-loading');
});