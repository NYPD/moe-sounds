<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    <title>Admin</title>
    
    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="/css/home.css">
  </head>
  <body>
  
  
    
    <form id="form1" method="post" action="/spring-mvc-file-upload/rest/cont/upload" enctype="multipart/form-data">
 
 	  	<input type="hidden" name="pageId" value="${page.pageId}">
 
      <!-- File input -->    
      <label>Page Name</label><input type="text" name="pageName" value="${page.pageName}"><br/>
      <label>CSS</label><textarea rows="10" cols="60" name="css">${page.css}</textarea><br/>
      
    
	    <c:forEach items="${page.media}" var="entry" varStatus="count">
	    	
	    	<c:choose>
	    	  <c:when test="${entry.key.sound}">
	    	 	 	<audio controls src="data:${entry.value.fileType};base64,${entry.value.fileDataAsBase64}"></audio><br/>
	    	  </c:when>
	    	  <c:otherwise>
	    	  	<img class="${entry.key}" src="data:${entry.value.fileType};base64,${entry.value.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
	    	  </c:otherwise>
	    	</c:choose>
	    	
	    	<input type="file"   name="formFiles[${count.index}].file" id="${entry.key}" data-file-hash="${entry.value.fileName}${entry.value.fileSize}"/><br/>
	      <input type="hidden" name="formFiles[${count.index}].mediaId" value="${entry.value.mediaId}">
	    	<input type="hidden" name="formFiles[${count.index}].mediaType"class="media-type"  value="${entry.key}">
	    	
	    </c:forEach>
     
      <input type="button" value="Upload" class="button"/>
    </form>
    
    <button id="clear-form">Clear Form for Insert</button>
    
    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    
    var FormUtil = new function() {
    	this.storeInitialFormValues = function storeInitialFormValues($selector){

    		var $defaultSelector = $('input,textarea,select');

    		$selector ? $selector: $selector = $defaultSelector;

    		$selector.each(function() {

    			var $this = $(this);

    			var inputType = $this.attr('type');
    			
    			var isCheckbox = inputType === "checkbox";
          var isRadio = inputType === "radio";
          var isFile = inputType === "file";

    			if(isCheckbox || isRadio){
    				$this.data('initialValue', $this.is(':checked'));
    			}else if(isFile){
    				$this.data('initialValue', $this.data('file-hash'));
    			}else {
    				$this.data('initialValue', $this.val());
    			}

    		});
    	};

    	this.checkIfFormValuesIsDirty = function checkIfFormValuesIsDirty($selector){
    		var isDirty = false;

    		var $defaultSelector = $('input,textarea,select');

    		$selector ? $selector: $selector = $defaultSelector;

    		$selector.each(function () {

    			var $this = $(this);

    			var noInitialValue = $this.data('initialValue') === undefined;
    			if(noInitialValue) return true;

					var inputType = $this.attr('type');
    			
    			var isCheckbox = inputType === "checkbox";
          var isRadio = inputType === "radio";
          var isFile = inputType === "file";


    			var valueChanged = false;

    			if(isCheckbox || isRadio){
    				valueChanged = $this.data('initialValue') != $this.is(':checked');
    			}else if(isFile){
    				valueChanged = ($(this).data('initialValue').length !== 0 &&  $(this).val().length !== 0) && '' + $(this).data('initialValue') !== $(this)[0].files[0].name + '' + $(this)[0].files[0].size;
    			}else{
    				valueChanged = $this.data('initialValue') != $this.val();
    			}

	        if(valueChanged){
	            isDirty = true;
	            return false;
	        }

    	    });

    	   return isDirty;
    	};
    	
    };
    
    FormUtil.storeInitialFormValues();
    
    	$('.button').on('click', function() {
    	  
    	  var $form = $('form');
    	  
    	 	var formData = new FormData($form[0]);    	  
    	  
    	  $('form input[type="file"]').each(function() {
    		  
    		  
    		  var isDirty = FormUtil.checkIfFormValuesIsDirty($(this));
    		  
    		  
    		  if(isDirty) return true;
    		  
    		  var name = $(this).attr('name');
    		  var formFileGroup = name.match(/formFiles\[\d{1}\]/);
    		  
    		  //This might not be supported in IE or Edge, we might need to delete the name from the input
    		  formData.delete(name);
    		  formData.delete(formFileGroup + '.mediaId');
    		  formData.delete(formFileGroup + '.mediaType');
    		  
    	  });
    	  
    	  
    	  
    	  $.ajax({
    	    url: 'save-page-form',
    	    type: 'POST',
    	    processData: false, 
    	    contentType: false,
    	    data: formData
    	    
    	  }).done(function(data) {
    	    alert("Save successfullllll");
    	  });
    	  
    	});
    	
    	$('input[type="file"]').on('change', function() {
    	  
    	  var className = this.id;
    	  var imageFile = this.files[0];
    	  
    	  
    	  var reader  = new FileReader();
    	  
    	  reader.readAsDataURL(imageFile);
    	  
    	  $(reader).on('load' ,function(event) {
          	$('img.' + className).attr("src", event.target.result);
      	  });
    	  
    	});
    	
    	
    	$('#clear-form').on('click', function() {
    		$('form input').not('.media-type, .button').val('');
    	});
    
    </script>
  </body>
</html>