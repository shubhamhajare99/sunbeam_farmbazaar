import React from 'react';
import { Routes, Route } from 'react-router-dom';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Product from './screens/AdminUsers/Product';
import Login from './screens/Login';  
import Layout from './components/Layout';
import FarmerDashboard from './screens/FarmerDashboard';
import AdminDashboard from './screens/AdminDashboard';
import Register from './screens/Register';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {CartProvider} from "./components/CartContext";
import Category from './screens/AdminUsers/Category';
import Customer from './screens/CustomerUsers/Customer';
import Cart from './screens/CustomerUsers/Cart';
import Payment from './screens/CustomerUsers/Payment';
import OrderList from './screens/CustomerUsers/OrderList';
import EditProfile from './screens/CustomerUsers/EditProfile';
import OrdersTable from './screens/AdminUsers/OrdersTable';

function App() {
  return (
    <CartProvider>
      <ToastContainer />
      <Routes>
        <Route path="/admin/products" element={<Product/>} />
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/admin/categories" element={<Category/>} />
        <Route path="/farmer-dashboard" element={<FarmerDashboard />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
        <Route path="/customer-dashboard" element={<Customer/>} />
        <Route path="/customer-cart" element={<Cart/>} />
        <Route path="/payment/:totalAmount" element={<Payment />} />
        <Route path="/customer-orders" element={<OrderList/>} />
        <Route path="/EditProfile" element={<EditProfile/>} />
        <Route path="/admin/orders" element={<OrdersTable/>} />
      </Routes>
    </CartProvider>
  );
}

export default App;
