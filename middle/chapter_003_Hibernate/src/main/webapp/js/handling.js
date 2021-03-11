$(document).ready(() => {

    $('.button-filter').click(function (e) {
        filter(e, {filter: $(this).attr("id")})
    });

    $('select#brand').change(function (e) {
        filter(e, {filter: $(this).attr("id"), brandId: $(this).find('option:selected').val()})
    });

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
            const folder = $(".my-container").data("folder")
                || (($(".my-container .photo-container").data("folder") == undefined) ? "" : $(".my-container .photo-container").data("folder"));
            console.log(folder);
            $.ajax({
                url : window.location.href.match(/(.+\/)/g) + "upload.do?ad=" + $(".image-upload img").data("ad") + "&folder=" + folder,
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
            const folder = $(".my-container").data("folder")
                || (($(".my-container .photo-container").data("folder") == undefined) ? "" : $(".my-container .photo-container").data("folder"));
            const data = $('#ad-form').serialize() + "&folder=" + folder;
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

function filter(event, args) {
    event.preventDefault();
    let query_params = "?" + Object.keys(args).map(function(k){return k + "=" + args[k]}).join("&");
    $.ajax({
        url : window.location.href.match(/(.+\/)/g) + "select" + query_params,
        type: "POST",
        //data : data
    }).done((data) => {
        $('#auto-table > tbody').empty().append(receive_filtered(data));
    })
        .fail(err => console.log(err))
}

function append_photo(photo) {
    const jphoto = JSON.parse(photo);
    const photo_elem = "<div class=\"text-center photo-container\" data-folder=\""+ jphoto.folder.name +"\">"
        + "<img alt=\"car photo\" src=\"" + window.location.href.match(/(.+\/)/g) + "download?folder=" + jphoto.folder.name + "&name=" + jphoto.name + "\" class=\"img-responsive my-thumbnail\">"
        + "<button id=\"" + jphoto.id + "\" class=\"deleter\">Удалить</button>"
        + "</div>";
    $(photo_elem).insertBefore('div.image-upload');
}

function receive_filtered(data) {
    console.log(data);
    const ads = JSON.parse(data);
    let res = '';
    Object.keys(ads).forEach(key => {
        const ad = ads[key];
        const status = (ad.isSold) ? "Продано" : "В продаже";
        res += '<tr>'
            + '<td>' + ad.auto.make.name + " " + ad.auto.model.name + '</td>'
            + '<td>' + ad.auto.manufactured + '</td>'
            + '<td>' + ad.auto.bodyStyle.name + '</td>'
            + '<td>' + ad.auto.engine.name + '</td>'
            + '<td>' + ad.auto.driveUnit.name + '</td>'
            + '<td>' + ad.auto.transmission.name + '</td>'
            + '<td>' + parseFloat(ad.price).toLocaleString('ru') + ' руб.</td>'
            + '<td>' + moment(ad.created).format('DD.MM.YYYY HH:mm') + '</td>'
            + '<td>' + status + '</td>'
            + '<td>'
                + '<a href="'+ window.location.href.match(/(.+\/)/g) +'view?ad=' + ad.id + '" class="btn btn-info">Подробнее</a>'
            + '</td>'
            + '<tr>';
    })
    return res;
}

/*
*
    <td>
        <a href="<c:url value="/view?ad=${ad.id}"/>" class="btn btn-info">Подробнее</a>
        <c:if test="${ad.seller.id == current.id && !ad.isSold}">
            <a href="<c:url value="/update.do?ad=${ad.id}"/>" class="btn btn-secondary">Редактировать</a>
        </c:if>
    </td>
* */
