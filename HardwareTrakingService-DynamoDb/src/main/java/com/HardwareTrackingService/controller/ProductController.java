package com.HardwareTrackingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.HardwareTrackingService.model.Product;
import com.HardwareTrackingService.Services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/dynamodb")
public class ProductController {
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productServices) {
		logger.info("inside ProductController");
		logger.info("Autowiring Product services");
		this.productService = productServices;
	}

	@PostMapping(value = "/products")
	public ResponseEntity createProduct(@RequestBody Product product) {
		logger.info("inside Create Product");
		try {
			Product response = productService.createProduct(product);
			logger.info("Reponse Created");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (AmazonServiceException e) {
			logger.error("INTERNAL_SERVER_ERROR");
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> getProducts(@PathVariable String productId) {
		try {
			Product response = productService.getProduct(productId);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (AmazonServiceException e) {
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@PutMapping(value = "/products")
	public ResponseEntity<Product> updateProducts(@RequestBody Product product) {
		try {
			Product response = productService.updateProduct(product);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (AmazonServiceException e) {
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<Product> deleteProducts(@PathVariable String productId) {
		try {
			productService.deleteProduct(productId);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (AmazonServiceException e) {
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}
