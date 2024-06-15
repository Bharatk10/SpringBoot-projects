package com.zettamine.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.zettamine.accounts.dto.CardsDto;

@Component
public class CardsFallBack implements CardsFeignClient {

	@Override
	public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
		return null;
	}

}
