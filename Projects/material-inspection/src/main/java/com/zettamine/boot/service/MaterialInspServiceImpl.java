package com.zettamine.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
		
		try {
			 matInspObj = matInspRepo.save(matInsp);
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		
		
		return matInspObj;
	}

	@Override
	public MaterialInspection editMaterialInspStatusBtId(int id) {
		
		MaterialInspection matInsp = null;
		
		Optional<MaterialInspection> optMatInsp = matInspRepo.findById(id);
		if(optMatInsp.isPresent()) {
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
		
		MaterialInspection matInsp = matInspRepo.findById(id).get();
		return matInsp;
	}

}
