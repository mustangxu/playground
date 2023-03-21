/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.util;

import java.util.Date;

/**
 * @author xujiajing
 */
public record ApolloConfigItem(String key, String value,
        String dataChangeCreatedBy, String dataChangeLastModifiedBy,
        Date dataChangeCreatedTime, Date dataChangeLastModifiedTime) {
}
