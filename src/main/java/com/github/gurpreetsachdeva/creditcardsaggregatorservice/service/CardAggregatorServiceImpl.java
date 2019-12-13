package com.github.gurpreetsachdeva.creditcardsaggregatorservice.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.ServiceConstants;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUpstreamResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

public class CardAggregatorServiceImpl implements ICardAggregatorService {

	public String[][] serviceURLs = {
			{ "https://app.clearscore.com/api/global/backend-tech-test/v1/cards", "10", "eligibility","ServiceName" } };

	@Override
	public List<CreditCardResponse> getCardsFromDifferentProviders(CreditCardUser user) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub

		List<CreditCardResponse> cards = new ArrayList<>();

		for (String[] upstreamService : serviceURLs) {

			List<CreditCardResponse> upstreamResponse = getDataFromUpstream(user, upstreamService);

			cards.addAll(upstreamResponse);

		}

		Collections.reverse(cards);
		return cards;

	}

	public static void main(String[] args) throws Exception {

		CardAggregatorServiceImpl test = new CardAggregatorServiceImpl();
		CreditCardUser user = new CreditCardUser(700, "John Smith", 28000);
		test.getDataFromUpstream(user, test.serviceURLs[0]);

	}

	private List<CreditCardResponse> getDataFromUpstream(CreditCardUser user, String[] upstreamService)
			throws JsonMappingException, JsonProcessingException {
		final String uri = upstreamService[0];

		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("creditScore", 100);
		personJsonObject.put("name", "John");
		// HttpEntity<String> request = new
		// HttpEntity<String>(personJsonObject.toString(), headers);

		HttpEntity<String> entity = new HttpEntity<String>(personJsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		// ResponseEntity<String> result=restTemplate.exchange

		String personResultAsJsonStr = restTemplate.postForObject(uri, entity, String.class);

		// postForObject
		System.out.println(personResultAsJsonStr);
		System.out.println(result.getBody());
		ObjectMapper mapper = new ObjectMapper();

		CreditCardUpstreamResponse[] response = mapper.readValue(personResultAsJsonStr,
				CreditCardUpstreamResponse[].class);

		// restTemplate.exchange()
		
	 List<CreditCardUpstreamResponse> res=new ArrayList<>(); 
	 Collections.addAll(res,response );
	 
	 List<CreditCardResponse>resp=responseConverter(res,Integer.valueOf(upstreamService[1]),upstreamService[2],upstreamService[3]);
	 
	 return resp;
	}

	private List<CreditCardResponse> responseConverter(List<CreditCardUpstreamResponse> cumulativeList,
			int normalizedScale, String field, String upstreamServiceName) {
		List<CreditCardResponse> cards = new ArrayList<>();
		// Make field Dynamic
		double cardScore = ServiceConstants.MIN_CARD_SCORE_USER;
		for (CreditCardUpstreamResponse res : cumulativeList) {
			cardScore = calculateScore(normalizedScale, res.getApr(), res.getEligibility());

			CreditCardResponse card = new CreditCardResponse(res.getApr(), res.getCardName(), upstreamServiceName,
					cardScore);

			cards.add(card);
		}

		return cards;
	}

	private double calculateScore(int scale, double apr, double field) {

		double cardScore = ServiceConstants.MIN_CARD_SCORE_USER;

		cardScore = field * (Math.pow(apr, ServiceConstants.APR_POWER)) * scale;
		return Double.valueOf(new DecimalFormat("#.###").format(cardScore));

	}

}

// Swagger Integration
// Parameter passing 
// Test Cases for rest clients and dummy testing
// Readme and deployment
//Time out
// Multithreading at service level
//
