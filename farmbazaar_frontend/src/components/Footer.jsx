//npm install --save @fortawesome/fontawesome-free
//import '@fortawesome/fontawesome-free/css/all.min.css';


import React from "react";

function Footer() {
  return (
    <footer className="bg-gray-100 text-black py-10">
      <div className="container mx-auto px-6">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {/* About Us Section */}
          <div>
            <h4 className="text-lg font-semibold mb-4">About Us</h4>
            <p className="text-gray-600">
              Welcome to Farm Bazaar, your trusted destination for fresh vegetables,
              fruits, and grains. We provide top-quality
              products with a seamless shopping
              experience.
            </p>
          </div>

          {/* Quick Links Section */}
          <div>
            {/* <h4 className="text-lg font-semibold mb-4">Quick Links</h4>
            <ul className="space-y-2"> */}
              {/* <li>
                <NavLink to="/home" className="text-gray-700 hover:text-green-600">
                  Home
                </NavLink>
              </li>
              <li>
                <NavLink to="/about" className="text-gray-700 hover:text-green-600">
                  About
                </NavLink>
              </li> */}
            {/* </ul> */}
          </div>

          {/* Contact Us Section */}
          <div>
            <h4 className="text-lg font-semibold mb-4">Contact Us</h4>
            <ul className="text-gray-600 space-y-2">
              <li>Sunbeam , Hinjewadi</li>
              <li>Pune, 411027</li>
              <li>Email: farmbazaar@gmail.com</li>
              <li>Phone: 7517658239</li>
            </ul>
          </div>
        </div>

        {/* Social Media Icons Section */}
        <div className="flex justify-center space-x-4 mt-6">
          <a
            href="#!"
            className="w-10 h-10 flex items-center justify-center rounded-full bg-blue-600 text-white hover:bg-blue-700 transition"
          >
            <i className="fa-brands fa-facebook-f"></i>
          </a>
          <a
            href="#!"
            className="w-10 h-10 flex items-center justify-center rounded-full bg-red-600 text-white hover:bg-red-700 transition"
          >
            <i className="fa-brands fa-google"></i>
          </a>
          <a
            href="#!"
            className="w-10 h-10 flex items-center justify-center rounded-full bg-purple-600 text-white hover:bg-purple-700 transition"
          >
            <i className="fa-brands fa-instagram"></i>
          </a>
        </div>

        {/* Copyright Section */}
        <div className="text-center text-gray-600 mt-6 pt-4 border-t border-gray-300">
          © 2025 Copyright: Farm Bazaar
        </div>
      </div>
    </footer>
  );
}

export default Footer;



// import React from 'react';

// function Footer() {
//   return (
//     <footer className="bg-gray-800 text-white py-4">
//       <div className="container mx-auto text-center">
//         <p>&copy; 2025 FARM BAZAAR. All Rights Reserved.</p>
//       </div>
//     </footer>
//   );
// }

// export default Footer;
