import React, { useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import logo from "../components/logo.png";
import { ArrowRightOnRectangleIcon, ClipboardDocumentListIcon, ShoppingCartIcon } from "@heroicons/react/24/solid";

const FarmerNavbar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const userData = JSON.parse(sessionStorage.getItem("userData"));
    if (!userData || !userData.isLoggedIn || userData.role !== "FARMER") {
      navigate("/");
    }
  }, [navigate]);

  const handleSignOut = () => {
    sessionStorage.removeItem("userData");
    navigate("/");
  };

  return (
    <>
      <nav className="bg-white shadow-md py-4 px-6 flex justify-between items-center">
        {/* Left Section - Logo */}
        <div className="flex items-center space-x-2">
          <Link to="/" className="flex items-center space-x-2">
            <img src={logo} alt="FarmBazaar Logo" className="h-10" />
            <span className="text-2xl font-bold">
              <span className="text-green-700">Farm</span>
              <span className="text-red-600">Bazaar</span>
            </span>
          </Link>
        </div>

        {/* Right Section - Buttons */}
        <div className="flex space-x-6">
         


          {/* Logout Button */}
          <button onClick={handleSignOut} className="flex items-center space-x-2 px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700">
            <ArrowRightOnRectangleIcon className="w-5 h-5" />
            <span>Logout</span>
          </button>
        </div>
      </nav>
    </>
  );
};

export default FarmerNavbar;
