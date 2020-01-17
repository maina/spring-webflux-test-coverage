package com.honeacademy.webclientexample.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbUtils {
    private final JdbcTemplate devJdbcTemplate;

    public void cleanDatatable(String query) {
        log.info("Executing query {} for karate db clean up",query);
        devJdbcTemplate.execute(query);
        log.info(">>> Done cleaning up the db >>>");

    }

}
