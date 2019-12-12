package com.github.gurpreetsachdeva.creditcardsaggregatorservice.service;

import java.util.List;

import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCard;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

public interface ICardAggregatorService {

	List<CreditCard> getCardsFromDifferentProviders(CreditCardUser user);
	
}
