$(document).ready(function () {
    let baseURL = "http://localhost:8080/prosport/";

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



