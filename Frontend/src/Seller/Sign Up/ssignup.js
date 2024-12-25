document.querySelector('.verify-btn').addEventListener('click', async () => {
    const seller = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        phonenumber: document.getElementById('phno').value,
        password: document.getElementById('password').value,
        storename: document.getElementById('storename').value,
        address: document.getElementById('storeaddress').value
    };

    const response = await fetch('http://localhost:8081/sellers/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(seller)
    });

    if (response.ok) {
        alert('Seller registered successfully');
        window.location.href = '../Sign In/ssignin.html';
    } else {
        alert('Failed to register');
    }
});
