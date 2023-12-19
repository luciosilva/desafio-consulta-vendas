package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SummaryMinProjection;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "SELECT TB_SELLER.NAME sellerName, " +
    "SUM(TB_SALES.AMOUNT) total " +
    "FROM TB_SELLER " +
    "INNER JOIN TB_SALES " +
    "ON TB_SELLER.ID = TB_SALES.SELLER_ID " +
    "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate " +
    "AND UPPER(TB_SELLER.NAME) LIKE UPPER(CONCAT('%', :name, '%')) " +
    "GROUP BY TB_SELLER.NAME", nativeQuery = true)
    List<SummaryMinProjection> searchSummary(String name, LocalDate minDate, LocalDate maxDate);
}
