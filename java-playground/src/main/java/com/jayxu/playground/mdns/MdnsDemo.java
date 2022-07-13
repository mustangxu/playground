/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.mdns;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.util.concurrent.Executors;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class MdnsDemo {
    private static final String SERVICE = "_http._tcp.local.";

    public static void main(String[] args) throws Exception {
        var pool = Executors.newFixedThreadPool(2);
        var server = pool.submit(MdnsDemo::startServer);
        pool.submit(MdnsDemo::startClient);

        Thread.sleep(10_000);
        try (var jmDNS = server.get();) {
            jmDNS.unregisterAllServices();
            System.out.println("Done");
        }

        System.exit(0);
    }

    private static JmDNS startClient() {
        System.out.println(Thread.currentThread().getName());

        try {
            // Create a JmDNS instance
            var host = InetAddress.getLocalHost();
            System.out.println(host);
            var jmdns = JmDNS.create(host);

            // Add a service listener
            jmdns.addServiceListener(MdnsDemo.SERVICE, new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    System.out.println("Service added: " + event.getInfo());
                }

                @Override
                public void serviceRemoved(ServiceEvent event) {
                    System.out.println("Service removed: " + event.getInfo());
                }

                @Override
                public void serviceResolved(ServiceEvent event) {
                    System.out.println("Service resolved: " + event.getInfo());
                }
            });

            return jmdns;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static JmDNS startServer() {
        System.out.println(Thread.currentThread().getName());

        try {
            // Create a JmDNS instance
            var jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Register a service
            var serviceInfo = ServiceInfo.create(MdnsDemo.SERVICE, "example",
                1234, "path=index.html");
            jmdns.registerService(serviceInfo);

            return jmdns;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
