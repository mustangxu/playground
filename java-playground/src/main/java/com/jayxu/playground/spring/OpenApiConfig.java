/**
 *
 */
package com.jayxu.playground.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author xujiajing
 */
@Configuration
public class OpenApiConfig {
    @Autowired
    private BuildProperties build;
    @Autowired
    private GitProperties git;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Jay's Coding Playground")
                .version(String.format("v%s-%s @%s",
                    this.build.getVersion(), this.git.getShortCommitId(),
                    this.git.getCommitTime())));
    }
}
