package com.github.bfu4.BitcoinTickersCC;

import com.github.bfu4.BitcoinTickersCC.except.AlreadyInstantiatedException;
import com.github.bfu4.BitcoinTickersCC.except.ProcessStartedException;
import com.github.bfu4.BitcoinTickersCC.obj.Rate;
import com.github.bfu4.BitcoinTickersCC.obj.Ticker;
import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;
import com.github.bfu4.BitcoinTickersCC.web.json.BitcoinTickersCCJSONUtil;
import com.github.bfu4.BitcoinTickersCC.web.json.JsonResponse;

import java.util.logging.Logger;

public class BitcoinTickersCC implements IBitcoinTickersCC {

    private static BitcoinTickersCC instance;

    private BitcoinTickersCCFunctionalClient blockchainEndpointClient;
    private BitcoinTickersCCFunctionalClient bitcoinEndpointClient;

    private boolean started;

    /**
     * Private constructor with exception in attempt to ensure another instance is not created.
     *
     * @throws AlreadyInstantiatedException throws exception if {@link BitcoinTickersCC#instance} is not null
     */
    private BitcoinTickersCC() throws AlreadyInstantiatedException {
        if (instance != null) {
            throw new AlreadyInstantiatedException();
        }
    }

    public static void main(String[] args) {
        instance = getInstance();
        instance.start();
    }

    /**
     * Get singleton instance
     *
     * @return instance
     */
    public static BitcoinTickersCC getInstance() {
        if (instance == null) {
            try {
                instance = new BitcoinTickersCC();
            } catch (AlreadyInstantiatedException e) {
                // If we reach here, this would be a bug.
                Logger.getGlobal().severe(
                        "Errored creating BitcoinTickersCC instance. This is a bug. Stacktrace: "
                );

                e.printStackTrace();
            }
        }
        return instance;
    }


    /**
     * Start the main process
     */
    public void start() {
        // Verify and make sure the process has not started.
        try {
            checkIfStarted();
        } catch (ProcessStartedException e) {
            return;
        }

        this.bitcoinEndpointClient = new BitcoinTickersCCFunctionalClient(
                "https://api.exchange.bitcoin.com/api/2/public/ticker",
                "sell"
        );

        this.blockchainEndpointClient = new BitcoinTickersCCFunctionalClient(
                "https://blockchain.info/ticker",
                "USD"
        );

        JsonResponse blockchainEndpointResponse = this.blockchainEndpointClient.getResponse();
        JsonResponse bitcoinEndpointResponse = this.bitcoinEndpointClient.getResponse();


        if (bitcoinEndpointResponse.getEntity() == null || blockchainEndpointResponse.getEntity() == null) {
            System.out.println("Missing a response from an endpoint, and could not continue.");
            return;
        }

        float blockchainEndpointRate = this.blockchainEndpointClient.getRate(
                blockchainEndpointClient.getNested(BitcoinTickersCCJSONUtil.jsonResponseToJson(
                        blockchainEndpointResponse), blockchainEndpointClient.getJsonField()
                )
        );

        float bitcoinEndpointRate = this.bitcoinEndpointClient.getRate(BitcoinTickersCCJSONUtil.jsonResponseToJson(bitcoinEndpointResponse));

        Rate bitcoin = new Rate(bitcoinEndpointRate, Ticker.BTC);
        Rate blockchain = new Rate(blockchainEndpointRate, Ticker.BTC);

        Rate cheaperRate = bitcoinEndpointRate < blockchainEndpointRate ? bitcoin : blockchain;

        System.out.println("*========== CHEAPER SELL PRICE ==========*");
        System.out.println("\t " + cheaperRate);
        System.out.println("*========================================*");
    }


    /**
     * Check if the process has started, to prevent starting again.
     *
     * @throws ProcessStartedException throw exception if the process has already started
     */
    private void checkIfStarted() throws ProcessStartedException {
        if (started) throw new ProcessStartedException();
    }

    @Override
    public BitcoinTickersCCFunctionalClient getBitcoinEndpoint() {
        return bitcoinEndpointClient;
    }

    @Override
    public BitcoinTickersCCFunctionalClient getBlockchainEndpoint() {
        return blockchainEndpointClient;
    }

}
