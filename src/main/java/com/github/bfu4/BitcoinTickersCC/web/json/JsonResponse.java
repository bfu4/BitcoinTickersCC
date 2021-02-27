package com.github.bfu4.BitcoinTickersCC.web.json;

import javax.ws.rs.core.Response;

/**
 * JsonResponse
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:36 AM
 */
public class JsonResponse {

    private final Object entity;

    public JsonResponse(Response response) {
        this.entity = response != null ? response.getEntity() : null;
    }

    public Object getEntity() {
        return this.entity;
    }

}
