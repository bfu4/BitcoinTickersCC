package com.github.bfu4.BitcoinTickersCC;

import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;

/**
 * IBitcoinTickersCC
 *
 * @author bfu4
 * @since 2/27/2021 @ 12:27 PM
 */
public interface IBitcoinTickersCC {

    /**
     * Get the Bitcoin endpoint client
     *
     * @return bitcoin endpoint client
     */
    BitcoinTickersCCFunctionalClient getBitcoinEndpoint();

    /**
     * Get the blockchain endpoint client
     *
     * @return blockchain endpoint client
     */
    BitcoinTickersCCFunctionalClient getBlockchainEndpoint();
}
