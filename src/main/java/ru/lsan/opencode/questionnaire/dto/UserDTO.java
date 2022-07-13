package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserDTO {

    @NotBlank
    String username;

    @NotBlank
    String password;

    public static UserDTO fromUserEntity(UserEntity userEntity){
        return UserDTO.builder()
                .username(userEntity.getLogin())
                .password(userEntity.getPassword())
                .build();
    }

}
