package com.java.back.end.casa.codigo.service;

import com.java.back.end.casa.codigo.dto.DTOConverter;
import com.java.back.end.casa.codigo.model.Shop;
import com.java.back.end.casa.codigo.repository.ReportRepository;
import dto.ShopDTO;
import dto.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    @Qualifier("reportRepositoryImpl")
    private ReportRepository reportRepository;

    public List<ShopDTO> getShopsByFilter(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo) {
        List<Shop> shops = reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim) {
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }
}
