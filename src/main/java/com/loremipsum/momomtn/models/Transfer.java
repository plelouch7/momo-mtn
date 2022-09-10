package com.loremipsum.momomtn.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transfer implements Serializable {
    private Payer payee;
    private String payeeNote;
    private String payerMessage;
    private String externalId;
    private String currency;
    private String amount;
}
