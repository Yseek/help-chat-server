package chat.help.help_chat.service;

import chat.help.help_chat.domain.ChatRoom;
import chat.help.help_chat.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Mono<ChatRoom> findRoomByEmail(String email) {
        return chatRoomRepository.findByEmail(email);
    }

    public Mono<ChatRoom> createRoom(String email) {
        ChatRoom room = ChatRoom.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .createdAt(LocalDateTime.now())
                .status("open")
                .build();

        log.info("room >> " + room);

        return chatRoomRepository.save(room);
    }
}
