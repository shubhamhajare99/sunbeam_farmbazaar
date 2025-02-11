import React, { useState, useEffect } from "react";
import { useCart } from "../components/CartContext";

const productsList = [
  {
    id: 1,
    title: "Irani Apple",
    weight: "(400-500)g",
    price: 163,
    image: "../img/apples.jpg", // Replace with your image path
  },
  {
    id: 2,
    title: "Green Capsicum",
    weight: "(250-280)g",
    price: 27,
    image: "/img/green-capsicum.jpg", // Replace with your image path
  },
  {
    id: 3,
    title: "Cauliflower",
    weight: "1 piece(300-500)g",
    price: 21,
    image: "/img/cauliflower.jpg", // Replace with your image path
  },
  {
    id: 4,
    title: "Dragon Fruit",
    weight: "1 piece(300-500)g",
    price: 100,
    image: "/img/dragon-fruit.jpg", // Replace with your image path
  },
  {
    id: 5,
    title: "Onions",
    weight: "(0.95-1.05)kg",
    price: 45,
    image: "/img/onions.jpg", // Replace with your image path
  },
  {
    id: 6,
    title: "Oranges",
    weight: "250 g",
    price: 45,
    image: "/img/oranges.jpg", // Replace with your image path
  },
  {
    id: 7,
    title: "Potatoes",
    weight: "1 kg",
    price: 45,
    image: "../img/potatoes.jpg", // Replace with your image path
  },
  {
    id: 8,
    title: "Premium Rice",
    weight: "1 kg",
    price: 67,
    image: "../img/rice.jpg", // Replace with your image path
  },
  {
    id: 9,
    title: "Tomatoes",
    weight: "500g",
    price: 23,
    image: "/img/tomatoes.jpg", // Replace with your image path
  },
  {
    id: 10,
    title: "Fortune Wheat",
    weight: "10kg",
    price: 472,
    image: "../img/wheat.jpg", // Replace with your image path
  },
];


const ProductList = () => {
  const [loading, setLoading] = useState(false); // Simulate loading state
  const { cart, addToCart, updateQuantity } = useCart();

  const getQuantity = (id) => {
    const product = cart.find((item) => item.id === id);
    return product ? product.quantity : 0;
  };

  if (loading) return <div className="text-center mt-10 text-lg">Loading Products...</div>;

  return (
    <div className="container mx-auto px-2 py-10">
      <h2 className="text-2xl font-bold text-center mb-6">Our Products</h2>

      <div className="grid grid-cols-3 gap-4 p-0 m-0">
        {productsList.map((product) => (
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
