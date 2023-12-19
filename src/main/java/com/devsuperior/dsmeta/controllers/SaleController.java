package com.devsuperior.dsmeta.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SummaryMinProjection;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SellerService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	@Autowired
	private SellerService sellerService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = saleService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportMinDTO>> getReport(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "minDate", defaultValue = "") String minDate,
			@RequestParam(name = "maxDate", defaultValue = "") String maxDate,
			Pageable pageable) {
		Page<ReportMinDTO> dto = saleService.searchReport(name, minDate, maxDate, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryMinProjection>> getSummary(
		@RequestParam(name = "name", defaultValue = "") String name,
		@RequestParam(name = "minDate", defaultValue = "") String minDate,
		@RequestParam(name = "maxDate", defaultValue = "") String maxDate) {
		List<SummaryMinProjection> listDto = sellerService.searchSummary(name, minDate, maxDate);
		return ResponseEntity.ok(listDto);
	}
}
