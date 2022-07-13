package ru.lsan.opencode.questionnaire.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.UsersClient;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;
import ru.lsan.opencode.questionnaire.database.service.UsersService;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController implements UsersClient {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UserDTO> getByUsername(@RequestBody UserDTO userDTO){
        UserEntity user = usersService.findByLogin(userDTO.getUsername());
        return ResponseEntity.ok(UserDTO.fromUserEntity(user));
    }

    @PutMapping
    public void createUser(UserDTO userDTO) {
        usersService.create(userDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserEntity> userEntityList = usersService.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity user : userEntityList) {
            userDTOList.add(UserDTO.fromUserEntity(user));
        }
        return ResponseEntity.ok(userDTOList);
    }

}
