import React, { useState, useEffect } from 'react';
import { getCartItems, checkoutOrder } from '../../services/customer.services';
import Payment from './Payment';
import './cart.css'; 
import NavBarCustomer from '../../components/Navbar';
import Footer from '../../components/Footer';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [totalAmount, setTotalAmount] = useState(0);
  const [deliveryAddress, setDeliveryAddress] = useState('');
  const [userData, setUserData] = useState(null);
  const [showPayment, setShowPayment] = useState(false);

  useEffect(() => {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    setUserData(userData);

    if (userData) {
      // Pre-populate delivery address from session
      setDeliveryAddress(userData.address);

      getCartItems(userData.id)
        .then(response => {
          setCartItems(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    }
  }, []);

  useEffect(() => {
    const totalAmount = cartItems.reduce((acc, item) => acc + (item.price * item.quantity), 0);
    setTotalAmount(totalAmount);
  }, [cartItems]);

  const handleDeliveryAddressChange = (event) => {
    setDeliveryAddress(event.target.value);
  };

  const handleCheckout = () => {
    // Call the checkout service with customer ID and checkout request data
    if (userData) {
      const checkoutRequest = {
        //deliveryDate: deliveryDate,
        deliveryAddress: deliveryAddress || userData.address
      };

      checkoutOrder(userData.id, checkoutRequest)
        .then(response => {
          alert('Order placed successfully!');
          // Redirect to customer dashboard
          window.location.href = '/customer-dashboard'; // Update with your actual customer dashboard route
        })
        .catch(error => {
          console.error(error);
        });
    }
  };

  return (
    <>
    <NavBarCustomer />
    <div className="container">
      <div className="row">
        <div className="col-md-8">
          <h4>Your Cart</h4>
          {cartItems.map((item, index) => (
            <div className="card mb-3" key={index}>
              <div className="row g-0">
                <div className="col-md-4">
                  <div className="product-image-container">
                    {item.product.imageBase64 && (
                      <img
                        src={`data:image/jpeg;base64,${item.product.imageBase64}`}
                        className="img-fluid"
                        alt={item.product.name}
                      />
                    )}
                  </div>
                </div>
                <div className="col-md-8">
                  <div className="card-body">
                    <h5 className="card-title">{item.product.name}</h5>
                    <p className="card-text">Price: ${item.product.price}</p>
                    <p className="card-text">Quantity: {item.quantity}</p>
                  </div>
                </div>
              </div>
            </div>
          ))}
          <h5>Total: ${totalAmount}</h5>
        </div>
        <div className="col-md-4">
          <h4>Billing Details</h4>
          <form>
          <div className="mb-3">
              <label htmlFor="grandTotal" className="form-label">Grand Total:</label>
              <input
                type="text"
                id="grandTotal"
                className="form-control"
                value={totalAmount}

              />
            </div>
            <div className="mb-3">
              <label htmlFor="deliveryAddress" className="form-label">Delivery Address:</label>
              <input
                type="text"
                id="deliveryAddress"
                className="form-control"
                value={deliveryAddress}
                onChange={handleDeliveryAddressChange}
              />
            </div>
            <button type="button" className="btn btn-primary" onClick={() => setShowPayment(true)}>Go to Checkout</button>
          </form>
          {showPayment && <Payment totalAmount={totalAmount} onPayAmount={handleCheckout} />}
        </div>
      </div>
    </div>
   <Footer />
    </>
  );
};

export default Cart;
