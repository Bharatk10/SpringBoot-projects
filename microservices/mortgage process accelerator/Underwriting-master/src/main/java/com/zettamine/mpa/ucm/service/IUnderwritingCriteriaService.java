package com.zettamine.mpa.ucm.service;

import java.util.List;
import java.util.Set;

import com.zettamine.mpa.ucm.dto.UnderwritingCriteriaDto;

public interface IUnderwritingCriteriaService {

	void save(UnderwritingCriteriaDto underwritingCriteriaDto) throws IllegalArgumentException, IllegalAccessException;
	
	void update(Long id, UnderwritingCriteriaDto underwritingCriteriaDto) throws IllegalArgumentException, IllegalAccessException;
	
	UnderwritingCriteriaDto get(Long id);
	
	List<UnderwritingCriteriaDto> getAll();
	
	void addCriteriaToLoanProd(List<String> criteriaNames, String loanProductName);
	
	void removeCriteriaToLoanProd(List<String> criteriaNames, String loanProductName);
	
	Set<Integer> getByCriterias(List<String> criteriaNames);

	List<String> getAllCriteriaNames();
	
	void saveLoanProdCriteria(Integer prodctId, List<String> criteria);
	
	public List<UnderwritingCriteriaDto> getByLoanId(Integer id);
}
