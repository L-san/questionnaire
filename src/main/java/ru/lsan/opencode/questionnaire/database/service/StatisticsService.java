package ru.lsan.opencode.questionnaire.database.service;

import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;
import ru.lsan.opencode.questionnaire.dto.StatisticsDTO;

import java.util.List;

public interface StatisticsService {

    StatisticsEntity create(UserEntity user, QuestionnaireEntity questionnaire);

    StatisticsEntity update(StatisticsEntity statistics);

    StatisticsEntity findByUserLoginAndQuestionnaireId(String username, Long id);

    StatisticsEntity updateRateById(Long id);

    List<StatisticsDTO> findStatisticsByQuestionnaireId(Long questionnaireId);

    StatisticsEntity findStatisticsEntitiesByQuestionnaireIdAndUser(Long questionnaireId, String username);

}
