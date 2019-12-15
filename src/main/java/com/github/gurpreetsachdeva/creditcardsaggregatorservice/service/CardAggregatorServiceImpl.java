package com.github.gurpreetsachdeva.creditcardsaggregatorservice.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.ServiceConstants;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.controller.CreditCardAggregatorController;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUpstreamResponse;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.CreditCardUser;
import com.github.gurpreetsachdeva.creditcardsaggregatorservice.model.UpStreamServiceConfig;

@Component
@Configuration
public class CardAggregatorServiceImpl implements ICardAggregatorService {

	private static final Logger logger = LoggerFactory.getLogger(CreditCardAggregatorController.class);

	@Autowired
	private Environment env;

	@Override
	public List<CreditCardResponse> getCardsFromDifferentProviders(CreditCardUser user)
			 {
		// TODO Auto-generated method stub

		List<CreditCardResponse> cards = new ArrayList<>();

		List<UpStreamServiceConfig> services = readUpstreamConfigs();

		logger.info("Inside Service Layer for fetching records");
		for (UpStreamServiceConfig upstreamService : services) {

			List<CreditCardResponse> upstreamResponse;
			try {
				upstreamResponse = getDataFromUpstream(user, upstreamService);
				logger.info("Upstream Response {}", upstreamResponse);

				cards.addAll(upstreamResponse);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("Service Broke : {}", upstreamService);
			} 

			

		}
		logger.info("Total Objects in Response :{}", cards.size());
		Collections.sort(cards);

		return cards;
	}

	public static void main(String[] args) throws Exception {

		CardAggregatorServiceImpl test = new CardAggregatorServiceImpl();
		CreditCardUser user = new CreditCardUser(700, "John", 28000);
		List<CreditCardResponse> result2 = test.getCardsFromDifferentProviders(user);

		System.out.println(result2.size());
		System.out.println("Exit Successful");
		test.readUpstreamConfigs();

	}

	private List<CreditCardResponse> getDataFromUpstream(CreditCardUser user, UpStreamServiceConfig upstreamService)
			throws JsonMappingException, JsonProcessingException ,IOException{
		final String uri = upstreamService.getUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(ServiceConstants.user_agent,ServiceConstants.browser);

		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put(ServiceConstants.CREDIT_SCORE, user.getCreditScore());
		personJsonObject.put(ServiceConstants.NAME, user.getName());
		personJsonObject.put(ServiceConstants.SCORE, user.getCreditScore());
		personJsonObject.put(ServiceConstants.SALARY, user.getSalary());
	
		HttpEntity<String> entity = new HttpEntity<String>(personJsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
	
		String personResultAsJsonStr = restTemplate.postForObject(uri, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		List<CreditCardUpstreamResponse> res = new ArrayList<>();


		CreditCardUpstreamResponse[] response;
		
			response = mapper.readValue(personResultAsJsonStr,
					CreditCardUpstreamResponse[].class);
			Collections.addAll(res, response);
	

		

		List<CreditCardResponse> resp = responseConverter(res, upstreamService.getNormalizedScale(),
				upstreamService.getField(), upstreamService.getServiceName());

		return resp;
	}

	private List<CreditCardResponse> responseConverter(List<CreditCardUpstreamResponse> cumulativeList,
			int normalizedScale, String field, String upstreamServiceName) {
		List<CreditCardResponse> cards = new ArrayList<>();
		// Make field Dynamic
		double cardScore = ServiceConstants.MIN_CARD_SCORE_USER;
		for (CreditCardUpstreamResponse res : cumulativeList) {
			Object propType = null;
			Object propValue = null;
			// Get the dynamic property
			try {
				propType = PropertyUtils.getPropertyType(res, field);
				logger.info("Type for Property: {}", propType);
				propValue = PropertyUtils.getProperty(res, field);
				logger.info("Value for Property: {}", propType);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cardScore = calculateScore(normalizedScale, res.getApr(), (Double) propValue);
			String name=res.getCardName();
			if(name==null)
				name=res.getCard();

			CreditCardResponse card = new CreditCardResponse(res.getApr(), name, upstreamServiceName,
					cardScore);

			cards.add(card);
		}

		return cards;
	}

	private double calculateScore(int scale, double apr, double field) {

		double cardScore = ServiceConstants.MIN_CARD_SCORE_USER;

		cardScore = field * (Math.pow(apr, ServiceConstants.APR_POWER)) * scale;
		// return Double.valueOf(df.format(cardScore));
		cardScore = Math.floor(cardScore * 1000) / 1000;

		return cardScore;
	}

	private List<UpStreamServiceConfig> readUpstreamConfigs() {
		// TODO Auto-generated method stub
		String keyValue = env.getProperty("service.endpoints");
		
		List<UpStreamServiceConfig> services = new ArrayList<>();
		String[] configUpstream = keyValue.split("#");

		for (String configSplit : configUpstream) {
			String[] config = configSplit.split("\\$");
			logger.info("After breaking the config :{}",config.length);

			UpStreamServiceConfig ups=new UpStreamServiceConfig(config[1], Integer.valueOf(config[2]), config[3], config[0]);
			logger.info("Making Upstream as :{}",ups);

			services.add(ups);
		}

		return services;

	}

}
