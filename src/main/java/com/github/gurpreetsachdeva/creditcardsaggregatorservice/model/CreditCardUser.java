package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

import io.swagger.annotations.ApiModelProperty;

public class CreditCardUser {

	@ApiModelProperty(notes = "Credit score between 0 and 700", name = "creditScore", required = true, value = "555")
	private Integer creditScore;
	@ApiModelProperty(notes = "Users full name", name = "name", required = true, value = "Gurpreet Sachdeva")
	private String name;
	@ApiModelProperty(notes = "Users annual salary", name = "salary", required = false, value = "100")
	private Integer salary;



	public CreditCardUser(Integer creditScore, String name, Integer salary) {
		super();
		this.creditScore = creditScore;
		this.name = name;
		this.salary = salary;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "{\"creditScore\":" + creditScore + ",\"name\":\"" + name + "\",\"salary\":" + salary + "}";
	}
}
