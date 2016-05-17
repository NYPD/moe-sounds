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
      <input type="text" name="name"><br/>
      <input name="file" id="file" type="file" /><br/>
     
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
    	    alert(data);
    	  });
    	  
    	});
    
    </script>
  </body>
</html>