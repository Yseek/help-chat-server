package chat.help.help_chat.service;

import chat.help.help_chat.domain.ChatMessage;
import chat.help.help_chat.domain.ChatRoom;
import chat.help.help_chat.dto.ChatRoomResponse;
import chat.help.help_chat.repository.ChatRepository;
import chat.help.help_chat.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    // 방별로 Sink를 따로 관리
    private final Map<String, Sinks.Many<ChatMessage>> sinkMap = new ConcurrentHashMap<>();

    /**
     * 메시지를 저장하고 해당 방에 broadcast
     */
    public Mono<Void> saveAndBroadcast(ChatMessage message) {
        return chatRepository.save(message)
                .doOnNext(saved -> {
                    getSink(saved.getRoomId()).tryEmitNext(saved); // 실시간 전송
                })
                .then();
    }

    /**
     * 해당 roomId에 연결된 실시간 메시지 스트림 반환
     */
    public Flux<ChatMessage> getStream(String roomId) {
        return getSink(roomId).asFlux();
    }

    /**
     * 방에 대한 Sink 생성 또는 반환
     */
    private Sinks.Many<ChatMessage> getSink(String roomId) {
        return sinkMap.computeIfAbsent(roomId, key ->
                Sinks.many().multicast().onBackpressureBuffer()
        );
    }

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

    public Flux<ChatMessage> getChatHistory(String roomId) {
        return chatRepository.findByRoomId(roomId)
                .sort(Comparator.comparing(ChatMessage::getSendAt)); // 시간순 정렬
    }

    public Flux<ChatRoomResponse> getAllRooms(){
        return chatRoomRepository
                .findAll()
                .map(chatRoom -> new ChatRoomResponse(chatRoom.getId(), chatRoom.getEmail()));
    }

}
