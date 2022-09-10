package com.loremipsum.momomtn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loremipsum.momomtn.models.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

public abstract class MomoUtils {

    protected AccessToken accessToken(String userId, String apiSecret, String primaryKey, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createTokenHeaders(userId, apiSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Ocp-Apim-Subscription-Key", primaryKey);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<AccessToken> response = restTemplate.exchange(url + "collection/token/", POST, entity, AccessToken.class);
        return response.getBody();
    }

    protected Balance getBalance(RequestOptions options, String accessToken) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders(options, accessToken, options.getCollectionUserId());
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(options.getBaseUrl() + "collection/v1_0/account/balance", GET, requestBody, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), Balance.class);
    }

    protected void requestToPay(RequestOptions options, String accessToken, String reference, HashMap<String, String> map) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders(options, accessToken, reference);
        ObjectMapper mapper = new ObjectMapper();
        RequestToPay requestToPay = mapper.convertValue(map, RequestToPay.class);
        requestToPay.setCurrency(options.getCurrency());
        String request = mapper.writeValueAsString(requestToPay);
        HttpEntity<String> requestBody = new HttpEntity<>(request, headers);
        restTemplate.exchange(options.getBaseUrl() + "collection/v1_0/requesttopay", POST, requestBody, String.class);
    }

    protected Transaction getTransactionStatus(RequestOptions options, String accessToken, String reference) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders(options, accessToken, options.getCollectionUserId());
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(options.getBaseUrl() + "collection/v1_0/requesttopay/" + reference, GET, requestBody, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), Transaction.class);
    }

    private HttpHeaders getHttpHeaders(RequestOptions options, String accessToken, String reference) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        headers.set("X-Reference-Id", reference);
        headers.set("X-Target-Environment", options.getTargetEnvironment());
        headers.set("Ocp-Apim-Subscription-Key", options.getCollectionPrimaryKey());
        return headers;
    }

    private HttpHeaders createTokenHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
