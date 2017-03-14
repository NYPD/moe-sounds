<div class="modal-content">

  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Delete Moe Page</h4>
  </div>
  
  <div class="modal-body">
   <p>
     Are you sure you want to delete <strong>${page.pageName}</strong>?
   </p>
  </div>
  
  <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-warning btn-delete-moe-page" data-page-id="${page.pageId}">Delete</button>
  </div>
  
</div>

<script src="${context}/js/modal/maintenance/delete-moe-page.js?v=${projectVersion}"></script>