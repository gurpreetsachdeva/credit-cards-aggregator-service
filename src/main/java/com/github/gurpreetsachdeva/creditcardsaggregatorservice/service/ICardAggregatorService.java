package com.github.gurpreetsachdeva.creditcardsaggregatorservice.service;

import java.util.List;

import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

public interface ICardAggregatorService {

	List<CreditCardResponse> getCardsFromDifferentProviders(CreditCardUser user);
	
}
