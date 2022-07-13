package ru.lsan.opencode.questionnaire.database.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserAnswersEntity;
import ru.lsan.opencode.questionnaire.database.repository.QuestionnaireRepository;
import ru.lsan.opencode.questionnaire.database.service.QuestionService;
import ru.lsan.opencode.questionnaire.database.service.QuestionnaireService;
import ru.lsan.opencode.questionnaire.dto.AnswerDTO;
import ru.lsan.opencode.questionnaire.dto.QuestionDTO;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionService questionService;

    @EventListener(ApplicationReadyEvent.class)
    public void initAfterStartup() {
        List<QuestionDTO> dtoList = new ArrayList<>();
        dtoList.add(QuestionDTO.builder()
                .id(1L)
                .value("Есть ли колени?")
                .answers(Set.of(AnswerDTO.of("Да", true), AnswerDTO.of("Нет", false)))
                .build());
        dtoList.add(QuestionDTO.builder()
                .id(2L)
                .value("Есть ли клюв?")
                .answers(Set.of(AnswerDTO.of("Да", true), AnswerDTO.of("Нет", false)))
                .build());
        dtoList.add(QuestionDTO.builder()
                .id(3L)
                .value("Выберите небесные тела, носящие статус планет:")
                .answers(Set.of(AnswerDTO.of("Меркурий", true),
                        AnswerDTO.of("Церера", false),
                        AnswerDTO.of("Нептун", true),
                        AnswerDTO.of("Плутон", false)))
                .build());
        QuestionnaireDTO questionnaireDTO = QuestionnaireDTO.builder()
                .theme("Есть ли у пингвинов колени")
                .questions(dtoList)
                .build();

        QuestionnaireEntity entity = QuestionnaireEntity.builder()
                .theme(questionnaireDTO.getTheme())
                .build();
        entity = questionnaireRepository.save(entity);
        questionService.createAsList(List.copyOf(questionnaireDTO.getQuestions()), entity);
    }

    @Override
    public QuestionnaireEntity create(QuestionnaireDTO dto) {
        QuestionnaireEntity entity = QuestionnaireEntity.builder()
                .theme(dto.getTheme())
                .build();
        entity = questionnaireRepository.save(entity);
        List<QuestionEntity> questionEntities = questionService.createAsList(List.copyOf(dto.getQuestions()), entity);
        entity.setQuestions(Set.copyOf(questionEntities));
        return entity;
    }

    @Override
    public QuestionnaireEntity update(QuestionnaireDTO dto){
        QuestionnaireEntity entity = findById(dto.getId());
        List<QuestionEntity> questionEntities = questionService.createAsList(List.copyOf(dto.getQuestions()), entity);
        Set<QuestionEntity> questions = entity.getQuestions();
        questions.addAll(Set.copyOf(questionEntities));
        entity.setQuestions(questions);
        return entity;
    }

    @Override
    public QuestionnaireEntity findById(Long id) {
        return questionnaireRepository.findById(id).get();
    }

    @Override
    public QuestionnaireEntity findByQuestionId(Long id) {
        return questionnaireRepository.findByQuestionId(id);
    }

    @Override
    public Integer countUserRateOnQuestionnaire(Long questionnaireId) {
        return questionnaireRepository.countUsersRightAnswersNumberOnQuestionnaire(questionnaireId);
    }

    @Override
    public List<QuestionnaireDTO> getAll() {
        List<QuestionnaireDTO> questionnaireDTOList = new LinkedList<>();
        List<QuestionnaireEntity> questionnaireEntities = questionnaireRepository.findAll();

        for (QuestionnaireEntity questionnaire : questionnaireEntities) {
            questionnaireDTOList.add(QuestionnaireDTO.fromQuestionnaireEntity(questionnaire));
        }
        return questionnaireDTOList;
    }

    @Override
    public QuestionnaireDTO markUserAnswers(QuestionnaireDTO dto, String username) {
        List<UserAnswersEntity> userAnswersEntityList = questionnaireRepository.getAnswersOnQuestionnaireForUser(username, dto.getId());
        List<AnswerDTO> userAnswers = new ArrayList<>(userAnswersEntityList.size());

        for (UserAnswersEntity u : userAnswersEntityList) {
            userAnswers.add(AnswerDTO.fromAnswerEntity(u.getAnswer()));
        }

        List<QuestionDTO> questions = dto.getQuestions();
        for (QuestionDTO question : questions) {
            Set<AnswerDTO> answers = question.getAnswers();
            for (AnswerDTO answer : answers) {
                if (userAnswers.contains(answer)) {
                    answer.setIsChosen(true);
                }else{
                    answer.setIsChosen(false);
                }
            }
            question.setAnswers(answers);
        }
        dto.setQuestions(questions);
        return dto;
    }

    @Override
    public Optional<QuestionnaireEntity> getQuestionnaire() {
        return Optional.empty();
    }

    @Override
    public Integer countTotalAnswersByQuestionnaireId(Long questionnaireId) {
        return questionnaireRepository.countTotalAnswersByQuestionnaireId(questionnaireId);
    }

    @Override
    public List<QuestionnaireDTO> findAllQuestionnairesByUsername(String login) {
        List<QuestionnaireDTO> questionnaireDTOList = new LinkedList<>();
        List<QuestionnaireEntity> questionnaireEntities = questionnaireRepository.findAllQuestionnaires(login);
        for (QuestionnaireEntity questionnaire : questionnaireEntities) {
            questionnaireDTOList.add(QuestionnaireDTO.fromQuestionnaireEntity(questionnaire));
        }
        return questionnaireDTOList;
    }

}
