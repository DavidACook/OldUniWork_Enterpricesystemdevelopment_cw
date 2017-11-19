// Add function on page load
$(document).ready(function() {
    // (func on table row click) Selects a clicked row, removes previous selection
    $('tbody tr').click(function() {
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

// Bubble sort a table by selected column
function sortTable(column){
    order = headerSelect(column);
    rows = $('#table > tbody > tr');
    sorting = true;
    // Stop when no sorts are left
    while(sorting){
        sorting = false;
        for(i = 0; i < (rows.length - 1); i++){
            // Rows list must be refreshed or won't point to corret row
            rows = $('#table > tbody > tr');
            element1 = rows[i].children[column];
            element2 = rows[i+1].children[column];
            if(order == 'asc'){
                if(element1.innerHTML.toLowerCase() > element2.innerHTML.toLowerCase()){
                    // Put the first row (1) after the next row (2) 
                    $(rows[i]).next().after($(rows[i]));
                    sorting = true;
                } 
            } else {
                if(element1.innerHTML.toLowerCase() < element2.innerHTML.toLowerCase()){
                    // Put the first row (1) after the next row (2) 
                    $(rows[i]).next().after($(rows[i]));
                    sorting = true;
                } 
            }
        }
    }
}

function headerSelect(column){
    id = '#header' + column;
    header = $(id);
    order = 'asc';
    
    // Toggle asc or desc for selected header
    if(header.hasClass('asc') || header.hasClass('desc')){
        header.toggleClass('asc');
        header.toggleClass('desc');
        order = header.attr('class');
    } else {
        header.addClass('asc');
    }
    
    // Remove class from any different headers
    $('.asc:not(' + id + ')').removeClass('asc');
    $('.desc:not(' + id + ')').removeClass('desc');
    return order;
}