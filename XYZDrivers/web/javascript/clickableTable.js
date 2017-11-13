$('tr').click(function() {
    alert("CLICKED");
    $('.selected').removeClass('selected');
    $(this).addClass('selected');
});

function onClick() {
    var id = $('.selected').children()[0]
    var form = document.getElementById('form');
    form.append('<input type="hidden" name="id" value="" />');
    form.submit();
}