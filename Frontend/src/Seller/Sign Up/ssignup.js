document.querySelector('.verify-btn').addEventListener('click', function () {
    const n = document.getElementById('name').value;
    const e = document.getElementById('email').value;
    const p = document.getElementById('phno').value;
    const pass = document.getElementById('password').value;
    const sn = document.getElementById('storename').value;
    const sa = document.getElementById('storeaddress').value;

    const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@-]).{8,}$/;
    
    if (regex.test(pass)) {
        const userDetails = {
            name: n,
            email: e,
            phonenumber: p,
            password: pass,
            storename: sn,
            Address: sa
        };

        // Send POST request to the backend
        fetch('http://localhost:8081/users/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userDetails)
        })
        .then(response => response.text())
        .then(data => {
            alert(data); // Shows "User registered successfully! or Email or Phone number already registered!"
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
    else{
        alert("Invalid Password");
    }
});