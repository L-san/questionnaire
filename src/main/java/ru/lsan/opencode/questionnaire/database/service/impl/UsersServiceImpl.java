package ru.lsan.opencode.questionnaire.database.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;
import ru.lsan.opencode.questionnaire.database.repository.UserRepository;
import ru.lsan.opencode.questionnaire.database.service.UsersService;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;
import ru.lsan.opencode.questionnaire.enums.RoleEnum;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void initAfterStartup() {
        UserEntity userEntity = UserEntity.builder()
                .login("admin@app.com")
                .password(passwordEncoder.encode("admin"))
                .role(RoleEnum.ADMIN)
                .build();
        userRepository.save(userEntity);

        userEntity = UserEntity.builder()
                .login("user@app.com")
                .password(passwordEncoder.encode("user"))
                .role(RoleEnum.USER)
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity create(UserDTO dto) {
        UserEntity user = UserEntity.builder()
                .login(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(RoleEnum.USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

}
