package com.hotel.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.model.PaymentData;
import com.hotel.model.Show;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

@Service
public class PaymentService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Logger logger;
	
	public String paymentApi = "payment-management-service";
	
	// ************************* API Calls To Payment Management Service ********************* //
	
	public String GET_INVOICE_BY_ID = "http://" + paymentApi + "/getInvoiceById/{id}";
	public String GET_INVOICE_BY_STATUS = "http://" + paymentApi + "/getInvoiceByStatus/{status}";
	public String GET_ALL_INVOICES = "http://" + paymentApi + "/getAllInvoices";
	public String UPDATE_STATUS = "http://" + paymentApi + "/updateInvoiceStatus";
	public String DELETE_INVOICE_BY_ID = "http://" + paymentApi + "/deleteInvoiceById/{id}";
	
	public String CREATE_CUSTOMER = "http://" + paymentApi + "/payment/createCustomer";
	public String CHARGE_CREDITCARD = "http://" + paymentApi + "/payment/chargeCustomer";
	
	// **************************************************************************************** //
	
	public Customer createCustomer(String token, String email, String invoiceId)
	{
		
			String url = CREATE_CUSTOMER;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			
			HttpEntity<Customer> httpEntity = new HttpEntity<>(headers);
			
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("token", token)
					.queryParam("email", email)
					.queryParam("invoiceId", invoiceId);
			
			ResponseEntity<Customer> responseEntity = restTemplate.exchange(uriBuilder.toString(),HttpMethod.GET,httpEntity,Customer.class);
			return responseEntity.getBody();
	}
	
	public Charge chargeCreditCard(String invoiceId, int amount)
	{
		String url = CHARGE_CREDITCARD;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<Customer> httpEntity = new HttpEntity<>(headers);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("invoiceId", invoiceId)
			    .queryParam("amount", amount);
		
		ResponseEntity<Charge> responseEntity = restTemplate.exchange(uriBuilder.toString(),HttpMethod.GET,httpEntity, Charge.class);
		return responseEntity.getBody();
	}
	
	public PaymentData getInvoiceById(String id) 
	{	 
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 String url = GET_INVOICE_BY_ID;

		 HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		 ResponseEntity<PaymentData> responseEntity = restTemplate.exchange(url,HttpMethod.GET, httpEntity,PaymentData.class,id);
		 return responseEntity.getBody();	 
	 }
	
	public List<PaymentData> getInvoiceByStatus(String status)
	{
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 String url = GET_INVOICE_BY_STATUS;
		
		 HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		 ResponseEntity<PaymentData[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET, httpEntity,PaymentData[].class,status);
		 PaymentData[] allInvoices = responseEntity.getBody();
		
		 ObjectMapper mapper = new ObjectMapper();
		 List<PaymentData> list = Arrays.stream(allInvoices).map(p->mapper.convertValue(p, PaymentData.class)).collect(Collectors.toList());
		 return list;
	 }
	
	public List<PaymentData> getAllInvoices(){

		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(GET_ALL_INVOICES, Object[].class);
		ObjectMapper mapper = new ObjectMapper();
		List<PaymentData> list = Arrays.stream(responseEntity.getBody())
				.map(obj->mapper.convertValue(obj, PaymentData.class)).collect(Collectors.toList());
		
		return list;
	}
	
	 public Show updateInvoiceStatus(PaymentData paymentData) 
	 {
		 if(paymentData.getId() == null)
		 { 
			 return new Show("Invalid Request! "," Enter a Proper Id");
		 }
		 
		 RequestEntity<PaymentData> requestEntity = RequestEntity.put(UPDATE_STATUS).accept(MediaType.APPLICATION_JSON).body(paymentData);
		 ResponseEntity<Show> responseEntity = restTemplate.exchange(requestEntity, Show.class);
		 return responseEntity.getBody();
	 }
	
	 public Show deleteInvoiceById(String id)
	 {
		 HttpHeaders headers = new HttpHeaders();
		 String url = DELETE_INVOICE_BY_ID;
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		 
		 ResponseEntity<Show> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Show.class,id);
		 return responseEntity.getBody();
	 }
	
}
