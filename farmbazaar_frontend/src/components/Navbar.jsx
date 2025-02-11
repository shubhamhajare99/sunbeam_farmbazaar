import React, { useEffect } from 'react';
import { Link, NavLink, useLocation, useNavigate } from 'react-router-dom';
import logo from "../components/logo.png";

const NavBar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    if (!userData || !userData.isLoggedIn || userData.role !== 'CUSTOMER') {
      // Redirect to login if not logged in or not a customer
      navigate('/');
    }
  }, [navigate]);

  const handleSignOut = () => {
    // Clear session storage
    sessionStorage.removeItem('userData');
    // Redirect to the login page
    navigate("/");
  };

  const handleOrdersClick = () => {
    navigate('/orders');  // Navigate to /orders
  };

  // Function to determine if the current path matches the given link
  const isActiveLink = (link) => {
    return location.pathname === link;
  };

  return (
    <>
      <nav style={{ backgroundColor: 'transparent', marginBottom: '20px' }} className="navbar navbar-expand-lg bg-light">
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }} className="container-fluid">
          <div>
            <Link to="/" style={{ textDecoration: 'none', color: '#12372A', display: 'flex', alignItems: 'center' }} className="navbar-brand">
            <img
              src={logo} 
              alt="FarmBazaar Logo"
              className="me-2"
              style={{ height: "40px" }}
            />
            <span style={{fontFamily: 'cursive', fontSize: '24px', color:"green", fontWeight: 'bold'}}>Farm</span>
            <span style={{fontFamily: 'cursive', fontSize: '24px', color:"red", fontWeight: 'bold'}}>Bazaar</span>
            </Link>
          </div>
          <div className="navbar-nav">
            <ul style={{ display: 'flex', justifyContent: 'center', marginBottom: '0' }} className="nav">
              <li className="nav-item">
                <NavLink to="/customer-cart" style={{ textDecoration: 'none', color: '#12372A', padding: '0.5rem 1rem' }} activeClassName="active" className="nav-link">Cart</NavLink>
              </li>
              <li className="nav-item">
                <NavLink to="/customer-orders" style={{ textDecoration: 'none', color: '#12372A', padding: '0.5rem 1rem' }} activeClassName="active" className="nav-link">Orders</NavLink>
              </li>
            </ul>
          </div>
          <div className="flex space-x-6">
            <Link to="/customer-cart"> {/* Navigate to the Cart page */}
              <button className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700">
                🛒 My Cart
              </button>
            </Link>
          </div>
          <div>
            <button style={{ marginRight: '1rem', backgroundColor: '#FFFFFF', color: '#1F4E3D' }} type="button" className="btn btn-outline-primary" onClick={handleSignOut}>Logout</button>
          </div>
        </div>
      </nav>
      {/* Add margin or padding below the Navbar */}
      <div style={{ marginBottom: '20px' }}>
        {/* Your page content here */}
      </div>
    </>
  );
};

export default NavBar;












// import React from "react";
// import { Link } from "react-router-dom";

// function Navbar() {
//   return (
//     <nav className="flex items-center justify-between px-6 py-4 bg-white shadow-md">
//       {/* Left Section - Logo */}
//       <div className="text-2xl font-bold">
//         <Link to="/">
//           FARM<span className="text-green-600">BAZAAR</span>
//         </Link>
//       </div>

//       {/* Center Section - Search Bar */}
//       <div className="flex-grow mx-4">
//         <input
//           type="text"
//           placeholder='Search "egg"'
//           className="w-full px-4 py-2 border rounded-md border-gray-300 focus:outline-none focus:ring-2 focus:ring-green-500"
//         />
//       </div>

//       {/* Right Section - Buttons */}
//       <div className="flex space-x-6">
//         <Link to="/customer-cart"> {/* Navigate to the Cart page */}
//           <button className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700">
//             🛒 My Cart
//           </button>
//         </Link>
//       </div>
//     </nav>
//   );
// } 

// export default Navbar;  
