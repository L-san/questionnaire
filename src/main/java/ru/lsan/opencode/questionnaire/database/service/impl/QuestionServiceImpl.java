package ru.lsan.opencode.questionnaire.database.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lsan.opencode.questionnaire.database.entity.AnswerEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.repository.QuestionRepository;
import ru.lsan.opencode.questionnaire.database.service.AnswerService;
import ru.lsan.opencode.questionnaire.database.service.QuestionService;
import ru.lsan.opencode.questionnaire.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    @Override
    public QuestionEntity create(QuestionDTO dto, QuestionnaireEntity questionnaire) {
        QuestionEntity question = QuestionEntity.builder()
                .theme(dto.getValue())
                .questionnaire(questionnaire)
                .build();
        question = questionRepository.save(question);
        List<AnswerEntity> answers = answerService.createAsList(List.copyOf(dto.getAnswers()),question);
        Integer rightAnswersNumber = questionRepository.countRightAnswersNumber(question.getId());
        question.setRightAnswersNumber(rightAnswersNumber);
        question.setAnswers(answers);
        return questionRepository.save(question);
    }

    @Override
    public List<QuestionEntity> createAsList(List<QuestionDTO> dto, QuestionnaireEntity questionnaire) {
        List<QuestionEntity> questionEntities = new ArrayList<>(dto.size());
        for (QuestionDTO questionDTO : dto){
            questionEntities.add(create(questionDTO, questionnaire));
        }
        return questionEntities;
    }

    @Override
    public QuestionEntity findById(Long id) {
        return questionRepository.findById(id).get();
    }


    @Override
    public QuestionEntity update(QuestionDTO dto) {
        return null;
    }

}
