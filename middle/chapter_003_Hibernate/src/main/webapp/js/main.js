$(document).ready(() => {
    $("#task-form").submit(function(event){
        event.preventDefault();
        const post_url = $(this).attr("action");
        const form_data = $(this).serialize();
        $.ajax({
            url : post_url,
            type: "POST",
            data : form_data
        }).done(data => add(data))
            .fail(err => console.log(err));
    });

    $('input[type="checkbox"]').click(function(){
        $.ajax({
            url : window.location.href,
            type: "POST",
            data : { id: $(this).closest("tr").attr("id"), isDone: $(this).prop("checked"), op: "update" }
        }).fail(err => console.log(err));
    });
});

function add(data) {
    const json = JSON.parse(data);
    const res = '<tr>'
        + '<td>' + json.id + '</td>'
        + '<td>' + json.description + '</td>'
        + '<td>' + json.login + '</td>'
        + '<td>' + moment(json.created).format('DD.MM.YYYY HH:mm') + '</td>'
        + '<td><input type="checkbox" name="status"></td>'
    + '<tr>';
    $('#task-table tbody').prepend(res);
}