package com.github.gurpreetsachdeva.creditcardsaggregatorservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCard;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

public class CreditCardAggregatorController {
	
	

	  @PostMapping("/creditcards")
	  List<CreditCard> newEmployee(@RequestBody CreditCardUser user) {
	    return null;
	  }

}
