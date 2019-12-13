package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCardResponse  implements Comparable<CreditCardResponse>{

	private double apr;
	private String name;
	private String provider;
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
		
		return Double.compare(getCardScore(), u.getCardScore());

	  }

}
