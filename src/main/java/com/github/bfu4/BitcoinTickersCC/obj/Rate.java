package com.github.bfu4.BitcoinTickersCC.obj;

/**
 * Rate
 *
 * @author bfu4
 * @since 2/27/2021 @ 12:35 PM
 */
public class Rate {

    private final float price;
    private final Ticker ticker;

    public Rate(float price, Ticker ticker) {
        this.price = price;
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "(TICKER: " + ticker.name() + ")" + price;
    }

}
