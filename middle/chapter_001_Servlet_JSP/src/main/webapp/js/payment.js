$(document).ready(() => {

});

function pay() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8081/chapter_001_Servlet_JSP_war_exploded/payment.html',
    }).done(data => document.write(data))
        .fail(err => console.log(err));
}
