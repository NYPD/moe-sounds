/* Cached Variables *******************************************************************************/
var currentPageId = sessionStorage.getItem('currentPageId');

/* Initialization *********************************************************************************/
ion.sound({
  sounds: [
    {
      name: currentPageId
    }
  ],
  multiplay: true,
  path: "get-sound-file/",
  preload: true
});

/* Listeners **************************************************************************************/
$('.sound_play').on('click', function() {
  ion.sound.play(currentPageId);
});


