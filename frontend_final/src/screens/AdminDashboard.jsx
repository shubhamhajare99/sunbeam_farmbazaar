import React, { useState } from 'react';
import { FaUsers, FaProductHunt, FaBox, FaClipboardList } from 'react-icons/fa'; 
import ManageProducts from '././AdminUsers/Product'; 
import ManageCategories from '././AdminUsers/Category'
import OrdersTable from '././AdminUsers/OrdersTable' 
import ViewFarmers from "./ViewFarmers";  
import ViewCustomers from "./ViewCustomers";

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState('welcome');

  const tabs = [
    { id: 'viewFarmers', label: 'View Farmers', icon: <FaUsers /> },
    { id: 'viewCustomers', label: 'View Customers', icon: <FaUsers /> },
    { id: 'manageProducts', label: 'Manage Products', icon: <FaProductHunt /> },
    { id: 'manageCategories', label: 'Manage Categories', icon: <FaProductHunt /> },
    { id: 'ordersTable', label: 'View All Orders', icon: <FaClipboardList /> }, 
  ];

  return (
    <div className="flex min-h-screen">
      {/* Sidebar */}
      <div className="w-64 bg-gray-800 text-white p-5 fixed h-full">
        <h2 className="text-2xl font-bold mb-8 text-center">Farm Bazaar</h2>
        <ul>
          {tabs.map((tab) => (
            <li key={tab.id} className="mb-4">
              <button
                onClick={() => setActiveTab(tab.id)}
                className={`w-full text-left py-2 px-4 rounded-lg flex items-center ${
                  activeTab === tab.id ? 'bg-green-600' : 'hover:bg-green-500'
                }`}
              >
                {tab.icon}
                <span className="ml-3">{tab.label}</span>
              </button>
            </li>
          ))}
        </ul>
      </div>

      {/* Main Content */}
      <div className="flex-grow ml-64 p-6">
        <h1 className="text-3xl font-bold mb-8">Admin Dashboard</h1>

        {/* Welcome Message */}
        {activeTab === 'welcome' && (
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h2 className="text-2xl font-semibold">Welcome Admin</h2>
            <p className="mt-4">Select an option from the sidebar to manage the platform.</p>
          </div>
        )}

        {activeTab === 'viewFarmers' && <ViewFarmers />}
        {activeTab === 'viewCustomers' && <ViewCustomers />}
        {activeTab === 'manageProducts' && <ManageProducts />}
        {activeTab === 'manageCategories' && <ManageCategories />} 
        {activeTab === 'ordersTable' && <OrdersTable />} 
      </div>
    </div>
  );
};

export default AdminDashboard;
