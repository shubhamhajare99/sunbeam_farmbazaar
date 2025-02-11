import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './screens/Login'; 
import Home from './screens/Home';  
import Layout from './components/Layout';
import FarmerDashboard from './screens/FarmerDashboard';
import AdminDashboard from './screens/AdminDashboard';
import Register from './screens/Register';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <>
      <ToastContainer />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/home"
          element={
            <Layout>
              <Home />
            </Layout>
          }
        />
        <Route path="/farmer-dashboard" element={<FarmerDashboard />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
      </Routes>
    </>
  );
}

export default App;
