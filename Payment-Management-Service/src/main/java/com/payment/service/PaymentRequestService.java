package com.payment.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.payment.Repository.PaymentRepository;
import com.payment.Repository.SequenceRepository;
import com.payment.model.Invoice;
import com.payment.model.PaymentData;
import com.payment.model.Show;

@Service
public class PaymentRequestService {
	
	// ***************** Default Values from application.properties ***************** //
	
	@Value("${sequence.key")
	private String counterKey;
	
	@Value("${payment.prefix}")
	private String paymentPrefix;
	
	@Value("${payment.status.paid}")
	private String paidStatus;
	
	@Value("${payment.status.unpaid}")
	private String unpaidStatus;
	
	// ******************************************************************************* //
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	SequenceRepository sequenceRepository;
	
	@Autowired
	Logger logger;
	
	// Get Invoice By Id
	public PaymentData getInvoiceById(String id)
	{
		try
		{
			PaymentData data = paymentRepository.findById(id).get();
			return data;
		}
		catch(IllegalArgumentException e)
		{
			logger.error(e.getMessage());
			return null;
		}
		catch(NoSuchElementException e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
	
	// Get Invoice By Status - PAID/UNPAID
	public List<PaymentData> getInvoiceByStatus(String status)
	{
		try
		{
			return paymentRepository.findByStatus(status);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
	
	// Get All Invoices
	public List<PaymentData> getAllInvoices()
	{
		List<PaymentData> data = paymentRepository.findAll();
		return data;
	}
	
	// Update Status
	public Show updateStatus(PaymentData data)
	{
		try
		{
			paymentRepository.save(data);
			return new Show(data.getId()," Updated Successfully!");
		}
		catch(IllegalArgumentException e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
	
	// QUEUE
	public void rabbitQueueConsumer(Invoice invoice)
	{
		try
		{
			PaymentData data = new PaymentData();
			long seqId = sequenceRepository.getNextSequenceId(counterKey);
			String id = paymentPrefix + seqId;
			
			data.setId(id);
			data.setInvoice(invoice);
			data.setStatus(unpaidStatus);
			paymentRepository.save(data);
			
		}
		catch(IllegalArgumentException e)
		{
			logger.error(e.getMessage());
		}		
	}
	
	// Delete Invoice By Id
	public Show deleteInvoiceById(String id)
	{
		try
		{
			paymentRepository.deleteById(id);
			return new Show("Invoice with Id -"+id," Deleted!");
		}
		catch(IllegalArgumentException e)
		{
			logger.error(e.getMessage());
			return new Show("Exception Occured", e.getLocalizedMessage());
		}
	}
	
	
}
