/* Cached Variables *******************************************************************************/
var $moePageForm = $('#moe-page-form');

/* Listeners **************************************************************************************/
$('.btn-delete-moe-page').on('click', function(event) {
  
  var pageId = $(this).data('page-id');
  
  var $deletePagePromise = $.post('delete-page', {pageId: pageId});
  
  $deletePagePromise.done(function () {
    
    var $rowtoRemove = $moePagesTable.find('tbody tr[data-page-id="'+ pageId + '"]');
    
    $moePagesTable.DataTable().row($rowtoRemove).remove().draw();
    
    $maintenanceModalSmall.modal('hide');
    $maintenanceModalSmall.find('.modal-dialog').empty();
    
  });
  
});