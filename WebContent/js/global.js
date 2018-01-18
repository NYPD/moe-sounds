/* Cached Variables *******************************************************************************/
var $allModals = $('.modal');
var $globalSmallModal = $('#global-small-modal');

/* Initialization *********************************************************************************/
selectTab($('body').data('tab-id'));
$('#copyright-year').text(new Date().getFullYear());

/* Listeners **************************************************************************************/
$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
  
  $allModals.modal('hide');
  
  var isServerError = jqXHR.status >= 500 && jqXHR.status < 600;
  
  if(isServerError)
    $globalSmallModal.find('.modal-dialog').html(jqXHR.responseText);
  else
    $globalSmallModal.find('.modal-dialog').html(getGenericErrorModal());
  
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

function selectTab(id) {
  $('#' + id).addClass('active');
}

function getGenericErrorModal() {
  
  return '<div class="modal-content">' +

            '<div class="modal-header">' +
              '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
              '<h4 class="modal-title">Error</h4>' +
            '</div>' +
            
            '<div class="modal-body">' +
             '<p>Something is goofed up. If this keeps happening please submit a bug report.</p>' +
            '</div>' +
            
            '<div class="modal-footer">' +
              '<button type="button" class="btn btn-warning" data-dismiss="modal">OK</button>' +
            '</div>' +
            
          '</div>';
}

/* Document Ready Stuff ***************************************************************************/
$(document).ready(function() {
  $('.table-container').removeClass('table-loading');
});