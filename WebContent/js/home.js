/* Cached Variables *******************************************************************************/
var currentPageId = sessionStorage.getItem('currentPageId');
var $count = $('#count');
var $stutterSetting = $('#stutter-setting');

var pageHowl = new Howl({
  format: ['mp3'],
  preload : true,
  src: ['/get-page-media/' + currentPageId + '-SOUND_FILE']
});

var soundFileNotLoaded = true;

/* Initialization *********************************************************************************/
$stutterSetting[0].checked = localStorage.getItem('stutter') === 'true';

pageHowl.once('load', function(){
  soundFileNotLoaded = false;
});

window.setInterval(fetchMoeCount, 10000);//10 Seconds

/* Listeners **************************************************************************************/
$('.sound-play').on('click', function() {
  
  if(soundFileNotLoaded) return false;
  
  var isStutter = localStorage.getItem('stutter') === 'true';
  if(isStutter) pageHowl.stop();
  
  pageHowl.play();
  $.post('/update-click-count', {pageId: currentPageId});
  
  var currentCount = Number.parseInt($count.text(), 10);
  $count.text(++currentCount);
});

$stutterSetting.on('click', function() {
  localStorage.setItem('stutter', this.checked);
});

/* Functions **************************************************************************************/
function fetchMoeCount() {
  var $getClickCountPromise = $.get('/get-click-count', {pageId: currentPageId});
  
  $getClickCountPromise.done(function(clickCount) {
    $count.text(clickCount);
  });
};