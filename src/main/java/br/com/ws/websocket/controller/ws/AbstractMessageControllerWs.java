package br.com.ws.websocket.controller.ws;

import br.com.ws.websocket.model.Message;
import br.com.ws.websocket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public abstract class AbstractMessageControllerWs {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void convertAndSend(String path, Object object) {
        this.simpMessagingTemplate
                .convertAndSend(path, object);
    }

    public void convertAndSend(Object object, String[] paths) {
        Arrays.stream(paths).forEach(path ->
                this.simpMessagingTemplate
                        .convertAndSend(path, object));
    }

    public void convertAndSendPrivateMessage(User user, Message message) {
        var userMessage = message.getUser();
        message.setTime(LocalDateTime.now());
        message.setPrivate(true);
        var urls = new String[2];
        urls[0] = "/topic/" + userMessage.getId() + "/private";
        if (user != null) {
            urls[1] = "/topic/" + user.getId() + "/private";
        }
        this.convertAndSend(message, urls);
    }
}
