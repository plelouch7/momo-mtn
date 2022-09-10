package com.loremipsum.momomtn.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loremipsum.momomtn.models.AccessToken;
import com.loremipsum.momomtn.models.Balance;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;

public interface BaseService {

    AccessToken getAccessToken();

    Balance getAccountBalance() throws JsonProcessingException;
}
