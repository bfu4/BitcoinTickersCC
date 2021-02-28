package com.github.bfu4.BitcoinTickersCC.except;

import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;

/**
 * RateNotFoundException
 *
 * @author bfu4
 * @since 2/27/2021 @ 11:20 PM
 */
public class RateNotFoundException extends Exception {

    public RateNotFoundException(BitcoinTickersCCFunctionalClient client) {
        super("Could not find rate for " + client + "!");
    }
}
