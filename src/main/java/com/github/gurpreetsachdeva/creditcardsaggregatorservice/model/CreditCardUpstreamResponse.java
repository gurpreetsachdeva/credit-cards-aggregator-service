package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCardUpstreamResponse {
	
			
	private String cardName;
	private double apr;
	private double eligibility;
	private double approvalRating;
	private String card;
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public double getApr() {
		return apr;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
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
	public double getApprovalRating() {
		return approvalRating;
	}
	public void setApprovalRating(double approvalRating) {
		this.approvalRating = approvalRating;
	}
	

}
