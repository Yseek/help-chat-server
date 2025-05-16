package chat.help.help_chat.service;

import chat.help.help_chat.domain.User;
import chat.help.help_chat.dto.SignUpRequest;
import chat.help.help_chat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<String> signup(SignUpRequest request) {
        return userRepository.findByEmail(request.email())
                .flatMap(existing -> Mono.<String>error(new RuntimeException("이미 가입된 이메일입니다.")))
                .switchIfEmpty(
                        userRepository.save(
                                new User(request.email(), passwordEncoder.encode(request.password()))
                        ).thenReturn("회원가입 성공")
                );
    }
}
