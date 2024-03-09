package com.personal.project.common.web.exception;

import com.personal.project.common.dto.error.ErrorResponse;
import com.personal.project.common.dto.error.FieldErrorResponse;
import com.personal.project.common.dto.error.InvalidInputResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.common.error.BadRequestError;
import com.personal.project.common.error.ResponseError;
import com.personal.project.common.exception.ResponseException;
import com.personal.project.common.web.i18n.LocaleStringService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {

    private final LocaleStringService localeStringService;

    public ExceptionHandleAdvice(LocaleStringService localeStringService) {
        this.localeStringService = localeStringService;
    }

//    @Override
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers,
//                                                                  HttpStatusCode status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return ResponseEntity.unprocessableEntity().body(Response.of(errors, Boolean.FALSE, HttpStatus.BAD_REQUEST.value()));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InvalidInputResponse> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        Set<FieldErrorResponse> fieldErrors = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    try {
                        FieldError fieldError = (FieldError) objectError;
                        String message = localeStringService.getMessage(
                                fieldError.getDefaultMessage(), fieldError.getDefaultMessage());
                        return FieldErrorResponse.builder()
                                .field(fieldError.getField())
                                .objectName(fieldError.getObjectName())
                                .message(message)
                                .build();
                    } catch (ClassCastException ex) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        log.warn("Failed to handle request " + request.getRequestURI() + ": " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new InvalidInputResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        localeStringService.getMessage(
                                BadRequestError.INVALID_INPUT.getName(),
                                "Invalid request arguments"),
                        BadRequestError.INVALID_INPUT.getName(),
                        fieldErrors));
    }

    @ExceptionHandler({ AccessDeniedException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response<Object> handleAccessDeniedException(
            Exception ex) {
        return Response.of("Access denied message here", Boolean.FALSE,HttpStatus.FORBIDDEN.value());
    }

    /**
     * Exception handler common
     * @param e Exception
     * @param request request
     * @return ResponseEntity
     */
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse<Object>> handleResponseException(ResponseException e, HttpServletRequest request) {
        log.warn("Failed to handle request {}: {}", request.getRequestURI(), e.getError().getMessage(), e);
        ResponseError error = e.getError();
        String message = localeStringService.getMessage(error.getName(), e.getError().getMessage(), e.getParams());
        return ResponseEntity.status(error.getStatus())
                .body(ErrorResponse.builder()
                        .code(error.getCode())
                        .error(error.getName())
                        .message(message)
                        .build());
    }

}
