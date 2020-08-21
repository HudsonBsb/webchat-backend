package br.com.ws.websocket.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    public static <T> T convertStringJsonToObject(String json, Class<T> klass) {
        try {
            var mapper = new ObjectMapper();
            return mapper.readValue(json, klass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
