/**
 * Authored by jayxu-@2025
 */
package com.jayxu.playground.spring.ai.mcp;

import org.junit.jupiter.api.Test;

/**
 *
 */
class WeatherServiceTest {

    /**
     * Test method for
     * {@link com.jayxu.playground.spring.ai.mcp.WeatherService#getWeatherForecastByLocation(double, double)}.
     */
    @Test
    void testGetWeatherForecastByLocation() {
        var s = new WeatherService().getWeatherForecastByLocation(47.6062, -122.3321);
        System.out.println(s);
    }
}
