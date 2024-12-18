const API_BASE_URL = "http://localhost:8080/products";

// Function to fetch and display products for the seller
async function fetchSellerProducts() {
    try {
        const response = await fetch('${API_BASE_URL}/my-products', {
            method: "GET",
            credentials: "include", // Include session cookies
        });

        if (response.ok) {
            const products = await response.json();
            const container = document.getElementById("sellerProducts");
            container.innerHTML = "";

            products.forEach((product) => {
                const productCard = `
                    <div class="product-card">
                        <img src="${product.imageUrl}" alt="${product.name}" />
                        <h3>${product.name}</h3>
                        <p>Price: $${product.price}</p>
                        <p>Size: ${product.size}</p>
                        <p>Color: ${product.color}</p>
                        <p>Category: ${product.category}</p>                        
                        <p>Description: ${product.description}</p>
                        <button onclick="deleteProduct(${product.id})">Delete</button>
                        <button onclick="showUpdateForm(${product.imageUrl, product.name, product.price, product.size, 
                            product.color, product.category, product.description, product.id})">Update</button>
                    </div>
                `;
                container.innerHTML += productCard;
            });
        } else {
            alert("Failed to fetch products. Please log in as a seller.");
        }
    } catch (error) {
        console.error("Error fetching seller products:", error);
    }
}

// Function to add a new product
async function addProduct() {
    const productData = {
        name: document.getElementById("name").value,
        size: document.getElementById("size").value,
        colour: document.getElementById("colour").value,
        category: document.getElementById("category").value,
        price: document.getElementById("price").value,
        image: document.getElementById("image").value,
        sellerid: document.getElementById("sellerid").value,
        description: document.getElementById("description").value,
    };

    try {
        const response = await fetch('${API_BASE_URL}/add', {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(productData),
        });

        const result = await response.text();
        alert(result);
        fetchSellerProducts(); // Refresh product list
    } catch (error) {
        console.error("Error adding product:", error);
    }
}

// Function to delete a product
async function deleteProduct(productId) {
    try {
        const response = await fetch('${API_BASE_URL}/delete/${productId}', {
            method: "DELETE",
        });

        const result = await response.text();
        alert(result);
        fetchSellerProducts(); // Refresh product list
    } catch (error) {
        console.error("Error deleting product:", error);
    }
}

// Function to update a product
async function updateProduct(productId) {
    const updatedData = {
        name: document.getElementById("name").value,
        size: document.getElementById("size").value,
        colour: document.getElementById("colour").value,
        category: document.getElementById("category").value,
        price: document.getElementById("price").value,
        image: document.getElementById("image").value,
        sellerid: document.getElementById("sellerid").value,
        description: document.getElementById("description").value,
    };

    try {
        const response = await fetch('${API_BASE_URL}/update/${productId}', {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedData),
        });

        const result = await response.text();
        alert(result);
        fetchSellerProducts(); // Refresh product list
    } catch (error) {
        console.error("Error updating product:", error);
    }
}

// Function to show the update form pre-filled with product data
function showUpdateForm(image, name, price, size, colour, category, description, id) {
    document.getElementById("image").value = image || "";
    document.getElementById("name").value = name || "";
    document.getElementById("price").value = price || "";
    document.getElementById("size").value = size || "";
    document.getElementById("colour").value = colour || "";
    document.getElementById("category").value = category || "";
    document.getElementById("description").value = description || "";
    document.getElementById("updateForm").style.display = "block";
    document.getElementById("updateSubmit").onclick = () => updateProduct(id);
}

// Call the function on page load
fetchSellerProducts();