/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xujiajing
 */
@SpringBootTest
public class EthereumChainServiceTest {
    @Autowired
    private EthereumChainService service;

    /**
     * Test method for
     * {@link com.jayxu.playground.blockchain.eth.EthereumChainService#getHeight()}.
     *
     * @throws Exception
     */
    @Test
    void testGetHeight() throws Exception {
        System.out.println(this.service.getHeight());
    }

}
