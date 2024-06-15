package com.zm.cards.service;

import com.zm.cards.dto.CardsDto;

import jakarta.validation.Valid;

public interface CardsService {

	void createCards(String mobileNum);

	CardsDto getCard(String mobileNum);

	void updateCard(@Valid CardsDto cardsDto);

	void deleteCard(String mobileNum);

}
