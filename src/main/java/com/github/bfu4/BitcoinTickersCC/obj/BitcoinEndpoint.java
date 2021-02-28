package com.github.bfu4.BitcoinTickersCC.obj;

import com.github.bfu4.BitcoinTickersCC.except.RateNotFoundException;
import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;
import com.github.bfu4.BitcoinTickersCC.web.json.BitcoinTickersCCJSONUtil;
import com.github.bfu4.BitcoinTickersCC.web.json.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * BitcoinEndpoint
 *
 * @author bfu4
 * @since 2/27/2021 @ 10:41 PM
 */
public class BitcoinEndpoint extends BitcoinTickersCCFunctionalClient {

    public BitcoinEndpoint() {
        super("https://api.exchange.bitcoin.com/api/2/public/ticker", "BTC");
    }

    /**
     * Get the root array which is fed back by the specified endpoint
     *
     * @param response response
     * @return root array
     */
    public JsonArray getRootArray(JsonResponse response) {
        return BitcoinTickersCCJSONUtil.fromJsonResponse(response);
    }

    /**
     * Get the element from the json array
     *
     * @param arr array to fetch from
     * @param field field to fetch from array
     * @param match string to search for
     * @return element from json array
     */
    public JsonElement getElementFromJsonArray(JsonArray arr, String field, String match) {
        return BitcoinTickersCCJSONUtil.streamFromJsonArray(
                arr
        ).filter(el -> new Gson().fromJson(el, JsonObject.class).get(field).getAsString().equals(match)).findFirst().orElse(null);
    }

    /**
     * Get the sell rate
     *
     * @param currency currency to get the rate from
     * @return sell rate
     * @throws RateNotFoundException if the rate cannot be found
     */
    public float getSellRate(String currency) throws RateNotFoundException {
        JsonElement el = getElementFromJsonArray(getRootArray(get()), "symbol", getJsonField() + currency)
                .getAsJsonObject().get("ask");
        if (el == null) throw new RateNotFoundException(this);
        return el.getAsFloat();
    }

}
