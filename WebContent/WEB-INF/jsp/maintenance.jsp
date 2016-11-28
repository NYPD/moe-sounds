<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Moe Maintenance</title>

    <!-- Styles -->
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/v/bs/dt-1.10.12/r-2.1.0/datatables.min.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/plug-ins/1.10.12/integration/font-awesome/dataTables.fontAwesome.css" rel="stylesheet"/>
    <link href="${context}/css/global.css" rel="stylesheet">
    <link href="${context}/css/maintenance.css" rel="stylesheet">

  </head>
  
  <body>
  
    <div class="container">
      
      <nav class="navbar navbar-default">
  	    <div class="container-fluid">
        
  	      <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
  	        <a class="navbar-brand" href="${context}/random">
              <img src="https://v.dreamwidth.org/231938/328523">
              <span>Moe Sounds</span>
  	        </a>
  	      </div>
          
          <div class="collapse navbar-collapse" id="navbar-collapse">
            <p class="navbar-text navbar-right">
              <span>Signed in as ${moeSoundsSessionBean.user.nickname}</span>
              <img class="user-profile-picture" src="${moeSoundsSessionBean.user.userProfilePicture}">
            </p>
          </div>
          
  	    </div>
  	  </nav>
    
      <h2>Moe Pages ${projectVersion}</h2>
      
      <div class="row">
      
	      <div class="col-xs-12 table-container table-loading">
        
	        <table class="table table-striped table-moe-pages" id="moe-pages">
	          <thead>
	            <tr>
	              <th></th>
	              <th></th>
	              <th>Page Name</th>
                <th>Missing Media Count</th>
	              <th>Click Count</th>
	            </tr>
	          </thead>
	          <tbody>
	            <c:forEach items="${allPages}" var="page">
	              <tr data-page-id="${page.pageId}">
                  <td>
                    <i class="fa fa-wrench fa-2x edit-page" aria-hidden="true"></i>
                    <i class="fa fa-trash-o fa-2x delete-page" aria-hidden="true"></i>
                  </td>
                  <td>
                    <c:set var="thumbnail" value="${page.getMediaWithMediaType('THUMBNAIL_ICON')}"/>
                    <c:if test="${not empty thumbnail}">
                      <img class="page-thumbnail-image" src="data:${thumbnail.fileType};base64,${thumbnail.fileDataAsBase64}">
                    </c:if>
                  </td>
                  <td>${page.pageName}</td>
                  <td>${page.missingMediaCount}</td>
                  <td>${page.clickCount}</td>              
	              </tr>
	            </c:forEach>
	          </tbody>
	        
	        </table>
	      
	      </div>
	      
      </div>
      
    </div>
    
    <!-- Modals -->
    <%@ include file = "global-modals.jsp" %>
    <div class="modal fade" id="maintenance-modal-large" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-lg" role="document"></div>
    </div>
    <div class="modal fade" id="maintenance-modal-small" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-sm" role="document"></div>
    </div>
    

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/v/bs/dt-1.10.12/r-2.1.0/datatables.min.js"></script>
    <script src="${context}/js/global.js"></script>
    <script src="${context}/js/maintenance.js"></script>
    
    <!-- Page Image Preview Backdrop Thingy -->
    <div class="preview-image-container">
	    <img class="img-rounded img-preview">
	  </div>
	  
  </body>
  
</html>

