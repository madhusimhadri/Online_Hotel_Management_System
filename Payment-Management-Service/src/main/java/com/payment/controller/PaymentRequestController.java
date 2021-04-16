package com.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.model.PaymentData;
import com.payment.model.Show;
import com.payment.service.PaymentRequestService;

@RestController
public class PaymentRequestController {

	@Autowired
	PaymentRequestService paymentReqService;
	
	@GetMapping("/getInvoiceById/{id}")
	public PaymentData getInvoiceById(@PathVariable String id)
	{
		return paymentReqService.getInvoiceById(id);
	}
	
	@GetMapping("/getInvoiceByStatus/{status}")
	public List<PaymentData> getInvoiceByStatus(@PathVariable String status)
	{
		return paymentReqService.getInvoiceByStatus(status);
	}
	
	@GetMapping("/getAllInvoices")
	public List<PaymentData> getAllInvoices()
	{
		return paymentReqService.getAllInvoices();
	}
	
	@PutMapping("/updateInvoiceStatus")
	public Show updateStatus(@RequestBody PaymentData paymentData)
	{
		return paymentReqService.updateStatus(paymentData);
	}
	
	@DeleteMapping("/deleteInvoiceById/{id}")
	public Show deleteInvoiceById(@PathVariable String id)
	{
		return paymentReqService.deleteInvoiceById(id);
	}
	
	
}
