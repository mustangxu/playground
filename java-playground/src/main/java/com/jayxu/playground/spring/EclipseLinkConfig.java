/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.jpa.autoconfigure.JpaBaseConfiguration;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author jayxu
 */
@Configuration
@EnableJpaAuditing
public class EclipseLinkConfig extends JpaBaseConfiguration {
    public EclipseLinkConfig(DataSource dataSource, JpaProperties properties,
            ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    //    @Override
    //    protected Map<String, Object> getVendorProperties() {
    //    }

    @Override
    protected Map<String, Object> getVendorProperties(DataSource dataSource) {
        return new HashMap<>(this.getProperties().getProperties());
    }
}
