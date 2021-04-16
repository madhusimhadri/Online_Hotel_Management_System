package com.hotel.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PaymentData")
public class PaymentData {
	
	private String id;
	private Invoice invoice;
	private String status;
	
	public PaymentData()
	{
		super();
	}

	public PaymentData(String id, Invoice invoice, String status) {
		super();
		this.id = id;
		this.invoice = invoice;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
