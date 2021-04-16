package com.payment.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.stripe.model.Customer;

@Document(collection="StripeData")
public class StripeData {
	
	private String id;
	private Customer customer;
	
	public StripeData()
	{
		super();
	}
	
	public StripeData(String id, Customer customer) {
		super();
		this.id = id;
		this.customer = customer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
