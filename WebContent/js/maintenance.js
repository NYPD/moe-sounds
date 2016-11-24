/* Cached Variables *******************************************************************************/
var $moePagesTable = $('#moe-pages');
var $maintenanceModalLarge = $('#maintenance-modal-large');
var $maintenanceModalSmall = $('#maintenance-modal-small');

/* Listeners **************************************************************************************/
$('.btn-add-moe-page').on('click', function () {
  
  var $getMoePageFormModalPromise = $.get('get-moe-page-form-modal');
  
  $getMoePageFormModalPromise.done(function(modalContent) {
    $maintenanceModalLarge.find('.modal-dialog').html(modalContent);
    $maintenanceModalLarge.modal('show');
  });
  
});

$('.edit-page').on('click', function () {
  
  var pageId = $(this).closest('tr').data('pageId');
  
  var $getMoePageFormModalPromise = $.get('get-moe-page-form-modal', {pageId: pageId});
  
  $getMoePageFormModalPromise.done(function(modalContent) {
    $maintenanceModalLarge.find('.modal-dialog').html(modalContent);
    $maintenanceModalLarge.modal('show');
  });
  
});

$('.delete-page').on('click', function () {
  
  var $trToRemove = $(this).closest('tr');
  
  var pageId = $trToRemove.data('pageId');
  
  var $getDeleteMoePageModalPromise = $.get('get-delete-moe-page-modal', {pageId: pageId});
  
  $getDeleteMoePageModalPromise.done(function(modalContent) {
    
    $maintenanceModalSmall.find('.modal-dialog').html(modalContent);
    $maintenanceModalSmall.modal('show');
  });
  
});


/* Initialization *********************************************************************************/
$moePagesTable.DataTable({
  columnDefs: [
    {className: "text-right",    "targets": [3,4]},
    {className: "table-actions", "targets": [0]},
    {orderable: false,           "targets": [0,1]},
    {width:     '90px',          "targets": [0]}
    
  ],
  language: {
    emptyTable: "No kawaii pages found"
  },
  order: [[ 2, 'asc' ]],
  paging: false,
  responsive: true,
  retrieve: true,
  scrollY: "500px",
  scrollCollapse: true,
  stateSave: true
  
});