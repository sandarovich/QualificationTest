package com.sandarovich.service;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.dto.PurchaseDTO;
import com.sandarovich.model.Product;
import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseItem;
import com.sandarovich.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseReportService implements ReportService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public String getReport(int month) {
        if (month < 0) {
            return "Month can not be negative. Aborted!!!";
        }

        List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
        List<Purchase> purchases = purchaseDao.getByDate(Utility.getDateByMonthOffset(month));

        for (Purchase purchase : purchases) {
            for (PurchaseItem item : purchase.getItems()) {
                purchaseDTOList.add(getPurchaseDTO(item));
            }
        }

        try {
            String rootName = PurchaseDTO.class.getAnnotation(JsonRootName.class).value();
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writer().withRootName(rootName).writeValueAsString(purchaseDTOList);
        } catch (IOException e) {
            return "Bad serializing.Please try later (";
        }

    }

    private PurchaseDTO getPurchaseDTO(PurchaseItem item) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Product product = item.getProduct();
        purchaseDTO.setProduct(product.getName());
        purchaseDTO.setCount(item.getQuantity());
        purchaseDTO.setSum(item.getQuantity() * product.getPrice());
        return purchaseDTO;
    }


}
