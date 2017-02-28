<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  
    <title>Home</title>
    <link rel="shortcut icon" href="${context}/images/favicon.ico" />
    
    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="${context}/css/home.css">
    <style>
      .wrapper {
        background-image: url('/get-page-media/${page.pageId}-PAGE_BACKGROUND');
      }
      .thumb_large {
        background-image: url('/get-page-media/${page.pageId}-BACKGROUND_INNER');
      }
    </style>
    <style>${page.css}</style>
  </head>
  <body>
  
    <!-- Load Facebook SDK for JavaScript -->
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

    <div class="wrapper">
  
      <div class="top_bar">
        <a href="#">
          <p class="about">
            <u>about</u>
          </p>
        </a>
  
      </div>
  
      <div class="main">
  
        <div class="main_inner">
  
          <div class="tag_line">
            <p id="tag_text">We'll have a global tagline or something</p>
          </div>
  
          <div class="hit_count">
            <h3 id="count">${page.clickCount}</h3>
          </div>
  
          <div class="thumb_large"></div>
  
          <div class="sound_play">
            <p id="play_display" class="noselect">PLAY</p>
          </div>
  
          <div class="sound_text">
            <p id="play_text">${page.pageName}</p>
          </div>
  
          <div class="share_links">
          
            <div class="facebook">
              <div class="fb-share-button" 
                data-href="${applicationUrl}" 
                data-layout="button_count">
              </div>
            </div>
  
            <div class="twitter">
              <a href="https://twitter.com/share" class="twitter-share-button" data-text="Check out this moe site:" data-url="${applicationUrl}" data-show-count="false">Tweet</a>
              <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
            </div>
          </div>
  
          <div class="inner_other">
            <p id="misc_text">
              <span>${projectName}</span>
              <a class="version-link" href="https://github.com/NYPD/moe-sounds/releases">v${projectVersion}</a>
            </p>
          </div>
  
  
  
        </div>
  
      </div>
  
  
  
      <div class="footer">
        <p class="copyright">&copy;<span id="copyright-year"></span> moe-sounds</p>
      </div>
  
    </div>
    
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