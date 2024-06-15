package com.zm.cards.repo;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zm.cards.entity.Cards;
import java.util.List;


public interface CardsRepo extends JpaRepository<Cards,Serializable>{
	
	Optional<Cards> findByMobileNum(String mobileNum);

}
