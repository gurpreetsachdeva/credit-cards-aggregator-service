package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCardResponse {

	private double apr;
	private String name;
	private String provider;
	private double cardScore;
	
	

	public CreditCardResponse(double d, String name, String provider, double e) {
		super();
		this.apr = d;
		this.name = name;
		this.provider = provider;
		this.cardScore = e;
	}

	public String getCardName() {
		return name;
	}

	public void setCardName(String cardName) {
		this.name = cardName;
	}

	public String getCardProviderName() {
		return provider;
	}

	public void setCardProviderName(String cardProviderName) {
		this.provider = cardProviderName;
	}

	public double getApr() {
		return apr;
	}

	public void setApr(double apr) {
		this.apr = apr;
	}

	public double getCardScore() {
		return cardScore;
	}

	public void setCardScore(double cardScore) {
		this.cardScore = cardScore;
	}

}
