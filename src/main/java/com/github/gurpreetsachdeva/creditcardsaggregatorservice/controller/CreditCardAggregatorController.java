package com.github.gurpreetsachdeva.creditcardsaggregatorservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

@RestController
public class CreditCardAggregatorController {


	@PostMapping(
			  value = "/creditcards", consumes = "application/json", produces = "application/json")
	List<CreditCardResponse> newEmployee(@RequestBody CreditCardUser user) {

		List<CreditCardResponse> cards = new ArrayList<>();

		cards.add(new CreditCardResponse(19.4, "ScoredCard Builder", "ScoredCards", 0.212));
		cards.add(new CreditCardResponse(21.4, "SuperSaver Card", "CSCards", 0.137));
		cards.add(new CreditCardResponse(19.2, "SuperSpender Card", "CSCards", 0.135));
		// Call N Services and aggregate based on them
		//
		return cards;
	}

}
