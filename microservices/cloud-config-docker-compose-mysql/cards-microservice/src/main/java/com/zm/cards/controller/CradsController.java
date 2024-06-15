package com.zm.cards.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zm.cards.dto.CardsDto;
import com.zm.cards.service.CardsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CradsController {
	
private CardsService cardsService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createCards(@RequestParam String mobileNum)
	{
		
		cardsService.createCards(mobileNum);
		
		return ResponseEntity.ok().body("cards is created successfully");
		
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<?> getCards(@RequestParam String mobileNum)
	{
		
		
		
		return ResponseEntity.ok().body(cardsService.getCard(mobileNum));
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCards(@RequestBody @Valid CardsDto cardsDto)
	{
		
		
		cardsService.updateCard(cardsDto);
		
		return ResponseEntity.ok().body("card updated successfully");
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCards(@RequestParam String mobileNum)
	{
		
		
		
		cardsService.deleteCard(mobileNum);
		
		return ResponseEntity.ok().body("card updated successfully");
		
	}
	
	

}
