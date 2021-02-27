package com.github.bfu4.BitcoinTickersCC.web;

import com.github.bfu4.BitcoinTickersCC.BitcoinTickersCC;
import com.github.bfu4.BitcoinTickersCC.util.ClientUtil;
import com.github.bfu4.BitcoinTickersCC.web.json.BitcoinTickersCCJSONUtil;
import com.github.bfu4.BitcoinTickersCC.web.json.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * BitcoinTickersCCFunctionalClient
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:18 AM
 */
public class BitcoinTickersCCFunctionalClient implements FunctionalClient {

    private final WebClient client;
    private final String endpointUrl;
    private final String jsonField;

    public BitcoinTickersCCFunctionalClient(String endpointUrl, String jsonField) {
        this.client = ClientUtil.createClient(endpointUrl);
        this.endpointUrl = endpointUrl;
        this.jsonField = jsonField;
    }

    public String getJsonField() {
        return this.jsonField;
    }

    @Override
    @GET
    public JsonResponse getResponse() {
        return new JsonResponse(ClientUtil.getResponse(this.client, "application/json"));
    }

    @Override
    public WebClient getClient() {
        return client;
    }

    @Override
    public String getClientEndpoint() {
        return endpointUrl;
    }


    @Override
    public float getRate(JsonObject object) {
        return BitcoinTickersCCJSONUtil.getFloatFromField(object, jsonField);
    }

    public JsonObject getNested(JsonObject object, String jsonField) {
        return new Gson().fromJson(object.get(jsonField).getAsString(), JsonObject.class);
    }

}
