/* Cached Variables *******************************************************************************/
var $moePagesTable = $('#moe-pages');
var $maintenanceModalLarge = $('#maintenance-modal-large');
var $maintenanceModalSmall = $('#maintenance-modal-small');

/* Listeners *******************************************************************************/
$('.btn-add-moe-page').on('click', function () {
  
  var $getMoePageFormModalPromise = $.get('get-moe-page-form-modal');
  
  $getMoePageFormModalPromise.done(function(data) {
    
    var modalContent = data;
    
    $maintenanceModalLarge.find('.modal-dialog').html(modalContent);
    $maintenanceModalLarge.modal('show')
  });
  
});


/* Initialization *********************************************************************************/
$moePagesTable.DataTable({
  columnDefs: [
    {className: "text-right", "targets": [3] },
    {orderable: false,        "targets": [0,1]}
    
  ],
  language: {
    emptyTable: "No kawaii pages found"
  },
  order: [[ 2, 'asc' ]],
  paging: false,
  responsive: true,
  scrollY: "500px",
  scrollCollapse: true,
  stateSave: true
  
});