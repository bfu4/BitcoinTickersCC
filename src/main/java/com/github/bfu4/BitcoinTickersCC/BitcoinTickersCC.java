package com.github.bfu4.BitcoinTickersCC;

import com.github.bfu4.BitcoinTickersCC.except.AlreadyInstantiatedException;
import com.github.bfu4.BitcoinTickersCC.except.ProcessStartedException;
import com.github.bfu4.BitcoinTickersCC.except.RateNotFoundException;
import com.github.bfu4.BitcoinTickersCC.obj.BitcoinEndpoint;
import com.github.bfu4.BitcoinTickersCC.obj.BlockchainEndpoint;
import com.github.bfu4.BitcoinTickersCC.obj.Rate;
import com.github.bfu4.BitcoinTickersCC.obj.Ticker;
import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;
import com.github.bfu4.BitcoinTickersCC.web.json.JsonResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.logging.Logger;

public class BitcoinTickersCC implements IBitcoinTickersCC {

    private static BitcoinTickersCC instance;

    private BlockchainEndpoint blockchainEndpointClient;
    private BitcoinEndpoint bitcoinEndpointClient;

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
    public synchronized void start() {

        // Verify and make sure the process has not started.
        try {
            checkIfStarted();
        } catch (ProcessStartedException e) {
            return;
        }

        started = true;

        this.bitcoinEndpointClient = new BitcoinEndpoint();
        this.blockchainEndpointClient = new BlockchainEndpoint();

        JsonResponse blockchainEndpointResponse = this.blockchainEndpointClient.get();
        JsonResponse bitcoinEndpointResponse = this.bitcoinEndpointClient.get();

        if (bitcoinEndpointResponse.getEntity() == null || blockchainEndpointResponse.getEntity() == null) {
            System.out.println("Missing a response from an endpoint, and could not continue.");
            return;
        }

        try {
            float blockchainEpPrice = blockchainEndpointClient.getSellRate("USD");
            float btcEpPrice = bitcoinEndpointClient.getSellRate("USD");

            Rate bitcoin = new Rate(btcEpPrice, Ticker.BTC, bitcoinEndpointClient);
            Rate blockchain = new Rate(blockchainEpPrice, Ticker.BTC, blockchainEndpointClient);

            Rate cheaperRate = btcEpPrice < blockchainEpPrice ? bitcoin : blockchain;

            System.out.println("\t\t\t*=================== CHEAPER SELL PRICE ===================*");
            System.out.println("\t" + cheaperRate);
            System.out.println("\t\t\t*==========================================================*");

        } catch (RateNotFoundException e) {
            System.out.println(e.getMessage());
        }

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
