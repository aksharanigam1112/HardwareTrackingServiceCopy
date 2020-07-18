package com.HardwareTrackingService.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HardwareTrackingService.Dao.ProductDao;
import com.HardwareTrackingService.Services.ProductService;
import com.HardwareTrackingService.controller.ProductController;
import com.HardwareTrackingService.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	private ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		logger.info("insideProductServiceImpl ");
		logger.info("Autowiring ProductDao");
		this.productDao = productDao;
	}

	@Override
	public Product createProduct(Product product) {
		logger.info("Inside Create Product ProductDao");
		return productDao.createProduct(product);

	}

	@Override
	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(String productId) {
		productDao.deleteProduct(productId);

	}

}