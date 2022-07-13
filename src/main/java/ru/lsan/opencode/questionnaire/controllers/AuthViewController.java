package ru.lsan.opencode.questionnaire.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.UsersClient;
import ru.lsan.opencode.questionnaire.dto.UserDTO;
import ru.lsan.opencode.questionnaire.tools.EmailService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
@RequestMapping
@RequiredArgsConstructor
@SessionAttributes(types = UserDTO.class)
public class AuthViewController {

    private final UsersClient usersClient;
    private final EmailService emailService;
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static final Base64.Decoder base64Decoder = Base64.getUrlDecoder();

    @GetMapping("/")
    public String index(Model model) {
        UserDTO user = UserDTO.builder().build();
        model.addAttribute("user", user);
        return "auth";
    }

    @PostMapping("/auth")
    public String auth(Model model, UserDTO user) {
        user = usersClient.getByUsername(user).getBody();
        model.addAttribute("user",user);
        return "redirect:/questionnaire/all";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        UserDTO user = UserDTO.builder().build();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(UserDTO user) {
        String rel = user.getUsername() + "/" + user.getPassword();
        String subLink = base64Encoder.encodeToString(rel.getBytes(StandardCharsets.UTF_8));
        String link = "localhost:8080/register/" + subLink;
        emailService.sendSimpleMessage(user.getUsername(),
                "Завершение регистрации <(￣︶￣)>",
                "Для завершения регистрации перейдите по ссылке: " + link);
        return "check";
    }

    @GetMapping("/register/{link}")
    public String finishRegister(Model model, @PathVariable String link) {
        byte[] decodedBytes = base64Decoder.decode(link);
        String decodedLogin = new String(decodedBytes);
        int index = decodedLogin.indexOf("/");
        String login = decodedLogin.substring(0, index);
        String password = decodedLogin.substring(index + 1);
        UserDTO user = UserDTO.builder()
                .username(login)
                .password(password)
                .build();
        usersClient.createUser(user);
        model.addAttribute("user", user);
        return "redirect:/questionnaire/all";
    }

}
