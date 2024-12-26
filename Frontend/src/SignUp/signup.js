document.querySelector(".verify-btn").addEventListener("click", async () => {
    const name = document.querySelector("#name").value;
    const email = document.querySelector("#email").value;
    const phone = document.querySelector("#phno").value;
    const password = document.querySelector("#pass").value;


    const response = await fetch("http://localhost:8081/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, email, phone, password }),
    });

    if (response.ok) {
        alert("Registration successful!");
        window.location.href = "../SignIn/signin.html";
    } else {
        alert("Error registering user.");
    }
});
