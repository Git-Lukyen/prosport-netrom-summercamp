$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    $("#add-player-btn").click(function () {
        let data =
            {
                firstName: $("#input-first-name").val(),
                lastName: $("#input-last-name").val(),
                age: $("#input-age").val(),
                height: $("#input-height").val(),
                weight: $("#input-weight").val()
            };

        data = JSON.stringify(data);
        $("input").val("");

        $.ajax({
            type: "POST",
            data: data,
            url: baseURL + "players/add/single",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                console.log(data);
                updatePlayersTable();
                $("#player-menu input").val("");
            }
        });
    });

    $("#rem-player-btn").click(function () {
        let ID = $("#player-menu #rem-input").val();
        $("#" + ID).remove();

        console.log("deleted");
        $("#player-menu #rem-input").val("");

        $.ajax({
            type: "DELETE",
            url: baseURL + "players/" + ID,
            contentType: "application/json",
            success: function () {
                updatePlayersTable();
            }
        });
    });

    let appendPlayersTable = function (data) {
        tbody.append("<tr id = \"" + data.playerID + "\"><th scope=\"row\">" + data.playerID + "</th>" +
            "<td>" + data.firstName + " " + data.lastName + "</td>" +
            "<td>" + data.age + "</td>" +
            "<td>" + data.height + "</td>" +
            "<td>" + data.weight + "</td>" +
            "<td>" + data.registrationDate + "</td></tr>");
    };

    let updatePlayersTable = function () {
        $.ajax({
            url: baseURL + "players",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $("#players-table").DataTable().clear();
                $("#players-table").DataTable().destroy();
                $.each(data, function (index) {
                    appendPlayersTable(data[index]);
                });
                $("#players-table").DataTable();
            }
        });
    }

    updatePlayersTable();

});