package chat.help.help_chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class LoginController {
    
    @PostMapping
    public Mono<ResponseEntity<LoginResponse>> postMethodName(@RequestBody LoginRequest entity) {

        //TODO: process POST request
        System.out.println(entity.email() + " " + entity.password());

        return Mono.just(ResponseEntity.ok(new LoginResponse(entity.email(), "tokenTest")));
    }

    record LoginRequest(String email, String password) {}
    record LoginResponse(String email, String token) {}
}
