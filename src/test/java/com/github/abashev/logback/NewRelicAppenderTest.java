package com.github.abashev.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class NewRelicAppenderTest {
    private final Logger log = LoggerFactory.getLogger(NewRelicAppenderTest.class);

    @Test
    public void initLogback() {
        log.info("Test");
        log.warn("Test {}", 33);
        log.error("Test", new IllegalArgumentException());
    }
}