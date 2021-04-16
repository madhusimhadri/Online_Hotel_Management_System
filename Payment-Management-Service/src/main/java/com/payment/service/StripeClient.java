package com.payment.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.payment.Repository.StripeRepository;
import com.payment.model.PaymentData;
import com.payment.model.StripeData;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

@Service
public class StripeClient {

	@Value("${payment.status.paid}")
	private String paidStatus;
	
	@Autowired
	PaymentRequestService paymentReqService;
	
	@Autowired
	StripeRepository stripeRepository;
	
	// Stripe is one of the most popular, easy to use and fast to integrate payment service
	
	@Autowired
	StripeClient()
	{
		String secretKey = "sk_test_51Ig075SJ9Hq1XQJH1GQZz7OHpG50MZDX4hY9Cs0ly7YPTwodlLSyyWqoDvOgMEllZF3sWKXiFmT6GPFG2yks1alY00aT3qXTeB";
		Stripe.apiKey = secretKey;
	}
	
	// To Save Users Credit Card on Stripe Side- create customer
	public Customer createCustomer(String token, String email, String invoiceId) throws Exception
	{
		Map<String, Object> customerParams = new HashMap<String,Object>();
		customerParams.put("email", email);
		customerParams.put("source", token);
		
		StripeData customerData = new StripeData();
		Customer customer = Customer.create(customerParams);
		customerData.setId(invoiceId);
		customerData.setCustomer(customer);
		
		stripeRepository.save(customerData);
		return customer;
	}
	
	/*
	In the constructor we assigned apiKey to Stripe. Insted of Publishable key we are using Secret key here.
	By doing this we’re telling Stripe to add this key to each request that we are making.
	so there is no need to add api key manually. 
	*/
	public Charge chargeCreditCard(String invoiceId, int amount) throws Exception
	{
		StripeData 	customerData = stripeRepository.findById(invoiceId).get();
		Customer stripeCustomer = customerData.getCustomer();
		
		String id = stripeCustomer.getId();
		String sourceCard = Customer.retrieve(id).getDefaultSource();
		
		Map<String,Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", "USD");
		chargeParams.put("customer", invoiceId);
		chargeParams.put("source", sourceCard);
		
		Charge charge = Charge.create(chargeParams);
		
		PaymentData invoiceData = paymentReqService.getInvoiceById(invoiceId);
		invoiceData.setStatus(paidStatus);
		paymentReqService.updateStatus(invoiceData);
		
		return charge;
	}
	
	
	
}
