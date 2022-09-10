package com.loremipsum.momomtn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loremipsum.momomtn.models.Balance;
import com.loremipsum.momomtn.models.RequestOptions;
import com.loremipsum.momomtn.services.CollectionService;
import com.loremipsum.momomtn.services.Impls.CollectionServiceImpl;

public class MomoMtnApplication {

    public static void main(String[] args) {
        RequestOptions options = RequestOptions.builder().build();
        CollectionService service = new CollectionServiceImpl(options);

        Balance balance = null;
        try {
            balance = service.getAccountBalance();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(balance);
    }
}
