import React, { useState } from "react";
import { toast } from "react-toastify";
import { Link, useNavigate } from "react-router-dom";
// Instead of a regular <a> tag that triggers a full page reload, Link allows you to navigate between routes without reloading the page.
// Returns a function that lets you navigate programmatically.
function Register() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [address, setAddress] = useState("");
  const [role, setRole] = useState("CUSTOMER"); // Default role

  const [loading, setLoading] = useState(false); // Prevent duplicate submissions

  const navigate = useNavigate();

  // Email validation
  const validateEmail = (email) => {
    const re = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return re.test(String(email).toLowerCase());
  };

  // Mobile number validation (10-digit number)
  const validateMobileNo = (mobileNo) => {
    const re = /^[0-9]{10}$/;
    return re.test(mobileNo);
  };

  // Password validation (Minimum 6 characters)
  const validatePassword = (password) => {
    return password.length >= 6;
  };

  const onRegister = async () => {
    if (loading) return; // Prevent multiple clicks
    setLoading(true);

    try {
      if (!email || !password || !firstName || !lastName || !mobileNo || !address) {
        toast.warning("Please fill all fields");
        return;
      } else if (!validateEmail(email)) {
        toast.warning("Please enter a valid email");
        return;
      } else if (!validateMobileNo(mobileNo)) {
        toast.warning("Please enter a valid mobile number (10 digits)");
        return;
      } else if (!validatePassword(password)) {
        toast.warning("Password must be at least 6 characters long");
        return;
      }

      const userData = { email, password, firstName, lastName, mobileNo, address,role: role.toUpperCase() };
     
      // Determine the API endpoint based on the role
    const endpoint = role === 'CUSTOMER'
    ? "http://localhost:8080/customer/addcustomer"
    : "http://localhost:8080/farmer/addfarmer";

      const response = await fetch(endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Registration failed!");
      }

      toast.success("Registration successful! Please login.");
      
      // Clear form fields after successful registration
      setEmail("");
      setPassword("");
      setFirstName("");
      setLastName("");
      setMobileNo("");
      setAddress("");
      setRole("CUSTOMER");

      navigate("/");
    } catch (error) {
      toast.error(error.message || "Something went wrong. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-cover bg-center"
      style={{ backgroundImage: "url('/images/bg1.gif')" }}>

      <div className="p-6 bg-white rounded shadow-md w-96">
        <h2 className="text-2xl font-bold text-center mb-6">Register</h2>

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

        {/* First Name */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">First Name</label>
          <input type="text" className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={firstName} onChange={(e) => setFirstName(e.target.value)} />
        </div>

        {/* Last Name */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">Last Name</label>
          <input type="text" className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={lastName} onChange={(e) => setLastName(e.target.value)} />
        </div>

        {/* Mobile Number */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">Mobile No</label>
          <input type="text" className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={mobileNo} onChange={(e) => setMobileNo(e.target.value)} />
        </div>

        {/* Address */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">Address</label>
          <input type="text" className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={address} onChange={(e) => setAddress(e.target.value)} />
        </div>

        {/* Role Selection */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium mb-2">Register As</label>
          <select className="w-full px-3 py-2 border rounded focus:ring focus:ring-green-500"
            value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="customer">CUSTOMER</option>
            <option value="farmer">FARMER</option>
          </select>
        </div>

        {/* Register Button */}
        <button 
          className="w-full py-2 bg-green-500 text-white font-medium rounded hover:bg-green-600 disabled:opacity-50"
          onClick={onRegister} 
          disabled={loading}>
          {loading ? "Registering..." : "Register"}
        </button>

        {/* Login Link */}
        <div className="mt-4 text-center">
          <Link to="/" className="text-green-500 hover:underline">
            Already have an account? Login here.
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Register;
