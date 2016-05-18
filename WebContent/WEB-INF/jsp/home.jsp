<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    <title>Home</title>
    
    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="/css/home.css">
    
  </head>
  <body>
    <p>Zoopies is cute</p>
    
    <ul>
      <c:forEach items="${allUsers}" var="user">
        <li>${user.userId} - ${user.nickname}</li>
      </c:forEach>
    </ul>
    
    <form id="form1" method="post" action="/spring-mvc-file-upload/rest/cont/upload" enctype="multipart/form-data">
 
      <!-- File input -->    
      <label>Page Name</label><input type="text" name="pageName"><br/>
      <label>CSS</label><textarea rows="10" cols="60" name="css"></textarea><br/>
      
      <img class="carouselImageSmall" src="data:image/png;base64,${encodeBase64String}" height="200" alt="Image preview...">
      <input name="carouselImageSmall" id="file" type="file" /><br/>
      
      <img class="carouselImageBig" src="" height="200" alt="Image preview...">
      <input name="carouselImageBig" id="file" type="file" /><br/>
      
      <img class="backgroundPage" src="" height="200" alt="Image preview...">
      <input name="backgroundPage" id="file" type="file" /><br/>
      
      <img class="backgroundInner" src="" height="200" alt="Image preview...">
      <input name="backgroundInner" id="file" type="file" /><br/>
      
     
      <input type="button" value="Upload" class="button"/>
    </form>
    
    
    
    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    
    
    	$('.button').on('click', function() {
    	  
    	  var $form = $('form');
    	  
    	  var formData = new FormData($form[0]);
    	  
    	  $.ajax({
    	    url: 'form-save',
    	    type: 'POST',
    	    processData: false, 
    	    contentType: false,
    	    data: formData
    	    
    	  }).done(function(data) {
    	    alert("Save successfullllll");
    	  });
    	  
    	});
    	
    	$('input[type="file"]').on('change', function() {
    	  
    	  var className = this.name;
    	  var imageFile = this.files[0];
    	  
    	  
    	  var reader  = new FileReader();
    	  
    	  reader.readAsDataURL(imageFile);
    	  
    	  $(reader).on('load' ,function(event) {
          	$('img.' + className).attr("src", event.target.result);
      	  });
    	  
    	});
    
    </script>
  </body>
</html>