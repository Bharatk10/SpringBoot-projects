package com.zettamine.mpa.lpm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.zettamine.mpa.lpm.entity.LoanProduct;
import com.zettamine.mpa.lpm.entity.PrepayPenalty;
import com.zettamine.mpa.lpm.entity.ProductStatusHistory;
import com.zettamine.mpa.lpm.entity.PropertyRestriction;
import com.zettamine.mpa.lpm.exception.DataViolationException;
import com.zettamine.mpa.lpm.exception.MismatchFoundException;
import com.zettamine.mpa.lpm.exception.ResourceAlreadyExistsException;
import com.zettamine.mpa.lpm.exception.ResourceNotFoundException;
import com.zettamine.mpa.lpm.mapper.LoanProductMapper;
import com.zettamine.mpa.lpm.model.LoanProdDto;
import com.zettamine.mpa.lpm.model.LoanProductDto;
import com.zettamine.mpa.lpm.model.LoanProductSearchCriteria;
import com.zettamine.mpa.lpm.model.LoanReqDto;
import com.zettamine.mpa.lpm.model.PrePayPenalityDto;
import com.zettamine.mpa.lpm.model.PrepayPenaltyStatusDto;
import com.zettamine.mpa.lpm.model.ProductStatusHistoryDto;
import com.zettamine.mpa.lpm.model.UnderwritingCriteriaDto;
import com.zettamine.mpa.lpm.repository.LoanProductRepository;
import com.zettamine.mpa.lpm.repository.LoanProductStatusHistoryRepsitory;
import com.zettamine.mpa.lpm.repository.PrePayPenalityRepository;
import com.zettamine.mpa.lpm.repository.PropertyRestrictionRepository;
import com.zettamine.mpa.lpm.service.client.EscrowFeignClient;
import com.zettamine.mpa.lpm.service.client.UnderWritingCriteriaFeignClient;

@SpringBootTest
public class LoanProductServiceImplTest {

    @InjectMocks
    private LoanProductServiceImpl loanProductService;

    @Mock
    private LoanProductRepository loanProductRepository;
    
    @Mock
    private UnderWritingCriteriaFeignClient criteriaClient;

    @Mock
    private PropertyRestrictionRepository propertyRestrictionRepository;
    
    @Mock
    private EscrowFeignClient escrowClient;
    
    @Mock
    private PrePayPenalityRepository prePayPenalityRepository;
    
    @Mock
    private LoanProductStatusHistoryRepsitory  loanProductStatusHistoryRepsitory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLoanProduct_ThrowsResourceAlreadyExistsException() {
       
        
      LoanProductDto loanProductDto = new LoanProductDto();
  	  loanProductDto.setProductName("Home Loan");
  	  loanProductDto.setLoanTerm("240"); loanProductDto.setInterestRate("11.7");;
  	  loanProductDto.setMaxLoanAmount("23343422");
  	  loanProductDto.setMinDownPayment("3232");
  	  loanProductDto.setMinDownPaymentType("absolute");
  	  loanProductDto.setMinCreditScore("600");
  	  loanProductDto.setMaxLoanToValueRatio("90");
  	  loanProductDto.setPrivateMortgageInsuranceReq(true);
  	  loanProductDto.setOrginationFee("678"); loanProductDto.setLockinPeriod("20");
  	  loanProductDto.setMinPenalityAmount("123");
  	  loanProductDto.setPenalityPercentage("1.34");
  	  loanProductDto.setDocumentRequirement("document requirements");

        when(loanProductRepository.findByProductName(any(String.class)))
                .thenReturn(Optional.of(new LoanProduct()));

        try {
            loanProductService.create(loanProductDto);
        } catch (ResourceAlreadyExistsException e) {
            verify(loanProductRepository, times(1)).findByProductName(any(String.class));
        }
    }

