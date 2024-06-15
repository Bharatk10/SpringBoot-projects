package com.zettamine.mpa.escrow.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zettamine.mpa.escrow.constants.EscrowConstants;
import com.zettamine.mpa.escrow.dto.EscrowAgentDto;
import com.zettamine.mpa.escrow.dto.EscrowDto;
import com.zettamine.mpa.escrow.dto.EscrowFetchDto;
import com.zettamine.mpa.escrow.dto.EscrowServiceAreaDto;
import com.zettamine.mpa.escrow.dto.SearchCriteria;
import com.zettamine.mpa.escrow.entity.Escrow;
import com.zettamine.mpa.escrow.entity.EscrowAgent;
import com.zettamine.mpa.escrow.entity.EscrowServiceArea;
import com.zettamine.mpa.escrow.repository.EscrowRepository;
import com.zettamine.mpa.escrow.service.EscrowService;
import com.zettamine.mpa.escrow.utils.StringUtil;
import com.zettamine.mpa.exception.ResourceNotFoundException;
import com.zettamine.mpa.mapper.EscrowAgentMapper;
import com.zettamine.mpa.mapper.EscrowMapper;
import com.zettamine.mpa.mapper.EscrowServiceAreaMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EscrowServiceImpl implements EscrowService {
			
	private EscrowRepository escrowRepository;
	private EscrowMapper escrowMapper;
	private EscrowServiceAreaMapper serviceAreaMapper;
	private EscrowAgentMapper agentMapper;

	private JdbcTemplate jdbcTemplate;
	
	
	/**
     * Creates a new Escrow entity and saves it to the database.
     *
     * @param escrowDto The DTO containing the data for the new Escrow entity.
     */
	
	@Override
	@Transactional
	public void save(EscrowDto escrowDto) {		
		
		Escrow escrow = escrowMapper.toEntity(escrowDto);
	    StringUtil.escrowRemoveSpaces(escrow);
	    List<EscrowServiceArea> serviceAreaList = new ArrayList<>();
	    List<EscrowAgent> agentList = new ArrayList<>();
	    
	    Set<EscrowServiceArea> distinctServiceAreas = new HashSet<>();
	    for (EscrowServiceAreaDto serviceArea : escrowDto.getServiceArea()) {
	        EscrowServiceArea escrowService = serviceAreaMapper.toEntity(serviceArea);
	        StringUtil.escrowServiceAreaRemoveSpaces(escrowService);
	        escrowService.setEscrow(escrow);
	        distinctServiceAreas.add(escrowService);
	    }
	    serviceAreaList.addAll(distinctServiceAreas);

	    Set<EscrowAgent> distinctAgents = new HashSet<>();
	    for (EscrowAgentDto agent : escrowDto.getEscrowAgent()) {
	        EscrowAgent escrowAgent = agentMapper.toEntity(agent);
	        StringUtil.escrowAgentRemoveSpaces(escrowAgent);
	        escrowAgent.setEscrow(escrow);
	        distinctAgents.add(escrowAgent);
	    }
	    agentList.addAll(distinctAgents);

	    escrow.setServiceArea(serviceAreaList);
	    escrow.setEscrowAgent(agentList);
	    escrowRepository.save(escrow);
	  }
	
	/**
     * Retrieves an Escrow entity by its ID.
     *
     * @param escrowId The ID of the Escrow entity to retrieve.
     * @return The DTO representing the retrieved Escrow entity.
     * @throws ResourceNotFoundException if no Escrow entity with the given ID is found.
     */
	@Override
	public EscrowDto findById(Integer escrowId) {
		Optional<Escrow> escrow = escrowRepository.findById(escrowId);
		if(escrow.isEmpty()) {
			throw new ResourceNotFoundException(EscrowConstants.ESCROW_NOT_FOUND + escrowId);
		}
		return escrowMapper.toDto(escrow.get());
	}

	/**
     * Retrieves an Escrow entity by its name.
     *
     * @param escoName The name of the Escrow entity to retrieve.
     * @return The DTO representing the retrieved Escrow entity.
     * @throws ResourceNotFoundException if no Escrow entity with the given name is found.
     */
	@Override
	public EscrowDto findByEscrowName(String escoName) {
		 Optional<Escrow> escrow = escrowRepository.findByEscoName(StringUtil.removeExtraSpaces(escoName).toUpperCase());
		 if(escrow.isEmpty()) {
			 throw new ResourceNotFoundException(EscrowConstants.ESCROW_NOT_FOUND_BY_NAME  + escoName);
		 }
		 
		 return escrowMapper.toDto(escrow.get());
	}
	
	/**
     * Retrieves all Escrow entities from the database.
     *
     * @return A list of DTOs representing all Escrow entities.
     */
	@Override
	public List<EscrowDto> getAll() {
	    return escrowMapper.toDtoList(escrowRepository.findAll());
	}
	
	/**
     * Updates an existing Escrow entity in the database.
     *
     * @param escrowDto The DTO containing the updated data for the Escrow entity.
     * @param escoId    The ID of the Escrow entity to update.
     * @return True if the update was successful, false otherwise.
     */
    @Transactional
	@Override
	public boolean update(EscrowDto escrowDto, Integer escoId) {
	   Optional<Escrow> escrowObj = escrowRepository.findById(escoId);
	   if(escrowObj.isPresent()) {
		    Escrow escrow = escrowMapper.toEntity(escrowDto);
		    escrow.setEscoId(escoId);
		    StringUtil.escrowRemoveSpaces(escrow);
		    escrowRepository.save(escrow);
		    return true;
	   }
		return false;
	}
    
    /**
     * Searches for Escrow entities based on the provided search criteria.
     *
     * @param criteria The criteria to search for Escrow entities.
     * @return A list of DTOs representing the search results.
     */
    
    @Override
    @Transactional
    public List<EscrowFetchDto> search(SearchCriteria criteria) {
       
        if (criteria.getCity() == null && criteria.getName() == null && criteria.getState() == null && criteria.getZipcode() == null) {
            return new ArrayList<>();
        }
        
        List<Object> queryParams = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT distinct(m.esco_id), m.esco_name, m.state, m.city, m.email, m.phone, m.zipcode FROM mpa.escrow_company m INNER JOIN mpa.escrow_service_area a ON a.esco_id = m.esco_id WHERE 1=1");

        if (criteria.getName() != null) {
            sql.append(" AND UPPER(m.esco_name) ILIKE ?");
            queryParams.add("%" + StringUtil.removeExtraSpaces(criteria.getName()) + "%");
        }
       
        if (criteria.getState() != null) {
            sql.append(" AND UPPER(a.state) = ?");
            queryParams.add(StringUtil.removeExtraSpaces(criteria.getState().toUpperCase()));
        }
        if (criteria.getCity() != null) {
            sql.append(" AND UPPER(a.city) = ?");
            queryParams.add(StringUtil.removeExtraSpaces(criteria.getCity().toUpperCase()));
        }
        if (criteria.getZipcode() != null) {
            sql.append(" AND UPPER(a.zipcode) = ?");
            queryParams.add(StringUtil.removeAllSpaces(criteria.getZipcode().toUpperCase()));
        }

        List<EscrowFetchDto> escrowSearchResults = jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            EscrowFetchDto escrowFetchDto = new EscrowFetchDto();
            escrowFetchDto.setEscoName(rs.getString("esco_name"));
            escrowFetchDto.setState(rs.getString("state"));
            escrowFetchDto.setCity(rs.getString("city"));
            escrowFetchDto.setZipcode(rs.getString("zipcode"));
            escrowFetchDto.setEmail(rs.getString("email"));
            escrowFetchDto.setPhone(rs.getString("phone"));
            return escrowFetchDto;
        }, queryParams.toArray());

        return escrowSearchResults;
    }

       
}
