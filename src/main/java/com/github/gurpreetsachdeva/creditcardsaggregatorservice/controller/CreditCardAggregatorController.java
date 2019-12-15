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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "CreditCardAggregatorController", description = "REST APIs for Credit Card Aggregation")
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
	@ApiOperation(value = "Get list of Eligible cards for a user from different microservices", response = Iterable.class, tags = "getCards")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
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
