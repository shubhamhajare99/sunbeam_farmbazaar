import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import NavBarCustomer from '../../components/Navbar';
import Footer from '../../components/Footer';

const EditProfile = () => {
    
  const [customer, setCustomer] = useState({ address: "", mobileNo: "" });
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  // Get user data safely from sessionStorage
  const userData = JSON.parse(sessionStorage.getItem("userData")) || {};
  const { id: customerId } = userData; // Extract customerId safely

  useEffect(() => {
    if (!customerId) return; // Prevent API call if no ID is found

    axios.get(`http://localhost:8080/customer/${customerId}`)
      .then(response => {
        setCustomer(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error("Error fetching customer data", error);
        setLoading(false);
      });
  }, [customerId]);

  const handleChange = (e) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!customerId) {
      alert("User not found. Please log in again.");
      return;
    }
    
    try {
      await axios.put(`http://localhost:8080/customer/${customerId}`, customer);
      alert("Profile updated successfully!");
      navigate("/customer-dashboard"); 
    } catch (error) {
      console.error("Error updating profile", error);
      alert("Failed to update profile. Try again.");
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <>
    <NavBarCustomer />
    <div className="max-w-md mx-auto bg-white p-6 rounded-lg shadow-md mt-10">
      <h2 className="text-xl font-semibold mb-4">Edit Profile</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-gray-700">Address</label>
          <input
            type="text"
            name="address"
            value={customer.address}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Mobile Number</label>
          <input
            type="text"
            name="mobileNo"
            value={customer.mobileNo}
            onChange={handleChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded" >
          Update Profile
        </button>
      </form>
    </div>
    <br></br>
 < Footer />
</>
  );
};

export default EditProfile;
