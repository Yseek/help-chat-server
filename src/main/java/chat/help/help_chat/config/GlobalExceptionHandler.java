package chat.help.help_chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleRuntimeException(RuntimeException e) {
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", e.getMessage()))
        );
    }
}
