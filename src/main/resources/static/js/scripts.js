$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";

    $("#teams-button").click(function(){
        $.ajax({
            type: "GET",
            url: baseURL + "teams",
            dataType: "json",
            success: function (data, result) {
                console.log("got-here");

                let table = $("<table><tr><th>Teams List</th></tr>");

                $.each(data, function (index) {
                    table.append("<p>Name: " + data[index].teamName + "</p>");
                });

                $('#teams-table').html(table);
            }
        });
    });
});


