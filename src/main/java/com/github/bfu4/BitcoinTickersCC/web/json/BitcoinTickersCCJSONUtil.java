package com.github.bfu4.BitcoinTickersCC.web.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.cxf.helpers.IOUtils;

import java.io.IOException;
import java.io.InputStream;


/**
 * BitcoinTickersCCJSONUtil
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:31 AM
 */
public final class BitcoinTickersCCJSONUtil {

    /**
     * Restrict instantiation of utility class
     */
    private BitcoinTickersCCJSONUtil() { }

    /**
     * Turn the JSON response to String
     *
     * @param response {@link JsonResponse } to turn into a string
     * @return string form of the JsonResponse
     */
    public static JsonObject jsonResponseToJson(JsonResponse response) {
        String resp = "";
        try {
            resp = IOUtils.toString((InputStream) response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(resp, JsonObject.class);
    }

    public static String getFromJsonAsString(JsonObject object, String field) {
        return object.get(field).getAsString();
    }

    public static float getFloatFromField(JsonObject object, String field) {
        return object.get(field).getAsFloat();
    }

}
