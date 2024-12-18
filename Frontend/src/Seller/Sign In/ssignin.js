document.querySelector('.continue-btn').addEventListener('click', function () {
    const e = document.getElementById('email').value;
    const p = document.getElementById('password').value;

    // Prepare data to send
    const userDetails = {
        email: e,
        phonenumber: e,
        password: p
    };

    // Send POST request to the backend
    fetch('http://localhost:8081/sellers/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDetails)
    })
    .then(response => response.text())
    .then(data => {
        alert(data); // Shows "Login successful!" or "Invalid Login."
        if(data.toLowerCase()=="Login successful!"){
            window.location.replace("../Sell Online/so.html");
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});