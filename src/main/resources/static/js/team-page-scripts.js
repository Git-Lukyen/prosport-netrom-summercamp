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
                tbody.append("<tr><th scope = \"row\">" + data.teamID + "</th>" +
                    "<td>" + data.teamName + "</td>" +
                    "<td>" + data.registrationDate + "</td>" +
                    "<td>" + data.numberOfPlayers + "</td></tr>");
                $("#team-menu input").val("");
            }
        });
    });

    $("#remove-team-btn").click(function () {
        let ID = $("#team-menu #rem-input").val();
        console.log(ID);
        console.log(tbody.remove(ID));

        $.ajax({
            type: "DELETE",
            url: baseURL + "teams/" + ID,
            contentType: "application/json",
            dataType: 'json',
            data: {},
            success: function () {
                console.log("deleted");
            }
        });
    });

    let update = function () {
        $.ajax({
            url: baseURL + "teams",
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                $.each(data, function (index) {
                    tbody.append("<tr><th scope = \"row\"><data value=" + data[index].teamID + ">" + data[index].teamID + "</data></data></th>" +
                        "<td>" + data[index].teamName + "</td>" +
                        "<td>" + data[index].registrationDate + "</td>" +
                        "<td>" + data[index].numberOfPlayers + "</td></tr>");
                });
            }
        });
        console.log("updated teams table");
    };
    update();

})
;