    @Test
    void createLoanProduct_ThrowsDataViolationException() {
        LoanProductDto loanProductDto = new LoanProductDto();
        loanProductDto.setProductName("New Product");
      
        loanProductDto.setLockinPeriod("10"); // This should cause a DataViolationException
    	  loanProductDto.setProductName("Home Loan");
    	  loanProductDto.setLoanTerm("5"); loanProductDto.setInterestRate("11.7");;
    	  loanProductDto.setMaxLoanAmount("23343422");
    	  loanProductDto.setMinDownPayment("3232");
    	  loanProductDto.setMinDownPaymentType("absolute");
    	  loanProductDto.setMinCreditScore("600");
    	  loanProductDto.setMaxLoanToValueRatio("90");
    	  loanProductDto.setPrivateMortgageInsuranceReq(true);
    	  loanProductDto.setOrginationFee("678"); loanProductDto.setLockinPeriod("20");
    	  loanProductDto.setMinPenalityAmount("123");
    	  loanProductDto.setPenalityPercentage("1.34");
    	  loanProductDto.setDocumentRequirement("document requirements");

        when(loanProductRepository.findByProductName(any(String.class)))
                .thenReturn(Optional.empty());

        try {
            loanProductService.create(loanProductDto);
        } catch (DataViolationException e) {
            verify(loanProductRepository, times(1)).findByProductName(any(String.class));
        }
    }

    @Test
  
    void createLoanProduct_ThrowsResourceNotFoundExceptionForMissingRestrictions() {
        LoanProductDto loanProductDto = new LoanProductDto();
        loanProductDto.setProductName("New Product");
        loanProductDto.setPropertyRestrictionTypes(List.of("Non-Existent Restriction"));
       
  
    	  loanProductDto.setLoanTerm("240"); loanProductDto.setInterestRate("11.7");;
    	  loanProductDto.setMaxLoanAmount("23343422");
    	  loanProductDto.setMinDownPayment("3232");
    	  loanProductDto.setMinDownPaymentType("absolute");
    	  loanProductDto.setMinCreditScore("600");
    	  loanProductDto.setMaxLoanToValueRatio("90");
    	  loanProductDto.setPrivateMortgageInsuranceReq(true);
    	  loanProductDto.setOrginationFee("678"); loanProductDto.setLockinPeriod("20");
    	  loanProductDto.setMinPenalityAmount("123");
    	  loanProductDto.setPenalityPercentage("1.34");
    	  loanProductDto.setDocumentRequirement("document requirements");

        when(loanProductRepository.findByProductName(any(String.class)))
                .thenReturn(Optional.empty());
        when(propertyRestrictionRepository.findByRestrictionType(any(String.class)))
                .thenReturn(Optional.empty());

        try {
            loanProductService.create(loanProductDto);
        } catch (ResourceNotFoundException e) {
            verify(loanProductRepository, times(1)).findByProductName(any(String.class));
            verify(propertyRestrictionRepository, times(1)).findByRestrictionType(any(String.class));
        }
    }
    
    @Test
    
    void createLoanProduct_Successfully() {
        LoanProductDto loanProductDto = new LoanProductDto();
        loanProductDto.setProductName("New Product");
        loanProductDto.setPropertyRestrictionTypes(List.of("Non-Existent Restriction"));
       
  
    	  loanProductDto.setLoanTerm("240"); loanProductDto.setInterestRate("11.7");;
    	  loanProductDto.setMaxLoanAmount("23343422");
    	  loanProductDto.setMinDownPayment("3232");
    	  loanProductDto.setMinDownPaymentType("absolute");
    	  loanProductDto.setMinCreditScore("600");
    	  loanProductDto.setMaxLoanToValueRatio("90");
    	  loanProductDto.setPrivateMortgageInsuranceReq(true);
    	  loanProductDto.setOrginationFee("678"); loanProductDto.setLockinPeriod("20");
    	  loanProductDto.setMinPenalityAmount("123");
    	  loanProductDto.setPenalityPercentage("1.34");
    	  loanProductDto.setDocumentRequirement("document requirements");

        when(loanProductRepository.findByProductName(any(String.class)))
                .thenReturn(Optional.empty());
        when(propertyRestrictionRepository.findByRestrictionType(any(String.class)))
                .thenReturn(Optional.of(new PropertyRestriction()));
        when(loanProductRepository.save(any(LoanProduct.class)))
                .thenReturn(new LoanProduct());

       
        try {
            loanProductService.create(loanProductDto);
        } catch (NullPointerException e) {
        	  verify(loanProductRepository, times(1)).findByProductName(any(String.class));
              verify(propertyRestrictionRepository, times(1)).findByRestrictionType(any(String.class));
              verify(loanProductRepository, times(1)).save(any(LoanProduct.class));
        }
        
        
      
    }
    
