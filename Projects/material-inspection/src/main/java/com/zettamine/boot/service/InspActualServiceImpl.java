package com.zettamine.boot.service;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.boot.entity.InspectionActuals;
import com.zettamine.boot.entity.InspectionLot;
import com.zettamine.boot.entity.Material;
import com.zettamine.boot.entity.MaterialInspection;
import com.zettamine.boot.repository.InspectionActualsRepo;
import com.zettamine.boot.repository.InspectionLotRepository;
import com.zettamine.boot.repository.MatInspRepository;

@Service
public class InspActualServiceImpl implements IInspActualService {

	private InspectionActualsRepo inspActRepo;

	

	public InspActualServiceImpl(InspectionActualsRepo inspActRepo) {
		super();
		this.inspActRepo = inspActRepo;
	}

	@Override
	public InspectionActuals saveInspectionActuals(InspectionActuals inspAct) {

		InspectionActuals inspActObj = null;
		try {
			inspActObj = inspActRepo.save(inspAct);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return inspActObj;
	}

}
