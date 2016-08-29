<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    <title>Home</title>
    
    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="/css/home.css">
  </head>
  <body>
  
    <!-- File input -->    
    <label>Page Name</label><input type="text" name="pageName" value="${page.pageName}"><br/>
    <label>CSS</label><textarea rows="10" cols="60" name="css">${page.css}</textarea><br/>
    
    <c:forEach items="${page.media}" var="entry">
    	
    	<c:choose>
    	  <c:when test="${entry.key eq 'SOUND_FILE'}">
    	 	 	<audio controls src="data:${entry.value.fileType};base64,${entry.value.fileDataAsBase64}"></audio>
    	  </c:when>
    	  <c:otherwise>
    	  	<img class="carouselImageSmall" src="data:${entry.value.fileType};base64,${entry.value.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
    	  </c:otherwise>
    	</c:choose>
    </c:forEach>
    
    <!-- Scripts -->
  </body>
</html>