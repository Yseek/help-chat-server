package chat.help.help_chat.controller;

import chat.help.help_chat.domain.ChatMessage;
import chat.help.help_chat.dto.ChatRoomRequest;
import chat.help.help_chat.dto.ChatRoomResponse;
import chat.help.help_chat.service.ChatRoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/chat")
@Slf4j
@AllArgsConstructor
public class ChatController {

    private  final ChatRoomService chatRoomService;

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<ChatMessage>> stream(@RequestParam String roomId) {
        log.info("SSE 연결: roomId = {}", roomId);

        return chatRoomService.getStream(roomId)
                .map(msg -> ServerSentEvent.builder(msg).build());
    }

    @PostMapping("/send")
    public Mono<Void> sendMessage(@RequestBody ChatMessage message) {
        message.setSendAt(LocalDateTime.now()); // 서버 시간 설정
        return chatRoomService.saveAndBroadcast(message);
    }

    @GetMapping("/history")
    public Flux<ChatMessage> getHistory(@RequestParam String roomId) {
        return chatRoomService.getChatHistory(roomId);
    }

    @PostMapping
    public Mono<ChatRoomResponse> createChatRoom(@RequestBody ChatRoomRequest request) {
        String email = request.email();

        return chatRoomService.findRoomByEmail(email)
                .switchIfEmpty(
                        chatRoomService.createRoom(email)
                )
                .map(room -> new ChatRoomResponse(room.getId(), room.getEmail()));
    }

    @GetMapping("/rooms")
    public Flux<ChatRoomResponse> getAllRooms() {
        return chatRoomService.getAllRooms();
    }
}
