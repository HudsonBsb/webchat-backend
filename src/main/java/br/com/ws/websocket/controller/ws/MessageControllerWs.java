package br.com.ws.websocket.controller.ws;

import br.com.ws.websocket.model.Message;
import br.com.ws.websocket.model.User;
import br.com.ws.websocket.service.UserService;
import br.com.ws.websocket.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;

import static br.com.ws.websocket.constant.PathsConstants.*;

@Controller
public class MessageControllerWs extends AbstractMessageControllerWs {

    @Autowired
    private UserService userService;

    @MessageMapping(WS)
    @SendTo(TOPIC)
    public Message message(Message message) {
        return new Message(message.getUser(), message.getMessage(), LocalDateTime.now(), false, null);
    }

    @MessageMapping(TOPIC_PRIVATE)
    public void messageToPrivate(
            @DestinationVariable String id, Message message, @Header(SESSION_ID) String sessionId) {
        this.userService.getUserById(id)
                .ifPresent(usr -> this.convertAndSendPrivateMessage(usr, message));
    }

    @MessageMapping(WS_CONNECTION_NEW)
    public void connectNewUser(@Header(USER) String userJson) {
        var user = Utils.convertStringJsonToObject(userJson, User.class);
        this.userService.save(user);
        this.convertAndSend(TOPIC_CONNECTION_NEW, user);
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        this.userService.getUserById(event.getSessionId())
                .ifPresent(user -> {
                    this.userService.delete(user);
                    this.convertAndSend(WS_DISCONNECTION_MEMBER, user);
                });
    }
}
