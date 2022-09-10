package com.loremipsum.momomtn.services.Impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loremipsum.momomtn.models.AccessToken;
import com.loremipsum.momomtn.models.Balance;
import com.loremipsum.momomtn.models.RequestOptions;
import com.loremipsum.momomtn.models.Transaction;
import com.loremipsum.momomtn.services.CollectionService;
import com.loremipsum.momomtn.utils.MomoUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class CollectionServiceImpl extends MomoUtils implements CollectionService {

    private final RequestOptions options;

    public CollectionServiceImpl(RequestOptions options) {
        this.options = options;
    }

    @Override
    public AccessToken getAccessToken() {
        return accessToken(this.options.getCollectionUserId(), this.options.getCollectionApiSecret(), this.options.getCollectionPrimaryKey(), this.options.getBaseUrl());
    }

    @Override
    public Balance getAccountBalance() throws JsonProcessingException {
        AccessToken accessToken = accessToken(this.options.getCollectionUserId(), this.options.getCollectionApiSecret(), this.options.getCollectionPrimaryKey(), this.options.getBaseUrl());
        return getBalance(this.options, accessToken.getAccessToken());
    }


    @Override
    public String requestToPay(HashMap<String, String> hashMap) throws JsonProcessingException {
        String ref = UUID.randomUUID().toString();
        AccessToken accessToken = accessToken(this.options.getCollectionUserId(), this.options.getCollectionApiSecret(), this.options.getCollectionPrimaryKey(), this.options.getBaseUrl());
        requestToPay(this.options, accessToken.getAccessToken(), ref, hashMap);
        return ref;
    }

    @Override
    public Transaction transactionStatus(String reference) throws JsonProcessingException {
        AccessToken accessToken = accessToken(this.options.getCollectionUserId(), this.options.getCollectionApiSecret(), this.options.getCollectionPrimaryKey(), this.options.getBaseUrl());
        return getTransactionStatus(this.options, accessToken.getAccessToken(), reference);
    }
}
