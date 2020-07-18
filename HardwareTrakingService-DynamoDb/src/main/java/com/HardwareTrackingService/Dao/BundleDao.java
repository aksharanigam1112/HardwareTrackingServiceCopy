package com.HardwareTrackingService.Dao;

import com.HardwareTrackingService.model.Bundle;

public interface BundleDao {
	Bundle createBundle(Bundle bundle);
	Bundle getBundle(String bundleId);


}
