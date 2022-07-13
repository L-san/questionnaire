package ru.lsan.opencode.questionnaire.database.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lsan.opencode.questionnaire.database.entity.*;
import ru.lsan.opencode.questionnaire.database.repository.UserAnswersRepository;
import ru.lsan.opencode.questionnaire.database.service.*;
import ru.lsan.opencode.questionnaire.dto.UserAnswersDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAnswersServiceImpl implements UserAnswersService {

    private final UserAnswersRepository userAnswersRepository;
    private final StatisticsService statisticsService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Override
    public UserAnswersEntity create(UserAnswersEntity userAnswers) {
        return userAnswersRepository.save(userAnswers);
    }

    @Override
    public List<UserAnswersEntity> createAsList(UserAnswersDTO dto, QuestionnaireEntity questionnaire, StatisticsEntity statistics) {
        List<AnswerEntity> answerEntityList = answerService.findByIdAsList(dto.getAnswerId());
        List<UserAnswersEntity> userAnswersEntityList = new ArrayList<>(answerEntityList.size());
        QuestionEntity question = questionService.findById(dto.getQuestionId());
        for (AnswerEntity answer : answerEntityList) {
            UserAnswersEntity entity = UserAnswersEntity.builder()
                    .answer(answer)
                    .question(question)
                    .statistics(statistics)
                    .build();
            userAnswersEntityList.add(userAnswersRepository.save(entity));
        }
        statisticsService.updateRateById(statistics.getId());
        return userAnswersEntityList;
    }

}
