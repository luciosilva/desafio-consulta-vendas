package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.repositories.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;

	@Transactional(readOnly=true)
	public List<SummaryMinDTO> searchSummary(String name, String minDate, String maxDate) {

		LocalDate localDateMin = minDate.equals("") ?
		LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L) :
		LocalDate.parse(minDate);

		LocalDate localDateMax = maxDate.equals("") ?
		LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :
		LocalDate.parse(maxDate);

		List<SummaryMinDTO> result = repository.searchSummary(name, localDateMin, localDateMax);
		return result;
	}
}
