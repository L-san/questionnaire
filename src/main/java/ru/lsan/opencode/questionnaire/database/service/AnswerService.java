package ru.lsan.opencode.questionnaire.database.service;

import ru.lsan.opencode.questionnaire.database.entity.AnswerEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;
import ru.lsan.opencode.questionnaire.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {

    AnswerEntity create(AnswerDTO answer, QuestionEntity question);

    AnswerEntity findById(Long id);

    List<AnswerEntity> findByIdAsList(List<Long> id);

    List<AnswerEntity> createAsList(List<AnswerDTO> answers, QuestionEntity question);

}
