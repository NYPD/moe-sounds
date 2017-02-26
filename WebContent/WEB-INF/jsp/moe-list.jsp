<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Moe List</title>
    <link rel="shortcut icon" href="${context}/images/favicon.ico" />
    
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/font-awesome.min.css" rel="stylesheet">
    <link href="${context}/css/global.css" rel="stylesheet">
    <link href="${context}/css/moe-list.css" rel="stylesheet">
    
    <script type="text/javascript">
      sessionStorage.setItem('context', '${context}');
    </script>
    
  </head>
  
  <body data-tab-id="all-nav">
  
    <%@ include file = "fragments/navbar.jsp" %>
  
    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
          <h1>Moe Pages</h1>
        </div>
      </div>  
      
      <div class="row row-thumbnails">
        
        <c:forEach items="${allPages}" var="page" varStatus="loopTagStatus">
          
          <c:set var="count" value="${loopTagStatus.index + 1}"/>
          <c:set var="thumbnail" value="${page.getMediaWithMediaType('CAROUSEL_IMAGE')}"/>
          
          <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
            <div class="thumbnail">
              <img class="thumbnail-image" src="data:${thumbnail.fileType};base64,${thumbnail.fileDataAsBase64}" alt="${page.pageName}" data-page-id="${page.pageId}">
              
              <div class="caption">
                <h4>${page.pageName}</h4>
                <span class="label label-primary">${page.clickCount}</span>
              </div>
            </div>
          </div>
          
          <!-- Add the extra clearfix for only the required viewport. The number mod'd is the amount of images per row at that media query -->
          <c:if test="${count % 6 == 0}">
            <div class="clearfix visible-lg-block"></div>
          </c:if>
          
          <c:if test="${count % 4 == 0}">
            <div class="clearfix visible-md-block"></div>
          </c:if>
          
          <c:if test="${count % 3 == 0}">
            <div class="clearfix visible-sm-block"></div>
          </c:if>
          
          <c:if test="${count % 2 == 0}">
            <div class="clearfix visible-xs-block"></div>
          </c:if>
        
        </c:forEach>
      
      </div>
      
    </div>
    
    <%@ include file = "fragments/footer.jsp" %>
  
    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/global.js"></script>
    <script src="${context}/js/moe-list.js"></script>
  </body>
</html>