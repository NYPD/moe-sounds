/* Cached Variables *******************************************************************************/
var $moePageForm = $('#moe-page-form');
var $modalContent = $('.modal-content');

var $body = $('body');
var $imgPreview = $('.img-preview')


/* Listeners **************************************************************************************/
$moePageForm.on('change', 'input[type="file"]', function() {
  
  var $inputGroup = $(this).closest('.input-group');
  var $inputFileName = $inputGroup.find('input.file-name');
  var $fileActionButtons = $inputGroup.find('.input-group-btn-file-actions').find('.btn');
  
  var file = this.files[0];
  var noFile = file === undefined;
  
  if(noFile) {
    $inputFileName.val('');
    $fileActionButtons.prop('disabled', true);
  } else {
    $inputFileName.val(file.name);
    $fileActionButtons.prop('disabled', false);
  }
  
});

$moePageForm.on('click', '.btn-remove-file', function(event) {
  $(this).closest('.input-group').find('input.file-data').val('').trigger('change');
});

$moePageForm.on('click', '.btn-preview-image', function() {
  
  var file = $(this).closest('.input-group').find('input.file-data')[0].files[0];
  
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
  
});

$('.btn-save-moe-page').on('click', function() {
  
  var formData = new FormData($moePageForm[0]);
  
  $moePageForm.find('.file-data').each(function() {
    
    var $this = $(this);
    
    var isDirty = ritsu.isFormDirty($this);
    if (isDirty) return true;
    
    //If the file input field is not dirty (was not changed), remove it from the formData so we tell the back-end to not modify this file
    var name = $this.attr('name');
    var formFileGroup = name.match(/formFiles\[\d{1}\]/);
    
    //This might not be supported in IE or Edge, we might need to delete the name from the input
    formData.delete(name);
    formData.delete(formFileGroup + '.mediaId');
    formData.delete(formFileGroup + '.mediaType');
    
  });
  
  
  $.ajax({
    url: 'save-page-form',
    type: 'POST',
    contentType: false,
    processData: false, 
    data: formData
    
  }).done(function(pageId) {
    alert("Save successfullllll");
  });
  
});

/* Initialization *********************************************************************************/
ritsu.initialize({
  useBootstrap3Stlying: true
});

ritsu.storeInitialFormValues();

/* Document Ready Stuff ***************************************************************************/

//# sourceURL=moe-page-form.js