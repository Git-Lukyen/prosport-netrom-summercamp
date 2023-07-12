$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    $("#add-team-btn").click(function () {
        let name = $("#team-menu #add-input").val();
        let data = {teamName: name};
        data = JSON.stringify(data);

        $("#team-menu #add-input").val("");

        $.ajax({
            type: "POST",
            data: data,
            url: baseURL + "teams/add/single",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                console.log(data);
                updateTeamsTable();
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
            success: function () {
                updateTeamsTable();
            }
        });
    });

    let appendTeamsTable = function (data) {
        tbody.append("<tr id = \"" + data.teamID + "\"><th scope=\"row\">" + data.teamID + "</th>" +
            "<td>" + data.teamName + "</td>" +
            "<td>" + data.registrationDate + "</td>" +
            "<td>" + data.numberOfPlayers + "</td></tr>");
    };

    let updateTeamsTable = function () {
        $.ajax({
            url: baseURL + "teams",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $("#teams-table").DataTable().clear();
                $("#teams-table").DataTable().destroy();
                $.each(data, function (index) {
                    appendTeamsTable(data[index]);
                });
                $("#teams-table").DataTable();
            }
        });
    }

    updateTeamsTable();

});