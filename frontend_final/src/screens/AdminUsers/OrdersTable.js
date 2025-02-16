import React, { useState, useEffect } from 'react';
import { getAllOrders } from '../../services/admin.services'; 


const OrdersTable = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    // Fetch orders data when the component mounts
    getAllOrders()
      .then(response => {
        setOrders(response.data); // Assuming the response data is an array of orders
      })
      .catch(error => {
        console.error('Error fetching orders:', error);
      });
  }, []);

  return (
    <div>
      <div style={{ margin: '20px' }}>
      <h1>Orders</h1>
      <hr></hr>
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Customer ID</th>
              <th>Customer Name</th>
              <th>Total Amount</th>
              <th>Delivery Address</th>
              <th>Delivery Status</th>
              <th>Placed Date</th>

            </tr>
          </thead>
          <tbody>
            {orders.map(order => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.customer.id}</td>
                <td>{`${order.customer.firstName} ${order.customer.lastName}`}</td>
                <td>{order.totalAmount}</td>
                <td>{order.deliveryStatus}</td>
                <td>{order.deliveryAddress}</td>
                <td>{order.placedDate}</td>
    
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default OrdersTable;
