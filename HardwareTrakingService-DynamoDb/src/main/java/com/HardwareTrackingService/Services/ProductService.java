package com.HardwareTrackingService.Services;



import com.HardwareTrackingService.model.Product;

public interface ProductService {

	    Product createProduct(Product product);

		Product getProduct(String productId);

		Product updateProduct(Product product);
		
		void deleteProduct(String productId);
}
