$(document).ready(function() {
    setupButtons();
})

function setupButtons() {
    $("#ghost_him_page").on("click", doPostUsers);
    $("#spit_your_guts_page").on("click", doPostVictims);
}

function errorCallback(request, status, error) {
    console.log(request)
    console.log(status)
    console.log(error)
}



function doPostUsers() {

    $.ajax({
        url: 'http://localhost:8080/ferramisto/api/supporters/add',
        type: 'POST',
        data: JSON.stringify({
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            
            message:$('#message').val()
        }),
        async: true,
        contentType: 'application/json',
        success:resetCustomer()
        ,
        error: errorCallback
    });
}

function doPostVictims() {
    $.ajax({
        url: 'http://localhost:8080/ferramisto/api/user/add',
        type: 'POST',
        data: JSON.stringify({
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            
            message:$('#message').val()
        }),
        async: true,
        contentType: 'application/json',
        success:resetCustomer(),
        error: errorCallback
    });
}

function doDelete(event) {

    $("#deleted").show();

    var id = event.target.id.split("-")[2];
    $.ajax({
        url: 'http://localhost:8080/javabank5/api/customer/' + id,
        type: 'DELETE',
        async: true,
        success: doGet,
        error: errorCallback
    });
}




function loadCustomers(response) {
   
    var table = $('#user_table');

    response.forEach(function (element) {
        var htmlStr = "<tr id=customer-data><td>" + element.firstName +
            "</td>" + "<td>" + element.lastName +
            "</td>" + "<td>" + element.email +
            "</td>" + "<td>" + element.phone +
            "</td>" +
            '<td><button type="button" id="edit-btn-' + element.id +
            '" class="edit-btn btn btn-success">edit</button></td>'
            + '<td><button type="button" id="remove-btn-' + element.id +
            '" class="remove-btn btn btn-danger">delete</button></td></tr>';
        $(htmlStr).appendTo(table);

        $("#edit-btn-" + element.id).on("click", doEdit);
        $("#remove-btn-" + element.id).on("click", doDelete);

    });
}

function resetCustomer() {
    $('.form-control').val('')
}