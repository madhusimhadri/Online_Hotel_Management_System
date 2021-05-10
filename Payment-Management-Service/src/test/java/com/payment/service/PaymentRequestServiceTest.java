package com.payment.service;



import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.payment.Repository.PaymentRepository;
import com.payment.model.Invoice;
import com.payment.model.PaymentData;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PaymentRequestServiceTest {
	
	@Autowired
	private PaymentRequestService paymentService;
	
	@MockBean
	private PaymentRepository paymentRepository;
	
	@Test
	public void testgetInvoiceById()
	{
			/*Optional<PaymentData> invoice = new Invoice();
			invoice.setCompanyName("NOVOTEL");
			invoice.setBookedRoomType("SINGLE");
			invoice.setGuestId("G6");
			invoice.setGuestMobile("7095209696");
			invoice.setGuestName("Yash");
			invoice.setNights(2);
			invoice.setNumberOfPeople(2);
			invoice.setRoomPricePerNight(3000);
			invoice.setTax(5);
			invoice.setTotalBill(6300);
			invoice.setIssuedBy("Madhu");
			
			Mockito.when(paymentRepository.findById("IN1")).thenReturn(invoice);_
			*/
	}
	
	
	@Test
	public void testgetInvoiceByStatus()
	{
		
	}
	
	@Test
	public void testgetIAllInvoices()
	{
		
	}
	
	@Test
	public void testupdateStatus()
	{
		
	}
	
	@Test
	public void testdeleteInvoiceById()
	{
		
	}
	
}
