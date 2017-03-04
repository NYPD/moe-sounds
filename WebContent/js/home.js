/* Cached Variables *******************************************************************************/
var currentPageId = sessionStorage.getItem('currentPageId');
var $count = $('#count');

/* Initialization *********************************************************************************/
ion.sound({
  sounds: [
    {
      name: currentPageId + '-SOUND_FILE'
    }
  ],
  multiplay: true,
  path: "/get-page-media/",
  preload: true
});

window.setInterval(fetchMoeCount, 10000);//10 Seconds

/* Listeners **************************************************************************************/
$('.sound-play').on('click', function() {
  
  ion.sound.play(currentPageId + '-SOUND_FILE');
  
  var $updateClickCountPromise = $.post('/update-click-count', {pageId: currentPageId});
  
  var currentCount = Number.parseInt($count.text(), 10);
  $count.text(++currentCount);
});


/* Functions **************************************************************************************/
function fetchMoeCount() {
  var $getClickCountPromise = $.get('/get-click-count', {pageId: currentPageId});
  
  $getClickCountPromise.done(function(clickCount) {
    $count.text(clickCount);
  });
}