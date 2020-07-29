/* Copyright © 2020 Gatech-API - All Rights Reserved. Subject to terms of the PolyForm Noncommercial License. */
package xyz.gatechapi.rest.config;

import com.backendless.Backendless;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class BackendlessInitializer {

    @PostConstruct
    public void initialize() {
        Backendless.initApp(System.getenv("BACKENDLESS_APPLICATION_ID"), System.getenv("BACKENDLESS_API_KEY"));
        log.info("Backendless app initialized.");
    }
}
