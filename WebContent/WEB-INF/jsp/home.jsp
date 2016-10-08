<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    <title>Home</title>
    
    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="/css/home.css">
  </head>
  <body>
  
     <label>Page Name</label><input type="text" name="pageName" value="${page.pageName}"><br/>
     <label>CSS</label><textarea rows="10" cols="60" name="css">${page.css}</textarea><br/>
      
      <img class="carouselImageSmall" src="data:${CAROUSEL_IMAGE_SMALL.fileType};base64,${CAROUSEL_IMAGE_SMALL.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <img class="carouselImageBig" src="data:${CAROUSEL_IMAGE_BIG.fileType};base64,${CAROUSEL_IMAGE_BIG.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <img class="backgroundPage" src="data:${PAGE_BACKGROUND.fileType};base64,${PAGE_BACKGROUND.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <img class="backgroundInner" src="data:${BACKGROUND_INNER.fileType};base64,${BACKGROUND_INNER.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
      <audio controls src="data:${SOUND_FILE.fileType};base64,${SOUND_FILE.fileDataAsBase64}"></audio>
    
    <!-- Scripts -->
  </body>
</html>