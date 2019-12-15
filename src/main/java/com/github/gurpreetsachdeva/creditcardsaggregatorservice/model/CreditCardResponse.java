package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

import io.swagger.annotations.ApiModelProperty;

public class CreditCardResponse implements Comparable<CreditCardResponse> {

	@ApiModelProperty(notes = "Annual percentage rate for the card", name = "apr", required = true, value = "21.4")
	private double apr;
	
	@ApiModelProperty(notes = "Name of the credit card product", name = "name", required = true, value = "John Smith")
	private String name;
	
	@ApiModelProperty(notes = "Name of the partner that provides the credit card", name = "provider", required = true, value = "555")
	private String provider;

	@ApiModelProperty(notes = "The score given to the credit card based on the scoring algorithm", name = "cardScore", required = true, value = "0.134")
	private double cardScore;

	public CreditCardResponse(double apr, String name, String provider, double cardScore) {
		super();
		this.apr = apr;
		this.name = name;
		this.provider = provider;
		this.cardScore = cardScore;
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

	@Override
	public int compareTo(CreditCardResponse u) {

		return Double.compare(u.getCardScore(), getCardScore());

	}

}
