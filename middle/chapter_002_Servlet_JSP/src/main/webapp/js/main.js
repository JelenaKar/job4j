$(document).ready(() => {
    checkOccupation();
    setInterval(() => {
        checkOccupation();
    }, 30000);

});

function checkOccupation() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/chapter_001_Servlet_JSP_war_exploded/hall",
    }).done(data => markOccupied(data))
        .fail(err => console.log(err));
}

function markOccupied(data) {
    let places = eval(data);

    $('.seat').css("color", "#000");
    $('.seat input:radio').attr('disabled',false);

    $.each(places, function(index, value){
        let td = '.' + value;
        $(td).css("color", "#dc143c");
        $(td + ' input:radio').attr('disabled',true);
    });
}

function doPayment() {
    let places = $('input[name="place"]:checked').parent().attr('class').match(/r\d+c\d+/);
    const place = places[0].split('c');
    const row = place[0].slice(1);
    const col = place[1];
    const url = window.location.href.match(/(.+\/)/g) + "payment.html?hall=1&row=" + row + "&col=" + col;
    window.location.assign(url);
}
