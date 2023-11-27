/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.gson.Gson;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Slf4j
@UtilityClass
public class YamlUtils {
    public static String parseApollo(InputStream is) {
        var apollo = new Gson().fromJson(new InputStreamReader(is),
            ApolloConfig.class);
        var value = apollo.items().get(0).value();
        // log.debug("items[0].value:\n{}", value);

        var yaml = new Yaml();
        Map<String, ?> content = yaml.load(value);

        var dump = yaml.dump(content);
        log.debug("yaml:\n{}", dump);

        return value;
    }
}
