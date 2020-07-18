package com.HardwareTrackingService.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.HardwareTrackingService.model.Bundle;

@Repository
public class BundleDaoImple implements BundleDao {
	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	public BundleDaoImple(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public Bundle createBundle(Bundle bundle) {
		dynamoDBMapper.save(bundle);
		return null;
	}

	@Override
	public Bundle getBundle(String bundleId) {
		return dynamoDBMapper.load(Bundle.class, bundleId);
	}

}