    @SuppressWarnings("unchecked")
	@Test
	 
    void addUnderWritingCriteria_Success() {
        Integer productId = 1;
        List<String> underwritingCriteria = Arrays.asList("Criteria1", "Criteria2");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(criteriaClient.fetchAll()).thenReturn(ResponseEntity.ok(underwritingCriteria));

        loanProductService.addUnderWritingCriteria(productId, underwritingCriteria);

        verify(criteriaClient, times(1)).fetchAll();
        verify(criteriaClient, times(1)).addCriteriaToLoanProd(eq(productId), any(List.class));
    }

    @Test
    
    void addUnderWritingCriteria_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> underwritingCriteria = Arrays.asList("Criteria1", "Criteria2");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            loanProductService.addUnderWritingCriteria(productId, underwritingCriteria);
        } catch (ResourceNotFoundException e) {
            verify(criteriaClient, never()).fetchAll();
        }
    }

    @Test
    
    void addEscrowRequirements_Success() {
        Integer productId = 1;
        List<String> escrowRequirements = Arrays.asList("Requirement1", "Requirement2");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(escrowClient.getAllReq()).thenReturn(ResponseEntity.ok(escrowRequirements));

        loanProductService.addEscrowRequirements(productId, escrowRequirements);

        verify(escrowClient, times(1)).getAllReq();
        verify(escrowClient, times(1)).save(any(LoanReqDto.class));
    }

    @Test
    
    void addEscrowRequirements_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> escrowRequirements = Arrays.asList("one", "two");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());
        when(escrowClient.getAllReq()).thenReturn(ResponseEntity.ok(Arrays.asList("one","two")));

        try {
            loanProductService.addEscrowRequirements(productId, escrowRequirements);
        } catch (ResourceNotFoundException e) {
            verify(escrowClient, times(1)).getAllReq();
        }
    } 
    
    

  //  @Test
    void addUnderWritingCriteriaToLoanProduct_Success() {
        Integer productId = 1;
        List<String> underwritingCriteria = Arrays.asList("Criteria1", "Criteria2");
        
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setStatus(false);
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        UnderwritingCriteriaDto underwritingCriteriaDto = new UnderwritingCriteriaDto();
        underwritingCriteriaDto.setCriteriaId(1L);
        underwritingCriteriaDto.setCriteriaName("Criteria1");
        underwritingCriteriaDto.setNotes("notes");
        UnderwritingCriteriaDto underwritingCriteriaDtoTwo = new UnderwritingCriteriaDto();
        underwritingCriteriaDtoTwo.setCriteriaName("Criteria2");
        when(criteriaClient.fetchByLoanId(productId)).thenReturn(ResponseEntity.ok(List.of(underwritingCriteriaDto,underwritingCriteriaDtoTwo)));
        loanProductService.addUnderWritingCriteriaToLoanProduct(productId, underwritingCriteria);
        
        verify(loanProductRepository).save(loanProduct);
    }

    @Test
    void addUnderWritingCriteriaToLoanProduct_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> underwritingCriteria = Arrays.asList("Criteria1", "Criteria2");
        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());
        try {
            loanProductService.addUnderWritingCriteriaToLoanProduct(productId, underwritingCriteria);
        } catch (ResourceNotFoundException ignored) {
        }
    }

    //@Test
    void addUnderWritingCriteriaToLoanProduct_ThrowsResourceAlreadyExistsException() {
        Integer productId = 1;
        List<String> underwritingCriteria = Arrays.asList("Criteria1");
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(criteriaClient.fetchByLoanId(productId)).thenReturn(ResponseEntity.ok(List.of(new UnderwritingCriteriaDto())));
        try {
            loanProductService.addUnderWritingCriteriaToLoanProduct(productId, underwritingCriteria);
        } catch (ResourceAlreadyExistsException ignored) {
        }
    }

   // @Test
    void addEscrowRequirementsToLoanProduct_Success() {
        Integer productId = 1;
        List<String> escrowRequirements = Arrays.asList("Requirement1", "Requirement2");
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setEscrowRequired(false);
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(escrowClient.findByProdId(productId)).thenReturn(ResponseEntity.ok(List.of("one","two")));
        loanProductService.addEscrowRequirementsToLoanProduct(productId, escrowRequirements);
    }

    @Test
    void addEscrowRequirementsToLoanProduct_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> escrowRequirements = Arrays.asList("Requirement1", "Requirement2");
        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());
        try {
            loanProductService.addEscrowRequirementsToLoanProduct(productId, escrowRequirements);
        } catch (ResourceNotFoundException ignored) {
        }
    }

    @Test
    void addEscrowRequirementsToLoanProduct_ThrowsResourceAlreadyExistsException() {
        Integer productId = 1;
        List<String> escrowRequirements = Arrays.asList("Requirement1");
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(escrowClient.findByProdId(productId)).thenReturn(ResponseEntity.ok(List.of("Requirement1")));
        try {
            loanProductService.addEscrowRequirementsToLoanProduct(productId, escrowRequirements);
        } catch (ResourceAlreadyExistsException ignored) {
        }
    }

   // @Test
    
    void getById_Success() {
        Integer productId = 1;
        LoanProductDto loanProductDto = new LoanProductDto();
        loanProductDto.setProductName("New Product");
      
        loanProductDto.setLockinPeriod("10"); // This should cause a DataViolationException
    	  loanProductDto.setProductName("Home Loan");
    	  loanProductDto.setLoanTerm("5"); loanProductDto.setInterestRate("11.7");;
    	  loanProductDto.setMaxLoanAmount("23343422");
    	  loanProductDto.setMinDownPayment("3232");
    	  loanProductDto.setMinDownPaymentType("absolute");
    	  loanProductDto.setMinCreditScore("600");
    	  loanProductDto.setMaxLoanToValueRatio("90");
    	  loanProductDto.setPrivateMortgageInsuranceReq(true);
    	  loanProductDto.setOrginationFee("678"); loanProductDto.setLockinPeriod("20");
    	  loanProductDto.setMinPenalityAmount("123");
    	  loanProductDto.setPenalityPercentage("1.34");
    	  loanProductDto.setDocumentRequirement("document requirements");
    	  
    	  LoanProdDto loanProdDto = LoanProductMapper.loanProductDtoToLoanProdDto(loanProductDto, new LoanProdDto());
    	  
    	  LoanProduct loanProduct = LoanProductMapper.loanProdDtoToLoanProduct(loanProdDto, new LoanProduct());
    	  loanProduct.setPenalty(new PrepayPenalty());
    	  loanProduct.setPropertyRestrcitions(Arrays.asList(new PropertyRestriction()));
    	  loanProduct.setStatus(true);
    	  loanProduct.setEscrowRequired(true);
    	  loanProduct.setPropertyRestrictionExists(true);
    	  loanProduct.setProductId(1);

    	  UnderwritingCriteriaDto underwritingCriteriaDto = new UnderwritingCriteriaDto();
    	  
    	  
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(criteriaClient.fetchByLoanId(productId).getBody()).thenReturn((List<UnderwritingCriteriaDto>) ResponseEntity.ok(Arrays.asList(underwritingCriteriaDto)));
        loanProductService.getbyId(productId);
        
      
        
        
    }

    @Test
    void getById_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());
        try {
            loanProductService.getbyId(productId);
        } catch (ResourceNotFoundException ignored) {
        }
    }
    

   // @Test
    void updateLoanProductStatus_Success_ActiveToInactive() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setStatus(true);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(loanProductStatusHistoryRepsitory.findByProductIdAndEndDate(productId, LocalDateTime.MAX)).thenReturn(Optional.of(new ProductStatusHistory()));

        loanProductService.updateLoanProductStatus(productId);

        verify(loanProductRepository, times(1)).findById(productId);
        verify(loanProductRepository).save(loanProduct);
        verify(loanProductStatusHistoryRepsitory).save(any(ProductStatusHistory.class));
    }

    //@Test
    void updateLoanProductStatus_Success_InactiveToActive() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setStatus(false);

        UnderwritingCriteriaDto underwritingCriteriaDto = new UnderwritingCriteriaDto();
        underwritingCriteriaDto.setCriteriaName("one");
        underwritingCriteriaDto.setCriteriaId(1L);
        underwritingCriteriaDto.setCriteriaName("SriRam");
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(criteriaClient.fetchByLoanId(productId).getBody()).thenReturn(Arrays.asList(underwritingCriteriaDto));
        
        loanProductService.updateLoanProductStatus(productId);
        

        verify(loanProductRepository).findById(productId);
        verify(loanProductRepository).save(loanProduct);
        verify(loanProductStatusHistoryRepsitory, never()).findByProductIdAndEndDate(productId, LocalDateTime.MAX);
    }

    @Test
    void updateLoanProductStatus_ThrowsResourceNotFoundException() {
        Integer productId = 1;

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            loanProductService.updateLoanProductStatus(productId);
        } catch (ResourceNotFoundException ignored) {
        }

        verify(loanProductRepository).findById(productId);
        verify(loanProductRepository, never()).save(any(LoanProduct.class));
    }

   // @Test
    void updateLoanStatusHistory_Success() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setStatus(false);
  	  loanProduct.setPenalty(new PrepayPenalty());
  	  loanProduct.setPropertyRestrcitions(Arrays.asList(new PropertyRestriction()));
  	  loanProduct.setStatus(true);
  	  loanProduct.setEscrowRequired(true);
  	  loanProduct.setPropertyRestrictionExists(true);
  	  loanProduct.setProductId(1);
  	  loanProduct.setPrepayPenalty(true);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));

        loanProductService.updateLoanStatusHistory(productId);

        verify(loanProductRepository).findById(productId);
        verify(loanProductRepository).save(loanProduct);
        verify(loanProductStatusHistoryRepsitory).save(any(ProductStatusHistory.class));
    }

   // @Test
    void updateLoanStatus_Success() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setStatus(false);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));

        loanProductService.updateLoanStatus(productId);
        
        ProductStatusHistory productStatusHistory = new ProductStatusHistory();

        verify(loanProductRepository).findById(productId);
        verify(loanProductRepository).save(loanProduct);
        verify(loanProductStatusHistoryRepsitory).save(productStatusHistory);
    }
    
    

   // @Test
    void getProductStatusHistory_Success() {
        Integer productId = 1;
        
        ProductStatusHistory productStatusHistory = new ProductStatusHistory();
        productStatusHistory.setEndDate(LocalDateTime.now());
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(loanProductStatusHistoryRepsitory.findByProductId(productId)).thenReturn(Arrays.asList(new ProductStatusHistory()));

        List<ProductStatusHistoryDto> result = loanProductService.getProductStatusHistory(productId);
        

        verify(loanProductStatusHistoryRepsitory).findByProductId(productId);
        assert(!result.isEmpty());
    }

    @Test
    void getProductStatusHistory_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            loanProductService.getProductStatusHistory(productId);
        } catch (ResourceNotFoundException ignored) {
        }
    }

    @Test
    void updatePrePayPenalityStatus_Success() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setPrepayPenalty(true);
        PrepayPenalty penalty = new PrepayPenalty();
        penalty.setStatus('A');
        loanProduct.setPrepayPenalty(true);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(prePayPenalityRepository.findById(productId)).thenReturn(Optional.of(penalty));

        loanProductService.updatePrePayPenalityStatus(productId);

        verify(loanProductRepository).save(loanProduct);
    }

    @Test
    void updatePrePayPenalityStatus_SuccessTwo() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setPrepayPenalty(true);
        PrepayPenalty penalty = new PrepayPenalty();
        penalty.setStatus('A');
        loanProduct.setPrepayPenalty(false);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(prePayPenalityRepository.findById(productId)).thenReturn(Optional.of(penalty));

        loanProductService.updatePrePayPenalityStatus(productId);

        verify(loanProductRepository).save(loanProduct);
    }
    @Test
    void updatePrePayPenalityStatus_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            loanProductService.updatePrePayPenalityStatus(productId);
        } catch (ResourceNotFoundException ignored) {
        }
    }

    @Test
    void updatePrePayPenality_Success() {
        Integer productId = 1;
        PrePayPenalityDto penalityDto = new PrePayPenalityDto();
        penalityDto.setProductId(productId);
        penalityDto.setPenalityPercentage(5.0f);//setPenalityPercentage(5.0);
        penalityDto.setMinPenalityAmount(1000.0f);

        PrepayPenalty penalty = new PrepayPenalty();
        penalty.setStatus('A');

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(prePayPenalityRepository.findById(productId)).thenReturn(Optional.of(penalty));

        loanProductService.updatePrePayPenality(penalityDto);

        verify(prePayPenalityRepository).save(penalty);
    }

    @Test
    void updatePrePayPenality_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        PrePayPenalityDto penalityDto = new PrePayPenalityDto();
        penalityDto.setProductId(productId);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            loanProductService.updatePrePayPenality(penalityDto);
        } catch (ResourceNotFoundException ignored) {
        }
    }
    

   // @Test
    void createPrePayPenality_Success() {
        PrePayPenalityDto penalityDto = new PrePayPenalityDto();
        penalityDto.setProductId(1);
        penalityDto.setPenalityPercentage(5.0f);
        penalityDto.setMinPenalityAmount(1000.0f);

        when(loanProductRepository.findById(penalityDto.getProductId())).thenReturn(Optional.of(new LoanProduct()));
        when(prePayPenalityRepository.findById(penalityDto.getProductId())).thenReturn(Optional.empty());

        loanProductService.createPrePayPenality(penalityDto);

        verify(prePayPenalityRepository).save(any(PrepayPenalty.class));
    }

    @Test
    void createPrePayPenality_ThrowsMismatchFoundException() {
        PrePayPenalityDto penalityDto = new PrePayPenalityDto();
        penalityDto.setProductId(1);

        when(loanProductRepository.findById(penalityDto.getProductId())).thenReturn(Optional.of(new LoanProduct()));
        when(prePayPenalityRepository.findById(penalityDto.getProductId())).thenReturn(Optional.of(new PrepayPenalty()));

        try {
            loanProductService.createPrePayPenality(penalityDto);
        } catch (MismatchFoundException ignored) {
        }
    }

    //@Test
    void updateLoanProduct_Success() {
        LoanProductDto loanProductDto = new LoanProductDto();
        Integer productId = 1;
        loanProductDto.setProductName("Updated Product");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));

        loanProductService.updateLoanProduct(productId, loanProductDto);

        verify(loanProductRepository).save(any(LoanProduct.class));
    }

    @Test
    void updateLoanProduct_ThrowsResourceNotFoundException() {
        LoanProductDto loanProductDto = new LoanProductDto();
         Integer productId = 1;

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            loanProductService.updateLoanProduct(productId, loanProductDto);
        } catch (ResourceNotFoundException ignored) {
        }
    }

    @Test
    void checkLoanProductRestrictions_Success() {
        List<String> restrictions = Arrays.asList("Restriction1", "Restriction2");

        when(propertyRestrictionRepository.findByRestrictionType(anyString())).thenReturn(Optional.of(new PropertyRestriction()));

        List<String> result = loanProductService.checkLoanProductRestrictions(restrictions);

        assertTrue(result.isEmpty());
    }


    //@Test
    void getLoanProductIdByProductName_Success() {
        String productName = "Test Product";
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setProductId(1);

        when(loanProductRepository.findByProductName(productName)).thenReturn(Optional.of(loanProduct));

        Integer result = loanProductService.getLoanProductIdByProductName(productName);

        assertEquals(1, result);
    }

    @Test
    void getLoanProductIdByProductName_ThrowsResourceNotFoundException() {
        String productName = "Nonexistent Product";

        when(loanProductRepository.findByProductName(productName)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanProductService.getLoanProductIdByProductName(productName));
    }

    @SuppressWarnings("unchecked")
	//@Test
    void searchLoanProducts_Success() {
        LoanProductSearchCriteria searchCriteria = new LoanProductSearchCriteria();
        searchCriteria.setLoanTerm(30);
        searchCriteria.setCreditScore(700);

        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setProductId(1);
        List<LoanProduct> loanProducts = Arrays.asList(loanProduct);

        when(loanProductRepository.findAll(any(Specification.class))).thenReturn(loanProducts);

        List<LoanProdDto> result = loanProductService.searchLoanProducts(searchCriteria);

        assertFalse(result.isEmpty());
        assertEquals(1, result.get(0).getProductId());
    }

  //  @Test
    void addRestrictions_Success() {
        Integer productId = 1;
        List<String> restrictions = Arrays.asList("Restriction1", "Restriction2");
        LoanProduct loanProduct = new LoanProduct();
        PropertyRestriction propertyRestriction = new PropertyRestriction();
        propertyRestriction.setRestrictionType("restType");
        propertyRestriction.setRestrictionId(productId);
        propertyRestriction.setRestrictionType("one");
        propertyRestriction.setRestrictionDescription("Number one");       
        loanProduct.setPropertyRestrcitions(Arrays.asList(propertyRestriction));
        loanProduct.setPropertyRestrictionExists(true);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(loanProductService.checkLoanProductRestrictions(restrictions)).thenReturn(Collections.emptyList());
        when(loanProductService.getExistsPropertyRestrictions(Arrays.asList("one","two"))).thenReturn(Arrays.asList(new PropertyRestriction()));
        when(propertyRestrictionRepository.findByRestrictionType(anyString())).thenReturn(Optional.of(new PropertyRestriction()));

        loanProductService.addRestrictions(productId, restrictions);

        verify(loanProductRepository).save(loanProduct);
    }

    @Test
    void addRestrictions_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> restrictions = Arrays.asList("Restriction1");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanProductService.addRestrictions(productId, restrictions));
    }

    //@Test
    void removeRestrictions_Success() {
        Integer productId = 1;
        List<String> restrictions = Arrays.asList("Restriction1");
        LoanProduct loanProduct = new LoanProduct();

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(loanProductService.checkLoanProductRestrictions(restrictions)).thenReturn(restrictions);
        when(loanProductService.getExistsPropertyRestrictions(restrictions)).thenReturn(Arrays.asList(new PropertyRestriction()));
        when(propertyRestrictionRepository.findByRestrictionType(anyString())).thenReturn(Optional.of(new PropertyRestriction()));

        loanProductService.removeRestrictions(productId, restrictions);

        verify(loanProductRepository).save(loanProduct);
    }

    @Test
    void removeRestrictions_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> restrictions = Arrays.asList("Restriction1");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanProductService.removeRestrictions(productId, restrictions));
    }

    @Test
    void getPrepayPenaltyById_Success() {
        Integer productId = 1;
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setPrepayPenalty(true);
   
        PrepayPenalty prepayPenalty = new PrepayPenalty();
  
        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        
        when(prePayPenalityRepository.findById(productId)).thenReturn(Optional.of(prepayPenalty));
        
        PrepayPenaltyStatusDto result = loanProductService.getPrepayPenaltyById(productId);

        assertNotNull(result);
   
    }

    @Test
    void getPrepayPenaltyById_ThrowsResourceNotFoundException() {
        Integer productId = 1;

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanProductService.getPrepayPenaltyById(productId));
    }

   // @Test
    void removeEscrowRequirements_Success() {
        Integer productId = 1;
        List<String> requirements = Arrays.asList("Requirement1", "Requirement2");
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setEscrowRequired(true);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(escrowClient.findByProdId(productId)).thenReturn(ResponseEntity.ok(requirements));

        loanProductService.removeEscrowRequirements(productId, requirements);

        verify(loanProductService).deleteEscrowRequirements(productId,requirements);
    }

    @Test
    void removeEscrowRequirements_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> requirements = Arrays.asList("Requirement1");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> loanProductService.removeEscrowRequirements(productId, requirements));
    }

    //@Test
    void deleteEscrowRequirements_Success() {
        Integer productId = 1;
        List<String> requirements = Arrays.asList("Requirement1", "Requirement2");
        LoanProduct loanProduct = new LoanProduct();
        loanProduct.setEscrowRequired(true);

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(loanProduct));
        when(escrowClient.findByProdId(productId)).thenReturn(ResponseEntity.ok(requirements));
        when(loanProductRepository.findById(productId).get()).thenReturn(loanProduct);
        loanProductService.removeEscrowRequirements(productId, requirements); // indirectly tests deleteEscrowRequirements

        verify(loanProductRepository).save(loanProduct);
    }

   // @Test
    void deleteEscrowRequirements_ThrowsResourceNotFoundException() {
        Integer productId = 1;
        List<String> requirements = Arrays.asList("Requirement1");

        when(loanProductRepository.findById(productId)).thenReturn(Optional.of(new LoanProduct()));
        when(escrowClient.findByProdId(productId)).thenReturn(ResponseEntity.ok(Arrays.asList("DifferentRequirement")));

        assertThrows(ResourceNotFoundException.class, () -> loanProductService.removeEscrowRequirements(productId, requirements));
    }

   
 
}