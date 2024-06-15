package com.zettamine.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.models.MaterialSearchCriteria;
import com.zettamine.boot.repository.MaterialRepository;
import com.zettamine.boot.utility.StringUtilis;

@Service
public class MaterialServiceImpl implements IMaterialService {

	@Autowired
	private MaterialRepository materialRepo;

	@Override
	public Material saveMaterialById(Material material, String id) {

		material.setMaterialId(id);
		material.setDesc(StringUtilis.processSentance(material.getDesc()));
		material.setMaterialType(StringUtilis.processSentance(material.getMaterialType()));
		material.setStatus('A');
		
		HashMap<String, String> conflicts = new HashMap<>();
		
	    Optional<Material>  optMaterial = materialRepo.findByDesc(material.getDesc());

		if (optMaterial.isPresent()) {
			conflicts.put(material.getDesc(), AppConstants.FIELD_ERROR);
			throw new DataIntegrityViolationException(conflicts.toString());
		}

		Material materialObj = null;

		try {
			materialObj = materialRepo.save(material);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return materialObj;
	}

	@Override
	public String materialIdGenerator() {
		
		
		String materialId = materialRepo.findHighestMaterialId();
		
		int number = Integer.parseInt(materialId.replace("M-",""));
		
		String c = "";

		if (number >= 9) {
			c = Long.toString(number + 1);
			return "M-" + c;

		}
		c = Long.toString(number + 1);
		return "M-0" + c;

	}

	@Override
	public List<Material> getAllMaterials() {

		List<Material> materials = materialRepo.findAllOrderByMaterialId();
		return materials;
	}

	@Override
	public Material getMaterialById(String id) {

		Material material = null;
		Optional<Material> optMaterial = materialRepo.findById(StringUtilis.processString(id));

		if (optMaterial.isPresent()) {
			material = optMaterial.get();
		}

		return material;
	}

	@Override
	public Material saveMaterial(Material material) {
		
		material.setMaterialId(StringUtilis.processString(material.getMaterialId()));
		material.setDesc(StringUtilis.processSentance(material.getDesc()));
		material.setMaterialType(StringUtilis.processSentance(material.getMaterialType()));
		material.setStatus('A');
		
		HashMap<String, String> conflicts = new HashMap<>();
		
	    Optional<Material>  optMaterial = materialRepo.findByDescAndMaterialIdNot(material.getDesc(),material.getMaterialId());

		if (optMaterial.isPresent()) {
			conflicts.put(material.getDesc(), AppConstants.FIELD_ERROR);
			throw new DataIntegrityViolationException(conflicts.toString());
		}

		Material materialObj = null;

		try {
			materialObj = materialRepo.save(material);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return materialObj;
	}

	@Override
	public TreeSet<String> getAllMaterialNames() {

		TreeSet<String> materialNames = materialRepo.findAllMaterialName();
		return materialNames;
	}

	@Override
	public Material getMaterialByName(String materialName) {
		Optional<Material> OptMaterial = materialRepo.findByDesc(materialName);

		Material material = null;

		if (OptMaterial.isPresent()) {

			material = OptMaterial.get();
		}

		return material;
	}

	@Override
	public List<Material> getAllMaterialsBasedonSearchCriteria(MaterialSearchCriteria criteria) {

		List<Material> materials = materialRepo.findAllOrderByMaterialId();

		if (!criteria.getMaterialType().isBlank()) {
			materials = materials.stream().filter(mat -> mat.getMaterialType().equals(criteria.getMaterialType()))
					.toList();
		}
		if (!criteria.getDesc().isBlank()) {
			materials = materials.stream()
					.filter(mat -> mat.getDesc().contains(StringUtilis.processSentance(criteria.getDesc()))).toList();
		}
		return materials;
	}

	@Override
	public Material getFiletedMaterial(Material material) {

		List<MaterialInspection> materalInsp = material.getMatInsp().stream()
				.filter(matInsp -> 'A' == (matInsp.getStatus()))
				.sorted((o1, o2) -> o1.getChannelId() - o2.getChannelId()).collect(Collectors.toList());

		material.setMatInsp(materalInsp);

		return material;

	}

}
