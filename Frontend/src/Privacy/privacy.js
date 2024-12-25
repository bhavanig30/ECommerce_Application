document.addEventListener("DOMContentLoaded", function () {
    fetch("../Home/header.html")
        .then(response => response.text())
        .then(data => {
            document.getElementById('header').innerHTML = data;
        });
});
