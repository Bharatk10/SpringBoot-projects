package com.zm.cards.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.zm.cards.dto.CardsDto;
import com.zm.cards.entity.Cards;
import com.zm.cards.exception.ResourceNotFoundException;
import com.zm.cards.mapper.CardsMapper;
import com.zm.cards.repo.CardsRepo;
import com.zm.cards.service.CardsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService{
	
	private CardsRepo cardsRepo;


	@Override
	public void createCards(String mobileNum) {

		Cards cards = new Cards();
		
		cards.setCardNum(1000000+new Random().nextInt(990900)+"");
		cards.setAmountUsed(0);
		cards.setAvailableAmount(60000);
		cards.setCardType("CREDIT");
		cards.setMobileNum(mobileNum);
		cards.setTotalLimit(60000);
	
		
		cardsRepo.save(cards);
		
		
	}


	@Override
	public CardsDto getCard(String mobileNum) {

		Cards card = cardsRepo.findByMobileNum(mobileNum).orElseThrow(()->new ResourceNotFoundException("Card ", "mibile number", mobileNum));
		
		 CardsDto cardDto= CardsMapper.mapToCardDto(new CardsDto(),card);
		
		return cardDto;
	}


	@Override
	public void updateCard(@Valid CardsDto cardsDto) {


		Cards card = cardsRepo.findByMobileNum(cardsDto.getMobileNum()).orElseThrow(()->new ResourceNotFoundException("cards ", "Mobile Number", cardsDto.getMobileNum()));
		
		card.setAmountUsed(cardsDto.getAmountUsed());
		card.setAvailableAmount(cardsDto.getAmountUsed());
		card.setCardNum(cardsDto.getCardNum());
		card.setCardType(cardsDto.getCardType());
		card.setTotalLimit(cardsDto.getTotalLimit());
		
		cardsRepo.save(card);
		
		
		
	}


	@Override
	public void deleteCard(String mobileNum) {

		Cards card = cardsRepo.findByMobileNum(mobileNum).orElseThrow(()->new ResourceNotFoundException("Card ", "mibile number", mobileNum));
		
		cardsRepo.delete(card);
		
	}

}
