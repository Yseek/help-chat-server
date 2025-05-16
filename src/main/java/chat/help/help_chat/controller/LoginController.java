package chat.help.help_chat.controller;

import chat.help.help_chat.dto.LoginRequest;
import chat.help.help_chat.dto.LoginResponse;
import chat.help.help_chat.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public Mono<ResponseEntity<LoginResponse>> postMethodName(@RequestBody Mono<LoginRequest> requestMono) {

        return requestMono
                .flatMap(loginService::login)
                .map(ResponseEntity::ok);
    }
}
