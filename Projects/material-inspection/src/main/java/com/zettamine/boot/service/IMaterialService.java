package com.zettamine.boot.service;

import java.util.List;
import java.util.TreeSet;

import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.models.MaterialSearchCriteria;

public interface IMaterialService {
	
	Material saveMaterialById(Material material,String id);
	
	String materialIdGenerator();
	
	List<Material> getAllMaterials();
	
	Material getMaterialById(String id);
	
	Material saveMaterial(Material material);
	
	TreeSet<String> getAllMaterialNames();
	
	Material getMaterialByName(String materialName);
	
	List<Material> getAllMaterialsBasedonSearchCriteria(MaterialSearchCriteria criteria);
	
	Material getFiletedMaterial(Material material);
	


}
