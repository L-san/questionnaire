package ru.lsan.opencode.questionnaire.database.service;

import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    QuestionEntity create(QuestionDTO dto, QuestionnaireEntity questionnaire);

    List<QuestionEntity> createAsList(List<QuestionDTO> dto, QuestionnaireEntity questionnaire);

    QuestionEntity findById(Long id);

    QuestionEntity update(QuestionDTO dto);

}
