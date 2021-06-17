/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain;

import java.io.IOException;

/**
 * @author xujiajing
 */
public interface ChainService {
    long getHeight() throws IOException;
}
