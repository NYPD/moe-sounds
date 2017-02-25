<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    <title>Home</title>
    
    <!-- Styles -->
    
    <!-- How to include a css file, css files can be found in `WebContent > css` -->
    <link rel="stylesheet" type="text/css" href="${context}/css/home.css">
    
    
    <!-- How to use the specific page css -->
    <style>${page.css}</style>
  </head>
  <body>
  
    <!-- This 'page' object is being added to the jsp/html in the home controller, Java Resources > src > com.moesounds.controller > HomeController -->

    <!-- page name -->
    ${page.pageName}
    
    
    <br/>
    
    <!-- page id -->
    ${page.pageId}
    
    
    <br/>
    
    <!-- page click count -->
    ${page.clickCount}
    
    
    <br/>
    
    <!-- CAROUSEL_IMAGE -->
    <c:set var="carousel" value="${page.getMediaWithMediaType('CAROUSEL_IMAGE')}"/>
    <img  src="data:${carousel.fileType};base64,${carousel.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
    
    <!-- PAGE_BACKGROUND  -->
    <c:set var="pageBackground" value="${page.getMediaWithMediaType('PAGE_BACKGROUND')}"/>
    <img src="data:${pageBackground.fileType};base64,${pageBackground.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
    
    <!-- page BACKGROUND_INNER  -->
    <c:set var="backgroundInner" value="${page.getMediaWithMediaType('BACKGROUND_INNER')}"/>
    <img src="data:${backgroundInner.fileType};base64,${backgroundInner.fileDataAsBase64}" height="200" alt="Image preview..."><br/>
    
    <!-- page THUMBNAIL_ICON  -->
    <c:set var="thumbnail" value="${page.getMediaWithMediaType('THUMBNAIL_ICON')}"/>
    <img class="page-thumbnail-image" src="data:${thumbnail.fileType};base64,${thumbnail.fileDataAsBase64}" height="200" >
    
    <!-- SOUND_FILE, but we dont need this  -->
    <c:set var="soundFile" value="${page.getMediaWithMediaType('SOUND_FILE')}"/>
    <audio controls src="data:${soundFile.fileType};base64,${soundFile.fileDataAsBase64}"></audio>
  
  
    <!-- to check if something is not empty, use the "var" or whichver c:set -->
    <c:if test="${not empty thumbnail}">
      cool stuff in here
    </c:if>
    
    <button id="play-sound">Play moe sound</button>
    
    <!-- How to include a script, script can be found in `WebContent > js` 
    always put vendor and more generic gs at the top and specific js at the bottom 
    Also always put script files at the very end of the <body> to make the webpage appear to load faster for the user -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/ion.sound.min.js"></script>
    <script src="${context}/js/global.js"></script>
    
    <!-- I set in the browser session what page id currently loaded, and retrieve it in the home.js -->
    <script type="text/javascript">
      sessionStorage.setItem('currentPageId', '${page.pageId}');
    </script>
    <script src="${context}/js/home.js"></script>
    
  </body>
</html>