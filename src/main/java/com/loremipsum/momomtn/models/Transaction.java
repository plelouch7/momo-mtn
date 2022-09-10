package com.loremipsum.momomtn.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    private double amount;
    private String currency;
    @JsonProperty("financialTransactionId")
    private String financialTransactionId;
    @JsonProperty("externalId")
    private String externalId;
    @JsonProperty("payer")
    private Payer payer;
    private String status;
    private String payerMessage;
    private String payeeNote;
    private String reason;
}
