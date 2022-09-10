package com.loremipsum.momomtn.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Balance implements Serializable {
    private String description;
    private String availableBalance;
    private String currency;
}
