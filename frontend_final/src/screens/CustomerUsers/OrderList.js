import React, { useState, useEffect } from 'react';
import { Badge, Card, Row, Col } from 'react-bootstrap';
import { getOrdersByCustomerId } from '../../services/customer.services';
import NavBarCustomer from '../../components/Navbar';
import Footer from '../../components/Footer';

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  const [userData, setUserData] = useState(null);

  const getStatusBadgeVariant = (status) => {
    switch (status) {
      case 'Confirmed Order':
        return 'secondary';
      case 'Delivered':
        return 'success';
      default:
        return 'secondary';
    }
  };

  useEffect(() => {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    setUserData(userData);

    if (userData) {
      const fetchOrders = async () => {
        try {
          const response = await getOrdersByCustomerId(userData.id);
          console.log('Fetched orders:', response.data);
          setOrders(response.data);
        } catch (error) {
          console.error('Error fetching orders:', error);
        }
      };

      fetchOrders();
    }
  }, []);

  return (
    <>
      <NavBarCustomer />
      <Row xs={1} md={5} className="g-4">
        {orders.map((order) => (
          <Col key={order.id}>
            <Card
              className={`text-white bg-${getStatusBadgeVariant(order.deliveryStatus)} mb-3`}
              style={{ maxWidth: '18rem' }}
            >
              <Card.Header>{order.deliveryStatus}</Card.Header>
              <Card.Body>
                <Card.Title>Order #{order.id}</Card.Title>
                <Card.Text>
                  <strong>Total Amount:</strong> ${order.totalAmount}
                  <br />
                  <strong>Delivery Address:</strong> {order.deliveryAddress}
                  <br />
                  <strong>Placed Date:</strong>{' '}
                  {order.placedDate ? new Date(order.placedDate).toLocaleDateString() : 'Not specified'}
                  <br />
                  <strong>Delivery Status:</strong>{' '}
                  <Badge bg={getStatusBadgeVariant(order.deliveryStatus)}>{order.deliveryStatus}</Badge>
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
      <Footer />
    </>
  );
};

export default OrderList;
