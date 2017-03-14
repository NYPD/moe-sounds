<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>

    <title>Moe Maintenance</title>
    
    <%@ include file = "fragments/meta-block.jsp" %>

    <!-- Styles -->
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/v/bs/dt-1.10.12/r-2.1.0/datatables.min.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/plug-ins/1.10.12/integration/font-awesome/dataTables.fontAwesome.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Baloo" rel="stylesheet">
    <link href="${context}/css/global.css?v=${projectVersion}" rel="stylesheet">
    <link href="${context}/css/maintenance.css?v=${projectVersion}" rel="stylesheet">
    
    <script type="text/javascript">
      sessionStorage.setItem('context', '${context}');
    </script>

  </head>
  
  <body data-tab-id="admin-nav">
  
    <%@ include file = "fragments/navbar.jsp" %>
  
    <div class="container">
      
      <div class="row">
      
	      <div class="col-xs-12 table-container table-loading">
        
          <h2>Moe Pages</h2>
          
          <div class="text-right">
            <button type="button" class="btn btn-primary btn-add-moe-page">Add Moe Page</button>
          </div>
          
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
                    <div class="row-options">
                      <i class="fa fa-wrench fa-2x edit-page" aria-hidden="true"></i>
                      <div class="visible-xs-block"></div>
                      <i class="fa fa-trash-o fa-2x delete-page" aria-hidden="true"></i>
                    </div>
                  </td>
                  <td>
                    <c:set var="thumbnail" value="${page.getMediaWithMediaType('THUMBNAIL_ICON')}"/>
                    <c:if test="${not empty thumbnail}">
                      <img class="page-carousel-image" src="data:${thumbnail.fileType};base64,${thumbnail.fileDataAsBase64}">
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
    
     <!-- Page Image Preview Backdrop Thingy -->
    <div class="preview-image-container">
      <img class="img-rounded img-preview">
    </div>
    
    <%@ include file = "fragments/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file = "fragments/global-modals.jsp" %>
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
    <script src="${context}/js/global.js?v=${projectVersion}"></script>
    <script src="${context}/js/maintenance.js?v=${projectVersion}"></script>
    
  </body>
  
</html>