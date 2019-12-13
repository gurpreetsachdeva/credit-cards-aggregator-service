package com.github.gurpreetsachdeva.creditcardsaggregatorservice.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

public interface ICardAggregatorService {

	List<CreditCardResponse> getCardsFromDifferentProviders(CreditCardUser user) throws JsonMappingException, JsonProcessingException;
	
}
