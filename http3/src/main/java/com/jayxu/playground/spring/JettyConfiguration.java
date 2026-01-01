//package com.jayxu.playground.spring;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//
//import org.eclipse.jetty.quic.server.ServerQuicConfiguration;
//import org.eclipse.jetty.util.ssl.SslContextFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jetty.servlet.JettyServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Configuration
//public class JettyConfiguration extends OncePerRequestFilter
//        implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {
//    @Value("${server.port}")
//    private int serverPort;
//
//    @Override
//    public void customize(JettyServletWebServerFactory factory) {
//        factory.addServerCustomizers(server -> {
//            var sslContextFactory = new SslContextFactory.Server();
//            sslContextFactory.setKeyStore(factory.getSslBundles().getBundle("https").getStores().getKeyStore());
//            sslContextFactory.setKeyStorePassword(""); // Must be set for Jetty
//
//            var httpConfig =
//                    new ServerQuicConfiguration(sslContextFactory, Paths.get(System.getProperty("java.io.tmpdir")));
//
//            //            HttpConfiguration httpConfig = new HttpConfiguration();
//            //            httpConfig.addCustomizer(new SecureRequestCustomizer());
//
//            var connector = new QuicServerConnector(server, httpConfig);
//
//            //            HTTP3ServerConnector connector = new HTTP3ServerConnector(server,
//            //                sslContextFactory,
//            //                new HTTP3ServerConnectionFactory(quicConfig, httpConfig));
//
//            connector.setPort(JettyConfiguration.this.serverPort);
//            server.addConnector(connector);
//        });
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        response.setHeader("Alt-Svc", "h3=\":" + this.serverPort + "\"; ma=3600");
//        chain.doFilter(request, response);
//    }
//}
