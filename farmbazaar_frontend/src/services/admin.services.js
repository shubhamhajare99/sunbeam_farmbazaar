import http from './http-common';

// Farmer Users CRUD operations

const getAllFarmerUsers = () => {
  return http.get('/admin/farmer-users');
};

const deleteFarmerUser = (id) => {
  return http.delete(`/admin/farmer-users/${id}`);
};

// Customer Users CRUD operations

const getAllCustomerUsers = () => {
  return http.get('/admin/customer-users');
};

// Category CRUD operations
const createCategory = (data) => {
  return http.post('/admin/categories', data);
};

const updateCategory = (id, data) => {
  return http.put(`/admin/categories/${id}`, data);
};

const deleteCategory = (id) => {
  return http.delete(`/admin/categories/${id}`);
};

const getAllCategories = () => {
  return http.get('/admin/categories');
};

// Product CRUD operations
const createProduct = (data) => {
  return http.post('/admin/products', data, {
      headers: {
          'Content-Type': 'multipart/form-data' // Ensure proper content type
      }
  });
};

const updateProductAPI = (id, updatedData) => {
  return http.put(`/admin/products/${id}`, updatedData);
};

const updateProduct = (id, formData) => {
  return http.put(`/admin/products/${id}`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};


const deleteProduct = (id) => {
  return http.delete(`/admin/products/${id}`);
};

const getAllProducts = () => {
  return http.get('/admin/products');
};

// Additional operations

const getProductsByFarmerId = (farmerId) => {
  return http.get(`/admin/${farmerId}/products`);
};

const getAllOrders = () => {
  return http.get('/admin/orders');
};

export {
  
  deleteFarmerUser,
  getAllFarmerUsers,
  getAllCustomerUsers,
  createCategory,
  updateCategory,
  deleteCategory,
  getAllCategories,
  createProduct,
  updateProductAPI,
  updateProduct,
  deleteProduct,
  getAllProducts,
  getProductsByFarmerId,
  getAllOrders
};