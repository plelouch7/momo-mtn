package com.loremipsum.momomtn.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class RequestOptions implements Serializable {
    private String collectionUserId;
    private String collectionApiSecret;
    private String collectionPrimaryKey;
    private String remittancePrimaryKey;
    private String remittanceUserId;
    private String remittanceApiSecret;
    private String disbursementPrimaryKey;
    private String disbursementUserId;
    private String disbursementApiSecret;
    private String baseUrl;
    private String targetEnvironment;
    private String currency;
}
