package com.zettamine.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.repository.MatInspRepository;
import com.zettamine.boot.utility.StringUtilis;

@Service
public class MaterialInspServiceImpl implements IMaterialInspService {

	private MatInspRepository matInspRepo;

	public MaterialInspServiceImpl(MatInspRepository matInspRepo) {
		super();
		this.matInspRepo = matInspRepo;
	}

	@Override
	public MaterialInspection saveMaterialInsp(MaterialInspection matInsp) {

		MaterialInspection matInspObj = null;

		matInsp.setChannelDescription(StringUtilis.processSentance(matInsp.getChannelDescription()));
		matInsp.setUms(StringUtilis.processString(matInsp.getUms()));

		matInsp.setStatus('A');

		HashMap<String, String> conflicts = new HashMap<>();

		Optional<MaterialInspection> optMatInsp = matInspRepo
				.findMatInspByDescAndMatrId(matInsp.getMaterial().getMaterialId(), matInsp.getChannelDescription());

		if (optMatInsp.isPresent()) {

			conflicts.put(matInsp.getChannelDescription(), AppConstants.FIELD_ERROR);
			throw new DataIntegrityViolationException(conflicts.toString());
		}

		try {
			matInspObj = matInspRepo.save(matInsp);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return matInspObj;
	}

	@Override
	public MaterialInspection editMaterialInspStatusBtId(int id) {

		MaterialInspection matInsp = null;

		Optional<MaterialInspection> optMatInsp = matInspRepo.findById(id);
		if (optMatInsp.isPresent()) {
			matInsp = optMatInsp.get();
			matInsp.setStatus('I');
		}
		matInsp = matInspRepo.save(matInsp);
		return matInsp;
	}

	@Override
	public List<MaterialInspection> getAllCharcateristicsById(String matId) {

		List<MaterialInspection> matInspList = matInspRepo.findMaterialInspectionByMaterialId(matId);
		return matInspList;
	}

	@Override
	public MaterialInspection getMaterialInspStatus(int id) {

		MaterialInspection matInsp = null;

		Optional<MaterialInspection> optMaptInsp = matInspRepo.findById(id);

		if (optMaptInsp.isPresent()) {
				return optMaptInsp.get();
		}
		return matInsp;
	}

}
