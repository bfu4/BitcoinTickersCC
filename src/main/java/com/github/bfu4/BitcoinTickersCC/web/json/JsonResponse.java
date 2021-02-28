package com.github.bfu4.BitcoinTickersCC.web.json;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * JsonResponse
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:36 AM
 */
public class JsonResponse {

    private final String entity;

    public JsonResponse(Response response) {
        AtomicReference<String> ent = new AtomicReference<>();
        if (response != null) {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(((InputStream) response.getEntity()))
                );
                br.lines().forEach(line -> {
                    ent.set(ent.get() != null ? ent.get() + line : "" + line);
                });
            } catch (Exception e) {
                System.out.println("Could not get a legible response. Reason: " + e.getMessage());
            }
        }
        this.entity = ent.get();
    }

    public String getEntity() {
        return this.entity;
    }

}
