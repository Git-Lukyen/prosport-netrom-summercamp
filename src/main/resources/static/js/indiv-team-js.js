$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf('/') + 1);

    console.log(id);
    let currentTeam;

    function updateDetails() {
        $.ajax({
            type: "GET",
            url: baseURL + "teams/id/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                currentTeam = data;
                $("#team-title").empty();
                $("#team-details").empty();

                $("#team-title").append("<h3>" + id + " - " + data.teamName + "</h3>");
                $("#team-details").append("<p> Registration Date: " + data.registrationDate + "</p>" +
                    "<p> Number Of Players: " + data.numberOfPlayers + "</p>");

                $('#team_date_picker').datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', data.registrationDate);
                $("#input-team-name").val(data.teamName);
            }
        });
    }

    updateDetails();

    let lastSavedID;
    let lastSavedName;
    let appendPlayersTable = function (data) {
        $("#team-players-table tbody").append("<tr id = \"" + data.playerID + "\"><th scope=\"row\">" + data.playerID + "</th>" +
            "<td id='name'>" + data.firstName + " " + data.lastName + "</td>" +
            "<td><button class='unassign-player-btn fa fa-close' data-bs-toggle=\"modal\" data-bs-target=\"#unassign-player-popup\">" + "&times;" + "</button></td></tr>");

        $('.unassign-player-btn').click(function () {
            lastSavedID = $(this).parent().parent().attr("ID");
            lastSavedName = $("#" + lastSavedID + " #name").text();
            $("#unassign-player-popup .modal-body").empty();
            $("#unassign-player-popup .modal-body").append("<h3>Remove player " + lastSavedName + " from the team?</h3>");
        });
    };

    $("#popup-confirm-btn").click(function () {
        $.ajax({
            type: "PATCH",
            url: baseURL + "players/id/" + lastSavedID + "/unassign-team",
            contentType: "application/json",
            success: function () {
                updatePlayersTable();
                createPlayerSelect();
            }
        });
    });

    $("#save-changes-btn").click(function () {
        let newName = $('#input-team-name').val();
        let newRegDate = $("#team_date_picker").val();

        let data = {
            teamName: newName,
            registrationDate: newRegDate
        };

        $.ajax({
            type: "PATCH",
            data: JSON.stringify(data),
            url: baseURL + "teams/id/" + id,
            contentType: "application/json",
            success: function (data) {
                updateDetails();
            }
        });
    });

    let updatePlayersTable = function () {
        $.ajax({
            url: baseURL + "players/in-team/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $("#team-players-table").DataTable().clear();
                $("#team-players-table").DataTable().destroy();
                $.each(data, function (index) {
                    appendPlayersTable(data[index]);
                });
                $("#team-players-table").DataTable({
                    paging: false
                });
                updateDetails();
            }
        });
    }

    updatePlayersTable();

    function createPlayerSelect() {

        $.ajax({
            url: baseURL + "players/not-in-team/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $(".easySelect").remove();
                $("#team-main-display").append("<select id=\"add-players-select\" multiple=\"multiple\" style=\"\">");
                $.each(data, function (index) {
                    let name = data[index].firstName + " " + data[index].lastName;
                    $("#add-players-select").append("<option value=" + data[index].playerID + ">" + name + "</option>");
                });

                $("#add-players-select").easySelect({
                    search: true,
                    placeholder: '-- SELECT PLAYERS --',
                    width: '17%',
                    dropdownMaxHeight: '20vh',
                });
            }
        });
    }

    createPlayerSelect();

    $("#add-players-select-btn").click(function () {
        let selectedPlayerIDs = $("#add-players-select").val();
        $.each(selectedPlayerIDs, function (index) {
            $.ajax({
                type: "POST",
                url: baseURL + "players/id/" + selectedPlayerIDs[index] + "/assign-team/" + id,
                contentType: "application/json",
                dataType: 'json',
                success: function () {
                    updatePlayersTable();
                    createPlayerSelect();
                }
            });
        });
    });


});