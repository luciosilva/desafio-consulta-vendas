package com.devsuperior.dsmeta.projections;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public interface SummaryMinProjection {

    String getSellerName();
    Double getTotal();

}
