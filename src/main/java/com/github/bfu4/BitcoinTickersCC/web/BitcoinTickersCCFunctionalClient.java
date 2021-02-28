package com.github.bfu4.BitcoinTickersCC.web;

import com.github.bfu4.BitcoinTickersCC.util.ClientUtil;
import com.github.bfu4.BitcoinTickersCC.web.json.JsonResponse;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.GET;

/**
 * BitcoinTickersCCFunctionalClient
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:18 AM
 */
public abstract class BitcoinTickersCCFunctionalClient implements FunctionalClient {

    private final WebClient client;
    private final String endpointUrl;
    private final String jsonField;

    /**
     * Create a new client
     *
     * @param endpointUrl url of the endpoint
     * @param jsonField field at root to fetch from
     */
    public BitcoinTickersCCFunctionalClient(String endpointUrl, String jsonField) {
        this.client = ClientUtil.createClient(endpointUrl);
        this.endpointUrl = endpointUrl;
        this.jsonField = jsonField;
    }

    /**
     * Get the jsonfield specified at constructor
     *
     * @return jsonfield
     */
    public String getJsonField() {
        return this.jsonField;
    }

    /**
     * Get a response in type of application/json
     *
     * @return response
     * @deprecated Deprecated as less reliable
     */
    @Override
    @Deprecated
    @GET
    public JsonResponse getResponse() {
        return new JsonResponse(ClientUtil.getResponse(this.client, "application/json"));
    }

    /**
     * Get the web client
     *
     * @return client
     */
    @Override
    public WebClient getClient() {
        return client;
    }

    /**
     * Get the endpoint url
     *
     * @return endpoint url
     */
    @Override
    public String getClientEndpoint() {
        return endpointUrl;
    }


    /**
     * Get a JsonResponse using a response from {@link WebClient#get()}
     *
     * @return JsonResponse respective to received response
     */
    @GET
    public JsonResponse get() {
        return new JsonResponse(client.get());
    }

}
