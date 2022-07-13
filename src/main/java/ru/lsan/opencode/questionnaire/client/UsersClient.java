package ru.lsan.opencode.questionnaire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.List;

@FeignClient(name = "users",url="https://localhost:8080/api/users")
public interface UsersClient {

    @PostMapping
    ResponseEntity<UserDTO> getByUsername(@RequestBody UserDTO userDTO);

    @GetMapping("/all")
    ResponseEntity<List<UserDTO>> findAll();

    @PutMapping
    void createUser(@RequestBody UserDTO userDTO);

}
