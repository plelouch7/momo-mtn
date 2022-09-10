package com.loremipsum.momomtn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestToPay implements Serializable {
    private Payer payer;
    private String payeeNote;
    private String payerMessage;
    private String externalId;
    private String currency;
    private String amount;
    private String mobile;

    public void setMobile(String mobile) {
        this.payer = new Payer("MSISDN", mobile);
        this.mobile = "";
    }
}
