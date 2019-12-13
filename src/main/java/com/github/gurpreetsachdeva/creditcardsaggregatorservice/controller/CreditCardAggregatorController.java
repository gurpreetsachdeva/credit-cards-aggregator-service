package com.github.gurpreetsachdeva.creditcardsaggregatorservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.service.ICardAggregatorService;

@RestController
public class CreditCardAggregatorController {
	
	private ICardAggregatorService cardService;


	@PostMapping(
			  value = "/creditcards", consumes = "application/json", produces = "application/json")
	List<CreditCardResponse> newEmployee(@RequestBody CreditCardUser user) {

		List<CreditCardResponse> cards = new ArrayList<>();

		try {
			cards=cardService.getCardsFromDifferentProviders(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cards;
	}

}
