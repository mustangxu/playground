/**
 * Authored by jayxu-@2024
 */
package com.jayxu.playground.spring;

import java.time.Duration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.Http3SslContextSpec;
import reactor.netty.http.HttpProtocol;

@Component
@ConditionalOnProperty(name = "http3.enabled", havingValue = "true")
@Slf4j
public class Http3NettyWebServerCustomizer
        implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        factory.addServerCustomizers(server -> {
            var sslBundle = factory.getSslBundles().getBundle("https");
            var sslContextSpec = Http3SslContextSpec.forServer(
                sslBundle.getManagers().getKeyManagerFactory(),
                sslBundle.getKey().getPassword());

            log.info("HTTP/3 server configed");

            return server
                // Configure HTTP/3 protocol
                .protocol(HttpProtocol.HTTP3)
                // Configure HTTP/3 SslContext
                .secure(spec -> spec.sslContext(sslContextSpec))
                .http3Settings(spec -> spec.idleTimeout(Duration.ofSeconds(5))
                    .maxData(10_000_000)
                    .maxStreamDataBidirectionalRemote(1_000_000)
                    .maxStreamsBidirectional(100));
        });

    }
}
