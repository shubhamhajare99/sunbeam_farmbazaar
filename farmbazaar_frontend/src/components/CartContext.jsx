import React, { createContext, useContext, useState } from "react";
//createContext: This creates a "context" which is like a container for data.,
//Data Sharing: Context is used to share data (like user info, settings, etc.) across different components without passing props manually.
//useContext: This allows you to access the data in the context from any component.

const CartContext = createContext();

export const useCart = () => {
  return useContext(CartContext);
};

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  const addToCart = (product) => {
    const existingProduct = cart.find((item) => item.id === product.id); //=== -> compares both the type and value of the operands...
    if (existingProduct) {
      setCart(
        cart.map((item) =>
          item.id === product.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        )
      );
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);
    }
  };

  const updateQuantity = (id, action) => {
    setCart((prevCart) => {
      const updatedCart = prevCart.map((item) =>
        item.id === id
          ? {
              ...item,
              quantity:
                action === "increase" ? item.quantity + 1 : item.quantity - 1,
            }
          : item
      );
      return updatedCart.filter((item) => item.quantity > 0);
    });
  };

  return (
    <CartContext.Provider value={{ cart, addToCart, updateQuantity }}>
      {children}
    </CartContext.Provider>
  );
};
