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

    $('#task-table').on('click','input[type="checkbox"]',function(event) {
        $.ajax({
            url : window.location.href,
            type: "POST",
            data : { id: $(this).closest("tr").attr("id"), isDone: $(this).prop("checked"), op: "update" }
        }).fail(err => console.log(err));
    });

    $('#submitter').click(
        (event) => {
            event.preventDefault();
            const data = $('#login-form').serialize();
            $.ajax({
                type: "POST",
                cache: false,
                data : data
            }).done(() => {
                let url = (document.referrer) ? document.referrer : window.location.href.match(/(.+\/)/g) + "all";
                    window.location.replace(url);
                })
                .fail(err => {
                    const res = '<div class="w3-container w3-panel w3-pale-red">'
                        + err.responseText
                    + '</div>';
                    $('div#error').html(res);
                })
        }
    );
});

function add(data) {
    const json = JSON.parse(data);
    const res = '<tr id="' + json.id + '">'
        + '<td>' + json.id + '</td>'
        + '<td>' + json.description + '</td>'
        + '<td>' + json.user.name + '</td>'
        + '<td>' + moment(json.created).format('DD.MM.YYYY HH:mm') + '</td>'
        + '<td><input type="checkbox" name="status"></td>'
    + '<tr>';
    $('#task-table tbody').prepend(res);
}