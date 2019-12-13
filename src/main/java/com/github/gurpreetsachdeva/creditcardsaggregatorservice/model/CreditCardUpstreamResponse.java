package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCardUpstreamResponse {
	
			
	private String cardName;
	private float apr;
	private float eligibility;
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public float getApr() {
		return apr;
	}
	public void setApr(float apr) {
		this.apr = apr;
	}
	public float getEligibility() {
		return eligibility;
	}
	public void setEligibility(float eligibility) {
		this.eligibility = eligibility;
	}
	

}
