package simple.spring.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    private final ChatModel chatModel;

    public JokeController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/")
    public ResponseEntity<String> joke() {
        String content = ChatClient.builder(chatModel).
                build().
                prompt("tell me a joke").
                call().
                content();

        return ResponseEntity.ok(content);
    }
}
