package chat.help.help_chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat-room")
@Slf4j
public class ChatRoomController {

    @GetMapping("/{roomId}")
    public Mono<String> enter(@PathVariable String roomId) {
        log.info("ChatRoomController.enter roomID {}", roomId);
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(auth -> "User '" + auth.getName() + "' joind chat room " + roomId);
    }
}
