package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCardUpstreamResponse {
	
			
	private String cardName;
	private double apr;
	private double eligibility;
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public double getApr() {
		return apr;
	}
	public void setApr(double apr) {
		this.apr = apr;
	}
	public double getEligibility() {
		return eligibility;
	}
	public void setEligibility(double eligibility) {
		this.eligibility = eligibility;
	}
	

}
