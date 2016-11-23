<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="modalAction" value="Edit"/>
<c:if test="${empty page}">
  <c:set var="modalAction" value="Add"/>
</c:if>

<link href="${context}/css/modal/maintenance/moe-page-form.css" rel="stylesheet">

<div class="modal-content">

  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">${modalAction} Moe Page</h4>
  </div>
  
  <div class="modal-body">
  
    <form class="form-horizontal" id="moe-page-form" enctype="multipart/form-data">
    
      <input type="hidden" name="pageId" value="${page.pageId}">
    
		  <div class="form-group">
		    <label for="page-name" class="col-sm-3 control-label">Page Name</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" id="page-name" placeholder="Page Name" value="${page.pageName}">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="css" class="col-sm-3 control-label">CSS</label>
		    <div class="col-sm-9">
		      <textarea class="form-control" id="css"  rows="10" cols="60"></textarea>
		    </div>
		  </div>
		  
		  <c:forEach items="${mediaTypes}" var="mediaType" varStatus="count">
		  
		    <div class="form-group">
      
	        <label for="${mediaType.name}" class="col-sm-3 control-label">${mediaType.nameWithoutAllCaps}</label>
	        
	        <div class="col-sm-9">
	          <div class="input-group">
	            <input type="text" class="form-control file-name" readonly>
	            <label class="input-group-btn">
                <span class="btn btn-default">
                  <span>Browse&hellip;</span>
                  <input type="file" class="file-data" name="formFiles[${count.index}].file" style="display: none;">
                </span>
                <button class="btn btn-default btn-preview-image" type="button">Preview</button>
              </label>
	          </div>
	          <span class="help-block">
	            Image should be around 200 x 200
	          </span>
	        </div>
	        
	        <input type="hidden" name="formFiles[${count.index}].mediaId"   value="${entry.value.mediaId}">
          <input type="hidden" name="formFiles[${count.index}].mediaType" class="media-type" value="${mediaType}">
	        
	      </div>
		  
		  </c:forEach>
		  
		</form>
		
  </div>
  
  <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary">Save Moe Page</button>
  </div>
  
</div>

<script src="${context}/js/modal/maintenance/moe-page-form.js"></script>