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
  </body>
</html>