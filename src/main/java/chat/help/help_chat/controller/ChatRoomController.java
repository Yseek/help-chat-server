package chat.help.help_chat.controller;

import chat.help.help_chat.dto.ChatRoomRequest;
import chat.help.help_chat.dto.ChatRoomResponse;
import chat.help.help_chat.service.ChatRoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat-room")
@Slf4j
@AllArgsConstructor
public class ChatRoomController {

    private  final ChatRoomService chatRoomService;

    @GetMapping("/{roomId}")
    public Mono<String> enter(@PathVariable String roomId) {
        log.info("ChatRoomController.enter roomID {}", roomId);
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(auth -> "User '" + auth.getName() + "' joind chat room " + roomId);
    }

    @PostMapping
    public Mono<ChatRoomResponse> createChatRoom(@RequestBody ChatRoomRequest request) {
        String email = request.email();

        return chatRoomService.findRoomByEmail(email)
                .switchIfEmpty(
                        chatRoomService.createRoom(email)
                )
                .map(room -> new ChatRoomResponse(room.getId()));
    }
}
