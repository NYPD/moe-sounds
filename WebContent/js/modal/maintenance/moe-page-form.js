/* Cached Variables *******************************************************************************/
var $moePageForm = $('#moe-page-form');
var $modalContent = $('.modal-content');

var $body = $('body');
var $imgPreview = $('.img-preview')


/* Listeners **************************************************************************************/
$moePageForm.on('change', 'input[type="file"]', function() {
  
  var file = this.files[0];
  
  var noFile = file === undefined;
  if(noFile)
    $(this).closest('.input-group').find('input.file-name').val('');
  else
    $(this).closest('.input-group').find('input.file-name').val(file.name);
  
  
});

$moePageForm.on('click', '.btn-preview-image', function() {
  
  var file = $(this).closest('.input-group-btn').find('input.file-data')[0].files[0];
  
  var noFile = file === undefined;
  if (noFile) return false;
  
  var reader = new FileReader();
  
  reader.readAsDataURL(file);
  
  $(reader).on('load' ,function(event) {
    $body.addClass('show-preview-image');
    $imgPreview.attr('src', event.target.result);
  });
  
});

$('.preview-image-container').on('click', function(event) {
  
  var isImage = event.target.nodeName === 'IMG';
  if(isImage) return false;
  
  $body.removeClass('show-preview-image');
  $imgPreview.attr('src', '');
  
})

/* Document Ready Stuff ***************************************************************************/

//# sourceURL=moe-page-form.js