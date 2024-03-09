package com.personal.project.apigatewayservice.fallback;

import com.personal.project.apigatewayservice.dto.Response;
import com.personal.project.apigatewayservice.error.InternalServerError;
import com.personal.project.apigatewayservice.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class FallBackResource {

    @GetMapping("/fallback")
    public Mono<Response<Void>> fallback() {
        throw new ResponseException(InternalServerError.SERVICE_UNAVAILABLE_ERROR);
    }
}
