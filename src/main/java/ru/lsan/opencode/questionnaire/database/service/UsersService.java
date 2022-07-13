package ru.lsan.opencode.questionnaire.database.service;

import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;

import java.util.List;

public interface UsersService {

    UserEntity create(UserDTO dto);

    UserEntity findByLogin(String login);

    List<UserEntity> findAll();

}
