package com.github.bfu4.BitcoinTickersCC.util;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * ClientUtil
 *
 * @author bfu4
 * @since 2/27/2021 @ 11.18
 */
public final class ClientUtil {

    /**
     * Restrict creation of utility class.
     */
    private ClientUtil() { }

    /**
     * Create a {@link WebClient } for a specified endpoint
     *
     * @param endpointUrl url of the specified endpoint
     * @return WebClient object respective to the endpoint url
     */
    public static WebClient createClient(final String endpointUrl) {
        return WebClient.create(endpointUrl, true);
    }


    /**
     * Get a response from the specified client respective to the response type
     *
     * @param client client to get a response from
     * @param responseType type of response to accept
     * @return response
     */
    @GET
    public static Response getResponse(Client client, String responseType) {
        return client.accept(responseType).getResponse();
    }

}
