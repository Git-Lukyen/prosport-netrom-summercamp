$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";
    var url = window.location.pathname;
    var id = url.substring(url.lastIndexOf('/') + 1);

    let currentPlayer;

    jQuery.validator.addMethod("lettersonly", function (value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    });

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
            regDate: {
                required: true
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
            regDate: {
                required: "Registration date is required"
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

                if (data.assignedTeam != null) {
                    let link = baseURL + "teams-section/id/" + data.assignedTeam.teamID;
                    $("#player-details").append("<p> Team: " + "<a href=" + link + ">" + data.assignedTeam.teamName + "</a></p>");
                }


                $("#player-details").append("<p> Registration Date: " + data.registrationDate + "</p>" +
                    "<p> Age: " + data.age + " yr</p>" +
                    "<p> Height: " + data.height + " cm</p>" +
                    "<p> Weight: " + data.weight + " kg</p>");

                $('#date_picker').datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', data.registrationDate);
                $("#input-first-name").val(data.firstName);
                $("#input-last-name").val(data.lastName);
                $("#input-age").val(data.age);
                $("#input-height").val(data.height);
                $("#input-weight").val(data.weight);
            }
        });
    }

    updateDetails();

    $("#popup-save-changes").click(function () {
        if (!$("#input-form").valid())
            return;

        let data = {
            firstName: $("#input-first-name").val(),
            lastName: $("#input-last-name").val(),
            registrationDate: $("#date_picker").val(),
            age: $("#input-age").val(),
            height: $("#input-height").val(),
            weight: $("#input-weight").val()
        };

        $.ajax({
            type: "PATCH",
            data: JSON.stringify(data),
            url: baseURL + "players/id/" + id,
            contentType: "application/json",
            success: function (data) {
                updateDetails();
                $("#close-edit").click();
            }
        });
    });

    $("#delete-confirm-btn").click(function () {
        $.ajax({
            type: "DELETE",
            url: baseURL + "players/" + id,
            contentType: "application/json",
            success: function () {
                window.location.replace(baseURL + "players-section")
            }
        });
    });
});