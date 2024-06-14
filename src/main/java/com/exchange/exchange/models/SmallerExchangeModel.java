package com.exchange.exchange.models;

import lombok.Data;

@Data
public class SmallerExchangeModel {
    private String fromCurrency;
    private Long fromAmount;
    private String toCurrency;
    private Long toAmount;

    public SmallerExchangeModel() {
    }

    public SmallerExchangeModel(String fromCurrency, Long fromAmount, String toCurrency, Long toAmount) {
        this.fromCurrency = fromCurrency;
        this.fromAmount = fromAmount;
        this.toCurrency = fromCurrency;
        this.toAmount = toAmount;
    }
}
