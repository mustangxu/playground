/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.wolfram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author jayxu
 */
class WolframServiceTest {
    private WolframService service = WolframService.init();

    /**
     * Test method for
     * {@link com.jayxu.playground.wolfram.WolframService#query(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    void testQuery() {
        var resp = this.service.query("2^10").extractRawResult();
        assertEquals(1024, Integer.parseInt(resp));
    }
}
