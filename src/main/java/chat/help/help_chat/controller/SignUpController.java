package chat.help.help_chat.controller;

import chat.help.help_chat.dto.SignUpRequest;
import chat.help.help_chat.service.SignUpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping
    public Mono<ResponseEntity<String>> signup(@Valid @RequestBody Mono<SignUpRequest> signUpRequestMono) {
        return signUpRequestMono
                .flatMap(signUpService::signup)
                .map(ResponseEntity::ok);
    }
}
