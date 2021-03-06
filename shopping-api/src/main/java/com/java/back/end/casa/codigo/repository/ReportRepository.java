package com.java.back.end.casa.codigo.repository;

import com.java.back.end.casa.codigo.model.Shop;
import dto.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);
}
