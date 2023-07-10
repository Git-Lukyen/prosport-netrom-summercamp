$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    $.ajax({
        url: baseURL + "teams",
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            $.each(data, function (index) {
                tbody.append("<tr><th scope = \"row\">" + data[index].teamID + "</th>" +
                    "<td>" + data[index].teamName + "</td>" +
                    "<td>" + data[index].registrationDate + "</td>" +
                    "<td>" + data[index].numberOfPlayers + "</td></tr>");
            });
        }
    });

});