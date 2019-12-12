package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCard {

	private float apr;
	private String cardName;
	private String cardProviderName;
	private float cardScore;

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardProviderName() {
		return cardProviderName;
	}

	public void setCardProviderName(String cardProviderName) {
		this.cardProviderName = cardProviderName;
	}

	public float getApr() {
		return apr;
	}

	public void setApr(float apr) {
		this.apr = apr;
	}

	public float getCardScore() {
		return cardScore;
	}

	public void setCardScore(float cardScore) {
		this.cardScore = cardScore;
	}

}
