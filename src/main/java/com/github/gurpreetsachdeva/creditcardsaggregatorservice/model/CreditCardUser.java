package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class CreditCardUser {

	@Override
	public String toString() {
		return "{\"creditScore\":" + creditScore + ",\"name\":\"" + name + "\",\"salary\":" + salary + "}";
	}

	private int creditScore;
	private String name;
	private int salary;
	
	

	public CreditCardUser(int creditScore, String name, int salary) {
		super();
		this.creditScore = creditScore;
		this.name = name;
		this.salary = salary;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}