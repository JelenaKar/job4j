$(document).ready(() => {
    $('select#make').change(function(event) {
        const post_url = window.location.href.match(/(.+\/)/g) + "models";
        const selected_id = $(this).find('option:selected').val();
        $.ajax({
            url : post_url,
            type: "POST",
            data : {make_id: selected_id}
        }).done(models => refresh(models))
            .fail(err => console.log(err));
    });

    $('#upload-pic').submit(
        (event) => {
            event.preventDefault();
            const form = $('#upload-pic')[0];
            const data = new FormData(form);
            $.ajax({
                url : window.location.href.match(/(.+\/)/g) + "upload.do?ad=" + $(".image-upload img").data("ad") + "&folder=" + $(".my-container div").data("folder"),
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                data : data
            }).done((resp) => append_photo(resp))
                .fail(err => console.log(err))
        });

    $('.my-container').on('click','.deleter',function(event) {
        event.preventDefault();
         $.ajax({
            url : window.location.href.match(/(.+\/)/g) + "delete.do",
            type: "POST",
            data : { id: $(this).attr("id")}
        }).done(() => {
            $(this).closest('.photo-container').empty();
         })
             .fail(err => console.log(err));
    });

    $('.is-sold').click(function(event) {
        if (confirm('Вы уверены что хотите закрыть это объявление?')) {
            $.ajax({
                url : window.location.href.match(/(.+\/)/g) + "close.do",
                type: "POST",
                data : {id: $(this).data("ad")}
            }).done(url => {
                window.location.replace(url);
            })
                .fail(err => console.log(err));
        }
    });

    $('#sent-form').click(
        (event) => {
            event.preventDefault();
            const data = $('#ad-form').serialize() + "&folder=" + $(".my-container div").data("folder");
            $.ajax({
                type: "POST",
                cache: false,
                data : data
            }).done((url) => window.location.replace(url))
                .fail(err => console.log(err))
        }
    );
});

$(document).on('change', "#file-input", function() {
    if ($(this).val()) {
        $('#upload-pic').submit();
    }
});

function refresh(models) {
    $("#model").empty();
    $('#model').append($('<option>', { text: '-' }));
    $.each(JSON.parse(models), function (i, val) {
        $('#model').append($('<option>', {
            value: val.id,
            text : val.name
        }));
    });
}

function append_photo(photo) {
    const jphoto = JSON.parse(photo);
    const photo_elem = "<div class=\"text-center photo-container\" data-folder=\""+ jphoto.folder.name +"\">"
        + "<img src=\"" + window.location.href.match(/(.+\/)/g) + "download?folder=" + jphoto.folder.name + "&name=" + jphoto.name + "\" class=\"img-responsive my-thumbnail\">"
        + "<button id=\"" + jphoto.id + "\" class=\"deleter\">Удалить</button>"
        + "</div>";
    $(photo_elem).insertBefore('div.image-upload');
}
