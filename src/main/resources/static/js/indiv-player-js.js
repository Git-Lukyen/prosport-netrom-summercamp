$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf('/') + 1);

    let currentPlayer;

    function updateDetails() {
        $.ajax({
            type: "GET",
            url: baseURL + "players/id/" + id,
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                currentPlayer = data;
                $("#player-title").empty();
                $("#player-details").empty();

                $("#player-title").append("<h3>" + id + " - " + data.firstName + " " + data.lastName + "</h3>");

                debugger;
                if (data.assignedTeam != null) {
                    let link = baseURL + "teams-section/id/" + data.assignedTeam.teamID;
                    $("#player-details").append("<p> Team: " + "<a href=" + link + ">" + data.assignedTeam.teamName + "</a></p>");
                }


                $("#player-details").append("<p> Registration Date: " + data.registrationDate + "</p>" +
                    "<p> Age: " + data.age + " yr</p>" +
                    "<p> Height: " + data.height + " cm</p>" +
                    "<p> Weight: " + data.weight + " kg</p>");

            }
        });
    }

    updateDetails();
});