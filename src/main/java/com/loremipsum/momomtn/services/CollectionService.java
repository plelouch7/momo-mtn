package com.loremipsum.momomtn.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loremipsum.momomtn.models.Transaction;

import java.util.HashMap;

public interface CollectionService extends BaseService{

    String requestToPay(HashMap<String, String> hashMap) throws JsonProcessingException;

    Transaction transactionStatus(String reference) throws JsonProcessingException;
}
