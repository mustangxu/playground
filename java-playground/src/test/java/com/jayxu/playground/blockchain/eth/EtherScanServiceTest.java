/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author jayxu
 */
//@SpringBootTest
public class EtherScanServiceTest {

    /**
     * Test method for
     * {@link com.jayxu.playground.blockchain.eth.EtherScanService#getHeight()}.
     */
    @Test
    void testGetHeight() {
        var height = new EtherScanService().getHeight();
        System.out.println(height);
        assertTrue(height > 0, "height");
    }

}
