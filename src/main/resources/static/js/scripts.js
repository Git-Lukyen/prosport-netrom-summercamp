$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";

    $("body").append("<div id=\"side-menu\" class=\"side-menu\" href=\"\">\n" +
        "<a id=\"close-menu-btn\" class=\"closebtn\">&times;</a>\n" +
        "<a href=\"/prosport/\">🏠 &#160;Home</a>\n" +
        "<a href=\"/prosport/comp-section\">🏆 &#160;Competitions</a>\n" +
        "<a href=\"/prosport/teams-section\">👥 Teams</a>\n" +
        "<a href=\"/prosport/players-section\">👤 &#160;Players</a>\n" +
        "<div class=\"break-line\"></div>\n" +
        "</div>");

    $("#open-menu-btn").click(function () {
        $("#side-menu").width("18vw");
        $("body").css("background", "rgba(0, 0, 0, 0.4");
        $("#main-div").css("filter", "brightness(60%)");
    });

    $("#close-menu-btn").click(function () {
        $("#side-menu").width("0vw");
        $("body").css("background", "#f8f4f4");
        $("#main-div").css("filter", "brightness(100%)");
    });
});



