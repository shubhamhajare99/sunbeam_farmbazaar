import React, { useState, useEffect } from "react";
import { useCart } from "../components/CartContext";

const ProductList = () => {
  const [products, setProducts] = useState([]); // State to store products
  const [loading, setLoading] = useState(true); // Loading state
  const { cart, addToCart, updateQuantity } = useCart();

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

  const getQuantity = (id) => {
    const product = cart.find((item) => item.id === id);
    return product ? product.quantity : 0;
  };

  return (
    <div className="container mx-auto px-2 py-10">
      <h2 className="text-2xl font-bold text-center mb-6">Our Products</h2>

      <div className="grid grid-cols-3 gap-4 p-0 m-0">
        {products.map((product) => (
          <div key={product.id} className="rounded-lg shadow-md p-2 flex flex-col h-full">
            <img
              src={product.image}
              alt={product.title}
              className="w-full h-48 object-contain mb-4"
            />
            <div className="flex-grow">
              <h6 className="text-lg font-semibold">{product.title}</h6>
            </div>
            <p className="text-gray-700 mt-2 text-left">${product.price}</p>
            <div className="flex items-center justify-center mt-4">
              {getQuantity(product.id) > 0 ? (
                <>
                  <button
                    onClick={() => updateQuantity(product.id, "decrease")}
                    className="px-2 py-1 bg-gray-200 rounded-md"
                  >
                    -
                  </button>
                  <span className="px-4">{getQuantity(product.id)}</span>
                  <button
                    onClick={() => updateQuantity(product.id, "increase")}
                    className="px-2 py-1 bg-gray-200 rounded-md"
                  >
                    +
                  </button>
                </>
              ) : (
                <button
                  onClick={() => addToCart(product)}
                  className="w-full py-2 bg-green-500 text-white font-medium rounded hover:bg-green-600"
                >
                  Add to Cart
                </button>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
