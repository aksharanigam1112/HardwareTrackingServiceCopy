package com.HardwareTrackingService.Services;

import com.HardwareTrackingService.model.Bundle;

public interface BundleService {
	Bundle createBundle(Bundle bundle);

	Bundle getBundle(String bundleId);
}
