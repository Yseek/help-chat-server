package chat.help.help_chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 끄기 (API용이면)
                .cors(ServerHttpSecurity.CorsSpec::disable) // CORS는 별도 처리 or enable
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/**").permitAll()
                )
                .build();
    }
}
