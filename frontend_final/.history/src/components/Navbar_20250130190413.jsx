import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="flex items-center justify-between px-6 py-4 bg-white shadow-md">
      {/* Left Section - Logo */}
      <div className="text-2xl font-bold">
        <Link to="/">
          FARM<span className="text-green-600">BAZAAR</span>
        </Link>
      </div>

      {/* Center Section - Search Bar */}
      <div className="flex-grow mx-4">
        <input
          type="text"
          placeholder='Search "egg"'
          className="w-full px-4 py-2 border rounded-md border-gray-300 focus:outline-none focus:ring-2 focus:ring-green-500"
        />
      </div>

      {/* Right Section - Buttons */}
      <div className="flex space-x-6">
        {/* <Link to="/login" className="text-green-600 font-medium hover:underline">
          Login
        </Link> */}
        <Link to="/cart">
          <button className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700">
            🛒 My Cart
          </button>
        </Link>
      </div>
    </nav>
  );
}

export default Navbar;
