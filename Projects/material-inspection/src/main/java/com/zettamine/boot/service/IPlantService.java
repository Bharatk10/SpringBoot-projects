package com.zettamine.boot.service;

import java.util.List;
import java.util.TreeSet;

import com.zettamine.boot.entity.Plant;


public interface IPlantService {
	
	Plant savePlant(Plant plant);
	
	Plant updatePlant(Plant plant);
	
	List<Plant> getAllPlants();
	
	Plant getPlantById(String id);
	
	Plant editPlantStatus(String id);
	
	TreeSet<String> getAllPlantNames();
	
	Plant getPantByName(String plantName);
	
}
