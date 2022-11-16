/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.util;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xujiajing
 */
@Slf4j
public class YamlUtils {
    public static String parseApollo(InputStream is) {
        var yaml = new Yaml();
        Map<String, ?> map = yaml.load(is);

        var value = (String) ((Map<String, ?>) ((List<?>) map.get("items"))
            .get(0)).get("value");
        log.debug("items[0].value:\n{}", value);

        Map<String, ?> content = yaml.load(value);

        var dump = yaml.dump(content);
        log.debug("yaml:\n{}", dump);

        return value;
    }
}
