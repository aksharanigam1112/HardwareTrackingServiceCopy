package com.HardwareTrackingService.Dao;

import com.HardwareTrackingService.model.Product;

public interface ProductDao {

	Product createProduct(Product product);

	Product getProduct(String productId);

	Product updateProduct(Product product);
	
	void deleteProduct(String productId);
}
