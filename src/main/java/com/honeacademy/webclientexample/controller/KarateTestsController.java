package com.honeacademy.webclientexample.controller;

import com.honeacademy.webclientexample.model.KarateTestCleanUpRequest;
import com.honeacademy.webclientexample.utils.DbUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Class to handle test data and fixtures clean up
 */
@RestController
@RequestMapping("/karate-tests")
@RequiredArgsConstructor
public class KarateTestsController {
    private final DbUtils dbUtils;

    /**
     * Clean up posts table
     * @param request
     * @return
     */
    @DeleteMapping("/posts-after-scenario")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> deletePost(@RequestBody final KarateTestCleanUpRequest request) {
        String query=request.getQuery()==null?"truncate table post":request.getQuery();
        dbUtils.cleanDatatable(query);
        return Mono.just(HttpStatus.NO_CONTENT);
    }


}
