package ru.lsan.opencode.questionnaire.database.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.lsan.opencode.questionnaire.database.entity.AnswerEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;
import ru.lsan.opencode.questionnaire.database.repository.AnswerRepository;
import ru.lsan.opencode.questionnaire.database.service.AnswerService;
import ru.lsan.opencode.questionnaire.dto.AnswerDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public AnswerEntity create(AnswerDTO answer, QuestionEntity question) {
        AnswerEntity entity = AnswerEntity.builder()
                .theme(answer.getAnswer())
                .isRight(answer.getIsRight())
                .question(question)
                .build();
        return answerRepository.save(entity);
    }

    @Override
    public AnswerEntity findById(Long id) {
        return answerRepository.findById(id).get();
    }

    @Override
    public List<AnswerEntity> findByIdAsList(List<Long> id) {
        List<AnswerEntity> answerEntityList = new ArrayList<>(id.size());
        for (Long i : id) {
            answerEntityList.add(findById(i));
        }
        return answerEntityList;
    }

    @Override
    public List<AnswerEntity> createAsList(List<AnswerDTO> answers, QuestionEntity question) {
        List<AnswerEntity> answerList = new ArrayList<>(answers.size());
        for (AnswerDTO answer : answers) {
            answerList.add(create(answer, question));
        }
        return answerList;
    }

}
