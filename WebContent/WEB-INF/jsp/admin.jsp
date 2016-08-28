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
      
      
      <img class="carouselImageSmall" src="data:${carousalSmall.fileType};base64,${carousalSmall.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <input type="file"   name="formFiles[0].file" id="carouselImageSmall" value="${carousalSmall.fileName}"/><br/>
      <input type="hidden" name="formFiles[0].mediaId" value="${carousalSmall.mediaId}">
    	<input type="hidden" name="formFiles[0].mediaType"class="media-type"  value="CAROUSEL_IMAGE_SMALL">
      
      <img class="carouselImageBig" src="data:${carousalBig.fileType};base64,${carousalBig.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <input type="file"   name="formFiles[1].file" id="carouselImageBig" value="${carousalBig.fileName}"/><br/>
      <input type="hidden" name="formFiles[1].mediaId" value="${carousalBig.mediaId}">
    	<input type="hidden" name="formFiles[1].mediaType" class="media-type" value="CAROUSEL_IMAGE_BIG">
      
      <img class="backgroundPage" src="data:${pageBackGround.fileType};base64,${pageBackGround.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <input type="file"   name="formFiles[2].file" id="backgroundPage" value="${pageBackGround.fileName}"/><br/>
      <input type="hidden" name="formFiles[2].mediaId" value="${pageBackGround.mediaId}">
    	<input type="hidden" name="formFiles[2].mediaType" class="media-type" value="PAGE_BACKGROUND">
      
      <img class="backgroundInner" src="data:${backgroundInner.fileType};base64,${backgroundInner.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <input type="file"   name="formFiles[3].file" id="backgroundInner"  value="${backgroundInner.fileName}"/><br/>
      <input type="hidden" name="formFiles[3].mediaId" value="${backgroundInner.mediaId}">
    	<input type="hidden" name="formFiles[3].mediaType" class="media-type" value="BACKGROUND_INNER">
      
      <audio controls src="data:${soundFile.fileType};base64,${soundFile.fileDataAsBase64}"></audio>
      <input type="file"   name="formFiles[4].file" id="soundFile" value="${soundFile.fileName}"/><br/>
      <input type="hidden" name="formFiles[4].mediaId"   value="${soundFile.mediaId}">
    	<input type="hidden" name="formFiles[4].mediaType" class="media-type" value="SOUND_FILE">
      
     
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

    			var isCheckbox = $this.is('input[type="checkbox"]');
          var isRadio = $this.is('input[type="radio"]');

    			if(isCheckbox || isRadio){
    				$this.data('initialValue', $this.is(':checked'));
    			}else{
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

    			var isCheckbox = $this.is('input[type="checkbox"]');
    			var isRadio = $this.is('input[type="radio"]');

    			var valueChanged = false;

    			if(isCheckbox || isRadio){
    				valueChanged = $this.data('initialValue') != $this.is(':checked');
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
    		  
    		  formData.delete($(this).attr('name'));
    		  
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