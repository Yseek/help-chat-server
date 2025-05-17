package chat.help.help_chat.repository;

import chat.help.help_chat.domain.ChatRoom;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ChatRoomRepository extends ReactiveMongoRepository<ChatRoom, String> {
    Mono<ChatRoom> findByEmail(String email);
}
