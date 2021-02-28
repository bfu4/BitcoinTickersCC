package com.github.bfu4.BitcoinTickersCC.obj;

import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;

/**
 * Rate
 *
 * @author bfu4
 * @since 2/27/2021 @ 12:35 PM
 */
public class Rate {

    private final BitcoinTickersCCFunctionalClient endpoint;
    private final float price;
    private final Ticker ticker;

    public Rate(float price, Ticker ticker, BitcoinTickersCCFunctionalClient endpoint) {
        this.price = price;
        this.ticker = ticker;
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "(TICKER: " + ticker.name() + ") " + price + " from [" + endpoint.getClientEndpoint() + "]";
    }

}
