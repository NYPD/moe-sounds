<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  
    <title>Home</title>
    
    <link rel="shortcut icon" href="${context}/images/favicon.ico" />
    <link href="https://fonts.googleapis.com/css?family=Unica+One|Passion+One|Roboto|Oswald|Anton|Exo+2:900|Raleway:700" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" type="text/css" href="${context}/css/home.css">
    
    <!-- Inline Styles from page object -->
    <style>
      .main {
        background-image: url('${context}/get-page-media/${page.pageId}-PAGE_BACKGROUND');
      }
      .thumb_large {
        background-image: url('${context}/get-page-media/${page.pageId}-BACKGROUND_INNER');
      }
    </style>
    <style>${page.css}</style>
  </head>
  <body>
  
    <nav>
      <a href="https://github.com/NYPD/moe-sounds" target="_blank">about</a>
      <a href="${context}/all">all</a>
    </nav>

    <div class="main">

      <div class="main_inner">
        
        <div class="thumb_large"></div>
        
        <div class="hit_count">
          <span id="count">${page.clickCount}</span>
        </div>

        <div class="sound_play">
          <c:set var="thumbnail" value="${page.getMediaWithMediaType('THUMBNAIL_ICON')}"/>
          <c:if test="${not empty thumbnail}">
            <div class="thumbnail-container">
              <img class="page-thumbnail-image" src="data:${thumbnail.fileType};base64,${thumbnail.fileDataAsBase64}">
            </div>
          </c:if>
          <p class="play_display noselect">PLAY</p>
        </div>

        <div class="sound_name">
          <p class="noselect">${page.pageName}</p>
        </div>
        
        <div class="sound-footer">
        
          <div class="share_links">
            <div class="social-media facebook">
              <div class="fb-share-button" data-href="${applicationUrl}" data-layout="button_count"></div>
            </div>
  
            <div class="social-media twitter">
              <a href="https://twitter.com/share" class="twitter-share-button" data-text="Check out this moe site:" data-url="${applicationUrl}" data-show-count="false"></a>
            </div>
          </div>
  
          <div class="project-info">
            <span id="misc_text">
              <span>${projectName}</span>
              <a class="version-link" href="https://github.com/NYPD/moe-sounds/releases" target="_blank">v${projectVersion}</a>
            </span>
          </div>
          
        </div>

      </div>

    </div>

    <footer class="footer">
      <span class="copyright">&copy;<span id="copyright-year"></span> moe-sounds</span>
    </footer>
  
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/ion.sound.min.js"></script>
    <script src="${context}/js/global.js"></script>
    
    <!-- I set in the browser session what page id currently loaded, and retrieve it in the home.js -->
    <script type="text/javascript">
      sessionStorage.setItem('currentPageId', '${page.pageId}');
    </script>
    <script src="${context}/js/home.js"></script>
    
    <!-- Facebook Like Script -->
    <div id="fb-root"></div>
    <script>
      (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8";
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
    </script>
    <!-- Twitter Share Script from twitcount.com -->
    <script type='text/javascript'>
      !function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
            .test(d.location) ? 'http' : 'http';
        if (!d.getElementById(id)) {
          js = d.createElement(s);
          js.id = id;
          js.src = p + '://static1.twitcount.com/js/twitcount.js';
          fjs.parentNode.insertBefore(js, fjs);
        }
      }(document, 'script', 'twitcount_plugins');
    </script>
</body>
</html>