package com.zm.cards.mapper;

import com.zm.cards.dto.CardsDto;
import com.zm.cards.entity.Cards;

public class CardsMapper {

	public static CardsDto mapToCardDto(CardsDto cardsDto, Cards card) {
		
		cardsDto.setAmountUsed(card.getAmountUsed());
		cardsDto.setAvailableAmount(card.getAvailableAmount());
		cardsDto.setCardNum(card.getCardNum());
		cardsDto.setCardType(card.getCardType());
		cardsDto.setMobileNum(card.getMobileNum());
		cardsDto.setTotalLimit(card.getTotalLimit());

		
		
		
		return cardsDto;
	}

}
