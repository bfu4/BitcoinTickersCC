package com.github.bfu4.BitcoinTickersCC.web.json;

import com.google.gson.*;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


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
        return new Gson().fromJson(response.getEntity(), JsonObject.class);
    }

    public static float getFloatFromField(JsonObject object, String field) {
        return object.get(field).getAsFloat();
    }

    public static JsonPrimitive getFromStringPrimitive(String prim) {
        return new JsonPrimitive(prim);
    }

    public static JsonArray fromJsonResponse(JsonResponse json) {
        return new Gson().fromJson(
                BitcoinTickersCCJSONUtil.getFromStringPrimitive(
                        json.getEntity()
                ).getAsString(),
                JsonArray.class
        );
    }

    public static Stream<JsonElement> streamFromJsonArray(JsonArray array) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        array.iterator(),
                        Spliterator.ORDERED
                ),
                false
        );
    }

}
