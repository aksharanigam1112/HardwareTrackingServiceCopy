package com.HardwareTrackingService.Dao;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.HardwareTrackingService.Dao.ProductDao;
import com.HardwareTrackingService.model.Product;

@Repository
public class ProductDaoImple implements ProductDao {

	private DynamoDBMapper dynamoDBMapper;
	Logger logger = LoggerFactory.getLogger(ProductDaoImple.class);

	@Autowired
	public ProductDaoImple(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public Product createProduct(Product product) {
		dynamoDBMapper.save(product);
		logger.info("Product created in  ProductDaoImpl");
		return product;
	}

	@Override
	public Product getProduct(String productId) {
		return dynamoDBMapper.load(Product.class, productId);
	}

	@Override
	public Product updateProduct(Product product) {
		HashMap<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
		expectedAttributeValueMap.put("productId",
				new ExpectedAttributeValue(new AttributeValue().withS(product.getProductId())));
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
		dynamoDBMapper.save(product, saveExpression);
		return product;
	}

	@Override
	public void deleteProduct(String productId) {
		// TODO Auto-generated method stub
		Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
		expectedAttributeValueMap.put("productId", new ExpectedAttributeValue(new AttributeValue().withS(productId)));
		DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression()
				.withExpected(expectedAttributeValueMap);
		Product product = dynamoDBMapper.load(Product.class, productId);
		dynamoDBMapper.delete(product, deleteExpression);

	}

}
