$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    jQuery.validator.addMethod("lettersonly", function (value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    });

    $("#input-form").validate({
        rules: {
            teamName: {
                required: true,
                minlength: 3,
                lettersonly: true
            }
        },
        messages: {
            teamName: {
                required: "Team name required.",
                minlength: "Name must have at least 3 characters.",
                lettersonly: "Numbers not allowed."
            }
        }
    });

    $("#add-team-btn").click(function () {
        if (!$("#input-form").valid())
            return;

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
    $("#team-menu").keypress(function (e) {
        if (e.which == 13)
            $("#add-team-btn").click();
    });

    let lastSavedID;
    let lastSavedName;
    let appendTeamsTable = function (data) {
        let link = baseURL + "teams-section/id/" + data.teamID;

        tbody.append("<tr id = \"" + data.teamID + "\"><th scope=\"row\">" + data.teamID + "</th>" +
            "<td id='name'><a href=" + link + ">" + data.teamName + "</a></td>" +
            "<td>" + data.registrationDate + "</td>" +
            "<td>" + data.numberOfPlayers + "</td>" +
            "<td><button class='del-team-btn fa fa-close' data-bs-toggle=\"modal\" data-bs-target=\"#del-team-popup\">" + "&times;" + "</button></td></tr>");

        $('.del-team-btn').click(function () {
            lastSavedID = $(this).parent().parent().attr("ID");
            lastSavedName = $("#" + lastSavedID + " #name").text();
            console.log(lastSavedName);
            $(".modal-body").empty();
            $(".modal-body").append("<h3>Delete team " + lastSavedName + "?</h3>");
        });
    };

    $("#popup-confirm-btn").click(function () {
        $.ajax({
            type: "DELETE",
            url: baseURL + "teams/" + lastSavedID,
            contentType: "application/json",
            success: function () {
                updateTeamsTable();
            }
        });
    });

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