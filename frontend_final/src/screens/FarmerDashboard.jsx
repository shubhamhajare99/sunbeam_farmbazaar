import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import FarmerNavbar from '../components/FarmerNavbar'; 
import Footer from '../components/Footer';

function FarmerDashboard() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/farmer/getallproducts");
      const updatedProducts = response.data.map((product) => ({
        ...product,
        tempQuantity: 0,
      }));
      setProducts(updatedProducts);
      setLoading(false);
    } catch (error) {
      setError("Error fetching products");
      setLoading(false);
    }
  };

  const handleInputChange = (id, value) => {
    setProducts((prevProducts) =>
      prevProducts.map((product) =>
        product.id === id ? { ...product, tempQuantity: Number(value) } : product
      )
    );
  };

  const handleQuantitySubmit = async (id) => {
    const product = products.find((p) => p.id === id);
    const newQuantity = Number(product.tempQuantity);

    if (newQuantity <= 0) {
      toast.warning("Please enter a valid quantity!");
      return;
    }

    try {
      await axios.post(`http://localhost:8080/farmer/products/add-quantity`, null, {
        params: {
          productId: id,
          quantity: newQuantity,
        },
      });
      toast.success("Quantity updated, awaiting admin approval!");
      fetchProducts();
    } catch (error) {
      toast.error("Failed to update quantity");
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="spinner"></div>
      </div>
    );
  }

  if (error) {
    return <div className="text-red-500 text-center">{error}</div>;
  }

  return (
  <>
    <FarmerNavbar />  
    <div
      className="min-h-screen flex items-center justify-center bg-cover bg-center p-6"
      style={{ backgroundImage: "url('/images/farmer.gif')", backgroundSize: "cover", backgroundPosition: "center" }}
    >
      <div className="w-full max-w-3xl mx-auto p-10 bg-white rounded-lg shadow-xl bg-opacity-90">

        <h2 className="text-3xl font-semibold text-center mb-8 text-green-600">Farmer Dashboard</h2>
        <div className="overflow-x-auto mx-auto w-full">
          <table className="w-full table-auto bg-white border-collapse rounded-lg shadow-lg">
            <thead className="bg-green-500 text-white">
              <tr>
                <th className="py-3 px-4 text-left">Name</th>
                <th className="py-3 px-4 text-left">Price</th>
                <th className="py-3 px-4 text-left">Quantity</th>
                <th className="py-3 px-4 text-left">Status</th>
              </tr>
            </thead>
            <tbody>
              {products.map((product) => (
                <tr key={product.id} className="hover:bg-gray-100">
                  <td className="py-3 px-4">{product.name}</td>
                  <td className="py-3 px-4">{product.price}</td>
                  <td className="py-3 px-4">
                    <input
                      type="number"
                      className="border px-2 py-1 rounded-lg w-16 focus:ring-2 focus:ring-green-500"
                      value={product.tempQuantity}
                      min="0"
                      onChange={(e) => handleInputChange(product.id, e.target.value)}
                    />
                  </td>
                  <td className="py-3 px-4">
                    <button
                      onClick={() => handleQuantitySubmit(product.id)}
                      className="px-3 py-1 bg-green-500 text-white rounded-lg hover:bg-green-600"
                    >
                      Add Quantity
                    </button>
                  </td>
                  <td className="py-3 px-4">{product.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <Footer />
  </>  
  );
} 


export default FarmerDashboard;