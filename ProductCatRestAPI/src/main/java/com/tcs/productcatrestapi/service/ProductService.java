package com.tcs.productcatrestapi.service;

import java.util.Optional;

import com.tcs.productcatrestapi.model.Product;



public interface ProductService {
	
	public Product createProduct(Product product);
	public Product updateProduct(Product product);
	public Optional<Product> getProductById(int id);
	public void deleteProduct(int id);
	public Optional<java.util.List<Product>> getProducts();
	public Optional<java.util.List<Product>> getProductsByCategory(String catName);

}
