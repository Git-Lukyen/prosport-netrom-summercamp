$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    let tbody = $("tbody");

    $(function () {
        $("#date_picker").datepicker(
            {
                minDate: "+0d",
                dateFormat: "yy-mm-dd"
            }
        );
    });

    $("#add-comp-btn").click(function () {
        let data =
            {
                compName: $("#input-comp-name").val(),
                compLocation: $("#input-location").val(),
                compStart: $("#date_picker").val()
            };
        console.log($("#date_picker").val());

        data = JSON.stringify(data);
        $("input").val("");

        $.ajax({
            type: "POST",
            data: data,
            url: baseURL + "comps/add/single",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                console.log(data);
                updateCompetitionTable();
            }
        });
    });

    $("#comp-menu").keypress(function (e) {
        if (e.which == 13)
            $("#add-comp-btn").click();
    });

    let lastSavedID;
    let lastSavedName;
    let appendCompTable = function (data) {
        tbody.append("<tr id = \"" + data.compID + "\"><th scope=\"row\">" + data.compID + "</th>" +
            "<td id='name'>" + data.compName + "</td>" +
            "<td>" + data.compLocation + "</td>" +
            "<td>" + data.compStart + "</td>" +
            "<td>" + data.numberOfTeams + "</td>" +
            "<td><button class='del-comp-btn fa fa-close' data-bs-toggle=\"modal\" data-bs-target=\"#del-comp-popup\">" + "&times;" + "</button></td></tr>");

        $('.del-comp-btn').click(function () {
            lastSavedID = $(this).parent().parent().attr("ID");
            lastSavedName = $("#" + lastSavedID + " #name").text();
            $(".modal-body").empty();
            $(".modal-body").append("<h3>Delete competition " + lastSavedName + "?</h3>");
        });
    };

    $("#popup-confirm-btn").click(function () {
        $.ajax({
            type: "DELETE",
            url: baseURL + "comps/id/" + lastSavedID,
            contentType: "application/json",
            success: function () {
                updateCompetitionTable();
            }
        });
    });

    let updateCompetitionTable = function () {
        $.ajax({
            url: baseURL + "comps",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $("#comp-table").DataTable().clear();
                $("#comp-table").DataTable().destroy();
                $.each(data, function (index) {
                    appendCompTable(data[index]);
                });
                $("#comp-table").DataTable();

            }
        });
    }

    updateCompetitionTable();

});