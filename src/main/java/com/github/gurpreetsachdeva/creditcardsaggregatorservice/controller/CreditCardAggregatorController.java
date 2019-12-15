package com.github.gurpreetsachdeva.creditcardsaggregatorservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.service.ICardAggregatorService;

@RestController
public class CreditCardAggregatorController {
	
	@Autowired
	private ICardAggregatorService cardService;
	
    @Autowired
    public void setCardService(ICardAggregatorService cardService) {
        this.cardService = cardService;
    }
	
    private static final Logger logger = LoggerFactory.getLogger(CreditCardAggregatorController.class);



	@PostMapping(
			  value = "/creditcards", consumes = "application/json", produces = "application/json")
	List<CreditCardResponse> getCards(@RequestBody CreditCardUser user) {
		
        logger.info("Credit Card User: {}",user);

		List<CreditCardResponse> cards = new ArrayList<>();

		try {
			cards=cardService.getCardsFromDifferentProviders(user);
			logger.info("Cards size:{}",cards.size());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cards;
	}

}
