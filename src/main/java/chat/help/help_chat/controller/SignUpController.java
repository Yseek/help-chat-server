package chat.help.help_chat.controller;

import chat.help.help_chat.domain.User;
import chat.help.help_chat.dto.SignUpRequest;
import chat.help.help_chat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class SignUpController {

    private final UserRepository userRepository;

    @PostMapping
    public Mono<ResponseEntity<String>> signup(@RequestBody Mono<SignUpRequest> signUpRequestMono) {

        return signUpRequestMono
                .flatMap(request ->
                        userRepository.findByEmail(request.email())
                                .flatMap(existingUser -> {
                                    return Mono.just(ResponseEntity.badRequest().body("이미 가입된 이메일입니다."));
                                })
                                .switchIfEmpty(
                                        userRepository.save(new User(request.email(), request.password()))
                                                .map(savedUser -> ResponseEntity.ok("회원가입 성공"))
                                ));
    }
}
