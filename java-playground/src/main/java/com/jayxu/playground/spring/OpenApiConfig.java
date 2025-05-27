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
 * @author jayxu
 */
@Configuration
public class OpenApiConfig {
    @Autowired(required = false)
    private BuildProperties build;
    @Autowired(required = false)
    private GitProperties git;

    @Bean
    OpenAPI openAPI() {
        var info = new Info().title("Jay's Coding Playground");
        if (this.build != null && this.git != null) {
            info.version(String.format("v%s-%s @%s", this.build.getVersion(), this.git.getShortCommitId(),
                this.git.getCommitTime()));
        }

        return new OpenAPI().info(info);
    }
}
