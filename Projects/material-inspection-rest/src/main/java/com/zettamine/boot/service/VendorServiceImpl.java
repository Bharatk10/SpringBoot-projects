package com.zettamine.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.repository.VendorRepository;
import com.zettamine.boot.utility.StringUtilis;

@Service
public class VendorServiceImpl implements IVendorService {

	@Autowired
	private VendorRepository vendorRepo;

	@Override
	public Vendor saveVendor(Vendor vendor) {

		Vendor vendorObj = null;

		vendor.setVendorName(StringUtilis.processSentance(vendor.getVendorName()));
		vendor.setVendorEmail(StringUtilis.processString(vendor.getVendorEmail()));
		vendor.setLocation(StringUtilis.processString(vendor.getLocation()));
		vendor.setState(StringUtilis.processSentance(vendor.getState()));

		Optional<Vendor> optVendor = vendorRepo.findByVendorName(vendor.getVendorName());

		HashMap<String, String> conflicts = new HashMap<>();
		if (optVendor.isPresent()) {

			conflicts.put(vendor.getVendorName(), AppConstants.FIELD_ERROR);

		}
		optVendor = vendorRepo.findByVendorEmail(vendor.getVendorEmail());

		if (optVendor.isPresent()) {
			System.out.println("error");
			conflicts.put(vendor.getVendorEmail(), AppConstants.FIELD_ERROR);
		}

		Character ch = 'A';
		vendor.setStatus(ch);

		try {
			vendorObj = vendorRepo.save(vendor);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException(conflicts.toString());

		}

		return vendorObj;
	}

	@Override
	public List<Vendor> getAllVendors() {

		Character ch = 'A';

		List<Vendor> vendorList = vendorRepo.findByStatusOrderByVendorId(ch);

		return vendorList;
	}

	@Override
	public Vendor getVendorById(int id) {

		Vendor vendor = null;

		Optional<Vendor> vendorObj = vendorRepo.findByVendorIdAndStatus(id, 'A');
		if (vendorObj.isPresent()) {
			vendor = vendorObj.get();
		}

		return vendor;
	}

	@Override
	public Vendor editVendorStatus(int id) {

		Vendor vendor = null;

		Optional<Vendor> vendorObj = vendorRepo.findByVendorIdAndStatus(id,'A');

		if (vendorObj.isPresent()) {

			vendor = vendorObj.get();
			vendor.setStatus('I');

			vendor = vendorRepo.save(vendor);
		}

		return vendor;
	}

	@Override
	public TreeSet<String> getAllVendorNames() {

		TreeSet<String> vendorNames = vendorRepo.getAllVendorNames();
		return vendorNames;
	}

	@Override
	public Vendor getVendorByName(String vendorName) {

		Vendor vendor = null;

		Optional<Vendor> optVendor = vendorRepo.findByVendorName(vendorName);

		if (optVendor.isPresent()) {
			vendor = optVendor.get();
		}
		return vendor;
	}
	@Override
	public Vendor updateVendor(Vendor vendor) {

		Vendor vendorObj = null;
		
		Optional<Vendor> optVendorObj = vendorRepo.findByVendorIdAndStatus(vendor.getVendorId(),'A');
		
		if(optVendorObj.isEmpty()) {
			
			return vendorObj;
		}

		vendor.setVendorName(StringUtilis.processSentance(vendor.getVendorName()));
		vendor.setVendorEmail(StringUtilis.processString(vendor.getVendorEmail()));
		vendor.setLocation(StringUtilis.processString(vendor.getLocation()));
		vendor.setState(StringUtilis.processSentance(vendor.getState()));
		Character ch = 'A';
		vendor.setStatus(ch);
		
		

		Optional<Vendor> optVendor = vendorRepo.findByVendorNameAndVendorIdNot(vendor.getVendorName(),vendor.getVendorId());

		HashMap<String, String> conflicts = new HashMap<>();
		if (optVendor.isPresent()) {

			System.out.println("name rror");
			conflicts.put(vendor.getVendorName(), AppConstants.FIELD_ERROR);

		}
		Optional<Vendor> opVendor = vendorRepo.findByVendorEmailAndVendorIdNot(vendor.getVendorEmail(),vendor.getVendorId());

		if (opVendor.isPresent()) {
			System.out.println("email error");
			conflicts.put(vendor.getVendorEmail(), AppConstants.FIELD_ERROR);
		}

		try {
			vendorObj = vendorRepo.save(vendor);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException(conflicts.toString());

		}

		return vendorObj;
	}

}
