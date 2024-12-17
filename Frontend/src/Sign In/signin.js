document.querySelector('.continue-btn').addEventListener('click', function () {
    const input = document.getElementById('email').value;

    // Prepare data to send
    const userDetails = {
        email: input,
        phonenumber: input
    };

    // Send POST request to the backend
    fetch('http://localhost:8081/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDetails)
    })
    .then(response => response.text())
    .then(data => {
        alert(data); // Shows "Login successful!" or "Invalid email or phone number."
    })
    .catch(error => {
        console.error('Error:', error);
    });
});