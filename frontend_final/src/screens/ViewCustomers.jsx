import React, { useEffect, useState } from "react";
//useEffect -> Helps manage side effects (things that happen outside the component).

const ViewCustomers = () => {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    // Fetch customers' data from backend
    fetch("http://localhost:8080/admin/customer-users") // Adjust URL based on your backend API
      .then((response) => response.json())
      .then((data) => setCustomers(data))
      .catch((error) => console.error("Error fetching customers:", error));
  }, []);

  return (
    <div className="bg-white p-6 rounded-lg shadow-lg">
      <h2 className="text-2xl font-semibold mb-4">Customers List</h2>
      <table className="min-w-full table-auto border">
        <thead>
          <tr>
            <th className="border px-4 py-2">Email</th>
            <th className="border px-4 py-2">Password</th>
            <th className="border px-4 py-2">First Name</th>
            <th className="border px-4 py-2">Last Name</th>
            <th className="border px-4 py-2">Mobile No</th>
            <th className="border px-4 py-2">Address</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((customer, index) => (
            <tr key={index}>
              <td className="border px-4 py-2">{customer.email}</td>
              <td className="border px-4 py-2">{customer.password}</td>
              <td className="border px-4 py-2">{customer.firstName}</td>
              <td className="border px-4 py-2">{customer.lastName}</td>
              <td className="border px-4 py-2">{customer.mobileNo}</td>
              <td className="border px-4 py-2">{customer.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewCustomers;
