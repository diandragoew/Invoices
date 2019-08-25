package com.example.demo.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Invoice;
import com.example.demo.services.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/")
	public String welcome(final HttpServletRequest request) {
		final Principal principal = request.getUserPrincipal();
		return "Welcome " + principal.getName();
	}

	@PostMapping("/invoices")
	public Set<Invoice> getInvoices(final HttpServletRequest request, @RequestBody final List<Long> nomeraNaZaqvki) {
		final Principal principal = request.getUserPrincipal();
		try {
			return invoiceService.returnInvoices(nomeraNaZaqvki, principal.getName());
		} catch (final Exception e) {
			return new HashSet<>();
		}

	}

}
