$(document).ready(function() {
    $('tr').click(function() {
        $('.selected').removeClass('selected');
        $(this).addClass('selected');
    });
});

function submitRow(){
    var id = $('.selected').children(':first').text();
    if(!id){
        alert('Select a row first.');
        event.preventDefault();
        return false;
    }
    var input = $('<input />').attr('type','hidden').attr('name','id').val(id);
    $('#form').append(input);
    return true;
}