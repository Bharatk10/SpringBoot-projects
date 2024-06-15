package com.zettamine.boot.repository;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.entity.Vendor;
import com.zettamine.boot.models.VendorNames;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	List<Vendor> findByStatusOrderByVendorId(Character ch);
	
	@Query("select  v.vendorName from Vendor v where v.status='A'")
	TreeSet<String> getAllVendorNames();

	Optional<Vendor> findByVendorName(String vendorName);

}
