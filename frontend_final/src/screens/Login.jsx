import React, { useState } from 'react';
import { toast } from 'react-toastify';
import { Link, useNavigate } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('CUSTOMER'); 

  const navigate = useNavigate();
  
  const onLogin = async () => {
    if (email.length === 0) {
      toast.warning('Please enter email');
    } else if (password.length === 0) {
      toast.warning('Please enter password');
    } else {
      // Admin credentials check
      const adminEmail = 'admin@gmail.com';
      const adminPassword = 'admin123';
  
      if (email === adminEmail && password === adminPassword) {
        const adminData = {
          id: "admin",
          username: "Admin",
          role: "ADMIN",
          isLoggedIn: true
        };
        sessionStorage.setItem('userData', JSON.stringify(adminData));
        sessionStorage.setItem('token', 'admin-token');
  
        toast.success('Welcome, Admin!');
        navigate('/admin-dashboard');  
      } else {
        try {
          // Fetch user role from backend
          const response = await fetch("http://localhost:8080/farmbazaar/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
          });
  
          if (response.ok) {
            const data = await response.json();
  
            // Store necessary user data in session storage
            const userData = {
              id: data.id,
              email: data.email,
              firstName: data.firstName,
              lastName: data.lastName,
              mobileNo: data.mobileNo,
              address: data.address,
              role: data.role,
              active: data.active,
              isLoggedIn: true, // Add isLoggedIn flag
            };
            sessionStorage.setItem('userData', JSON.stringify(userData));
            sessionStorage.setItem('token', data.token);
  
            toast.success(`Welcome, ${data.role.charAt(0).toUpperCase() + data.role.slice(1)}!`);
  
            // Redirect user based on role
            if (data.role === "CUSTOMER") {
              navigate('/customer-dashboard');  
            } else if (data.role === "FARMER") {
              navigate('/farmer-dashboard');  
            } else {
              toast.warning("Invalid role detected!");
            }
          } else {
            toast.error("Invalid credentials. Please try again.");
          }
        } catch (error) {
          console.error("Login Error:", error);
          toast.error("Something went wrong. Please try again.");
        }
      }
    }
  };
  

  /*
  const onLogin = async () => {
    if (email.length === 0) {
      toast.warning('Please enter email');
    } else if (password.length === 0) {
      toast.warning('Please enter password');
    } else {
      // Admin credentials check
      const adminEmail = 'admin@gmail.com';
      const adminPassword = 'admin123';
  
      if (email === adminEmail && password === adminPassword) {
        sessionStorage.setItem('token', 'admin-token');
        sessionStorage.setItem('role', 'admin');
  
        toast.success('Welcome, Admin!');
        navigate('/admin-dashboard');  
      } else {
        try {
          // Fetch user role from backend
          const response = await fetch("http://localhost:8080/farmbazaar/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
          });
  
          if (response.ok) {
            const data = await response.json();
            sessionStorage.setItem('token', data.token);
            sessionStorage.setItem('role', data.role); // Store role from backend
  
            toast.success(`Welcome, ${data.role.charAt(0).toUpperCase() + data.role.slice(1)}!`);
  
            if (data.role === "CUSTOMER") {
              navigate('/customer-dashboard');  // Customer dashboard
            } else if (data.role === "FARMER") {
              navigate('/farmer-dashboard');  // Farmer dashboard
            } else {
              toast.warning("Invalid role detected!");
            }
          } else {
            toast.error("Invalid credentials. Please try again.");
          }
        } catch (error) {
          console.error("Login Error:", error);
          toast.error("Something went wrong. Please try again.");
        }
      }
    }
  };*/
  
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-cover bg-center"
      style={{ backgroundImage: "url('/images/bg1.gif')" }}>
      
      <div className="p-6 bg-white rounded shadow-md w-96">
        <h2 className="text-2xl font-bold text-center mb-6">Login</h2>

        {/* Email Input */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">Email</label>
          <input type="email" className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>

        {/* Password Input */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">Password</label>
          <input type="password" className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>

     
        {/* Login Button */}
        <button className="w-full py-2 bg-green-500 text-white font-medium rounded hover:bg-green-600"
          onClick={onLogin}>
          Login
        </button>

        {/* Register Link */}
        <div className="mt-4 text-center">
          <Link to="/register" className="text-green-500 hover:underline">
            Don’t have an account? Register here.
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Login;
