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
            }
        });
    });
    $("#player-menu").keypress(function (e) {
        if (e.which == 13)
            $("#add-player-btn").click();
    });

    let lastSavedID;
    let lastSavedName;
    let appendPlayersTable = function (data) {
        tbody.append("<tr id = \"" + data.playerID + "\"><th scope=\"row\">" + data.playerID + "</th>" +
            "<td id='name'>" + data.firstName + " " + data.lastName + "</td>" +
            "<td>" + data.age + "</td>" +
            "<td>" + data.height + "</td>" +
            "<td>" + data.weight + "</td>" +
            "<td>" + data.registrationDate + "</td>" +
            "<td><button class='del-player-btn fa fa-close' data-bs-toggle=\"modal\" data-bs-target=\"#del-player-popup\">" + "&times;" + "</button></td></tr>");

        $('.del-player-btn').click(function () {
            lastSavedID = $(this).parent().parent().attr("ID");
            lastSavedName = $("#" + lastSavedID + " #name").text();
            $(".modal-body").empty();
            $(".modal-body").append("<h3>Delete player " + lastSavedName + "?</h3>");
        });
    };

    $("#popup-confirm-btn").click(function () {
        $.ajax({
            type: "DELETE",
            url: baseURL + "players/" + lastSavedID,
            contentType: "application/json",
            success: function () {
                updatePlayersTable();
            }
        });
    });

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