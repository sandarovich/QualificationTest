package com.sandarovich.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PurchaseDTOHolder {

    private List<PurchaseDTO> purchaseDTOList;

    @JsonProperty("data")
    public List<PurchaseDTO> getPurchaseDTOList() {
        return purchaseDTOList;
    }

    public void setPurchaseDTOList(List<PurchaseDTO> purchaseDTOList) {
        this.purchaseDTOList = purchaseDTOList;
    }

}
