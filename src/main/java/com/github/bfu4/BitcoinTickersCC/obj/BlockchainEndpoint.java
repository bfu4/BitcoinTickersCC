package com.github.bfu4.BitcoinTickersCC.obj;

import com.github.bfu4.BitcoinTickersCC.except.RateNotFoundException;
import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;
import com.github.bfu4.BitcoinTickersCC.web.json.BitcoinTickersCCJSONUtil;
import com.google.gson.JsonElement;

/**
 * BlockchainEndpoint
 *
 * @author bfu4
 * @since 2/27/2021 @ 10:42 PM
 */
public class BlockchainEndpoint extends BitcoinTickersCCFunctionalClient {

    public BlockchainEndpoint() {
        super("https://blockchain.info/ticker", "USD");
    }

    /**
     * Get value at field from the root element
     *
     * @param field field to get
     * @return element from field
     */
    public JsonElement getFromRootElement(String field) {
        return BitcoinTickersCCJSONUtil.jsonResponseToJson(get()).get(field);
    }

    /**
     * Get a nested element from a root element
     *
     * @param field root element
     * @param nestedEl nested element
     * @return jsonelement
     */
    public JsonElement getFromRootNested(String field, String nestedEl) {
        return getFromRootElement(field).getAsJsonObject().get(nestedEl);
    }

    /**
     * Get the sell rate respective to currency (if found)
     *
     * @param currency currency to find
     * @return sell rate if found
     * @throws RateNotFoundException if the sell rate could not be found
     */
    public float getSellRate(String currency) throws RateNotFoundException {
        JsonElement el = getFromRootNested(currency, "sell");
        if (el == null) throw new RateNotFoundException(this);
        return el.getAsFloat();
    }

}
