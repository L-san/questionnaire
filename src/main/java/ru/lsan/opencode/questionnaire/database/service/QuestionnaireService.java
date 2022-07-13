package ru.lsan.opencode.questionnaire.database.service;

import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;

import java.util.List;
import java.util.Optional;

public interface QuestionnaireService {

    QuestionnaireEntity create(QuestionnaireDTO dto);

    QuestionnaireEntity update(QuestionnaireDTO dto);

    QuestionnaireEntity findById(Long id);

    QuestionnaireEntity findByQuestionId(Long id);

    Integer countTotalAnswersByQuestionnaireId(Long questionnaireId);

    Integer countUserRateOnQuestionnaire(Long questionnaireId);

    List<QuestionnaireDTO> getAll();

    QuestionnaireDTO markUserAnswers(QuestionnaireDTO dto, String username);

    Optional<QuestionnaireEntity> getQuestionnaire();

    List<QuestionnaireDTO> findAllQuestionnairesByUsername(String login);

}
