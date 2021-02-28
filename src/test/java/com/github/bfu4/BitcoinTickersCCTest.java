package com.github.bfu4;

import com.github.bfu4.BitcoinTickersCC.except.RateNotFoundException;
import com.github.bfu4.BitcoinTickersCC.obj.BitcoinEndpoint;
import com.github.bfu4.BitcoinTickersCC.obj.BlockchainEndpoint;
import org.junit.Assert;
import org.junit.Test;

/**
 * BitcoinTickersCCTest
 *
 * @author bfu4
 * @since 2/27/2021 @ 02:09 PM
 */
public class BitcoinTickersCCTest {

    /**
     * Test the bitcoin endpoint response to verify not null
     */
    @Test
    public void testBitcoinEndpointResponse() {
        BitcoinEndpoint client = new BitcoinEndpoint();
        Assert.assertNotNull(client.get().getEntity());
    }

    /**
     * Test the blockchain endpoint response to verify not null
     */
    @Test
    public void testBlockchainEndpointResponse() {
        BlockchainEndpoint client = new BlockchainEndpoint();
        Assert.assertNotNull(client.get().getEntity());
    }

    @Test
    public void testBlockChainEndpointUSDFound() {
        BlockchainEndpoint client = new BlockchainEndpoint();
        float rate = -1f;
        try {
            rate = client.getSellRate("USD");
        } catch (RateNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(-1f < rate);
    }

    @Test
    public void testBitcoinEndpointUSDFound() {
        BitcoinEndpoint client = new BitcoinEndpoint();
        float rate = -1f;
        try {
            rate = client.getSellRate("USD");
        } catch (RateNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(-1f < rate);
    }

}
