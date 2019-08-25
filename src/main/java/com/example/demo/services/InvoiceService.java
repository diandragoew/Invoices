package com.example.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Invoice;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class InvoiceService {
	@Autowired
	private UserRepository userRepository;

	public Set<Invoice> returnInvoices(final List<Long> nomeraNaPorychki, final String name) throws Exception {

		final User user = userRepository.findByName(name);

		if (user == null) {
			throw new Exception("losh user");
		}

		final Set<Invoice> invoicesNaKonkretniPorychki = new HashSet<>();
		for (final long nomerNaPorychka : nomeraNaPorychki) {
			for (final Invoice invoice : user.getInvoices()) {
				for (final Order order : invoice.getOrders()) {
					if (order.getZaDelivID() == nomerNaPorychka) {
						invoicesNaKonkretniPorychki.add(invoice);
					}
				}
			}
		}
		return invoicesNaKonkretniPorychki;
	}
}
