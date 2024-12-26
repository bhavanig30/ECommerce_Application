// Adding event listener for Add Product button
document.querySelector('.add-product-btn').addEventListener('click', async () => {
    // Collect product details from the form
    const product = {
        name: document.getElementById('name').value.trim(),
        size: document.getElementById('size').value.trim(),
        colour: document.getElementById('colour').value.trim(),
        category: document.getElementById('category').value.trim(),
        price: parseFloat(document.getElementById('price').value.trim()),
        image: document.getElementById('image').value.trim(),
        description: document.getElementById('description').value.trim(),
    };

    // Validation for form inputs
    if (!product.name || !product.size || !product.colour || !product.category || isNaN(product.price) || !product.image || !product.description) {
        alert('Please fill in all the fields correctly.');
        return;
    }

    // Fetch sellerId from localStorage (assuming it's stored after login)
    const sellerId = localStorage.getItem('sellerId');
    if (!sellerId) {
        alert('Seller is not logged in. Please log in again.');
        window.location.href = '/signin.html'; // Redirect to login page if sellerId is not found
        return;
    }

    try {
        // Call backend API to add the product
        const response = await fetch(`http://localhost:8081/products/add/${sellerId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(product),
        });

        if (response.ok) {
            alert('Product added successfully!');
            window.location.href = '/so.html'; // Redirect to the product list page
        } else {
            const errorData = await response.json();
            alert(`Failed to add product: ${errorData.message || 'Unknown error'}`);
        }
    } catch (error) {
        console.error('Error adding product:', error);
        alert('An error occurred while adding the product. Please try again.');
    }
});

// Function to load and display products for the seller
async function loadSellerProducts() {
    const sellerId = localStorage.getItem('sellerId');
    if (!sellerId) {
        alert('Seller is not logged in. Please log in again.');
        window.location.href = '/signin.html';
        return;
    }

    try {
        // Fetch products for the logged-in seller
        const response = await fetch(`/api/products/${sellerId}`);
        if (response.ok) {
            const products = await response.json();

            // Populate products in the HTML
            const productContainer = document.querySelector('.product-container');
            productContainer.innerHTML = ''; // Clear previous content

            if (products.length === 0) {
                productContainer.innerHTML = '<p>No products added yet.</p>';
            } else {
                products.forEach(product => {
                    const productCard = `
                        <div class="product">
                            <img src="${product.image}" alt="${product.name}">
                            <h3>${product.name}</h3>
                            <p>Size: ${product.size}</p>
                            <p>Colour: ${product.colour}</p>
                            <p>Category: ${product.category}</p>
                            <p>Price: ₹${product.price}</p>
                            <p>${product.description}</p>
                        </div>
                    `;
                    productContainer.innerHTML += productCard;
                });
            }
        } else {
            alert('Failed to load products. Please try again later.');
        }
    } catch (error) {
        console.error('Error loading products:', error);
        alert('An error occurred while loading products.');
    }
}

// Call the function to load products on page load
loadSellerProducts();
