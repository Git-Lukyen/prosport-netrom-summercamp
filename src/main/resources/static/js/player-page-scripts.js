$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    $("#input-form").validate({
        rules: {
            firstName: {
                required: true,
                minlength: 3,
                lettersonly: true
            },
            lastName: {
                required: true,
                minlength: 3,
                lettersonly: true
            },
            age: {
                required: true,
                min: 5,
                max: 75,
                digits: true
            },
            height: {
                required: true,
                min: 60,
                max: 250,
                digits: true
            },
            weight: {
                required: true,
                min: 20,
                max: 500,
                digits: true
            }
        },
        messages: {
            firstName: {
                required: "First name required.",
                minlength: "First name must have at least 3 characters.",
                lettersonly: "First name cannot contain numbers."
            },
            lastName: {
                required: "Last name required.",
                minlength: "Last name must have at least 3 characters.",
                lettersonly: "Last name cannot include numbers."
            },
            age: {
                required: "Age is required.",
                min: "Minimum age allowed is 5.",
                max: "Maximum age allowed is 75",
                digits: "Age cannot contain letters."
            },
            height: {
                required: "Height is required",
                min: "Minimum height allowed is 60cm.",
                max: "Maximum height allowed is 250cm.",
                digits: "Height cannot contain letters."
            },
            weight: {
                required: "Weight is required.",
                min: "Minimum weight allowed is 20kg.",
                max: "Maximum weight allowed is 500kg.",
                digits: "Weight cannot contain letters."
            }
        }
    });

    $("#add-player-btn").click(function () {
        if (!$("#input-form").valid())
            return;

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
        let link = baseURL + "players-section/id/" + data.playerID;

        tbody.append("<tr id = \"" + data.playerID + "\"><th scope=\"row\">" + data.playerID + "</th>" +
            "<td id='name'><a href=" + link + ">" + data.firstName + " " + data.lastName + "</a></td>" +
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