/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.util;

import java.util.Date;
import java.util.List;

/**
 * @author xujiajing
 */
public record ApolloConfig(String appId, String clusterName,
        String namespaceName, String comment, String format, boolean isPublic,
        List<ApolloConfigItem> items, String dataChangeCreatedBy,
        String dataChangeLastModifiedBy, Date dataChangeCreatedTime,
        Date dataChangeLastModifiedTime) {
}
