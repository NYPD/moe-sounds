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
		      <input type="text" class="form-control alpha alpha-numeric" id="page-name" name="pageName" placeholder="Page Name" value="${page.pageName}" required>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="css" class="col-sm-3 control-label">CSS</label>
		    <div class="col-sm-9">
		      <textarea class="form-control" id="css" name="css" rows="10" cols="60">${page.css}</textarea>
		    </div>
		  </div>
		  
		  <c:forEach items="${mediaTypes}" var="mediaType" varStatus="loopTagstatus">
      
        <input type="hidden" name="formFiles[${loopTagstatus.index}].mediaId"   value="${media.mediaId}">
        <input type="hidden" name="formFiles[${loopTagstatus.index}].mediaType" class="media-type" value="${mediaType}">
		  
		    <div class="form-group">
      
	        <label for="${mediaType}" class="col-sm-3 control-label">${mediaType.nameWithoutAllCaps}</label>
	        
	        <div class="col-sm-9">
          
	          <div class="input-group">
            
	            <label class="input-group-btn">
                <a class="btn btn-default">
                  <span>Browse&hellip;</span>
                  <c:set var="media" value="${page.getMediaWithMediaType(mediaType)}"/>
                  <input type="file" class="file-data" name="formFiles[${loopTagstatus.index}].file" id="${mediaType}" data-simple-file-hash="${media.fileName}${media.fileSize}" 
                                                                                                                       <c:if test="${not empty media}">data-src="data:${media.fileType};base64,${media.fileDataAsBase64}"</c:if> 
                                                                                                                       <c:if test="${mediaType.required}">required</c:if>>
                </a>
              </label>
              
	            <input type="text" class="form-control file-name" value="${media.fileName}" readonly <c:if test="${mediaType.required}">required</c:if>>
              
	            <div class="input-group-btn input-group-btn-file-actions">
	              <button type="button" class="btn btn-danger btn-remove-file" <c:if test="${empty media}">disabled</c:if>>
	                <i class="fa fa-ban" aria-hidden="true"></i>
	              </button>
                <button class="btn btn-default btn-preview-image" type="button" <c:if test="${empty media}">disabled</c:if>>Preview</button>
              </div>
              
	          </div>
            
	          <span class="help-block">${mediaType.fileSuggestion}</span>
            
	        </div>
	        
	      </div>
        
		  </c:forEach>
		  
		</form>
		
  </div>
  
  <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary btn-save-moe-page">Save Moe Page</button>
  </div>
  
</div>

<script src="${context}/js/vendor/ritsu.min.js"></script>
<script src="${context}/js/modal/maintenance/moe-page-form.js"></script>