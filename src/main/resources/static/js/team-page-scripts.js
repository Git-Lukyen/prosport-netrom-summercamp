$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    $("#add-team-btn").click(function () {
        let name = $("#team-menu #add-input").val();
        let data = {teamName: name};
        data = JSON.stringify(data);

        $.ajax({
            type: "POST",
            data: data,
            url: baseURL + "teams/add/single",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                console.log(data);
                appendTeamsTable(data);
                $("#team-menu input").val("");
            }
        });
    });

    $("#remove-team-btn").click(function () {
        let ID = $("#team-menu #rem-input").val();
        $("#" + ID).remove();

        console.log("deleted");
        $("#team-menu #rem-input").val("");

        $.ajax({
            type: "DELETE",
            url: baseURL + "teams/" + ID,
            contentType: "application/json",
            dataType: 'json',
            data: {},
            success: function () {
            }
        });
    });

    let appendTeamsTable = function (data) {
        tbody.append("<tr id = \"" + data.teamID + "\"><th scope=\"row\">" + data.teamID + "</th>" +
            "<td>" + data.teamName + "</td>" +
            "<td>" + data.registrationDate + "</td>" +
            "<td>" + data.numberOfPlayers + "</td></tr>");
    };

    $.ajax({
        url: baseURL + "teams",
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            $.each(data, function (index) {
                appendTeamsTable(data[index]);
            });
        }
    });

});