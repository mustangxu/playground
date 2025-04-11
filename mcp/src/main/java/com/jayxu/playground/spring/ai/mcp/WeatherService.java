/*
 * Authored by jayxu @2025
 */
package com.jayxu.playground.spring.ai.mcp;

import java.util.List;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {
    private static final String BASE_URL = "https://api.weather.gov";
    private final RestClient restClient;

    public WeatherService() {
        this.restClient = RestClient.builder().baseUrl(BASE_URL).defaultHeader("Accept", "application/geo+json")
                                    .defaultHeader("User-Agent", "WeatherApiClient/1.0 (your@email.com)").build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Points(Props properties) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Props(String forecast) {
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Forecast(Props properties) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Props(List<Period> periods) {
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Period(Integer number,
                             String name,
                             String startTime,
                             String endTime,
                             Boolean isDayTime,
                             Integer temperature,
                             String temperatureUnit,
                             String temperatureTrend,
                             Map probabilityOfPrecipitation,
                             String windSpeed,
                             String windDirection,
                             String icon,
                             String shortForecast,
                             String detailedForecast) {
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Alert(List<Feature> features) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Feature(Properties properties) {
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Properties(String event,
                                 String areaDesc,
                                 String severity,
                                 String description,
                                 String instruction) {
        }
    }

    @Tool(description = "Get weather forecast for a specific latitude/longitude")
    public Forecast getWeatherForecastByLocation(double latitude, double longitude) {
        var points = restClient.get().uri("/points/{latitude},{longitude}", latitude, longitude).retrieve()
                               .body(Points.class);
        log.debug("{}", points);
        if (points.properties() == null) {
            return null;
        }

        var forecast = restClient.get().uri(points.properties().forecast()).retrieve().body(Forecast.class);
        if (forecast.properties() == null) {
            return null;
        }

        return forecast;
    }

    @Tool(description = "Get weather alerts for a US state. Input is Two-letter US state code (e.g. CA, NY)")
    public Alert getAlerts(String state) {
        return restClient.get().uri("/alerts/active/area/{state}", state).retrieve().body(Alert.class);
    }
}
