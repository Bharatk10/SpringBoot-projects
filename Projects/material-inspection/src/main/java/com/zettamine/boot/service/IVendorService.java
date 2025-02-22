package com.zettamine.boot.service;

import java.util.List;
import java.util.TreeSet;

import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.models.VendorNames;

public interface IVendorService {
	
	Vendor saveVendor(Vendor vendor);
	
	List<Vendor> getAllVendors();
	
	Vendor getVendorById(int id);
	
	Vendor editVendorStatus(int id);
	
	TreeSet<String> getAllVendorNames();
	
	Vendor getVendorByName(String vendorName);
	
}
