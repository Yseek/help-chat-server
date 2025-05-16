package chat.help.help_chat.service;

import chat.help.help_chat.dto.LoginRequest;
import chat.help.help_chat.dto.LoginResponse;
import chat.help.help_chat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<LoginResponse> login (LoginRequest request) {
        return userRepository.findByEmail(request.email())
                .switchIfEmpty(Mono.error(new RuntimeException("존재하지 않는 사용자입니다.")))
                .flatMap( user ->{
                    if (!passwordEncoder.matches(request.password(),user.getPassword())) {
                        return Mono.error(new RuntimeException("비밀번호가 일치하지 않습니다."));
                    }
                    return Mono.just(new LoginResponse(user.getEmail(), "tokenTest"));
                });
    }
}
