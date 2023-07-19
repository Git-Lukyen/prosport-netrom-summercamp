$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf('/') + 1);
    let nrTeams;

    console.log(id);
    let currentComp;

    jQuery.validator.addMethod("lettersonly", function (value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    });

    $("#input-form").validate({
        rules: {
            compName: {
                required: true,
                minlength: 3,
                lettersonly: true
            },
            address: {
                required: true,
                minlength: 10,
            },
            compStart: {
                required: true,
            }
        },
        messages: {
            compName: {
                required: "Name is required.",
                minlength: "Name must have at least 3 characters.",
                lettersonly: "Name cannot include numbers"
            },
            address: {
                required: "Address is required.",
                minlength: "Address must have at least 10 characters.",
            },
            compStart: {
                required: "Start date is required.",
            }
        }
    });

    function updateDetails() {
        $.ajax({
            type: "GET",
            url: baseURL + "comps/id/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                currentComp = data;
                $("#comp-title").empty();
                $("#comp-details").empty();

                nrTeams = data.numberOfTeams;

                if (data.numberOfTeams >= 16)
                    $("#full-comp-alert").css("display", "block");
                else
                    $("#full-comp-alert").css("display", "none");

                $("#comp-title").append("<h3>" + id + " - " + data.compName + "</h3>");
                $("#comp-details").append("<p> Location: " + data.compLocation + "</p>" +
                    "<p> Start Date: " + data.compStart + "</p>" +
                    "<p> Number Of Teams: " + data.numberOfTeams + "</p>");

                $('#date_picker').datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', data.compStart);
                $("#input-comp-name").val(data.compName);
                $("#input-location").val(data.compLocation);
            }
        });
    }

    updateDetails();

    let lastSavedID;
    let lastSavedName;
    let appendTeamsTable = function (data) {
        let link = baseURL + "teams-section/id/" + data.teamID;

        $("#comp-teams-table tbody").append("<tr id = \"" + data.teamID + "\"><th scope=\"row\">" + data.assignDate + "</th>" +
            "<td id='name'><a href=" + link + ">" + data.teamName + "</a></td>" +
            "<td><button class='unassign-team-btn fa fa-close' data-bs-toggle=\"modal\" data-bs-target=\"#unassign-team-popup\">" + "&times;" + "</button></td></tr>");

        $('.unassign-team-btn').click(function () {
            lastSavedID = $(this).parent().parent().attr("ID");
            lastSavedName = $("#" + lastSavedID + " #name").text();
            $("#unassign-team-popup .modal-body").empty();
            $("#unassign-team-popup .modal-body").append("<h3>Remove team " + lastSavedName + " from the competition?</h3>");
        });
    };

    $("#delete-confirm-btn").click(function () {
        $.ajax({
            type: "DELETE",
            url: baseURL + "comps/id/" + id,
            contentType: "application/json",
            success: function () {
                window.location.replace(baseURL + "comp-section")
            }
        });
    });

    $("#popup-confirm-btn").click(function () {
        $.ajax({
            type: "PATCH",
            url: baseURL + "teams/id/" + lastSavedID + "/unassign-comp/" + id,
            contentType: "application/json",
            success: function () {
                updateTeamsTable();
                createTeamsSelect();
            }
        });
    });

    $("#popup-save-changes").click(function () {
        if (!$("#input-form").valid())
            return;

        let data = {
            compName: $("#input-comp-name").val(),
            compStart: $("#date_picker").val(),
            compLocation: $("#input-location").val()
        };

        $.ajax({
            type: "PATCH",
            data: JSON.stringify(data),
            url: baseURL + "comps/id/" + id,
            contentType: "application/json",
            success: function (data) {
                updateDetails();
                $("#close-edit").click();
            }
        });

    });

    let updateTeamsTable = function () {
        $.ajax({
            url: baseURL + "teams/in-comp/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $("#comp-teams-table").DataTable().clear();
                $("#comp-teams-table").DataTable().destroy();
                $.each(data, function (index) {
                    appendTeamsTable(data[index]);
                });
                $("#comp-teams-table").DataTable({
                    paging: false
                });
                updateDetails();
            }
        });
    }

    updateTeamsTable();

    function createTeamsSelect() {

        $.ajax({
            url: baseURL + "teams/not-in-comp/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $(".easySelect").remove();
                $("#comp-main-display").append("<select id=\"add-teams-select\" multiple=\"multiple\" style=\"\">");
                $.each(data, function (index) {
                    let name = data[index].teamName;
                    $("#add-teams-select").append("<option value=" + data[index].teamID + ">" + name + "</option>");
                });

                $("#add-teams-select").easySelect({
                    search: true,
                    placeholder: '-- SELECT TEAMS --',
                    width: '17%',
                    dropdownMaxHeight: '20vh',
                });
            }
        });
    }

    createTeamsSelect();

    $("#add-teams-select-btn").click(function () {
        let selectedTeamsIDs = $("#add-teams-select").val();
        if (nrTeams >= 16)
            return;
        nrTeams++;

        $.ajax({
            type: "POST",
            data: JSON.stringify(selectedTeamsIDs),
            url: baseURL + "teams/assign-comp/" + id,
            contentType: "application/json",
            success: function () {
                updateTeamsTable();
                createTeamsSelect();
            }
        });
    });
});