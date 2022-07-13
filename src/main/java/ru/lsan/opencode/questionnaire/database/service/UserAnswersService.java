package ru.lsan.opencode.questionnaire.database.service;

import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserAnswersEntity;
import ru.lsan.opencode.questionnaire.dto.UserAnswersDTO;

import java.util.List;

public interface UserAnswersService {

    UserAnswersEntity create(UserAnswersEntity userAnswers);

    List<UserAnswersEntity> createAsList(UserAnswersDTO dto, QuestionnaireEntity questionnaire,  StatisticsEntity statistics);

}
