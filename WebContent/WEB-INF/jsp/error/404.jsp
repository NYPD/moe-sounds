<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Moe Maintenance</title>
    <link rel="shortcut icon" href="${context}/images/favicon.ico" />

    <!-- Styles -->
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Baloo" rel="stylesheet">
    <link href="${context}/css/global.css" rel="stylesheet">
    <link href="${context}/css/404.css" rel="stylesheet">
    
    <script type="text/javascript">
      sessionStorage.setItem('context', '${context}');
    </script>

  </head>
  
  <body data-tab-id="admin-nav">
  
    <%@ include file = "../fragments/navbar.jsp" %>
  
    <div class="container">
      
      <div class="row">
      
        <div class="col-md-6 col-text-container">
          <h1>404</h1>
          <p>What URL on earth are you trying to access? <br> <small>(If it was our fault, sorry, please submit a bug report)</small></p>
        </div>
        
        <div class="col-md-6">
          <img alt="pekoe confused" src="${context}/images/pekoe-404.png" class="center-block 404-image">
        </div>
        
      </div>
      
    </div>
    
    <%@ include file = "../fragments/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file = "../fragments/global-modals.jsp" %>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/global.js"></script>
    
  </body>
  
</html>