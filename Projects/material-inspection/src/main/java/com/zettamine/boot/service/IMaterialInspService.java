package com.zettamine.boot.service;

import java.util.List;

import com.zettamine.boot.entity.MaterialInspection;

public interface IMaterialInspService {
	
	MaterialInspection saveMaterialInsp(MaterialInspection matInsp);
	
	MaterialInspection editMaterialInspStatusBtId(int id);
	
	List<MaterialInspection> getAllCharcateristicsById(String matId);
	
	MaterialInspection getMaterialInspStatus(int id);



}
