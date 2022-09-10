package com.loremipsum.momomtn.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payer implements Serializable {
    @JsonProperty("partyIdType")
    private String partyIdType;
    @JsonProperty("partyId")
    private String partyId;
}
