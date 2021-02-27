import com.github.bfu4.BitcoinTickersCC.web.BitcoinTickersCCFunctionalClient;
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
        BitcoinTickersCCFunctionalClient client = new BitcoinTickersCCFunctionalClient(
                "https://api.exchange.bitcoin.com/api/2/public/ticker",
                "sell"
        );
        Assert.assertNotNull(client.getResponse().getEntity());
    }

    /**
     * Test the blockchain endpoint response to verify not null
     */
    @Test
    public void testBlockchainEndpointResponse() {
        BitcoinTickersCCFunctionalClient client = new BitcoinTickersCCFunctionalClient(
                "https://blockchain.info/ticker",
                "USD"
        );
        Assert.assertNotNull(client.getResponse().getEntity());
    }

}
