// Add function on page load
$(document).ready(function() {
    // (func on table row click) Selects a clicked row, removes previous selection
    $('tr').click(function() {
        $('.selected').removeClass('selected');
        $(this).addClass('selected');
    });
});

// When time to submit, add id of selected row to the form as a parameter
function submitRow(){
    var id = $('.selected').children(':first').text();
    // Prevent submission is no row is selected
    if(!id){
        alert('Select a row first.');
        event.preventDefault();
        return false;
    }
    var input = $('<input />').attr('type','hidden').attr('name','id').val(id);
    $('#form').append(input);
    return true;
}