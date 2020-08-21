package br.com.ws.websocket.controller.api;

import br.com.ws.websocket.controller.ws.AbstractMessageControllerWs;
import br.com.ws.websocket.model.User;
import br.com.ws.websocket.service.FilesStorageService;
import br.com.ws.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.ws.websocket.constant.PathsConstants.*;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@Controller
@RequestMapping(API)
@CrossOrigin("*")
public class UserControllerApi extends AbstractMessageControllerWs {

    private static final String MESSAGE = "message";

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private UserService userService;

    @GetMapping(USERS)
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @PostMapping(USERS)
    public ResponseEntity<Map<String, String>> users(@RequestBody User user) {
        var message = new HashMap<String, String>();

        if (!user.getName().isEmpty() && user.getName().length() > 14) {
            message.put(MESSAGE, "Usuário deve conter no máximo 14 caracteres.");
            return ResponseEntity.badRequest().body(message);
        }

        var userExists = this.userService.getUserByName(user.getName());

        if (userExists.isPresent()) {
            message.put(MESSAGE, "Usuário " + user.getName() + " já existe.");
            return ResponseEntity.badRequest().body(message);
        } else {
            message.put(MESSAGE, "Acesso autorizado.");
            return ResponseEntity.ok(message);
        }
    }

    @PostMapping(USERS_UPLOAD)
    public ResponseEntity<?> userNameUpload(@PathVariable(NAME) String name, @RequestParam(FILE) MultipartFile file) {

        var user = this.userService.getUserByName(name);

        if (user.isPresent()) {
            var usr = user.get();
            this.filesStorageService.save(file, usr.getId());
            String path = Paths.get("/users/images/" + usr.getId(), file.getOriginalFilename()).toString();
            usr.setImage(path);
            this.userService.save(usr);
            this.convertAndSend("/topic/image/change", user);
            return ResponseEntity.ok(usr);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(USERS_DOWNLOAD)
    public ResponseEntity<?> fileDownload(@PathVariable String id,
                                          @PathVariable String fileName, HttpServletRequest request) throws IOException {

        var path = Paths.get(id, fileName);
        var resource = this.filesStorageService.get(path);
        String contentType = APPLICATION_OCTET_STREAM_VALUE;


        if (resource.exists()) {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/users/clear")
    public ResponseEntity<?> clearAllUsers() {
        this.userService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
