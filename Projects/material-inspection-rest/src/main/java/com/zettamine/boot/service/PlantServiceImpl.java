package com.zettamine.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zettamine.boot.constants.AppConstants;
import com.zettamine.boot.entity.Plant;
import com.zettamine.boot.repository.PlantRepository;
import com.zettamine.boot.utility.StringUtilis;

@Service
public class PlantServiceImpl implements IPlantService {

	@Autowired
	private PlantRepository plantRepo;

	@Override
	public Plant savePlant(Plant plant) {

		plant.setPlantId(StringUtilis.processString(plant.getPlantId()));
		plant.setPlantName(StringUtilis.processSentance(plant.getPlantName()));
		plant.setLocation(StringUtilis.processString(plant.getLocation()));

		Optional<Plant> optPlant = plantRepo.findById(plant.getPlantId());

		HashMap<String, String> conflicts = new HashMap<>();

		if (optPlant.isPresent()) {
			
			conflicts.put(plant.getPlantId(), AppConstants.FIELD_ERROR);
		}

		optPlant = plantRepo.findByPlantName(plant.getPlantName());

		if (optPlant.isPresent()) {
			
			conflicts.put(plant.getPlantName(), AppConstants.FIELD_ERROR);
		}

		if (conflicts.size() > 0) {
			throw new DataIntegrityViolationException(conflicts.toString());
		}

		Character ch = 'A';
		plant.setStatus(ch);

		Plant PlantObj = null;

		try {

			PlantObj = plantRepo.save(plant);

		} catch (Exception ex) {

		}

		return PlantObj;
	}

	@Override
	public List<Plant> getAllPlants() {

		Character ch = 'A';

		List<Plant> PlantList = plantRepo.findByStatus(ch);

		return PlantList;
	}

	@Override
	public Plant getPlantById(String id) {

		Plant Plant = null;

		Optional<Plant> PlantObj = plantRepo.findByPlantIdAndStatus(id, 'A');

		if (PlantObj.isPresent()) {
			Plant = PlantObj.get();
		}

		return Plant;
	}

	@Override
	public Plant editPlantStatus(String id) {

		Plant plant = null;

		Optional<Plant> plantObj = plantRepo.findById(id);

		if (plantObj.isPresent() && plantObj.get().getStatus() == 'A') {

			plant = plantObj.get();
			plant.setStatus('I');

			plant = plantRepo.save(plant);
		}

		return plant;
	}

	@Override
	public TreeSet<String> getAllPlantNames() {

		TreeSet<String> plantIds = plantRepo.getAllPlantName();

		return plantIds;
	}

	@Override
	public Plant getPantByName(String plantName) {

		Plant plant = null;
		Optional<Plant> optPlant = plantRepo.findByPlantName(plantName);
		if (optPlant.isPresent()) {
			plant = optPlant.get();
		}
		return plant;
	}

	@Override
	public Plant updatePlant(Plant plant) {

		HashMap<String, String> conflicts = new HashMap<>();

		plant.setPlantId(StringUtilis.processString(plant.getPlantId()));
		plant.setPlantName(StringUtilis.processSentance(plant.getPlantName()));
		plant.setLocation(StringUtilis.processString(plant.getLocation()));

		Character ch = 'A';
		plant.setStatus(ch);

		Optional<Plant> optPlant = plantRepo.findByPlantNameAndPlantIdNot(plant.getPlantName(),plant.getPlantId());

		if (optPlant.isPresent()) {
			System.out.println("name error");
			conflicts.put(plant.getPlantName(), AppConstants.FIELD_ERROR);
		}

		if (conflicts.size() > 0) {
			throw new DataIntegrityViolationException(conflicts.toString());
		}

		Plant PlantObj = null;

		try {

			PlantObj = plantRepo.save(plant);

		} catch (Exception ex) {

		}

		return PlantObj;
	}

}
