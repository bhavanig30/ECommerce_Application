document.querySelector('.continue-btn').addEventListener('click', async () => {
    const loginDetails = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    const response = await fetch("http://localhost:8081/sellers/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginDetails)
    });

    if (response.ok) {
        const seller = await response.json();
        localStorage.setItem('sellerId', seller.id);
        alert('Login successful');
        window.location.href = '/Seller/Sell Online/so.html';  // Use relative path

    } else {
        alert('Invalid email or password');
    }
});
