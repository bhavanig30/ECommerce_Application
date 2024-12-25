document.querySelector(".continue-btn").addEventListener("click", async () => {
    const emailOrPhone = document.querySelector("#email").value;

    const response = await fetch("http://localhost:8081/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email: emailOrPhone, phone: emailOrPhone }),
    });

    if (response.ok) {
        const token = await response.text();
        localStorage.setItem("jwt", token);
        alert("Login successful!");
        window.location.href = "header.html";
    } else {
        alert("Invalid email or phone number.");
    }
});
