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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.HardwareTrackingService.Services.BundleService;
import com.HardwareTrackingService.model.Bundle;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

//@RestController
public class BundleController {

	private BundleService bundleServices;

	@Autowired
	public BundleController(BundleService bundleServices) {
		this.bundleServices = bundleServices;
	}

	@PostMapping(value = "/bundles")
	public ResponseEntity createBundle(@RequestBody Bundle bundle) {
		try {
			Bundle response = bundleServices.createBundle(bundle);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (AmazonServiceException e) {
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	@GetMapping(value = "/bundles/{bundleId}")
	public ResponseEntity<Bundle> getBundles(@PathVariable String bundleId) {
		try {
			Bundle response = bundleServices.getBundle(bundleId);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (AmazonServiceException e) {
			throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
		} catch (AmazonClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

}
