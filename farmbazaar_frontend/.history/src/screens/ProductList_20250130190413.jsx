import React, { useState, useEffect } from "react";

const ProductList = () => {
  const [products, setProducts] = useState([]); // State to store products
  const [loading, setLoading] = useState(true); // Loading state

  useEffect(() => {
    // Fetch products from API
    fetch("https://fakestoreapi.com/products")
      .then((res) => res.json())
      .then((data) => {
        setProducts(data); // Store data in state
        setLoading(false); // Stop loading
      })
      .catch((error) => console.error("Error fetching products:", error));
  }, []);

  if (loading) return <div className="text-center mt-10 text-lg">Loading Products...</div>;

  return (
    <div className="container mx-auto px-4 py-10">
      <h2 className="text-2xl font-bold text-center mb-6">Our Products</h2>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {products.map((product) => (
          <div key={product.id} className="bg-white rounded-lg shadow-md p-4">
            <img src={product.image} alt={product.title} className="w-full h-48 object-contain mb-4" />
            <h3 className="text-lg font-semibold">{product.title}</h3>
            <p className="text-gray-700 mt-2">${product.price}</p>
            <button className="mt-4 w-full py-2 bg-green-500 text-white font-medium rounded hover:bg-green-600">
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
