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
    
    <script type="text/javascript">
      sessionStorage.setItem('context', '${context}');
    </script>

  </head>
  
  <body data-tab-id="admin-nav">
  
    <%@ include file = "../fragments/navbar.jsp" %>
  
    <div class="container">
      
      <div class="row">
      
        <div class="col-sm-6">
        
          <h2>Ooops</h2>
          <p>Something broke back here, hopefully it is not too much a concern. See if you can try doing whatever you were trying to do. If this keeps happening, submit a bug
          report by clicking the link on the footer and let the developer know he goofed up.</p>
        </div>
        
        <div class="col-sm-6">
          <img alt="lillie gasp" src="${context}/images/lillie-gasp.png" class="center-block">
        
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