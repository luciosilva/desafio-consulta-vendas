package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly=true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<ReportMinDTO> searchReport(String name, String minDate, String maxDate, Pageable pageable) {

		LocalDate localDateMin = minDate.equals("") ?
		LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L) :
		LocalDate.parse(minDate);

		LocalDate localDateMax = maxDate.equals("") ?
		LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :
		LocalDate.parse(maxDate);

		Page<Sale> result = repository.searchReport(name, localDateMin, localDateMax, pageable);
		return result.map(x -> new ReportMinDTO(x));
    }
}
