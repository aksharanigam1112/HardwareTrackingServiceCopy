package com.HardwareTrackingService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HardwareTrackingService.Dao.BundleDao;
import com.HardwareTrackingService.model.Bundle;

@Service 
public class BundleServiceImpl implements BundleService{
	private BundleDao bundleDao;
	@Autowired
	 public BundleServiceImpl(BundleDao bundleDao) {
        this.bundleDao = bundleDao;
    }

	@Override
	public Bundle createBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		return bundleDao.createBundle(bundle);
	}

	@Override
	public Bundle getBundle(String bundleId) {
		// TODO Auto-generated method stub
		return bundleDao.getBundle(bundleId);
	}
	

}