/* Cached Variables *******************************************************************************/
var $moePagesTable = $('#moe-pages');
var $maintenanceModalLarge = $('#maintenance-modal-large');
var $maintenanceModalSmall = $('#maintenance-modal-small');

var $document = $(document);
var $primaryContainer = $('body > .container');

/* Listeners **************************************************************************************/
$moePagesTable.on('click', '.edit-page', function () {
  
  var pageId = $(this).closest('tr').data('pageId');
  
  var $getMoePageFormModalPromise = $.get('get-moe-page-form-modal', {pageId: pageId});
  
  $getMoePageFormModalPromise.done(function(modalContent) {
    $maintenanceModalLarge.find('.modal-dialog').html(modalContent);
    $maintenanceModalLarge.modal('show');
  });
  
});


$moePagesTable.on('click', '.delete-page', function () {
  
  var $trToRemove = $(this).closest('tr');
  
  var pageId = $trToRemove.data('pageId');
  
  var $getDeleteMoePageModalPromise = $.get('get-delete-moe-page-modal', {pageId: pageId});
  
  $getDeleteMoePageModalPromise.done(function(modalContent) {
    
    $maintenanceModalSmall.find('.modal-dialog').html(modalContent);
    $maintenanceModalSmall.modal('show');
  });
  
});

$('.btn-add-moe-page').on('click', function () {
  
  var $getMoePageFormModalPromise = $.get('get-moe-page-form-modal');
  
  $getMoePageFormModalPromise.done(function(modalContent) {
    $maintenanceModalLarge.find('.modal-dialog').html(modalContent);
    $maintenanceModalLarge.modal('show');
  });
  
});


$document.ajaxStart(function() {
  $primaryContainer.addClass('page-loading');
});

$document.ajaxStop(function() {
  $primaryContainer.removeClass('page-loading');
});

/* Initialization *********************************************************************************/
$moePagesTable.DataTable({
  autoWidth: false,
  columnDefs: [
    {className: "text-right",    "targets": [3,4]},
    {className: "text-center",   "targets": [1]},
    {className: "table-actions", "targets": [0]},
    {orderable: false,           "targets": [0,1]},
    {width:     '90px',          "targets": [0]}
    
  ],
  language: {
    emptyTable: "No kawaii pages found",
    info: "_START_ to _END_ of _TOTAL_"
  },
  lengthChange: false,
  order: [[ 2, 'asc' ]],
  pagingType: "simple",
  responsive: true,
  retrieve: true,
  searching: false,
  
  stateSave: true
  
});