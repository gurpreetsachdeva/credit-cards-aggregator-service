package com.github.gurpreetsachdeva.creditcardsaggregatorservice.service;

import java.util.Arrays;
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
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUpstreamResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;

public class CardAggregatorServiceImpl implements ICardAggregatorService {

	@Override
	public List<CreditCardResponse> getCardsFromDifferentProviders(CreditCardUser user) {
		// TODO Auto-generated method stub
		return null;
		
		
	}
	public static void main(String[] args) throws Exception{
		
		CardAggregatorServiceImpl test=new CardAggregatorServiceImpl();
		test.createEmployee();
		
	}
	
	private void createEmployee() throws JsonMappingException, JsonProcessingException
	{
	    final String uri = "https://app.clearscore.com/api/global/backend-tech-test/v1/cards"; 
	 
	    CreditCardUser user = new CreditCardUser(700, "John Smith", 28000);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");


	    JSONObject personJsonObject = new JSONObject();
	    personJsonObject.put("creditScore", 100);
	    personJsonObject.put("name", "John");
	    //HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
	    
	    HttpEntity<String> entity = new HttpEntity<String>(personJsonObject.toString(), headers);


	    
		 RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> result=restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		// ResponseEntity<String> result=restTemplate.exchange

		 String personResultAsJsonStr = 
			      restTemplate.postForObject(uri, entity, String.class);
		 
	//	 postForObject
		 System.out.println(personResultAsJsonStr);
		 System.out.println(result.getBody());
		 ObjectMapper mapper = new ObjectMapper();
		 
		 CreditCardUpstreamResponse[] response = mapper.readValue(personResultAsJsonStr, CreditCardUpstreamResponse[].class);
		 
	   //restTemplate.exchange()

		 

	 
	 
		 System.out.println(response.length);
		 
		 
		 for (CreditCardUpstreamResponse res : response) {
			 
			 System.out.println(res);
		 }
		 
		 
	
	
	}
	
	     
	     

}

