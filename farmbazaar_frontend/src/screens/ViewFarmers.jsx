import React, { useEffect, useState } from "react";

const ViewFarmers = () => {
  const [farmers, setFarmers] = useState([]);

  useEffect(() => {
    // Fetch farmers from the backend
    fetch("http://localhost:8080/admin/farmer-users")
      .then((response) => response.json())
      .then((data) => setFarmers(data))
      .catch((error) => console.error("Error fetching farmers:", error));
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-2xl font-semibold mb-4">Farmers List</h2>
      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-300">
          <thead>
            <tr className="bg-gray-200">
              <th className="border px-4 py-2">Email</th>
              <th className="border px-4 py-2">Password</th>
              <th className="border px-4 py-2">First Name</th>
              <th className="border px-4 py-2">Last Name</th>
              <th className="border px-4 py-2">Mobile No</th>
              <th className="border px-4 py-2">Address</th>
            </tr>
          </thead>
          <tbody>
            {farmers.map((farmer, index) => (
              <tr key={index} className="border">
                <td className="border px-4 py-2">{farmer.email}</td>
                <td className="border px-4 py-2">{farmer.password}</td>
                <td className="border px-4 py-2">{farmer.firstName}</td>
                <td className="border px-4 py-2">{farmer.lastName}</td>
                <td className="border px-4 py-2">{farmer.mobileNo}</td>
                <td className="border px-4 py-2">{farmer.address}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewFarmers;
