package br.com.ws.websocket.constant;

public class PathsConstants {
    public static final String USER = "user";
    public static final String API = "api";
    public static final String APP = "/app";
    public static final String USERS = "users";
    public static final String NAME = "name";
    public static final String FILE = "file";
    public static final String USERS_UPLOAD = "/users/{name}/upload";
    public static final String USERS_DOWNLOAD = "/users/images/{id}/{fileName:.+}";
    public static final String ATTACHMENT_FILENAME = "attachment; filename=\"";
    public static final String SESSION_ID = "simpSessionId";
    public static final String WS = "/ws";
    public static final String TOPIC = "/topic";
    public static final String TOPIC_PRIVATE = "/ws/{id}/private";
    public static final String TOPIC_CONNECTION_NEW = "/topic/connect/member";
    public static final String WS_CONNECTION_NEW = "/ws/connect/new";
    public static final String WS_DISCONNECTION_MEMBER = "/topic/disconnect/member";
}
