package ru.lsan.opencode.questionnaire.database.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;
import ru.lsan.opencode.questionnaire.database.repository.StatisticsRepository;
import ru.lsan.opencode.questionnaire.database.repository.UserAnswersRepository;
import ru.lsan.opencode.questionnaire.database.service.StatisticsService;
import ru.lsan.opencode.questionnaire.database.service.UserAnswersService;
import ru.lsan.opencode.questionnaire.dto.StatisticsDTO;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final UserAnswersRepository userAnswersRepository;

    @Override
    public StatisticsEntity create(UserEntity user, QuestionnaireEntity questionnaire) {
        StatisticsEntity statistics = StatisticsEntity.builder()
                .user(user)
                .attempt(1)
                .questionnaire(questionnaire)
                .build();
        return statisticsRepository.save(statistics);
    }

    @Override
    public StatisticsEntity update(@Valid StatisticsEntity statistics) {
        if (statistics.getAttempt() < 2) userAnswersRepository.deleteAll(statistics.getUserAnswers());
        StatisticsEntity statisticsEntity = StatisticsEntity.builder()
                .id(statistics.getId())
                .user(statistics.getUser())
                .attempt(statistics.getAttempt() + 1)
                .questionnaire(statistics.getQuestionnaire())
                .build();
        return statisticsRepository.save(statisticsEntity);
    }

    @Override
    public StatisticsEntity findByUserLoginAndQuestionnaireId(String username, Long id) {
        return statisticsRepository.findByUserLoginAndQuestionnaireId(username, id);
    }

    @Override
    public StatisticsEntity updateRateById(Long id) {
        StatisticsEntity statistics = statisticsRepository.findById(id).get();
        Integer rightAnswersCount = statisticsRepository.countRightAnswersNumber(statistics.getId());
        statistics.setRate(rightAnswersCount);
        return statisticsRepository.save(statistics);
    }

    @Override
    public List<StatisticsDTO> findStatisticsByQuestionnaireId(Long questionnaireId) {
        List<StatisticsEntity> statisticsEntities = statisticsRepository.findStatisticsEntitiesByQuestionnaireId(questionnaireId);
        return StatisticsDTO.fromStatisticsEntityList(statisticsEntities);
    }

    @Override
    public StatisticsEntity findStatisticsEntitiesByQuestionnaireIdAndUser(Long questionnaireId, String username) {
        return statisticsRepository.findStatisticsEntitiesByQuestionnaireIdAndUserId(questionnaireId, username);
    }

}
