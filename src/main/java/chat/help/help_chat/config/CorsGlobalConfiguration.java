package chat.help.help_chat.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // ✅ 프론트엔드 origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ 허용할 메서드
                .allowedHeaders("*") // ✅ 모든 헤더 허용
                .allowCredentials(true) // ✅ 쿠키/세션 등 인증 허용
                .maxAge(3600); // ✅ preflight 결과 캐싱 시간 (초)
    }
}
