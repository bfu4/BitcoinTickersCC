package com.github.bfu4.BitcoinTickersCC.web;

import com.github.bfu4.BitcoinTickersCC.web.json.JsonResponse;
import com.google.gson.JsonObject;
import org.apache.cxf.jaxrs.client.WebClient;

/**
 * FunctionalClient
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:19 AM
 */
public interface FunctionalClient {

    JsonResponse getResponse();

    WebClient getClient();

    String getClientEndpoint();

    float getRate(JsonObject response);

}
