$(document).ready(function () {
    var saveData = {
        teams: [
            ["Team 1", "Team 2"],
            ["Team 3", null],
            ["Team 4", null],
            ["Team 5", null]
        ],
        results: [
            [
                [[1, 0], [null, null], [null, null], [null, null]],
                [[null, null], [1, 4]],
                [[null, null], [null, null]]
            ]
        ]
    };

    function saveFn(data, userData) {
        console.log(data);
    }

    $(function () {
        var container = $('.demo')
        container.bracket({
            init: saveData,
            save: saveFn,
            toolbar: false
        });
    });
});



